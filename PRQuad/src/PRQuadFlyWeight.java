import java.util.List;

public class PRQuadFlyWeight<T> extends PRQuadLeaf<T> {

    @SuppressWarnings("rawtypes")
    private static PRQuadFlyWeight instance = null;
    private PRQuadNode<T> leaf = new PRQuadLeaf<T>();

    private PRQuadFlyWeight() {

    }

    @SuppressWarnings("unchecked")
    public static <T> PRQuadFlyWeight<T> getFlyWeight() {
        if(instance == null) {
            return new PRQuadFlyWeight<T>();
        }
        return (PRQuadFlyWeight<T>)instance;

    }

    /**
     * Insertions to a Fly Weight return a newly instantiated leaf node.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param data The data to insert.
     * @param ul_x The x coordinate of the upper left point
     * @param ul_y The y coordinate of the upper left point
     * @param size The size of this node.
     * @return a new leaf node with the data inserted.
     */
    public PRQuadNode<T> insert(KVPair<String, CoordinatePoints> pair, int upperX, int upperY, int width)
    {

        return leaf.insert(pair, upperX, upperY, width);
    }


    /**
     * There are no points in the flyweight to find...
     */
    @Override
    public int region_search(int x, int y, int w, int h, List<CoordinatePoints> list, int ul_x, int ul_y, int width) {
        return 1;
    }

    /**
     * Trying to remove something from the fly weight always returns the fly
     * weight and sets the data returned to null.
     * @param x _
     * @param y _
     * @param data A pointer to a one sized array of T.
     * @return the flyweight
     */
    public PRQuadNode<T> remove(CoordinatePoints point, int x, int y, int width, T[] data)
    {
        return null;
    }

    public int dump(int x, int y, int width, int indentation) {
        System.out.print(new String(new char[indentation]).replace("\0", " "));
        System.out.println("Node at " + x + ", " + y + ", " +  width + ": Empty" );
        return 1;
    }
}