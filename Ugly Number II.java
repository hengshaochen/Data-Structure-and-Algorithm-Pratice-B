// I
public boolean isUgly(int num) {
    if (num <= 0) {return false;}
    if (num == 1) {return true;}
    if (num % 2 == 0) {
        return isUgly(num/2);
    }
    if (num % 3 == 0) {
        return isUgly(num/3);
    }
    if (num % 5 == 0) {
        return isUgly(num/5);
    }
    return false;
}


// II
class Solution {
    public int nthUglyNumber(int n) {
        PriorityQueue<Long> pq = new PriorityQueue<>();
        Set<Long> set = new HashSet<>();
        
        long[] prime = new long[3];
        prime[0] = 2;
        prime[1] = 3;
        prime[2] = 5;
        for (int i = 0; i < 3; i++) {
            pq.add(prime[i]);
            set.add(prime[i]);
        }
        
        long cur = 1;
        // 從n = 1開始，因為當n = 1, 回傳答案是1
        for (int i = 1; i < n; i++) {
            cur = pq.remove();
            for (int j = 0; j < 3; j++) {
                long curAdd = cur * prime[j];
                if (set.contains(curAdd)) {
                    continue;
                }
                set.add(curAdd);
                pq.add(curAdd);
            }
        }
        return (int)cur;
    }
}