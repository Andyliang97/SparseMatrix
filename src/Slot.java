/**
 * Slot is the spot in hash table
 * @author Junjie Liang
 * @version 2018 Aug
 *
 */
public class Slot implements Comparable<Slot> {
    private String key;
    private Boolean tombstone;
    private SMNode pointer;
    
    /**
     * COPY CONSTRUCTOR
     * @param that the Slot needed to copy from
     */
    public Slot(Slot that) {
        this(that.key, that.pointer, that.tombstone);
    }
    
    /**
     * CONSTRUCTOR
     * @param keyvalue the key
     * @param returnNode the node from sparse matrix
     * @param tomb tombstone value
     */
    public Slot(String keyvalue,  SMNode returnNode, Boolean tomb) {
        key = keyvalue;
        pointer = returnNode;
        tombstone = tomb;
    }

    /**
     * DEFAULT CONSTRUCTOR
     */
    public Slot() {
        key = "";
        pointer = null;
        tombstone = false;
    }
    
    /**
     * constructor that allows you to enter keyvalue, data and handle
     * @param keyvalue key
     * @param returnNode the node needed to be inserted
     */
    public Slot(String keyvalue,  SMNode returnNode) {
        key = keyvalue;
        pointer = returnNode;
        tombstone = false;
    }
    
    /**
     * check if the slot is empty
     * @return true if it is empty, otherwise return false
     */
    public boolean isEmpty() {
        return ((getKey().equals("")) && (pointer == null));
    }
    
    /**
     * method used to set key value in the slot
     * @param keyvalue key
     */
    public void setKey(String keyvalue) {
        key = keyvalue;
    }

    /**
     * method used to get key value in the slot
     * @return the key value
     */
    public String getKey() {
        return key;
    }
    /**
     * method used to set handle
     * @param returnNode node from sparse matrix
     */
    public void setNode(SMNode returnNode) {
        pointer = returnNode;
    }
    
    /**
     * method used to get node
     * @return the SMNode 
     */
    public SMNode getNode() {
        return pointer;
    }
    
    /**
     * method used to set tombstone
     * 
     */
    public void setTombstone() {
        tombstone = true;
    }
    
    /**
     * method used to get tombstone
     * @return true if it is a tombstone
     */
    public boolean getTombstone() {
        return tombstone;
    }
    
    /**
     * method used to set slot to empty
     */
    public void setEmpty() {
        key = "";
        pointer = null;
    }
    
    /**
     * PRINT RATING METHOD FOR EACH SLOT
     * it prints the key value and the row number 
     * in SMNode
     */
    public void printrating() {
        if (!this.isEmpty()) {
            System.out.print(key + ": ");
            System.out.print(this.pointer.getRow());
            System.out.println("");
        }
    }
    
    /**
     * COMPARE METHOD FOR SLOT. IT TAKES OTHER SLOT
     * AND COMPARE WITH IT.
     * 1. if the pointers in both slots are null, return -100
     * 2. method can compare the row/column in the pointer
     * 3. it return -1, 1 or 0 based on the situation below
     * @param other the slot you need to compare to
     * @return comparison result
     */
    public int compareTo(Slot other) {
        if (this.pointer == null || other.pointer == null) {
            return -100;
        }
        else {
            if (this.pointer.getCol() != -1) {
                if (this.pointer.getCol() > other.pointer.getCol()) {
                    return 1;
                }
                else if (this.pointer.getCol() == other.pointer.getCol()) {
                    return 0;
                }
                else {
                    return -1;
                }
            }
            else  {
                if (this.pointer.getRow() > other.pointer.getRow()) {
                    return 1;
                }
                else if (this.pointer.getRow() == other.pointer.getRow()) {
                    return 0;
                }
                else {
                    return -1;
                }
            }
        }
    }
}
