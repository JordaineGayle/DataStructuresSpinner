package structures;
import structures.Nodes.LetterNode;

public class GuessedLetters {

    LetterNode Front;
    LetterNode Rear;
    int Length;

    public GuessedLetters()
    {
        this.Front = null;
        this.Rear = null;
        this.Length = 0;
    }

    public void Enqueue(Character guess) throws Exception{
        if(guess == null)return;
        LetterNode node = new LetterNode(guess,null,null);
        if(this.Front == null){
            this.Rear = node;
            this.Front = this.Rear;
            Length++;
            return;
        }

        Rear.SetNextNode(node);
        node.SetPrevNode(this.Rear);
        this.Rear = node;
        Length++;
    }

    public Character Dequeue(){
        if(IsEmpty())return null;
        LetterNode node = this.Front;
        if(this.Front == this.Rear){
            Length--;
            this.Front = null;
            this.Rear = null;
            return node.GetData();
        }

        this.Front = this.Front.GetNextNode();
        Length--;
        return node.GetData();
    }

    public Character Peek(){
        if(!IsEmpty()){
            return this.Front.GetData();
        }
        return null;
    }

    public boolean IsEmpty(){
        return this.Length <= 0;
    }

    public int GetLength(){
        return this.Length;
    }

    /*
    * public void Display(){
        if(!IsEmpty()){
            System.out.print("Letters Guessed: ");
            LetterNode temp = Front;
            while (temp.GetNextNode() != null){
                System.out.print(temp.GetData().charValue());
                temp = temp.GetNextNode();
            }

            System.out.print(temp.GetData().charValue());
        }else{
            System.out.println("No Letters Guessed");
        }
    }
    * */

    public void Display(){
        if(!IsEmpty()){
            GuessedLetters tempQueue = new GuessedLetters();
            System.out.print("Letters Guessed: ");
            LetterNode temp = Front;
            while (temp != null){
                System.out.print(temp.GetData().charValue());
                try{
                    tempQueue.Enqueue(Dequeue());
                }catch (Exception ex){}
                temp = temp.GetNextNode();
            }

            temp = tempQueue.Front;

            while(temp != null)
            {
                try{
                    Enqueue(tempQueue.Dequeue());
                }catch (Exception ex){}
                temp = temp.GetNextNode();
            }

        }else{
            System.out.println("No Letters Guessed");
        }
    }
}
