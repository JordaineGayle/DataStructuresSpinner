package structures;

public class DoublyGenericNode<T> {
    private T Data;
    private DoublyGenericNode<T> NextNode;
    private DoublyGenericNode<T> PrevNode;

    public DoublyGenericNode(T data, DoublyGenericNode<T> nextNode, DoublyGenericNode<T> prevNode) throws Exception{
        ValidateData(data);
        this.Data = data;
        this.NextNode = nextNode;
        this.PrevNode = prevNode;
    }

    public void SetData(T data) throws Exception{
        ValidateData(data);
        this.Data = data;
    }

    public T GetData(){
        return this.Data;
    }

    public void SetNextNode(DoublyGenericNode<T> node){
        this.NextNode = node;
    }

    public DoublyGenericNode<T> GetNextNode(){
        return this.NextNode;
    }

    public void SetPrevNode(DoublyGenericNode<T> node){
        this.PrevNode = node;
    }

    public DoublyGenericNode<T> GetPrevNode(){
        return this.PrevNode;
    }

    private void ValidateData(T data) throws Exception{
        if(data == null) throw new NullPointerException("The nodes data property cannot be null or empty.");
    }
}


