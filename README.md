## Note
>This is a course assignment for the course CSCI3901-Software Developement Concepts. The main purpose of this assignment to get familiar with the Graph data structures and algorithms related to graph.This assignemnt also includes the concept of the clustering.
# Description:

This program is mainly implemented clustering using undirected weighted Graph. It&#39;s based on the concept of the minimum weight spanning Tree. Clustering will be done on the basis of the weight of the edge. We have used the concept of Kruskal&#39;s algorithm for minimum spanning tree. There are mainly two classes in this program named &quot; **VertexCluster**&quot; and &quot; **Edge**&quot;. VertexCluster class contains several methods for adding edge and creating clusters. Edge class contains several variables and methods regarding edge properties. Following is the general approach used and a high-level overview of classes in the program:

**Class Edge** : This class mainly contains properties of the edge of the graph. Edge contains two vertexes (starting and ending) and weight of the edge. Moreover, this class has one constructor and four methods. Four methods are getters and setters method. Following are the general elements of class:

**Variables:&quot;vertexA&quot; **is a starting vertex,**&quot;vertexB&quot; **is an ending vertex,&quot;** length&quot;** is a weight of the edge.

**getLength ()**: This method will return the weight of an edge.

**getVertexA ()**: This method will return the string label of starting vertex.

**getVertexB ()**: This method will return the string label of starting vertex.

**setLength ()**: This method will set weight for an edge.

**Class VertexCluster:** This class contains mainly responsible to add edge and **generate clusters** of similar vertexes. It contains five methods. Which are discussed further in a given document.

# Files and external data

- There are two java files for this problem.
  - First java file named &quot; **Edge.java**&quot;, which contains the Edge class with 4 methods. It has basically the structure of an Edge in the tree.
  - Second java file named &quot; **VertexCluster.java**&quot;, which contains VertexCluster class with 4 methods.
- There are **no database** tables related to this program implementation.

# Data Structure and their relation to each other

Following are the data structures used in the program:

- **ArrayList<String>** is used to store all possible **vertex**. Vertexes are stored in ArrayList in a sorted manner. At the time of clustering, we use this list of vertexes.
- **ArrayList<Edge>** is used to store all instances of **edge**. It is stored in ascending order according to the weight of the edge.
- We have used **ArrayList<ArrayList<String>>** to store the **cluster list**. The cluster contains a list of vertexes, that are stored in ArrayList<String> and the list of clusters is store in main ArrayList<ArrayList<String>>. Which is the main intention to use nested ArrayList rather than other data structure.
- We have used **ArrayList<Integer>** to store the weight of clusters.

# Key algorithms and design elements:

Here is my general approach to a solution:

- Program will start from the step of adding the edge. We have **addEdge** method which will create the edge object with properties of edge and then inserted in edge\_list.
- After successfully adding all the edges we move to the clustering step.
- The program will maintain one list named &quot;vertex\_list&quot;. Where we store a **list of vertices** in a **sorted** manner.
- We need edge\_list in sorted because we need to consider the **minimum weight edge** first. so,in pre­-processing we sort edge\_list in **ascending** order of weight.
- Now at the time of clustering, first we initiate list named &quot; **cluster\_list**&quot; with **individual cluster** of single vertexes. With that we initiate ArrayList of &quot; **weight\_list**&quot; with **1.0** weight of each cluster.
- &quot;weight\_list&quot; is maintained with the cluster\_list. Both the list is relate with index count. For example, Cluster at index 3 in cluster\_list has weight on index 3 of weight\_list.
- After initializing cluster\_list, we start to process each and every edge of the graph we have in edge\_list.
- It uses the concept of **Kruskal&#39;s Algorithm** for **minimum spanning tree**. It will take edge first whose weight is minimum. Process that and then take another edge. But the difference here is that if both (starting/ending) vertex of the edge are already in same cluster then we do not consider that edge.
- At the time of merging we consider &quot; **ratio**&quot;. We will get value of ratio from formula given in problem description. If the ratio is **less than or equal to** tolerance, then we **merge** cluster otherwise we do not.
- After processing all edges, we convert cluster\_list into **Set<Set<String>>** cluster\_set and return to user.

# Description of methods Used:

- **addEdge** :
  - This method is used to add edge to edge\_list.
  - User will pass 3 parameters: starting vertex, ending vertex, and weight of edge.
  - This method will first check some preconditions and then create the instance of Edge class.
  - It adds that instance in edge\_list and then we sort edge\_list using method sort and custom Comparator.
  - If the edge added successfully then it will return true otherwise false.

- **input\_validate:**
  - This method will do input validation for the addEdge method.
  - This method will return true if input to addEdge is valid otherwise false.
  - It checks all condition which is mentioned in Test Case section.
  - Access modifier of this method is private because it is called by internal code.
- **clusterVertices** :
  - This method is mainly responsible for clustering.
  - User will pass tolerance value as a parameter.
  - This method will call preprocess method to sort edges.
  - Working flow of this method is already discussed above.
  - It will process each and every edge in order to minimum to maximum weight.
  - Program will compare ratio with passed tolerance and then if the ratio is less than or equal to tolerance then merge cluster.
  - If there is weight of edge in cluster is more than weight of cluster, then we update the weight of cluster.
  - It will convert cluster\_list to cluster\_set explicitly.
  - It will return cluster\_set.

- **Preprocess** :
  - This will sort edgelist in ascending order of weight and clear previous clusters list.
  - It sorts the edge\_list using sort method Collections class and custom Comparator.
  - This method is private and doesn&#39;t return any value.

- **To\_set:**
  - This method will convert ArrayList of clusters to Set of Clusters.
  - It will return Set<Set<String>> to clusterVertex.
  - Access modifier of this method is private because it is called by internal code.

- **addVertex** :
  - This method will add vertex to the vertex list.
  - Vertex is string passed by method addEdge.
  - It does not return any value.
  - It will not add vertex if it is already in vertex list.

# Assumptions

- This Graph will not self-edge because that vertex itself is one cluster that self-loop does not have any significance. For Example- addEdge (&quot;A&quot;,&quot;A&quot;,9) will return false.
- This Graph will not allow duplicate edge even though it has different weights.
- the edge weights are positive integers. It will return false if weight is 0.
- Vertexes of edge is case sensitive.
- If tolerance is less than zero then program will return null.

# Limitations

- The current design is limited to a simple undirected weighted graph.
- If a graph is not connected, we can adapt our algorithm to compute the clusters of each of its connected components.
- Only float values are allowed in tolerance as a argument in clusterVertices method.
- Only Integer values are allowed in weight of an edge in addEdge method.

# Test Cases:

**Input validation**

- addEdge:
  - Pass null value as a parameter in start and end.
  - Pass empty value as a parameter in start and end.
  - Pass the 0 or negative number in weight.
- VertexCluster:
  - Pass a negative number in tolerance.
  - Pass 0 in tolerance.

**Boundary Case**

- Weight of an edge is 1
- Weight of an edge is 1000
- If the number of clusters 100.
- If the number of edges in graph is 1.
- If the number of edges in Graph is 100.

**Control**** Flow**

- addEdge
  - Add edge with positive weight.
  - Add edge in an empty graph.
  - Add edge with same starting and ending vertex.
  - Add edge with different starting and ending vertex.
  - Add edge that is already added previously.
- clusterVertices:
  - If both ending vertexes of edge is in same cluster.
  - If the ratio is greater than tolerance.
  - If the ratio is less than tolerance.
  - If the ratio is equal to tolerance.
  - Invoke this method without adding edges.
  - If there are no clusters possible.
- Graph:
  - If graph contains only 1 edge.
  - If all vertexes of graph are connected with each other.
  - If graph is unconnected.
  - If graph contains cycle.
  - If graph contains N vertices and N-1 Edges.

**Data Flow**

- Call the addEdge method before clusterVertices.
- Call the clusterVertices method before addEdge.
- First add all the edges then invoke clusterVertices method and then add 2 more edges and then call clusterVertices method.
- Call clusterVerices twice in a row.
