package database;
import models.Word;
import models.enums.Categories;
import java.io.File;
import java.util.Random;
import java.util.Scanner;
/**
 * WordsDB Class
 * {
 *     Author: Jordaine Gayle
 *     StudentId: 1800708
 *     Desc: Reads in the word list from a csv file
 * }
 * */
public class WordsDB {

    //words array
    Word[] Words;

    //word db constructor
    public WordsDB() throws Exception{
        int count = 0;
        //defining the size of the array
        this.Words = new Word[count];

        //read in the file from the physical location from storage
        File file = new File("./src/database/words_db.csv");
        Scanner reader = new Scanner(file);

        //reads each line of the file while there is a line to read
        while (reader.hasNextLine()) {

            //stores the line of the file in a String variable
            String line = reader.nextLine();

            //break the string at the 'comma' to get the two sections of the string
            String[] sections = line.split(",");

            //handle errors while reading the lines of a file
            try{

                //reading the section of the sections in the word structure, which consist of the category and the puzzle or word itself
                Word word = new Word(Categories.valueOf(sections[0].toUpperCase()), sections[1]);

                //create a new array for word and increases it length by 1
                Word[] newWords = new Word[count+1];

                //sets the new word to the end of the list
                newWords[count] = word;

                //looping through the old list of words and pushing them to the new list
                for (int x = 0; x < Words.length; x++)
                {
                    newWords[x] = Words[x];
                }

                //setting the old words array to the new words array
                Words = newWords;
                //incrementing the count by 1;
                count++;
            }
            catch (IllegalArgumentException e)
            {
                //throw new Exception("incorrect category name: "+sections[0].toUpperCase());
            }
            catch (Exception ex){
                //rethrow all exception we cant code for
                throw ex;
            }

        }

        //disposes of the read class
        reader.close();
    }

    //gets the list of words we loaded into memory
    public Word[] GetWords(){
        return Words;
    }

    //class to show all the words stored in memory
    public void Display(){
        Word[] words = GetWords();
        for(int x = 0; x < words.length; x++){
            System.out.println(words[x].ToString());
        }
    }

    //gets a specific word from a section in memory if can't locate returns null
    public Word GetWordAt(int index){
        if(Words.length > index){
            return Words[index];
        }
        return null;
    }

    //gets a random word from memory
    public Word GetRandomWord(){
        Random rand = new Random();
        int index = rand.nextInt(Words.length);
        return GetWordAt(index);
    }
}
