import java.util.*;

public class Practice {

//Revamped after extra practice with it last time.

  // =========================================================================
  // 1. oddVertices
  // =========================================================================

  /**
   * Returns the count of vertices with odd values that can be reached from the given starting vertex.
   * The starting vertex is included in the count if its value is odd.
   * If the starting vertex is null, returns 0.
   */
  public static int oddVertices(Vertex<Integer> starting) {
    // --- VERSION 1: Recursive DFS (ACTIVE) ---
    if (starting == null) {
      return 0;
    }
    Set<Vertex<Integer>> visited = new HashSet<>();
    return oddVerticesRecursiveHelper(starting, visited);

    /* --- VERSION 2: Iterative DFS (COMMENTED OUT) ---
    if (starting == null) return 0;
    Set<Vertex<Integer>> visited = new HashSet<>();
    Deque<Vertex<Integer>> stack = new ArrayDeque<>();
    int count = 0;

    stack.push(starting);
    while (!stack.isEmpty()) {
      Vertex<Integer> curr = stack.pop();
      if (visited.add(curr)) {
        if (curr.data != null && curr.data % 2 != 0) {
          count++;
        }
        if (curr.neighbors != null) {
          for (Vertex<Integer> neighbor : curr.neighbors) {
            if (neighbor != null && !visited.contains(neighbor)) {
              stack.push(neighbor);
            }
          }
        }
      }
    }
    return count;
    */

    /* --- VERSION 3: Iterative BFS (COMMENTED OUT) ---
    if (starting == null) return 0;
    Set<Vertex<Integer>> visited = new HashSet<>();
    Queue<Vertex<Integer>> queue = new LinkedList<>();
    int count = 0;

    queue.offer(starting);
    visited.add(starting);

    while (!queue.isEmpty()) {
      Vertex<Integer> curr = queue.poll();
      if (curr.data != null && curr.data % 2 != 0) {
        count++;
      }
      if (curr.neighbors != null) {
        for (Vertex<Integer> neighbor : curr.neighbors) {
          if (neighbor != null && visited.add(neighbor)) {
            queue.offer(neighbor);
          }
        }
      }
    }
    return count;
    */
  }

  private static int oddVerticesRecursiveHelper(Vertex<Integer> curr, Set<Vertex<Integer>> visited) {
    if (curr == null || visited.contains(curr)) {
      return 0;
    }
    visited.add(curr);
    int count = (curr.data != null && curr.data % 2 != 0) ? 1 : 0;
    if (curr.neighbors != null) {
      for (Vertex<Integer> neighbor : curr.neighbors) {
        count += oddVerticesRecursiveHelper(neighbor, visited);
      }
    }
    return count;
  }


  // =========================================================================
  // 2. sortedReachable (Vertex-based)
  // =========================================================================

  /**
   * Returns a sorted list of all values reachable from the starting vertex (including starting).
   */
  public static List<Integer> sortedReachable(Vertex<Integer> starting) {
    // --- VERSION 1: Recursive DFS (ACTIVE) ---
    List<Integer> result = new ArrayList<>();
    if (starting == null) {
      return result;
    }
    Set<Vertex<Integer>> visited = new HashSet<>();
    sortedReachableRecursiveHelper(starting, visited, result);
    Collections.sort(result);
    return result;

    /* --- VERSION 2: Iterative DFS (COMMENTED OUT) ---
    List<Integer> result = new ArrayList<>();
    if (starting == null) return result;
    Set<Vertex<Integer>> visited = new HashSet<>();
    Deque<Vertex<Integer>> stack = new ArrayDeque<>();

    stack.push(starting);
    while (!stack.isEmpty()) {
      Vertex<Integer> curr = stack.pop();
      if (visited.add(curr)) {
        result.add(curr.data);
        if (curr.neighbors != null) {
          for (Vertex<Integer> neighbor : curr.neighbors) {
            if (neighbor != null && !visited.contains(neighbor)) {
              stack.push(neighbor);
            }
          }
        }
      }
    }
    Collections.sort(result);
    return result;
    */

    /* --- VERSION 3: Iterative BFS (COMMENTED OUT) ---
    List<Integer> result = new ArrayList<>();
    if (starting == null) return result;
    Set<Vertex<Integer>> visited = new HashSet<>();
    Queue<Vertex<Integer>> queue = new LinkedList<>();

    queue.offer(starting);
    visited.add(starting);

    while (!queue.isEmpty()) {
      Vertex<Integer> curr = queue.poll();
      result.add(curr.data);
      if (curr.neighbors != null) {
        for (Vertex<Integer> neighbor : curr.neighbors) {
          if (neighbor != null && visited.add(neighbor)) {
            queue.offer(neighbor);
          }
        }
      }
    }
    Collections.sort(result);
    return result;
    */
  }

  private static void sortedReachableRecursiveHelper(Vertex<Integer> curr, Set<Vertex<Integer>> visited, List<Integer> result) {
    if (curr == null || visited.contains(curr)) {
      return;
    }
    visited.add(curr);
    result.add(curr.data);
    if (curr.neighbors != null) {
      for (Vertex<Integer> neighbor : curr.neighbors) {
        sortedReachableRecursiveHelper(neighbor, visited, result);
      }
    }
  }


  // =========================================================================
  // 3. sortedReachable (Map-based)
  // =========================================================================

  /**
   * Returns a sorted list of all values reachable from the given starting vertex in the map graph.
   */
  public static List<Integer> sortedReachable(Map<Integer, Set<Integer>> graph, int starting) {
    // --- VERSION 1: Recursive DFS (ACTIVE) ---
    List<Integer> result = new ArrayList<>();
    if (graph == null || !graph.containsKey(starting)) {
      return result;
    }
    Set<Integer> visited = new HashSet<>();
    sortedReachableMapRecursiveHelper(starting, graph, visited, result);
    Collections.sort(result);
    return result;

    /* --- VERSION 2: Iterative DFS (COMMENTED OUT) ---
    List<Integer> result = new ArrayList<>();
    if (graph == null || !graph.containsKey(starting)) return result;
    Set<Integer> visited = new HashSet<>();
    Deque<Integer> stack = new ArrayDeque<>();

    stack.push(starting);
    while (!stack.isEmpty()) {
      int curr = stack.pop();
      if (visited.add(curr)) {
        result.add(curr);
        Set<Integer> neighbors = graph.get(curr);
        if (neighbors != null) {
          for (int neighbor : neighbors) {
            if (!visited.contains(neighbor)) {
              stack.push(neighbor);
            }
          }
        }
      }
    }
    Collections.sort(result);
    return result;
    */

    /* --- VERSION 3: Iterative BFS (COMMENTED OUT) ---
    List<Integer> result = new ArrayList<>();
    if (graph == null || !graph.containsKey(starting)) return result;
    Set<Integer> visited = new HashSet<>();
    Queue<Integer> queue = new LinkedList<>();

    queue.offer(starting);
    visited.add(starting);

    while (!queue.isEmpty()) {
      int curr = queue.poll();
      result.add(curr);
      Set<Integer> neighbors = graph.get(curr);
      if (neighbors != null) {
        for (int neighbor : neighbors) {
          if (visited.add(neighbor)) {
            queue.offer(neighbor);
          }
        }
      }
    }
    Collections.sort(result);
    return result;
    */
  }

  private static void sortedReachableMapRecursiveHelper(int curr, Map<Integer, Set<Integer>> graph, Set<Integer> visited, List<Integer> result) {
    if (visited.contains(curr)) {
      return;
    }
    visited.add(curr);
    result.add(curr);
    Set<Integer> neighbors = graph.get(curr);
    if (neighbors != null) {
      for (int neighbor : neighbors) {
        sortedReachableMapRecursiveHelper(neighbor, graph, visited, result);
      }
    }
  }


  // =========================================================================
  // 4. twoWay
  // =========================================================================

  /**
   * Returns true if it is possible both to reach v2 from v1 and v1 from v2.
   */
  public static <T> boolean twoWay(Vertex<T> v1, Vertex<T> v2) {
    // --- VERSION 1: Recursive DFS (ACTIVE) ---
    if (v1 == null || v2 == null) {
      return false;
    }
    return canReach(v1, v2, new HashSet<>()) && canReach(v2, v1, new HashSet<>());

    /* --- VERSION 2: Iterative DFS (COMMENTED OUT) ---
    if (v1 == null || v2 == null) return false;
    return canReachIterativeDFS(v1, v2) && canReachIterativeDFS(v2, v1);
    */

    /* --- VERSION 3: Iterative BFS (COMMENTED OUT) ---
    if (v1 == null || v2 == null) return false;
    return canReachIterativeBFS(v1, v2) && canReachIterativeBFS(v2, v1);
    */
  }

  private static <T> boolean canReach(Vertex<T> start, Vertex<T> target, Set<Vertex<T>> visited) {
    if (start == target) {
      return true;
    }
    visited.add(start);
    if (start.neighbors != null) {
      for (Vertex<T> neighbor : start.neighbors) {
        if (neighbor != null && !visited.contains(neighbor)) {
          if (canReach(neighbor, target, visited)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  private static <T> boolean canReachIterativeDFS(Vertex<T> start, Vertex<T> target) {
    if (start == target) return true;
    Set<Vertex<T>> visited = new HashSet<>();
    Deque<Vertex<T>> stack = new ArrayDeque<>();
    stack.push(start);

    while (!stack.isEmpty()) {
      Vertex<T> curr = stack.pop();
      if (curr == target) return true;
      if (visited.add(curr)) {
        if (curr.neighbors != null) {
          for (Vertex<T> neighbor : curr.neighbors) {
            if (neighbor != null && !visited.contains(neighbor)) {
              stack.push(neighbor);
            }
          }
        }
      }
    }
    return false;
  }

  private static <T> boolean canReachIterativeBFS(Vertex<T> start, Vertex<T> target) {
    if (start == target) return true;
    Set<Vertex<T>> visited = new HashSet<>();
    Queue<Vertex<T>> queue = new LinkedList<>();
    queue.offer(start);
    visited.add(start);

    while (!queue.isEmpty()) {
      Vertex<T> curr = queue.poll();
      if (curr == target) return true;
      if (curr.neighbors != null) {
        for (Vertex<T> neighbor : curr.neighbors) {
          if (neighbor != null && visited.add(neighbor)) {
            queue.offer(neighbor);
          }
        }
      }
    }
    return false;
  }


  // =========================================================================
  // 5. positivePathExists
  // =========================================================================

  /**
   * Returns whether there exists a path from starting to ending vertex including only positive values.
   */
  public static boolean positivePathExists(Map<Integer, Set<Integer>> graph, int starting, int ending) {
    // --- VERSION 1: Recursive DFS (ACTIVE) ---
    if (graph == null || starting <= 0 || ending <= 0 || !graph.containsKey(starting) || !graph.containsKey(ending)) {
      return false;
    }
    return positivePathRecursive(starting, ending, graph, new HashSet<>());

    /* --- VERSION 2: Iterative DFS (COMMENTED OUT) ---
    if (graph == null || starting <= 0 || ending <= 0 || !graph.containsKey(starting) || !graph.containsKey(ending)) {
      return false;
    }
    Set<Integer> visited = new HashSet<>();
    Deque<Integer> stack = new ArrayDeque<>();
    stack.push(starting);

    while (!stack.isEmpty()) {
      int curr = stack.pop();
      if (curr == ending) return true;
      if (visited.add(curr)) {
        Set<Integer> neighbors = graph.get(curr);
        if (neighbors != null) {
          for (int neighbor : neighbors) {
            if (neighbor > 0 && !visited.contains(neighbor)) {
              stack.push(neighbor);
            }
          }
        }
      }
    }
    return false;
    */

    /* --- VERSION 3: Iterative BFS (COMMENTED OUT) ---
    if (graph == null || starting <= 0 || ending <= 0 || !graph.containsKey(starting) || !graph.containsKey(ending)) {
      return false;
    }
    Set<Integer> visited = new HashSet<>();
    Queue<Integer> queue = new LinkedList<>();
    queue.offer(starting);
    visited.add(starting);

    while (!queue.isEmpty()) {
      int curr = queue.poll();
      if (curr == ending) return true;
      Set<Integer> neighbors = graph.get(curr);
      if (neighbors != null) {
        for (int neighbor : neighbors) {
          if (neighbor > 0 && visited.add(neighbor)) {
            queue.offer(neighbor);
          }
        }
      }
    }
    return false;
    */
  }

  private static boolean positivePathRecursive(int curr, int ending, Map<Integer, Set<Integer>> graph, Set<Integer> visited) {
    if (curr == ending) {
      return true;
    }
    visited.add(curr);
    Set<Integer> neighbors = graph.get(curr);
    if (neighbors != null) {
      for (int neighbor : neighbors) {
        if (neighbor > 0 && !visited.contains(neighbor)) {
          if (positivePathRecursive(neighbor, ending, graph, visited)) {
            return true;
          }
        }
      }
    }
    return false;
  }


  // =========================================================================
  // 6. hasExtendedConnectionAtCompany
  // =========================================================================

  /**
   * Returns true if a professional has anyone in their extended network that works for the given company.
   */
  public static boolean hasExtendedConnectionAtCompany(Professional person, String companyName) {
    // --- VERSION 1: Recursive DFS (ACTIVE) ---
    if (person == null) {
      return false;
    }
    return hasCompanyRecursive(person, companyName, new HashSet<>());

    /* --- VERSION 2: Iterative DFS (COMMENTED OUT) ---
    if (person == null) return false;
    Set<Professional> visited = new HashSet<>();
    Deque<Professional> stack = new ArrayDeque<>();
    stack.push(person);

    while (!stack.isEmpty()) {
      Professional curr = stack.pop();
      if (curr != null && visited.add(curr)) {
        if (companyName == null ? curr.getCompany() == null : companyName.equals(curr.getCompany())) {
          return true;
        }
        if (curr.getConnections() != null) {
          for (Professional conn : curr.getConnections()) {
            if (conn != null && !visited.contains(conn)) {
              stack.push(conn);
            }
          }
        }
      }
    }
    return false;
    */

    /* --- VERSION 3: Iterative BFS (COMMENTED OUT) ---
    if (person == null) return false;
    Set<Professional> visited = new HashSet<>();
    Queue<Professional> queue = new LinkedList<>();
    queue.offer(person);
    visited.add(person);

    while (!queue.isEmpty()) {
      Professional curr = queue.poll();
      if (curr != null) {
        if (companyName == null ? curr.getCompany() == null : companyName.equals(curr.getCompany())) {
          return true;
        }
        if (curr.getConnections() != null) {
          for (Professional conn : curr.getConnections()) {
            if (conn != null && visited.add(conn)) {
              queue.offer(conn);
            }
          }
        }
      }
    }
    return false;
    */
  }

  private static boolean hasCompanyRecursive(Professional curr, String companyName, Set<Professional> visited) {
    if (curr == null || visited.contains(curr)) {
      return false;
    }
    visited.add(curr);
    if (companyName == null ? curr.getCompany() == null : companyName.equals(curr.getCompany())) {
      return true;
    }
    if (curr.getConnections() != null) {
      for (Professional conn : curr.getConnections()) {
        if (hasCompanyRecursive(conn, companyName, visited)) {
          return true;
        }
      }
    }
    return false;
  }


  // =========================================================================
  // 7. nextMoves
  // =========================================================================

  /**
   * Returns a list of possible next moves starting from a given position.
   */
  public static List<int[]> nextMoves(char[][] board, int[] current, int[][] directions) {
    List<int[]> moves = new ArrayList<>();
    if (board == null || board.length == 0 || current == null || directions == null) {
      return moves;
    }

    int rows = board.length;
    int cols = board[0].length;
    int startRow = current[0];
    int startCol = current[1];

    for (int[] dir : directions) {
      int nextRow = startRow + dir[0];
      int nextCol = startCol + dir[1];

      if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols) {
        if (board[nextRow][nextCol] != 'X') {
          moves.add(new int[]{nextRow, nextCol});
        }
      }
    }

    return moves;
  }
}