package models;

public class GuessedLetter {
    int Index;
    char Letter;

    public GuessedLetter(){};

    public GuessedLetter(int index, char letter){
        this.Index = index;
        this.Letter = letter;
    }

    public GuessedLetter(GuessedLetter guess){
        this.Index = guess.Index;
        this.Letter = guess.Letter;
    }

    public String ToString(){
        return "[Index: "+Index+", Letter: "+Letter+"]";
    }
}
