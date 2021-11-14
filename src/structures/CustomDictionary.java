package structures;

public class CustomDictionary {

    KVP[] Items;
    int Length;

    public CustomDictionary(int length)
    {
        this.Length = length;
        Items = new KVP[127];
    }

    public void Insert(char key, int index)
    {
        KVP kvp;
        if(Items[key] == null)
        {
            kvp = new KVP(this.Length);
            kvp.Insert(key,index);
            Items[key] = kvp;
        }
        else
        {
            kvp = Items[key];
            kvp.Insert(key,index);
        }
    }

    public KVP DeleteAt(char key)
    {
        KVP deleted = Items[key];
        Items[key] = null;
        return deleted;
    }

    public KVP GetKVP(char key)
    {
        return Items[key];
    }
}
