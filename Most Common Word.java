class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> bannedSet = new HashSet<String>();
        for (String cur : banned) {
            bannedSet.add(cur);
        }
        paragraph = paragraph.toLowerCase();
        Map<String, Integer> map = new HashMap<>();
        
        int i = 0;
        while (i < paragraph.length()) {
            // 如果當前字不是英文字母就skip當前char (不是符號就skip)
            if (!Character.isAlphabetic(paragraph.charAt(i))) {
                i++;
                continue;
            }
            
            // 一次讀一個字
            int end = i;
            while (end < paragraph.length() && Character.isAlphabetic(paragraph.charAt(end))) {
                // i代表start, cur代表end
                end++;
            }
            String curWord = paragraph.substring(i, end);
            if (!map.containsKey(curWord)) {
                map.put(curWord, 1);
            } else {
                map.put(curWord, map.get(curWord) + 1);
            }
            
            // 把當前i移動到當前end位置
            i = end;
        }
        
        // 要全部統計完成後才能掃一次找合法的max freq
        String ans = null;
        int maxCount = 0;
        for (String cur : map.keySet()) {
            if (!bannedSet.contains(cur) && map.get(cur) > maxCount) {
                ans = cur;
                maxCount = map.get(cur);
            }
        }
        return ans;
    }
}