package xyz.langyo.wsbash.java.client;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import xyz.langyo.wsbash.java.client,command.Command;

import java.util.List;
import java.util.Arrays;

public class CommandTest extends TestCase {
    public CommandTest( String testName ) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite( CommandTest.class );
    }
}