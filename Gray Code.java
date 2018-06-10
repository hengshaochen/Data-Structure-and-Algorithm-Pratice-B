// 快 Time: O(1 << n)
public class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> result = new LinkedList<>();
        for (int i = 0; i < 1<<n; i++) result.add(i ^ i>>1);
        return result;
    }
}

// 慢 O(2^n)
public class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> ans = new ArrayList<>();

        if (n == 0) { 
            ans.add(0);
            return ans;
        }

        // Init n = 1
        ans.add(0);
        ans.add(1);

        for (int i = 2; i <= n; i++) {
            int curSize = ans.size() - 1;
            while (curSize >= 0) {
                ans.add(ans.get(curSize--) + (int)Math.pow(2, i - 1));
            }
        }

        return ans;
    }
}
