import database.UserManual;
import models.Game;

import java.util.Scanner;

public class Driver {

    public static void Menu() throws Exception{
        System.out.print("\nPress 'a' to  view user manual or 'b' to begin the game: ");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        input = input.toLowerCase();
        char option = input.charAt(0);
        if(option != 'a' && option != 'b')
        {
            System.out.println("Invalid input, please enter 'a' or 'b'");
            Menu();
        }

        if(option == 'a')
        {
            new UserManual();
            Menu();
        }
        else
        {
            ProcessGameInput();
        }
    }

    public static void ProcessGameInput(){
        System.out.print("Enter the amount of players. Range [2,n]: ");
        Scanner sc = new Scanner(System.in);
        int numPlayers = 0;
        try{
            numPlayers = sc.nextInt();
            if(numPlayers < 2)
            {
                System.out.println("Please enter a minimum number of 2 players.");
                ProcessGameInput();
            }
        }
        catch (Exception e)
        {
            System.out.println("invalid imput please enter a valid number. Range [0,n]");
            ProcessGameInput();
        }

        try{
            Game game = new Game(numPlayers);
        }catch (Exception ex){
            System.out.println("ERROR: "+ex.getMessage().toUpperCase());
        }
    }
}
