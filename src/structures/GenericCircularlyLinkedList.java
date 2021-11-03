package structures;

import models.Card;

public class GenericCircularlyLinkedList<T> {
    int Length;
    SinglyGenericNode<T> Head;

    public GenericCircularlyLinkedList()
    {
        Length = 0;
        Head = null;
    }

    public GenericCircularlyLinkedList(SinglyGenericNode<T> head){
        this.Head = head;
    }

    public void Append(T data) throws Exception
    {
        SinglyGenericNode<T> newNode = new SinglyGenericNode<>(data,null);

        if(this.Head == null)
        {
            this.Head = new SinglyGenericNode(newNode);
            this.Head.SetNextNode(this.Head);
            Length++;
        }
        else
        {
            SinglyGenericNode<T> nextNode = this.Head.GetNextNode();
            while (nextNode.GetNextNode() != this.Head){
                nextNode = nextNode.GetNextNode();
            }
            nextNode.SetNextNode(newNode);
            newNode.SetNextNode(this.Head);
            Length++;
        }
    }

    public final SinglyGenericNode<T> GetHead(){
        return this.Head;
    }
}
