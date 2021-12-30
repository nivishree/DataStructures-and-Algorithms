import org.junit.BeforeClass;
import org.junit.Test;

import student.TestCase;

/**
 * 
 */

/**
 * This class is a test class for Database.java file
 * 
 * @author Nivishree
 * 
 * @version 2021-09-22
 *
 */
public class DatabaseTest extends TestCase {

    private static Database db2;

    /**
     * @throws java.lang.Exception
     */
    @SuppressWarnings("static-access")
    @BeforeClass
    public static void setUpBeforeClass()
            throws Exception {
        Rectangle1 rect = new Rectangle1();
        rect.main(new String[] { "Data/sample.txt" });
        db2 = new Database();
    }

    /**
     * Tests insert method
     */
    @Test
    public void testInsert() {
        Rectangle rect = new Rectangle(200, 200, 200, 299);
        KVPair<String, Rectangle> kvPair =
                new KVPair<String, Rectangle>("r1", rect);
        db2.insert(kvPair);
        Rectangle rect2 = new Rectangle(0, 0, 0, 0);
        KVPair<String, Rectangle> kvPair2 =
                new KVPair<String, Rectangle>("r1", rect2);
        Rectangle rect3 =
                new Rectangle(1029, 1029, 100, 100);
        KVPair<String, Rectangle> kvPair3 =
                new KVPair<String, Rectangle>("r1", rect3);
        Rectangle rect4 =
                new Rectangle(100, 100, 2000, 2000);
        KVPair<String, Rectangle> kvPair4 =
                new KVPair<String, Rectangle>("r1", rect4);
        KVPair<String, Rectangle> kvPair5 =
                new KVPair<String, Rectangle>("78r1##",
                        rect4);
        KVPair<String, Rectangle> kvPair6 =
                new KVPair<String, Rectangle>("@", rect4);
        db2.insert(kvPair3);
        db2.insert(kvPair4);
        db2.insert(kvPair2);
        db2.insert(kvPair5);
        db2.insert(kvPair6);
        assertTrue(systemOut().getHistory().contains(
                "Rectangle inserted: (r1, 200, 200, 200, 299)\n"
                        + "Rectangle rejected: (r1, 1029, 1029, 100, 100)\n"
                        + "Rectangle rejected: (r1, 100, 100, 2000, 2000)\n"
                        + "Rectangle rejected: (r1, 0, 0, 0, 0)\n"
                        + "Rectangle rejected: "
                        + "(78r1##, 100, 100, 2000, 2000)\n"));
    }

    /**
     * Tests the remove method with mock values
     */
    @SuppressWarnings("rawtypes")
    @Test
    public void testRemove() {
        db2.remove("r19");
        Rectangle rect = new Rectangle(1, 2, 3, 4);
        KVPair<String, Rectangle> kvPair =
                new KVPair<String, Rectangle>("r1", rect);
        SkipList sl = new SkipList();
        db2.insert(kvPair);
        db2.dump();
        db2.remove("r1#");
        db2.remove("r1");
        assertTrue(systemOut().getHistory()
                .contains("Rectangle rejected: (r1#)\n"
                        + "Rectangle removed: (r1, 1, 2, 3, 4)"));
    }

    /**
     * Tests remove by value method using mock values
     */
    @SuppressWarnings("rawtypes")
    @Test
    public void testRemoveByValue() {
        db2.remove(-1, -1, 0, 0);
        db2.remove(1345, 4534, 234233, 324234);
        db2.remove(1, 2, 3, 5);
        Rectangle rect = new Rectangle(1, 2, 3, 5);
        KVPair<String, Rectangle> kvPair =
                new KVPair<String, Rectangle>("r1", rect);
        db2.insert(kvPair);
        db2.remove(1, 2, 3, 5);
        db2.remove(1245, 1256, 45643, 34546);
        assertTrue(systemOut().getHistory().contains(
                "Rectangle rejected: (1245, 1256, 45643, 34546)\n"));
    }

    /**
     * Tests region search method with mock values
     */

    @Test
    public void testRegionSearch() {
        Rectangle rect = new Rectangle(1, 2, 3, 5);
        KVPair<String, Rectangle> kvPair =
                new KVPair<String, Rectangle>("r1", rect);
        db2.insert(kvPair);
        Rectangle rect2 = new Rectangle(0, 0, 1, 1);
        KVPair<String, Rectangle> kvPair2 =
                new KVPair<String, Rectangle>("r2", rect2);
        db2.insert(kvPair2);
        db2.regionsearch(-1, -2, 10, 10);
        db2.regionsearch(100, 100, 100, 100);
        db2.regionsearch(132432000, 324322000, 3242310,
                3243210);
        db2.regionsearch(100, 100, 10, 10);
        db2.regionsearch(-1000, -2000, 10, 10);
        db2.regionsearch(-1000, -2000, -10, -10);
        assertTrue(systemOut().getHistory().contains(
                "Rectangle Intersecting region (-1, -2, 10, 10):\n"));
    }

    /**
     * Tests for search method
     */
    public void testSearch() {
        db2.search("r15");
        assertTrue(systemOut().getHistory()
                .contains("Rectangle not found: r15"));
    }

    /**
     * tests intersection method with mock values
     */
    @Test
    public void testIntersections() {
        Rectangle rect = new Rectangle(1, 2, 3, 4);
        KVPair<String, Rectangle> kvPair =
                new KVPair<String, Rectangle>("r1", rect);
        db2.insert(kvPair);
        db2.insert(kvPair);
        db2.intersections();
        assertTrue(systemOut().getHistory().contains(
                "Rectangle inserted: (r1, 1, 2, 3, 4)\n"
                        + "Intersection pairs:\n"
                        + "(r1, 1, 2, 3, 4 | r1, 1, 2, 3, 5)\n"));
    }

}
