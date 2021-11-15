import database.UserManual;
import models.Game;
import java.util.Scanner;
/**
 * Driver Class
 * {
 *     Author: X
 *     StudentId: X
 *     Desc: Holds the methods handle the menu flow
 * }
 * */
public class Driver {

    //main menu
    public static void Menu() throws Exception{
        System.out.print("\nPress 'a' to  view user manual or 'b' to begin the game: ");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        input = input.toLowerCase();
        char option = input.charAt(0);

        //validation check to ensure that the option entered must be 'a' or 'b'
        if(option != 'a' && option != 'b')
        {
            System.out.println("Invalid input, please enter 'a' or 'b'");

            //recursively call the menu until a valid input is entered
            Menu();
        }

        //displays the user manual and recursively call the menu in order for the user to get the option to start the game
        if(option == 'a')
        {
            //loads in the user manual
            new UserManual();

            //recursive menu call
            Menu();
        }
        else
        {
            //initiates the game process
            ProcessGameInput();
        }
    }

    //game processing method
    public static void ProcessGameInput()
    {
        System.out.print("Enter the amount of players. Range [2,n]: ");
        Scanner sc = new Scanner(System.in);
        int numPlayers = 0;

        //catch errors related to input validation
        try{

            //reading the user input
            numPlayers = sc.nextInt();

            //validating that the user input is within the accepted range of 2 - n, if not it recursively call the ProcessGameInput
            if(numPlayers < 2)
            {
                //error message that reminds the user of the valid player range
                System.out.println("Please enter a minimum number of 2 players.");

                //recursively call the method on failure
                ProcessGameInput();
            }
        }
        catch (Exception e)
        {
            //displays the error message to the user
            System.out.println("invalid imput please enter a valid number. Range [0,n]");


            //recursively call the method on failure
            ProcessGameInput();
        }

        //handle any error that might occur when creating a game and display to the user
        try
        {
            //initiates a new game
            Game game = new Game(numPlayers);
        }
        catch (Exception ex)
        {
            //displays the error message
            System.out.println("ERROR: "+ex.getMessage().toUpperCase());
        }
    }
}
