package database;
import models.Word;
import models.enums.Categories;
import java.io.File;
import java.util.Random;
import java.util.Scanner;

public class WordsDB {

    Word[] Words;

    public WordsDB() throws Exception{
        int count = 0;
        this.Words = new Word[count];
        File file = new File("./src/database/words_db.csv");
        Scanner reader = new Scanner(file);

        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String[] sections = line.split(",");
            try{
                Word word = new Word(Categories.valueOf(sections[0].toUpperCase()), sections[1]);
                Word[] newWords = new Word[count+1];
                newWords[count] = word;
                for (int x = 0; x < Words.length; x++){
                    newWords[x] = Words[x];
                }
                Words = newWords;
            }catch (IllegalArgumentException e){
                throw new Exception("incorrect category name: "+sections[0].toUpperCase());
            }catch (Exception ex){
                throw ex;
            }
            count++;
        }
        reader.close();
    }

    public Word[] GetWords(){
        return Words;
    }

    public void Display(){
        Word[] words = GetWords();
        for(int x = 0; x < words.length; x++){
            System.out.println(words[x].ToString());
        }
    }

    public Word GetWordAt(int index){
        if(Words.length > index){
            return Words[index];
        }
        return null;
    }

    public Word GetRandomWord(){
        Random rand = new Random();
        int index = rand.nextInt(Words.length);
        return GetWordAt(index);
    }
}
