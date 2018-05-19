class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        
        // 用sliding window: Time Complexity: O((s.length - p.length) * 26) 
        List<Integer> ans = new ArrayList<>();
        int sLen = s.length(), pLen = p.length();
        int[] mapS = new int[26];
        int[] mapP = new int[26];
        
        // 特判Corner case
        if (sLen == 0 || pLen == 0 || sLen < pLen) {
            return ans;
        }
        
        // 先統計p
        for (char cur : p.toCharArray()) {
            mapP[cur - 'a']++;
        }
        
        // 抓前p.length的element建立mapS
        for (int i = 0; i < p.length(); i++) {
            mapS[s.charAt(i) - 'a']++;
        }
        if (isAnagrams(mapP, mapS)) {
            ans.add(0);
        }
        
        // 掃一遍s, 抓有anagrams的index
        for (int i = 0; i < sLen - pLen; i++) {
            // 定義當前i為頭char必須刪除，i + pLen為要增加的尾端char
            // 當前的window是[i+1 ~ i + pLen]
            char curDeleteHead = s.charAt(i);
            char curAddTail = s.charAt(i + pLen);
            mapS[curDeleteHead - 'a']--;
            mapS[curAddTail - 'a']++;
            
            if (isAnagrams(mapP, mapS)) {
                ans.add(i + 1);
            }
        }
        return ans;
    }
    
    boolean isAnagrams(int[] mapP, int[] mapS) {
        for (int i = 0; i < 26; i++) {
            if (mapP[i] != mapS[i]) {
                return false;
            }
        }
        return true;
    }
}