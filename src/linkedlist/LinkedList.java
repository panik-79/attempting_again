package linkedlist;

import util.Utils;

public class LinkedList {

    private static class Node {
        int data;
        Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    Node head;

    public void print() {
        Node temp = head;
        while (temp != null) {
            Utils.print(temp.data + " -> ");
            temp = temp.next;
        }
        Utils.print("null");
    }

    public boolean contains (int target) {
        Node temp = head;
        while (temp != null) {
            if (temp.data == target) return true;
            temp = temp.next;
        }
        return false;
    }

    public int getSize() {

        if (head == null) {
            return 0;
        }

        int size = 0;
        Node temp = head;

        while (temp != null) {
            size++;
            temp = temp.next;
        }

        return size;
    }

    public void addFirst (int data) {
        head = new Node(data, head);
    }

    public void addLast (int data) {
        Node newNode = new Node(data, null);
        if (head == null) {
            head = newNode;
            return;
        }

        Node temp = head;
        while(temp.next != null) {
            temp = temp.next;
        }

        temp.next = newNode;
    }

    public void add (int data, int index) {

        if (index < 0 || index > getSize()) {
            Utils.println("Invalid index for insertion");
            return;
        }

        if (index == 0) addFirst(data);
        else if (index == getSize()) addLast(data);
        else {
            Node temp = head;
            while(--index > 0) {
                temp = temp.next;
            }
            temp.next = new Node(data, temp.next);
        }
    }

    public void deleteFirst() {
        if (head == null) return;
        head = head.next;
    }

    public void deleteLast() {
        if (head == null) return;

        if (head.next == null) {
            head = null;
            return;
        }

        Node temp = head;

        while (temp.next != null && temp.next.next != null) {
            temp = temp.next;
        }

        temp.next = null;
    }

    public void delete (int index) {

        if (index < 0 || index >= getSize()) {
            Utils.println("Invalid index for deletion");
            return;
        }

        if (head.next == null) {
            head = null;
            return;
        }

        if (index == 0) deleteFirst();
        else if (index == getSize() - 1) deleteLast();
        else {
            Node temp = head;
            while(--index > 0) {
                temp = temp.next;
            }
            temp.next = temp.next.next;
        }
    }

    public void deleteAll(int value) {

        // Remove matching nodes from the head
        while (head != null && head.data == value) {
            head = head.next;
        }

        if (head == null) return;

        Node prev = head;
        Node curr = head.next;

        while (curr != null) {

            if (curr.data == value) {
                prev.next = curr.next;
                curr = curr.next;
            } else {
                prev = curr;
                curr = curr.next;
            }
        }
    }

    public void insertBefore(int value, int target) {

        Node dummy = new Node(0, head);

        Node prev = dummy;
        Node curr = head;

        while (curr != null) {

            if (curr.data == target) {
                prev.next = new Node(value, curr);
            }

            prev = curr;
            curr = curr.next;
            curr = curr.next;
        }

        head = dummy.next;
    }

    public static void main(String[] args) {

        LinkedList list = new LinkedList();
        list.addLast(10);
        list.addLast(10);
        list.addLast(20);
        list.addLast(10);
        list.addLast(10);

        list.print();
        Utils.println();

        list.insertBefore(5, 10);
        list.print();
    }
}
