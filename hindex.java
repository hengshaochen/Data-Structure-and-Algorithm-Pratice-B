class Solution {
    public int hIndex(int[] citations) {
        // O(n^2): 思路，H-index就是在全部文章中，至少有h個文章的引用次數大於h
        // ex: [1, 1]: 至少有1篇引用次數>1 , [1,2] 至少有1篇引用次數>1
        
        int ans = 0;
        for (int i = 0; i < citations.length; i++) {
            int cur = 0;
            for (int j = 0; j < citations.length; j++) {
                if (citations[j] >= i + 1) {
                    cur++;
                }
            }
            if (cur >= i + 1) {
                ans = Math.max(ans, i + 1);
            }
        }
        return ans;
    }
}

// 法2:
class Solution {
    public int hIndex(int[] citations) {
        // 優化 先排序後使用Binary Search 時間:O(nlogn 排序 + nlogn 針對每個h-index做binary search)
        // 相當於上個方法每次必須耗費O(n)當前是否符合h-index, 現在只需要O(logn)即可, 等同於把前一個方法的cur轉成用binary searcy來求
        if (citations.length == 0) {
            return 0;
        }
        Arrays.sort(citations);
        
        int ans = 0;
        for (int i = 0; i <= citations[citations.length - 1]; i++) {
            int cur = 0, start = 0, end = citations.length - 1;
            while (start + 1 < end) {
                int mid = (start + end) / 2;
                // *****binary search找最左邊 >= 當前h-index的 (i + 1)
                if (citations[mid] == i) {
                    end = mid;
                } else if (citations[mid] > i) {
                    end = mid;
                } else {
                    start = mid;
                }
            }
            
            if (citations[start] >= i && citations.length - start >= i) {
                ans = Math.max(ans, i);
            }
            if (citations[end] >= i && citations.length - end >= i) {
                ans = Math.max(ans, i);
            }
        }
        return ans;
    }
}
// 法3: O(n)