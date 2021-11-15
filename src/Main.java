public class Main {

    public static void main(String[] args) {

        System.out.println("\t\t\tWelcome to GMS Word/Puzzle Game");

        try
        {
            Driver.Menu();
        }
        catch (Exception e)
        {
            System.out.println("ERROR: "+e.getMessage().toUpperCase());
        }
    }
}
