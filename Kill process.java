class Solution {
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        // 思路1
        // 1. 建樹
        // 2. 掃描整棵樹，掃到要刪除的點，把那個subtree輸出
        
        // 思路2: DFS: 用遞迴不斷尋找kill的兒子 --> 複雜度 O(N^2) , 刪除N個點，每刪除一個點需要N
        // 1. 找到kill
        // 2. 在PPID找當前kill，再對應回PID得到下個要被刪除的點
        
        // 思路3: 解決上面超時，使用空間換取時間，用HashMap做一個preprocessing.
        // Time: O(N), Space: O(N)
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
        for (int i = 0; i < pid.size(); i++) {
            map.put(pid.get(i), new HashSet<Integer>());
        }
        
        for (int i = 0; i < ppid.size(); i++) {
            if (map.containsKey(ppid.get(i))) {
                map.get(ppid.get(i)).add(pid.get(i));
            }
        }
        
        List<Integer> ans = new ArrayList<>();
        helper(ans, pid, ppid, kill, map);
            
        return ans;
    }
    
    public void helper(List<Integer> ans, List<Integer> pid, List<Integer> ppid, int kill, HashMap<Integer, HashSet<Integer>> map) {
        ans.add(kill);
        //for (int i = 0; i < pid.size(); i++) {
        for (Integer adjNode : map.get(kill)) {
                helper(ans, pid, ppid, adjNode, map);
        }
    }
}