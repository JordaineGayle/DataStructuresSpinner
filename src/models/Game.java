package models;

import com.sun.javaws.exceptions.InvalidArgumentException;
import database.WordsDB;
import models.Round;
import models.enums.Categories;
import structures.Players;

import java.util.Scanner;

public class Game {
    private WordsDB Database;
    private Round[] Rounds;
    private structures.Players Players;
    private int CurrentRound;
    private int NumOfPlayers;

    public Game() throws Exception
    {
        this.Database = new WordsDB();
        this.Players = new Players();
        NumOfPlayers = 3;
        CreatePlayers();
        this.Rounds = new Round[3];
        CreateRounds();
        this.CurrentRound = 0;
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
    }

    private void CreateRounds(){
        for (int x = 0; x < 3; x++){
            Word word = Database.GetRandomWord();
            Round round = null;
            while (round == null){
                try{
                    round = new Round(Categories.valueOf(word.Category),word.Word,this.NumOfPlayers);
                }catch (InvalidArgumentException e){
                    round = null;
                }
            }
            this.Rounds[x] = round;
            this.Rounds[x].ToString();
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

        this.Players.Display();
    }
}
