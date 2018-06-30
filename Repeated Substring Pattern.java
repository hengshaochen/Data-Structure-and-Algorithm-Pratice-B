// O(N^2)
class Solution {
    public boolean repeatedSubstringPattern(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        
        // Time: O(N^2)
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length() - 1; i++) {
            sb.append(s.charAt(i));
            if (s.length() % sb.length() != 0) {
                continue;
            }
            int timesToAppend = s.length() / sb.length();
            StringBuilder cur = new StringBuilder();
            for (int j = 0; j < timesToAppend; j++) {
                cur.append(sb);
            }
            
            if (s.compareTo(cur.toString()) == 0) {
                return true;
            }
        }
        
        return false;
    }
}