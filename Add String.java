class Solution {
    public String addStrings(String num1, String num2) {
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        while (i >= 0 || j >= 0) {
            int curSum = carry;
            curSum += i < 0 ? 0 : num1.charAt(i) - '0';
            curSum += j < 0 ? 0 : num2.charAt(j) - '0';
            sb.append((curSum % 10));
            carry = curSum / 10;
            
            i--;
            j--;
        }
        if (carry != 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }
}