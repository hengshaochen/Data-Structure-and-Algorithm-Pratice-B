class Solution {
    public void rotate(int[][] matrix) {
        if (matrix.length == 0) {
            // 避免後續matrix[0]出錯
            return;
        }
        // 對稱(symmetric) swap
        int m = matrix.length;
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                if (i != j) {
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = temp;
                }
            }
        }
        
        // 對稱的column互換
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][m - 1 - j];
                matrix[i][m - 1 - j] = temp;
            }
        }
        
    }
}