// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        new Main();
    }
    
    public Main() {
        int k = 3;
        int[] nums = {-5,-3,3,4};
        System.out.println(maxK_product(nums, k));
    }
    
    int maxK_product(int[] nums, int k) {
        Arrays.sort(nums);
        
        int len = nums.length;
        
        int[] prefix = new int[len];
        int[] postfix = new int[len];
        int sum = 1;
        for (int i = 0; i < len; i++) {
            sum *= nums[i];
            prefix[i] = sum;
        }
        sum = 1;
        for (int i = len - 1; i >= 0; i--) {
            sum *= nums[i];
            postfix[i] = sum;
        }
        
        /*
        
                System.out.println("i: " + i + " max: " + max + " left:" + (k - i - 1)
                                   + " right: " + (len - i));
        */
        
        int max = Integer.MIN_VALUE;
        if (k % 2 != 0) {
            // odd
            for (int i = 1; i <= k; i += 2) {
                int curSum = k - i - 1 >= 0 ? prefix[k - i - 1] : 1;
                curSum *= postfix[len - i];
                max = Math.max(max, curSum);
            }
        } else {
            // even
            for (int i = 0; i <= k; i += 2) {
                int curSum = k - i - 1 >= 0 ? prefix[k - i - 1] : 1;
                if (len - i < 0 || i == 0) {
                    // 當k == 0 或 k == len k
                    curSum *= 1;
                } else {
                    curSum *= postfix[len - i];
                }
                max = Math.max(max, curSum);
            }
        }
        
        return max;
    }
}