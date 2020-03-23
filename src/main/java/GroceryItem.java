public class GroceryItem
{
    String name;
    String price;
    String expirationDate;

    public GroceryItem(String name, String price, String expirationDate)
    {
        this.name = name;
        this.price = price;
        this.expirationDate = expirationDate;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPrice()
    {
        return price;
    }

    public void setPrice(String price)
    {
        this.price = price;
    }

    public String getExpirationDate()
    {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate)
    {
        this.expirationDate = expirationDate;
    }
}