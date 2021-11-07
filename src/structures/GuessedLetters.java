package structures;

import structures.Nodes.DoublyGenericNode;

public class GuessedLetters {

    DoublyGenericNode<Character> Head;
    DoublyGenericNode<Character> Tail;
    int Length;

    public GuessedLetters()
    {
        this.Head = null;
        this.Tail = null;
        this.Length = 0;
    }

    public void Enqueue(Character guess) throws Exception{
        if(guess == null)return;
        DoublyGenericNode<Character> node = new DoublyGenericNode<>(guess,null,null);
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

    public Character Dequeue(){
        if(IsEmpty())return null;
        DoublyGenericNode<Character> node = this.Head;
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

    public Character Peek(){
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
            System.out.print("Letters Guessed: ");
            DoublyGenericNode<Character> temp = Head;
            while (temp.GetNextNode() != null){
                System.out.print(temp.GetData().charValue());
                temp = temp.GetNextNode();
            }
            System.out.print(temp.GetData().charValue());
        }else{
            System.out.println("No Letters Guessed");
        }
    }
}
