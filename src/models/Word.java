package models;

import models.enums.Categories;

public class Word {
    private String Category;
    private String Word;

    public Word()
    {
        this.Category = "";
        this.Word = "";
    }

    public Word (Categories category, String word) throws Exception{
        if(word.isEmpty()){
            throw new Exception("word cannot be null or empty.");
        }
        word = word.toUpperCase();
        this.Category = category.name();
        this.Word = word;
    }

    public String GetCategory() {
        return Category;
    }

    public void SetCategory(String category) {
        Category = category;
    }

    public String GetWord() {
        return Word;
    }

    public void SetWord(String word) {
        Word = word;
    }

    public String ToString(){
        return "[Category: "+Category+", Word: "+Word+"]";
    }
}
