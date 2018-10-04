// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        new Main();
    }
    
    public Main() {
        int[][] a =  {{1, 2, 3},
                     {4, 5, 6},
                     {7, 8, 9}};
    
        
        int[] start = {0, 0};
        int[] end = {a.length - 1, a[0].length - 1};
        int[] cur = {0, 0};
        List<String> ans = new ArrayList<>();
        dfs(a, start, end, cur, new StringBuilder(), ans);
        System.out.println(ans);
    }
    
    void dfs(int[][] a, int[] start, int[] end, int[] cur,
                      StringBuilder path, List<String> ans) {
        path.append(a[cur[0]][cur[1]]);
        
        // base case:
        if (cur[0] == end[0] && cur[1] == end[1]) {
            ans.add(new String(path));
            return;
        }
        
        int[] right = {cur[0], cur[1] + 1};
        int[] down = {cur[0] + 1, cur[1]};
        if (right[1] < a[0].length) {
            dfs(a, start, end, right, path, ans);
            path.deleteCharAt(path.length() - 1);
        }
        if (down[0] < a.length) {
            dfs(a, start, end, down, path, ans);
            path.deleteCharAt(path.length() - 1);
        }
        
    }
}