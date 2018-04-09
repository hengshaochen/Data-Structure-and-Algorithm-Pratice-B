class Solution {
    public boolean canPermutePalindrome(String s) {
        // 思路1: 窮舉O(n! * n)
        // 思路2: 用HashMap統計數量，如果每個字母都是偶數次，或是有一個字母是奇數次，就可以構成回文 O(n)
        HashMap<Character, Integer> map = new HashMap<>();
        for (Character cur : s.toCharArray()) {
            if (!map.containsKey(cur)) {
                map.put(cur, 1);
            } else {
                map.put(cur, map.get(cur) + 1);
            }
        }
        
        boolean odd = false;
        for (Character cur : map.keySet()) {
            if (map.get(cur) % 2 != 0) {
                if (odd == false) {
                    odd = true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}