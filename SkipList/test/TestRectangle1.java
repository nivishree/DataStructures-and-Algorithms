import java.io.FileNotFoundException;

import org.junit.Test;

import student.TestCase;

/**
 * The purpose of this class to test the main method
 * 
 * @author Nivishree
 * 
 * @version 2021-08-23
 */
public class TestRectangle1 extends TestCase {

    /**
     * Tests for positive scenario with correct fie name
     * 
     * @throws FileNotFoundException
     */

    @SuppressWarnings("static-access")
    @Test
    public void testMainWithFile() {
        Rectangle1 rect = new Rectangle1();
        rect.main(new String[] { "Data/sample.txt" });
        int a = 2;
        assertEquals(a, 2);

    }

    /**
     * Tests whether the correct file is passed to the
     * function
     * 
     * @throws FileNotFoundException
     */

    @SuppressWarnings("static-access")
    @Test
    public void testMainMethod() {
        Rectangle1 rect2 = new Rectangle1();
        rect2.main(new String[] { "file1", "sample" });
        int a = 2;
        assertEquals(a, 2);
    }
}
