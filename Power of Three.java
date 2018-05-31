// 法1 loop
class Solution {
    public boolean isPowerOfThree(int n) {
        // conrer case
        double cur = n;
        if (cur == 1) {
            return true;
        }
        
        while (cur >= 3) {
            if (cur == 3) {
                return true;
            }
            cur = cur / 3;
        }
        return false;
    }
}

// 法2
class Solution {
    public static boolean isPowerOfThree(int n) {
        if (n <= 0)
            return false;
        double r = Math.log10(n) / Math.log10(3);
        if (r % 1 == 0)
            return true;
        else
            return false;
    }
}

// 法3
class Solution {
    public static boolean isPowerOfThree(int n) {
        return (n > 0 && 1162261467 % n == 0);
    }
}