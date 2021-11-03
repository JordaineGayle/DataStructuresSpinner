package models;

import models.enums.Categories;

public class Round {
    String Category;
    String Puzzle;
    double[] PlayerTotals;

    public Round(){
        Category = Categories.PERSON.name();
        Puzzle = "";
        PlayerTotals = new double[]{0.00,0.00,0.00};
    }

    public Round(Categories category, String puzzle, double[] playerTotals){
        this.Category = category.name();
        this.Puzzle = puzzle;
        this.PlayerTotals = playerTotals;
    }

    public Round(Round round){
        this.Category = round.Category;
        this.Puzzle = round.Puzzle;
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
}
