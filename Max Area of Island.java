class Solution {
    class Coord {
        int x, y;
        
        Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public int maxAreaOfIsland(int[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        
        int max = 0;
        
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    max = Math.max(max, bfs(grid, max, i, j));
                }
            }
        }
        
        return max;
    }
    
    public int bfs(int[][] grid, int max, int i, int j) {
        Queue<Coord> q = new LinkedList<>();
        
        int count = 0;
        q.add(new Coord(i, j));
        
        while (!q.isEmpty()) {
            Coord cur = q.remove();
            grid[cur.x][cur.y] = 0;
            count++;
            
            int[] dx = {0, -1, 0, 1};
            int[] dy = {1, 0, -1, 0};
            
            for (int k = 0; k < 4; k++) {
                Coord neighbor = new Coord(cur.x + dx[k], cur.y + dy[k]);
                if (outOfBoundary(grid, neighbor) || grid[neighbor.x][neighbor.y] == 0) {
                    continue;
                }
                q.add(neighbor);
                grid[neighbor.x][neighbor.y] = 0;
            }
        }
        
        // update if count is larger than max
        return count;
    }
    
    public boolean outOfBoundary(int[][] grid, Coord point) {
        if (point.x < 0 || point.y < 0 || point.x >= grid.length || point.y >= grid[0].length) {
            return true;
        }
        return false;
    }
    
}