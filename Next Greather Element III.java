class Solution {
    public int nextGreaterElement(int n) {
        // 思路1: 暴力法，用DFS窮舉所有然後排序。 O(2^n)
        // 思路2: 從後面往前面搜索，遇到的第一個“小於”後面數字的數，該數字為轉折點。接下來就是找誰要跟轉折點交換，由後往前，找第一個比轉折點大的數字
        // 12443322 --> 轉折點為1"2"443322 , 接著由後往左，3比2大 3和2交換，13443222，接著把轉折點後“升序”排序，13 222344
        
        // 找轉折點
        char[] arr = (n + "").toCharArray();
        int len = arr.length;
        int transPoint = 0;
        for (int i = len - 1; i > 0; i--) {
            if (arr[i - 1] < arr[i]) {
                transPoint = i - 1;
                break;
            }
        }
        
        //System.out.println(transPoint);
        /*
        if (transPoint == 0) {
            // 代表是降序 54321 , 不會有下一個比她大的數字了
            return -1;
        }
        */
        
        // 轉折點和由後往左“第一個比轉折點大的數字交換”
        for (int i = len - 1; i > 0; i--) {
            if (arr[i] > arr[transPoint]) {
                // swap
                char temp = arr[i];
                arr[i] = arr[transPoint];
                arr[transPoint] = temp;
                break;
            }
        }
        
        // 轉折點之後升序排序
        Arrays.sort(arr, transPoint + 1, len);
        
        // 防止越界
        long ans = Long.parseLong(new String(arr));

        // 代表原本是升序,找到轉折點會在index = 0, 例如21, 轉折點在0, 但是也不能交換，所以還是21，這時要輸出-1
        // 或是出來的數字比integer.max大，也要-1
        if (n >= ans || ans > Integer.MAX_VALUE) {
            return -1;
        }
        return (int)ans;
    }
}