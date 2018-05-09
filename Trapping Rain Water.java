class Solution {
    public int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int ans = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                leftMax = Math.max(leftMax, height[left]);
                ans = ans + (leftMax - height[left]);
                left++;
            } else {
                rightMax = Math.max(rightMax, height[right]);
                ans = ans + (rightMax - height[right]);
                right--;
            }
        }
        return ans;
    }
}