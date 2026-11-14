import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
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
    if(starting == null){
      return 0;
    }
    Set<Vertex<Integer>> visited = new HashSet<>();

    return oddHelper(starting, visited);
  }

    private static int oddHelper(
      Vertex<Integer> current, 
      Set<Vertex<Integer>> visited
    ){
        if(current == null || visited.contains(current)){
          return 0;
        }
        visited.add(current);
        int count =0;
        
        if(current.data % 2 != 0){
         count++;
      }
      for( Vertex<Integer> neighbor : current.neighbors){
        count += oddHelper(neighbor,visited);

      }
      return count;
  }

  // iterative dfs stack
  public static int oddVerticesIterative(Vertex<Integer> starting){
    if (starting == null){
      return 0;
    }
    Set<Vertex<Integer>> visited = new HashSet<>();
    Stack<Vertex<Integer>> stack = new Stack<>();

    stack.push(starting);
    while (stack.isEmpty()){
      Vertex<Integer> current = stack.pop();

      if(visited.contains(current)){
        continue;
      }
      visited.add(current);

      if(current.data %2 !=0){
        count++;
      }
      for(Vertex<Integer> neighbor : current.neighbors){
        if(neighbor != null && !visited.contains(neighbor)){
          stack.push(neighbor);
        }
      }
    }
    return count;

  }


  //iterative BFS queue
  public static int oddVerticesBFS(Vertex<Integer> starting) {
    if (starting == null) {
        return 0;
    }

    Set<Vertex<Integer>> visited = new HashSet<>();
    Queue<Vertex<Integer>> queue = new LinkedList<>();

    int count = 0;

    queue.add(starting);
    visited.add(starting);

    while (!queue.isEmpty()) {
      Vertex<Integer> current = queue.remove();

     if(current.data % 2 != 0) {
        count++;
      }

    for (Vertex<Integer> neighbor : current.neighbors) {
        if (neighbor != null && !visited.contains(neighbor)) {
            visited.add(neighbor);
            queue.add(neighbor);
        }
      }
    }

    return count;
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
  //recursive dfs
  public static List<Integer> sortedReachable(Vertex<Integer> starting) {
    List<Integer> result = new ArrayList<>();

    if(starting == null){
      return result;
    }

    Set<Vertex<Integer>> visited = new HashSet<>();
    sortedHelper(starting, visited, result);

    Collections.sort(result);
    return result;
  }
    private static void sortedHelper(
      Vertex<Integer> current,
      Set<Vertex<Integer>> visited,
      List<Integer> result)
    {
      if (current == null || visited.contains(current)) {
          return;
      }
      visited.add(current);
      result.add(current.data);

      for( Vertex<Integer> neighbor :current.neighbors){
        sortedHelper(neighbor, visited, result);
      }
  }

  //iterative dfs stack
  public static List<Integer> sortedReachableIterative(Vertex<Integer> starting) {
    List<Integer> result = new ArrayList<>();

    if (starting == null) {
        return result;
    }

    Set<Vertex<Integer>> visited = new HashSet<>();
    Stack<Vertex<Integer>> stack = new Stack<>();

    stack.push(starting);

    while (!stack.isEmpty()) {
      Vertex<Integer> current = stack.pop();

      if (visited.contains(current)) {
         continue;
        }

      visited.add(current);
      result.add(current.data);

      for (Vertex<Integer> neighbor : current.neighbors) {
        if (neighbor != null && !visited.contains(neighbor)) {
          stack.push(neighbor);
        }
      }
    }

    Collections.sort(result);

    return result;
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
     List<Integer> result = new ArrayList<>();

     if(!graph.containsKey(starting)){
      return result;
     }

     Set<Integer> visited = new HashSet<>();
     sortedMapHelper(graph, starting, visited, result);
     Collections.sort(result);

     return result;
    }
    private static void sortedMapHelper(
      Map<Integer, Set<Integer>> graph,
      int current,
      Set<Integer> visited,
      List<Integer> result)  {
       if (!graph.containsKey(current) || visited.contains(current)) {
      return;
      }
      visited.add(current);
      result.add(current);

    for (int neighbor : graph.get(current)) {
        sortedMapHelper(graph, neighbor, visited, result);
    }
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
  if( v1 == null || v2 == null){
    return false;
  }
   Set<Vertex<T>> visited1 = new HashSet<>();
   Set<Vertex<T>> visited2 = new HashSet<>();
       return canReach(v1, v2, visited1)
        && canReach(v2, v1, visited2);
}
  private static <T> boolean canReach(
      Vertex<T> current,
      Vertex<T> target,
      Set<Vertex<T>> visited
  ) {
      if (current == target) {
          return true;
      }

      if (current == null || visited.contains(current)) {
          return false;
      }

      visited.add(current);

      for (Vertex<T> neighbor : current.neighbors) {
          if (canReach(neighbor, target, visited)) {
              return true;
          }
      }

      return false;
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
     if (starting <= 0 || ending <= 0) {
        return false;
    }
       if (!graph.containsKey(starting) ||
        !graph.containsKey(ending)) {
        return false;
    }
    Set<Integer> visited = new HashSet<>();

    return positiveHelper(graph, starting, ending, visited);
}

  private static boolean positiveHelper(
      Map<Integer, Set<Integer>> graph,
      int current,
      int ending,
      Set<Integer> visited
  ) {
      if (current <= 0 ||
          !graph.containsKey(current) ||
          visited.contains(current)) {
          return false;
      }
      if (current == ending) {
          return true;
      }
      visited.add(current);

      for (int neighbor : graph.get(current)) {
          if (positiveHelper(graph, neighbor, ending, visited)) {
              return true;
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
    if (person == null) {
        return false;
    }

    Set<Professional> visited = new HashSet<>();

    return companyHelper(person, companyName, visited);
    }

  private static boolean companyHelper(
      Professional current,
      String companyName,
      Set<Professional> visited) {
    if (current == null || visited.contains(current)) {
        return false;
    }
    visited.add(current);

    if (java.util.Objects.equals(current.getCompany(), companyName)) {
        return true;
    }
    for (Professional neighbor : current.getConnections()) {
        if (companyHelper(neighbor, companyName, visited)) {
            return true;
        }
    }
    return false;
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
  List<int[]> result = new ArrayList<>();

    for (int[] direction : directions) {
        int newRow = current[0] + direction[0];
        int newCol = current[1] + direction[1];

        if (newRow >= 0 && newRow < board.length &&
            newCol >= 0 && newCol < board[0].length) {

            if (board[newRow][newCol] != 'X') {
                result.add(new int[]{newRow, newCol});
            }
        }
    }

    return result;
}
}