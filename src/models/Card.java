package models;
import models.enums.CardTypes;
/**
 * Card Class
 * {
 *     Author: X
 *     StudentId: X
 *     Desc: Card class that defines the card structrue
 * }
 * */
public class Card
{

    //card type
    private CardTypes Type;

    //card value
    private double Value;

    //default constructor
    public Card()
    {
        //setting the default card to a money card
        Type = CardTypes.MONEY;
        //setting default card to a free play
        Value = 850.00;
    }

    //primary constructor
    public Card(CardTypes type, double value) throws Exception
    {
        //validates the card type and value
        ValidateCard(type,value);
        //sets the card type
        this.Type = type;
        //sets the card value
        this.Value = value;
    }

    //copy constructor
    public Card(Card card) throws Exception
    {
        //ensuring that the card inputted isn't null
        if(card == null)
        {
            //throw error is a null card is entered
            throw new NullPointerException("ERROR: Card cannot be null or empty.");
        }
        //validates teh card type and value
        ValidateCard(card.Type,card.Value);
        //sets teh card type
        this.Type = card.Type;
        //sets the card value
        this.Value = card.Value;
    }

    //accessors
    public CardTypes GetType()
    {
        return this.Type;
    }

    public double GetValue()
    {
        return this.Value;
    }

    //mutators
    public void SetType(CardTypes type) {
        Type = type;
    }

    public void SetValue(double value) {
        Value = value;
    }

    //validates the card
    private void ValidateCard(CardTypes type, double value) throws Exception
    {
        //ensuring that the card is within the correct value based on the type
        if(type == CardTypes.MONEY && !(value  >= 500 && value <= 2500))
        {
            //throws an error if the condition is true
            throw new Exception("ERROR: When creating a money card the accepted value must be between $500.00 and $2500.00");
        }
        else if(type != CardTypes.MONEY && value > 0)
        {
            //throws an error if the condition is true
            throw new Exception("ERROR: When creating a "+type.name()+" the value must be $0.00.");
        }
    }

    //display method of what's inside the card class or what's defined
    public void ToString()
    {
        System.out.println("[CardType: "+Type.name()+", Value: $"+Value+"]");
    }
}
