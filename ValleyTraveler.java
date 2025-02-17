/**
 * ValleyTraveler class represents a magical map that can identify and modify
 * valley points in the landscape of Numerica.
 * 
 * @author Saloni Shah
 */
public class ValleyTraveler {

    private class Node {
        int data;
        Node next, prev;
        
        public Node(int data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    // Instance variables to manage the landscape and collected treasures.
    private Node head, tail, firstValley;
    private double totalTreasure;
    private long sumToFirstValley;
    private int elemsToFirstValley;
    private int[] landscape;

    /**
     * Constructor to initialize the magical map with the given landscape of Numerica.
     * 
     * @param landscape An array of distinct integers representing the landscape.
     */
    public ValleyTraveler(int[] landscape) {
        // Initialize the internal state based on the provided landscape.
        this.landscape = landscape;

        // initialize new landscape (DLL)
        for(int i = landscape.length - 1; i >= 0; i--) {
            Node newNode = new Node(landscape[i]);
            insertAtHead(newNode);
        }

        elemsToFirstValley = 0;
        sumToFirstValley = 0;

        // finds the first valley and updates the sum and elements accordingly
        firstValley = findFirstValley(head);
        for(Node curr = head; curr != null; curr = curr.next) {
            if(curr == firstValley) {
                break;
            }
            sumToFirstValley += curr.data;
            elemsToFirstValley++;
        }

        sumToFirstValley += firstValley.data;
        elemsToFirstValley++;
    }

    /**
     * Insert a node at the head.
     * 
     * @param node the node to be inserted
     */
    private void insertAtHead(Node node) {
        if(head == null) {
            head = tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
    }

    /**
     * Checks whether the given node is a valley using the conditions provided.
     * 
     * @param node the node to check conditions for
     * @return true if it is a valley, false if not
     */
    private boolean isValley(Node node) {
        if (node.prev == null && node.next != null) {
            return node.data < node.next.data;
        }
        
        if (node.next == null && node.prev != null) {
            return node.data < node.prev.data;
        }
        
        if (node.prev != null && node.next != null) {
            return node.data < node.prev.data && node.data < node.next.data;
        }
        
        return true;
    }

    /**
     * Finding the first valley (for initialization)
     * 
     * @param start the node to start the traversal at
     * @return the first valley after that node
     */
    private Node findFirstValley(Node start) {
        Node curr = start;
        
        while(curr != null) {
            if(isValley(curr)) {
                return curr;
            }
            curr = curr.next;
        }
        return null;
    }


    /**
     * Checks if the entire landscape is excavated (i.e., there are no landforms left).
     * 
     * @return true if the landscape is empty, false otherwise.
     */
    public boolean isEmpty() {
        // Determine if the landscape is empty.
        return head == null;
    }

    /**
     * Locates the first valley point in the landscape of Numerica.
     * 
     * @return The treasure associated with the first valley point.
     */
    public double getFirst() {
        // Locate the first valley point and calculate its treasure.
        return ((double) sumToFirstValley / elemsToFirstValley);
    }

    private void deleteNode(Node node) {
        if(node == null) {
            return;
        }

        if(node == head) {
            head = node.next;
        }

        if(node == tail) {
            tail = node.prev;
        }

        if(node.prev != null) {
            node.prev.next = node.next;
        }

        if(node.next != null) {
            node.next.prev = node.prev;
        }

        node.next = node.prev = null;
    }

    /**
     * Excavates the first valley point, removing it from the landscape of Numerica.
     * 
     * @return The treasure collected from the excavated valley point.
     */
    public double remove() {
        // Remove the first valley point and update internal state.
        if(isEmpty()) {
            return -1.0;
        }

        // update treasure
        double treasure = getFirst();
        totalTreasure += treasure;

        // storing the nodes
        Node prevNode = firstValley.prev;
        Node nextNode = firstValley.next;

        // instance variable updates
        sumToFirstValley -= firstValley.data;
        elemsToFirstValley--;

        // handle removal
        deleteNode(firstValley);

        // handle new valley in 2 cases:
        // case 1: the node right before is a valley (best case)
        // case 2: the node is present somewhere after the removed node (worst case)
        if(prevNode != null && isValley(prevNode)) {
            firstValley = prevNode;
        } else {
            Node curr = nextNode;
            while(curr != null) {
                elemsToFirstValley++;
                sumToFirstValley += curr.data;
                if(isValley(curr)) {
                    firstValley = curr;
                    break;
                }
                curr = curr.next;
            }
        }

        return treasure;
    }

    /**
     * Creates a new landform at the position where the first valley was just removed.
     * 
     * @param height The height of the new landform.
     */
    public void insert(int height) {
        // Insert a new landform at the correct position.
        Node newNode = new Node(height);

        // do the insertion here
        if(isEmpty()) {
            head = tail = newNode;
            sumToFirstValley = height;
            elemsToFirstValley = 1;
            return;
        }

        newNode.next = firstValley;
        newNode.prev = firstValley.prev;

        if(firstValley.prev != null) {
            firstValley.prev.next = newNode;
        } else {
            insertAtHead(newNode);
        }

        firstValley.prev = newNode;

        // check for changing first valley
        if(height < firstValley.data) {
            // inserted node becomes firstValley
            sumToFirstValley -= firstValley.data;
            sumToFirstValley += height;
            firstValley = newNode;
        } else if(height > firstValley.data && (newNode.prev == null || height < newNode.prev.data)) {
            // keep the first valley the same, just change the data
            sumToFirstValley += height;
            elemsToFirstValley++;
        } else if(newNode.prev != null && firstValley.data < newNode.prev.data && height > newNode.prev.data) {
            // the previous node becomes a valley
            sumToFirstValley -= firstValley.data;
            elemsToFirstValley--;
            firstValley = newNode.prev;
        }
     }

    /**
     * Returns the current total treasure collected through successive remove operations.
     * 
     * @return The total treasure collected.
     */
    public double getTotalTreasure() {
        // Calculate and return the total treasure collected.
        return totalTreasure;
    }
}