package models;

import models.enums.CardTypes;

public class Card {

    private CardTypes Type;
    private double Value;

    //default constructor
    public Card(){
        Type = CardTypes.MONEY;
        Value = 850.00;
    }

    //primary constructor
    public Card(CardTypes type, double value) throws Exception{
        ValidateCard(type,value);
        this.Type = type;
        this.Value = value;
    }

    //copy constructor
    public Card(Card card) throws Exception{
        if(card == null){
            throw new NullPointerException("ERROR: Card cannot be null or empty.");
        }
        ValidateCard(card.Type,card.Value);
        this.Type = card.Type;
        this.Value = card.Value;
    }

    //accessors
    public CardTypes GetType(){
        return this.Type;
    }

    public double GetValue(){
        return this.Value;
    }

    private void ValidateCard(CardTypes type, double value) throws Exception{
        if(type == CardTypes.MONEY && !(value  >= 500 && value <= 2500)){
            throw new Exception("ERROR: When creating a money card the accepted value must be between $500.00 and $2500.00");
        }
        else if(value > 0){
            throw new Exception("ERROR: When creating a "+type.name()+" the value must be $0.00.");
        }
    }
}
