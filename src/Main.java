import models.Player;
import structures.Players;
import structures.SinglyGenericNode;
import structures.Wheel;

public class Main {

    public static void main(String[] args) throws Exception{
        Players players = new Players();
        players.Append(new Player("Jordaine",0,0));
        players.Append(new Player("Paula",0,0));
        players.Append(new Player("Jerry",0,0));
        players.Append(new Player("Peter",0,0));
//        Player player = players.GetPlayerByNumber(1);
//        if(player != null){
//            System.out.println(player.ToString());
//        }else{
//            System.out.println("unable to locate player");
//        }

        players.Display();
        System.out.println("\n\n");
//        players.DeleteAt(3);
//        SinglyGenericNode<Player> player = players.GetPlayerByNumber(1);
//        if(player != null){
//            System.out.println(player.GetNextNode().GetData().ToString());
//        }else{
//            System.out.println("unable to locate player");
//        }
        players.ClearList();
//        Wheel wheel = new Wheel();
//        wheel.GenerateCards();
//        System.out.println("Spinned card");
//        wheel.SpinWheel().GetData().ToString();
//        while(true){
//            wheel.SpinWheel().GetData().ToString();
//            Thread.sleep(5000);
//        }

        System.out.println("\n\n");
        System.out.println("\n\n");
        players.Append(new Player("Paula",0,0));
        players.Append(new Player("Jerry",0,0));
        players.Display();
        players.DisplayLength();
    }
}
