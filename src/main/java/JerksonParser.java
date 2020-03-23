import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class JerksonParser
{
    String rawData;

    public JerksonParser(String initialRawData)
    {
        this.rawData = initialRawData;
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
        return null;
    }
}
