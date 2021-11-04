package structures;

import models.Card;
import models.Player;

//this data structure was used, because the size of the players wasn't explicitly specified
public class Players {

    int Length;
    SinglyGenericNode<Player> Head;

    public Players()
    {
        Length = 0;
        Head = null;
    }

    public Players(SinglyGenericNode<Player> head){
        this.Head = head;
    }

    public void Append(Player player) throws Exception
    {
        player.SetNumber(Length+1);
        SinglyGenericNode<Player> newNode = new SinglyGenericNode<>(player,null);

        if(this.Head == null)
        {
            this.Head = new SinglyGenericNode(newNode);
            this.Head.SetNextNode(this.Head);
            Length++;
        }
        else
        {
            SinglyGenericNode<Player> nextNode = this.Head.GetNextNode();
            while (nextNode.GetNextNode() != this.Head){
                nextNode = nextNode.GetNextNode();
            }
            nextNode.SetNextNode(newNode);
            newNode.SetNextNode(this.Head);
            Length++;
        }
    }

    public void DeleteAt(int index)
    {
        if(Length == 0){
            return;
        }
        else if(index > Length){
            return;
        }else if(Length == 1){
            this.Head.SetNextNode(null);
            this.Head = null;
            Length--;
            return;
        }

        SinglyGenericNode<Player> node = this.Head;
        int count = 0;
        while (true){
            if(count == index)
            {
                SinglyGenericNode<Player> tempNode = this.Head;
                int c = 0;
                if(index == 0){
                    index = Length-1;
                }else{
                    index = index - 1;
                }
                while (c < index){
                    tempNode = tempNode.GetNextNode();
                    c++;
                }

                tempNode.SetNextNode(node.GetNextNode());
                if(node == this.Head){
                    this.Head = tempNode.GetNextNode();
                }
                this.Length--;
                break;
            }
            node = node.GetNextNode();
            count++;
        }
    }

    public void ClearList(){

        for (int x = Length; x > 0; x--){
            DeleteAt(x);
        }
        System.out.println("List Cleared");
        System.out.println("Length: "+Length);
        Display();
    }

    public SinglyGenericNode<Player> GetPlayerByNumber(int number)
    {

        SinglyGenericNode<Player> nextNode = this.Head;
        int count = 0;
        while (nextNode.GetData().GetNumber() != number){
            nextNode = nextNode.GetNextNode();
            count++;
            if(count > this.Length){
                return null;
            }
        }

        return nextNode;
    }

    public void Display(){
        if(Head  == null){
            System.out.println("List is empty");
            return;
        }
        SinglyGenericNode<Player> temp = Head;
        while (temp.GetNextNode() != Head){
            System.out.println(temp.GetData().ToString());
            temp = temp.GetNextNode();
        }
        System.out.println(temp.GetData().ToString());
    }

    public void DisplayLength(){
        System.out.println("Length: "+Length);
    }

}
