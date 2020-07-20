//First Compile Edge class


import java.util.ArrayList;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Collections;
import java.util.Arrays;
import java.util.TreeSet;
import static java.lang.StrictMath.min;

public class VertexCluster {

    ArrayList<String> vertex_list = new ArrayList<>();                //List of all vertex
    ArrayList<Edge> edge_list = new ArrayList<Edge>();                //List of all edges in ascending order
    ArrayList<ArrayList<String>> cluster_list = new ArrayList<ArrayList<String>>();   //Clusters are stored in ArrayList
    ArrayList<Integer> weight_list = new ArrayList<Integer>();              //weights of clusters
    Set<Set<String>> cluster_set = new LinkedHashSet<Set<String>>();            //Clusters set

    //This method will do pre-processing for clusterVertex method
    private void preprocess() {
        //Clear all list
        cluster_list.clear();
        cluster_set.clear();
        weight_list.clear();

        //Sort the edge_list in ascending order
        edge_list.sort(new Comparator<Edge>() {
            @Override
            public int compare(Edge a, Edge b) {
                if (a.getLength() == b.getLength()) {
                    if (a.getVertexA().compareTo(b.getVertexA()) < 0) {
                        return -1;
                    } else if (a.getVertexA().compareTo(b.getVertexA()) > 0) {
                        return 1;
                    } else if (a.getVertexA().compareTo(b.getVertexA()) == 0) {
                        if (a.getVertexB().compareTo(b.getVertexB()) < 0) {
                            return -1;
                        } else if (a.getVertexB().compareTo(b.getVertexB()) > 0) {
                            return 1;
                        }
                    }
                    return 0;
                } else {
                    if (a.getLength() < b.getLength()) {
                        return -1;
                    } else if (a.getLength() > b.getLength()) {
                        return 1;
                    }
                    return 0;
                }
            }
        });
    }

    //This method will validate parameter passed to addEdge method
    //Return true if inputs are valid otherwise false.
    private boolean input_validate(String start, String end, int weight) {
        if (start == null || end == null || weight <= 0) {
            return false;
        } else if (start.equalsIgnoreCase("null") || start.equalsIgnoreCase("empty") || end.equalsIgnoreCase("null") || end.equalsIgnoreCase("empty")) {
            return false;
        } else if (start.isEmpty() || end.isEmpty()) {
            return false;
        } else if ((!start.matches(".*\\S.*")) || (!end.matches(".*\\S.*"))) {
            return false;
        } else if (start.equals(end)) {
            return false;
        } else {
            return true;
        }
    }

    //This method will convert cluster from ArrayList to Set
    private Set<Set<String>> to_set(ArrayList<ArrayList<String>> cluster_list) {
        for (int j = 0; j < cluster_list.size(); j++) {
            cluster_set.add(new TreeSet<>(cluster_list.get(j)));
        }
        return cluster_set;
    }

    //This method will maintain vertex list
    private void addVertex(String lab) {
        if (!vertex_list.contains(lab))
            vertex_list.add(lab);
        Collections.sort(vertex_list);
    }

    //This method will addEdge into edge_list
    public boolean addEdge(String start, String end, int weight) {
        try {
            boolean flag = false;
            if (!input_validate(start, end, weight)) {
                return false;
            }
            //if starting and ending vertex is same
            else {
                addVertex(start);
                addVertex(end);
                //Check for duplicate edge
                for (int i = 0; i < edge_list.size(); i++) {
                    Edge temp = new Edge(edge_list.get(i));
                    if (start.equals(temp.getVertexA()) && end.equals(temp.getVertexB())) {
                        flag = true;
                    } else if (end.equals(temp.getVertexA()) && start.equals(temp.getVertexB())) {
                        flag = true;
                    }
                }
                //If edge is already exist then return false
                if (flag) {
                    return false;
                } else {
                    if (start.compareTo(end) < 0)
                        edge_list.add(new Edge(start, end, weight));
                    else
                        edge_list.add(new Edge(end, start, weight));
                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }
    }

    //This method will do clustering and return clusters set
    Set<Set<String>> clusterVertices(float tolerance) {
        if(tolerance<0){
            return null;
        }
        // Initialisation step : sort edges by their weight
        preprocess();
        int clustor_a = 0, clustor_b = 0;   //Temporary cluster index
        float ratio;
        double a, b;
        try {
            if (!vertex_list.isEmpty() && !edge_list.isEmpty()) {
                //Initialize cluster list and weight list
                //Assign each vertex as a cluster and weight as 1
                for (int i = 0; i < vertex_list.size(); i++) {
                    cluster_list.add(new ArrayList<String>(Arrays.asList(vertex_list.get(i))));
                    weight_list.add(1);
                }

                //Process each edge in increasing order
                for (int j = 0; j < edge_list.size(); j++) {
                    Edge temp = edge_list.get(j);       //Temporary reference variable to store edge

                    //find that vertex of this edge is present in which cluster
                    for (int row = 0; row < cluster_list.size(); row++) {
                        if (cluster_list.get(row).contains(temp.getVertexA())) {
                            clustor_a = row;
                        }
                        if (cluster_list.get(row).contains(temp.getVertexB())) {
                            clustor_b = row;
                        }
                    }
                    //If it already present in same cluster do not process
                    //It is in different clusters then process
                    if (clustor_a != clustor_b) {

                        //Find the ratio for cluster
                        ratio = ((float)temp.getLength() / (min(weight_list.get(clustor_a), weight_list.get(clustor_b))));

                        //If ratio is les than or equal to tolerance then merge two clusters
                        if (ratio <= tolerance) {
                            cluster_list.get(clustor_a).addAll(cluster_list.get(clustor_b));
                            if (weight_list.get(clustor_a) < weight_list.get(clustor_b)) {
                                weight_list.set(clustor_a, weight_list.get(clustor_b));
                            }
                            if (weight_list.get(clustor_a) <= temp.getLength()) {
                                weight_list.set(clustor_a, temp.getLength());
                            }
                            cluster_list.remove(clustor_b);
                            weight_list.remove(clustor_b);
                        }
                    }
                }
                return (to_set(cluster_list));
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}
