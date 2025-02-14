/**
 * ValleyTraveler class represents a magical map that can identify and modify
 * valley points in the landscape of Numerica.
 * 
 * @author Saloni Shah
 */
public class ValleyTraveler {

    private class Node {
        int value;
        double prefixSum;
        Node prev, next;

        Node(int value) {
            this.value = value;
            this.prefixSum = 0;
            this.prev = null;
            this.next = null;
        }
    }

    // Instance variables to manage the landscape and collected treasures.
    private Node head, tail;
    private double totalTreasure = 0.0;

    /**
     * Constructor to initialize the magical map with the given landscape of Numerica.
     * 
     * @param landscape An array of distinct integers representing the landscape.
     */
    public ValleyTraveler(int[] landscape) {
        // Initialize the internal state based on the provided landscape.
        for(int i = landscape.length - 1; i >= 0; i--) {
            insertAtHead(landscape[i]);
        }
        updatePrefixSums();
    }

    private void insertAtHead(int value) {
        Node newNode = new Node(value);

        if(head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        updatePrefixSums();
    }

    private void updatePrefixSums() {
        Node curr = head;
        double sum = 0;
        int index = 0;

        while (curr != null) {
            sum += curr.value;
            curr.prefixSum = sum / (index + 1);
            index++;
            curr = curr.next;
        }
    }

    private double calculateTreasure(Node valley) {
        if(valley.prev == null) {
            return valley.prefixSum;
        }

        return valley.prefixSum;
    }

    private boolean isValley(Node node) {
        if (node.prev == null && node.next != null) {
            return node.value < node.next.value;
        }
        
        if (node.next == null && node.prev != null) {
            return node.value < node.prev.value;
        }
        
        if (node.prev != null && node.next != null) {
            return node.value < node.prev.value && node.value < node.next.value;
        }
        
        return true;
    }

    private Node findFirstValley() {
        Node curr = head;
        
        while(curr != null) {
            if(isValley(curr)) {
                return curr;
            }
            curr = curr.next;
        }

        return null;
    }

    private void removeValley(Node valley) {
        if(isEmpty()) {
            return;
        }

        if(valley.prev != null) {
            valley.prev.next = valley.next;
        } else {
            head = valley.next;
        }

        if(valley.next != null) {
            valley.next.prev = valley.prev;
        } else {
            tail = valley.prev;
        }

        valley.prev = null;
        valley.next = null;

        updatePrefixSums();
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
        // Locate the first valley point and return their associated treasures
        Node firstValleyPoint = findFirstValley();
        
        if(firstValleyPoint != null) {
            return calculateTreasure(firstValleyPoint);
        }

        return -1.0;
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
        
        Node firstValleyPoint = findFirstValley();

        if(firstValleyPoint != null) {
            double treasure = calculateTreasure(firstValleyPoint);
            totalTreasure += treasure;
            removeValley(firstValleyPoint);
            return firstValleyPoint.prefixSum;
        }

        return -1.0;
    }

    /**
     * Creates a new landform at the position where the first valley was just removed.
     * 
     * @param height The height of the new landform.
     */
    public void insert(int height) {
        // Insert a new landform at the correct position.
        Node newNode = new Node(height);
        Node firstValleyPoint = findFirstValley();

        if(firstValleyPoint == null) {
            insertAtHead(height);
            return;
        }

        newNode.next = firstValleyPoint;
        newNode.prev = firstValleyPoint.prev;

        if(firstValleyPoint.prev != null) {
            firstValleyPoint.prev.next = newNode;
        } else {
            head = newNode;
        }

        firstValleyPoint.prev = newNode;

        updatePrefixSums();
    }

    /**
     * Returns the current total treasure collected through successive remove operations.
     * 
     * @return The total treasure collected.
     */
    public double getTotalTreasure() {
        // Return the total treasure collected.
        return totalTreasure;
    }
}