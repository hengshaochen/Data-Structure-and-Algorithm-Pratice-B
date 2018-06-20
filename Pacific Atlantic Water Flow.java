class Solution {
    public List<int[]> pacificAtlantic(int[][] matrix) {
        if (matrix.length == 0) {
            return new ArrayList<>();
        }
        int n = matrix.length, m = matrix[0].length;
        
        Queue<int[]> pQueue = new LinkedList<>();
        Queue<int[]> aQueue = new LinkedList<>();
        boolean[][] canFlowToP = new boolean[n][m];
        boolean[][] canFlowToA = new boolean[n][m];
        
        // 初始化邊界那些原本就靠著海的點
        // Vertical
        for (int i = 0; i < n; i++) {
            pQueue.add(new int[]{i, 0});
            aQueue.add(new int[]{i, m - 1});
            canFlowToP[i][0] = true;
            canFlowToA[i][m - 1] = true;
        }
        // Horizontal
        for (int i = 0; i < m; i++) {
            pQueue.add(new int[]{0, i});
            aQueue.add(new int[]{n - 1, i});
            canFlowToP[0][i] = true;
            canFlowToA[n - 1][i] = true;
        }
        
        // 用bfs判斷哪些點可以由Pacific流過去（小->大） 和題目要求相反（trick，正面不好看，反過來看)
        bfs(matrix, pQueue, canFlowToP);
        // 用bfs判斷哪些點可以由Atlantic流過去（小->大） 和題目要求相反（trick，正面不好看，反過來看)
        bfs(matrix, aQueue, canFlowToA);
        
        // 把同時可以流到Pacific且Atlantic的點加入ans
        List<int[]> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (canFlowToP[i][j] && canFlowToA[i][j]) {
                    ans.add(new int[]{i, j});
                }
            }
        }
        return ans;
    }
            
    int[] dx = {0, 1, -1, 0};
    int[] dy = {1, 0, 0, -1};
    public void bfs(int[][] matrix, Queue<int[]> q, boolean[][] visited) {
        while (!q.isEmpty()) {
            int[] cur = q.remove();
            
            for (int i = 0; i < 4; i++) {
                int[] neighbor = new int[]{cur[0] + dx[i], cur[1] + dy[i]};
                // 超出邊界 或 cur的值 > neighbor 代表不能流，continue。如果已經解過可以流，不用重複解了，也可以直接continue
                if (outOfBoundary(matrix, neighbor[0], neighbor[1]) || visited[neighbor[0]][neighbor[1]] ||
                    matrix[cur[0]][cur[1]] > matrix[neighbor[0]][neighbor[1]]) {
                    continue;
                }
                // 如果可以流，設該點為visited，
                q.add(neighbor);
                visited[neighbor[0]][neighbor[1]] = true;
            }
        }
    }
    
    boolean outOfBoundary(int[][] matrix, int x, int y) {
        if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length) {
            return true;
        }
        return false;
    }
}