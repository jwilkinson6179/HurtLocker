import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JerksonParserTest
{
    JerksonParser test;

    @Before
    public void setup()
    {
        test = new JerksonParser();
    }

    @Test
    public void createListOfEntriesTest()
    {
        String[] expected = { "Key:1;valuE:one", "key:two;values:b", "KEY:FFOUR;VAL:iii" };
        String testData = "Key:1;valuE:one##key:two;values:b##KEY:FFOUR;VAL:iii##";

        test.setRawData(testData);
        String[] actual = test.createListOfEntries();

        assertEquals(expected, actual);
    }

    @Test
    public void createListOfEntriesNoEntriesTest()
    {
        String[] expected = { };
        String testData = "##";

        test.setRawData(testData);
        String[] actual = test.createListOfEntries();

        assertEquals(expected, actual);
    }

    @Test
    public void generatePairTest()
    {
        String[] expected = { "one", "2" }
        String input = "One:2;";

        String[] actual = test.generatePair(input);

        assertEquals(1, 2);
    }
}