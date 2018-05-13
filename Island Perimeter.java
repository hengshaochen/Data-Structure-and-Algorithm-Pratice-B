class Solution {
    public int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        
        int m = grid.length;
        int n = grid[0].length;
        int totalArea = 0;
        int totalAdj = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    totalArea++;
                    if (i - 1 >= 0 && grid[i - 1][j] == 1) { totalAdj++; }
                    if (i + 1 <  m && grid[i + 1][j] == 1) { totalAdj++; }
                    if (j - 1 >= 0 && grid[i][j - 1] == 1) { totalAdj++; }
                    if (j + 1 <  n && grid[i][j + 1] == 1) { totalAdj++; }
                }
            }
        }
        return totalArea * 4 - totalAdj;
    }
}