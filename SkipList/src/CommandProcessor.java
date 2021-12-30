/**
 * The purpose of this class is to parse a text file into
 * its appropriate, line by line commands for the format
 * specified in the project spec.
 * 
 * @author Nivishree
 * 
 * @version 2021-09-22
 */
public class CommandProcessor {

    private Database data;

    /**
     * The constructor for the command processor requires a
     * database instance to exist, so the only constructor
     * takes a database class object to feed commands to.
     */
    public CommandProcessor() {
        data = new Database();
    }

    /**
     * This method identifies keywords in the line and
     * calls methods in the database as required. Each line
     * command will be specified by one of the keywords to
     * perform the actions within the database required.
     * These actions are performed on specified objects and
     * include insert, remove, regionsearch, search,
     * intersections, and dump. If the command in the file
     * line is not one of these, an appropriate message
     * will be written in the console. This processor
     * method is called for each line in the file. Note
     * that the methods called will themselves write to the
     * console, this method does not, only calling methods
     * that do.
     * 
     * @param line a single line from the text file
     */

    public void processor(String line) {
        String[] arr = line.split("\\s+");
        String method = arr[0];
        switch (method) {
            case "insert":
                Rectangle rect = new Rectangle(
                        Integer.valueOf(arr[2]),
                        Integer.valueOf(arr[3]),
                        Integer.valueOf(arr[4]),
                        Integer.valueOf(arr[5]));
                KVPair<String, Rectangle> kvPair =
                        new KVPair<String, Rectangle>(
                                arr[1], rect);
                data.insert(kvPair);
                break;

            case "remove":
                if (arr.length - 1 < 2) {
                    data.remove(arr[1]);
                }
                else {
                    data.remove(Integer.valueOf(arr[1]),
                            Integer.valueOf(arr[2]),
                            Integer.valueOf(arr[3]),
                            Integer.valueOf(arr[4]));
                }
                break;

            case "regionsearch":
                data.regionsearch(Integer.valueOf(arr[1]),
                        Integer.valueOf(arr[2]),
                        Integer.valueOf(arr[3]),
                        Integer.valueOf(arr[4]));
                break;

            case "search":
                data.search(arr[1]);
                break;

            case "intersections":
                data.intersections();
                break;

            case "dump":
                data.dump();
                break;

            default:
                System.out
                        .println("Method does not exists");
        }

    }
}
