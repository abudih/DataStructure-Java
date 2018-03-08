import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author Andrew Budihardja
 *
 */
public class MazeGraph {
	/**
	 * 
	 *The inner class of location.
	 *
	 */
	public static class Location {
		public int row;
		public int column;
		
		public Location(int row, int column) {
			this.row = row;
			this.column = column;
			}
		
		public boolean equals(Object obj){
			
			if (this == obj)
				return true;
			if ((obj == null) || (obj.getClass() != this.getClass()))
				return false;
			Location cell = (Location) obj;
			if (row == cell.row && column == cell.column)
				return true;
			return false;
			
		}
		
		public String toString(){
			
			return "(" + row + "," + column + ")";
		}
	}

	private boolean[][][][] adjacent;
	private boolean[][] mazeGraph;
	
	private int tempWidth;
	private int tempHeight;
//	private ArrayList<Location> path;
	
	/**
	 * MazeGraph constructor
	 * @param width
	 * @param height
	 */
	public MazeGraph(int width, int height) {
		
		tempWidth=width;
		tempHeight=height;
		
		if (width < 1 || height < 1 || width % 2 != 1 || 
				height % 2 != 1)
			throw new IllegalArgumentException();
		
		adjacent = new boolean[height][width][height][width];
		
		mazeGraph = new boolean[height][width];
		for (int i = 0; i < height; i++)
			for(int j = 0; j< width; j++)
				mazeGraph[i][j] = true;
		
	}
	
	/**
	 * 
	 * method that analyzes two location and checks
	 * whether they may be connected/ adjacent
	 * @param a
	 * @param b
	 * @return
	 */
	private static boolean mayBeConnected(Location a, Location b){
		
		if (a.row == b.row - 1 && a.column == b.column 
				|| a.row == b.row + 1 && a.column == b.column
				|| a.row == b.row && a.column == b.column - 1
				|| a.row == b.row && a.column == b.column + 1)
			return true;
		else
			return false;
		
	}
	
	/**
	 * 
	 * checks whether two locations are connected.
	 * throws exception if the locations are not side-by-side.
	 * @param a
	 * @param b
	 */
	public void connect(Location a, Location b) {
		
		if (mayBeConnected(a, b))
			adjacent [a.row][a.column][b.row][b.column] = false;
		else
			throw new IllegalArgumentException();
		
	}
	
	/**
	 * 
	 * converts two adjacent vertices - for directed graph
	 * @param height
	 * @param edges
	 */
	public void convertAdjacent(int height,int width, int edges[][]){
		
		for(int[]edge: edges){
			mazeGraph[edge[0]][edge[1]]=false;
			mazeGraph[edge[2]][edge[3]]=false;
		}
	}
	
	/**
	 * 
	 * returns a list containing locations
	 * that consist the shortest path between two locations
	 * 
	 * @param s
	 * @param e
	 * @param maze
	 * @return
	 */
	public ArrayList<Location> getShortestPath(Location s, Location e) {
		
		int[][] levelMatrix = new int[mazeGraph.length][mazeGraph[0].length];
		for (int i = 0; i < mazeGraph.length; ++i){
			for (int j = 0; j < mazeGraph[0].length; ++j){
				levelMatrix[i][j] = mazeGraph[i][j] == true ? -1 : 0;
			
			}
		}
		
		LinkedList < Location > queue = new LinkedList < Location >();
		Location start = s;
		Location end = e;
		queue.add(start);
		levelMatrix[start.row][start.column] = 1;
		
		while (!queue.isEmpty()){
			Location location = queue.poll();
			if (location == end)
				break;
			
			int level = levelMatrix[location.row][location.column];
			Location[] nextLocations = new Location[4];
			nextLocations[3] = new Location(location.row, location.column - 1);
			nextLocations[2] = new Location(location.row - 1, location.column);
			nextLocations[1] = new Location(location.row, location.column + 1);
			nextLocations[0] = new Location(location.row + 1, location.column);
			
			for (Location nextLocation : nextLocations){
				if (nextLocation.row < 0 || nextLocation.column < 0)
					continue;
				if (nextLocation.row == mazeGraph.length
						|| nextLocation.column == mazeGraph[0].length)
					continue;
				if (levelMatrix[nextLocation.row][nextLocation.column] == 0){
					queue.add(nextLocation);
					levelMatrix[nextLocation.row][nextLocation.column] = level + 1;
				}
			}
		}
		
		if (levelMatrix[end.row][end.column] == 0)
			return null;
		
		LinkedList<Location>path= new LinkedList<Location>();
		Location location = end;
		
		while (!location.equals(start)){
			path.push(location);
			int level = levelMatrix[location.row][location.column];
			Location[] nextLocations = new Location[4];   
			nextLocations[0] = new Location(location.row + 1, location.column);
			nextLocations[1] = new Location(location.row, location.column + 1);
			nextLocations[2] = new Location(location.row - 1, location.column);
			nextLocations[3] = new Location(location.row, location.column - 1);
			
			for (Location nextLocation : nextLocations){
				if (nextLocation.row < 0 || nextLocation.column < 0)
					continue;
				if (nextLocation.row == mazeGraph.length
						|| nextLocation.column == mazeGraph[0].length)
					continue;
				if (levelMatrix[nextLocation.row][nextLocation.column] == level - 1)
				{
					location = nextLocation;
					break;
					}
				}
			}
		
		ArrayList<Location> result = new ArrayList<Location>();
		
		for(Location temp : path){
			result.add(temp);
		}
		return result;
		
		}
		

	/**
	 * returns string representation of the graph
	 */
	
	public String toString() {
		
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < mazeGraph.length; i++){
			for(int j = 0; j< mazeGraph[0].length; j++)
				if (mazeGraph[i][j] == false)
					result.append('.');
				else
					result.append('X');
			result.append('\n');
		}
		return result.toString();
		
	}
	
	public static void main(String args[]){
		
		
		final int edges[][] = {{1, 0, 1, 1}, {1, 1, 1, 2}, {1, 1, 2, 1}, 
				{1, 2, 1, 3}, {1, 3, 1, 4}, {1, 4, 1, 5}, {1, 5, 1, 6}, 
				{1, 5, 2, 5}, {1, 6, 1, 7}, {1, 7, 1, 8}, {1, 8, 1, 9}, 
				{1, 9, 2, 9}, {1, 11, 2, 11}, {2, 1, 3, 1}, {2, 5, 3, 5}, 
				{2, 9, 3, 9}, {2, 11, 3, 11}, {3, 1, 4, 1}, {3, 3, 4, 3}, 
				{3, 5, 3, 6}, {3, 6, 3, 7}, {3, 7, 4, 7}, {3, 11, 4, 11}, 
				{4, 1, 5, 1}, {4, 3, 5, 3}, {4, 7, 5, 7}, {4, 11, 5, 11}, 
				{5, 1, 5, 2}, {5, 2, 5, 3}, {5, 5, 5, 6}, {5, 6, 5, 7}, 
				{5, 7, 5, 8}, {5, 8, 5, 9}, {5, 11, 5, 12}, {5, 9, 5, 10}};
		
		MazeGraph maze = new MazeGraph(13, 7); 
		
		for (int[] edge : edges) 
			maze.connect(new Location(edge[0], edge[1]), new Location(edge[2], edge[3])); 
		
		maze.convertAdjacent(7, 13, edges);
		
		System.out.println(maze);
		
		List<Location> path = maze.getShortestPath(new Location(1,0), new Location(5,12));
		if (path == null)
			System.out.println("No path is possible");
		for (Location cell : path)
			   System.out.print(cell + ",");
		
	}
}	