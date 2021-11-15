package database;
import java.io.File;
import java.util.Scanner;

public class UserManual {

    public UserManual() throws Exception {
        try{
            int count = 0;
            File file = new File("./src/database/user_manual.txt");
            System.out.println("\n");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                System.out.println(reader.nextLine());
            }
            reader.close();
        }catch (Exception ex)
        {
            throw new Exception("Unable to display the contents of the user manual");
        }
    }
}
