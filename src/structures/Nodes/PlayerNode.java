package structures.Nodes;

import models.Player;

public class PlayerNode {
    private Player Data;
    private PlayerNode NextNode;

    public PlayerNode(){};

    public PlayerNode(Player data, PlayerNode nextNode) throws Exception
    {
        SetData(data);
        SetNextNode(nextNode);
    }

    public PlayerNode(PlayerNode node) throws Exception
    {
        if(node == null) throw new NullPointerException("The nodes cannot be null or empty.");
        SetData(node.GetData());
        SetNextNode(node.GetNextNode());
    }

    //accessors
    public Player GetData()
    {
        return this.Data;
    }

    public PlayerNode GetNextNode()
    {
        return this.NextNode;
    }

    //mutators
    public void SetData(Player data) throws Exception
    {
        ValidateData(data);
        this.Data = data;
    }

    public void SetNextNode(PlayerNode node)
    {
        this.NextNode = node;
    }

    //validate the data portion of the node
    private void ValidateData(Player data) throws Exception
    {
        if(data == null) throw new NullPointerException("The nodes data property cannot be null or empty.");
    }
}
