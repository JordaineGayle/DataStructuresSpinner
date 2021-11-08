package models;

import com.sun.javaws.exceptions.InvalidArgumentException;
import database.WordsDB;
import models.Round;
import models.enums.CardTypes;
import models.enums.Categories;
import structures.KVP;
import structures.Nodes.SinglyGenericNode;
import structures.Players;
import structures.Wheel;

import java.util.Scanner;

public class Game {
    private final int WON_ROUND = 1, ACTIVE_ROUND = 2,LOST_ROUND = 3;
    private Wheel Wheel;
    private WordsDB Database;
    private Round[] Rounds;
    private structures.Players Players;
    private int CurrentRound;
    private int NumOfPlayers;
    private SinglyGenericNode<Player> CurrentPlayerNode;

    public Game() throws Exception
    {
        this.Database = new WordsDB();
        this.Players = new Players();
        NumOfPlayers = 3;
        CreatePlayers();
        this.Rounds = new Round[3];
        CreateRounds();
        this.CurrentRound = 0;
        Wheel = new Wheel();
        this.CurrentPlayerNode = this.Players.GetPlayerByNumber(1);
        Gameloop();
    }

    public Game(int numOfPlayers) throws Exception
    {
        if(numOfPlayers <= 1){
            numOfPlayers = 3;
        }
        this.Database = new WordsDB();
        this.Players = new Players();
        NumOfPlayers = numOfPlayers;
        CreatePlayers();
        this.Rounds = new Round[3];
        CreateRounds();
        this.CurrentRound = 0;
        Wheel = new Wheel();
        this.CurrentPlayerNode = this.Players.GetPlayerByNumber(1);
        Gameloop();
    }

    private void Gameloop()
    {
        Round round = Rounds[CurrentRound];
        Player player = CurrentPlayerNode.GetData();
        int roundResult = 0;
        int roundLength = this.Rounds.length;
        while(CurrentRound < roundLength)
        {
            System.out.println("Round Number: "+(CurrentRound+1));
            System.out.println("Player Info");
            System.out.println("Player Number: "+player.GetNumber());
            System.out.println("Player Name: "+player.GetName());
            char result = Menu();
            if(result == 'a')
            {
                Wheel.SpinWheel();
                System.out.print("Spin Result: ");
                Wheel.GetCurrentCard().ToString();
                if(Wheel.GetCurrentCard().GetType() != CardTypes.MONEY)
                {
                    if(Wheel.GetCurrentCard().GetType() == CardTypes.BANKRUPTCY)
                    {
                        double roundTotal = round.GetPlayerTotal(player.GetNumber() - 1) * -1;
                        round.UpdatePlayerTotal(player.GetNumber() - 1,roundTotal);
                    }

                    roundResult = LOST_ROUND;
                }
                else
                {
                    roundResult = GuessLetter();
                }

            }
            else if(result == 'b')
            {
                //add buy vovel option
            }
            else if(result == 'c')
            {
                roundResult = GuessLetter();
            }
            else
            {
                Gameloop();
            }

            if(roundResult == WON_ROUND)
            {
                CurrentRound++;
                round = Rounds[CurrentRound];
                player.SetGrandTotal(round.GetPlayerTotal(player.GetNumber()-1));
            }
            else if(roundResult == LOST_ROUND)
            {
                CurrentPlayerNode = CurrentPlayerNode.GetNextNode();
                player = CurrentPlayerNode.GetData();
                round = Rounds[CurrentRound];
                round.UpdateRoundAttempt();
                if(round.GetRoundAttempt() <= 0)
                {
                    CurrentRound++;
                }
            }

            roundResult = 0;

        }
    }

    private void CreateRounds(){
        for (int x = 0; x < 3; x++){
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
            //this.Rounds[x].ToString();
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

    private char Menu()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Menu");
        System.out.println("a) Spin Wheel");
        System.out.println("b) Buy A Vowel");
        System.out.println("c) Solve Puzzle");
        System.out.print("Enter option: ");
        char option = sc.next().toLowerCase().charAt(0);
        return option;
    }

    private int GuessLetter()
    {

        Round round = this.Rounds[this.CurrentRound];
        System.out.println("Puzzle: "+round.GetWordToSolve());
        round.DisplayGuessedLetters();
        Scanner sc = new Scanner(System.in);
        System.out.print("\nMake A Lucky Guess: ");
        char letter = sc.next().toUpperCase().charAt(0);
        if(!round.IsGuessable(letter)){
            System.out.println("Letter Guessed Already, Try Again.");
            GuessLetter();
        }
        else
        {
            KVP kvp = round.IsValidLetter(letter);
            if(kvp != null)
            {
                round.UpdateWord(kvp);
                double roundTotal = kvp.GetValues().length * Wheel.GetCurrentCard().GetValue();
                round.UpdatePlayerTotal(CurrentPlayerNode.GetData().GetNumber() - 1,roundTotal);

                if(round.SolvedWord())
                    return WON_ROUND;
                return ACTIVE_ROUND;
            }
        }
        return LOST_ROUND;
    }
}
