class Solution {
    /**
     * @param A: An integers array.
     * @return: return any of peek positions.
     */
    public int findPeakElement(int[] A) {
        // write your code here
        int start = 0, end = A.length - 1;
        while(start + 1 <  end) {
            int mid = (start + end) / 2;
            if(A[mid] < A[mid - 1]) {
                end = mid;
            } else if(A[mid] < A[mid + 1]) {
                start = mid;
            } else {
                // start = mid 或 end = mid都可
                start = mid;
            }
        }
        
        // 最後start和end大的那個是peak
        if(A[start] < A[end]) {
            return end;
        } else { 
            return start;
        }
    }
}