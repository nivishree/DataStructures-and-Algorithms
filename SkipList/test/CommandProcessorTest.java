import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

import student.TestCase;

/**
 * This class is a test class for CommandProcessor.java
 * file
 * 
 * @author Nivishree
 * 
 * @version 2021-09-22
 *
 */
public class CommandProcessorTest extends TestCase {

    /**
     * Test the processor method in CommandProcesor class
     * which navigates to SkipList method depending on
     * command arguments
     */
    @Test
    public void testProcessor() {

        ByteArrayOutputStream outputStreamCaptor =
                new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        CommandProcessor cmdP = new CommandProcessor();
        cmdP.processor("insert r1 10 10 10 10");
        cmdP.processor("insert r1 0 0 5 5");
        assertTrue(contains(
                outputStreamCaptor.toString().trim(),
                "Rectangle inserted: (r1, 10, 10, 10, 10)"));
        cmdP.processor("search r1");
        assertTrue(contains(
                outputStreamCaptor.toString().trim(),
                "Rectangles found:"));

        cmdP.processor("dump");
        assertTrue(contains(
                outputStreamCaptor.toString().trim(),
                "Node"));
        cmdP.processor("remove r1");
        assertTrue(contains(
                outputStreamCaptor.toString().trim(),
                "removed"));
        cmdP.processor("default");
        assertTrue(contains(
                outputStreamCaptor.toString().trim(),
                "Method does not exists"));
        cmdP.processor("method");
        cmdP.processor("123");
        assertTrue(contains(
                outputStreamCaptor.toString().trim(),
                "Method does not exists"));
        cmdP.processor("regionsearch 0 0 15 15");
        assertTrue(contains(
                outputStreamCaptor.toString().trim(), ""));
        cmdP.processor("remove 0 0 5 5");
        assertTrue(contains(
                outputStreamCaptor.toString().trim(),
                "removed"));
        cmdP.processor("intersections");
    }

}
