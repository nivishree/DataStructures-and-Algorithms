import java.util.ArrayList;
import java.util.List;

/**
 * This class implements SkipList data structure and
 * contains an inner SkipNode class which the SkipList will
 * make an array of to store data.
 * 
 * @author Nivishree
 * @author 
 * 
 * @version 2021-10-10
 * 
 * @param <K> Key
 * @param <V> Value
 */

public class PRQuadInternal<T> extends PRQuadNode<T> {

    private PRQuadNode<T>[] childNode;

    // Enumerate the childNoderen
    public static final int NW = 0;
    public static final int NE = 1;
    public static final int SW = 2;
    public static final int SE = 3;

    @SuppressWarnings("unchecked")
    public PRQuadInternal() {
        childNode = new PRQuadNode[4];
        childNode[NW] = PRQuadFlyWeight.getFlyWeight();
        childNode[NE] = PRQuadFlyWeight.getFlyWeight();
        childNode[SW] = PRQuadFlyWeight.getFlyWeight();
        childNode[SE] = PRQuadFlyWeight.getFlyWeight();
    }

    @Override
    public PRQuadNode<T> insert(
            KVPair<String, CoordinatePoints> pair,
            int upLeftX, int upLeftY, int width) {
        int quadrant;
        int qx;
        int qy;
        ArrayList<Integer> quadrantData = findQuadrant(pair, upLeftX, upLeftY, width);

        quadrant = quadrantData.get(0);
        qx= quadrantData.get(1);
        qy = quadrantData.get(2);
        childNode[quadrant] = childNode[quadrant].insert(pair, upLeftX+qx, upLeftY+qy, width/2);  
        return this;
    }
    private ArrayList<Integer> findQuadrant(KVPair<String, CoordinatePoints> pair, int upperX, int upperY, int width)
    {
        int quad;
        int qx; // think of a name
        int qy;
        int xCoordinate = pair.getValue().getX();
        int yCoordinate = pair.getValue().getY();
        if (xCoordinate < (upperX + (width/2))) {
            qx = 0;
            if (yCoordinate < (upperY + (width/2))) {
                qy = 0;
                quad = NW;
            } else {
                qy = (width/2);
                quad = SW;
            }
        } else {
            qx = (width/2);
            if (xCoordinate < (upperY + (width/2))) {
                qy = 0;
                quad = NE;
            } else {
                qy = (width/2);
                quad = SE;
            }
        }

        ArrayList<Integer> quadrantData = new ArrayList<Integer>();
        quadrantData.add(quad);
        quadrantData.add(qx);
        quadrantData.add(qy);
        return quadrantData;
    }
    /**
     * Get a list of the points contained in the children of this internal node.
     * @return A list of the points in the children of this list.
     */
    public ArrayList<KVPair<String, CoordinatePoints>> getPoints()
    {
        ArrayList<KVPair<String, CoordinatePoints>> list = new ArrayList<KVPair<String, CoordinatePoints>>();
        for (int i = 0; i < 4; i++) {
            list.addAll(childNode[i].getPoints());
        }
        return list;
    }
    /**
     * The size of an internal node is the sum of the child nodes.
     * @return the combined sum of the child nodes.
     */
    public int size()
    {
        int width = 0;
        for (int i = 0; i < 4; i++) { width += childNode[i].size(); }
        return width;
    }

    public PRQuadNode<T> remove(CoordinatePoints point,
            int upLeftX, int upLeftY, int width, T[] data)
    {
        int quadrant;
        int qx;
        int qy;
        int x = point.getX();
        int y = point.getY();
        List<Integer> q_data = decompose(x, y, upLeftX, upLeftY, width);
        quadrant = q_data.get(0);
        qx= q_data.get(1);
        qy = q_data.get(2);
        CoordinatePoints points = new CoordinatePoints(x,y);

        childNode[quadrant] = childNode[quadrant].remove(points, upLeftX + qx, upLeftY + qy, width/2, data);

        if (size() < 4) {
            PRQuadNode<T> leaf = new PRQuadLeaf<T>();
            for( KVPair<String, CoordinatePoints> point1 : getPoints()) {
                leaf.insert(point1, upLeftX, upLeftY, width);
            }
            return leaf;
        } else {
            return this;
        }

    }

    private ArrayList<Integer> decompose(int x, int y, int upperX, int upperY, int width)
    {
        int quad;
        int qx;
        int qy;
        int xCoordinate = x;
        int yCoordinate = y;
        if (xCoordinate < (upperX + (width/2))) {
            qx = 0;
            if (yCoordinate < (upperY + (width/2))) {
                qy = 0;
                quad = NW;
            } else {
                qy = (width/2);
                quad = SW;
            }
        } else {
            qx = (width/2);
            if (xCoordinate < (upperY + (width/2))) {
                qy = 0;
                quad = NE;
            } else {
                qy = (width/2);
                quad = SE;
            }
        }

        ArrayList<Integer> quadrantData = new ArrayList<Integer>();
        quadrantData.add(quad);
        quadrantData.add(qx);
        quadrantData.add(qy);
        return quadrantData;
    }


    @Override
    public int dump(int x, int y, int width, int indentation) {
        System.out.print(new String(new char[indentation]).replace("\0", " "));
        System.out.println("Node at " + x + ", " + y + ", " +  width + ": Internal" );
        int sum =1;
        sum += childNode[0].dump(x, y, width/2, indentation+2);
        sum += childNode[1].dump(x+width/2, y, width/2, indentation+2);
        sum += childNode[2].dump(x, y+width/2, width/2, indentation+2);
        sum += childNode[3].dump(x+width/2, y+width/2, width/2, indentation+2);
        return sum;

    }


    public boolean intersects( int upperX, int upperY, int width, int x, int y, int w, int h) {
        
        return !(x+ w <= upperX || y + h <= upperY || x >= upperX + width || y >= upperY + width);
    }

    @Override
    public int region_search(int x, int y, int w, int h,
            List<CoordinatePoints> list, int upperX, int upperY,
            int size) {
        int sum = 1;
        if (intersects(upperX, upperY, size/2, x, y, w, h)) {

            sum += childNode[NW].region_search(x, y, w, h, list, upperX, upperY, size/2);
        }
        if (intersects(upperX + size/2, upperY, size/2, x, y, w, h)) {
            sum += childNode[NE].region_search(x, y, w, h, list, upperX + size/2, upperY, size/2);
        }
        if (intersects(upperX, upperY + size/2, size/2, x, y, w, h)) {
            sum += childNode[SW].region_search(x, y, w, h, list, upperX, upperY + size/2, size/2);
        }
        if (intersects(upperX + size/2, upperY + size/2, size/2, x, y, w, h)) {
            sum += childNode[SE].region_search(x, y, w, h, list, upperX + size/2, upperY + size/2, size/2);
        }
        return sum;
    }



}
