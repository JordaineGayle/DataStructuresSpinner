package models;

import com.sun.javaws.exceptions.InvalidArgumentException;
import models.enums.Categories;
import structures.CustomDictionary;
import structures.GuessedLetters;
import structures.KVP;

import java.util.Random;

public class Round {
    private String Category;
    private String Puzzle;
    private String WordToSolve;
    private GuessedLetters GuessedLetters;
    private CustomDictionary Dictionary;
    private CustomDictionary TrackedLetters;
    private double[] PlayerTotals;
    private int RoundAttempt;

    public Round(Categories category, String puzzle, int numOfPlayers) throws InvalidArgumentException{
        if(!IsValidWord(puzzle))
            throw new InvalidArgumentException(new String[] {"puzzle cannot have more space than letters"});
        this.Category = category.name();
        this.Puzzle = puzzle;
        this.WordToSolve = CreatePuzzle(puzzle);
        GuessedLetters = new GuessedLetters();
        this.PlayerTotals = new double[numOfPlayers];
        this.TrackedLetters= new CustomDictionary(1);
        RoundAttempt = numOfPlayers;
    }

    public boolean IsGuessable(char letter)
    {
        if(TrackedLetters.GetKVP(letter) != null) return false;
        return true;
    }

    public KVP IsValidLetter(char letter)
    {
        TrackedLetters.Insert(letter,letter);
        try{
            GuessedLetters.Enqueue(letter);
        }catch (Exception e){}
        return Dictionary.GetKVP(letter);
    }

    public void UpdateWord(KVP kvp)
    {
        StringBuilder builder = new StringBuilder(this.WordToSolve);
        int[] values = kvp.GetValues();
        for(int x = 0; x < values.length; x++)
        {
            builder.setCharAt(values[x],kvp.GetKey());
        }
        this.WordToSolve = builder.toString();
        System.out.println("Updated word: "+this.WordToSolve);
    }

    public boolean SolvedWord()
    {
        return Puzzle == WordToSolve;
    }

    public void UpdatePlayerTotal(int index, double amount)
    {
        PlayerTotals[index] += amount;
    }

    public double GetPlayerTotal(int index)
    {
        return PlayerTotals[index];
    }

    public void UpdateRoundAttempt()
    {
        RoundAttempt--;
    }

    public String GetWordToSolve()
    {
        return this.WordToSolve;
    }

    public String GetPuzzle()
    {
        return this.Puzzle;
    }

    public int GetRoundAttempt()
    {
        return this.RoundAttempt;
    }

    public void DisplayGuessedLetters(){
        GuessedLetters.Display();
    }

    private String CreatePuzzle(String word){
        int wordLength = word.length();
        this.Dictionary = new CustomDictionary(wordLength);
        StringBuilder newWord = new StringBuilder(word);
        Random rand = new Random();
        double percentage = 0.70;
        int maxLetters = (int)(wordLength * percentage);
        String[] positionUsed = new String[wordLength];
        while (wordLength > 0){
            if(maxLetters > 0)
            {
                int index = rand.nextInt(wordLength);
                char letter = word.charAt(index);
                if(letter != ' ' && positionUsed[index] == null){
                    newWord.setCharAt(index,'_');
                    positionUsed[index] = new String(new char[] {letter});
                    maxLetters--;
                }
            }else{
                Dictionary.Insert(this.Puzzle.charAt(wordLength-1),wordLength-1);
                wordLength--;
            }
        }
        return newWord.toString();
    }

    private boolean IsValidWord(String word){
        if(word == null || word.isEmpty()) return false;
        double wordPercentage = 0.70;
        double spacePercentage = word.split(" ").length / 100;
        if(spacePercentage >= wordPercentage) return false;
        return true;
    }

    public void ToString(){
        System.out.println("[Category: "+this.Category+", Puzzle: "+this.Puzzle+", WordToSolve: "+WordToSolve+", PlayerTotals: "+this.PlayerTotals+"]");
    }
}
