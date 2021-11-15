import models.Game;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception{
//        Scanner sc = new Scanner(System.in);
//
//        while (true)
//        {
//            System.out.print("Type a char: ");
//            String s = sc.nextLine();
//            char c = s.charAt(0);
//            System.out.print("Num: "+(int)c);
//            System.out.print(" => ");
//            System.out.print("Val: "+c);
//            System.out.println("\n");
//        }


        try{
            Game game = new Game(2);
        }catch (Exception ex){
            System.out.println("ERROR: "+ex.getMessage().toUpperCase());
        }
    }
}
