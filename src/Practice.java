import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import javax.print.attribute.standard.JobStateReasons;

public class Practice {

  /**
   * Returns the count of vertices with odd values that can be reached from the
   * given starting vertex.
   * The starting vertex is included in the count if its value is odd.
   * If the starting vertex is null, returns 0.
   *
   * Example:
   * Consider a graph where:
   * 5 --> 4
   * | |
   * v v
   * 8 --> 7 < -- 1
   * |
   * v
   * 9
   * 
   * Starting from 5, the odd nodes that can be reached are 5, 7, and 9.
   * Thus, given 5, the number of reachable odd nodes is 3.
   * 
   * @param starting the starting vertex (may be null)
   * @return the number of vertices with odd values reachable from the starting
   *         vertex
  //  */
  // public static int oddVertices(Vertex<Integer> starting) {
  //   HashSet<Vertex<Integer>> visited = new HashSet<>();
  //   return oddHelper(starting, visited);

  // }

  // private static int oddHelper(Vertex<Integer> current, HashSet<Vertex<Integer>> visited) {
  //   if (current == null)
  //     return 0;
  //   if (visited.contains(current))
  //     return 0;

  //   visited.add(current);
  //   int count = 0;

  //   if (current.data % 2 != 0) {
  //     count++;
  //   }

  //   for (Vertex<Integer> neighbor : current.neighbors) {
  //     count += oddHelper(neighbor, visited);
  //   }

  //   return count;
  // }

   /** Iterative DFS Version  **?
   * Returns the count of vertices with odd values that can be reached from the
   * given starting vertex.
   * The starting vertex is included in the count if its value is odd.
   * If the starting vertex is null, returns 0.
   *
   * Example:
   * Consider a graph where:
   * 5 --> 4
   * | |
   * v v
   * 8 --> 7 < -- 1
   * |
   * v
   * 9
   * 
   * Starting from 5, the odd nodes that can be reached are 5, 7, and 9.
   * Thus, given 5, the number of reachable odd nodes is 3.
   * 
   * @param starting the starting vertex (may be null)
   * @return the number of vertices with odd values reachable from the starting
   *         vertex
   */
  // public static int oddVertices(Vertex<Integer> starting) {
  //   Stack<Vertex<Integer>> stack = new Stack<>();
  //   HashSet<Vertex<Integer>> visited = new HashSet<>();
  //   stack.push(starting);


  //   int count = 0;

  //   while(!stack.isEmpty()) {
  //     Vertex<Integer> current = stack.pop();
  //     if(current == null || visited.contains(current)) continue; // skip too next neighbor
  //     visited.add(current);
  //     if(current.data % 2 != 0) {
  //       count++;
  //     }
  //     for(Vertex<Integer> neighbor : current.neighbors) {
  //         stack.push(neighbor);
  //     }
  //   }
  //   return count;

  // }

    /** Iterative BFS Version  **?
   * Returns the count of vertices with odd values that can be reached from the
   * given starting vertex.
   * The starting vertex is included in the count if its value is odd.
   * If the starting vertex is null, returns 0.
   *
   * Example:
   * Consider a graph where:
   * 5 --> 4
   * | |
   * v v
   * 8 --> 7 < -- 1
   * |
   * v
   * 9
   * 
   * Starting from 5, the odd nodes that can be reached are 5, 7, and 9.
   * Thus, given 5, the number of reachable odd nodes is 3.
   * 
   * @param starting the starting vertex (may be null)
   * @return the number of vertices with odd values reachable from the starting
   *         vertex
   */
  public static int oddVertices(Vertex<Integer> starting) {
    if(starting == null) return 0;
    Queue<Vertex<Integer>> queue = new ArrayDeque<>();
    HashSet<Vertex<Integer>> visited = new HashSet<>();
    visited.add(starting);
    queue.offer(starting);

    int count = 0;

    while(!queue.isEmpty()) {
      Vertex<Integer> current = queue.poll();
      if(current.data % 2 != 0) {
        count++;
      }
      for(Vertex<Integer> neighbor : current.neighbors) {
          if(neighbor == null || visited.contains(neighbor)) continue;
          visited.add(neighbor);
          queue.offer(neighbor);

      }
    }
    return count;

  }




  /**
   * Returns a *sorted* list of all values reachable from the starting vertex
   * (including the starting vertex itself).
   * If duplicate vertex data exists, duplicates should appear in the output.
   * If the starting vertex is null, returns an empty list.
   * They should be sorted in ascending numerical order.
   *
   * Example:
   * Consider a graph where:
   * 5 --> 8
   * | |
   * v v
   * 8 --> 2 <-- 4
   * When starting from the vertex with value 5, the output should be:
   * [2, 5, 8, 8]
   *
   * @param starting the starting vertex (may be null)
   * @return a sorted list of all reachable vertex values by
   */
  // public static List<Integer> sortedReachable(Vertex<Integer> starting) {
  //   HashSet<Vertex<Integer>> visited = new HashSet<>();
  //   List<Integer> result = new ArrayList<>();
  //   sortedHelper(starting, visited, result);
  //   Collections.sort(result);
  //   return result;
  // }

  // private static void sortedHelper(Vertex<Integer> current, HashSet<Vertex<Integer>> visited, List<Integer> result) {
  //   if (current == null)
  //     return;
  //   if (visited.contains(current))
  //     return;

  //   visited.add(current);
  //   result.add(current.data);

  //   for (Vertex<Integer> neighbor : current.neighbors) {
  //     sortedHelper(neighbor, visited, result);
  //   }
  // }

  // /** Iterative DFS **
  //  * Returns a *sorted* list of all values reachable from the starting vertex
  //  * (including the starting vertex itself).
  //  * If duplicate vertex data exists, duplicates should appear in the output.
  //  * If the starting vertex is null, returns an empty list.
  //  * They should be sorted in ascending numerical order.
  //  *
  //  * Example:
  //  * Consider a graph where:
  //  * 5 --> 8
  //  * | |
  //  * v v
  //  * 8 --> 2 <-- 4
  //  * When starting from the vertex with value 5, the output should be:
  //  * [2, 5, 8, 8]
  //  *
  //  * @param starting the starting vertex (may be null)
  //  * @return a sorted list of all reachable vertex values by
  //  */
  // public static List<Integer> sortedReachable(Vertex<Integer> starting) {
  //   if(starting == null ) return new ArrayList<>();
  //   Stack<Vertex<Integer>> stack = new Stack<>();
  //   HashSet<Vertex<Integer>> visited = new HashSet<>();
  //   List<Integer> result = new ArrayList<>();
  //   stack.push(starting);
    
  //   while(!stack.isEmpty()) {
  //     Vertex<Integer> current = stack.pop();
  //     if(current == null || visited.contains(current)) continue; // skip
  //      visited.add(current);
  //      result.add(current.data);
  //      for(Vertex<Integer> neighbor : current.neighbors) {
  //       stack.push(neighbor);
  //      }

  //   }
  //   Collections.sort(result);
  //   return result;
  // }


  /** Iterative BFS **
   * Returns a *sorted* list of all values reachable from the starting vertex
   * (including the starting vertex itself).
   * If duplicate vertex data exists, duplicates should appear in the output.
   * If the starting vertex is null, returns an empty list.
   * They should be sorted in ascending numerical order.
   *
   * Example:
   * Consider a graph where:
   * 5 --> 8
   * | |
   * v v
   * 8 --> 2 <-- 4
   * When starting from the vertex with value 5, the output should be:
   * [2, 5, 8, 8]
   *
   * @param starting the starting vertex (may be null)
   * @return a sorted list of all reachable vertex values by
   */
  public static List<Integer> sortedReachable(Vertex<Integer> starting) {
    if(starting == null ) return new ArrayList<>();
    Queue<Vertex<Integer>> queue = new ArrayDeque<>();
    HashSet<Vertex<Integer>> visited = new HashSet<>();
    List<Integer> result = new ArrayList<>();
    visited.add(starting);
    queue.offer(starting);
    
    while(!queue.isEmpty()) {
      Vertex<Integer> current = queue.poll();
       result.add(current.data);
       for(Vertex<Integer> neighbor : current.neighbors) {
        if(neighbor == null || visited.contains(neighbor)) continue; // skip
        visited.add(neighbor);
        queue.offer(neighbor);
       }
    }
    Collections.sort(result);
    return result;
  }

  /**
   * Returns a sorted list of all values reachable from the given starting vertex
   * in the provided graph.
   * The graph is represented as a map where each key is a vertex and its
   * corresponding value is a set of neighbors.
   * It is assumed that there are no duplicate vertices.
   * If the starting vertex is not present as a key in the map, returns an empty
   * list.
   *
   * @param graph    a map representing the graph
   * @param starting the starting vertex value
   * @return a sorted list of all reachable vertex values
   */
  public static List<Integer> sortedReachable(Map<Integer, Set<Integer>> graph, int starting) {
    HashSet<Integer> visited = new HashSet<>();
    List<Integer> result = new ArrayList<>();
    if (!graph.containsKey(starting))
      return result;

    sortedHelper(graph, starting, result, visited);

    Collections.sort(result);
    return result;
  }

  private static void sortedHelper(Map<Integer, Set<Integer>> graph, int current, List<Integer> result,
      HashSet<Integer> visited) {
    if (visited.contains(current))
      return;

    visited.add(current);
    result.add(current);

    if (graph.containsKey(current)) {
      for (Integer neighbor : graph.get(current)) {
        sortedHelper(graph, neighbor, result, visited);
      }
    }
  }

  /**
   * Returns true if and only if it is possible both to reach v2 from v1 and to
   * reach v1 from v2.
   * A vertex is always considered reachable from itself.
   * If either v1 or v2 is null or if one cannot reach the other, returns false.
   *
   * Example:
   * If v1 and v2 are connected in a cycle, the method should return true.
   * If v1 equals v2, the method should also return true.
   *
   * @param <T> the type of data stored in the vertex
   * @param v1  the starting vertex
   * @param v2  the target vertex
   * @return true if there is a two-way connection between v1 and v2, false
   *         otherwise
   */
  public static <T> boolean twoWay(Vertex<T> v1, Vertex<T> v2) {
    if (v1 == null || v2 == null)
      return false;
    HashSet<Vertex<T>> visited1 = new HashSet<>();
    HashSet<Vertex<T>> visited2 = new HashSet<>();
    return twoWayHelper(visited1, v1, v2) && twoWayHelper(visited2, v2, v1);
  }

  private static <T> boolean twoWayHelper(HashSet<Vertex<T>> visited, Vertex<T> current, Vertex<T> target) {
    if (current == null)
      return false;
    if (current == target)
      return true;
    if (visited.contains(current))
      return false;

    visited.add(current);

    for (Vertex<T> neighbor : current.neighbors) {
      if (twoWayHelper(visited, neighbor, target)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns whether there exists a path from the starting to ending vertex that
   * includes only positive values.
   * 
   * The graph is represented as a map where each key is a vertex and each value
   * is a set of directly reachable neighbor vertices. A vertex is always
   * considered reachable from itself.
   * If the starting or ending vertex is not positive or is not present in the
   * keys of the map, or if no valid path exists,
   * returns false.
   *
   * @param graph    a map representing the graph
   * @param starting the starting vertex value
   * @param ending   the ending vertex value
   * @return whether there exists a valid positive path from starting to ending
   */
  public static boolean positivePathExists(Map<Integer, Set<Integer>> graph, int starting, int ending) {

    if (starting <= 0 || ending <= 0 || !graph.containsKey(starting) || !graph.containsKey(ending))
      return false;

    HashSet<Integer> visited = new HashSet<>();
    return pathHelper(graph, visited, starting, ending);
  }

  private static boolean pathHelper(Map<Integer, Set<Integer>> graph, HashSet<Integer> visited, int current,
      int target) {
    if (current <= 0)
      return false;
    if (current == target)
      return true;
    if (visited.contains(current))
      return false;

    visited.add(current);

    if (graph.containsKey(current)) {
      for (Integer neighbor : graph.get(current)) {
        if (pathHelper(graph, visited, neighbor, target))
          return true;
      }
    }

    return false;
  }

  /**
   * Returns true if a professional has anyone in their extended network
   * (reachable through any number of links)
   * that works for the given company. The search includes the professional
   * themself.
   * If the professional is null, returns false.
   *
   * @param person      the professional to start the search from (may be null)
   * @param companyName the name of the company to check for employment
   * @return true if a person in the extended network works at the specified
   *         company, false otherwise
   */
  public static boolean hasExtendedConnectionAtCompany(Professional person, String companyName) {

    if (person == null)
      return false;
    HashSet<Professional> visited = new HashSet<>();
    return extendedHelper(companyName, person, visited);

  }

  private static boolean extendedHelper(String companyName, Professional current, HashSet<Professional> visited) {
    if (current == null)
      return false;
    if (current.getCompany().equals(companyName))
      return true;
    if (visited.contains(current))
      return false;
    visited.add(current);

    for (Professional neighbor : current.getConnections()) {
      if (extendedHelper(companyName, neighbor, visited)) {
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
   * The directions are given as a 2D array, where each inner array is a [row,
   * column]
   * pair that describes a move. For example, if given the below array it would be
   * possible
   * to move to the right, up, down, or down/left diagonally.
   * {
   * {0, 1}, // Right
   * {-1, 0}, // Up
   * {1, 0}, // Down
   * {1, -1} // Down/left diagonal
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
   * {' ', ' ', 'X'},
   * {'X', ' ', ' '},
   * {' ', ' ', ' '}
   * }
   * 
   * current:
   * {1, 2}
   * 
   * directions:
   * {
   * {0, 1}, // Right
   * {-1, 0}, // Up
   * {1, 0}, // Down
   * {1, -1} // Down/left diagonal
   * }
   * 
   * expected output (order of list is unimportant):
   * [{2, 2}, {2, 1}]
   * 
   * Explanation:
   * The player starts at {1, 2}.
   * The four directions the player might have to go are right, up, down, and
   * down/left (based on the directions array).
   * They cannot go right because that would go off the edge of the board.
   * They cannot go up because there is an X.
   * They can go down.
   * They can go down/left.
   * The resultant list has the two possible positions.
   * 
   * 
   * You can assume the board is rectangular.
   * You can assume valid input (no nulls, properly sized arrays, current is
   * in-bounds,
   * directions only move 1 square, any row/column pairs are arrays of length 2,
   * directions are unique).
   * 
   * If there are no possible moves, the method returns an empty list.
   * 
   * @param board      a rectangular array where 'X' represent an impassible
   *                   location
   * @param current    the [row, column] starting position of the player
   * @param directions an array of [row, column] possible directions
   * @return an unsorted list of next moves
   */
  public static List<int[]> nextMoves(char[][] board, int[] current, int[][] directions) {
    List<int[]> result = new ArrayList<>();

    for (int[] dir : directions) {
      int newRow = current[0] + dir[0];
      int newCol = current[1] + dir[1];

      if (newRow >= 0 && newRow <= board.length - 1 && newCol >= 0 && newCol <= board[0].length - 1
          && board[newRow][newCol] != 'X') {
        result.add(new int[] { newRow, newCol });
      }
    }
    return result;
  }

  /**
   * Returns the number of vertices reachable from starting whose value
   * is greater than threshold. The starting vertex counts if its value
   * is greater than threshold. If starting is null, returns 0.
   *
   * Example: 5 --> 12 --> 3
   * |
   * v
   * 20
   * countAbove(5, 10) returns 2 (12 and 20)
   */
  public static int countAbove(Vertex<Integer> starting, int threshold) {
    HashSet<Vertex<Integer>> visited = new HashSet<>();
    return helper(starting, visited, threshold);
  }

  private static int helper(Vertex<Integer> current, HashSet<Vertex<Integer>> visited, int threshold) {
    if (current == null)
      return 0;
    if (visited.contains(current))
      return 0;
    visited.add(current);

    int count = 0;

    if (current.data > threshold) {
      count++;
    }

    for (Vertex<Integer> neighbors : current.neighbors) {
      count += helper(neighbors, visited, threshold);
    }

    return count;
  }

  /**
   * Returns a sorted list (ascending) of all EVEN values reachable from
   * starting, including starting itself if even. Duplicate values from
   * different vertices should all appear. If starting is null, returns
   * an empty list.
   *
   * Example: 5 --> 4 --> 10
   * | ^
   * v |
   * 4 ----------+
   * evenReachable(5) returns [4, 4, 10]
   */
  public static List<Integer> evenReachable(Vertex<Integer> starting) {
    HashSet<Vertex<Integer>> visited = new HashSet<>();
    List<Integer> result = new ArrayList<>();
    helper(starting, visited, result);
    Collections.sort(result);
    return result;
  }

  private static void helper(Vertex<Integer> current, HashSet<Vertex<Integer>> visited, List<Integer> result) {
    if (current == null)
      return;
    if (visited.contains(current))
      return;

    visited.add(current);

    if (current.data % 2 == 0) {
      result.add(current.data);
    }

    for (Vertex<Integer> neighbor : current.neighbors) {
      helper(neighbor, visited, result);
    }
  }

  /**
   * Returns the sum of all values reachable from starting, including
   * starting itself. The graph maps each vertex to its set of neighbors.
   * Assume no duplicate vertices.
   * If starting is not a key in the map, returns 0.
   *
   * Example: 3 -> {5, 7}, 5 -> {7}, 7 -> {}, 9 -> {3}
   * sumReachable(graph, 3) returns 15 (3 + 5 + 7)
   */
  public static int sumReachable(Map<Integer, Set<Integer>> graph, int starting) {
    if (!graph.containsKey(starting))
      return 0;
    HashSet<Integer> visited = new HashSet<>();
    return sumHelper(starting, visited, graph);
  }

private static int sumHelper(int current, HashSet<Integer> visited, Map<Integer, Set<Integer>> graph) {
  if (visited.contains(current))
    return 0;
  visited.add(current);

  int count = current;

  if (graph.containsKey(current)) {
    for (Integer neighbor : graph.get(current)) {
      count += sumHelper(neighbor, visited, graph);
    }
  }
  return count;
}

  /**
   * Returns true if any vertex reachable from starting (including
   * starting itself) holds data equal to the given value.
   * If starting is null, returns false.
   *
   * Example: "a" --> "b" --> "c"
   * ^ |
   * +--------------+
   * containsValue(a, "c") returns true
   * containsValue(a, "z") returns false
   */
  public static <T> boolean containsValue(Vertex<T> starting, T value) {
    HashSet<Vertex<T>> visited = new HashSet<>();
    return helper(starting, visited, value);
  }

  private static <T> boolean helper(Vertex<T> current, HashSet<Vertex<T>> visited, T value) {
    if (current == null)
      return false;
    if (visited.contains(current))
      return false;
    visited.add(current);

    if (current.data.equals(value))
      return true;

    for (Vertex<T> neighbor : current.neighbors) {
      if (helper(neighbor, visited, value)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns the smallest string (by natural String ordering) among all vertices
   * that can be reached from the given starting vertex.
   * The starting vertex is included in the search.
   * If the starting vertex is null, returns null.
   *
   * Example:
   * Consider a graph where:
   *
   *   "pear" --> "kiwi"
   *
   *   |          |
   *
   *   v          v
   *
   *   "plum" --> "fig" < -- "apple"
   *
   *   |
   *
   *   v
   *
   *   "grape"
   *
   * Starting from "pear", the reachable strings are "pear", "kiwi", "plum", "fig", and "grape".
   * Thus, given "pear", the smallest reachable string is "fig".
   * Note that "apple" is not reachable from "pear", so it is not considered.
   * @param starting the starting vertex (may be null)
   * @return the smallest string reachable from the starting vertex, or null if starting is null
   */
  public static String smallestString(Vertex<String> starting) {
    if (starting == null)
      return null;

    HashSet<Vertex<String>> visited = new HashSet<>();
    return helper(starting, visited);
  }

  private static String helper(Vertex<String> current, HashSet<Vertex<String>> visited) {
    if (current == null)
      return null;
    if (visited.contains(current))
      return null;

    visited.add(current);
    String smallestStr = current.data;

    for (Vertex<String> neighbor : current.neighbors) {
      String neighborStr = helper(neighbor, visited);
      if (neighborStr != null && neighborStr.compareTo(smallestStr) < 0) {
        smallestStr = neighborStr;
      }
    }

    return smallestStr;
  }
}