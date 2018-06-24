// Prefix 知識點

// Maximum Subarray sum
class Solution {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int prefix = 0;
        int minPrefix = 0;
        int ans = nums[0];
        
        for (int i = 0; i < nums.length; i++) {
            prefix += nums[i];
            ans = Math.max(ans, prefix - minPrefix);
            minPrefix = Math.min(minPrefix, prefix);
        }
        return ans;
    }
}

// Subarray Sum EQUAL to zero
public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public List<Integer> subarraySum(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        int prefix = 0;
        
        for (int i = 0; i < nums.length; i++) {
            prefix += nums[i];
            
            if (map.containsKey(prefix)) {
                ans.add(map.get(prefix));
                ans.add(i);
                return ans;
            } else {
                map.put(prefix, i + 1);
            }
        }
        return ans;
    }
}

// Subarray sum CLOSET to zero
class Pair {
    int prefix, prefixIndex;
    public Pair(int prefix, int prefixIndex) {
        this.prefix = prefix;
        this.prefixIndex = prefixIndex;
    }
}
public class Solution {
    /*
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public int[] subarraySumClosest(int[] nums) {
        int len = nums.length;
        Pair[] sums = new Pair[len + 1];  // 因為包含prefixIndex = 0，會多一個
        sums[0] = new Pair(0, 0);
        int curPrefix = 0;
        
        // Step1
        // 算prefix pair
        for (int i = 0; i < len; i++) {
            curPrefix += nums[i];
            sums[i + 1] = new Pair(curPrefix, i + 1);
        }
        
        // Step2
        // 依照prefix來排序
        Comparator<Pair> cmp = new Comparator<Pair>() {
            public int compare(Pair p1, Pair p2) {
                return p1.prefix - p2.prefix;
            }
        };
        Arrays.sort(sums, cmp);
        
        // Step3
        // 求兩兩相鄰的數的相差絕對值
        int[] ansIdx = new int[2];
        int closetToZero = Integer.MAX_VALUE;
        for (int i = 0; i < sums.length - 1; i++) {
            int cur = Math.abs(sums[i].prefix - sums[i + 1].prefix);
            if (cur < closetToZero) {
                if (sums[i].prefixIndex < sums[i + 1].prefixIndex) {
                    ansIdx[0] = sums[i].prefixIndex;
                    ansIdx[1] = sums[i + 1].prefixIndex - 1;
                } else {
                    ansIdx[0] = sums[i + 1].prefixIndex;
                    ansIdx[1] = sums[i].prefixIndex - 1;
                }
                closetToZero = cur;
            }
        }
        return ansIdx;
    }
}