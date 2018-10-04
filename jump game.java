class Solution {
    public boolean canJump(int[] nums) {
        int reach = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > reach) {
                // 代表i這點達不到
                break;
            }
            if (reach >= nums.length - 1) {
                // reach已經超過終點
                break;
            }
            reach = Math.max(reach, i + nums[i]);
        }
        return reach >= nums.length - 1;
    }
}