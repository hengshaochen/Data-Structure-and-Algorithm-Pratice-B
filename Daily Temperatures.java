class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        // stack存temperatures的index
        Stack<Integer> s = new Stack<>();
        int[] ans = new int[temperatures.length];
        
        for (int i = 0; i < temperatures.length; i++) {
            while (!s.isEmpty() && temperatures[i] > temperatures[s.peek()]) {
                // 當前元素比遞減stack的頂端大，不能放進去，因為要維持遞減stack
                int preDayIndex = s.pop();
                ans[preDayIndex] = i - preDayIndex;
            }
            // 出了while代表 case1) stack為空 case2) 當前元素temperatures[i]比stack頂小了，放進去可以維持遞減stack
            s.push(i);
        }
        return ans;
    }
}