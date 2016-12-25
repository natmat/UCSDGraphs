/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which represents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
package roadgraph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

import geography.GeographicPoint;
import sun.awt.image.VolatileSurfaceManager;
import util.GraphLoader;

/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which represents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
public class MapGraph {

	//TODO: Add your member variables here in WEEK 2
	private static final Map<Vertex, List<Edge>> mapGraph = new HashMap<>();

	/** 
	 * Create a new empty MapGraph 
	 */
	public MapGraph()
	{
		// TODO: Implement in this constructor in WEEK 2
		mapGraph.clear();
	}

	/**
	 * Get the number of vertices (road intersections) in the graph
	 * @return The number of vertices in the graph.
	 */
	public int getNumVertices()
	{
		//TODO: Implement this method in WEEK 2
		return(mapGraph.keySet().size());
	}

	/**
	 * Return the intersections, which are the vertices in this graph.
	 * @return The vertices in this graph as GeographicPoints
	 */
	public Set<GeographicPoint> getVertices()
	{
		//TODO: Implement this method in WEEK 2
		HashSet<GeographicPoint> locationSet = new HashSet<>();
		for (Vertex v : mapGraph.keySet()) {
			locationSet.add(v.getLocation());
		}
		return(locationSet);
	}

	/**
	 * Get the number of road segments in the graph
	 * @return The number of edges in the graph.
	 */
	public int getNumEdges()
	{
		//TODO: Implement this method in WEEK 2
		int count = 0;
		for (Vertex v : mapGraph.keySet()) {
			count += mapGraph.get(v).size();
		}
		return count;
	}



	/** Add a node corresponding to an intersection at a Geographic Point
	 * If the location is already in the graph or null, this method does 
	 * not change the graph.
	 * @param location  The location of the intersection
	 * @return true if a node was added, false if it was not (the node
	 * was already in the graph, or the parameter is null).
	 */
	public boolean addVertex(GeographicPoint location)
	{
		// TODO: Implement this method in WEEK 2
		// Attempt to add new unique location/vertex to set
		if (null == location) {
			return (false);
		}

		List<Edge> rval = mapGraph.put(new Vertex(location), new ArrayList<>());
		return (rval == null);
	}

	/**
	 * Adds a directed edge to the graph from pt1 to pt2.  
	 * Precondition: Both GeographicPoints have already been added to the graph
	 * @param from The starting point of the edge
	 * @param to The ending point of the edge
	 * @param roadName The name of the road
	 * @param roadType The type of the road
	 * @param length The length of the road, in km
	 * @throws IllegalArgumentException If the points have not already been
	 *   added as nodes to the graph, if any of the arguments is null,
	 *   or if the length is less than 0.
	 */
	public void addEdge(GeographicPoint from, GeographicPoint to, String roadName,
			String roadType, double length) throws IllegalArgumentException {

		//TODO: Implement this method in WEEK 2
		if ((null == from) || (null == to) 
				|| (null == roadName) || (null == roadType) 
				|| !mapGraph.containsKey(new Vertex(from))
				|| !mapGraph.containsKey(new Vertex(to))
				|| (length < 0)) {
			throw(new IllegalArgumentException());
		}

		Edge e = new Edge(from, to, roadName, roadType, length);
		List<Edge> edges = mapGraph.get(new Vertex(from));
		if (!edges.contains(e)) {
			edges.add(e);
		}
		else {
			throw (new IllegalArgumentException());
		}
	}

	public List<GeographicPoint> dfs(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
		Consumer<GeographicPoint> temp = (x) -> {};
		return dfs(start, goal, temp);
	}

	public List<GeographicPoint> dfs(GeographicPoint start, 
			GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 2
		// Hook for visualization.  See write up.
		//nodeSearched.accept(next.getLocation());

		class Stack {
			private List<GeographicPoint> alStack = new ArrayList<>();
			public void push(final GeographicPoint inGP) {
				alStack.add(0, inGP);
			}
			public GeographicPoint pop() {
				return(alStack.remove(0));
			}
			public boolean isEmpty() {
				return(alStack.size() == 0);
			}
		}

		Set<GeographicPoint> visitedHashSet = new HashSet<>();
		Stack stack = new Stack();
		HashMap<GeographicPoint, GeographicPoint> parentMap = new HashMap<>();

		GeographicPoint cur = start;

		stack.push(cur);
		visitedHashSet.add(cur);

		int i = 0;
		while (!stack.isEmpty()) {
			cur = stack.pop();
			if (null == cur) break; // Didn't find goal
			if (cur.equals(goal)) break; // Found goal

			System.out.println(i++ + ":" + cur);
			visitedHashSet.add(cur);
			nodeSearched.accept(cur);

			for (Edge e : mapGraph.get(new Vertex(cur))) {
				if (!visitedHashSet.contains(e.getEndPoint())) {
					GeographicPoint endPoint = e.getEndPoint();
					visitedHashSet.add(endPoint);
					parentMap.put(endPoint, cur);
					stack.push(e.getEndPoint());
				}
				else {
				}
			}
		}

		if (!cur.equals(goal)) {
			return(null);
		}

		System.out.println("Found goal " + goal);

		ArrayList<GeographicPoint> route = new ArrayList<>();
		GeographicPoint ep = goal; 
		route.add(0, ep);
		while (!parentMap.get(ep).equals(start)) {
			route.add(0,parentMap.get(ep));
			ep = parentMap.get(ep);
		}

		System.out.println("Route: " + route.toString());
		return(route);
	}


	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
		Consumer<GeographicPoint> temp = (x) -> {};
		return bfs(start, goal, temp);
	}

	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, 
			GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 2
		// Hook for visualization.  See write up.
		//nodeSearched.accept(next.getLocation());

		Set<GeographicPoint> visitedHashSet = new HashSet<>();
		Queue<GeographicPoint> queue = new LinkedList<>();
		HashMap<GeographicPoint, GeographicPoint> parentMap = new HashMap<>();

		GeographicPoint cur = start;

		queue.add(cur);
		visitedHashSet.add(cur);

		int i = 0;
		while (!queue.isEmpty()) {
			cur = queue.remove();
			if (null == cur) break; // Didn't find goal
			if (cur.equals(goal)) break; // Found goal

			System.out.println(i++ + ":" + cur);
			visitedHashSet.add(cur);
			nodeSearched.accept(cur);

			for (Edge e : mapGraph.get(new Vertex(cur))) {
				if (!visitedHashSet.contains(e.getEndPoint())) {
					GeographicPoint endPoint = e.getEndPoint();
					visitedHashSet.add(endPoint);
					parentMap.put(endPoint, cur);
					queue.add(e.getEndPoint());
				}
				else {
				}
			}
		}

		if (!cur.equals(goal)) {
			return(null);
		}

		System.out.println("Found goal " + goal);

		ArrayList<GeographicPoint> route = new ArrayList<>();
		GeographicPoint ep = goal; 
		route.add(0, ep);
		while (!parentMap.get(ep).equals(start)) {
			route.add(0,parentMap.get(ep));
			ep = parentMap.get(ep);
		}

		System.out.println("Route: " + route.toString());
		return(route);
	}

	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
		// You do not need to change this method.
		Consumer<GeographicPoint> temp = (x) -> {};
		return dijkstra(start, goal, temp);
	}

	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, 
			GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 3
		System.out.println(new Object(){}.getClass());
		System.out.println(new Object(){}.getClass().getEnclosingClass());
		System.out.println(new Object(){}.getClass().getEnclosingClass().getName());

		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());

		/**
		 *  shortest path tree set
		 */
		Set<GeographicPoint> sptSet = new LinkedHashSet<>();
		Set<GeographicPoint> visitedList = new HashSet<>();
		
		class GeographicPointComparator implements Comparator<GeographicPoint> {

			@Override
			public int compare(GeographicPoint o1, GeographicPoint o2) {
				if (o1.getDist() < o2.getDist()) return(-1);
				if (o1.getDist() > o2.getDist()) return(1);
				return(0);
			}			
		}
		Comparator<GeographicPoint> gpComparator = new GeographicPointComparator();
		PriorityQueue<GeographicPoint> pq = new PriorityQueue<>(getNumVertices(), gpComparator);

		GeographicPoint cur = start;
		cur.setDist(0.0);
		sptSet.add(cur);

		while (cur != goal) {
			// Add neighs to the pq
			for (Edge neighbour : mapGraph.get(new Vertex(cur))) {
				final GeographicPoint endPoint = neighbour.getEndPoint();
				if (sptSet.contains(endPoint)) {
					continue;
				}
				
				endPoint.setDist(cur.distance(endPoint));
				pq.add(endPoint);
			}
			
			cur = pq.poll();
			sptSet.add(cur);
		}

		return null;
	}

	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
		Consumer<GeographicPoint> temp = (x) -> {};
		return aStarSearch(start, goal, temp);
	}

	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, 
			GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 3

		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());

		return null;
	}



	public static void main(String[] args)
	{
		System.out.print("Making a new map...");
		MapGraph firstMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", firstMap);
		System.out.println("DONE.");

		// You can use this method for testing.  


		/* Here are some test cases you should try before you attempt 
		 * the Week 3 End of Week Quiz, EVEN IF you score 100% on the 
		 * programming assignment.
		 */
		MapGraph simpleTestMap = new MapGraph();
		List<GeographicPoint> testroute;
		GeographicPoint testStart;
		GeographicPoint testEnd;

		//		GraphLoader.loadRoadMap("data/testdata/simpletest.map", simpleTestMap);
		//		testStart = new GeographicPoint(1.0, 1.0);
		//		testEnd = new GeographicPoint(8.0, -1.0);
		//		System.out.println("Test NM using bfs");
		//		testroute = simpleTestMap.bfs(testStart,testEnd);

		//		GraphLoader.loadRoadMap("data/maps/cambridge.map", simpleTestMap);
		//		testStart = new GeographicPoint(52.1993476,0.1273227);
		//		testEnd = new GeographicPoint(52.2019205, 0.1180646);
		//		System.out.println("Test NM using bfs");
		//		testroute = simpleTestMap.bfs(testStart,testEnd);

		GraphLoader.loadRoadMap("data/maps/cambridge.map", simpleTestMap);
		testStart = new GeographicPoint(52.1993476,0.1273227);
		testEnd = new GeographicPoint(52.2019205, 0.1180646);
		System.out.println("Test NM using bfs");
		testroute = simpleTestMap.dfs(testStart,testEnd);

		// A very simple test using real data
		//		testStart = new GeographicPoint(32.869423, -117.220917);
		//		testEnd = new GeographicPoint(32.869255, -117.216927);
		//		System.out.println("Test 2 using utc: Dijkstra should be 13 and AStar should be 5");
		//		testroute = testMap.dijkstra(testStart,testEnd);
		//		testroute2 = testMap.aStarSearch(testStart,testEnd);


		// A slightly more complex test using real data
		//		testStart = new GeographicPoint(32.8674388, -117.2190213);
		//		testEnd = new GeographicPoint(32.8697828, -117.2244506);
		//		System.out.println("Test 3 using utc: Dijkstra should be 37 and AStar should be 10");
		//		testroute = testMap.dijkstra(testStart,testEnd);
		//		testroute2 = testMap.aStarSearch(testStart,testEnd);


		/* Use this code in Week 3 End of Week Quiz */
		/*MapGraph theMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/maps/utc.map", theMap);
		System.out.println("DONE.");

		GeographicPoint start = new GeographicPoint(32.8648772, -117.2254046);
		GeographicPoint end = new GeographicPoint(32.8660691, -117.217393);


		List<GeographicPoint> route = theMap.dijkstra(start,end);
		List<GeographicPoint> route2 = theMap.aStarSearch(start,end);

		 */

	}	
}
