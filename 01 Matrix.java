class Solution {
    public int[][] updateMatrix(int[][] matrix) {
        // 類似wallsAndGates
        
        Queue<int[]> q = new LinkedList<>();
        int row = matrix.length;
        int col = matrix[0].length;
        // 初始化：把1變成無限大，0加入queue
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 1) {
                    matrix[i][j] = Integer.MAX_VALUE;
                } else {
                    q.add(new int[]{i, j});
                }
            }
        }
        
        int[] dx = {0, 1, -1, 0};
        int[] dy = {1, 0, 0, -1};
        while (!q.isEmpty()) {
            int qsize = q.size();
            for (int i = 0; i < qsize; i++) {
                int[] cur = q.remove();
                
                for (int j = 0; j < 4; j++) {
                    int[] neighbor = {cur[0] + dx[j], cur[1] + dy[j]};
                    // 當前點 + 1
                    if (outOfBoundary(matrix, neighbor[0], neighbor[1]) ||
                        matrix[cur[0]][cur[1]] + 1 >= matrix[neighbor[0]][neighbor[1]]) {
                        continue;
                    }
                    q.add(neighbor);
                    matrix[neighbor[0]][neighbor[1]] = matrix[cur[0]][cur[1]] + 1;
                }
            }
        }
        return matrix;
    }
    
    boolean outOfBoundary(int[][] matrix, int x, int y) {
        if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length) {
            return true;
        }
        return false;
    }
}