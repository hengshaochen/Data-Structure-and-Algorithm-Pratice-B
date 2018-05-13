class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        // 把每個string shift成a開頭的，然後用HashMap分類
        // 最後再把HashMap的結果輸出到List<List<String>>即可
        List<List<String>> ans = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();

        for (int i = 0; i < strings.length; i++) {
            String curShift = shift(strings[i]);
            if (!map.containsKey(curShift)) {
                map.put(curShift, new ArrayList<String>());
            }
            map.get(curShift).add(strings[i]);
        }
        
        // 遍歷HashMap輸出到答案中
        for (String cur : map.keySet()) {
            ans.add(map.get(cur));
        }
        
        return ans;
    }
    
    //db --> ax
    //4,1 --> 0,23
    public String shift(String s) {
        StringBuilder sb = new StringBuilder();
        int diff = s.charAt(0) - 'a';
        for (Character c : s.toCharArray()) {
            int shiftC = (c - 'a') - diff;
            if (shiftC < 0) {
                // 如果小於0，要把它環狀變成正的，例如b是1, b - 4 = 1 - 4 = -3 , -3其實要變成x
                // 26 + (-3) = 23 = x
                shiftC = 26 + shiftC;
            }
            sb.append( (char)(shiftC + 'a') );
        }
        return sb.toString();
    }
}