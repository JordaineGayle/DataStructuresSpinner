package models;

public class Player
{
    //attributes
    String Name;
    int Number;
    double GrandTotal;

    //default constructor
    public Player()
    {
        Name = "";
        Number = 1;
        GrandTotal = 0;
    }

    //primary constructor
    public Player(String name, int number, double grandTotal)
    {
        Name = name;
        Number = number;
        GrandTotal = grandTotal;
    }
    //copy constructors
    public Player(Player p) {
        Name = p.Name;
        Number = p.Number;
        GrandTotal = p.GrandTotal;
    }

    //accessors and mutators
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public double getGrandTotal() {
        return GrandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        GrandTotal = grandTotal;
    }

    @Override
    public String toString() {
        return "";
    }
}

