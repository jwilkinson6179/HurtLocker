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
        String[] expected = { "one:2", "three:IV" };
        String input = "one:2;three:IV";

        String[] actual = test.generatePair(input);

        assertEquals(expected, actual);
    }

    @Test
    public void generatePairWithEscapedTest()
    {
        String[] expected = { "five:6", "SEVEN:ate", "ninE:10" };
        String input = "five:6^SEVEN:ate*ninE:10";

        String[] actual = test.generatePair(input);

        assertEquals(expected, actual);
    }

    @Test
    public void fixCaseTest()
    {
        String expected = "Expected";
        String input = "EXPECTED";

        String actual = test.fixCase(input);

        assertEquals(expected, actual);
    }

    @Test
    public void fixCaseNumbersTest()
    {
        String expected = "12.96";
        String input = "12.96";

        String actual = test.fixCase(input);

        assertEquals(expected, actual);
    }
}