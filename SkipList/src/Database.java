import java.util.ArrayList;
import java.util.Iterator;

//import java.awt.Rectangle;

/**
 * This class is responsible for interfacing between the
 * command processor and the SkipList. The responsibility
 * of this class is to further interpret variations of
 * commands and do some error checking of those commands.
 * This class further interpreting the command means that
 * the two types of remove will be overloaded methods for
 * if we are removing by name or by coordinates. Many of
 * these methods will simply call the appropriate version
 * of the SkipList method after some preparation.
 * 
 * @author Nivishree
 * 
 * @version 2021-09-22
 */
public class Database {

    private SkipList<String, Rectangle> list;

    /**
     * The constructor for this class initializes a
     * SkipList object with String and Rectangle a its
     * parameters.
     */
    public Database() {
        list = new SkipList<String, Rectangle>();
    }

    /**
     * Inserts the KVPair in the SkipList if the rectangle
     * has valid coordinates and dimensions, that is that
     * the coordinates are non-negative and that the
     * rectangle object has some area (not 0, 0, 0, 0).
     * This insert will insert the KVPair specified into
     * the sorted SkipList appropriately
     * 
     * @param pair the KVPair to be inserted
     */
    public void insert(KVPair<String, Rectangle> pair) {
        int x = pair.getValue().getX();
        int y = pair.getValue().getY();
        int w = pair.getValue().getWidth();
        int h = pair.getValue().getHeight();
        String s = pair.getKey().toString();
        String regex = "^[0-9A-Za-z\\s_]+$";
        if (s.matches(regex) && (Character.isLetter(
                s.charAt(0)) && (x >= 0 && x < 1024)
                && (y >= 0 && y <= 1024)
                && (w > 0 && h > 0)
                && (x + w <= 1024 && y + h <= 1024))) {
            System.out.print("Rectangle inserted: ");
            list.insert(pair);
        }
        else {
            System.out.print("Rectangle rejected: ");
        }
        System.out.println(pair);
    }

    /**
     * Removes a rectangle with the name "name" if
     * available. If not an error message is printed to the
     * console.
     * 
     * @param name the name of the rectangle to be removed
     */
    public void remove(String name) {
        String regex = "^[0-9A-Za-z\\s_]+$";
        if (name.matches(regex)) {
            KVPair<String, Rectangle> pair =
                    list.remove(name);
            if (pair != null) {
                System.out.printf("Rectangle removed: ");
                System.out.println(pair);
            }
            else {
                System.out.printf(
                        "Rectangle not removed: (%s)\n",
                        name);
            }
        }
        else {
            System.out.printf("Rectangle rejected: (%s)\n",
                    name);
        }
    }

    /**
     * Removes a rectangle with the specified coordinates
     * if available. If not an error message is printed to
     * the console.
     * 
     * @param x x-coordinate of the rectangle to be removed
     * @param y x-coordinate of the rectangle to be removed
     * @param w width of the rectangle to be removed
     * @param h height of the rectangle to be removed
     */
    public void remove(int x, int y, int w, int h) {
        if ((x < 0 || x > 1024) || (y < 0 || y > 1024)
                || (w <= 0 || h <= 0)
                || (x + w > 1024 || y + h > 1024)) {

            System.out.printf(
                    "Rectangle rejected: (%d, %d, %d, %d)\n",
                    x, y, w, h);
        }
        else {
            Rectangle rect = new Rectangle(x, y, w, h);
            KVPair<String, Rectangle> pair =
                    list.removeByValue(rect);
            if (pair != null) {
                System.out.printf("Rectangle removed: ");
                System.out.println(pair);
            }
            else {
                System.out.printf(
                        "Rectangle not found: (%d, %d, %d, %d)\n",
                        x, y, w, h);
            }
        }
    }

    /**
     * Displays all the rectangles inside the specified
     * region. The rectangle must have some area inside the
     * area that is created by the region, meaning,
     * Rectangles that only touch a side or corner of the
     * region specified will not be said to be in the
     * region. You will need a SkipList Iterator for this
     * 
     * @param x x-Coordinate of the region
     * @param y y-Coordinate of the region
     * @param w width of the region
     * @param h height of the region
     * 
     * @return
     */

    public void regionsearch(int x, int y, int w, int h) {
        ArrayList<KVPair<String,
                Rectangle>> regionSearchRect =
                        new ArrayList<KVPair<String,
                                Rectangle>>();
        if (w <= 0 | h <= 0) {
            System.out.println(
                    "Rectangle rejected: (" + x + ", " + y
                            + ", " + w + ", " + h + ")");
            return;

        }
        else {
            System.out.println(
                    "Rectangle Intersecting region (" + x
                            + ", " + y + ", " + w + ", "
                            + h + "):");

            regionSearchRect = intersectRect(x, y, w, h);
            Iterator<KVPair<String, Rectangle>> arItr2 =
                    regionSearchRect.iterator();
            while (arItr2.hasNext()) {
                System.out
                        .println(arItr2.next().toString());
            }

        }
    }

    /**
     * 
     * @param x Xcoordinate
     * @param y Ycoordinate
     * @param w width
     * @param h height
     * @return return array list of interesecting
     *         rectangles
     */
    public ArrayList<KVPair<String, Rectangle>>
            intersectRect(int x, int y, int w, int h) {
        int r1x = x;
        int r1y = y;
        int r1width = w;
        int r1height = h;

        ArrayList<KVPair<String,
                Rectangle>> skiplistElements =
                        list.listElements();
        ArrayList<KVPair<String,
                Rectangle>> regionSearchRect =
                        new ArrayList<KVPair<String,
                                Rectangle>>();
        Iterator<KVPair<String, Rectangle>> arItr =
                skiplistElements.iterator();
        while (arItr.hasNext()) {
            KVPair<String, Rectangle> ele = arItr.next();
            int r2x = ele.getValue().getX();
            int r2y = ele.getValue().getY();
            int r2width = ele.getValue().getWidth();
            int r2height = ele.getValue().getHeight();
            if (r1x + r1width <= r2x
                    || r1y + r1height <= r2y
                    || r1x >= r2x + r2width
                    || r1y >= r2y + r2height) {
                continue;

            }
            else {
                regionSearchRect.add(ele);
            }
        }
        return regionSearchRect;
    }

    /**
     * Prints out all the rectangles that Intersect each
     * other by calling the SkipList method for
     * intersections. You will need to use two SkipList
     * Iterators for this
     */
    public void intersections() {
        System.out.println("Intersection pairs:");
        ArrayList<KVPair<String,
                Rectangle>> skiplistElements =
                        list.listElements();
        Iterator<KVPair<String, Rectangle>> arItr1 =
                skiplistElements.iterator();

        while (arItr1.hasNext()) {
            KVPair<String, Rectangle> ele1 = arItr1.next();
            ArrayList<KVPair<String,
                    Rectangle>> intersectingRect =
                            intersectRect(
                                    ele1.getValue().getX(),
                                    ele1.getValue().getY(),
                                    ele1.getValue()
                                            .getWidth(),
                                    ele1.getValue()
                                            .getHeight());

            Iterator<KVPair<String, Rectangle>> rectItr =
                    intersectingRect.iterator();
            while (rectItr.hasNext()) {
                KVPair<String, Rectangle> element =
                        rectItr.next();
                if (element != ele1) {
                    System.out.println("(" + ele1.getKey()
                            + ", " + ele1.getValue()
                            + " | " + element.getKey()
                            + ", " + element.getValue()
                            + ")");
                }
            }
        }
    }

    /**
     * Prints out all the rectangles with the specified
     * name in the SkipList. This method will delegate the
     * searching to the SkipList class completely.
     * 
     * @param name name of the Rectangle to be searched for
     */

    public void search(String name) {
        ArrayList<
                KVPair<String, Rectangle>> foundRectangle =
                        new ArrayList<KVPair<String,
                                Rectangle>>();
        foundRectangle = list.search(name);
        Iterator<KVPair<String, Rectangle>> arItr2 =
                foundRectangle.iterator();
        if (arItr2.hasNext()) {
            System.out.println("Rectangles found:");
            while (arItr2.hasNext()) {
                System.out.println(arItr2.next());
            }
        }
        else {
            System.out.printf("Rectangle not found: %s\n",
                    name);
        }
    }

    /**
     * Prints out a dump of the SkipList which includes
     * information about the size of the SkipList and shows
     * all of the contents of the SkipList. This will all
     * be delegated to the SkipList.
     */
    public void dump() {
        if (list.size() == 0) {
            System.out.println("SkipList dump: ");
            System.out.printf(
                    "Node has depth 1, Value (null)\n");
            System.out.printf("SkipList size is: %d\n", 0);
        }
        else {
            list.dump();
        }
    }
}
