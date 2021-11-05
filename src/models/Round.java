package models;

import models.enums.Categories;
import structures.GuessedLetters;

public class Round {
    String Category;
    String Puzzle;
    String WordToSolve;
    GuessedLetters GuessedLetters;
    double[] PlayerTotals;

    public Round(){
        Category = Categories.PERSON.name();
        Puzzle = "";
        WordToSolve = "";
        GuessedLetters = new GuessedLetters();
        PlayerTotals = new double[]{};
    }

    public Round(Categories category, String puzzle, int numOfPlayers){
        this.Category = category.name();
        this.Puzzle = puzzle;
        GuessedLetters = new GuessedLetters();
        WordToSolve = "";
        this.PlayerTotals = new double[numOfPlayers];
    }

    public Round(Round round){
        this.Category = round.Category;
        this.Puzzle = round.Puzzle;
        GuessedLetters = round.GuessedLetters;
        this.PlayerTotals = round.PlayerTotals;
    }

    public String GetCategory() {
        return Category;
    }

    public void SetCategory(String category) {
        Category = category;
    }

    public String GetPuzzle() {
        return Puzzle;
    }

    public void SetPuzzle(String puzzle) {
        Puzzle = puzzle;
    }

    public double[] GetPlayerTotals() {
        return PlayerTotals;
    }

    public void SetPlayerTotals(double[] playerTotals) {
        PlayerTotals = playerTotals;
    }

    public void SetPlayerTotal(int index, double value){
        this.PlayerTotals[index] = value;
    }

    public double GetPlayerTotal(int index){
        return this.PlayerTotals[index];
    }

    public String CreatePuzzle(){
        double percentage = 0.70;
        return "";
    }

    public void ToString(){
        System.out.println("[Category: "+this.Category+", Puzzle: "+this.Puzzle+", PlayerTotals: "+this.PlayerTotals+"]");
    }
}
