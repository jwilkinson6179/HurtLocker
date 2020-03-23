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

        String[] actual = test.generatePairs(input);

        assertEquals(expected, actual);
    }

    @Test
    public void generatePairWithEscapedTest()
    {
        String[] expected = { "five:6", "SEVEN:ate", "ninE:10" };
        String input = "five:6^SEVEN:ate*ninE:10";

        String[] actual = test.generatePairs(input);

        assertEquals(expected, actual);
    }

    @Test
    public void separatePairTest()
    {
        String[] expected = {"five",  "7" };
        String input = "five:7";

        String[] actual = test.separatePair(input);

        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void separatePairErrorTest()
    {
        Integer expected = 1;
        String input = "five:";

        test.separatePair(input);

        Integer actual = test.getErrorCount();

        assertEquals(expected, actual);
    }

    @Test
    public void cookieFixerTest()
    {
        String expected = "Cookie";
        String input = "C00Kie";

        String actual = test.cookieFixer(input);

        assertEquals(expected, actual);
    }

    @Test
    public void cookieFixerTwoTest()
    {
        String expected = "Cookie";
        String input = "COOKIE";

        String actual = test.cookieFixer(input);

        assertEquals(expected, actual);
    }

    @Test
    public void generateGroceryItemTest()
    {
        String expected = "Milk, 3.23";
        String input = "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016";
        GroceryItem item = test.generateItem(input);

        String actual = item.toString();

        assertEquals(expected, actual);
    }

    @Test
    public void matchIgnoreCaseTest()
    {
        Boolean expected = true;

        Boolean actual = test.matchIgnoreCase("MILK", "MiLk");

        assertEquals(expected, actual);
    }

    @Test
    public void matchIgnoreCaseTwoTest()
    {
        Boolean expected = true;

        Boolean actual = test.matchIgnoreCase("milk", "MIlk");

        assertEquals(expected, actual);
    }

    @Test
    public void matchIgnoreCaseThreeTest()
    {
        Boolean expected = false;

        Boolean actual = test.matchIgnoreCase("MILK", "MiLkkkk");

        assertEquals(expected, actual);
    }

    @Test
    public void matchIgnoreCaseFourTest()
    {
        Boolean expected = false;

        Boolean actual = test.matchIgnoreCase("MILK", "Apple");

        assertEquals(expected, actual);
    }
}