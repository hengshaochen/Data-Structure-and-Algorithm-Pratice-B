// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        new Main();
    }
    
    public Main() {
        int[] nums = {0,1,2,3,4,5,6,7,8,9};
        List<int[]> interval = new ArrayList<>();
        interval.add(new int[]{0,1});
        interval.add(new int[]{3,4});
        interval.add(new int[]{7,9});
        deleteInterval(nums, interval);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
    
    void deleteInterval(int[] nums, List<int[]> intervals) {
        
        int i = intervals.get(0)[0];
        int j = intervals.get(0)[1] + 1;
        int curInterval = 0;
        
        while (j < nums.length) {
            // System.out.println("i,j : " + i + " " + j);
            // j找下一個合法, 把不合法跳掉
            if (curInterval + 1 < intervals.size() &&
                   intervals.get(curInterval + 1)[0] <= j &&
                   j <= intervals.get(curInterval + 1)[1]) 
            {
                while (curInterval + 1 < intervals.size() &&
                   intervals.get(curInterval + 1)[0] <= j &&
                   j <= intervals.get(curInterval + 1)[1]) 
                {
                    j++;
                }
                curInterval += 1;
            }
            
             System.out.println("i,j : " + i + " " + j + " " + (curInterval + 1));
            // 防止越界
            if (j < nums.length) {
                nums[i++] = nums[j++];
            }
        }
        System.out.println(i);
    }
}