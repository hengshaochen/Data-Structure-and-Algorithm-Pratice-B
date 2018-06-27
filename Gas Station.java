class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int total = 0, sum = 0;
        int start = 0;
        // 這個O(n)算法是基於數學定理：當數組總和 > 0代表一定能找到一個起點，可以繞一圈為正
        for (int i = 0; i < gas.length; i++) {
            total = total + (gas[i] - cost[i]);
            sum =     sum + (gas[i] - cost[i]);
            
            // 如果當前sum < 0, 代表先前的點都不能當起點走到終點，直接嘗試下一個
            if (sum < 0) {
                start = i + 1;
                sum = 0;
            }
        }
        
        if (total < 0) {
            return -1;
        }
        return start;
    }
}