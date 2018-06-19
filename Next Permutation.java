class Solution {
    public void nextPermutation(int[] nums) {
        if (nums.length <= 1) {
            return;
        }
        
        int len = nums.length;
        
        // [1,2,8,4] , 找到swapStart = index1, 就是2這個元素
        int swapStart = -1;
        for (int i = len - 1; i >= 1; i--) {
            if (nums[i] > nums[i - 1]) {
                swapStart = i - 1;
                break;  // 取找到的最右邊那個，就是for loop右邊往左找遇到符合條件的第一個
            }
        }
        
        // 如果找不到swapStart代表遇到[3,2,1]這種，直接翻轉整個list變成[1,2,3]
        if (swapStart == -1) {
            swapList(nums, 0, len - 1);
            return;
        }
        
        // 把2這個元素和右到左數來最小的，就是4交換，變成[1,4,8,2]
        int swapEnd = -1;
        for (int i = len - 1; i > swapStart; i--) {
            if (nums[i] > nums[swapStart]) {
                swapEnd = i;
                break;
            }
        }
        swap(nums, swapStart, swapEnd);
        
        
        // 把swapStart + 1之後變成升序，[1,4,8,2]變成[1,4,2,8]
        swapList(nums, swapStart + 1, len - 1);
        
    }
    
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    public void swapList(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }
}