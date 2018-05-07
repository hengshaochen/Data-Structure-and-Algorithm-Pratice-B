class Solution {
    public int findDuplicate(int[] nums) {
        // 法1: O(n^2), 固定一個數和其他的比較, 若相同就回傳該值
        // 法2: 用額外空間HashSet O(n), one-pass
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return nums[i];
            } else {
                set.add(nums[i]);
            }
        }
        // 不會執行到這裡
        return -1;
    }
}

// 法3: Time: O(n) Space: O(1) 
class Solution {
    public int findDuplicate(int[] nums) {
        // 法3: O(n), 這方法根本不可能想到，就是用找“linked-list cycle的起點“: 快滿指針，先找重合，重合後在套公式找起點
        // 因為這題規定n + 1個數字都是在1 到 n ，代表必有一個重複（鴿籠原理）
        // index:   0 1 2 3 4
        // example: 1 2 4 1 3
        // 映射關係：0->1 , 1->2 , 2->4 , 3->1 , 4->3
        // 0->1->2->4->"3"-> “cycle起點“ 1 -> 2 -> 4 ...
        
        
        // 這題nums[slow]就相當於slow.next的意思
        int slow = 0;
        int fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
            
        // 找linked-list cycle發生的起頭處
        int head = nums[0];
        while (head != nums[slow]) {
            slow = nums[slow];
            head = nums[head];
        }
        return nums[slow];
    }
}