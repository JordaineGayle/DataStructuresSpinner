import models.Player;
import structures.Players;
import structures.Wheel;

public class Main {

    public static void main(String[] args) throws Exception{
//        Players players = new Players();
//        players.Append(new Player("Jordaine",0,0));
//        players.Append(new Player("Paula",0,0));
//        players.Append(new Player("Jerry",0,0));
//        players.Append(new Player("Peter",0,0));
        Wheel wheel = new Wheel();
        wheel.GenerateCards();
        System.out.println("Spinned card");
        wheel.SpinWheel().GetData().ToString();
    }
}
