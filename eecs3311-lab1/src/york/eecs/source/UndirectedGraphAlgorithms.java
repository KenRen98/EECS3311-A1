package york.eecs.source;

import java.util.*;

public class UndirectedGraphAlgorithms<T extends Comparable<T>> 
								implements GraphAlgorithms<T> {

	/**
	 * Please implement BFS algorithm as described on the handout
	 * @param g: a graph
	 * @param initial: the starting vertex of the path
	 * @param destination: the destination vertex of the path
	 * @return the path from initial to destination in the form of
	 *         an ArrayList of vertices, with initial as the first
	 *         element, and destination as the last element of the 
	 *         ArrayList
	 */
	public List<T> findBFSpath(Graph<T> g, T initial, T destination) {
		// TODO : implement BFS path search
		if (g.getAdjacent(initial)==null){
			return null;
		}
		else if(initial.equals(destination)){
			List<T> X = new ArrayList<>();
			X.add(initial);
			return X;
		}
		else {
			Set<T> visited = new HashSet<T>();
			ArrayList<T> path = new ArrayList<T>();
			Queue<T> found = new LinkedList<>();

			T current = initial;
			found.add(initial);
			List<T> local= new LinkedList<>();

			while(!found.isEmpty()|| !local.isEmpty()){

				//when current Vertex has no more edges to visit
				if(local.isEmpty()){
					visited.add(current);
					current=found.remove();
					path.add(current);
					local = g.getAdjacent(current);
				}

				//System.out.println("Current:"+current);
				//System.out.println("Array:"+g.getAdjacent(current));

				//If all connected Vertex is visited
				if(visited.containsAll(g.getAdjacent(current))){
					visited.add(current);
					path.remove(current);
					//System.out.println("Visited");
				}

				//next in the list of edges are going to visit
				T next;
				if(local.iterator().hasNext())
				{
					next = local.iterator().next();
					local.remove(local.iterator().next());
				}
				else{
					return null;
				}


				//if next is not visited and found
				if(!found.contains(next)&&!visited.contains(next)){
					found.add(next);
				}

				/*
				System.out.println("next:"+next+": "+next.getClass().toString());
				System.out.println("LIST:"+local);
				System.out.println(destination+": "+destination.getClass().toString());
				*/

				//If a destination is found
				if(next.equals(destination)){
					path.add(next);
					current = destination;
					List<T> result = new ArrayList<T>();
					result.add(destination);
					//Using the idea of last node only have 1 connected vertex in path list
					//Find the way back, by removing the only node on way back each time.
					while(!g.getAdjacent(current).contains(initial)){
						List<T> temp = new ArrayList<T>(path);
						temp.retainAll(g.getAdjacent(current));
						//System.out.println("Temp: "+temp);
						path.removeAll(g.getAdjacent(current));
						current=temp.iterator().next();
						result.add(current);
					}
					result.add(initial);
					Collections.reverse(result);
					//System.out.println("Return: "+result);
					return (List)result;
				}

				//System.out.println(local.isEmpty());
			}
		}
		return null;
	}
}