package undirected_unweighted;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
* unweighted undirected acyclic graph
* assume we have given number of Nodes
* contained in the graph and number of
* edges to make*/
class Node{
    ArrayList<Integer> neighbours;

    public Node(){
        this.neighbours = new ArrayList<>();
    }

    public void addNeighbour(int initial){
        neighbours.add(initial);
    }

    public boolean hasNeighbour(int initial){
        if(neighbours.isEmpty()) return false;
        return neighbours.contains(initial);
    }

}
public class Graph1 {
   private Node[] nodes;

   public Graph1(int capacity){
       this.nodes = new Node[capacity + 1];
   }

   private void insertNode(int initial){
       nodes[initial] = new Node();
   }
    public void insertEdge(int source, int destination){
       if(getNode(source) == null){
           insertNode(source);
       }
       if(getNode(destination) == null){
           insertNode(destination);
       }
       if(!getNode(source).hasNeighbour(destination)){
           getNode(source).addNeighbour(destination);
           getNode(destination).addNeighbour(source);
       }
    }

    private Node getNode(int initial){
        return nodes[initial];
    }

    public void printNodesWithTheirNeighbours(){
       for(int i = 1; i < nodes.length; i++){
           if(nodes[i] != null){
               System.out.print(i + "\n");
               for(Integer neighbour: nodes[i].neighbours){
                   System.out.print("  " + neighbour + "\n");
               }
           }
       }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        int numOfNodes = Integer.parseInt(tokenizer.nextToken());
        int numOfEdges = Integer.parseInt(tokenizer.nextToken());
        Graph1 graph1 = new Graph1(numOfNodes);

        for(int i = 0; i < numOfEdges; i++){
            tokenizer = new StringTokenizer(reader.readLine());
            int source = Integer.parseInt(tokenizer.nextToken());
            int destination = Integer.parseInt(tokenizer.nextToken());
            graph1.insertEdge(source, destination);
        }
        graph1.printNodesWithTheirNeighbours();

        reader.close();
    }

}
