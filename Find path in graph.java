// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        new Main();
    }
    
    public Main() {
        int[][] map = { {1, 1, 1, 0, 0},
                        {1, 1, 0, 0, 1},
                        {1, 0, 1, 1, 0},
                        {0, 0, 1, 1, 1},
                        {0, 1, 0, 1, 1} };
        
        int start = 0;
        int end = 4;
        
        List<List<Integer>> ans = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        List<Integer> path = new ArrayList<>();
        visited.add(start);
        path.add(start);
        findPath(map, start, end, start, visited, path, ans);
        
        System.out.println(ans);
    }
    
    void findPath(int[][] map, int start, int end, int cur, 
                  Set<Integer> visited, List<Integer> path, List<List<Integer>> ans) {
        // exit
        if (cur == end) {
            ans.add(new ArrayList<>(path));
            return;
        }
        
        for (int neighbor = 0; neighbor < map[0].length; neighbor++) {
            if (map[cur][neighbor] != 1 || neighbor == cur 
                || visited.contains(neighbor)) {
                continue;
            }
            
            path.add(neighbor);
            visited.add(neighbor);
            findPath(map, start, end, neighbor, visited, path, ans);
            path.remove(path.size() - 1);
            visited.remove(neighbor);
        }
    }
}

// Adj List
// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        new Main();
    }
    
    public Main() {
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        List<Integer> list2 = new ArrayList<>();
        list2.add(0);
        list2.add(3);
        List<Integer> list3 = new ArrayList<>();
        list3.add(0);
        list3.add(4);
        List<Integer> list4 = new ArrayList<>();
        list4.add(2);
        list4.add(4);
        List<Integer> list5 = new ArrayList<>();
        list5.add(1);
        list5.add(3);
        map.put(0, list);
        map.put(2, list2);
        map.put(1, list3);
        map.put(3, list4);
        map.put(4, list5);
        
        int start = 0;
        int end = 4;
        
        List<List<Integer>> ans = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        List<Integer> path = new ArrayList<>();
        visited.add(start);
        path.add(start);
        findPath(map, start, end, start, visited, path, ans);
        
        System.out.println(ans);
    }
    
    void findPath(Map<Integer, List<Integer>> map, int start, int end, int cur, 
                  Set<Integer> visited, List<Integer> path, List<List<Integer>> ans) {
        // exit
        if (cur == end) {
            ans.add(new ArrayList<>(path));
            return;
        }
        
        for (Integer neighbor : map.get(cur)) {
            if (visited.contains(neighbor)) {
                continue;
            }
            
            path.add(neighbor);
            visited.add(neighbor);
            findPath(map, start, end, neighbor, visited, path, ans);
            path.remove(path.size() - 1);
            visited.remove(neighbor);
        }
    }
}