package com.prabuddha.poc.graph;

import java.util.*;
import java.util.stream.Collectors;

public class MyGraphV1 {

    Map<Vertex,LinkedList<Vertex>> adjNodes=new HashMap<Vertex,LinkedList<Vertex>>();

//    public MyGraphV1() {
//
//
//        this.adjNodes = adjNodes;
//    }

    public void addVertex(String value){
        Vertex vertex=new Vertex(value);
        LinkedList<Vertex> list=new LinkedList<>();
        adjNodes.put(vertex,list);
    }

    public void addEdge(String fromLable, String toLable){
        Vertex toVertex=new Vertex(toLable);
        Vertex fromVertex=new Vertex(fromLable);
        adjNodes.get(fromVertex).add(toVertex);
    }

    public void removeEdge(String fromLable, String toLable){
        Vertex toVertex=new Vertex(toLable);
        Vertex fromVertex=new Vertex(fromLable);
        adjNodes.get(fromVertex).remove(toVertex);
    }

    public void removeVertex(String lable){
        Vertex removedVertex=new Vertex(lable);
        adjNodes.values().stream().map(adjNodeList -> adjNodeList.remove(removedVertex)).collect(Collectors.toList());
        adjNodes.remove(removedVertex);
    }

    public LinkedList<Vertex> getAllAdjacentNodes(String lable){
        Vertex rootVertex=new Vertex(lable);
        return adjNodes.get(rootVertex);
    }

    public void printAllNodes (){
        StringBuffer sb=new StringBuffer();
       for (Vertex vertex : adjNodes.keySet()){
            sb.append(vertex.getValue());
            sb.append(adjNodes.get(vertex).toString());
       }

        System.out.println(sb.toString());
    }


    class Vertex{

        String value;

        public Vertex(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex vertex = (Vertex) o;
            return vertex.getValue().equals(this.value);
        }

        @Override
        public int hashCode() {
            return this.value.hashCode();
        }

        @Override
        public String toString() {
            return "Vertex{" +
                    "value='" + value + '\'' +
                    '}';
        }
    }

    public void bfsOperation(String rootLable){
        Vertex rootVertex=new Vertex(rootLable);
        Set<String> visitedVertex=new HashSet<>();
        Queue<Vertex> bfsQueue=new LinkedList<Vertex>();
        bfsQueue.add(rootVertex);
        visitedVertex.add(rootVertex.getValue());
        while(!bfsQueue.isEmpty()){
            Vertex currentVertex= bfsQueue.remove();
            System.out.println(" : "+currentVertex.getValue());
            for(Vertex vertex : adjNodes.get(currentVertex)){
                if(!visitedVertex.contains(vertex.getValue())) {
                    visitedVertex.add(vertex.getValue());
                    bfsQueue.add(vertex);
                }
            }
        }

    }

    public static void main(String[] args) {
        MyGraphV1 graph = new MyGraphV1();
        graph.addVertex("Bob");
        graph.addVertex("Alice");
        graph.addVertex("Mark");
        graph.addVertex("Rob");
        graph.addVertex("Maria");
        graph.addEdge("Bob", "Alice");
        graph.addEdge("Bob", "Rob");
        graph.addEdge("Alice", "Mark");
        graph.addEdge("Rob", "Mark");
        graph.addEdge("Alice", "Maria");
        graph.addEdge("Rob", "Maria");

        graph.printAllNodes();

//        graph.removeVertex("Mark");
//        graph.printAllNodes();
//
//        graph.removeEdge("Alice","Bob");
//        graph.printAllNodes();

        graph.bfsOperation("Bob");
    }

}
