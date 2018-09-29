/**
 * SPARSE MATRIX CLASS. THIS WILL STORE THE 
 * RATING FOR MOVIES AND REVIEWERS.
 * @author Junjie Liang
 * @version November 2018
 */

public class SparseMatrix {
    private LinkedList<SMNode> rowList;
    private LinkedList<SMNode> colList;
    private int nextRowNum = 0;
    private int nextColNum = 0;
    
    
    /**
     * CONSTRUCTOR
     * it builds two linked lists
     */
    public SparseMatrix() {
        rowList = new LinkedList<SMNode>();
        colList = new LinkedList<SMNode>();
        
    }
    
    /**
     * INSERT METHOD FOR SPARSE MATRIX. IT TAKE
     * TWO SMNodes AND A RATING AS A ITEM AS 
     * PARAMETER.
     * 1. there are 4 kinds of situation: 
     * rowPointer=null & colPointer=null: both not exist
     * rowPointer!=null & colPointer=null: movie exists, reviewer not exist
     * rowPointer=null & colPointer!=null: reviewer exists, movie not exist
     * rowPointer!=null & colPointer!=null: both exist
     * 2. for the first situation, it will first insert nodes to each list
     * and connect the inserted nodes with the newNode that stores rating
     * 3. for the second and third, it will only insert to one list,
     * and connect with the new node that stores rating
     * 4. for the fourth, it will check if the newNode that stores the ratings
     * exist. If not, create a newNode and make connection. If exist, simply
     * update the ratings
     * @param rowPointer pointer stores in the movie list
     * @param colPointer pointer stores in the reviewer list
     * @param item ratings
     * @return return an array stores both pointers
     */
    public SMNode[] insert(SMNode rowPointer, SMNode colPointer, int item) {
        SMNode[] returnItem = new SMNode[2];  //used to return headers
        if (rowPointer == null) {
            if (colPointer == null) {
                returnItem = insertRowCol(rowPointer, colPointer, item);
            }
            else {
                returnItem = insertRow(colPointer, item);
            }
        }
        else {
            if (colPointer == null) {
                returnItem = insertCol(rowPointer, item);
            }
            else {
                returnItem = insertExistRowCol(rowPointer, colPointer, item);
            }
        }
        //returnItem[0] is the header store in rowList, 
        //[1] is the header store in colList
        return returnItem; 
    }
    
    /**
     * IF ROW AND COL ALREADY EXIST, USE THIS INSERT METHOD
     * @param rowPointer given rowPointer
     * @param colPointer given colPointer
     * @param item rating
     * @return the pointers pointing to the newNode
     */
    public SMNode[] insertExistRowCol(SMNode rowPointer
            , SMNode colPointer, int item) {
        SMNode[] returnItem = new SMNode[2];  //used to return headers
        SMNode gotNode = findNode(rowPointer, colPointer);
        if (gotNode == null) {
            SMNode temp = new SMNode();
            SMNode newNode = new SMNode(rowPointer.getRow(),
                    colPointer.getCol(), item);
            temp = rowPointer;
            while (temp.getnextCol() != null) {
                if (temp.getnextCol().getCol() > colPointer.getCol()) { 
                    break;
                }
                temp = temp.getnextCol();
            }
            newNode.setnextCol(temp.getnextCol());
            if (temp.getnextCol() != null) {
                temp.getnextCol().setpreCol(newNode);
            }
            temp.setnextCol(newNode);
            newNode.setpreCol(temp);
            temp = colPointer;
            while (temp.getnextRow() != null) {
                if (temp.getnextRow().getRow() > rowPointer.getRow()) { 
                    break;
                }
                temp = temp.getnextRow();
            }
            newNode.setnextRow(temp.getnextRow());
            if (temp.getnextRow() != null) {
                temp.getnextRow().setpreRow(newNode);
            }
            temp.setnextRow(newNode);
            newNode.setpreRow(temp);
        }
        else {
            gotNode.setItem(item);
        }
        returnItem[0] = rowPointer;
        returnItem[1] = colPointer;
        return returnItem;
    }
    
    /**
     * IF ROW AND COLUMN DONT EXIST, INSERT A NEW ROW AND NEW COLUMN
     * BEFORE ADDING NEW NODE
     * @param rowPointer given row pointer
     * @param colPointer given column pointer
     * @param item rating
     * @return the pointers pointing to the newNode
     */
    public SMNode[] insertRowCol(SMNode rowPointer
            , SMNode colPointer, int item) {
        SMNode[] returnItem = new SMNode[2];  //used to return headers
        //build a new node to insert
        SMNode newNode = new SMNode(nextRowNum, nextColNum, item);    
        
        //build a new header(store in a node) for rowList
        //will store the corresponding hash number.
        SMNode newrowNode = new SMNode(nextRowNum, -1 , 0);    
        //setnextCol: because it is in next column position on the same row
        newrowNode.setnextCol(newNode); 
        newNode.setpreCol(newrowNode);
        rowList.append(newrowNode);
        //build a new header(store in a node) for colList
        //will store the corresponding hash number.
        SMNode newcolNode = new SMNode(-1, nextColNum , 0);    
        newcolNode.setnextRow(newNode);
        newNode.setpreRow(newcolNode);
        colList.append(newcolNode);
        returnItem[0] = newrowNode;
        returnItem[1] = newcolNode;
        nextRowNum++;
        nextColNum++;
        return returnItem;
    }
    
    /**
     * THE ROW EXISTS, ADD A NEW COLUMN BEFORE ADDING NEW NODE
     * @param rowPointer given row pointer
     * @param item rating
     * @return the pointers pointing to the newNode
     */
    public SMNode[] insertCol(SMNode rowPointer, int item) {
        SMNode[] returnItem = new SMNode[2];  //used to return headers
        //get the last position in rowList
        int row = rowPointer.getRow(); 
        SMNode newNode = new SMNode(row, nextColNum, item);   
        //build a new header(store in a node) for rowList
        SMNode newcolNode = new SMNode(-1, nextColNum, 0);
        //setnextCol: because it is in next column position on the same row
        newcolNode.setnextRow(newNode); 
        newNode.setpreRow(newcolNode);
        colList.append(newcolNode);
        
        SMNode colLastNode = getLastNode(rowPointer, "col");
        colLastNode.setnextCol(newNode);
        newNode.setpreCol(colLastNode);
        
        returnItem[0] = null;
        returnItem[1] = newcolNode;
        nextColNum++;
        return returnItem;
    }
    
    /**
     * COLUMN EXISTS, A NEW ROW BEFORE ADDING A NEW NODE
     * @param colPointer given column pointer
     * @param item rating
     * @return the pointers pointing to the newNode
     */
    public SMNode[] insertRow(SMNode colPointer, int item) {
        SMNode[] returnItem = new SMNode[2];  //used to return headers
        int col = colPointer.getCol();    //the exist column
        SMNode newNode = new SMNode(nextRowNum, col, item);  
        
        //build a new header(store in a node) for rowList
        SMNode newrowNode = new SMNode(nextRowNum, -1, 0);
        //setnextCol: because it is in next column position on the same row
        newrowNode.setnextCol(newNode); 
        newNode.setpreCol(newrowNode);
        rowList.append(newrowNode);
        
        SMNode rowLastNode = getLastNode(colPointer, "row");
        rowLastNode.setnextRow(newNode);
        newNode.setpreRow(rowLastNode);
        
        returnItem[0] = newrowNode;
        returnItem[1] = null;
        nextRowNum++;
        return returnItem;
    }
    
    /**
     * METHOD THAT CAN GET THE LAST NODE OF CERTAIN MOVIE
     * OR REVIEWER
     * 1. if the movie/reviewer has no rating, return null
     * 2. if it has something in it, traverse to 
     * the end and return the last node
     * @param head the head pointer 
     * @param rowORcol choose either movie or reviewer
     * @return return the last SMNode node
     */
    public SMNode getLastNode(SMNode head, String rowORcol) {
        SMNode temp = head;
        if (head == null) {
            return null;
        }
        else {
            if (rowORcol.equals("row")) {
                //it will traverse the whole list from up to down
                while (temp.getnextRow() != null) {
                    temp = temp.getnextRow();
                }
                return temp;
            }
            else if (rowORcol.equals("col")) { 
                //it will traverse the whole list from left to right
                while (temp.getnextCol() != null) {
                    temp = temp.getnextCol();
                }
                return temp;
            }
            else {
                return null;
            }
        }
    }
    
    /**
     * METHOD THAT CAN FIND THE SPECIFIC NODE THAT STORES THE RATINGS
     * WITH THE GIVEN POSITION
     * @param rowPointer pointer stores in the movie list
     * @param colPointer pointer stores in the reviewer list
     * @return return a found node 
     */
    public SMNode findNode(SMNode rowPointer, SMNode colPointer) { 
        if (rowPointer == null || colPointer == null) {
            //throw new IllegalArgumentException("Wrong input arguments");
            return null;
        }
        SMNode temp = rowPointer;
        int colPos = colPointer.getCol();
        while (temp != null) {
            if (temp.getCol() == colPos) {
                break;
            }
            temp = temp.getnextCol();
        }
        return temp;
        
    }
    
    /**
     * METHOD THAT CAN CHECK IF A SINGLE ROW IS EMPTY WITH THE GIVEN HEAD
     * @param rowPointer pointer stores in the movie list
     * @return true if it is empty, otherwise false
     */
    public boolean rowIsEmpty(SMNode rowPointer) {
        if (rowPointer == null) {
            return false;
            //throw new IllegalArgumentException("argument invalid");
        }
        else {
            return rowPointer.getnextCol() == null;
        }
    }
    
    /**
     * METHOD THAT CAN CHECK IF A SINGLE COLUMN IS EMPTY WITH THE GIVEN HEAD
     * @param colPointer pointer stores in the reviewer list
     * @return true if it is empty, otherwise false
     */
    public boolean colIsEmpty(SMNode colPointer) {
        if (colPointer == null) {
            return false;
            //throw new IllegalArgumentException("argument invalid");
        }
        else {
            return colPointer.getnextRow() == null;
        }
    }
    
    /**
     * METHOD THAT CAN REMOVE A WHOLE ROW WITH THE GIVEN HEAD
     * @param rowPointer head 
     * @return the head that just removed
     */
    public SMNode removeRowByValue(SMNode rowPointer) {
        if (rowList.isEmpty()) {
            return null;
        }
        else {
            if (rowPointer != null) {
                //int prerowNum = rowPointer.getRow() - 1;
                SMNode temp = rowPointer.getnextCol();
                while (temp != null) {
                    //if (prerowNum == temp.preRow.getRow()) {
                    temp.getpreRow().setnextRow(temp.getnextRow());
                    if (temp.getnextRow() != null) {
                        temp.getnextRow().setpreRow(temp.getpreRow());
                    }
                    temp = temp.getnextCol();
                }
            }
            return rowList.removeByValue(rowPointer);
        }
    }
    
    /**
     * METHOD THAT CAN REMOVE A WHOLE COLUMN WITH THE GIVEN HEAD
     * @param colPointer head
     * @return the head that just removed
     */
    public SMNode removeColByValue(SMNode colPointer) {
        if (colList.isEmpty()) {
            return null;
        }
        else {
            if (colPointer != null) {
                //int precolNum = colPointer.getCol() - 1;
                SMNode temp = colPointer.getnextRow();
                while (temp != null) {
                    temp.getpreCol().setnextCol(temp.getnextCol());
                    if (temp.getnextCol() != null) {
                        temp.getnextCol().setpreCol(temp.getpreCol());
                    }
                    temp = temp.getnextRow();
                }        
            }
            return colList.removeByValue(colPointer);
        }
    }
    
    /**
     * METHOD THAT CAN CLEAR UP THE WHOLE SPARSE MATRIX
     */
    public void clear() {
        rowList.clear();
        colList.clear();
    }
    
    /**
     * METHOD THAT CAN PRINT OUT EVERYTHING IN THE SPARSE MATRIX
     * IT WILL PRINT OUT THE DATA ROW BY ROW
     */
    public void print() {
        if (rowList.isEmpty() || colList.isEmpty()) {
            System.out.println("The sparse matrix is empty");
            System.out.println("");
            return;
        }
        
        int rowLast = rowList.getLast().getRow();
        int colLast = colList.getLast().getCol();
        int k = 0;
        SMNode temp = new SMNode();
        
        System.out.print("  ");
        for (int i = 0; i <= colLast; i++) {
            System.out.print(i + " ");
        }
        System.out.println("");
        
        for (int i = 0; i <= rowLast; i++) {
            temp = rowList.get(k).getnextCol();
            System.out.print(i + " ");
            if (temp == null) {
                for (int n = 0; n <= colLast; n++) {
                    System.out.print(0 + " ");
                }
                k++;
            }
            else if (i != temp.getRow()) {
                for (int n = 0; n <= colLast; n++) {
                    System.out.print(0 + " ");
                }
            }
            else {
                for (int j = 0; j <= colLast; j++) {
                    if (temp == null) {
                        System.out.print(0 + " ");
                    }
                    else {
                        if (j == temp.getCol()) {
                            System.out.print(temp.getItem() + " ");
                            temp = temp.getnextCol();
                        }
                        else {
                            System.out.print(0 + " ");
                        }
                    }
                }
                k++;
            }
            System.out.println("");
            
        }
        System.out.println("");
    }
    
    /**
     * METHOD USED FOR LISTING EVERYTHING IN A SINGLE ROW
     * @param pointer the head of the list needed to print
     * @param list a sort list that has keys in it. It comes from the hash table
     */
    public void listrow(SMNode pointer, LinkedList<Slot> list) {
        if (rowIsEmpty(pointer)) {
            return;
        }
        else {
            pointer = pointer.getnextCol();
            while (pointer != null) {
                while (list.getFirst().getNode().getCol() != pointer.getCol()) {
                    list.removeFirst();
                }
                System.out.print(list.getFirst().getKey() + ": ");
                System.out.println(pointer.getItem());
                pointer = pointer.getnextCol();
                list.removeFirst();
            }
        }
    }
    
    /**
     * METHOD USED FOR LISTING EVERYTHING IN A SINGLE COLUMN
     * @param pointer the head of the list needed to print
     * @param list a sort list that has keys in it. It comes from the hash table
     */
    public void listcol(SMNode pointer, LinkedList<Slot> list) {
        if (colIsEmpty(pointer)) {
            return;
        }
        else {
            pointer = pointer.getnextRow();
            while (pointer != null) {
                while (list.getFirst().getNode().getRow() != pointer.getRow()) {
                    list.removeFirst();
                }
                System.out.print(list.getFirst().getKey() + ": ");
                System.out.println(pointer.getItem());
                pointer = pointer.getnextRow();
                list.removeFirst();
            }
        }
    }
    
    /**
     * METHOD USED FOR FINDING THE SIMILAR MOVIE
     * @param desiredCol the movie that used to compare
     * @param list the movie name list
     * @return a class called keyrate that store key and score
     */
    public KeyRate similarCol(SMNode desiredCol, LinkedList<Slot> list) {
        SMNode desiredTemp = new SMNode();
        SMNode compareTemp1 = list.getFirst().getNode();
        SMNode compareTemp2 = new SMNode();
        KeyRate returnItem = new KeyRate("", 10);
        double score = 0;
        int count = 0;
        if (!colIsEmpty(desiredCol)) {
            desiredTemp = desiredCol.getnextRow();
        }
        while (!list.isEmpty()) {
            desiredTemp = desiredCol.getnextRow();
            count = 0;
            score = 0;
            compareTemp1 = list.getFirst().getNode();
            if (compareTemp1 != desiredCol) {
                compareTemp2 = compareTemp1.getnextRow();
                while (compareTemp2 != null && desiredTemp != null) {
                    if (compareTemp2.getRow() == desiredTemp.getRow()) {
                        score = score + Math.abs(compareTemp2.
                                getItem() - desiredTemp.getItem());
                        count++;
                        compareTemp2 = compareTemp2.getnextRow();
                        desiredTemp = desiredTemp.getnextRow();
                    }
                    else if (compareTemp2.getRow() < desiredTemp.getRow()) {
                        compareTemp2 = compareTemp2.getnextRow();
                    }
                    else {
                        desiredTemp = desiredTemp.getnextRow();
                    }
                }
                if (count != 0) {
                    score = score / count;
                    if (score < returnItem.getScore()) {
                        returnItem = new KeyRate(list.getFirst().
                                getKey(), score);
                    }
                }
            }
            list.removeFirst();
        }
        return returnItem;
        
    }
    
    /**
     * METHOD USED FOR FINDING THE SIMILAR REVIEWER
     * @param desiredRow the reviewer that used to compare
     * @param list the reviewer name list
     * @return a class called keyrate that store key and score
     */
    public KeyRate similarRow(SMNode desiredRow, LinkedList<Slot> list) {
        SMNode desiredTemp = new SMNode();
        SMNode compareTemp1 = list.getFirst().getNode();
        SMNode compareTemp2 = new SMNode();
        KeyRate returnItem = new KeyRate("", 10);
        double score = 0;
        int count = 0;
        if (!rowIsEmpty(desiredRow)) {
            desiredTemp = desiredRow.getnextCol();
        }
        while (!list.isEmpty()) {
            desiredTemp = desiredRow.getnextCol();
            count = 0;
            score = 0;
            compareTemp1 = list.getFirst().getNode();
            if (compareTemp1 != desiredRow) {
                compareTemp2 = compareTemp1.getnextCol();
                while (compareTemp2 != null && desiredTemp != null) {
                    if (compareTemp2.getCol() == desiredTemp.getCol()) {
                        score = score + Math.abs(compareTemp2.
                                getItem() - desiredTemp.getItem());
                        count++;
                        compareTemp2 = compareTemp2.getnextCol();
                        desiredTemp = desiredTemp.getnextCol();
                    }
                    else if (compareTemp2.getCol() < desiredTemp.getCol()) { 
                        compareTemp2 = compareTemp2.getnextCol();
                    }
                    else {
                        desiredTemp = desiredTemp.getnextCol();
                    }
                }
                if (count != 0) {
                    score = score / count;
                    if (score < returnItem.getScore()) {
                        returnItem = new KeyRate(list.
                                getFirst().getKey(), score);
                    }
                }
            }
            list.removeFirst();
        }
        return returnItem;
        
    }
    
    /**
     * METHOD USED FOR PRINTING RATING
     * @param list a sorted list that has key in it
     */
    public void printRating(LinkedList<Slot> list) {
        Slot tempSlot = new Slot();
        SMNode tempNode = new SMNode();
        
        tempSlot = list.getFirst();
        
        tempNode = tempSlot.getNode();
        while (!list.isEmpty()) {
            tempSlot = list.getFirst();
            tempNode = tempSlot.getNode();
            if (!colIsEmpty(tempNode)) {
                System.out.print(tempSlot.getKey() + ": ");
                tempNode = tempNode.getnextRow();
                while (tempNode != null) {
                    System.out.print(tempNode.getRow() + ":");
                    System.out.print(tempNode.getItem() + " ");
                    tempNode = tempNode.getnextRow();
                }
                System.out.println("");
            }
            else {
                System.out.println(tempSlot.getKey() + ": ");
            }
            list.removeFirst();
        }
    }
    
    /**
     * check if the row list or the column list is empty
     * @return true if one of the list is empty
     */
    public boolean ratingisEmpty() {
        return (rowList.isEmpty() || colList.isEmpty());
    }
    
}
