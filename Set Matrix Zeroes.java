// Time: O(mn) Space: O(mn)
class Solution {
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }
        
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0 && visited[i][j] == false) {
                    dfs(matrix, visited, i, j);
                }
            }
        }
    }
    
    public void dfs(int[][] matrix, boolean[][] visited, int i, int j) {
        // 走右邊走到底
        int cur = j;
        while (cur < matrix[0].length) {
            if (matrix[i][cur] != 0) {
                matrix[i][cur] = 0;
                visited[i][cur] = true;
            }
            cur++;
        }
        
        // 走左邊走到底
        cur = j;
        while (cur >= 0) {
            if (matrix[i][cur] != 0) {
                matrix[i][cur] = 0;
                visited[i][cur] = true;
            }
            cur--;
        }
        
        // 走上面走到底
        cur = i;
        while (cur >= 0) {
            if (matrix[cur][j] != 0) {
                matrix[cur][j] = 0;
                visited[cur][j] = true;
            }
            cur--;
        }
        
        // 走下面走到底
        cur = i;
        while (cur < matrix.length) {
            if (matrix[cur][j] != 0) {
                matrix[cur][j] = 0;
                visited[cur][j] = true;
            }
            cur++;
        }
        
    }
}

