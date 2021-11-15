package structures.Nodes;
/**
 * LetterNode Class
 * {
 *     Author: X
 *     StudentId: X
 *     Desc: defines the  structure
 * }
 * */
public class LetterNode
{
    //data property
    private Character Data;
    //next letter node
    private LetterNode NextNode;
    //previous letter node
    private LetterNode PrevNode;

    //default constructor
    public LetterNode(){};

    //primary constructor
    public LetterNode(Character data, LetterNode nextNode, LetterNode prevNode) throws Exception
    {
        SetData(data);
        SetNextNode(nextNode);
        SetPrevNode(prevNode);
    }

    //copy constructor
    public LetterNode(LetterNode node) throws Exception
    {
        SetData(node.GetData());
        SetNextNode(node.GetNextNode());
        SetPrevNode(node.GetPrevNode());
    }

    //accessors
    public Character GetData()
    {
        return this.Data;
    }

    public LetterNode GetNextNode()
    {
        return this.NextNode;
    }

    public LetterNode GetPrevNode()
    {
        return this.PrevNode;
    }

    //mutators
    public void SetData(Character data) throws Exception
    {
        ValidateData(data);
        this.Data = data;
    }

    public void SetNextNode(LetterNode node)
    {
        this.NextNode = node;
    }

    public void SetPrevNode(LetterNode node)
    {
        this.PrevNode = node;
    }

    //validates the data portion of the node
    private void ValidateData(Character data) throws Exception
    {
        if(data == null) throw new NullPointerException("The nodes data property cannot be null or empty.");
    }
}
