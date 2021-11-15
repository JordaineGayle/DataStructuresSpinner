package structures;
import structures.Nodes.LetterNode;

public class GuessedLetters {

    private LetterNode Front;
    private LetterNode Rear;
    private int Length;

    public GuessedLetters()
    {
        SetFront(null);
        SetRear(null);
        SetLength(0);
    }

    public GuessedLetters(LetterNode front, LetterNode rear, int length)
    {
        SetFront(front);
        SetRear(rear);
        SetLength(length);
    }

    public GuessedLetters(GuessedLetters guessedLetters)
    {
        SetFront(guessedLetters.GetFront());
        SetRear(guessedLetters.GetRear());
        SetLength(guessedLetters.GetLength());
    }

    //accessors
    public LetterNode GetFront()
    {
        return Front;
    }

    public LetterNode GetRear()
    {
        return Rear;
    }

    public int GetLength() {
        return Length;
    }

    //mutators
    public void SetFront(LetterNode front)
    {
        Front = front;
    }

    public void SetRear(LetterNode rear)
    {
        Rear = rear;
    }

    public void SetLength(int length)
    {
        Length = length;
    }

    //add an item to the back of the queue
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

    //removes the very first item from the top of the queue
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

    //looking at the very first value in the queue without dequeuing it
    public Character Peek(){
        if(!IsEmpty()){
            return this.Front.GetData();
        }
        return null;
    }

    //checks if the queue is empty
    public boolean IsEmpty()
    {
        return this.Length <= 0;
    }

    //check if an item exist within the queue
    public boolean InQueue(char letter)
    {
        if(!IsEmpty())
        {
            GuessedLetters tempQueue = new GuessedLetters();
            LetterNode temp = Front;
            boolean foundLetter = false;
            while (temp != null)
            {
                try
                {
                    Character value = Dequeue();

                    if(value.charValue() == letter)
                    {
                        foundLetter = true;
                    }
                    tempQueue.Enqueue(value);
                }
                catch (Exception ex)
                {}

                temp = temp.GetNextNode();

            }

            temp = tempQueue.Front;

            while(temp != null)
            {
                try
                {
                    Enqueue(tempQueue.Dequeue());
                }
                catch (Exception ex)
                {}

                temp = temp.GetNextNode();
            }

            return foundLetter;
        }
        return false;
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
            while (temp != null)
            {
                Character value = Dequeue();

                System.out.print(value.charValue());

                try
                {
                    tempQueue.Enqueue(value);
                }
                catch (Exception ex)
                {}

                temp = temp.GetNextNode();
            }

            temp = tempQueue.Front;

            while(temp != null)
            {
                try
                {
                    Enqueue(tempQueue.Dequeue());
                }
                catch (Exception ex)
                {}

                temp = temp.GetNextNode();
            }

        }
        else
        {
            System.out.println("No Letters Guessed");
        }
    }
}
