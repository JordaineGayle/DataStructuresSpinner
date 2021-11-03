import models.Player;
import structures.Players;

public class Main {

    public static void main(String[] args) throws Exception{
        Players players = new Players();
        players.Append(new Player("Jordaine",0,0));
        players.Append(new Player("Paula",0,0));
        players.Append(new Player("Jerry",0,0));
        players.Append(new Player("Peter",0,0));
//        players.DisplayLength();
//        players.Display();
    }
}
