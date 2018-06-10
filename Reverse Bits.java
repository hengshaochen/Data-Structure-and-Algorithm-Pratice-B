// Overflow
public class Solution {
    // you need treat n as an unsigned value
    public long reverseBits(int n) {
        List<Integer> ans = new ArrayList<>();
        while (n > 0) {
            ans.add(n % 2);
            n = n / 2;
        }
        
        // 如果ans.size()比32小，要讓他尾端補0，不然位數會錯
        while (ans.size() < 32) {
            ans.add(0);
        }
        
        long intAns = 0;
        int curDig = ans.size() -1;
        for (int i = 0; i < ans.size(); i++) {
            if (ans.get(curDig) != 0) {
                intAns += Math.pow(2, i);
            }
            curDig--;
        }
        
        return intAns;
    }
}

// O(32)
public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            ans = ans << 1;   // 向左移動一bits, 相當於乘以2, 每次都迴圈都必須做這動作
            if ((n & 1) == 1) {
                ans = ans + 1;  // 最低位補1
            }
            n = n >> 1;
        }
        return ans;
    }

}


// Follow-up 用ｃａｃｈｅ加速，還沒做