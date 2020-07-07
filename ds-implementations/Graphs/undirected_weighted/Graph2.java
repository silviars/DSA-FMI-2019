package undirected_weighted;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
 * weighted undirected graph
 * assume we have given number of Nodes
 * contained in the graph and number of
 * edges to make*/

class Node{
    Map<Integer, Integer> neighbours;

    public Node(){
        this.neighbours = new HashMap<>();
    }

    public boolean hasNeighbour(int initial){
        if(neighbours.isEmpty()) return false;
        return neighbours.containsKey(initial);
    }

    public void addNeighbour(int initial, int weight){
        if(!hasNeighbour(initial)){
            neighbours.put(initial, weight);
        }
    }

}
public class Graph2 {
    Node[] nodes;

    public Graph2(int capacity){
        this.nodes = new Node[capacity + 1];
    }

    public void insertNode(int initial){
        nodes[initial] = new Node();
    }

    public void addPath(int source, int destination, int weight){
        if(nodes[source] == null) insertNode(source);
        if(nodes[destination] == null) insertNode(destination);

        nodes[source].addNeighbour(destination, weight);
        nodes[destination].addNeighbour(source, weight);
    }

    public void print(){
        for(int i = 1; i < nodes.length; i++){
            if(nodes[i] != null){
                System.out.print("Node: " + i + "\n  Neighbours: ");
                for(Map.Entry<Integer,Integer> neighbour: nodes[i].neighbours.entrySet()){
                    System.out.print(neighbour.getKey() + " Weight: " + neighbour.getValue() + "\n");
                }
            }
        }
    }



    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        int numOfNodes = Integer.parseInt(tokenizer.nextToken());
        int numOfPaths = Integer.parseInt(tokenizer.nextToken());

        Graph2 graph2 = new Graph2(numOfNodes);

        int source, destination, weight;
        for(int i = 0; i < numOfPaths; i++){
            tokenizer = new StringTokenizer(reader.readLine());
            source = Integer.parseInt(tokenizer.nextToken());
            destination = Integer.parseInt(tokenizer.nextToken());
            weight = Integer.parseInt(tokenizer.nextToken());

            graph2.addPath(source, destination, weight);
        }

        graph2.print();
        reader.close();
    }

}
