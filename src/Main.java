/**
 *  Main class
 *  {
 *      Author: Jordaine Gayle
 *      StudentId: 1800708
 *      Desc: Initiate the game play or displays the user manual
 *  }
 *
 */

public class Main {

    //main method
    public static void main(String[] args) {

        //welcome message
        System.out.println("\t\t\tWelcome to GMS Word/Puzzle Game");

        try
        {
            //display main menu
            Driver.Menu();
        }
        catch (Exception e)
        {
            //handle internal system errors and display the error message to user and end program
            System.out.println("ERROR: "+e.getMessage().toUpperCase());
        }
    }
}
