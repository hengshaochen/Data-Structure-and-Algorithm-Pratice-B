class Solution {
    public String getPermutation(int n, int k) {
        // n = 4為例子，dig存1,2,3,4
        List<Integer> dig = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            dig.add(i);
        }
        
        // fact存 1, 1, 2, 6
        int[] fact = new int[n];
        fact[0] = 1;
        for (int i = 1; i < n; i++) {
            fact[i] = fact[i - 1] * i;
        }
        
        // 把k變成和array一樣以0為開頭
        k = k - 1;
        StringBuilder sb = new StringBuilder();
        int idx = n - 1;
        while (dig.size() > 1) {
            System.out.println(k + " " + fact[idx]);
            System.out.println(dig);
            int curPickIndex = k / fact[idx];
            k = k % fact[idx];
            
            sb.append(dig.get(curPickIndex));
            dig.remove(curPickIndex); // 拿到該dig後就必須刪除，因為permutation不能重複拿同個數字
            idx--;
        }
        sb.append(dig.get(0));
        return sb.toString();
    }
}