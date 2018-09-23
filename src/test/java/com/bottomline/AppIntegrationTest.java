package com.bottomline;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class AppIntegrationTest {

    private final PrintStream systemOut = System.out;

    private ByteArrayOutputStream testOut;

    @Before
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private String getOutput() {
        return testOut.toString();
    }

    @After
    public void restoreSystemInputOutput() {
        System.setOut(systemOut);
    }

    @Test
    public void testCalculateRepayment1() {
        App.main(new String[]{"10.00", "4"});

        assertEquals("Regular Amount\t\t\t£2.50\n", getOutput());
    }

    @Test
    public void testCalculateRepayment2() {
        App.main(new String[]{"10.00", "3"});

        assertEquals("Regular Amount\t\t\t£3.33\nLast Amount\t\t\t£3.34\n", getOutput());
    }

    @Test
    public void testCalculateRepayment3() {
        App.main(new String[]{"5444333222.00", "1234"});

        assertEquals("Regular Amount\t\t\t£4,411,939.40\nLast Amount\t\t\t£4,411,941.80\n", getOutput());
    }

    @Test
    public void testCalculateRepayment4() {
        App.main(new String[]{"", ""});

        assertEquals("Proper Usage is (e.g.): java -cp target/cc-1.0-SNAPSHOT.jar com.bottomline.App 10.00 3.00", getOutput());
    }

}