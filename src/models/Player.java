package models;
/**
 * Player Class
 * {
 *     Author: X
 *     StudentId: X
 *     Desc: defines the  structure
 * }
 * */
public class Player
{
    //attributes
    private String Name;
    private int Number;
    private double GrandTotal;

    //default constructor
    public Player()
    {
        SetName("");
        SetNumber(1);
        SetGrandTotal(0);
    }
    //primary constructor
    public Player(String name, int number, double grandTotal)
    {
        SetName(name);
        SetNumber(number);
        SetGrandTotal(grandTotal);
    }

    //copy constructors
    public Player(Player p)
    {
        SetName(p.Name);
        SetNumber(p.Number);
        SetGrandTotal(p.GrandTotal);
    }

    //accessors

    public String GetName()
    {
        return Name;
    }

    public int GetNumber()
    {
        return Number;
    }

    public double GetGrandTotal()
    {
        return GrandTotal;
    }

    //mutators

    public void SetName(String name)
    {
        Name = name;
    }

    public void SetNumber(int number)
    {
        Number = number;
    }

    public void SetGrandTotal(double grandTotal)
    {
        GrandTotal+=grandTotal;
    }

    public void ResetGrandTotal(double grandTotal)
    {
        GrandTotal=grandTotal;
    }

    //displays what's inside the player class
    public String ToString()
    {
        return "[Name: "+Name+", Number: "+Number+", GrandTotal: "+GrandTotal+"]";
    }
}

