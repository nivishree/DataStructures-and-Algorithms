
/**
 * This class overrides the toString method of awt
 * Rectangle class and returns parameters of int type
 * 
 * @author Nivishree
 * 
 * @version 2021-09-22
 * 
 */

public class Rectangle {

    private int x;
    private int y;
    private int width;
    private int height;

    /**
     * Initializes the rectangle values
     * 
     * @param x xcoordinates
     * @param y ycoordinates
     * @param w width
     * @param h height
     */
    public Rectangle(int x, int y, int w, int h) {
        this.setX(x);
        this.setY(y);
        setWidth(w);
        setHeight(h);
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
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Converts the rectangle values to string
     * 
     * @return returns rectangle values in string format
     */

    public String toString() {
        String stringValue = this.getX() + ", "
                + this.getY() + ", " + this.getWidth()
                + ", " + this.getHeight();

        return stringValue;
    }

}
