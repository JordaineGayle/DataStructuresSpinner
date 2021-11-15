package models;
import models.enums.Categories;
/**
 * Word Class
 * {
 *     Author: X
 *     StudentId: X
 *     Desc: defines the  structure
 * }
 * */
public class Word
{
    //word category
    private String Category;
    //actual word
    private String Word;

    //default constructor
    public Word()
    {
        this.Category = "";
        this.Word = "";
    }

    //primary constructor
    public Word (Categories category, String word) throws Exception
    {
        //test if the word is empty or null
        if(word.isEmpty())
        {
            //throws an error is the word is null
            throw new Exception("word cannot be null or empty.");
        }

        //set the word to upper case
        word = word.toUpperCase();

        //sets the category
        this.Category = category.name();

        //sets the word
        this.Word = word;
    }

    //accessors
    public String GetCategory()
    {
        return Category;
    }

    public String GetWord()
    {
        return Word;
    }

    //mutators
    public void SetWord(String word)
    {
        Word = word;
    }

    public void SetCategory(String category)
    {
        Category = category;
    }

    //displays whats inside the word class
    public String ToString()
    {
        return "[Category: "+Category+", Word: "+Word+"]";
    }
}
