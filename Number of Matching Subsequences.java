// Brute Force + Cache
class Solution {
    public int numMatchingSubseq(String S, String[] words) {
        int ans = 0;
        
        // 做一個cache, 如果這個字已經之前解過, 不用重複解
        Map<String, Boolean> cache = new HashMap<>();
        
        // 掃描整個words字典, 然後針對當前word的每個char, 用binary search做查找
        for (String cur : words) {
            if (cache.containsKey(cur)) {
                ans += cache.get(cur) == true ? 1 : 0;
                continue;
            }
            if (isMatch(S, cur, cache)) {
                ans++;
            }
        }
        return ans;
    }
    
    public boolean isMatch(String S, String cur, Map<String, Boolean> cache) {
//        acbca
//            p
//        aba
        int pre = -1;
        
        // 掃cur的每個字母, 如果都存在且符合先後順序代表為S的subseq
        for (Character c : cur.toCharArray()) {
            boolean find = false;
            for (int idx = pre + 1; idx < S.length(); idx++) {
                if (c == S.charAt(idx)) {
                    // 如果找到指定char, 提前結束內迴圈
                    find = true;
                    pre = idx;
                    break;
                }
            }
            if (find == false) {
                cache.put(cur, false);
                return false;   
            }
        }
        
        cache.put(cur, true);
        return true;
    }
}

// Binary search
class Solution {
    public int numMatchingSubseq(String S, String[] words) {
        int ans = 0;
        // 把S做preprocessing, 把每個字母出現的index放到map array中, 方便之後用binary search做查找
        Map<Character, List<Integer>> map = new HashMap<>();
        int i = 0;
        for (Character cur : S.toCharArray()) {
            if (!map.containsKey(cur)) {
                map.put(cur, new ArrayList<Integer>());
            }
            map.get(cur).add(i++);
        }
        /*
        for (Map.Entry<Character, List<Integer>> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        */
        // 掃描整個words字典, 然後針對當前word的每個char, 用binary search做查找
        for (String cur : words) {
            if (isMatch(S, cur, map)) {
                ans++;
            }
        }
        return ans;
    }
    
    public boolean isMatch(String S, String curWord, Map<Character, List<Integer>> map) {
        // 定義pre為當前的lowerBound index
        int pre = -1;
        
        for (Character c : curWord.toCharArray()) {
            // 一次針對某個char, 做binary search, 找比當前pre大的最小值(lowerBound)
            if (!map.containsKey(c)) {
                // 如果map中根本沒有這個char, 直接無法匹配
                return false;
            }
            List<Integer> curList = map.get(c);
            
            int start = 0;
            int end = curList.size() - 1;
            while (start + 1 < end) {
                int mid = (start + end) / 2;
                if (pre > curList.get(mid)) {
                    start = mid;
                } else if (pre < curList.get(mid)){
                    end = mid;
                } else {
                    // 相等的情況
                    start = mid;
                }
            }
            
            if (curList.get(start) > pre) {
                pre = curList.get(start);
            } else if (curList.get(end) > pre) {
                pre = curList.get(end);
            } else {
                return false;
            }
            
        }
        return true;
    }
}