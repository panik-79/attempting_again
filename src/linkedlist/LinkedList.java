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

    public boolean contains(int target) {
        Node temp = head;
        while (temp != null) {
            if (temp.data == target) return true;
            temp = temp.next;
        }
        return false;
    }

    public void addFirst(int data) {
        head = new Node(data, head);
    }

    public void addLast(int data) {
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


    public static void main(String[] args) {

        LinkedList list = new LinkedList();
        Node third = new Node(30, null);
        Node second = new Node(20, third);
        list.head = new Node(10, second);

        list.print();
    }
}
