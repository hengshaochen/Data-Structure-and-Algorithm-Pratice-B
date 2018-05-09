// 法1: 超時，例如ｎ＝３，窮舉100-999的所有，然後判斷
class Solution {
    public List<String> findStrobogrammatic(int n) {
        List<String> ans = new ArrayList<>();
        int start = (int) Math.pow(10, n - 1);
        int end = (int) Math.pow(10, n) - 1;
        for (int i = start; i <= end; i++) {
            //System.out.println(i);
            String cur = Integer.toString(i);
            if (isStrobogrammatic(cur) == true) {
                ans.add(cur);
            }
        }
        
        // 做一個特判，當只求1位數的時候，0會因為start = 10^0 = 1, start - end = 1 - 9 會漏掉0
        if (n == 1) {
            ans.add("0");
        }
        return ans;
    }
    
    public boolean isStrobogrammatic(String num) {
        // GOOGLE面試.
        // 建立一個對應表，[0->0, 1->1, 6->9, 8->8, 9->6]
        // input數字，mapping到對應表，mapping完畢後再reverse。如果遇到2,3,4,5,7就直接false，因為轉都不能轉
        HashMap<Character, Character> map = new HashMap<>();
        map.put('0','0');
        map.put('1','1');
        map.put('6','9');
        map.put('8','8');
        map.put('9','6');
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num.length(); i++) {
            if (!map.containsKey(num.charAt(i))) {
                return false;
            } else {
                sb.append(map.get(num.charAt(i)));
            }
        }
        
        sb.reverse();
        if (num.compareTo(sb.toString()) == 0) {
            return true;
        } else {
            return false;
        }
    }
}

// 法2: 優化
