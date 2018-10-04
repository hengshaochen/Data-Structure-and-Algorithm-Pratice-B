// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        int[] nums = {3, 1, 7, 5 ,5, 2, 7};
        int max = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;
        for (Integer num : nums) {
            if (num > max) {
                secondMax = max;
                max = num;
            } else if (num > secondMax && max != num) {
                // max != num這個條件是，如果max有重複兩個7, 那只能算一個, 答案還是5
                // 因此如果當前數字 != max, 才能更新secondMax
                secondMax = num;
            }
        }
        System.out.println(secondMax);
    }
}