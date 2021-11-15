package structures;

import models.Card;
import models.enums.CardTypes;
import structures.Nodes.CardNode;

public class Wheel {
    private int Length;
    private CardNode Head;
    private  CardNode CurrentCard;

    public Wheel() throws Exception
    {
        Length = 25;
        Head = null;
        CurrentCard = null;
        GenerateCards();
    }

    public Wheel(Wheel wheel) throws Exception
    {
        Length = wheel.Length;
        Head = wheel.Head;
        CurrentCard = wheel.CurrentCard;
        GenerateCards();
    }

    public Wheel(CardNode head) throws Exception{
        Length = 25;
        this.Head = head;
        this.CurrentCard = this.Head;
        GenerateCards();
    }

    public void Append(Card card) throws Exception
    {
        CardNode newNode = new CardNode(card,null);

        if(this.Head == null)
        {
            this.Head = new CardNode(newNode);
            this.Head.SetNextNode(this.Head);
        }
        else
        {
            CardNode nextNode = this.Head.GetNextNode();
            while (nextNode.GetNextNode() != this.Head){
                nextNode = nextNode.GetNextNode();
            }
            nextNode.SetNextNode(newNode);
            newNode.SetNextNode(this.Head);
        }
    }

    public Card GetCurrentCard(){
        return this.CurrentCard.GetData();
    }

    public Card SpinWheel(){
        CardNode nextNode = Head;
        if(CurrentCard != null){
            nextNode = CurrentCard;
        }
        int totalSpins = GenerateRandomNumber(50,100);
        while(totalSpins > 0){
            nextNode = nextNode.GetNextNode();
            totalSpins--;
        }
        CurrentCard = nextNode;
        return CurrentCard.GetData();
    }

    private int GenerateRandomNumber(int min, int max){
        return (int)Math.floor(Math.random()*(max-min+1)+min);
    }

    public void GenerateCards() throws Exception{
        CardTypes[] cardTypes = CardTypes.values();
        int cardTypesLength = cardTypes.length -1;
        int bankruptCards = 3;
        int lostTurnCards = 2;
        for (int i = 0; i < Length; i++){
            CardTypes cardType = cardTypes[GenerateRandomNumber(0,cardTypesLength)];
            if(cardType == CardTypes.BANKRUPTCY && bankruptCards <= 0)
            {
                cardType = CardTypes.MONEY;
            }
            if(cardType == CardTypes.LOSE_TURN && lostTurnCards <= 0)
            {
                cardType = CardTypes.MONEY;
            }

            if(cardType == CardTypes.MONEY)
            {
                double moneyVal = GenerateRandomNumber(500,2500);
                Append(new Card(cardType,moneyVal));
            }
            else
            {
                if(cardType == CardTypes.BANKRUPTCY)
                {
                    bankruptCards--;
                }
                else
                {
                    lostTurnCards--;
                }
                Append(new Card(cardType,0));
            }
        }
    }

    public void Display(){
        CardNode temp = Head;
        while (temp.GetNextNode() != Head){
            temp.GetData().ToString();
            temp = temp.GetNextNode();
        }
    }

}
