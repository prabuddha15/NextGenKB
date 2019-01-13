package com.prabuddha.poc.btree;

public class BasicBinaryTree {

    Node rootNode=null;

    public void addNode(int data){
        Node myNode=new Node();
        myNode.setData(data);

        if(rootNode==null){
            rootNode=myNode;
        }else {
            insert(rootNode,myNode);
        }
    }

    private void insert(Node root,Node myNode) {
        if(myNode.getData() < root.getData()){
            if(root.getLeftNode()==null) {
                root.setLeftNode(myNode);
            }else{
                insert(root.getLeftNode(),myNode);
            }
        }else if(myNode.getData() > root.getData()){
            if(root.getRightNode()==null) {
                root.setRightNode(myNode);
            }else{
                insert(root.getRightNode(),myNode);
            }
        }
    }

    private void printBinaryTree() {
        printNodeValue(rootNode);
    }

    public void printNodeValue(Node parent){
        System.out.println(parent.getData());
        if(parent.getLeftNode()!=null){
            printNodeValue(parent.getLeftNode());
        }
        if(parent.getRightNode()!=null){
            printNodeValue(parent.getRightNode());
        }
    }

    private class Node{
        Node leftNode;
        Node rightNode;
        Node parentNode;
        int data;

        public Node getLeftNode() {
            return leftNode;
        }

        public void setLeftNode(Node leftNode) {
            this.leftNode = leftNode;
        }

        public Node getRightNode() {
            return rightNode;
        }

        public void setRightNode(Node rightNode) {
            this.rightNode = rightNode;
        }

        public Node getParentNode() {
            return parentNode;
        }

        public void setParentNode(Node parentNode) {
            this.parentNode = parentNode;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        BasicBinaryTree tree=new BasicBinaryTree();
        int [] array=new int[]{5,3,6,8,1,2,7};
        for(int data: array){
            tree.addNode(data);
        }

        tree.printBinaryTree();
    }



}
