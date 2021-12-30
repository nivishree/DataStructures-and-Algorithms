import java.util.ArrayList;
import java.util.List;

public class PRQuadLeaf<T> extends PRQuadNode<T> {

    public static final int MAX_NODES = 3;
    private ArrayList<KVPair<String, CoordinatePoints>> leafPoints ;

    public PRQuadLeaf() {
        leafPoints = new ArrayList<KVPair<String, CoordinatePoints>>();
    }



    @Override
    public PRQuadNode<T> insert(
            KVPair<String, CoordinatePoints> pair,
            int upLeftX, int upLeftY, int width) {
       
        if(!leafPoints.isEmpty()) {

            for(KVPair<String, CoordinatePoints> nodePoints: leafPoints) {
                if((nodePoints.getValue().getX() == pair.getValue().getX()) && 
                        (nodePoints.getValue().getY() == pair.getValue().getY()) && 
                        (nodePoints.getKey().compareTo(pair.getKey()) == 0)) {
                    return null;
                }

            }
        }

        if (leafPoints.size() == MAX_NODES) {

            PRQuadNode<T> internalNode = new PRQuadInternal<T>();

            for (KVPair<String, CoordinatePoints> nodePoints: leafPoints) {

                internalNode = internalNode.insert(nodePoints, upLeftX, upLeftY, width);}
            internalNode = internalNode.insert(pair, upLeftX, upLeftY, width);
            PRQuadNodeHashMap.insertQuadNodeMap(pair.getKey(), pair.getValue().getX(), pair.getValue().getY());
            return internalNode;


        } else {
            leafPoints.add(pair);
            PRQuadNodeHashMap.insertQuadNodeMap(pair.getKey(), pair.getValue().getX(), pair.getValue().getY());
            return this;
        }


    }   
    /**
     * The size of a leaf node is the number of points it contains.
     * @return the number of points in this leaf.
     */
    public int size() {
        return leafPoints.size();
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public PRQuadNode<T> remove(CoordinatePoints point,
            int upLeftX, int upLeftY, int width, T[] data) {
        data[0] = null;
        for (int i = 0; i < leafPoints.size(); i++) {
            KVPair<String, CoordinatePoints> pair = leafPoints.get(i);
            if ((pair.getValue().getX() == point.getX()) && (pair.getValue().getY() == point.getY())) {
                leafPoints.remove(i);
                data[0] = (T) pair.getKey();
                break;
            }
        }

        if (leafPoints.isEmpty()) {
            return PRQuadFlyWeight.getFlyWeight();
        } else {
            return this;
        }
    }

    @Override
    public ArrayList<KVPair<String, CoordinatePoints>> getPoints() {

        return (ArrayList<KVPair<String, CoordinatePoints>>) leafPoints;
    }
    @Override
    public int dump(int x, int y, int width, int indentation) {
        System.out.print(new String(new char[indentation]).replace("\0", " "));
        System.out.println("Node at " + x + ", " + y + ", " +  width + ":" );   
        for (KVPair<String, CoordinatePoints> point : leafPoints) {
            System.out.print(new String(new char[indentation]).replace("\0", " "));
            System.out.println(point);
        }
        return 1;
    }


    @Override
    public int region_search(int x, int y, int w, int h, List<CoordinatePoints> list, int ul_x, int ul_y, int size) {

        for (KVPair<String, CoordinatePoints> point : leafPoints) {
            int x1 = point.getValue().getX();
            int y1 = point.getValue().getX();
            if ((x1>=x && x1<=x+w) && (y1>=y && y<=y+h)) {
                System.out.println(point.getValue());
                list.add(point.getValue());
            }
        }
        return 1;
    }


}
