import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class implements SkipList data structure and
 * contains an inner SkipNode class which the SkipList will
 * make an array of to store data.
 * 
 * @author Nivishree
 * 
 * @version 2021-09-22
 * 
 * @param <K> Key
 * @param <V> Value
 */
public class SkipList<K extends Comparable<? super K>, V> {
    private SkipNode head; // First element of the top
                           // level
    private int size; // number of entries in the Skip List
    private int level;

    /**
     * Initializes the fields head, size and level
     */
    public SkipList() {
        head = new SkipNode(null, 0);
        size = 0;
        level = -1;
    }

    /**
     * Returns a random level number which is used as the
     * depth of the SkipNode
     * 
     * @return a random level number
     */
    int randomLevel() {
        int lev;
        Random value = new Random();
        for (lev = 0; Math.abs(value.nextInt()) % 2 == 0;
                lev++) {
            // Do nothing
        }
        return lev; // returns a random level
    }

    /**
     * Searches for the KVPair using the key which is a
     * Comparable object.
     * 
     * @param key key to be searched for
     * @return returns ArrayList of found rectangles
     */

    public ArrayList<KVPair<K, V>> search(K key) {
        SkipNode x = head; // Dummy header node

        ArrayList<KVPair<K, V>> foundRectangle =
                new ArrayList<KVPair<K, V>>();
        for (int i = level; i >= 0; i--) { // For each
                                           // level...
            while ((x.forward[i] != null)
                    && (x.forward[i].element().getKey()
                            .compareTo(key) < 0)) {
                x = x.forward[i];
            }

        }
        x = x.forward[0];
        while (x != null) {
            if ((x.element().getKey()
                    .compareTo(key) == 0)) {
                foundRectangle.add(x.element());

            }
            x = x.forward[0];
        }
        return foundRectangle;
    }

    /**
     * @return the size of the SkipList
     */
    public int size() {
        return size;
    }

    /**
     * Inserts the KVPair in the SkipList at its
     * appropriate spot as designated by its
     * lexicoragraphical order.
     * 
     * @param it the KVPair to be inserted
     */
    @SuppressWarnings("unchecked")
    public void insert(KVPair<K, V> it) {
        int newLevel = randomLevel();
        if (newLevel > level) { // If new node is deeper
            adjustHead(newLevel); // adjust the header
        }

        SkipNode[] update;
        update = (SkipNode[]) Array.newInstance(
                SkipList.SkipNode.class, level + 1);
        SkipNode x = head; // Start at header node
        for (int i = level; i >= 0; i--) { // Find insert
                                           // position
            while ((x.forward[i] != null)
                    && (x.forward[i].element().getKey()
                            .compareTo(it.getKey()) < 0)) {
                x = x.forward[i];
            }

            update[i] = x; // Track end at level i
        }
        x = new SkipNode(it, newLevel);
        for (int i = 0; i <= newLevel; i++) {
            x.forward[i] = update[i].forward[i];
            update[i].forward[i] = x; // Who points to x
        }
        size++; // Increment dictionary size
    }

    /**
     * Increases the number of levels in head so that no
     * element has more indices than the head.
     * 
     * @param newLevel the number of levels to be added to
     *                 head
     */
    @SuppressWarnings("unchecked")
    private void adjustHead(int newLevel) {
        SkipNode temp = head;
        head = new SkipNode(null, newLevel);
        for (int i = 0; i <= level; i++) {
            head.forward[i] = temp.forward[i];

        }
        level = newLevel;
    }

    /**
     * Removes the KVPair that is passed in as a parameter
     * and returns true if the pair was valid and false if
     * not.
     * 
     * @param key the value of the KVPair to be removed
     * 
     * @return returns the removed pair if the pair was
     *         valid and null if not
     */

    @SuppressWarnings("unchecked")
    public KVPair<K, V> remove(K key) {
        SkipNode[] update;
        SkipNode current = head;
        SkipNode header = head;
        update = (SkipNode[]) Array.newInstance(
                SkipList.SkipNode.class, level + 1);
        try {
            for (int i = level; i >= 0; i--) { // Find
                                               // deletion
                                               // position
                while ((current.forward[i] != null)
                        && (current.forward[i].element()
                                .getKey()
                                .compareTo(key) < 0)) {
                    current = current.forward[i];
                }
                update[i] = current;
            }
            current = current.forward[0];

            if (current.element().getKey() != null
                    && current.element().getKey()
                            .compareTo(key) == 0) {
                for (int i = 0; i <= level; i++) {
                    if (update[i].forward[i] != current) {
                        break;
                    }
                    update[i].forward[i] =
                            current.forward[i];

                }

                while (level > 0
                        && header.forward[level] == null) {

                    level = level - 1;
                }
                size = size - 1;
                return current.pair;
            }
        }
        catch (NullPointerException ae) {
//            System.out.println(ae);
            return null;

        }
        /**
         * start from lowest level and rearrange references
         * just like we do in singly linked list to remove
         * target node
         */
        return null;
    }

    /**
     * Removes a KVPair with the specified value.
     * 
     * @param val the value of the KVPair to be removed
     * 
     * @return returns true if the removal was successful
     */

    @SuppressWarnings("unchecked")
    public KVPair<K, V> removeByValue(V val) {
        SkipNode[] update;
        SkipNode current = head;
        SkipNode header = head;
        try {

            for (int i = 0; i <= size; i++) {
                if ((current.forward[0] != null)) {
                    header = current;
                    current = current.forward[0];
                    if (current.element().getValue()
                            .toString()
                            .equals(val.toString())) {
                        header.forward[0] =
                                current.forward[0];
                        i = size + 1;
                        level = level - 1;
                        size = size - 1;
                        return current.element();

                    }

                }
            }
        }
        catch (

        NullPointerException ae) {
            return null;

        }
        return null;
    }

    /**
     * Returns elements in skiplist
     * 
     * @return returns array of elements in skiplist
     */
    public ArrayList<KVPair<K, V>> listElements() {
        ArrayList<KVPair<K, V>> skiplistElements =
                new ArrayList<KVPair<K, V>>();
        SkipList<K, V>.SkipNode node = head.forward[0];
        while (node != null) {
            skiplistElements.add(node.element());
            node = node.forward[0];
        }
        return skiplistElements;

    }

    /**
     * Prints out the SkipList in a human readable format
     * to the console.
     */
    public void dump() {
        System.out.println("SkipList dump: ");
        SkipNode x = head;
        System.out.printf(
                "Node has depth %d, Value (%s)\n", x.level,
                x.pair);
        while ((x != null) && (x.forward[0] != null)) {
            System.out.printf(
                    "Node has depth %d, Value %s\n",
                    x.forward[0].level,
                    x.forward[0].element().toString());
            x = x.forward[0];
        }
        System.out.printf("SkipList size is: %d\n", size);
    }

    /**
     * This class implements a SkipNode for the SkipList
     * data structure.
     * 
     * @author Nivishree
     * 
     * @version 2016-01-30
     */
    private class SkipNode {

        // the KVPair to hold
        private KVPair<K, V> pair;
        // what is this
        private SkipNode[] forward;
        // the number of levels
        private int level;

        /**
         * Initializes the fields with the required KVPair
         * and the number of levels from the random level
         * method in the SkipList.
         * 
         * @param tempPair the KVPair to be inserted
         * @param level    the number of levels that the
         *                 SkipNode should have
         */
        @SuppressWarnings("unchecked")
        public SkipNode(KVPair<K, V> tempPair, int level) {
            pair = tempPair;
            forward = (SkipNode[]) Array.newInstance(
                    SkipList.SkipNode.class, level + 1);
            this.level = level;
        }

        /**
         * Returns the KVPair stored in the SkipList.
         * 
         * @return the KVPair
         */
        public KVPair<K, V> element() {
            return pair;
        }

    }

}
