package database;
import java.io.File;
import java.util.Scanner;

/**
 * UserManual Class
 * {
 *     Author: X
 *     StudentId: 1800708
 *     Desc: Reads in the user manual from a file
 * }
 * */
public class UserManual {

    public UserManual() throws Exception
    {
        try
        {
            int count = 0;

            //reads file from the location in storage
            File file = new File("./src/database/user_manual.txt");
            System.out.println("\n");
            Scanner reader = new Scanner(file);

            //read all lines of the file
            while (reader.hasNextLine())
            {
                //display each line of the file in the console
                System.out.println(reader.nextLine());
            }

            //disposes of the reader when we are finish reading the file
            reader.close();
        }
        catch (Exception ex)
        {
            //throws new exception if we encountered and error reading the file
            throw new Exception("Unable to display the contents of the user manual");
        }
    }
}
