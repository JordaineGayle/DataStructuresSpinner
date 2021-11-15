package models;

public class Player
{
    //attributes
    private String Name;
    private int Number;
    private double GrandTotal;

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
    public String GetName() {
        return Name;
    }

    public void SetName(String name) {
        Name = name;
    }

    public int GetNumber() {
        return Number;
    }

    public void SetNumber(int number) {
        Number = number;
    }

    public double GetGrandTotal() {
        return GrandTotal;
    }

    public void SetGrandTotal(double grandTotal) {
        GrandTotal+=grandTotal;
    }

    public void ResetGrandTotal(double grandTotal) {
        GrandTotal=grandTotal;
    }

    public String ToString() {
        return "[Name: "+Name+", Number: "+Number+", GrandTotal: "+GrandTotal+"]";
    }
}

