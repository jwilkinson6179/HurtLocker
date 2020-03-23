import java.util.ArrayList;
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
            keyAndValue[0] = fixCase(keyAndValue[0]);
            keyAndValue[0] = cookieFixer(keyAndValue[0]);

            if(keyAndValue[0].equals("Name"))
            {
                name = keyAndValue[1];
            }
            else if(keyAndValue[0].equals("Price"))
            {
                price = keyAndValue[1];
            }
            else if(keyAndValue[0].equals("Type"))
            {
                type = keyAndValue[1];
            }
            else if(keyAndValue[0].equals("Expiration"))
            {
                date = keyAndValue[1];
            }
        }

        generatedItem = new GroceryItem(name, price, type, date);

        return generatedItem;
    }

    // TODO: Make this use regex
    public String fixCase(String word)
    {
        word = word.toLowerCase();
        String firstLetter = word.substring(0, 1);
        firstLetter = firstLetter.toUpperCase();

        return firstLetter + word.substring(1);
    }

    public String cookieFixer(String word)
    {
        Pattern cookiePermutation = Pattern.compile("[Cc]..[Kk][Ii][Ee]");

        Matcher fixed = cookiePermutation.matcher(word);
        String output = fixed.replaceAll("Cookie");

        return output;
    }
}
