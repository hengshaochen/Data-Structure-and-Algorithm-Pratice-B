class Solution {
    public String numberToWords(int num) {
        // 特判斷
        if (num == 0) {
            return "Zero";
        }
        
        StringBuilder sb = new StringBuilder();
        String[] unit = {"Thousand", "Million", "Billion"};
        
        // 最尾巴三個數字獨立先處理，因為不用加入unit
        // 1234567891 % 1000 = 891
        sb.append(handleThreeDigits(num % 1000));
        
        for (int i = 0; i < 3; ++i) {
            num /= 1000;
            StringBuilder cur = new StringBuilder();
            if (num % 1000 != 0) {
                cur.append(handleThreeDigits(num % 1000) + " " + unit[i] + " ");
            }
            sb.insert(0, cur);
        }
        
        // 去除結尾的空格
        while (sb.charAt(sb.length() -1) == ' ') {
            sb.deleteCharAt(sb.length() - 1);
        }
        
        return sb.toString();
    }
    
    public StringBuilder handleThreeDigits(int num) {
        StringBuilder cur = new StringBuilder();
        String[] v1 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        String[] v2 = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
        
        int hundred = num / 100;
        int tensOnes = num % 100;
        int tens = num % 100 / 10;
        int ones = num % 100 % 10;
        
        // 十位數和個位數一起看，若小於20, 直接v1拿, 不然就拆開看從v2 + v1拿
        if (tensOnes < 20) {
            cur.append(v1[tensOnes]);
        } else {
            cur.append(v2[tens]);
            // 如果個位數不為零, 加入入個位數, 不然就什麼也不加入
            cur.append(ones != 0 ? " " + v1[ones] :"" );
        }
        
        // 百位數字補上，插入到最前面. 如果十位和個位數合起來不為0, 就要插入, 不然什麼也不插入
        StringBuilder ans = new StringBuilder();
        if (hundred > 0) {
            ans.append(v1[hundred] + " Hundred" + (tensOnes != 0 ? " " + cur.toString() : ""));
            return ans;
        }
        
        // 如果沒有百位，return 十位和個位合起來的結果
        return cur;
    }
    
}