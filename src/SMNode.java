/**
 * THIS IS THE NODE CLASS FOR SPARSE MATRIX
 * @author Junjie Liang
 * @version November 2018
 */
public class SMNode implements Comparable<SMNode> {
    private int row;
    private int col;
    private SMNode nextRow;
    private SMNode nextCol;
    private SMNode preRow;
    private SMNode preCol;
    private int item;
    
    /**
     * DEFAULT CONSTRUCTOR
     */
    public SMNode() {
        //nothing
    }
    
    /**
     * CONSTRUCTOR. IT TAKE THE ROW NUMBER, COLUMN NUMBER,
     * AND THE POINTERS AROUND THE NODE AS PARAMTERS
     * @param row row number
     * @param col column number
     * @param nextRow the node in next row
     * @param nextCol the node in next column
     * @param preRow the node in previous row
     * @param preCol the node in previous column
     * @param item the rating
     */
    public SMNode(int row, int col, SMNode nextRow, 
            SMNode nextCol, SMNode preRow, SMNode preCol, int item ) {
        this.row = row;
        this.col = col;
        this.nextRow = nextRow;
        this.nextCol = nextCol;
        this.preRow = preRow;
        this.preCol = preCol;
        this.item = item;
    }
    
    /**
     * CONSTRUCTOR, IT TAKES THE ROW NUMBER, COLUMN NUMBER,
     * AND ITEM(RATING) AS PARAMTERS
     * @param row row number
     * @param col column number
     * @param item the rating
     */
    public SMNode(int row, int col, int item) {
        this.row = row;
        this.col = col;
        this.nextRow = null;
        this.nextCol = null;
        this.preRow = null;
        this.preCol = null;
        this.item = item;
    }
    
    /**
     * GET THE NODE IN NEXT ROW
     * @return sparse matrix node in the next row
     */
    public SMNode getnextRow() {
        return this.nextRow;
    }
    
    /**
     * GET THE NODE IN NEXT COLUMN
     * @return sparse matrix node in the next column
     */
    public SMNode getnextCol() {
        return this.nextCol;
    }
    
    /**
     * GET THE NODE IN THE PREVIOUS ROW
     * @return sparse matrix node in the previous row
     */
    public SMNode getpreRow() {
        return this.preRow;
    }
    
    /**
     * GET THE NODE IN THE PREVIOUS COLUMN
     * @return sparse matrix node in the previous column
     */
    public SMNode getpreCol() {
        return this.preCol;
    }
    
    /**
     * GET THE ROW NUMBER
     * @return row number of current node
     */
    public int getRow() {
        return row;
    }
    
    /**
     * GET THE COLUMN NUMBER
     * @return column number of current node
     */
    public int getCol() {
        return col;
    }
    
    /**
     * GET THE ITEM(RATING)
     * @return item value
     */
    public int getItem() {
        return item;
    }
    
    /**
     * SET THE ITEM FOR THE CURRENT NODE
     * @param item given rating
     */
    public void setItem(int item) {
        this.item = item;
    }
    
    /**
     * POINT TO THE NODE IN NEXT ROW
     * @param nextrow node that needs to be pointed to
     */
    public void setnextRow(SMNode nextrow) {
        this.nextRow = nextrow;
    }
    
    /**
     * POINT TO THE NODE IN NEXT COLUMN
     * @param nextcol node that needs to be pointed to
     */
    public void setnextCol(SMNode nextcol) {
        this.nextCol = nextcol;
    }
    
    /**
     * POINT TO THE NODE IN PREVIOUS ROW
     * @param prerow node that needs to be pointed to
     */
    public void setpreRow(SMNode prerow) {
        this.preRow = prerow;
    }
    
    /**
     * POINT TO THE NODE IN PREVIOUS COLUMN
     * @param precol node that needs to be pointed to
     */
    public void setpreCol(SMNode precol) {
        this.preCol = precol;
            
    }
    
    /**
     * COMPARISON METHOD. IT TAKES ANOTHER SMode AND COMPARE 
     * IT WITH THE CURRENT NODE
     * 1. this is only for node comparison in linked list
     * 2. it can be either row comparison or column comparison here
     * 3. if the current node is smaller than or equal to the given node 
     * return 1, otherwise return -1;
     * @param other number used to detect if it is smaller, greater or equal to 
     * the given node
     * @return the comparison result
     */
    // if its smaller than other, return positive
    public int compareTo(SMNode other) { 
        if (this.getCol() != -1) {
            if (this.col <= other.col) {
                return 1;
            }
            else {
                return -1;
            }
        }
        else  {
            if (this.row <= other.row) {
                return 1;
            }
            else {
                return -1;
            }
        }
    }
    
}
