package structures;

import models.Card;
import models.GuessedLetter;

public class GuessedLetters {

    DoublyGenericNode<GuessedLetter> Head;
    DoublyGenericNode<GuessedLetter> Tail;
    int Length;

    public GuessedLetters()
    {
        this.Head = null;
        this.Tail = null;
        this.Length = 0;
    }

    public void Enqueue(GuessedLetter guess) throws Exception{
        if(guess == null)return;
        DoublyGenericNode<GuessedLetter> node = new DoublyGenericNode<>(guess,null,null);
        if(this.Head == null){
            this.Tail = node;
            this.Head = this.Tail;
            Length++;
            return;
        }

        Tail.SetNextNode(node);
        node.SetPrevNode(this.Tail);
        this.Tail = node;
        Length++;
    }

    public GuessedLetter Dequeue(){
        if(IsEmpty())return null;
        DoublyGenericNode<GuessedLetter> node = this.Head;
        if(this.Head == this.Tail){
            Length--;
            this.Head = null;
            this.Tail = null;
            return node.GetData();
        }

        this.Head = this.Head.GetNextNode();
        Length--;
        return node.GetData();
    }

    public GuessedLetter Peek(){
        if(!IsEmpty()){
            return this.Head.GetData();
        }
        return null;
    }

    public boolean IsEmpty(){
        return this.Length <= 0;
    }

    public int GetLength(){
        return this.Length;
    }

    public void Display(){
        if(!IsEmpty()){
            DoublyGenericNode<GuessedLetter> temp = Head;
            while (temp.GetNextNode() != null){
                System.out.println(temp.GetData().ToString());
                temp = temp.GetNextNode();
            }
            System.out.println(temp.GetData().ToString());
        }else{
            System.out.println("Queue is empty");
        }
    }
}
