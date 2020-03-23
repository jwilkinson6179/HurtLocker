public class Pair
{
    String key;
    String value;

    public Pair(String key, String value)
    {
        this.key = key;
        this.value = value;
    }

    public String getKey()
    {
        return key;
    }

    public String getValue()
    {
        return value;
    }

    public boolean equals(Pair o)
    {
        return(this.key.equals(o.getKey()) && this.value.equals(o.getValue()));
    }
}