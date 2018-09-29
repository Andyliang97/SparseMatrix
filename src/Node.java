
/**
 * NODE CLASS FOR LINKED LIST
 * @author Junjie Liang
 * @version November 2018
 * @param <T> generic item
 */
public class Node<T extends Comparable<T>> {
    private T item;
    private Node<T> next = null;

    /**
     * DEFAULT CONSTRUCTOR
     */
    Node() {
        //nothing  
    }

   /**
    * CONSTRUCTOR 
    * @param item set a item into the node
    */
    Node(T item) {
        this.item = item;
    }
        
   /**
    * CONSTRUCTOR
    * @param item set item into the node
    * @param nextnode set next into the node
    */
    Node(T item, Node<T> nextnode) {
        this.item = item;
        this.next = nextnode;
    }
        
    /**
     * SET ITEM METHOD FOR THE NODE
     * @param item need to be put into node
     */
    public void setItem(T item) {
        this.item = item;
    }
        
    /**
     * GET ITEM METHOD FOR THE NODE
     * @return item in the node
     */
    public T getItem() {
        return item;
    }
        
    /**
     * SET NEXT NODE METHOD FOR THE NODE
     * @param nextnode node need to be put into node
     */
    public void setnext(Node<T> nextnode) {
        this.next = nextnode;
    }
        
    /**
     * GET NEXT NODE METHOD FOR THE NODE
     * @return next node
     */
    public Node<T> getnext() {
        return this.next;
    }
}
