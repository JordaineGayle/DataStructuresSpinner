import models.GuessedLetter;
import models.Player;
import structures.GuessedLetters;
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
        System.out.println("\n");
        System.out.println("\n\nGuessed Letters");

        GuessedLetters gs = new GuessedLetters();
        gs.Enqueue(new GuessedLetter(0,'A'));
        gs.Enqueue(new GuessedLetter(1,'b'));
        gs.Enqueue(new GuessedLetter(2,'c'));
        gs.Enqueue(new GuessedLetter(3,'T'));
        gs.Display();

        System.out.println("\n");
        System.out.println("\n\nTesting References");
        System.out.println("head: "+gs.GetHead().GetData().ToString());
        System.out.println("tail: "+gs.GetTail().GetData().ToString());

        System.out.println("\n");
        System.out.println("\n\nDequeuing");
        gs.Dequeue();
        gs.Dequeue();
        gs.Dequeue();
        gs.Dequeue();
        gs.Display();

        System.out.println("\n\nEnqueuing");
        gs.Enqueue(new GuessedLetter(0,'A'));
        gs.Enqueue(new GuessedLetter(1,'b'));
        gs.Display();

        System.out.println("\n");
        System.out.println("\n\nQuelength: "+gs.GetLength());

        System.out.println("\n");
        System.out.println("\n\nDequeuing");
        gs.Dequeue();
        gs.Dequeue();
        gs.Dequeue();
        gs.Dequeue();
        gs.Display();

        System.out.println("\n");
        System.out.println("\n\nQuelength: "+gs.GetLength());
    }
}
