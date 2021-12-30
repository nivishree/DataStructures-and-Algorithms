import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class PRQuadNodeHashMap {

    private static HashMap<String, Set<String>> quadNodeMap = new HashMap<>();

    public static <T> HashMap<String, Set<String>> getQuadNodeMap() {
        return (HashMap<String, Set<String>>) quadNodeMap;
    }

    /**
     * Insert method to insert values in hashmap
     * @param pointName
     * @param x
     * @param y
     */

    public static void insertQuadNodeMap(String pointName, int x, int y) {
        CoordinatePoints points = new CoordinatePoints(x, y);
        if (quadNodeMap.containsKey(points.toString()))   // note ref/value
        {
            Set<String> coordinateNames =  quadNodeMap.get(points.toString());
            coordinateNames.add(pointName);
        }

        else {
            Set<String> pointNames = new HashSet<String>();
            pointNames.add(pointName);
            quadNodeMap.put(points.toString(), pointNames);

        }
    }
    /**
     * Delete method to delete the given pair from hashmap
     * @param pair
     */
    public static void deleteQuadNodeMap(KVPair<String, CoordinatePoints> pair) 
    {
        CoordinatePoints points = new CoordinatePoints(
                pair.getValue().getX(), pair.getValue().getY());
        if (quadNodeMap.containsKey(points.toString()))   // note ref/value
        {
            Set<String> coordinateNames =  quadNodeMap.get(points.toString());
            while (coordinateNames.contains(pair.getKey())) {
                coordinateNames.remove(pair.getKey());
            }
        }

    }
}

