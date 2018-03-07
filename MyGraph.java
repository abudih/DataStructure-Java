import java.util.*;
/**
 * @author Andrew Budihardja
 * A representation of a graph. Assumes that we do not have negative cost edges
 * in the graph.
 */

public class MyGraph implements Graph {
	// you will need some private fields to represent the graph
	// you are also likely to want some private helper methods

	private Collection<Vertex> vertex;
	private Collection<Edge> edge;
	
	/**
	 * Creates a MyGraph object with the given collection of vertices and the
	 * given collection of edges.
	 * 
	 * @param v
	 *            a collection of the vertices in this graph
	 * @param e
	 *            a collection of the edges in this graph
	 */
	public MyGraph(Collection<Vertex> v, Collection<Edge> e) {

		vertex = v;
		edge = e;
		
//		System.out.println(v.toString());
//		System.out.println(e.toString());
//		Vertex temp = new Vertex("ATL");
//		System.out.println(this.adjacentVertices(temp).toString());
		
		
		Iterator<Edge> edgeIter = edge.iterator();
		
		
		while(edgeIter.hasNext()){
			Edge curEdge = edgeIter.next();
			
			
			if(curEdge.getWeight() <0) 
				throw new IllegalArgumentException("weight of edge is wrong");
			
			boolean checkStart = false;
			boolean checkDest = false;
			
		
			Iterator<Vertex> vertexIter = vertex.iterator();
			while(vertexIter.hasNext()) {
				Vertex curVertex = vertexIter.next();
				if(curEdge.getSource().equals(curVertex))
					checkStart = true;
				if(curEdge.getDestination().equals(curVertex)) 
					checkDest = true;
			}
			
			
			if(checkStart == false||checkDest == false) 
				throw new IllegalArgumentException("vertex collection does not contain vertex edge");
			
			
			Iterator<Edge> edgeIter2 = edge.iterator();
			while(edgeIter2.hasNext()) {
				Edge secondEdge = edgeIter2.next();
				
				if(secondEdge.getSource().equals(curEdge.getSource())
						&& secondEdge.getDestination().equals(curEdge.getDestination())
						&& secondEdge.getWeight() != curEdge.getWeight())
					throw new IllegalArgumentException();
			}
		}
		 
	}

	/**
	 * Return the collection of vertices of this graph
	 * 
	 * @return the vertices as a collection (which is anything iterable)
	 */
	public Collection<Vertex> vertices() {
		return vertex;
	}

	/**
	 * Return the collection of edges of this graph
	 * 
	 * @return the edges as a collection (which is anything iterable)
	 */
	public Collection<Edge> edges() {
		return edge;
	}

	/**
	 * Return a collection of vertices adjacent to a given vertex v. i.e., the
	 * set of all vertices w where edges v -> w exist in the graph. Return an
	 * empty collection if there are no adjacent vertices.
	 * 
	 * @param v
	 *            one of the vertices in the graph
	 * @return an iterable collection of vertices adjacent to v in the graph
	 * @throws IllegalArgumentException
	 *             if v does not exist.
	 */
	public Collection<Vertex> adjacentVertices(Vertex v) {

		boolean check = false;
		Iterator<Vertex> vertexIter = vertex.iterator();
		
		
		while(vertexIter.hasNext()) {
			if (vertexIter.next().equals(v)) {
				check = true;
				break;
			}
		}
		
		if(!check) 
			throw new IllegalArgumentException();
		
		List<Vertex> adjacent = new ArrayList<Vertex>();
		Iterator<Edge> edgeIter = edge.iterator();
		
		while(edgeIter.hasNext()) {
			Edge curEdge = edgeIter.next();
			
			if(curEdge.getSource().equals(v))
				adjacent.add(curEdge.getDestination());
		}
		
		
		return adjacent;
	}

	/**
	 * Test whether vertex b is adjacent to vertex a (i.e. a -> b) in a directed
	 * graph. Assumes that we do not have negative cost edges in the graph.
	 * 
	 * @param a
	 *            one vertex
	 * @param b
	 *            another vertex
	 * @return cost of edge if there is a directed edge from a to b in the
	 *         graph, return -1 otherwise.
	 * @throws IllegalArgumentException
	 *             if a or b do not exist.
	 */
	public int edgeCost(Vertex a, Vertex b) {

		boolean checkStart = false;
		boolean checkDest = false;
		Iterator<Vertex> vertexIter = vertex.iterator();
		
		while (vertexIter.hasNext()) {
			
			Vertex curVertex = vertexIter.next();
			if(a.equals(curVertex)) 
				checkStart = true;
			if(b.equals(curVertex)) 
				checkDest = true;
			
		}
		
		if(checkStart == false||checkDest == false) 
			throw new IllegalArgumentException();
		
		Iterator<Edge> edgeIter = edge.iterator();
		
		while(edgeIter.hasNext()) {
			Edge curEdge = edgeIter.next();
			
			if(curEdge.getSource().equals(a) && curEdge.getDestination().equals(b))
				return curEdge.getWeight();
		}
		
		
		
		
		return -1;
	}

	/**
	 * Returns the shortest path from a to b in the graph, or null if there is
	 * no such path. Assumes all edge weights are nonnegative. Uses Dijkstra's
	 * algorithm.
	 * 
	 * @param a
	 *            the starting vertex
	 * @param b
	 *            the destination vertex
	 * @return a Path where the vertices indicate the path from a to b in order
	 *         and contains a (first) and b (last) and the cost is the cost of
	 *         the path. Returns null if b is not reachable from a.
	 * @throws IllegalArgumentException
	 *             if a or b does not exist.
	 */
	
	public Path shortestPath(Vertex a, Vertex b) {
		
		boolean checkStart = false;
		boolean checkDest = false;
		Iterator<Vertex> vertexIter = vertex.iterator();
		
		
		while(vertexIter.hasNext()){
			
			Vertex curVertex = vertexIter.next();
			if (a.equals(curVertex)) 
				checkStart = true;
			if (b.equals(curVertex)) 
				checkDest = true;
		}
		
		
		if(checkStart == false||checkDest == false) 
			throw new IllegalArgumentException();
		
		if(a.equals(b)){
			ArrayList<Vertex> zero = new ArrayList<Vertex>();
			zero.add(a);
			
			return new Path(zero,0);
		}
		
		class Unvisited {
			
			Vertex v;
			int cost;
			
			Unvisited(Vertex theV, int theCost) {
				v = theV;
				cost = theCost;
			}
		}
		
		class Direction {
			
			
			Vertex cur;
			Vertex way;
			Direction(Vertex theV) {
				cur = theV;
				way = new Vertex("NULL");
			}
		}
		
		
		ArrayList<Unvisited> unv = new ArrayList<Unvisited>();
		Iterator<Vertex> vertexIter2 = vertex.iterator();
		while (vertexIter2.hasNext()){
			Vertex curVer = vertexIter2.next();
			
			
			if (curVer.equals(a)) 
				unv.add(new Unvisited(curVer, 0));
			else 
				unv.add(new Unvisited(curVer, Integer.MAX_VALUE));	
		}
		
		
		ArrayList<Direction> dir = new ArrayList<Direction>();
		Iterator<Vertex> vertexIter3 = vertex.iterator();
		while(vertexIter3.hasNext()){
			dir.add(new Direction(vertexIter3.next()));
		}

		
		Unvisited minU = null;
		while (!unv.isEmpty()){
			
			Iterator<Unvisited> uIter = unv.iterator();
			minU = uIter.next();
			
			while (uIter.hasNext()){
				Unvisited curU = uIter.next();
				
				if (curU.cost < minU.cost) 
					minU = curU;
			}
			
			if(minU.v.equals(b)) 
				break;
			
			unv.remove(minU);
			Collection<Vertex>adj = this.adjacentVertices(minU.v);
			Iterator<Vertex> adjIter = adj.iterator();
			
			while (adjIter.hasNext()){
				Vertex adjVertex = adjIter.next();
				Unvisited adjU = null;
				Iterator<Unvisited> unvIter = unv.iterator();
				
				
				while (unvIter.hasNext()){
					Unvisited curUnv = unvIter.next();
					
					if (curUnv.v.equals(adjVertex)){
						adjU = curUnv;
						break;
					}
				}
				
				if(adjU == null) 
					continue;
				
				if(this.edgeCost(minU.v, adjU.v) + minU.cost < adjU.cost){
					
					Iterator<Direction> dirIter = dir.iterator();
					
					while (dirIter.hasNext()){
						Direction curDir = dirIter.next();
						
						if (curDir.cur.equals(adjU.v)){
							curDir.way = minU.v;
							adjU.cost = this.edgeCost(minU.v, adjU.v) + minU.cost;
							
							break;
						}
					}
				}
			}
		}
		
		
		Direction curDir = null;
		List<Vertex> path = new ArrayList<Vertex>();
		Iterator<Direction> dirIter = dir.iterator();
		
		while (dirIter.hasNext()) {
			curDir = dirIter.next();
			
			if (curDir.cur.equals(minU.v))
				break;
		}
		
		path.add(curDir.cur);
		
		while (!curDir.way.getLabel().equals("NULL")){
			dirIter = dir.iterator();
			
			while (dirIter.hasNext()){
				Direction nextDir = dirIter.next();
				
				if (curDir.way.equals(nextDir.cur)){
					curDir = nextDir;
					break;
				}
			}
			path.add(curDir.cur);
		}
		
		return new Path(path,minU.cost);
	}

}
