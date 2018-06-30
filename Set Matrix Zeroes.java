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


// Space: O(1)
class Solution {
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        // 技巧，用row col的變數來判斷第0行列是否該設為0，是否原本就是0
        boolean row = false, col = false;
        
        
        // 1. 前處理，把第0 row col先拆開來看，先看除了第0 row col之外的其他數，若有0，把該row col設為0。
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                    
                    // 代表原本第0 row or col就是0，第三步驟要用，要把原本第0 row col設為0
                    if (i == 0) { row = true; }
                    if (j == 0) { col = true; }
                }
            }
        }
            
        // 2. 前處理後，我們只要掃第0 row col, 發現為0, 就把該row col全部設為0就好
        for (int i = 1; i < n; i++) {
            if (matrix[0][i] == 0) {
                for (int j = 1; j < m; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
        for (int i = 1; i < m; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < n; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
            
        // 3. 但是第一步驟我們把第0 row col先拆開了，現在要補回來，若row col為true, 則把該row or col都設為0
        if (row == true) {
            for (int i = 0; i < n; i++) {
                matrix[0][i] = 0;
            }
        }
        if (col == true) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}