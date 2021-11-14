package structures.Nodes;

public class LetterNode {
    private Character Data;
    private LetterNode NextNode;
    private LetterNode PrevNode;

    public LetterNode(Character data, LetterNode nextNode, LetterNode prevNode) throws Exception{
        ValidateData(data);
        this.Data = data;
        this.NextNode = nextNode;
        this.PrevNode = prevNode;
    }

    public void SetData(Character data) throws Exception{
        ValidateData(data);
        this.Data = data;
    }

    public Character GetData(){
        return this.Data;
    }

    public void SetNextNode(LetterNode node){
        this.NextNode = node;
    }

    public LetterNode GetNextNode(){
        return this.NextNode;
    }

    public void SetPrevNode(LetterNode node){
        this.PrevNode = node;
    }

    public LetterNode GetPrevNode(){
        return this.PrevNode;
    }

    private void ValidateData(Character data) throws Exception{
        if(data == null) throw new NullPointerException("The nodes data property cannot be null or empty.");
    }
}
