import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class JerksonParser
{
    String rawData;
    Integer errorCount;

    public JerksonParser(String initialRawData)
    {
        this.rawData = initialRawData;
        this.errorCount = 0;
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

    public String[] generatePair(String input)
    {
        Pattern delimiter = Pattern.compile("[;@%\\*\\^]");
        String[] pairs = delimiter.split(input);

        return pairs;
    }

    public String[] separatePairs(String pair)
    {
        Pattern delimiter = Pattern.compile(":");
        String[] keyAndVal = delimiter.split(pair);

        return keyAndVal;
    }

    public String fixCase(String word)
    {
        word = word.toLowerCase();
        String firstLetter = word.substring(0, 1);
        firstLetter = firstLetter.toUpperCase();

        return firstLetter + word.substring(1);
    }
}
