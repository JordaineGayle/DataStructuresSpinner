package structures;

public class KVP {
    int Key;
    int[] Values;
    int CurrentPosition;

    public KVP(int length)
    {
        CurrentPosition = 0;
        this.Values = new int[length];
    }

    public void Insert(int key, int value)
    {
        this.Key = key;
        Values[CurrentPosition] = value;
        CurrentPosition++;
    }

    public int[] GetValues()
    {
        return this.Values;
    }

    public void Display(){
        System.out.print("Key: "+(char)Key+", Values: [");
        for (int x =0; x < CurrentPosition; x++){
            System.out.print(Values[x]+",");
        }
        System.out.print("]\n");
    }
}
