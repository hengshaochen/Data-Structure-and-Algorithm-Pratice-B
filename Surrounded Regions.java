class Solution {
    int[] dx = {0, -1, 0, 1};
    int[] dy = {1, 0, -1, 0};
    public void solve(char[][] board) {
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O') {
                    bfs(board, i, j);
                }
            }
        }
        
        // 全部跑完再把T還原回O，防止重複走訪這樣可以保證每個點最多走1次
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'T') {
                    board[i][j] = 'O';
                }
            }
        }
    }
    
    // 如果這次bfs全部沒有nextToBound, 回傳false, 代表可以變成X / 如果有nextToBound, 回傳true, 代表要變成三角形
    void bfs(char[][] board, int x, int y) {
        boolean existNextToBound = false;
        Queue<int[]> q = new LinkedList<>();
        Queue<int[]> visited = new LinkedList<>();
        
        q.add(new int[]{x, y});
        visited.add(new int[]{x, y});
        board[x][y] = 'T';
        
        while (!q.isEmpty()) {
            int[] cur = q.remove();
            if (nextToBoundary(board, cur[0], cur[1])) {
                existNextToBound = true;
            }
            
            for (int i = 0; i < 4; i++) {
                int xNeighbor = cur[0] + dx[i];
                int yNeighbor = cur[1] + dy[i];
                if (!outOfBoundary(board, xNeighbor, yNeighbor) && board[xNeighbor][yNeighbor] == 'O') {
                    q.add(new int[]{xNeighbor, yNeighbor});
                    visited.add(new int[]{xNeighbor, yNeighbor});
                    board[xNeighbor][yNeighbor] = 'T';
                }
            }
        }
        
        // bfs走完，如果nextBound是false，變成X
        if (existNextToBound == false) {
            while (!visited.isEmpty()) {
                int[] curCoord = visited.remove();
                board[curCoord[0]][curCoord[1]] = 'X';
            }
        }
    }
    
    boolean nextToBoundary(char[][] board, int x, int y) {
        for (int i = 0; i < 4; i++) {
            if (x + dx[i] < 0 || x + dx[i] >= board.length ||
                y + dy[i] < 0 || y + dy[i] >= board[0].length) {
                return true;
            }
        }
        return false;
    }
    
    boolean outOfBoundary(char[][] board, int x, int y) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
            return true;
        }
        return false;
    }
}