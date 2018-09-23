// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        // int[] nums =  {0,1, 3, 4, 5, 6, 7};
        int[] nums = {0,1,3};
           
        int i = 0, j = nums.length - 1;
        while (i + 1 < j) {
            int mid = (i + j) / 2;
            if (nums[mid] == mid) {
                // 往右
                i = mid;
            } else {
                // 往左
                j = mid;
            }
        }
        
        if (nums[i] != i) {
            System.out.println(i);
        } else if (nums[j] != j) {
            System.out.println(j);
        } else {
            System.out.println(j + 1);
        }
    }
}