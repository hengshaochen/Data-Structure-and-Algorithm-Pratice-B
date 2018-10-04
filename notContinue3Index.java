// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        new Main();
    }
    
    public Main() {
        int[] nums = {1,1,1,2,2,2,4,4,4,5};
        System.out.println(notContinue3Index(nums));
    }
    
    int notContinue3Index(int[] nums) {
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = (start + end) / 2;
            // 移動到該數字最右邊
            int right = mid;
            while (right + 1 < nums.length && nums[right] == nums[right + 1]) {
                right++;
            }
            
            System.out.println(start + " " + end + " " + right);
            if ((right + 1) % 3 == 0) {
                // 左邊沒問題，往右邊走
                start = mid;
            } else {
                // 左邊有問題，往左邊走
                end = mid;
            }
        }
        
        // 兩根指針start end相鄰，左邊往左找1個，如果相同，代表start往左是ok, 回傳end
        // start在最左邊代表start有問題
        if (start != 0 && nums[start] == nums[start - 1]) {
            return end;
        } else {
            return start;
        }
    }
}