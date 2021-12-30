
/**
 * This class declares coordinate points with x and y coordinate
 * 
 * @author Nivishree
 * 
 * @version 2021-10-10
 * 
 */
public class CoordinatePoints {
    private int x;
    private int y;

    /**
     * Initializes the Coordinate values of spatial point
     * 
     * @param x xcoordinates
     * @param y ycoordinates
     */

    public CoordinatePoints(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * String method to return coordinate values
     */
    public String toString() {
        String stringValue = this.getX() + ", "
                + this.getY() ;

        return stringValue;
    }
}
