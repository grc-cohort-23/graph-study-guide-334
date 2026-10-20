import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Practice {

  /**
   * Returns the count of vertices with odd values that can be reached from the given starting vertex.
   * The starting vertex is included in the count if its value is odd.
   * If the starting vertex is null, returns 0.
   *
   * Example:
   * Consider a graph where:
   *   5 --> 4
   *   |     |
   *   v     v
   *   8 --> 7 < -- 1
   *   |
   *   v
   *   9
   * 
   * Starting from 5, the odd nodes that can be reached are 5, 7, and 9.
   * Thus, given 5, the number of reachable odd nodes is 3.
   * @param starting the starting vertex (may be null)
   * @return the number of vertices with odd values reachable from the starting vertex
   */
  public static int oddVertices(Vertex<Integer> starting) {
    Set<Vertex<Integer>> visited = new HashSet<>();

    Stack<Vertex<Integer>> stack = new Stack<>();
    stack.add(starting);
  
    int numOfOdds = 0;

    while(!stack.isEmpty()){
      Vertex<Integer> current = stack.pop();
      if(current == null || visited.contains(current))continue;
      visited.add(current);

      if(current.data % 2 == 1) numOfOdds++;

      // if(current.neighbors == null)
      for(var neighbor : current.neighbors){
        stack.add(neighbor);
      }
    }

    return numOfOdds;
  }

  /**
   * Returns a *sorted* list of all values reachable from the starting vertex (including the starting vertex itself).
   * If duplicate vertex data exists, duplicates should appear in the output.
   * If the starting vertex is null, returns an empty list.
   * They should be sorted in ascending numerical order.
   *
   * Example:
   * Consider a graph where:
   *   5 --> 8
   *   |     |
   *   v     v
   *   8 --> 2 <-- 4
   * When starting from the vertex with value 5, the output should be:
   *   [2, 5, 8, 8]
   *
   * @param starting the starting vertex (may be null)
   * @return a sorted list of all reachable vertex values by 
   */
  public static List<Integer> sortedReachable(Vertex<Integer> starting) {

    Set<Vertex<Integer>> visited = new HashSet<>();
    Stack<Vertex<Integer>> stack = new Stack<>();

    List<Integer> list = new ArrayList<>();

    stack.add(starting);

    while(!stack.isEmpty()){
      Vertex<Integer> current = stack.pop();
      if(current == null || visited.contains(current)) continue;
      visited.add(current);
      list.add(current.data);
      
      for(var neighbor : current.neighbors){
        stack.add(neighbor);
      }

    }
    list.sort(null);
    return list;
  }

  /**
   * Returns a sorted list of all values reachable from the given starting vertex in the provided graph.
   * The graph is represented as a map where each key is a vertex and its corresponding value is a set of neighbors.
   * It is assumed that there are no duplicate vertices.
   * If the starting vertex is not present as a key in the map, returns an empty list.
   *
   * @param graph a map representing the graph
   * @param starting the starting vertex value
   * @return a sorted list of all reachable vertex values
   */
  public static List<Integer> sortedReachable(Map<Integer, Set<Integer>> graph, int starting) {
    
    List<Integer> list = new ArrayList<>();

    Set<Integer> visited = new HashSet<>();
    
    Stack<Integer> stack = new Stack<>();
    stack.add(starting);


    while(!stack.isEmpty()){
      Integer current = stack.pop();
      if(visited.contains(current) || !graph.containsKey(current)) continue;
      visited.add(current);

      list.add(current);

      for(var neighbor : graph.get(current)){
        stack.add(neighbor);
      }
    }

    list.sort(null);

    return list;
  }

  /**
   * Returns true if and only if it is possible both to reach v2 from v1 and to reach v1 from v2.
   * A vertex is always considered reachable from itself.
   * If either v1 or v2 is null or if one cannot reach the other, returns false.
   *
   * Example:
   * If v1 and v2 are connected in a cycle, the method should return true.
   * If v1 equals v2, the method should also return true.
   *
   * @param <T> the type of data stored in the vertex
   * @param v1 the starting vertex
   * @param v2 the target vertex
   * @return true if there is a two-way connection between v1 and v2, false otherwise
   */
  public static <T> boolean twoWay(Vertex<T> v1, Vertex<T> v2) {

    Set<Vertex<T>> visited = new HashSet<>();
    
    if(!isPath(v1, v2, visited)) return false;

    visited.clear();

    return isPath(v2,v1,visited);
  }

  private static <T> boolean isPath(Vertex<T> current, Vertex<T> end, Set<Vertex<T>> visited){
    if(visited.contains(current) || current == null || end == null) return false;
      visited.add(current);

      if(current == end) return true;

      boolean isPath = false;

      for(var neighbor : current.neighbors){
        isPath = isPath || isPath(neighbor,end,visited);
      }

      return isPath;
  }

  /**
   * Returns whether there exists a path from the starting to ending vertex that includes only positive values.
   * 
   * The graph is represented as a map where each key is a vertex and each value is a set of directly reachable neighbor vertices. A vertex is always considered reachable from itself.
   * If the starting or ending vertex is not positive or is not present in the keys of the map, or if no valid path exists,
   * returns false.
   *
   * @param graph a map representing the graph
   * @param starting the starting vertex value
   * @param ending the ending vertex value
   * @return whether there exists a valid positive path from starting to ending
   */
  public static boolean positivePathExists(Map<Integer, Set<Integer>> graph, int starting, int ending) {
    
    Set<Integer> visited = new HashSet<>();

    Stack<Integer> stack = new Stack<>();
    stack.add(starting);

    while(!stack.isEmpty()){
      Integer current = stack.pop();
      if(visited.contains(current) || current <= 0)continue;
      visited.add(current);

      if(current == ending)return true;

      for(var neighbor : graph.get(current)){
        stack.add(neighbor);
      }
    }
    
    return false;
  }

  /**
   * Returns true if a professional has anyone in their extended network (reachable through any number of links)
   * that works for the given company. The search includes the professional themself.
   * If the professional is null, returns false.
   *
   * @param person the professional to start the search from (may be null)
   * @param companyName the name of the company to check for employment
   * @return true if a person in the extended network works at the specified company, false otherwise
   */
  public static boolean hasExtendedConnectionAtCompany(Professional person, String companyName) {
    if(person == null) return false;

    Set<Professional> checked = new HashSet<>();
    return hasExtendedConnectionAtCompany(person, companyName, checked);
  }

  private static boolean hasExtendedConnectionAtCompany(Professional person, String companyName, Set<Professional> checked){
    if(checked.contains(person)) return false;
    checked.add(person);

    if(person.getCompany() == companyName)return true;

    boolean hasConnection = false;
    for(var connection : person.getConnections()){
      hasConnection = hasConnection || hasExtendedConnectionAtCompany(connection, companyName, checked);
    }
    return hasConnection;

  }

  /**
   * Returns a list of possible next moves starting from a given position.
   * 
   * Starting from current, which is a [row, column] location, a player can move 
   * by one according to the directions provided.
   * 
   * The directions are given as a 2D array, where each inner array is a [row, column]
   * pair that describes a move. For example, if given the below array it would be possible
   * to move to the right, up, down, or down/left diagonally.
   * {
   *  {0, 1},  // Right
   *  {-1, 0}, // Up
   *  {1, 0},  // Down
   *  {1, -1}  // Down/left diagonal
   * }
   * 
   * However, the player can not move off the edge of the board, or onto any 
   * location that has an 'X' (capital X).
   * 
   * The possible moves are returned as a List of [row, column] pairs. The List
   * can be in any order.
   * 
   * Example:
   * 
   * board: 
   * {
   *  {' ', ' ', 'X'},
   *  {'X', ' ', ' '},
   *  {' ', ' ', ' '}
   * }
   * 
   * current:
   * {1, 2}
   * 
   * directions:
   * {
   *  {0, 1},  // Right
   *  {-1, 0}, // Up
   *  {1, 0},  // Down
   *  {1, -1}  // Down/left diagonal
   * }
   * 
   * expected output (order of list is unimportant):
   * [{2, 2}, {2, 1}]
   * 
   * Explanation:
   * The player starts at {1, 2}.
   * The four directions the player might have to go are right, up, down, and down/left (based on the directions array).
   * They cannot go right because that would go off the edge of the board.
   * They cannot go up because there is an X.
   * They can go down.
   * They can go down/left.
   * The resultant list has the two possible positions.
   * 
   * 
   * You can assume the board is rectangular.
   * You can assume valid input (no nulls, properly sized arrays, current is in-bounds,
   * directions only move 1 square, any row/column pairs are arrays of length 2,
   * directions are unique).
   * 
   * If there are no possible moves, the method returns an empty list.
   * 
   * @param board a rectangular array where 'X' represent an impassible location
   * @param current the [row, column] starting position of the player
   * @param directions an array of [row, column] possible directions
   * @return an unsorted list of next moves
   */
  public static List<int[]> nextMoves(char[][] board, int[] current, int[][] directions) {
    List<int[]> nextMoves = new ArrayList<>();

    for(int[] direction : directions){
      int[] nextStep = new int[]{current[0] + direction[0], current[1] + direction[1]};
      if(nextStep[0] >= 0 && nextStep[0] < board.length
        && nextStep[1] >= 0 && nextStep[1] < board[0].length 
        && board[nextStep[0]][nextStep[1]] != 'X'
      ){
        nextMoves.add(nextStep);
      }
    }
    return nextMoves;
  }
}
