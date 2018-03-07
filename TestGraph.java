import java.util.*;
/**
 * 
 * @author Andrew Budihardja
 *
 */

public class TestGraph {

	
	/**
	 *This test attempts to find the route from selected vertex to itself 50 times.
	 * The cost should be always equal to 0 and length to 1.
	 * @param g
	 * @return
	 */
	
	public static boolean testMono(MyGraph g) {
		
		for(int i = 0; i < 50; i++){
			
			List<Vertex> v = (List<Vertex>) g.vertices();
			
            if(v.isEmpty())
            	continue;
            
			Collections.shuffle(v);
			
			Path p = g.shortestPath(v.get(0), v.get(0));
			
			if(p.cost != 0 || p.vertices.size() != 1)
				return false;
		}
		
		
		return true;
	}

	/**
	 * This test attempts to find the route from selected vertex to the
	 * directly connected vertex. The path length should always be 2.
	 * @param g
	 * @return
	 */
	
	public static boolean testDirect(MyGraph g) {
		
		for(int i = 0; i < 50; i++){
			
			List<Vertex> v = (List<Vertex>) g.vertices();
			Collections.shuffle(v);
			Path p = g.shortestPath(v.get(0), ((List<Vertex>) g.adjacentVertices(v.get(0))).get(0));
			
			if(p.vertices.size() != 2)
				return false;
		}
		return true;
		
	}

	/**
	 *This test runs random pathfind requests and then reruns them with the first
	 *step already done. The first path should include all steps from the second path.
	 * @param g
	 * @return
	 */
	public static boolean testChain(MyGraph g) {
		
		for (int i = 0; i < 50; i++) {
			
			List<Vertex> v = (List<Vertex>) g.vertices();
			Collections.shuffle(v);
			
			Path p1 = g.shortestPath(v.get(0), v.get(1));
			Path p2 = g.shortestPath(p1.vertices.get(p1.vertices.size()-2), v.get(1));
			
			if(!p1.vertices.containsAll(p2.vertices))
				return false;
		}
		return true;
	}
}
