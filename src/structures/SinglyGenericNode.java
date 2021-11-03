package structures;

public class SinglyGenericNode<T> {
    T Data;
    SinglyGenericNode<T> NextNode;

    public SinglyGenericNode(T data, SinglyGenericNode<T> nextNode) throws Exception{
        ValidateData(data);
        this.Data = data;
        this.NextNode = nextNode;
    }

    public SinglyGenericNode(SinglyGenericNode<T> node) throws Exception{
        if(node == null) throw new NullPointerException("The nodes cannot be null or empty.");
        ValidateData(node.Data);
        this.Data = node.Data;
        this.NextNode = node.NextNode;
    }

    public void SetData(T data) throws Exception{
        ValidateData(data);
        this.Data = data;
    }

    public T GetData(){
        return this.Data;
    }

    public void SetNextNode(SinglyGenericNode<T> node){
        this.NextNode = node;
    }

    public SinglyGenericNode<T> GetNextNode(){
        return this.NextNode;
    }

    private void ValidateData(T data) throws Exception{
        if(data == null) throw new NullPointerException("The nodes data property cannot be null or empty.");
    }
}
