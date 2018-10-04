// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        new Main();
    }
    
    public Main() {
        System.out.println(swapInteger(71356, 1, 4));
    }
    
    int swapInteger(int num, int i, int j) {
        // corner case
        if (i == j) {
            return num;
        }
        
        // 確保i在j前面, 如果i > j, i j交換
        if (i > j) {
            int temp = j;
            j = i;
            i = temp;
        }
        
        int len = 0;
        int temp = num;
        while (temp > 0) {
            temp /= 10;
            len++;
        }
        
        int jNum = 0;
        int sum = 0;
        for (int k = len - 1; k >= 0; k--) {
            int dig = num % 10;
            if (k == j) {
                // 代表遇到j: 遇到j先把j的數字存起來, 到時遇到i在一併處理
                jNum = dig;
            } else if (k == i) {
                // 代表遇到i, 一併處理i和j的數字swap加入sum
                sum = sum + (dig * (int)Math.pow(10, len - 1 -  j)) + 
                            (jNum * (int)Math.pow(10, len - 1 - i));
            } else {
                sum += dig * (int)Math.pow(10 ,len - 1 - k);
            }
            num /= 10;
        }
        return sum;
    }
}