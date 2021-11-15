package structures;

import models.Player;
import structures.Nodes.PlayerNode;

public class Players {

    private int Length;
    private PlayerNode Head;

    public Players()
    {
        Length = 0;
        Head = null;
    }

    public Players(PlayerNode head){
        this.Head = head;
    }

    public int GetLength() {
        return Length;
    }

    public void SetLength(int length) {
        Length = length;
    }

    public PlayerNode GetHead() {
        return Head;
    }

    public void SetHead(PlayerNode head) {
        Head = head;
    }

    //insert at end
    public void Append(Player player) throws Exception
    {
        player.SetNumber(Length+1);
        player.SetName((player.GetName()+"_"+player.GetNumber()).toUpperCase().replace(" ","_"));
        PlayerNode newNode = new PlayerNode(player,null);

        if(this.Head == null)
        {
            this.Head = new PlayerNode(newNode);
            this.Head.SetNextNode(this.Head);
            Length++;
        }
        else
        {
            PlayerNode nextNode = this.Head.GetNextNode();
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

        PlayerNode node = this.Head;
        int count = 0;
        while (true){
            if(count == index)
            {
                PlayerNode tempNode = this.Head;
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

    public PlayerNode GetPlayerByNumber(int number)
    {

        PlayerNode nextNode = this.Head;
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
        PlayerNode temp = Head;
        while (temp.GetNextNode() != Head){
            System.out.println(temp.GetData().ToString());
            temp = temp.GetNextNode();
        }
        System.out.println(temp.GetData().ToString());
    }

    public void ResetGrandTotals(){
        if(Head  == null){
            return;
        }
        PlayerNode temp = Head;
        while (temp.GetNextNode() != Head){
            temp = temp.GetNextNode();
            temp.GetData().ResetGrandTotal(0);
        }
        temp.GetData().ResetGrandTotal(0);
    }

    public void DisplayLength(){
        System.out.println("Length: "+GetLength());
    }

}
