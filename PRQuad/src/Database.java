import java.util.ArrayList;
import java.util.HashMap;
//import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class is responsible for interfacing between the
 * command processor and the two data structure. The responsibility
 * of this class is to further interpret variations of
 * commands and do some error checking of those commands.
 * This class further interpreting the command means that
 * it will navigate to appropriate command in datastructure.
 * It will maintain synchronization between SkipList and PRQuad tree Datastructure 
 * 
 * @author Nivishree
 * 
 * @version 2021-10-10
 */
public class Database {

    private SkipList<String, CoordinatePoints> list;
    private PRQuadTree<KVPair<String, CoordinatePoints>> quadTree;

    private static final int WORLD_BOX =1024;
    /**
     * The constructor for this class initializes a
     * SkipList and PRQuad tree object with String and Points as its
     * parameters.
     */
    public Database() {
        list = new SkipList<String, CoordinatePoints>();
        quadTree = new PRQuadTree<KVPair<String, CoordinatePoints>>(WORLD_BOX);

    }

    /**
     * Insert the given coordinate points if it
     * passes the validation
     * 
     * @param pair with contains name and
     * Coordinate points
     */
    public void insert(KVPair<String, CoordinatePoints> pair) {
        int x = pair.getValue().getX();
        int y = pair.getValue().getY();
        String s = pair.getKey().toString();
        String regex = "^[0-9A-Za-z\\s_]+$";
        if (s.matches(regex) && (Character.isLetter(
                s.charAt(0)) && (x >= 0 && x < 1024)
                && (y >= 0 && y <= 1024))){
            boolean isInserted = quadTree.insert(pair);
            if(isInserted) {
                list.insert(pair);
                System.out.println("Point Inserted: " + pair);
            }
            else {
                System.out.println("Point Rejected: "+ pair);
            }

        }
        else {
            System.out.println("Point Rejected: "+ pair);
        }
    }

    /**
     * Removes a spatial points with the specified coordinates
     * if available. If not an error message is printed to
     * the console.
     * 
     * @param x x-coordinate of the point to be removed
     * @param y x-coordinate of the point to be removed
     */

    public void duplicates() {
        HashMap<String, Set<String>> quadNodeMap = 
                PRQuadNodeHashMap.getQuadNodeMap();
        System.out.println("Duplicate Points: ");
        for(Map.Entry<String, Set<String>> element : quadNodeMap.entrySet()) {
            if (element.getValue().size() > 1) {
                System.out.println("("+element.getKey()+")");

            }
        }
    }

    /**
     * dump method prints all the available nodes in skiplist and quad tree
     * it also prints the level in skiplist and node in quad tree
     */
    public void dump() {
        if (list.size() == 0) {
            System.out.println("SkipList Dump: ");
            System.out.printf(
                    "level: 1 Value: null\n");
            System.out.printf("The SkipList's Size is: %d\n", 0);
            System.out.println("QuadTree Dump: ");
            System.out.println("Node at 0, 0, 1024: Empty");
            System.out.println("QuadTree Size: 1 QuadTree Nodes Printed.");

        }
        else {
            list.dump();
            System.out.println("QuadTree Dump: ");
            int sum = quadTree.dump(0, 0, 1024, 0
                    /* no.of spaces for indentation */); 
            System.out.println("Quad Tree Size:" +
                    sum+" Quad Tree Nodes Printed");

        }    }

    /**
     * Prints out all the points with the specified
     * name in the SkipList and QuadTree. This method will delegate the
     * searching to the SkipList and QuadTree class completely.
     * 
     * @param name name of the Rectangle to be searched for
     */

    public void search(String name) {
        ArrayList<
        KVPair<String, CoordinatePoints>> points =
        new ArrayList<KVPair<String,
        CoordinatePoints>>();
        points = list.search(name);
        Iterator<KVPair<String, CoordinatePoints>> arItr2 =
                points.iterator();
        if (arItr2.hasNext()) {
            System.out.print("Point Found: ");
            while (arItr2.hasNext()) {
                System.out.println(arItr2.next());
            }
        }
        else {
            System.out.printf("Point Not Found: %s\n",
                    name);
        }
    }

    /**
     * Searches for the points in the given rectangle 
     * coordinates
     * 
     * @param x x-coordinate of the point to be removed
     * @param y x-coordinate of the point to be removed
     * @param w width of query rectangle
     * @param h height of query rectangle
     */
    public void regionsearch(int x, int y, int w, int h) {
        if (w <= 0 | h <= 0) {
            System.out.println(
                    "Rectangle rejected: (" + x + ", " + y
                    + ", " + w + ", " + h + ")");
            return;

        }else {
            System.out.println("Region Search Result : ");
            List<CoordinatePoints> pointList = 
                    new ArrayList<CoordinatePoints>();
            int visited = quadTree.region_search(x, y, w, h, pointList);
            System.out.println("Quad Nodes Visited: "+ visited);

        } 
    }


    /**
     * Removes a spatial points with the specified coordinates
     * if available. If not an error message is printed to
     * the console.
     * 
     * @param x x-coordinate of the point to be removed
     * @param y x-coordinate of the point to be removed
     */
    public KVPair<String, CoordinatePoints> removeByCoordinates( int x, int y )
    {
        if ((x < 0) || (x >(WORLD_BOX))) {
            System.out.println("Point Rejected: "+ "(" + x + ", " + y + ")");
            return null;
        }
        if ((y < 0) || (y > (WORLD_BOX))) {
            System.out.println("Point Rejected: "+ "(" + x + ", " + y + ")");
            return null;
        }
        KVPair<String, CoordinatePoints> points = quadTree.remove(x, y);
        return points;
    }

    /**
     * Removes a Coordinates with the name "name" if
     * available. If not an error message is printed to the
     * console.
     * 
     * @param name the name of the rectangle to be removed
     */
    public KVPair<String, CoordinatePoints>  removeByName(String name) {
        KVPair<String, CoordinatePoints> pair =
                list.removeByName(name);
        if (pair != null) {
            return pair;
        }
        else {
            return null;
        }
    }


    /**
     * Removes a Coordinates with the name "name" if
     * available. If not an error message is printed to the
     * console.
     * 
     * @param name the name of the rectangle to be removed
     */
    public void remove(String name) {
        String regex = "^[0-9A-Za-z\\s_]+$";
        if (name.matches(regex)) {
            KVPair<String, CoordinatePoints> pair = removeByName(name);
            if (pair != null) {
                removeByCoordinates(pair.getValue().getX(), 
                        pair.getValue().getY());
                PRQuadNodeHashMap.deleteQuadNodeMap(pair);
                System.out.println("Point " + pair + " Removed");
            }
            else {
                System.out.printf("Point Not found: (%s)\n",
                        name);
            }
        }
        else {
            System.out.printf("Point Rejected: (%s)\n",
                    name);
        }
    }
    /**
     * Removes a spatial points with the specified coordinates
     * if available. If not an error message is printed to
     * the console.
     * 
     * @param x x-coordinate of the point to be removed
     * @param y x-coordinate of the point to be removed
     */
    public void remove(int x, int y) {
        KVPair<String, CoordinatePoints> pair = removeByCoordinates(x, y);
        if (pair != null) {
            KVPair<String, CoordinatePoints> pair2 = 
                    removeByName(pair.getKey());
            if(pair2!=null) {
                PRQuadNodeHashMap.deleteQuadNodeMap(pair2);
                System.out.println("Point " + pair2 + " Removed");
            }
            else {
                System.out.println("Point Not found: ");
            }
        }
        else {
            System.out.println("Point Not found: \n");
        }
    }

}
