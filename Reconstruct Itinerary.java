class Solution {
    public List<String> findItinerary(String[][] tickets) {
        List<String> ans = new ArrayList<>();
        if (tickets == null || tickets.length == 0) {
            return ans;
        }
        
        // 1. 把所有資料丟入Map<String, PriorityQueue<String>>中
        Map<String, PriorityQueue<String>> map = new HashMap<>();
        for (int i = 0; i < tickets.length; i++) {
            String from = tickets[i][0];
            String end = tickets[i][1];
            if (!map.containsKey(from)) {
                map.put(from, new PriorityQueue<>(1));
            }
            map.get(from).add(end);
        }
        
        postOrder("JFK", map, ans);
        
        // 最後把postorder的結果reverse
        Collections.reverse(ans);

        return ans;
    }
    
    public void postOrder(String cur, Map<String, PriorityQueue<String>> map, List<String> ans) {
        // base case, 如果有點沒有往外流的點(沒辦法到其他機場)，根本沒有建立PQ，因此直接把當前點加入即可
        if (map.get(cur) == null) {
            ans.add(cur);
            return;
        }
        
        // 先走所有鄰居
        while (!map.get(cur).isEmpty()) {
            String neighbor = map.get(cur).remove();
            postOrder(neighbor, map, ans);
        }
        
        // 再走自己
        ans.add(cur);
    }
}