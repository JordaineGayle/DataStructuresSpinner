package structures;

import models.Card;
import models.enums.CardTypes;
import models.enums.Categories;

import java.util.Random;

public class Wheel {
    private int Length = 25;
    private SinglyGenericNode<Card> Head;
    private  SinglyGenericNode<Card> CurrentCard;

    public Wheel()
    {
        Length = 25;
        Head = null;
    }

    public Wheel(SinglyGenericNode<Card> head){
        this.Head = head;
    }

    public void Append(Card card) throws Exception
    {
        SinglyGenericNode<Card> newNode = new SinglyGenericNode<>(card,null);

        if(this.Head == null)
        {
            this.Head = new SinglyGenericNode(newNode);
            this.Head.SetNextNode(this.Head);
        }
        else
        {
            SinglyGenericNode<Card> nextNode = this.Head.GetNextNode();
            while (nextNode.GetNextNode() != this.Head){
                nextNode = nextNode.GetNextNode();
            }
            nextNode.SetNextNode(newNode);
            newNode.SetNextNode(this.Head);
        }
    }

    public SinglyGenericNode<Card> GetCurrentCard(){
        return this.CurrentCard;
    }

    public SinglyGenericNode<Card> SpinWheel(){
        SinglyGenericNode<Card> nextNode = Head;
        if(CurrentCard != null){
            nextNode = CurrentCard;
        }
        int totalSpins = GenerateRandomNumber(50,100);
        while(totalSpins > 0){
            nextNode = nextNode.GetNextNode();
            totalSpins--;
        }
        CurrentCard = nextNode;
        return CurrentCard;
    }

    private int GenerateRandomNumber(int min, int max){
        return (int)Math.floor(Math.random()*(max-min+1)+min);
    }

    public void GenerateCards() throws Exception{
        Random rand = new Random();
        CardTypes[] cardTypes = CardTypes.values();
        int cardTypesLength = cardTypes.length -1;
        for (int i = 0; i < Length; i++){
            CardTypes cardType = cardTypes[GenerateRandomNumber(0,cardTypesLength)];
            if(cardType == CardTypes.MONEY){
                double moneyVal = GenerateRandomNumber(500,2500);
                Append(new Card(cardType,moneyVal));
            }else{
                Append(new Card(cardType,0));
            }
        }
    }

    public void Display(){
        SinglyGenericNode<Card> temp = Head;
        while (temp.GetNextNode() != Head){
            temp.GetData().ToString();
            temp = temp.GetNextNode();
        }
    }

}
