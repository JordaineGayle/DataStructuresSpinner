package models;
import com.sun.javaws.exceptions.InvalidArgumentException;
import models.enums.Categories;
import structures.CustomDictionary;
import structures.GuessedLetters;
import structures.KVP;
import java.util.Random;
/**
 * Round Class
 * {
 *     Author: Jordaine Gayle
 *     StudentId: 1800708
 *     Desc: defines the  structure
 * }
 * */
public class Round {

    //category of the word/phrase to be guessed
    private String Category;

    //the word/phrase to be guessed
    private String Puzzle;

    //the modified puzzle word
    private String WordToSolve;

    //letter that have already been guessed
    private GuessedLetters GuessedLetters;

    //holds the letters that are missing in a grouped format
    private CustomDictionary Dictionary;

    //holds the player totals
    private double[] PlayerTotals;

    //holds the round attempts
    private int RoundAttempt;

    //default constructor
    public Round(){}

    //primary constructor
    public Round(Categories category, String puzzle, int numOfPlayers) throws Exception
    {
        SetPuzzle(puzzle);
        SetCategory(category.name());
        SetWordToSolve(CreatePuzzle(puzzle));
        SetGuessedLetters(new GuessedLetters());
        SetPlayerTotals(new double[numOfPlayers]);
        SetRoundAttempt(numOfPlayers);
    }

    //copy constructor
    public Round(Round round) throws Exception
    {
        SetCategory(round.GetCategory());
        SetPuzzle(round.GetPuzzle());
        SetWordToSolve(round.GetWordToSolve());
        SetGuessedLetters(round.GetGuessedLetters());
        SetDictionary(round.GetDictionary());
        SetPlayerTotals(round.GetPlayerTotals());
        SetRoundAttempt(round.GetRoundAttempt());
    }

    //accessors

    public String GetCategory()
    {
        return Category;
    }

    public String GetPuzzle()
    {
        return Puzzle;
    }

    public String GetWordToSolve()
    {
        return WordToSolve;
    }

    public structures.GuessedLetters GetGuessedLetters()
    {
        return GuessedLetters;
    }

    public CustomDictionary GetDictionary()
    {
        return Dictionary;
    }

    public double[] GetPlayerTotals()
    {
        return PlayerTotals;
    }

    //mutators

    public void SetPuzzle(String puzzle) throws Exception
    {
        if(!IsValidWord(puzzle))
            throw new InvalidArgumentException(new String[] {"puzzle cannot have more space than letters"});
        Puzzle = puzzle;
    }

    public void SetWordToSolve(String wordToSolve) {
        WordToSolve = wordToSolve;
    }

    public void SetCategory(String category)
    {
        Category = category;
    }

    public void SetGuessedLetters(structures.GuessedLetters guessedLetters)
    {
        GuessedLetters = guessedLetters;
    }

    public void SetDictionary(CustomDictionary dictionary)
    {
        Dictionary = dictionary;
    }

    public void SetPlayerTotals(double[] playerTotals)
    {
        PlayerTotals = playerTotals;
    }

    public void SetRoundAttempt(int roundAttempt)
    {
        RoundAttempt = roundAttempt;
    }

    //check if a letter is guessable
    public boolean IsGuessable(char letter)
    {
        return GuessedLetters.InQueue(letter);
    }

    //determines if a letter is valid
    public KVP IsValidLetter(char letter)
    {
        try
        {
            //queue the letter that's guessed
            GuessedLetters.Enqueue(letter);
        }
        //supresses this error IE not a good practice
        catch (Exception e)
        {}

        return Dictionary.GetKVP(letter);
    }

    //get the key value pair for a letter and it's ocuurences
    public KVP GetKVP(char letter)
    {
        return Dictionary.GetKVP(letter);
    }

    //delete a letter and it's ocurrances
    public KVP DeleteLetter(char letter)
    {
        return Dictionary.DeleteAt(letter);
    }

    //update a word with it's letter occurances
    public void UpdateWord(KVP kvp)
    {
        StringBuilder builder = new StringBuilder(this.WordToSolve);
        String[] values = kvp.GetValues();
        char key = kvp.GetKey();
        for(int x = 0; x < values.length; x++)
        {
            if(values[x] != null)
            {
                builder.setCharAt(Integer.parseInt(values[x]),key);
            }
        }
        this.WordToSolve = builder.toString();
        System.out.println("Updated word: "+this.WordToSolve);
    }

    //determine if the word to solve and the puzzle are equal to determine if the word is solved correctly
    public boolean SolvedWord()
    {
        return Puzzle.equals(WordToSolve);
    }

    //updates the players total
    public void UpdatePlayerTotal(int index, double amount)
    {
        double currentAmount = PlayerTotals[index];
        currentAmount+= amount;
        PlayerTotals[index] = currentAmount;
    }

    //getting a specifc player total
    public double GetPlayerTotal(int index)
    {
        return PlayerTotals[index];
    }

    //decreasing the round attempt
    public void UpdateRoundAttempt()
    {
        RoundAttempt--;
    }

    //getting the round attempt
    public int GetRoundAttempt()
    {
        return this.RoundAttempt;
    }

    //display the guessed letters
    public void DisplayGuessedLetters(){
        GuessedLetters.Display();
    }

    //creating the puzzle word
    private String CreatePuzzle(String word){

        //getting the current length of the puzzle
        int wordLength = word.length();

        //creating a custom hash with the length of the word
        this.Dictionary = new CustomDictionary(wordLength);

        //creates a copy of the current word - it's a value type so the references are fine
        StringBuilder newWord = new StringBuilder(word);

        //create a rand seed generator
        Random rand = new Random();

        //defining a word percentage removal
        double percentage = 0.70;
        //getting the max letters to be removed

        int maxLetters = (int)(wordLength * percentage);
        //stores the position used

        String[] positionUsed = new String[wordLength];
        //running the loop until there is no more letter to remove
        while (maxLetters > 0)
        {
            //getting a random index of the word
            int index = rand.nextInt(wordLength);
            //getting the letter the of the guessed index
            char letter = word.charAt(index);
            //checking that the letter is no space and that is haven't been entered
            if(letter != ' ' && positionUsed[index] == null){

                //replacing the letter with _
                newWord.setCharAt(index,'_');

                //storing the position used
                positionUsed[index] = new String(new char[] {letter});

                //sorting the missing letters in the hash/dictionary and their occurances if any
                Dictionary.Insert(letter,index);

                //decreasing the max letters that can be blocked out
                maxLetters--;
            }
        }
        return newWord.toString();
    }

    //determine if a word is valid
    private boolean IsValidWord(String word){
        if(word == null || word.isEmpty()) return false;
        double wordPercentage = 0.70;
        double spacePercentage = word.split(" ").length / 100;
        if(spacePercentage >= wordPercentage) return false;
        return true;
    }

    //displaying the contents of a round
    public void ToString()
    {
        System.out.println("[Category: "+this.Category+", Puzzle: "+this.Puzzle+", WordToSolve: "+WordToSolve+", PlayerTotals: "+this.PlayerTotals+"]");
    }
}
