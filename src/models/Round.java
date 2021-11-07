package models;

import com.sun.javaws.exceptions.InvalidArgumentException;
import models.enums.Categories;
import structures.CustomDictionary;
import structures.GuessedLetters;
import structures.KVP;

import java.util.Random;

public class Round {
    String Category;
    String Puzzle;
    String WordToSolve;
    GuessedLetters GuessedLetters;
    CustomDictionary Dictionary;
    double[] PlayerTotals;

    public Round(Categories category, String puzzle, int numOfPlayers) throws InvalidArgumentException{
        if(!IsValidWord(puzzle))
            throw new InvalidArgumentException(new String[] {"puzzle cannot have more space than letters"});
        this.Category = category.name();
        this.Puzzle = puzzle;
        this.WordToSolve = CreatePuzzle(puzzle);
        GuessedLetters = new GuessedLetters();
        this.PlayerTotals = new double[numOfPlayers];
    }

    private String CreatePuzzle(String word){
        String nonSpaceWord = word.replaceAll(" ","");
        int nonSpacedWordCount = nonSpaceWord.length();
        this.Dictionary = new CustomDictionary(nonSpacedWordCount);
        StringBuilder newWord = new StringBuilder(word);
        Random rand = new Random();
        int wordLength = word.length();
        double percentage = 0.70;
        int maxLetters = (int)(wordLength * percentage);
        String[] positionUsed = new String[wordLength];
        while (nonSpacedWordCount > 0){
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
                Dictionary.Insert(nonSpaceWord.charAt(nonSpacedWordCount-1),nonSpacedWordCount-1);
                nonSpacedWordCount--;
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
