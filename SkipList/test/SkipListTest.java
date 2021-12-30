import java.util.ArrayList;

import org.junit.Test;

import student.TestCase;

/**
 * This class is a test class for SkipList.java file
 * 
 * @author Nivishree
 * 
 * @version 2021-09-22
 *
 */

public class SkipListTest extends TestCase {

    /**
     * tests in insert method in SkipList class
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void testInsert() {
        Rectangle rect = new Rectangle(1, 2, 3, 4);
        KVPair<String, Rectangle> kvpair =
                new KVPair<String, Rectangle>("r1", rect);
        Rectangle rect1 =
                new Rectangle(100, 100, 100, 100);
        KVPair<String, Rectangle> kvpair1 =
                new KVPair<String, Rectangle>("r4", rect1);
        SkipList slTestInsert = new SkipList();
        slTestInsert.insert(kvpair1);
        slTestInsert.insert(kvpair);
        assertEquals(2, slTestInsert.size());
    }

    /**
     * tests in size method in SkipList class
     */
    @SuppressWarnings("rawtypes")
    @Test
    public void testSize() {
        SkipList slTest = new SkipList();
        assertEquals(0, slTest.size());
    }

    /**
     * tests in search method in SkipList class
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void testSearch() {
        Rectangle rect = new Rectangle(1, 2, 3, 4);
        KVPair<String, Rectangle> kvpair =
                new KVPair<String, Rectangle>("r1", rect);
        Rectangle rect1 =
                new Rectangle(100, 100, 100, 100);
        KVPair<String, Rectangle> kvpair1 =
                new KVPair<String, Rectangle>("r4", rect1);
        SkipList slTestSearch = new SkipList();
        slTestSearch.insert(kvpair);
        slTestSearch.insert(kvpair1);
        ArrayList<KVPair<String, Rectangle>> actualValue =
                slTestSearch.search("r1");
        assertEquals(actualValue.toString(),
                "[(r1, 1, 2, 3, 4)]");
    }

    /**
     * tests in remove method in SkipList class
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void testRemove() {

        Rectangle rect = new Rectangle(1, 2, 3, 4);
        KVPair<String, Rectangle> kvpair =
                new KVPair<String, Rectangle>("r1", rect);
        Rectangle rect1 =
                new Rectangle(100, 100, 100, 100);
        KVPair<String, Rectangle> kvpair1 =
                new KVPair<String, Rectangle>("r4", rect1);
        SkipList slTestRemoveKey = new SkipList();
        KVPair<String, Rectangle> actualValue3 =
                slTestRemoveKey.remove("r1");
        slTestRemoveKey.insert(kvpair);
        slTestRemoveKey.insert(kvpair1);
        KVPair<String, Rectangle> actualValue =
                slTestRemoveKey.remove("r1");
        KVPair<String, Rectangle> actualValue2 =
                slTestRemoveKey.remove("r19");
        assertEquals(actualValue, kvpair);
        assertEquals(actualValue2, null);
        assertEquals(actualValue3, null);
    }

    /**
     * tests in remove by value method in SkipList class
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void testRemoveByValue() {
        Rectangle rect = new Rectangle(1, 2, 3, 4);
        KVPair<String, Rectangle> kvPair =
                new KVPair<String, Rectangle>("r1", rect);
        SkipList slTestRemove = new SkipList();
        slTestRemove.insert(kvPair);
        KVPair<String, Rectangle> actualValue =
                slTestRemove.removeByValue(rect);
        Rectangle rectFault =
                new Rectangle(0, 0, 1029, 1029);
        slTestRemove.removeByValue(rectFault);

        KVPair<String, Rectangle> actualValue2 =
                slTestRemove.removeByValue(rectFault);
        assertEquals(actualValue, kvPair);
    }

    /**
     * tests in list elements method in SkipList class
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void testListElements() {
        Rectangle rect = new Rectangle(1, 2, 3, 4);
        KVPair<String, Rectangle> kvPair =
                new KVPair<String, Rectangle>("r1", rect);
        SkipList slTestList = new SkipList();
        slTestList.insert(kvPair);
        System.out.println(
                slTestList.listElements().toString());
        assertTrue(slTestList.listElements().toString()
                .contains("[(r1, 1, 2, 3, 4)]"));
    }

    /**
     * tests in dump method in SkipList class
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void testDump() {
//        ByteArrayOutputStream outputStreamCaptor =
//                new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outputStreamCaptor));

        Rectangle rect = new Rectangle(1, 2, 3, 4);
        KVPair<String, Rectangle> kvPair =
                new KVPair<String, Rectangle>("r1", rect);
        SkipList slTestDump = new SkipList();
        slTestDump.insert(kvPair);
        slTestDump.dump();
        assertTrue(systemOut().getHistory()
                .contains("SkipList size is: 1\n"));
    }

}
