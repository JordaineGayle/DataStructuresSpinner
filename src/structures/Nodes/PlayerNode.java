package structures.Nodes;

import models.Player;

public class PlayerNode {
    private Player Data;
    private PlayerNode NextNode;

    public PlayerNode(Player data, PlayerNode nextNode) throws Exception{
        ValidateData(data);
        this.Data = data;
        this.NextNode = nextNode;
    }

    public PlayerNode(PlayerNode node) throws Exception{
        if(node == null) throw new NullPointerException("The nodes cannot be null or empty.");
        ValidateData(node.Data);
        this.Data = node.Data;
        this.NextNode = node.NextNode;
    }

    public void SetData(Player data) throws Exception{
        ValidateData(data);
        this.Data = data;
    }

    public Player GetData(){
        return this.Data;
    }

    public void SetNextNode(PlayerNode node){
        this.NextNode = node;
    }

    public PlayerNode GetNextNode(){
        return this.NextNode;
    }

    private void ValidateData(Player data) throws Exception{
        if(data == null) throw new NullPointerException("The nodes data property cannot be null or empty.");
    }
}
