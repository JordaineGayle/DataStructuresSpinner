package models;

import com.sun.javaws.exceptions.InvalidArgumentException;
import database.WordsDB;
import models.enums.CardTypes;
import models.enums.Categories;
import structures.KVP;
import structures.Nodes.PlayerNode;
import structures.Players;
import structures.Wheel;

import java.util.Scanner;

public class Game {
    private final int WON_ROUND = 1, ACTIVE_ROUND = 2,LOST_ROUND = 3;
    private Wheel Wheel;
    private char[] Vowels;
    private WordsDB Database;
    private Round[] Rounds;
    private structures.Players Players;
    private int CurrentRound;
    private int NumOfPlayers;
    private PlayerNode CurrentPlayerNode;

    public Game() throws Exception
    {
        InitGame(3);
    }

    public Game(int numOfPlayers) throws Exception
    {
        if(numOfPlayers <= 1){
            numOfPlayers = 3;
        }
        InitGame(numOfPlayers);
    }

    private void InitGame(int numOfPlayers) throws Exception
    {
        this.Vowels = new char[]{'A','E','I','O','U'};
        this.Database = new WordsDB();
        this.Players = new Players();
        NumOfPlayers = numOfPlayers;
        CreatePlayers();
        InitDefaultValues();
        Gameplay();
    }

    private void InitDefaultValues() throws Exception
    {
        this.Rounds = new Round[3];
        CreateRounds();
        this.CurrentRound = 0;
        Wheel = new Wheel();
        this.CurrentPlayerNode = this.Players.GetPlayerByNumber(1);
    }

    private void DisplayWinner()
    {
        PlayerNode playerNode = Players.GetPlayerByNumber(1);
        Player player = playerNode.GetData();
        double max = player.GetGrandTotal();
        int index = 0;
        for(int x = 0; x < NumOfPlayers; x++)
        {
            double newMax = player.GetGrandTotal();
            if(newMax > max)
            {
                max = newMax;
                index = x;
            }
            playerNode = playerNode.GetNextNode();
            player = playerNode.GetData();
        }

        if(max <= 0)
        {
            System.out.println("Sorry!, nobody won the match.");
        }
        else
        {
            playerNode = Players.GetPlayerByNumber(index+1);
            player = playerNode.GetData();
            System.out.println("Congratulations "+player.GetName()+" you won the game!!!!");
            System.out.println("Grand Prize: $"+max);
        }
    }

    private void Gameplay()
    {
        int roundLength = this.Rounds.length;
        while(CurrentRound < roundLength)
        {
            Player player = CurrentPlayerNode.GetData();
            Round round = Rounds[CurrentRound];
            int roundResult = 0;
            ClearScreen();
            PlayersInfo(player);
            roundResult = GuessLetter();

            if(roundResult == WON_ROUND)
            {
                player.SetGrandTotal(round.GetPlayerTotal(player.GetNumber()-1));
                System.out.println("WON_ROUND");
                CurrentRound++;
            }
            else if(roundResult == LOST_ROUND)
            {
                CurrentPlayerNode = CurrentPlayerNode.GetNextNode();
                player = CurrentPlayerNode.GetData();
                round.UpdateRoundAttempt();
                if(round.GetRoundAttempt() < 1)
                {
                    CurrentRound++;
                }
            }
            else
            {
                System.out.println("ACTIVE_ROUND");
            }
        }
        InGameMenu();
    }

    private void CreateRounds(){
        for (int x = 0; x < this.Rounds.length; x++){
            Word word = Database.GetRandomWord();
            Round round = null;
            while (round == null){
                try{
                    round = new Round(Categories.valueOf(word.GetCategory()),word.GetWord(),this.NumOfPlayers);
                }catch (InvalidArgumentException e){
                    round = null;
                }
            }
            this.Rounds[x] = round;
        }
    }

    private void CreatePlayers() throws Exception {
        Scanner sc= new Scanner(System.in);
        for (int x =0; x < NumOfPlayers; x++){
            Player player = new Player();
            System.out.print("Enter your name: ");
            player.SetName(sc.nextLine());
            this.Players.Append(player);
        }

        //this.Players.Display();
    }


    private void InGameMenu()
    {
        ClearScreen();
        DisplayWinner();
        System.out.println("In Game Menu");
        System.out.print("Do you all wish to play again ? (y or n): ");
        Scanner sc = new Scanner(System.in);
        char letter = sc.next().toLowerCase().charAt(0);
        if(letter == 'y')
        {
            try
            {
                InitDefaultValues();
                Gameplay();
            }catch (Exception e)
            {
                e.printStackTrace();
                System.out.println("unable to play again, try restarting thee game.");
                System.exit(0);
            }

        }
        else
        {
            System.out.println("Thanks for playing, GOODBYE :). Hope to see you again, sn maybe ? Anyways thanks for playing!!!.");
            System.exit(0);
        }
    }

    private int SpinWheel()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nPress '^' to spin the wheel: ");
        char spin = sc.next().toLowerCase().charAt(0);
        if(spin == '^')
        {
            System.out.print("\nSpin Result: ");
            return WheelSpinner();
        }
        else
        {
            System.out.println("Invalid input spin the wheel again '^'");
            return SpinWheel();
        }
    }

    private int WheelSpinner()
    {
        Round round = this.Rounds[this.CurrentRound];
        Player player = CurrentPlayerNode.GetData();
        Wheel.SpinWheel();
        Wheel.GetCurrentCard().ToString();
        if(Wheel.GetCurrentCard().GetType() != CardTypes.MONEY)
        {
            if(Wheel.GetCurrentCard().GetType() == CardTypes.BANKRUPTCY)
            {
                double roundTotal = round.GetPlayerTotal(player.GetNumber() - 1) * -1;
                round.UpdatePlayerTotal(player.GetNumber() - 1,roundTotal);
            }
            System.out.println("You lost the round, Sorry! better luck next time.");
            return LOST_ROUND;
        }

        return 0;
    }

    private int BuyAVowel()
    {
        Round round = this.Rounds[CurrentRound];
        KVP kvp = null;
        double cardAmount = Wheel.GetCurrentCard().GetValue();
        int playerIndex = CurrentPlayerNode.GetData().GetNumber()-1;
        double amount = round.GetPlayerTotal(playerIndex);
        if(amount <= 0){
            System.out.println("You do not have enough money to guess a vowel.");
            return -1;
        }

        for (int x = 0; x < this.Vowels.length; x++)
        {
            if(round.IsGuessable(this.Vowels[x]))
            {
                kvp = round.GetKVP(this.Vowels[x]);

                if(kvp != null && (cardAmount * kvp.GetCount()) <= amount)
                {
                    double letterValue = cardAmount * kvp.GetCount();
                    round.UpdatePlayerTotal(playerIndex,letterValue*-1);
                    System.out.println("Vowel Bought: "+kvp.GetKey());
                    System.out.println("Current Round Total: $"+amount);
                    System.out.println("Vowel Price: $"+letterValue);
                    System.out.println("Round Balance: $"+round.GetPlayerTotal(playerIndex));
                    return kvp.GetKey();
                }
            }

        }

        System.out.println("Unable to find a vowel to match your request, or you don't have enough money to purchase a vowel");
        return -1;
    }

    private char Menu()
    {
        Round round = this.Rounds[this.CurrentRound];
        System.out.println("\nCategory: "+round.GetCategory());
        System.out.println("Puzzle: "+round.GetWordToSolve());
        round.DisplayGuessedLetters();
        Scanner sc = new Scanner(System.in);
        System.out.println("\nPress '_' to purchase a letter");
        System.out.println("Press '^' to spin again");
        System.out.print("\nMake A Lucky Guess: ");
        char letter = sc.next().toUpperCase().charAt(0);
        return letter;
    }

    private int GuessLetter()
    {
        boolean boughtVowel = false;
        Round round = this.Rounds[this.CurrentRound];
        Player player = CurrentPlayerNode.GetData();
        if(round.GetPlayerTotal(player.GetNumber() - 1) <= 0)
        {
            int spin = SpinWheel();
            if(spin == LOST_ROUND)return LOST_ROUND;
        }
        char letter = Menu();
        while(letter == '^')
        {
            int spin = WheelSpinner();
            if(spin == LOST_ROUND)return LOST_ROUND;
            letter = Menu();
        }

        if(letter == '_')
        {
            int vowel = BuyAVowel();
            if(vowel == -1) return GuessLetter();
            letter = (char)vowel;
            boughtVowel = true;
        }

        if(!round.IsGuessable(letter)){
            System.out.println("Letter Guessed Already, Try Again.");
            return GuessLetter();
        }
        else
        {
            KVP kvp = round.IsValidLetter(letter);
            if(kvp != null)
            {
                round.UpdateWord(kvp);
                double roundTotal = kvp.GetCount() * Wheel.GetCurrentCard().GetValue();
                if(!boughtVowel)
                {
                    round.UpdatePlayerTotal(CurrentPlayerNode.GetData().GetNumber() - 1,roundTotal);
                }
                round.DeleteLetter(letter);
                if(round.SolvedWord()) return WON_ROUND;
                return ACTIVE_ROUND;
            }
        }

        System.out.println("Nice try, but you lost the round. Incorrect Letter.");
        return LOST_ROUND;
    }

    private void ClearScreen()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void PlayersInfo(Player player)
    {
        System.out.print("\nRound Number: "+(CurrentRound+1)+"\t");
        System.out.print("Player Number: "+player.GetNumber()+"\t");
        System.out.print("Player Name: "+player.GetName());
    }
}
