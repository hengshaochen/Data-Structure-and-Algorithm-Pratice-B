class Solution {
    public int thirdMax(int[] nums) {
        Integer max1 = null, max2 = null, max3 = null;
        for (Integer cur : nums) {
            // 如果當前數字已經存在於max1,max2,max3其中一個數字中，則代表不需要更新直接skip
            if (cur.equals(max1) || cur.equals(max2) || cur.equals(max3)) {
                continue;
            }
            
            if (max1 == null || max1.compareTo(cur) < 0) {
                // 代表max1不存在 或是 max1 < 當前數字 --> 更新max1
                max3 = max2;
                max2 = max1;
                max1 = cur;
            } else if (max2 == null || max2.compareTo(cur) < 0) {
                max3 = max2;
                max2 = cur;
            } else if (max3 == null || max3.compareTo(cur) < 0) {
                max3 = cur;
            }
        }
        return max3 == null ? max1 : max3;
    }
}