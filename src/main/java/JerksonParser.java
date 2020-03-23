import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JerksonParser
{
    String rawData;
    Integer errorCount;
    List<GroceryItem> groceryList;

    public JerksonParser(String initialRawData)
    {
        this.rawData = initialRawData;
        this.errorCount = 0;
        groceryList = new ArrayList<>();
    }

    public JerksonParser()
    {
        this("");
    }

    public void setRawData(String rawData)
    {
        this.rawData = rawData;
    }

    public String getRawData()
    {
        return rawData;
    }

    public Integer getErrorCount()
    {
        return errorCount;
    }

    public String[] createListOfEntries()
    {
        Pattern delimiter = Pattern.compile("##");
        String[] entries = delimiter.split(rawData);

        return entries;
    }

    public String[] generatePairs(String input)
    {
        Pattern delimiter = Pattern.compile("[;@%\\*\\^]");
        String[] pairs = delimiter.split(input);

        return pairs;
    }

    public String[] separatePair(String pair)
    {
        Pattern delimiter = Pattern.compile(":");
        String[] keyAndVal = delimiter.split(pair);

        if(keyAndVal.length != 2)
        {
            System.err.println("The key "+ keyAndVal[0] + " has no associated value.");
            errorCount++;
            throw new IllegalArgumentException();
        }

        return keyAndVal;
    }

    public GroceryItem generateItem(String entry)
    {
        GroceryItem generatedItem = null;
        String[] pairs = generatePairs(entry);
        String name = "";
        String price = "";
        String type = "";
        String date = "";

        for(String pair : pairs)
        {
            String[] keyAndValue = separatePair(pair);
            keyAndValue[0] = cookieFixer(keyAndValue[0]);

            if(matchIgnoreCase(keyAndValue[0], "Name"))
            {
                name = keyAndValue[1];
            }
            else if(matchIgnoreCase(keyAndValue[0], "Price"))
            {
                price = keyAndValue[1];
            }
            else if(matchIgnoreCase(keyAndValue[0], "Type"))
            {
                type = keyAndValue[1];
            }
            else if(matchIgnoreCase(keyAndValue[0], "Expiration"))
            {
                date = keyAndValue[1];
            }
        }

        generatedItem = new GroceryItem(name, price, type, date);

        return generatedItem;
    }

    public Boolean matchIgnoreCase(String first, String second)
    {
        Pattern pattern = Pattern.compile(first, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(second);

        return matcher.matches();
    }

    public String cookieFixer(String word)
    {
        Pattern cookiePermutation = Pattern.compile("[Cc]..[Kk][Ii][Ee]");

        Matcher fixed = cookiePermutation.matcher(word);
        String output = fixed.replaceAll("Cookie");

        return output;
    }

    public List<GroceryItem> generateGroceryList()
    {
        List<GroceryItem> output = new ArrayList<>();
        String[] entries = createListOfEntries();

        for(String el : entries)
        {
            output.add(generateItem(el));
        }

        return output;
    }
}