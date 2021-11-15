package structures.Nodes;
import models.Card;
/**
 * CardNode Class
 * {
 *     Author: X
 *     StudentId: X
 *     Desc: defines the  structure
 * }
 * */
public class CardNode {
    //card data
    private Card Data;
    //next card node
    private CardNode NextNode;

    //primary constructor for card node
    public CardNode(Card data, CardNode nextNode) throws Exception
    {
        //validates the card data
        ValidateData(data);
        SetData(data);
        SetNextNode(nextNode);
    }

    //copy constructor for card node
    public CardNode(CardNode node) throws Exception
    {
        if(node == null) throw new NullPointerException("The nodes cannot be null or empty.");
        ValidateData(node.GetData());
        SetData(node.Data);
        SetNextNode(node.NextNode);
    }

    public void SetData(Card data) throws Exception
    {
        ValidateData(data);
        this.Data = data;
    }

    //accessors
    public Card GetData()
    {
        return this.Data;
    }

    public CardNode GetNextNode()
    {
        return this.NextNode;
    }

    //mutators
    public void SetNextNode(CardNode node)
    {
        this.NextNode = node;
    }

    //validates that the data inputted is not null
    private void ValidateData(Card data) throws Exception
    {
        if(data == null) throw new NullPointerException("The nodes data property cannot be null or empty.");
    }
}
