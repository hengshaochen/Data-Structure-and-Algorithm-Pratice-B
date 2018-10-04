// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        String s = "abbadaeeee";
        System.out.println(repeatMost(s));
    }

    public int repeatMost(String s) {
        char[] sArray = s.toCharArray();
        Arrays.sort(sArray);
        
        int ans = 0;
        int curCount = 1;
        int idx = 0;
        while (idx < s.length()) {
            // 重複的找到不重複
            while (idx + 1 < sArray.length && sArray[idx] == sArray[idx + 1]) {
                curCount++;
                idx++;
            }
            // 不重複，更新ans
            if (idx + 1 < sArray.length && sArray[idx] != sArray[idx + 1]) {
                ans = Math.max(ans, curCount);
                curCount = 1;
                idx++;
            }
            // 結尾，嘗試更新ans
            if (idx == sArray.length - 1) {
                ans = Math.max(ans, curCount);
                idx++;
            }
        }
        return ans;
    }
}