import java.util.*;

public class GraphImplementation implements Graph {

    private final int[][] adjacencyMatrix;

    public GraphImplementation(int vertices){
        adjacencyMatrix = new int [vertices][vertices];  //Initialize size of graph
    }

    public void addEdge(int source, int target) throws Exception {
        if (((source < 0) || (source > adjacencyMatrix.length)) ||
                ((target < 0) || (target > adjacencyMatrix.length))){
            throw new Exception("Out of bounds"); //If the targer/source is less than 0 or more than the length of the matrix, throw exception
        }
        if (source != target)
            adjacencyMatrix[source][target] = 1; //Add edge if they the source and the target are different
    }

    public List<Integer> neighbors(int vertex) throws Exception{
        if (vertex < 0 || vertex > adjacencyMatrix.length)
            throw new Exception("Out of bounds"); //throw exception if the vertex is less than zero or more than the matrix length

        List<Integer> adjacent = new ArrayList<>(); //Create new 'adjacent' ArrayList

        for(int i = 0; i < adjacencyMatrix.length; i++)
            if(adjacencyMatrix[vertex][i] == 1)
                adjacent.add(i); //Add i to the adjacent matrix for every edge of the 
        return adjacent;
    }

    public List<Integer> topologicalSort(){
        List<Integer> arr = new ArrayList<>(); //Create new array list

        int[] incidents = setIncidents(); //Call function setIncidents

        for (int i = 0; i < incidents.length; i++) { //For i less than incidents length
            int v = noIncidents(incidents);//v equals i if there are no incidents, -1 if there are
            if (v != -1) { //If there are no incidents
                arr.add(v); //add v to arr
                incidents[v] = -1; //incidents[v] takes value -1
                for(int j = 0; j < incidents.length; j++)
                    if (adjacencyMatrix[v][j] == 1)
                        incidents[j] -= 1;
            } 
        }
        System.out.println(arr); //Print arr and return it
        return arr;
    }

    private int[] setIncidents(){
        int[] incidents = new int[adjacencyMatrix.length]; //Incidents with adjacent matrix length
        for (int v = 0; v < adjacencyMatrix.length; v++) //For every element in adjacent matrix
            for (int w = 0; w < adjacencyMatrix.length; w++) //For every element in adjacent matrix
                incidents[v] += adjacencyMatrix[w][v]; //incidents[v] gets added adjacencyMatrix[w][v]

        return incidents; //return incidents
    } 
    
    private int noIncidents (int [] incidents) {
        for (int i = 0; i < incidents.length; i++) //For every incident value
            if (incidents[i] == 0)//If there is no incident
                return i; //return 1
        return -1;//otherwise return -1
    }
}