import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * The purpose of this class to test the KVPair class by
 * passing mock values
 * 
 * @author Nivishree
 * 
 * @version 2021-08-23
 */
public class KVPairTest {

    /**
     * Tests the value of the Key returned fom Getkey
     * method
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Test
    public void testGetKey() {
        Rectangle rect = new Rectangle(1, 2, 3, 4);
        KVPair pair = new KVPair("r1", rect);
        assertEquals(pair.getKey(), "r1");
    }

    /**
     * Tests the value returned from GetValue method
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Test
    public void testGetValue() {
        Rectangle rect = new Rectangle(1, 2, 3, 4);
        KVPair pair = new KVPair("r1", rect);
        assertEquals(pair.getValue(), rect);
    }

    /**
     * Tests the value returned from compareto method
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Test
    public void testCompareTo() {
        Rectangle rect = new Rectangle(1, 2, 3, 4);
        KVPair pair = new KVPair("r1", rect);
        KVPair pair2 = new KVPair("r1", rect);
        assertEquals(pair.compareTo(pair2), 0);
    }

}
