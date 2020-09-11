import java.io.*; 
import java.util.*;

@SuppressWarnings("unchecked")
class Graph { 
    int size;
    LinkedList<Integer> graph[];

    public Graph(int size) {
        // Creating graph
        this.size = size;
        graph = new LinkedList[size];

        for (int i = 0; i < size; i++) {
            graph[i] = new LinkedList<Integer>();
        }
    }

    public void addEdge(int source, int destination, boolean isUndirected){

        graph[source].add(destination); //add edge u -> v

        if(isUndirected)  //add back edge v -> u (if undirected)
            graph[destination].add(source);
    }

    public LinkedList<Integer> getAdj(int index){
        return graph[index];
    }

} 