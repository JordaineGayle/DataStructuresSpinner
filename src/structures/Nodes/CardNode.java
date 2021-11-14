package structures.Nodes;

import models.Card;

public class CardNode {
    private Card Data;
    private CardNode NextNode;

    public CardNode(Card data, CardNode nextNode) throws Exception{
        ValidateData(data);
        this.Data = data;
        this.NextNode = nextNode;
    }

    public CardNode(CardNode node) throws Exception{
        if(node == null) throw new NullPointerException("The nodes cannot be null or empty.");
        ValidateData(node.Data);
        this.Data = node.Data;
        this.NextNode = node.NextNode;
    }

    public void SetData(Card data) throws Exception{
        ValidateData(data);
        this.Data = data;
    }

    public Card GetData(){
        return this.Data;
    }

    public void SetNextNode(CardNode node){
        this.NextNode = node;
    }

    public CardNode GetNextNode(){
        return this.NextNode;
    }

    private void ValidateData(Card data) throws Exception{
        if(data == null) throw new NullPointerException("The nodes data property cannot be null or empty.");
    }
}
