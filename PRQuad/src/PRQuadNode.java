import java.util.ArrayList;
import java.util.List;

public abstract class PRQuadNode<T> {

    public abstract PRQuadNode<T> insert(KVPair<String, CoordinatePoints> pair, 
            int upLeftX, int upLeftY, int width);
    public abstract PRQuadNode<T> remove(CoordinatePoints point, int upLeftX, 
            int upLeftY, int width, T[] data);
    public abstract int size();
    public abstract ArrayList<KVPair<String, CoordinatePoints>> 
    getPoints();
    public abstract int region_search(int x, int y, int w, int h,
            List<CoordinatePoints> list, int ul_x, int ul_y, int size);  
    public abstract int  dump(int x, int y, int width, int indentation);


}
