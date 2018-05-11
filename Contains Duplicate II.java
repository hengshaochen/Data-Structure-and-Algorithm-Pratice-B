class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                int diff = Math.abs(i - map.get(nums[i]));
                if (diff <= k) {
                    return true;
                }
            }
            // 更新哈希表，不管什麼case都更新，因為是左掃到右邊，之前的index一定比現在的遠
            map.put(nums[i], i);
        }
        return false;
    }
}