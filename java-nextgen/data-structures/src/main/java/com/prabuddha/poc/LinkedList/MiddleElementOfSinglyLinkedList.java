package com.prabuddha.poc.LinkedList;

public class MiddleElementOfSinglyLinkedList {


    private Node head;
    private Node current;

    public void addNode(String value){
        if(this.head==null){
            this.head=new Node(value);
            current=head;
        }else{
            Node node=new Node(value);
            this.current.setNext(node);
            current=current.getNext();
        }
    }

    public void findMiddleElement(){
        if(head==null){
            return;
        }
        Node tempCurrent=head;
        Node middleNodeFinder=null;
        int listLength=1;
        while(tempCurrent.getNext()!=null){
            tempCurrent=tempCurrent.getNext();
            listLength++;
            if(listLength%2==0){
                if(middleNodeFinder==null) {
                    middleNodeFinder = head;
                }else{
                    middleNodeFinder=middleNodeFinder.getNext();
                }
            }
        }

        System.out.println("Middle Element is -> "+middleNodeFinder.getValue());
    }

    class Node {


        private String value;
        private Node next;

        public Node(String value) {
            this.value = value;
            this.next = null;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value='" + value + '\'' +
                    ", next=" + next +
                    '}';
        }
    }

    public static void main(String[] args) {
        MiddleElementOfSinglyLinkedList myObj=new MiddleElementOfSinglyLinkedList();

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

        myObj.findMiddleElement();


    }
}
