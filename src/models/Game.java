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
/**
 * Game Class
 * {
 *     Author: Jordaine Gayle
 *     StudentId: 1800708
 *     Desc: defines the  structure
 * }
 * */
public class Game
{
    //constants that are used to compare against round result
    protected final int WON_ROUND = 1, ACTIVE_ROUND = 2,LOST_ROUND = 3;

    //wheel list
    protected Wheel Wheel;

    //vowels array
    protected char[] Vowels;

    //words database
    protected WordsDB Database;

    //rounds array
    protected Round[] Rounds;

    //players list
    protected structures.Players Players;

    //current round value
    protected int CurrentRound;

    //number of players
    protected int NumOfPlayers;

    //current player node
    protected PlayerNode CurrentPlayerNode;

    //default constructor
    public Game() throws Exception
    {
        InitGame(3);
    }

    //primary constructor
    public Game(int numOfPlayers) throws Exception
    {
        if(numOfPlayers <= 1){
            numOfPlayers = 3;
        }
        InitGame(numOfPlayers);
    }

    //init game values
    private void InitGame(int numOfPlayers) throws Exception
    {
        //init the vowels array
        this.Vowels = new char[]{'A','E','I','O','U'};

        //init the words database
        this.Database = new WordsDB();

        //init the players of the game
        this.Players = new Players();

        //init the number of players
        NumOfPlayers = numOfPlayers;

        //creating the players
        CreatePlayers();

        //init values
        InitDefaultValues();

        //begin the gameplay
        Gameplay();
    }

    //init the default values
    private void InitDefaultValues() throws Exception
    {
        this.Rounds = new Round[3];
        CreateRounds();
        this.CurrentRound = 0;
        Wheel = new Wheel();
        NumOfPlayers = Players.GetLength();
        this.CurrentPlayerNode = this.Players.GetPlayerByNumber(1);
    }

    //create the rounds
    private void CreateRounds(){
        for (int x = 0; x < this.Rounds.length; x++){
            Word word = Database.GetRandomWord();
            Round round = null;
            while (round == null){
                try{
                    round = new Round(Categories.valueOf(word.GetCategory()),word.GetWord(),this.NumOfPlayers);
                }
                catch (Exception e)
                {
                    round = null;
                }
            }
            this.Rounds[x] = round;
        }
    }

    //create the game players
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

    //starts the game
    private void Gameplay()
    {
        //stores the length of the round
        int roundLength = this.Rounds.length;

        //continues until the current is less than the round length
        while(CurrentRound < roundLength)
        {
            //get the current player from the current node
            Player player = CurrentPlayerNode.GetData();

            //gets the current round
            Round round = Rounds[CurrentRound];

            //initialize the round result as 0
            int roundResult = 0;

            //print the current players info
            PlayersInfo(player);

            //gets the result of Guess letter
            roundResult = GuessLetter();

            //determine if the player won the round
            if(roundResult == WON_ROUND)
            {
                //added the round total to the grand total of the winner
                player.SetGrandTotal(round.GetPlayerTotal(player.GetNumber()-1));

                //displaying the end of the round
                System.out.println("WON_ROUND");

                //increment the current round by 1
                CurrentRound++;
            }
            //determine if the player lost the round
            else if(roundResult == LOST_ROUND)
            {
                //advancing the current not to the next node when the player lost the round
                CurrentPlayerNode = CurrentPlayerNode.GetNextNode();

                //getting the player after moving to the next player
                player = CurrentPlayerNode.GetData();

                //decreasing the round attempts
                round.UpdateRoundAttempt();

                //check if we have no more attempts left
                if(round.GetRoundAttempt() < 1)
                {
                    //advancing the round to the next round if no more attempt is left
                    CurrentRound++;
                }
            }
            else
            {
                //displaying that the round i still active
                System.out.println("ACTIVE_ROUND");
            }
        }

        //calling the game restart menu when all round have completed
        InGameMenu();
    }

    //handle the letters each user guesses
    private int GuessLetter()
    {
        //hold whether the player bought a vowel
        boolean boughtVowel = false;

        //gets the current round
        Round round = this.Rounds[this.CurrentRound];

        //gets the current player
        Player player = CurrentPlayerNode.GetData();

        //check if the player has money in the round
        if(round.GetPlayerTotal(player.GetNumber() - 1) <= 0)
        {
            //prompt the player to spin the wheel
            int spin = SpinWheel();

            //determine if result of the spin will advance the player further or loses the round.
            if(spin == LOST_ROUND)return LOST_ROUND;
        }

        //gets the current letter that entered into the console
        char letter = Menu();

        //check if the player wants to spin again
        while(letter == '^')
        {
            //hold the spin result
            int spin = WheelSpinner();

            //determine if the player advances any further based on the spin result
            if(spin == LOST_ROUND)return LOST_ROUND;

            //if the player should advance we get the letter guessed
            letter = Menu();
        }

        //determine if the player wants to buy a vowel
        if(letter == '_')
        {
            //automate the buying vowel process
            int vowel = BuyAVowel();

            //if the response is negative then we go a recursive call to GuesslLetter and return that
            if(vowel == -1) return GuessLetter();

            //if a valid vowel was present then we convert the ascii value for the int to a char and set it to the letter
            letter = (char)vowel;

            //once a player buys a vowel we set bought vowel to true
            boughtVowel = true;
        }

        //check if the letter is a valid letter or guessed already
        if(!round.IsGuessable(letter))
        {
            //display that the letter was already entered
            System.out.println("Letter Guessed Already, Try Again.");

            //do a recursive call for the player to guess again
            return GuessLetter();
        }
        //if the letter is indeed guessable we then enter the else block
        else
        {
            //retrieve all occurances of the letter entered
            KVP kvp = round.IsValidLetter(letter);

            //if the letter is found in the hash we enter the if block
            if(kvp != null)
            {
                //update the round with letter found
                round.UpdateWord(kvp);

                //calculating the round total based on the current card and the occurance of the letters found
                double roundTotal = kvp.GetCount() * Wheel.GetCurrentCard().GetValue();

                //check if the player bought a vowel as to not update their total if they did
                if(!boughtVowel)
                {
                    //updates the player total if they played the game and didn't buy a letter for the current guess
                    round.UpdatePlayerTotal(CurrentPlayerNode.GetData().GetNumber() - 1,roundTotal);
                }

                //deletes the letter from the hash when it guessed correctly
                round.DeleteLetter(letter);

                //check if the word is solved and return won_round
                if(round.SolvedWord()) return WON_ROUND;

                //if the round haven't been won or lost then return that it's active
                return ACTIVE_ROUND;
            }
        }

        //if the round was lost we display the message to the user
        System.out.println("Nice try, but you lost the round. Incorrect Letter.");

        //returns lost round
        return LOST_ROUND;
    }

    //handle the purchasing of vowels
    private int BuyAVowel()
    {
        //gets the current round
        Round round = this.Rounds[CurrentRound];

        //initialize the key value pair to null
        KVP kvp = null;

        //gets the current card amount
        double cardAmount = Wheel.GetCurrentCard().GetValue();

        //calculates the current player index
        int playerIndex = CurrentPlayerNode.GetData().GetNumber()-1;

        //gets the player total for the round so far
        double amount = round.GetPlayerTotal(playerIndex);

        //if the player amount is less than or equal to 0 then we display an error message and return -1 to have the player guess again
        if(amount <= 0)
        {
            //error messages displayed
            System.out.println("You do not have enough money to guess a vowel.");

            //returns the error code
            return -1;
        }

        //loops through the hash based on the length of the vowels
        for (int x = 0; x < this.Vowels.length; x++)
        {
            //check if the vowel is guessable
            if(round.IsGuessable(this.Vowels[x]))
            {
                //gets the current vowel at the position of the hash equavalent of the item at the x position in the vowel list
                kvp = round.GetKVP(this.Vowels[x]);

                //checks if a vowel was found and that the player has enough money to purchase that vowel
                if(kvp != null && (cardAmount * kvp.GetCount()) <= amount)
                {
                    //gets the letter value of the vowel
                    double letterValue = cardAmount * kvp.GetCount();

                    //subtract the letter value from the players round total
                    round.UpdatePlayerTotal(playerIndex,letterValue*-1);

                    //display the amount left on the players account
                    System.out.println("Vowel Bought: "+kvp.GetKey());
                    System.out.println("Current Round Total: $"+amount);
                    System.out.println("Vowel Price: $"+letterValue);
                    System.out.println("Round Balance: $"+round.GetPlayerTotal(playerIndex));

                    //return the letter guessed by the buy vowel
                    return kvp.GetKey();
                }
            }

        }

        //if no vowel was in the word then we return the error message
        System.out.println("Unable to find a vowel to match your request, or you don't have enough money to purchase a vowel");

        //return the error code to have the user guess again
        return -1;
    }

    //handle the spinnign fo the wheel
    private int SpinWheel()
    {
        //prompt the user to spin the wheel
        Scanner sc = new Scanner(System.in);

        System.out.print("\nPress '^' to spin the wheel: ");
        char spin = sc.next().toLowerCase().charAt(0);

        //check the spin result to see if the current letter was pressed
        if(spin == '^')
        {
            //display the return result
            System.out.print("\nSpin Result: ");

            //return the result of the spin
            return WheelSpinner();
        }
        //do a recursive call if an invalid input was detected
        else
        {
            System.out.println("Invalid input spin the wheel again '^'");
            return SpinWheel();
        }
    }

    //displays the game menu
    private char Menu()
    {
        //gets the current round
        Round round = this.Rounds[this.CurrentRound];

        //display the round menu
        System.out.println("\nCategory: "+round.GetCategory());
        System.out.println("Puzzle: "+round.GetWordToSolve());
        round.DisplayGuessedLetters();
        Scanner sc = new Scanner(System.in);
        System.out.println("\nPress '_' to purchase a vowel");
        System.out.println("Press '^' to spin again");
        System.out.print("\nMake A Lucky Guess: ");
        //getting the letter entered
        char letter = sc.next().toUpperCase().charAt(0);
        return letter;
    }

    //handle the gaem when for playing again
    private void InGameMenu()
    {
        //display the winner
        DisplayWinner();
        //display the game menu
        System.out.println("\nIn Game Menu");
        try
        {
            //call the play again function
            PlayAgain();
        }
        catch (Exception e)
        {
            //if an error occured while calling the play again we display an error message
            System.out.println("The game failed to start again. Please restart and try again.");
            //we further return a 500 error code and exit the system
            System.exit(500);
        }
    }

    //handle the play again logic
    private void PlayAgain() throws Exception{

        //stores the value of the amount of players willing to play the game again
        int playAgain = 0;

        //check that the Players length is at least 2 or more
        while (Players.GetLength() >= 2)
        {
            //if the play again is equal to the leght of the players then we exist the loop
            if(playAgain == Players.GetLength())
            {
                break;
            }

            //prompt the user to enter if they want to play again or not
            System.out.print(CurrentPlayerNode.GetData().GetName().toUpperCase()+" do you wish to play again ? (y or n): ");

            //get the user answer
            Scanner sc = new Scanner(System.in);

            //gets the letter that was entered
            char letter = sc.next().toLowerCase().charAt(0);

            //if the player wants to play again then play again increases by 1
            if(letter == 'y')
            {
                //once the current player accepts they want to play again we advance to the next player
                CurrentPlayerNode = CurrentPlayerNode.GetNextNode();

                //incrementing the play again variable by one
                playAgain++;
            }
            //if the player selects no then we enter the else block
            else if (letter == 'n')
            {
                //we delete the player from the game
                Players.DeleteAt(CurrentPlayerNode.GetData().GetNumber());

                //display the player that left
                System.out.println(CurrentPlayerNode.GetData().GetName().toUpperCase()+" left.");

                //advances to the next player
                CurrentPlayerNode = CurrentPlayerNode.GetNextNode();
            }
            //if the user entered and invalid input, we kindly ask them to enter a valid input
            else
            {
                //display that the users input was invalid
                System.out.println("Invalid input. Please enter a valid input 'y' or 'n' ");

                //recursively called the player again function
                PlayAgain();
            }
        }

        //if the players length is less than 2 then we cancel the game
        if(Players.GetLength() < 2)
        {
            System.out.println("Thanks for playing, GOODBYE :). Hope to see you again, sn maybe ? Anyways thanks for playing!!!.");
            System.exit(0);
        }
        //if the previous condition failed then we enter the else block
        else
        {
            //reset the game values
            InitDefaultValues();

            //reset the players grand total
            Players.ResetGrandTotals();

            //call the game play again
            Gameplay();
        }
    }

    //handle the spinning of the actual wheel
    private int WheelSpinner()
    {
        //getting the current round
        Round round = this.Rounds[this.CurrentRound];
        //getting the current player
        Player player = CurrentPlayerNode.GetData();
        //spin the wheel
        Wheel.SpinWheel();
        //get the current card after the wheel is spined and display its contents
        Wheel.GetCurrentCard().ToString();

        //if the current card is not a money card then the player lost the round
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

        //return success if we landed a money card
        return 0;
    }

    //displays the player's info
    private void PlayersInfo(Player player)
    {
        //displays the players info
        System.out.print("\nRound Number: "+(CurrentRound+1)+"\t");
        System.out.print("Player Number: "+player.GetNumber()+"\t");
        System.out.print("Player Name: "+player.GetName());
    }

    //determine and displays the winner
    private void DisplayWinner()
    {
        //getting the current node
        PlayerNode playerNode = Players.GetPlayerByNumber(1);
        Player player = playerNode.GetData();
        double max = player.GetGrandTotal();
        int index = 0;

        //looping through the players list to find the winner
        for(int x = 0; x < NumOfPlayers; x++)
        {
            //setting the max
            double newMax = player.GetGrandTotal();

            if(newMax > max)
            {
                //updates the max if it;s more than the current max
                max = newMax;
                index = x;
            }

            //moving to the next player in line
            playerNode = playerNode.GetNextNode();

            //getting the current node
            player = playerNode.GetData();
        }

        //if the max is less than or equal to 0 then we have no winner
        if(max <= 0)
        {
            System.out.println("\nSorry!, nobody won the match.");
        }
        //if there is a value, then we display the winner
        else
        {
            playerNode = Players.GetPlayerByNumber(index+1);
            player = playerNode.GetData();
            System.out.println("\nCongratulations "+player.GetName()+" you won the game!!!!");
            System.out.println("Grand Prize: $"+max);
        }
    }

}
