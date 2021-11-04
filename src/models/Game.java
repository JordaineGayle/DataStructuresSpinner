package models;

import models.Round;
import models.enums.Categories;
import structures.Players;

public class Game {
    private Round[] Rounds;
    private structures.Players Players;
    private int CurrentRound;
    private int NumOfPlayers;

    public Game()
    {
        this.Rounds = new Round[3];
        this.Players = new Players();

    }

    private void CreateRounds(){
        for (int x = 0; x < 3; x++){
            this.Rounds[x] = new Round(Categories.PLACE,"Hello world",new double[]{});
        }
    }
}
