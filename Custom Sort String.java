class Solution {
    public String customSortString(String S, String T) {
        StringBuilder ans = new StringBuilder();
        if (S == null || T == null) {
            return ans.toString();
        }
        
        // 1. 統計T的字母出現次數
        int[] map = new int[26];
        for (Character c : T.toCharArray()) {
            map[c - 'a'] += 1;
        }
        
        // 2. 用指針掃S，若S中當前字母存在於MAP，則把MAP的元素都取出來，然後MAP中該元素歸0
        for (Character c : S.toCharArray()) {
            for (int i = 0; i < map[c - 'a']; i++) {
                ans.append(c);
            }
            map[c - 'a'] = 0;
        }
        
        // 3. 把map中不為0的直接append，代表S中沒出現的元素，T可以隨便擺
        for (int i = 0; i < 26; i++) {
            if (map[i] != 0) {
                for (int j = 0; j < map[i]; j++) {
                    ans.append((char)(i + 'a'));
                }
                map[i] = 0;
            }
        }
        
        return ans.toString();
    }
}