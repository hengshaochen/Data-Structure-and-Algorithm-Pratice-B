class Solution {
    int count = 0;
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        // 標記走過的rooms, 防止重複走訪（因為Graph中有loop)
        Set<Integer> set = new HashSet<>();
        
        if (rooms == null || rooms.size() == 0) {
            return true;
        }
        
        dfs(0, rooms, set);
        
        return count == rooms.size();
    }
    
    public void dfs(int curRoom, List<List<Integer>> rooms, Set<Integer> set) {
        // 當層要做的事, count++, 把當前點設為走過
        count++;
        set.add(curRoom);
        
        for (Integer neighbor : rooms.get(curRoom)) {
            // 代表這個鄰居已經走過
            if (set.contains(neighbor)) {
                continue;
            }
            
            // 如果當前鄰居沒走過 --> 走訪該鄰居
            dfs(neighbor, rooms, set);
        }
    }
}