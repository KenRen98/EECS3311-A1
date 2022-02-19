package york.eecs.source;

import java.util.*;
import java.util.stream.Collectors;

public class UndirectedGraph<T extends Comparable<T>> extends Graph<T> {

	private Map<T, Set<T>> graph;

	/**
	 *  This is the constructor.
	 *  Please do not change it.
	 */
	public UndirectedGraph() {
		this.graph = new HashMap<>();
	}

	/**
	 * @return true if graph is nonempty, false otherwise.
	 */
	public boolean isEmpty() {
		// TODO: Complete this method
		// Hint: An empty graph contains zero vertices
		if (graph.size() == 0) return true;
		// this line needs to be rewritten
		return false;
	}

	/**
	 * @return the size (i.e. number of vertices) of this graph
	 */
	public int getSize() {
		// TODO: compute the size
		return graph.size();
		// this line needs to be rewritten
	}

	/**
	 * Add a new vertex to the graph. A new vertex points
	 * to an empty set of adjacent vertices.
	 *
	 * @param vertex: an object that is a new vertex in the graph
	 */
	public void addVertex(T vertex) throws VertexExistsException {
		// TODO: Complete this method
		// Hints: If the vertex already exists, throw and exception
		//        Else, add a new pair to the graph hashmap:
		//        the vertex is the key, the value is an empty
		//        set of vertices.
		if (graph.containsKey(vertex)) {
			throw new VertexExistsException("Vertex Exist");
		}
		else{
			Set<T> newset = new TreeSet<T>();
			graph.put(vertex,newset);
		}
	}

	public List<T> getAdjacent(T vertex) {
		if(this.getSize()==0){
			return null;
		}
		return new ArrayList<>(graph.get(vertex));
	}


	@Override
	public List<T> getVertices() {
		return new ArrayList<>(graph.keySet());
	}

	/**
	 * @param fromVertex one of vertices of this edge
	 * @param toVertex the other vertex of this egde
	 */
	public void addEdge(T fromVertex, T toVertex) {
		// TODO: Complete this method
		// Hint: Recall, both vertices already exist. Also,
		//       our graphs are not oriented, hence both edges
		//       need to be added.
		graph.get(fromVertex).add(toVertex);
		graph.get(toVertex).add(fromVertex);
	}

	@Override
	public String toString() {
		// TODO: Override toString() method
		String s = "Graph:\n";
		Iterator Iterator = graph.entrySet().iterator();
		while(Iterator.hasNext()){
			Map.Entry temp = (Map.Entry)Iterator.next();
			s+="Vertex: "+temp.getKey()+" & Adjacent Vertices: "+temp.getValue().toString()+'\n';
		}
        return s; // this line needs to change
	}


}
