class Solution {
    public List<Integer> partitionLabels(String S) {
        List<Integer> ans = new ArrayList<>();
        if (S == null || S.length() == 0) {
            return ans;
        }
        
        // 先統計各個char出現次數
        Map<Character, Integer> map = new HashMap<>();
        for (Character c : S.toCharArray()) {
            if (!map.containsKey(c)) {
                map.put(c, 1);
            } else {
                map.put(c, map.get(c) + 1);
            }
        }
        int i = 0;
        Set<Character> set = new HashSet<>();
        while (i < S.length()) {
            int end = i;
            set.add(S.charAt(i));
            // 一次處理一個substring
            while (end < S.length() && !set.isEmpty()) {
                char curChar = S.charAt(end);
                // 把統計的該字元 - 1
                map.put(curChar, map.get(curChar) - 1);
                // 如果當前字元是次數0, 就不用加入set
                if (map.get(curChar) != 0) {
                    set.add(curChar);
                } 
                // 如果當前字元存在於set, 且已經等於0, 從set中移除
                if (set.contains(curChar) && map.get(curChar) == 0) {
                    set.remove(curChar);
                }
                end++;
            }
            ans.add(end - i);
            i = end;
        }
        return ans;
    }
}

// 法2: LeetCode
public List<Integer> partitionLabels(String S) {
        if(S == null || S.length() == 0){
            return null;
        }
        List<Integer> list = new ArrayList<>();
        int[] map = new int[26];  // record the last index of the each char

        for(int i = 0; i < S.length(); i++){
            map[S.charAt(i)-'a'] = i;
        }
        // record the end index of the current sub string
        int last = 0;
        int start = 0;
        for(int i = 0; i < S.length(); i++){
            last = Math.max(last, map[S.charAt(i)-'a']);
            if(last == i){
                list.add(last - start + 1);
                start = last + 1;
            }
        }
        return list;
    }