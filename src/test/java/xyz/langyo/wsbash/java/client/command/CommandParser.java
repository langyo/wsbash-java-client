package xyz.langyo.wsbash.java.client;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import xyz.langyo.wsbash.java.client,command.CommandParser;

import java.util.List;
import java.util.Arrays;

public class CommandParserTest extends TestCase {
    public CommandParserTest( String testName ) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite( CommandParserTest.class );
    }

    @Test
    public void testParse() {
        assertEquals(CommandParser.parse("execute system register h5"), Arrays.asList("execute", "system", "register", "h5"));
    }
}