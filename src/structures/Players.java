package structures;

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


//    public void Display(){
//        SinglyGenericNode<Player> nextNode = this.Head;
//        while (nextNode.GetNextNode() != this.Head)
//        {
//            System.out.println(nextNode.GetData().ToString());
//            nextNode = nextNode.GetNextNode();
//        }
//        System.out.println(nextNode.GetData().ToString());
//    }


}
