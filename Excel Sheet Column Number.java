class Solution {
    public int titleToNumber(String s) {
        int ans = 0;
        int i = 1;
        for (Character cur : s.toCharArray()) {
            int curASCII = Character.toUpperCase(cur) - 'A' + 1;
            ans += curASCII * Math.pow(26, s.length() - i);
            i++;
        }
        return ans;
    }
}