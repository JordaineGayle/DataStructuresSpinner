package structures;

import models.Round;

public class Game {
    private Round[] Rounds;
    private Players Players;
    private int CurrentRound;
    private int NumOfPlayers;

    public Game()
    {
        this.Rounds = new Round[3];
    }
}
