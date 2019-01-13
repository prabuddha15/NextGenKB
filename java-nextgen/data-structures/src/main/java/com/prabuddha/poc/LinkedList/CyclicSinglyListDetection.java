package com.prabuddha.poc.LinkedList;

public class CyclicSinglyListDetection {

    private Node head;

    public void addNode(String data) {
        if (head == null) {
            head = new Node(data);
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            Node addedNode = new Node(data);
            current.next = addedNode;
            current = addedNode;
        }
    }

    public void printAllElements() {
        if (head == null) {
            System.out.println("There is no element in this link list");
        } else {
            Node current = head;
            while (current.next != null) {
                System.out.println(current.data);
                current = current.next;
            }
            System.out.println(current.data);
        }

    }

    public boolean isLinkListCyclic() {
        if(head==null){
            return false;
        }
        Node fast=head;
        Node slow=null;

        while(fast!=null && fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;

            if(fast==slow){
                return true;
            }

        }

        return false;
    }

    class Node {

        private String data;
        private Node next;

        public Node(String data) {
            this.data = data;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        CyclicSinglyListDetection myObj = new CyclicSinglyListDetection();
        myObj.addNode("1");
        myObj.addNode("2");
        myObj.addNode("3");
        myObj.addNode("4");
        myObj.addNode("5");
        myObj.addNode("6");
        myObj.addNode("7");
        myObj.addNode("8");
        myObj.addNode("9");
        myObj.addNode("10");

        myObj.printAllElements();
    }
}
