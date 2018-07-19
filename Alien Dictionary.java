class Solution {
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        
        // 統計哪些字母出現過, 以及總共有多少個character
        int count = 0;
        int[] inDegree = new int[26];
        for (String cur : words) {
            for (Character c : cur.toCharArray()) {
                if (inDegree[c - 'a'] == 0) {
                    inDegree[c - 'a'] = 1;
                    count++;
                }
            }
        }
        
        // 建圖
        Map<Character, Set<Character>> g = new HashMap<>();
        for (int i = 0; i < words.length - 1; i++) {
            char[] cur = words[i].toCharArray();
            char[] next = words[i + 1].toCharArray();
            
            int len = Math.min(cur.length, next.length);
            for (int j = 0; j < len; j++) {
                if (cur[j] != next[j]) {
                    // 如果字典中cur的當前char 和 字典中next的當前char不同，則建立cur[j] -> next[j] 的這條有向邊
                    if (!g.containsKey(cur[j])) {
                        g.put(cur[j], new HashSet<Character>());
                    }
                    
                    // 確保這次鄰居沒有被重複加入, 例如題目範例的t->f, 會被加入兩次, 只能加入一次, 因為是duplicate
                    // 用set.add的特性，當set.add成功代表原本沒有該元素，會傳true, 不然會傳false
                    if (g.get(cur[j]).add(next[j])) {
                        inDegree[next[j] - 'a']++;
                    }
                    // 如果當前的加入, 後面就沒有參考價值，因為越前面的字母序越重要, 例如cb 和da, c->d 建立後, b->a沒有參考價值不能建立
                    break;
                }
            }
        }
        
        // 做BFS, 從indegree為1的開始做(其實是indegee為0, 這邊的indegree比實際的indegree要多1, 程式寫法的關係)
        Queue<Character> q = new LinkedList<>();
        for (int i = 0; i < 26; i++) {
            if (inDegree[i] == 1) {
                q.add((char)(i + 'a'));
            }
        }
        
        for (int i = 0; i < 26; i++) {
            System.out.println((char)(i + 'a') + " " + inDegree[i]);
        }
        
        String ans = new String();
        while (!q.isEmpty()) {
            char cur = q.remove();
            ans += cur;
            // 遍歷當前點的鄰居
            Set<Character> neighborSet = g.get(cur);
            // 最後一個點，他沒有鄰居，例如題目範例的f
            if (neighborSet != null) {
                for (Character neighbor : neighborSet) {
                    if (inDegree[neighbor - 'a'] > 1) {
                        inDegree[neighbor - 'a']--;
                    }
                    // 代表沒有先修課程了(入度為0), 可以放到queue
                    if (inDegree[neighbor - 'a'] == 1) {
                        q.add(neighbor);
                    }
                }
            }
        }
        
        // 如果不能構成拓樸排序，則回傳""
        return ans.length() == count ? ans : "";
        
    }
}