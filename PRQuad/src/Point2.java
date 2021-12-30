import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

/**
 * The class containing the main method, the entry point of
 * the application. It will take a command line file
 * argument which include the commands to be read and
 * creates the appropriate SkipList object and outputs the
 * correct results to the console as specified in the file.
 *
 * @author Nivishree
 * 
 * @version 2021-10-10
 */
public class Point2 {
    /**
     * The entry point of the application.
     *
     * @param args The name of the command file passed in
     *             as a command line argument.
     * 
     * @throws FileNotFoundException
     */

    public static void main(String[] args) {
        File file = null;
        try {
            file = new File(args[0]);
            Scanner scanner = new Scanner(file);

            CommandProcessor cmdProc =
                    new CommandProcessor();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.trim().isEmpty()) {
                    cmdProc.processor(line.trim());
                }
            }
            scanner.close();
        }

        catch (FileNotFoundException e) {
            System.out.println("Invalid file");
            e.printStackTrace();

        }

    }

}
