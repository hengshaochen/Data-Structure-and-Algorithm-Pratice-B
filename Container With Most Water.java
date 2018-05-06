class Solution {
    public int maxArea(int[] height) {
        // O(n^2)
        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int curArea = Math.min(height[i], height[j]) * (j - i);
                if (curArea > maxArea) {
                    maxArea = curArea;
                }
            }
        }
        return maxArea;
    }
}