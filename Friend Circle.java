class Solution {
    int ans = 0;
    public int findCircleNum(int[][] M) {
        if (M == null || M.length == 0) {
            return 0;
        }
        
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                if (M[i][j] == 1) {
                    ans++;
                    M[i][j] = 0;
                    bfs(M, i, j);
                }
            }
        }
        
        return ans;
    }
    
    public void bfs(int[][] M, int x, int y) {
        int[] init = new int[]{x, y};
        Queue<int[]> q = new LinkedList<>();
        q.add(init);
        
        while (!q.isEmpty()) {
            int[] cur = q.remove();
            
            for (int i = 0; i < M.length; i++) {
                if (M[cur[1]][i] == 1) {
                    q.add(new int[]{cur[0], i});
                    M[cur[1]][i] = 0;
                }
            }
        }
    }
}