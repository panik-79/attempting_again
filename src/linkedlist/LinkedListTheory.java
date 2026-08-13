package linkedlist;

/*
 * ============================================================
 *                    LINKED LIST - STARTER PACK
 * ============================================================
 *
 * A Linked List is a linear data structure where elements
 * (called NODES) are connected using references.
 *
 *
 * ARRAY
 * ------------------------------------------------------------
 *
 *     [10] [20] [30] [40]
 *
 * Elements are stored next to each other in memory conceptually
 * as an array structure.
 *
 *
 * LINKED LIST
 * ------------------------------------------------------------
 *
 *     [10 | •] ---> [20 | •] ---> [30 | •] ---> [40 | null]
 *       Node          Node          Node           Node
 *
 * Each node contains:
 *
 *     1. data  -> the actual value
 *     2. next  -> reference to the next node
 *
 *
 * ============================================================
 * 1. NODE
 * ============================================================
 *
 * A node is the basic building block of a linked list.
 *
 * For an Integer linked list:
 *
 *     class Node {
 *         int data;
 *         Node next;
 *     }
 *
 * Example:
 *
 *     Node node1 = new Node(10);
 *     Node node2 = new Node(20);
 *
 *     node1.next = node2;
 *
 * Result:
 *
 *     node1
 *       |
 *       v
 *     [10 | •] ---> [20 | null]
 *
 *
 * ============================================================
 * 2. HEAD
 * ============================================================
 *
 * `head` stores a reference to the FIRST node.
 *
 *     head
 *      |
 *      v
 *     [10 | •] ---> [20 | •] ---> [30 | null]
 *
 * If the list is empty:
 *
 *     head = null
 *
 *
 * IMPORTANT:
 *
 * We do NOT store the entire linked list inside `head`.
 *
 * head simply points to the first node.
 *
 * From the first node, we follow `next` references to reach
 * the remaining nodes.
 *
 *
 * ============================================================
 * 3. TAIL
 * ============================================================
 *
 * The last node is called the tail.
 *
 *     head                         tail
 *      |                            |
 *      v                            v
 *     [10 | •] ---> [20 | •] ---> [30 | null]
 *
 * The last node always has:
 *
 *     tail.next == null
 *
 *
 * Maintaining a `tail` reference is optional.
 *
 * If we maintain it, inserting at the end can be O(1).
 *
 *
 * ============================================================
 * 4. WHY USE A LINKED LIST?
 * ============================================================
 *
 * Main advantage:
 *
 *     Insertion/deletion can be O(1)
 *
 *     IF we already have a reference to the correct node/
 *     position.
 *
 * Example:
 *
 * Before:
 *
 *     A ---> B ---> C
 *
 * Insert X after A:
 *
 *     A ---> X ---> B ---> C
 *
 * We only need to change references.
 *
 *
 * ============================================================
 * 5. LINKED LIST VS ARRAY
 * ============================================================
 *
 *                    ARRAY          LINKED LIST
 * ------------------------------------------------------------
 * Access by index      O(1)             O(n)
 *
 * Search               O(n)             O(n)
 *
 * Insert at beginning  O(n)             O(1)*
 *
 * Delete beginning     O(n)             O(1)
 *
 * Insert at end        O(1)**           O(n)***
 *
 * Delete at end        O(1)             O(n)
 *
 * Memory               Less overhead    Extra reference(s)
 *
 * *  If we already have the head.
 *
 * ** Amortized O(1) for dynamic arrays such as ArrayList.
 *
 * *** O(1) if a tail reference is maintained for insertion.
 *
 *
 * ============================================================
 * 6. IMPORTANT CATCH ABOUT O(1) INSERTION / DELETION
 * ============================================================
 *
 * You will often hear:
 *
 *     "Linked List insertion is O(1)."
 *
 * This is incomplete.
 *
 * Finding the position can itself take O(n).
 *
 * Example:
 *
 *     10 ---> 20 ---> 30 ---> 40 ---> 50
 *
 * If we want to insert after 40:
 *
 *     We first have to traverse:
 *
 *     10 -> 20 -> 30 -> 40
 *
 * That is O(n).
 *
 * But once we have the reference to 40,
 * the actual insertion is O(1).
 *
 *
 * ============================================================
 * 7. TRAVERSAL
 * ============================================================
 *
 * To visit every node:
 *
 *     Node current = head;
 *
 *     while (current != null) {
 *         // use current.data
 *         current = current.next;
 *     }
 *
 *
 * The important idea:
 *
 *     current = current.next;
 *
 * moves us from one node to the next.
 *
 *
 * ============================================================
 * 8. INSERTION
 * ============================================================
 *
 * Insert at beginning:
 *
 * Before:
 *
 *     head
 *      |
 *      v
 *     [10] ---> [20] ---> [30]
 *
 * Create 5:
 *
 *     newNode.next = head;
 *     head = newNode;
 *
 * After:
 *
 *     head
 *      |
 *      v
 *     [5] ---> [10] ---> [20] ---> [30]
 *
 *
 * ============================================================
 * Insert after a node
 * ============================================================
 *
 * Before:
 *
 *     [10] ---> [20] ---> [30]
 *                ^
 *                |
 *              current
 *
 * Insert 15 after 10:
 *
 *     newNode.next = current.next;
 *     current.next = newNode;
 *
 * After:
 *
 *     [10] ---> [15] ---> [20] ---> [30]
 *
 *
 * The ORDER is important.
 *
 * WRONG:
 *
 *     current.next = newNode;
 *     newNode.next = current.next;
 *
 * The original next node can be lost.
 *
 *
 * ============================================================
 * 9. DELETION
 * ============================================================
 *
 * Delete the first node:
 *
 *     head = head.next;
 *
 *
 * Before:
 *
 *     head
 *      |
 *      v
 *     [10] ---> [20] ---> [30]
 *
 * After:
 *
 *     head
 *      |
 *      v
 *     [20] ---> [30]
 *
 *
 * Delete a node in the middle:
 *
 * Before:
 *
 *     [10] ---> [20] ---> [30]
 *               ^
 *               |
 *             delete
 *
 * We need the previous node:
 *
 *     previous.next = current.next;
 *
 * After:
 *
 *     [10] ------------> [30]
 *
 *
 * ============================================================
 * 10. SINGLY LINKED LIST
 * ============================================================
 *
 * Each node only knows about the NEXT node.
 *
 *     [10] ---> [20] ---> [30] ---> null
 *
 * Node contains:
 *
 *     data
 *     next
 *
 *
 * ============================================================
 * 11. DOUBLY LINKED LIST
 * ============================================================
 *
 * Each node knows both previous and next nodes.
 *
 *     null <--- [10] <--> [20] <--> [30] ---> null
 *
 * Node contains:
 *
 *     data
 *     previous
 *     next
 *
 * Advantages:
 *
 *     - Can move forward
 *     - Can move backward
 *     - Easier deletion when node reference is available
 *
 * Disadvantage:
 *
 *     - Extra memory for previous reference
 *     - More links to maintain correctly
 *
 *
 * ============================================================
 * 12. CIRCULAR LINKED LIST
 * ============================================================
 *
 * The last node points back to the first node.
 *
 *     [10] ---> [20] ---> [30]
 *       ^                   |
 *       |___________________|
 *
 * There is no `null` at the end.
 *
 *
 * ============================================================
 * 13. COMMON LINKED LIST PATTERNS
 * ============================================================
 *
 * These are the important techniques we will eventually learn:
 *
 *     1. Basic traversal
 *
 *     2. Previous / current pointers
 *
 *     3. Fast / slow pointers
 *
 *            slow -> one step
 *            fast -> two steps
 *
 *        Used for:
 *            - finding middle
 *            - detecting cycles
 *            - finding cycle starting point
 *
 *     4. Dummy / sentinel node
 *
 *        Useful when modifying the beginning of a list.
 *
 *     5. Reversing pointers
 *
 *        Example:
 *
 *        Before:
 *            1 ---> 2 ---> 3 ---> null
 *
 *        After:
 *            1 <--- 2 <--- 3
 *                            ^
 *                           head
 *
 *     6. Merging two linked lists
 *
 *     7. Recursion
 *
 *        Linked lists are naturally recursive because:
 *
 *            current node
 *                 +
 *            rest of list
 *
 *
 * ============================================================
 * 14. IMPORTANT INTERVIEW PROBLEMS
 * ============================================================
 *
 * Basic:
 *
 *     - Traverse linked list
 *     - Search for a value
 *     - Insert at beginning
 *     - Insert at end
 *     - Insert at position
 *     - Delete first node
 *     - Delete last node
 *     - Delete by value
 *
 * Intermediate:
 *
 *     - Reverse Linked List
 *     - Find Middle of Linked List
 *     - Remove Nth Node From End
 *     - Merge Two Sorted Lists
 *     - Detect Cycle
 *     - Find Cycle Starting Point
 *     - Remove Duplicates
 *     - Palindrome Linked List
 *     - Intersection of Two Linked Lists
 *
 * Advanced:
 *
 *     - Reverse Nodes in K-Group
 *     - Merge K Sorted Lists
 *
 *
 * ============================================================
 * 15. JAVA IMPLEMENTATION
 * ============================================================
 *
 * We will implement our OWN linked list instead of using:
 *
 *     java.util.LinkedList
 *
 * This is important for learning.
 *
 * ============================================================
 */

public class LinkedListTheory {

    /*
     * ------------------------------------------------------------
     * Node
     * ------------------------------------------------------------
     *
     * Each Node contains:
     *
     *     data -> value stored in node
     *     next -> reference to next node
     */
    static class Node {

        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }


    /*
     * ------------------------------------------------------------
     * Linked List State
     * ------------------------------------------------------------
     */

    Node head;

    /*
     * Optional tail reference.
     *
     * Keeping tail allows O(1) insertion at the end.
     */
    Node tail;

    int size;


    /*
     * ------------------------------------------------------------
     * Constructor
     * ------------------------------------------------------------
     */

    public LinkedListTheory() {
        head = null;
        tail = null;
        size = 0;
    }


    /*
     * ------------------------------------------------------------
     * Is Empty?
     * ------------------------------------------------------------
     */

    public boolean isEmpty() {
        return head == null;
    }


    /*
     * ------------------------------------------------------------
     * Get Size
     * ------------------------------------------------------------
     */

    public int size() {
        return size;
    }


    /*
     * ------------------------------------------------------------
     * Add At Beginning
     * ------------------------------------------------------------
     *
     * Time: O(1)
     */

    public void addFirst(int data) {

        Node newNode = new Node(data);

        newNode.next = head;
        head = newNode;

        /*
         * If the list was empty, the new node is also the tail.
         */
        if (tail == null) {
            tail = newNode;
        }

        size++;
    }


    /*
     * ------------------------------------------------------------
     * Add At End
     * ------------------------------------------------------------
     *
     * Because we maintain tail:
     *
     * Time: O(1)
     */

    public void addLast(int data) {

        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }

        size++;
    }


    /*
     * ------------------------------------------------------------
     * Add At Index
     * ------------------------------------------------------------
     *
     * Valid indexes:
     *
     *     0 ... size
     *
     * Example:
     *
     *     [10, 20, 30]
     *
     * add(1, 15)
     *
     *     [10, 15, 20, 30]
     *
     * Time: O(n)
     */

    public void add(int index, int data) {

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            addFirst(data);
            return;
        }

        if (index == size) {
            addLast(data);
            return;
        }

        Node current = head;

        /*
         * Stop at the node BEFORE the insertion position.
         */
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }

        Node newNode = new Node(data);

        newNode.next = current.next;
        current.next = newNode;

        size++;
    }


    /*
     * ------------------------------------------------------------
     * Remove First
     * ------------------------------------------------------------
     *
     * Time: O(1)
     */

    public int removeFirst() {

        if (head == null) {
            throw new IllegalStateException("List is empty");
        }

        int removed = head.data;

        head = head.next;

        if (head == null) {
            tail = null;
        }

        size--;

        return removed;
    }


    /*
     * ------------------------------------------------------------
     * Remove At Index
     * ------------------------------------------------------------
     *
     * Time: O(n)
     */

    public int remove(int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            return removeFirst();
        }

        Node current = head;

        /*
         * Stop at the node BEFORE the node we want to remove.
         */
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }

        Node removedNode = current.next;

        current.next = removedNode.next;

        if (removedNode == tail) {
            tail = current;
        }

        size--;

        return removedNode.data;
    }


    /*
     * ------------------------------------------------------------
     * Search
     * ------------------------------------------------------------
     *
     * Returns true if data exists.
     *
     * Time: O(n)
     */

    public boolean contains(int data) {

        Node current = head;

        while (current != null) {

            if (current.data == data) {
                return true;
            }

            current = current.next;
        }

        return false;
    }


    /*
     * ------------------------------------------------------------
     * Print
     * ------------------------------------------------------------
     */

    public void print() {

        Node current = head;

        while (current != null) {

            System.out.print(current.data);

            if (current.next != null) {
                System.out.print(" -> ");
            }

            current = current.next;
        }

        System.out.println(" -> null");
    }


    /*
     * ------------------------------------------------------------
     * MAIN
     * ------------------------------------------------------------
     *
     * This is only a small demonstration.
     *
     * We will create proper problem-specific classes later.
     */

    public static void main(String[] args) {

        LinkedListTheory list = new LinkedListTheory();

        list.addLast(10);
        list.addLast(20);
        list.addLast(30);

        list.print();

        list.addFirst(5);
        list.print();

        list.add(2, 15);
        list.print();

        list.removeFirst();
        list.print();

        list.remove(1);
        list.print();

        System.out.println("Size: " + list.size());
        System.out.println("Contains 30: " + list.contains(30));
    }
}