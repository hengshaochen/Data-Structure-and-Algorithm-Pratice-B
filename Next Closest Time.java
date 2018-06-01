class Solution {
    public String nextClosestTime(String time) {
        // string to int
        int init = (((time.charAt(0) - '0') * 10) + (time.charAt(1) - '0')) * 60 + 
                   ((time.charAt(3) - '0') * 10) + (time.charAt(4) - '0');
        
        HashSet<Character> set = new HashSet<>();
        set.add(time.charAt(0));
        set.add(time.charAt(1));
        set.add(time.charAt(3));
        set.add(time.charAt(4));
        
        for (int i = 1; i <= 1440; i++) {
            int cur = (init + i) % 1440;
            String curTime = intToString(cur);
            if (valid(curTime, set)) {
                return curTime;
            }
        }
        
        return "";
    }
    
    public String intToString(int cur) {
        //int h = cur / 60;
        //int m = cur % 60;
        String h = String.format("%02d", cur / 60);
        String m = String.format("%02d", cur % 60);
        //String ans = String.format("%02d", String.valueOf(h)) + String.format("%02d", String.valueOf(m));
        return h + ":" + m;
    }
    
    public boolean valid(String curTime, HashSet<Character> set) {
        for (int i = 0; i <= 4; i++) {
            if (i != 2) {
                if (!set.contains(curTime.charAt(i))) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
}