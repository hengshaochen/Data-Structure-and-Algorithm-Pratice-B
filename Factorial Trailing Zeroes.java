class Solution {
    public int trailingZeroes(int n) {
        // 求從最低位數開始算直到不為零的bit，總共有多少個0
        // 算法：如何產生尾數是0？把2*5就會產生一個0，然而2的數量遠遠超過5的數量，所以只要算5的數量有幾個就是有幾個尾數0
        if (n < 5) {
            return 0;
        }
        return n / 5 + trailingZeroes(n / 5);
    }
}