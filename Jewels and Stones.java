class Solution {
    public int numJewelsInStones(String J, String S) {
        HashSet<Character> set = new HashSet<>();
        
        for (Character c : J.toCharArray()) {
            set.add(c);
        }
        
        int count = 0;
        for (Character c : S.toCharArray()) {
            if (set.contains(c)) {
                count++;
            }
        }
        
        return count;
    }
}