import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import student.TestCase;
/**
 * This class test command processor class
 * 
 * @author Nivishree
 * 
 * @version 2021-10-10
 */
public class PRQuadTest extends TestCase {

    /**
     * initializes command processor object to null
     */
    private CommandProcessor cmdProc = null;

    private static Database db2;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass()
            throws Exception {
        db2 = new Database();
    }

    /**
     * creates an object of type command processor
     */
    @Before
    public void setUp() throws Exception {
        cmdProc = new CommandProcessor();
    }

    /**
     * test Point2.java
     */
    @Test
    public void testPoint2() {
        String[] file = {"abc.txt"};
        Point2.main(file);

        assertFuzzyEquals("Invalid file", systemOut().getHistory());
        systemOut().clearHistory();

        String[] file1 = {"Data/P2test1.txt"};
        Point2.main(file1);

        systemOut().clearHistory();

    }


    public void testPRQuad1() {
        String commandDummy = "remove @@";
        cmdProc.processor(commandDummy.trim());
        String commandDummy1 = "remove 100 200";
        cmdProc.processor(commandDummy1.trim());
        systemOut().clearHistory();


        String command1 = "insert r1 -1 -20 3 ";
        cmdProc.processor(command1.trim());
        String command2 = "insert r2 7 -8 1 ";
        cmdProc.processor(command2.trim());
        String command3 = "insert r@4 7 -8 -100";
        cmdProc.processor(command3.trim());
        String command4 = "insert R_5 7 8 -1";
        cmdProc.processor(command4.trim());
        String command5 = "insert r2 4 5 21345";
        cmdProc.processor(command5.trim());
        String command6 = "insert 2ab 4 5 21345";
        cmdProc.processor(command6.trim());
        String command7 = "insert ab 4 -5 8 ";
        cmdProc.processor(command7.trim());
        String command8 = "insert 4 5 0";
        cmdProc.processor(command8.trim());
        String command9 = "insert d 4 5 -5";
        cmdProc.processor(command9.trim());

        String command10 = "search p1";
        cmdProc.processor(command10.trim());

        String command10a = "regionsearch 0 0 1024 1024";
        cmdProc.processor(command10a.trim());
        String command10b = "remove p100";
        cmdProc.processor(command10b.trim());


        assertFuzzyEquals("Point Rejected: (r1, -1, -20)\r\n"
                + "Point Rejected: (r2, 7, -8)\r\n"
                + "Point Rejected: (r@4, 7, -8)\r\n"
                + "Point Inserted: (R_5, 7, 8)\r\n"
                + "Point Inserted: (r2, 4, 5)\r\n"
                + "Point Rejected: (2ab, 4, 5)\r\n"
                + "Point Rejected: (ab, 4, -5)\r\n"
                + "Point Rejected: (4, 5, 0)\r\n"
                + "Point Inserted: (d, 4, 5)\r\n"
                + "Point Not Found: p1\r\n"
                + "Region Search Result : \r\n"
                + "7, 8\r\n"
                + "4, 5\r\n"
                + "4, 5\r\n"
                + "Quad Nodes Visited: 1\r\n"
                + "Point Not found: (p100)\r\n", systemOut().getHistory());

        systemOut().clearHistory();

        String command11 = "dump";
        cmdProc.processor(command11.trim());
        String command12 = "hello";
        cmdProc.processor(command12.trim());
        String command13 = "remove 5a";
        cmdProc.processor(command13.trim());
        String command14 = "remove p1";
        cmdProc.processor(command14.trim());

        systemOut().clearHistory();

    }

    /**
     * runs tests for different skip list functions
     */
    @Test
    public void testPRQuad2() {

        String command11 = "insert r2 15 5 ";
        cmdProc.processor(command11.trim());
        String command12 = "insert r3 10 10";
        cmdProc.processor(command12.trim());
        String command13 = "insert r4 7 9";
        cmdProc.processor(command13.trim());
        String command14 = "insert r4 20 12";
        cmdProc.processor(command14.trim());
        String command15 = "insert r5 10 10";
        cmdProc.processor(command15.trim());

        String command15a = "duplicates";
        cmdProc.processor(command15a.trim());

        String command16 = "remove r3";
        cmdProc.processor(command16.trim());
        String command16a = "duplicates";
        cmdProc.processor(command16a.trim());
        String command17 = "remove 7 9";
        cmdProc.processor(command17.trim());
        String command18 = "search r4";
        cmdProc.processor(command18.trim());
        String command19 = "regionsearch 0 0 200 200";
        cmdProc.processor(command19.trim());

        assertFuzzyEquals("Point Inserted: (r2, 15, 5)\r\n"
                + "Point Inserted: (r3, 10, 10)\r\n"
                + "Point Inserted: (r4, 7, 9)\r\n"
                + "Point Inserted: (r4, 20, 12)\r\n"
                + "Point Inserted: (r5, 10, 10)\r\n"
                + "Duplicate Points: \r\n"
                + "(10, 10)\r\n"
                + "(4, 5)\r\n"
                + "Point (r3, 10, 10) Removed\r\n"
                + "Duplicate Points: \r\n"
                + "(4, 5)\r\n"
                + "Point (r4, 20, 12) Removed\r\n"
                + "Point Found: (r4, 7, 9)\r\n"
                + "Region Search Result : \r\n"
                + "15, 5\r\n"
                + "10, 10\r\n"
                + "20, 12\r\n"
                + "Quad Nodes Visited: 1", systemOut().getHistory());

        systemOut().clearHistory();
    }

    /**
     * test hashmap class
     */
    @Test
    public void testHashMap() {
        PRQuadNodeHashMap.getQuadNodeMap();
        PRQuadNodeHashMap.insertQuadNodeMap("sampleTest", 1, 3);
        PRQuadNodeHashMap.insertQuadNodeMap("sampleTest2", 1, 3);
        CoordinatePoints point = new CoordinatePoints(1, 3);
        KVPair<String, CoordinatePoints> kvPair =
                new KVPair<String, CoordinatePoints>("sampleTest", point);
        PRQuadNodeHashMap.deleteQuadNodeMap(kvPair);
        System.out.println(PRQuadNodeHashMap.getQuadNodeMap());
        assertTrue(systemOut().getHistory().contains(
                "1, 3=[sampleTest2]"
                ));
        systemOut().clearHistory();
    }

    /**
     * Tests insert method
     */
    @Test
    public  void testInsert() {
        db2 = new Database();
        CoordinatePoints point = new CoordinatePoints(200, 200);
        KVPair<String, CoordinatePoints> kvPair =
                new KVPair<String, CoordinatePoints>("r1", point);
        db2.insert(kvPair);
        CoordinatePoints point2 = new CoordinatePoints(0, 0);
        KVPair<String, CoordinatePoints> kvPair2 =
                new KVPair<String, CoordinatePoints>("r1", point2);
        CoordinatePoints point3 =
                new CoordinatePoints(1029, 1029);
        KVPair<String, CoordinatePoints> kvPair3 =
                new KVPair<String, CoordinatePoints>("r1", point3);
        CoordinatePoints point4 =
                new CoordinatePoints(100, 100);
        KVPair<String, CoordinatePoints> kvPair4 =
                new KVPair<String, CoordinatePoints>("r1", point4);
        KVPair<String, CoordinatePoints> kvPair5 =
                new KVPair<String, CoordinatePoints>("78r1##",
                        point4);
        KVPair<String, CoordinatePoints> kvPair6 =
                new KVPair<String, CoordinatePoints>("@", point4);
        db2.insert(kvPair3);
        db2.insert(kvPair4);
        db2.insert(kvPair2);
        db2.insert(kvPair5);
        db2.insert(kvPair6);
        assertTrue(systemOut().getHistory().contains(
                "Point Inserted: (r1, 200, 200)\n"
                        + "Point Rejected: (r1, 1029, 1029)\n"
                        + "Point Inserted: (r1, 100, 100)\n"
                        + "Point Inserted: (r1, 0, 0)\n"
                        + "Point Rejected: (78r1##, 100, 100)\n"
                        + "Point Rejected: (@, 100, 100)\n"
                ));
        systemOut().clearHistory();
    }

    /**
     * Tests the remove method with mock values
     */
    @Test
    public void testRemove() {
        db2 = new Database();
        CoordinatePoints point = new CoordinatePoints(1, 2);
        KVPair<String, CoordinatePoints> kvPair =
                new KVPair<String, CoordinatePoints>("r1", point);
        db2.insert(kvPair);
        db2.dump();
        assertTrue(systemOut().getHistory()
                .contains("Quad Tree Size:1 Quad Tree Nodes Printed\n"));
        db2.remove("r1");
        assertTrue(systemOut().getHistory()
                .contains("Point (r1, 1, 2) Removed\n"));
        db2.dump();
        assertTrue(systemOut().getHistory()
                .contains("Node at 0, 0, 1024: Empty\n"));
        systemOut().clearHistory();
    }


    /**
     * Tests for search method
     */
    public void testSearch() {
        db2.search("r15");
        assertTrue(systemOut().getHistory()
                .contains("Point Not Found: r15"));
        CoordinatePoints point = new CoordinatePoints(1, 2);
        KVPair<String, CoordinatePoints> kvPair =
                new KVPair<String, CoordinatePoints>("r1", point);
        db2.insert(kvPair);
        db2.search("r1");
        db2.search("r10");
        db2.search("@r1");
        assertTrue(systemOut().getHistory()
                .contains("Point Found: (r1, 1, 2)\n"));
        systemOut().clearHistory();
    }

    /**
     * tests duplicates method with mock values
     */
    @Test
    public void testDuplicates() {
        CoordinatePoints point = new CoordinatePoints(1, 2);
        KVPair<String, CoordinatePoints> kvPair =
                new KVPair<String, CoordinatePoints>("r1", point);
        KVPair<String, CoordinatePoints> kvPair2 =
                new KVPair<String, CoordinatePoints>("r2", point);
        db2.insert(kvPair);
        db2.insert(kvPair2);
        db2.duplicates();
        assertFuzzyEquals("Point Rejected: (r1, 1, 2)\r\n"
                + "Point Inserted: (r2, 1, 2)\r\n"
                + "Duplicate Points: \r\n"
                + "(200, 200)\r\n"
                + "(4, 5)\r\n"
                + "(1, 2)", systemOut().getHistory());
        systemOut().clearHistory();

    }
    /**
     * test regionsearch method
     */
    @Test
    public void testRegionSearch() {
        db2.regionsearch(100, 100, 0, 0);
        assertTrue(systemOut().getHistory().contains(
                "Rectangle rejected: (100, 100, 0, 0)\n"));
        CoordinatePoints point = new CoordinatePoints(1, 2);
        KVPair<String, CoordinatePoints> kvPair =
                new KVPair<String, CoordinatePoints>("r1", point);
        db2.insert(kvPair);
        db2.regionsearch(0, 0, 20, 20);
        assertTrue(systemOut().getHistory().contains(
                "Region Search Result : \n"
                        + "1, 2\n"
                        + "1, 2\n"    
                        + "Quad Nodes Visited: 1\n"));
        systemOut().clearHistory();
    }
//    /**
//     * test remove by coordiantes method
//     */
//    @Test
//    public void testByRemoveByCoordiantes() {
//        CoordinatePoints point = new CoordinatePoints(1, 2);
//        KVPair<String, CoordinatePoints> kvPair =
//                new KVPair<String, CoordinatePoints>("r1", point);
//        db2.insert(kvPair);
//        KVPair<String, CoordinatePoints> pair = 
//                db2.removeByCoordinates(1, 2);
//        KVPair<String, CoordinatePoints> pair2 = 
//                db2.removeByCoordinates(2000, 2000);
//        KVPair<String, CoordinatePoints> pair3 = 
//                db2.removeByCoordinates(0, 2000);
//        assertTrue(systemOut().getHistory().contains(
//                "(r1, 1, 2)\n"));
//        systemOut().clearHistory();
//    }
//    /**
//     * test remove by name method
//     */
//    @Test
//    public void testByRemoveByName() {
//        CoordinatePoints point = new CoordinatePoints(1, 2);
//        KVPair<String, CoordinatePoints> kvPair =
//                new KVPair<String, CoordinatePoints>("r1", point);
//        db2.insert(kvPair);
//        KVPair<String, CoordinatePoints> pair = db2.removeByName("r1");
//        KVPair<String, CoordinatePoints> pair2 = db2.removeByName("r5");
//        assertTrue(systemOut().getHistory().contains(
//                "(r1, 1, 2)\n"));
//        systemOut().clearHistory();
//
//    }

    /**
     * test PRQuad Internal methods
     */
    @Test
    public void testPRQuadInternal() {

        String command1 = "insert r21 150 500 ";
        cmdProc.processor(command1.trim());
        String command2 = "insert r31 1000 10";
        cmdProc.processor(command2.trim());
        String command3 = "insert r41 700 9";
        cmdProc.processor(command3.trim());
        String command4 = "insert r42 200 1203";
        cmdProc.processor(command4.trim());
        String command5 = "insert r51 1000 1021";
        cmdProc.processor(command5.trim());
        String command5a = "insert r51 1 1021";
        cmdProc.processor(command5a.trim());
        String command5b = "insert r51 7 1021";
        cmdProc.processor(command5b.trim());
        String command5c = "insert r51 9 1021";
        cmdProc.processor(command5c.trim());

        String command6 = "regionsearch 5 5 1023 1023";
        cmdProc.processor(command6.trim());

        String command7 = "remove r31";
        cmdProc.processor(command7.trim());
        String command8 = "duplicates";
        cmdProc.processor(command8.trim());
        String command9 = "remove 700 9";
        cmdProc.processor(command9.trim());
        String command10 = "search r51";
        cmdProc.processor(command10.trim());
        String command11 = "regionsearch 10 10 2000 2000";
        cmdProc.processor(command11.trim());
        assertFuzzyEquals("Point Inserted: (r21, 150, 500)\r\n"
                + "Point Inserted: (r31, 1000, 10)\r\n"
                + "Point Inserted: (r41, 700, 9)\r\n"
                + "Point Rejected: (r42, 200, 1203)\r\n"
                + "Point Inserted: (r51, 1000, 1021)\r\n"
                + "Point Inserted: (r51, 1, 1021)\r\n"
                + "Point Inserted: (r51, 7, 1021)\r\n"
                + "Point Inserted: (r51, 9, 1021)\r\n"
                + "Region Search Result : \r\n"
                + "150, 500\r\n"
                + "7, 1021\r\n"
                + "9, 1021\r\n"
                + "1000, 10\r\n"
                + "700, 9\r\n"
                + "1000, 1021\r\n"
                + "Quad Nodes Visited: 5\r\n"
                + "Point (r31, 1000, 10) Removed\r\n"
                + "Duplicate Points: \r\n"
                + "(200, 200)\r\n"
                + "(4, 5)\r\n"
                + "(1, 2)\r\n"
                + "Point (r41, 700, 9) Removed\r\n"
                + "Point Found: (r51, 9, 1021)\r\n"
                + "(r51, 7, 1021)\r\n"
                + "(r51, 1, 1021)\r\n"
                + "(r51, 1000, 1021)\r\n"
                + "Region Search Result : \r\n"
                + "150, 500\r\n"
                + "1000, 1021\r\n"
                + "Quad Nodes Visited: 5\r\n"
                + "", systemOut().getHistory());

        systemOut().clearHistory();
    }


}

