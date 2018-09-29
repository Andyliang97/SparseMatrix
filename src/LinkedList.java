/**
 * THIS IS A GENERIC LINKED LIST CLASS
 * @author Junjie Liang
 * @version November 2018
 * @param <T> a comparable object
 */
public class LinkedList<T extends Comparable<T>> {


    private Node<T> head = null;
    private int size = 0;



    /**
     * INSERT AT THE HEAD OF THE LINKED LIST
     * @param value value that needs to be inserted to the linkedList
     */
    public void insertAtHead(T value) {
        Node<T> newNode = new Node<T>(value);
        if (head == null) {
            head = newNode;
        } 
        else {
            newNode.setnext(head);
            head = newNode;
        }
        size++;
    }


    /**
     * INSERT A NODE TO THE END OF THE LINKED LIST
     * @param value that needs to be inserted to the linked list
     */
    public void append(T value) {
        Node<T> newNode = new Node<T>(value);
        Node<T> temp = head;
        if (head == null) {
            head = newNode;
        } 
        else {
            while (temp.getnext() != null) {
                temp = temp.getnext();
            }
            temp.setnext(newNode);
        }
        size++;
    }


    /**
     * GET THE ITEM FROM THE GIVEN POSITION
     * @param pos given position
     * @return the position node's item
     */
    public T get(int pos) {
        int position = 0;
        Node<T> temp = head;
        if (position == pos) {
            return temp.getItem();
        }
        else if (head == null) {
            return null;
        }
        while (position != pos) {
            temp = temp.getnext();
            position++;
            if (temp == null) {
                return null;
            }
        }
        return temp.getItem();
    }

    /**
     * GET THE NEXT NODE OF THE GIVEN NODE
     * @param aNode given node
     * @return the next node
     */
    public Node<T> getNextNode(Node<T> aNode) {
        if (aNode == null) {
            return null;
        }
        return aNode.getnext();
    }
    
    /**
     * GET THE FIRST NODE OF THE LINKED LIST
     * @return the first node
     */
    public Node<T> getFirstNode() {
        return head;
    }

    /**
     * DELETE THE FIRST NODE FROM THE LINKED LIST
     * @return return the deleted node
     */
    public T removeFirst() {
        Node<T> temp = head;
        if (head == null) {
            return null;
        }
        else {
            head = head.getnext();
            size--;
            return temp.getItem();
        }
    }


    /**
     * 
     * DELETE THE LAST NODE FROM THE LINKED LIST
     * @return the delete node 
     */
    public T removeLast() {
        Node<T> temp = head;
        if (head == null) {
            return null;
        } 
        else if (temp.getnext() == null) {
            head = null;
            size--;
            return temp.getItem();
        } 
        else {
            while (temp.getnext().getnext() != null) {
                temp = temp.getnext();
            }
            T returnItem = temp.getnext().getItem();
            temp.setnext(null);
            size--;
            return returnItem;
        }
    }


    /**
     * GET THE ITEM IN THE FIRST NODE
     * @return the item in first node
     */
    public T getFirst() {
        if (head == null) {
            return null;
        }
        Node<T> temp = head;
        return temp.getItem();
    }


    /**
     * GET THE ITEM IN THE LAST NODE
     * @return the last node's value
     */
    public T getLast() {
        Node<T> temp = head;
        if (head == null) {
            return null;
        } 
        else {
            while (temp.getnext() != null) {
                temp = temp.getnext();
            }
            return temp.getItem();
        }
    }
    


    /**
     * GET THE POSITION OF A NODE IN THE LINKED LIST
     * BASED ON THE GIVEN ITEM
     * @param item pass in a value that needs to be searching for
     * @return return the position if the data is found
     */

    public int getPos(T item) {
        Node<T> temp = head;
        int pos = 0;
        while (temp != null) {
            if (temp.getItem() == item) {
                break;
            }
            temp = temp.getnext();
            pos++;
        }
        if (temp == null) {
            return -1;
        } 
        else {
            return pos;
        }
    }

    /**
     * REMOVE A NODE FROM THE LINKED LIST BASED ON THE GIVEN ITEM
     * @param data pass in a value that needs to be searching for
     * @return return -1 if the node is not found
     * otherwise the node is removed successfully
     */
    public T removeByValue(T data) {
        Node<T> temp = head;
        Node<T> deleteNode = new Node<T>();
        if (head == null) {
            return null;
        } 
        else if (head.getItem() == data) {
            head = head.getnext();
            size--;
            return temp.getItem();
        } 
        else {
            while (temp != null) {
                if (temp.getnext() != null && 
                        temp.getnext().getItem() == data) {
                    break;
                }
                temp = temp.getnext();
            }
            if (temp == null) {
                return null;
            }
            else {
                size--;
                deleteNode = temp.getnext();
                temp.setnext(deleteNode.getnext());
                return deleteNode.getItem();
            }
        }


    }

    /**
     * DETERMINE IF THE LINKED LIST IS EMPTY
     * @return return true if the linkedList is empty
     * otherwise, return false
     */
    public boolean isEmpty() {
        return head == null;
    }


    /**
     * PRINT OUT ALL THE ITEM FROM THE LINKED LIST
     */
    public void print() {
        Node<T> tempNode = head;
        System.out.print(getFirst());
        tempNode = tempNode.getnext();
        while (tempNode != null) {
            System.out.print(" " + tempNode.getItem());
            tempNode = tempNode.getnext();
        }
        System.out.println("");
    }

    /**
     * GET THE NUMBER OF ITEM IN THE LINKED LIST
     * THIS METHOD WILL TRAVERSE THE WHOLE LINKED LIST
     * TO FIND OUT THE SIZE
     * @return the size of the linked list
     */
    public int getSize() {
        int count = 0;
        Node<T> temp = head;
        while (temp != null) {
            temp = temp.getnext();
            count++;
        }
        return count;
    }

    /**
     * GET THE NUMBER OF ITEM IN THE LINKED LIST
     * THIS METHOD WILL DIRECTED RETURN THE VARIABLE SIZE
     * @return the size of the linked list
     */
    public int testSize() {
        return size;
    }
    
    /**
     * CLEAR OUT THE WHOLE LINKED LIST
     * @return the head of the linked list
     */
    public Node<T> clear() {
        head = null;
        size = 0;
        return head;
        
    }
    
    /**
     * METHOD THAT CAN INSERT ITEM IN THE SMALLEST POSITION
     * BASED ON THE COMPARETO METHOD
     * @param item that you need to insert to the list
     */
    public void insertAtSmallest(T item) {
        Node<T> newNode = new Node<T>(item);
        Node<T> temp = head;
        if (head == null) {
            head = newNode;
        } 
        else if (item.compareTo(temp.getItem()) < 0) {
            newNode.setnext(temp);
            head = newNode;
        }
        else {
            while (temp.getnext() != null) {
                if (item.compareTo(temp.getnext().getItem()) < 0) {
                    break;
                }
                temp = temp.getnext();
                
            }
            newNode.setnext(temp.getnext());
            temp.setnext(newNode);
        }
        size++;
    }
}