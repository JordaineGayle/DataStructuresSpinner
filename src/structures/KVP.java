package structures;

public class KVP {
    char Key;
    String[] Values;
    int CurrentPosition;
    int Count;

    public KVP(int length)
    {
        CurrentPosition = 0;
        this.Values = new String[length];
        this.Count = 0;
    }

    public void Insert(char key, int value)
    {
        this.Key = key;
        Values[CurrentPosition] = Integer.toString(value);
        CurrentPosition++;
        Count++;
    }

    public String[] GetValues()
    {
        return this.Values;
    }

    public char GetKey()
    {
        return (char)Key;
    }

    public int GetCount()
    {
        return Count;
    }

    public void Display(){
        System.out.print("Key: "+(char)Key+", Values: [");
        for (int x =0; x < CurrentPosition; x++){
            System.out.print(Values[x]+",");
        }
        System.out.print("]\n");
    }
}
