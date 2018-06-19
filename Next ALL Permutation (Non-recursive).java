// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        new Main();
    }
    
    public Main() {
        int[] arr = {1, 3, 2};
        List<List<Integer>> ans = permute(arr);
        
        for (List cur : ans) {
            System.out.println(cur);
        }
    }
    
    public boolean nextPermutation(int[] nums) {
        if (nums.length <= 1) {
            return false;
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
            return false;
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
        return true;
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
    public List<List<Integer>> permute(int[] A) {
        // Arrays.sort(A);  // 如果要找所有，而不是找current A接下來的，就要sort，讓他初始
        List<List<Integer>> result = new ArrayList<>();
        
        boolean next = true;  // next 为 true 时，表示可以继续迭代
        while (next)  {
            List<Integer> current = new ArrayList<>();  // 进行数组复制
            for (int a : A) {
                current.add(a);
            }
            
            result.add(current);
            next = nextPermutation(A);
        }
        return result;
    }
    
}