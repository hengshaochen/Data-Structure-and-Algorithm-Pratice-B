class Solution {
    public int shortestDistance(int[][] grid) {
        int[][] dist = new int[grid.length][grid[0].length];
        
        int counter = 0;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    ans = bfs(grid, i, j, dist, counter);
                    counter--;
                }
            }
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
    
    int bfs(int[][] grid, int x, int y, int[][] dist, int counter) {
        Queue<Integer> qx = new LinkedList<>();
        Queue<Integer> qy = new LinkedList<>();
        qx.add(x);
        qy.add(y);
        int[][] localDist = new int[grid.length][grid[0].length];
        int localAns = Integer.MAX_VALUE;
        
        int step = 0;
        while (!qx.isEmpty()) {
            int qsize = qx.size();
            step++;
            for (int i = 0; i < qsize; i++) {
                int curX = qx.remove();
                int curY = qy.remove();
                
                int[] dx = {0, -1, 0, 1};
                int[] dy = {1, 0, -1, 0};

                for (int k = 0; k < 4; k++) {
                    int neighborX = curX + dx[k];
                    int neighborY = curY + dy[k];

                    if (outBound(grid, neighborX, neighborY)) {
                        continue;
                    }
                   
                    if (grid[neighborX][neighborY] == counter) {
                        localDist[neighborX][neighborY] += step;
                        grid[neighborX][neighborY] = grid[neighborX][neighborY] - 1;
                        dist[neighborX][neighborY] += localDist[neighborX][neighborY];
                        qx.add(neighborX);
                        qy.add(neighborY);
                        
                        localAns = Math.min(localAns, dist[neighborX][neighborY]);
                    }
                }
                
            }
        }
        return localAns;

    }
    boolean outBound(int[][] arr, int i, int j) {
        if (i < 0 || i >= arr.length || j < 0 || j >= arr[0].length) {
            return true;
        }
        return false;
    }
}