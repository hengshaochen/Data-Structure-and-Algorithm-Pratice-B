class Solution {
    public boolean verifyPreorder(int[] preorder) {
        Stack<Integer> s = new Stack<>();
        int min = Integer.MIN_VALUE;
        for (int cur : preorder) {
            // 如果違反BST走preorder的規定, 當前cur比規定的min還小
            if (cur < min) {
                return false;
            }
            
            // 将路径中所有小于当前的数pop出来并更新最小值
            while (!s.isEmpty() && cur > s.peek()) {
                min = s.pop();
            }
            
            // 每輪都push
            s.push(cur);
        }
        return true;
    }
}