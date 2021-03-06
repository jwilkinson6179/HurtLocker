import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JerksonParser
{
    String rawData;
    List<GroceryItem> groceryList;
    Integer errorCount;
    // Should Map here instead of groceryList but out of time
    // Maybe even map that's <<K,V>, V> of <<name, price> occurrences>
    // Alongside map of <K, V> for <name, occurrences>
    // Approached this the wrong way.  Didn't think how I was organizing
    // data well enough.  Should have done that first.
    Map<String, Integer> itemCount;

    public JerksonParser(String initialRawData)
    {
        this.rawData = initialRawData;
        this.errorCount = 0;
        groceryList = new ArrayList<>();
        itemCount = new HashMap<>();
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
            String[] keyAndValue = new String[2];
            try{
                keyAndValue = separatePair(pair);
            } catch(Exception e)
            {

            }
            keyAndValue[0] = cookieFixer(keyAndValue[0]);

            if(matchIgnoreCase(keyAndValue[0], "Name"))
            {
                name = keyAndValue[1];
                if(matchIgnoreCase(keyAndValue[0], "Milk"))
                {
                    if(!itemCount.containsKey("Milk"))
                    {
                        itemCount.putIfAbsent("Milk", 1);
                    }
                    else
                    {
                        Integer currentCount = itemCount.get("Milk");
                        itemCount.put("Milk", currentCount + 1);
                    }
                }
                else if(matchIgnoreCase(keyAndValue[0], "Bread"))
                {
                    if(!itemCount.containsKey("Bread"))
                    {
                        itemCount.putIfAbsent("Bread", 1);
                    }
                    else
                    {
                        Integer currentCount = itemCount.get("Bread");
                        itemCount.put("Bread", currentCount + 1);
                    }
                }
                else if(matchIgnoreCase(keyAndValue[0], "Cookies"))
                {
                    if(!itemCount.containsKey("Cookies"))
                    {
                        itemCount.putIfAbsent("Cookies", 1);
                    }
                    else
                    {
                        Integer currentCount = itemCount.get("Cookies");
                        itemCount.put("Cookies", currentCount + 1);
                    }
                }
                else if(matchIgnoreCase(keyAndValue[0], "Apples"))
                {
                    if(!itemCount.containsKey("Apples"))
                    {
                        itemCount.putIfAbsent("Apples", 1);
                    }
                    else
                    {
                        Integer currentCount = itemCount.get("Apples");
                        itemCount.put("Apples", currentCount + 1);
                    }
                }
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

    public ArrayList<GroceryItem> generateGroceryList()
    {
        ArrayList<GroceryItem> output = new ArrayList<>();
        String[] entries = createListOfEntries();

        for(String el : entries)
        {
            output.add(generateItem(el));
        }

        return output;
    }

    // Planned to track items+price in a map, then use that map to print results
    public void printList()
    {
//        ArrayList<GroceryItem> groceryList = generateGroceryList();
        Map<String, Integer> itemAtPriceCount = new HashMap<>();
        StringBuilder output = new StringBuilder();

//        for(Integer i = 0; i < groceryList.size(); i++)
//        {
//            String itemAndPrice = groceryList.get(0).toString();
//        }
        output.append(String.format("\nname: %s\t\t\tseen: %d times", "Phold", 1));
        output.append(String.format("\n=============\t\t============="));
        output.append(String.format("\nErrors\t\t\t\tseen: %d times\n", errorCount));
        System.out.println(output.toString());
    }
    // Make an object that tracks by name, has a count, and then has a map of <k, v> as <price, occurrences>
}