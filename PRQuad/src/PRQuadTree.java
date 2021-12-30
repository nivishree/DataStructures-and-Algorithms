import java.util.List;

public class PRQuadTree<T> {
    private int width;
    private PRQuadNode<T> flyWeight;
    /**
     * constructor for quadtree class
     * @param width
     */
    public PRQuadTree(int width) {
        this.width = width;
        this.flyWeight = PRQuadFlyWeight.getFlyWeight();
    }
    /**
     * insert method for inserting point in quadtree
     * @param pair kvpair which has point name and coordinates
     * @return boolean value
     */
    public boolean insert(
            KVPair<String, CoordinatePoints> pair) {
        PRQuadNode<T> tempFlyWeight = flyWeight.insert(pair,
                0, 0, width);
        if (tempFlyWeight == null) {
            return false;
        }

        flyWeight = tempFlyWeight;
        return true;

    }
    /**
     * Remove points from quad tree
     * @param x x coordinate for point
     * @param y y coordinate for point
     * @return KVPair of removed point
     */
    public KVPair<String, CoordinatePoints> remove(int x,
            int y) {
        @SuppressWarnings("unchecked")
        T[] data = (T[]) (new Object[1]);
        CoordinatePoints point = new CoordinatePoints(x, y);
        PRQuadNode<T> tempFlyWeight = flyWeight.remove(point, 0, 0, width,
                data);
        if (tempFlyWeight == null) {
            return null;
        }

        flyWeight = tempFlyWeight;
        KVPair<String, CoordinatePoints> kvPair = 
                new KVPair<String, CoordinatePoints>(
                data[0].toString(),
                new CoordinatePoints(x, y));
        return kvPair;
    }


    /**
     * Searches for points within the query rectangle
     * @param x x coordinate for rectangle
     * @param y x coordinate for rectangle
     * @param w width of rectangle
     * @param h height of rectangle
     * @param list list for storing found rectangles
     * @return
     */
    public int region_search(int x, int y, int w, int h,
            List<CoordinatePoints> list) {
        return flyWeight.region_search(x, y, w, h, list, 0,
                0, width);
    }

    /**
     * 
     * @param x x coordinate for point
     * @param y y coordinate of point
     * @param size width of quadrant
     * @param indentation space for dump
     * @return
     */

    public int dump(int x, int y, int size,
            int indentation) {

        return flyWeight.dump(x, y, width, indentation);
    }

}
