import java.util.Comparator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    return oddVertices(starting, new HashSet<>());
  }

  private static int oddVertices(Vertex<Integer> vertex, Set<Vertex<Integer>> visited) {
    if (vertex == null || visited.contains(vertex)) return 0;
    visited.add(vertex);

    int total = 0;
    if (vertex.data % 2 != 0) total ++;

    for (Vertex<Integer> neighbor : vertex.neighbors) {
      total += oddVertices(neighbor, visited);
    }

    return total;
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
    if (starting == null) return new ArrayList<>();
    List<Integer> list = sortedReachable(starting, new HashSet<>());
    list.sort(Comparator.naturalOrder());
    return list;
  }

  private static List<Integer> sortedReachable(Vertex<Integer> current, Set<Vertex<Integer>> visited) {
    if (current == null || visited.contains(current)) return new ArrayList<>();
    visited.add(current);
    List<Integer> values = new ArrayList<>();
    visited.add(current);

    values.add(current.data);

    for (Vertex<Integer> neighbor : current.neighbors) {
      values.addAll(sortedReachable(neighbor, visited));
    }

    return values;
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
    if (!graph.keySet().contains(starting)) return new ArrayList<>();
    List<Integer> values = new ArrayList<>();
    sortedReachable(graph, starting, values);
    values.sort(Comparator.naturalOrder());
    return values;
  }

  private static void sortedReachable(Map<Integer, Set<Integer>> graph, int current, List<Integer> visited) {
    if (visited.contains(current)) return;
    visited.add(current);
    for (int neighbor : graph.get(current)) {
      sortedReachable(graph, neighbor, visited);
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
    if (v1 == null || v2 == null) return false;
    if (v1 == v2) return true;
    boolean v1Connected = twoWay(v1, v2, new HashSet());
    boolean v2Connected = twoWay(v2, v1, new HashSet());
    return v1Connected && v2Connected;
  }

  private static <T> boolean twoWay(Vertex<T> current, Vertex<T> target, Set<Vertex<T>> visited) {
    if (current == target) return true;
    visited.add(current);

    for (Vertex<T> neighbor : current.neighbors) {
      if (twoWay(neighbor, target, visited)) return true;
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
    if (!graph.keySet().contains(starting) || !graph.keySet().contains(ending) || starting < 0 || ending < 0) {
      return false;
    }
    return positivePathExists(graph, starting, ending, new HashSet<>());
  }

  private static boolean positivePathExists(Map<Integer, Set<Integer>> graph, int current, int target, Set<Integer> visited) {
    if (current == target) return true; // target = ending, and ending > 0, so no need to check for negative values
    if (visited.contains(current)) return false;
    visited.add(current);
    if (current < 0) return false;

    for (int neighbor : graph.get(current)) {
      if (positivePathExists(graph, neighbor, target, visited)) {
        return true;
      } else {
        continue;
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
    if (person == null) return false;
    return hasExtendedConnectionAtCompany(person, companyName, new HashSet<>());
  }

  private static boolean hasExtendedConnectionAtCompany(Professional person, String company, Set<Professional> visited) {
    if (person.getCompany().equals(company)) return true;
    if (visited.contains(person)) return false;
    visited.add(person);

    for (Professional connection : person.getConnections()) {
      if (hasExtendedConnectionAtCompany(connection, company, visited)) return true;
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
    int row = current[0], col = current[1];
    List<int[]> validMoves = new ArrayList<>();

    for (int i = 0; i < directions.length; i++) {
      int nRow = row + directions[i][0];
      int nCol = col + directions[i][1];

      if (nRow >= 0 && nRow < board.length && nCol >= 0 && nCol < board[nRow].length && board[nRow][nCol] != 'X') {
        validMoves.add(new int[]{nRow, nCol});
      }
    }

    return validMoves;
  }
}
