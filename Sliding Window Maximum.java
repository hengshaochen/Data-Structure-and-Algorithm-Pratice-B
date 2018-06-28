// O(nlogk) priority queue解法
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        Comparator<Integer> cmp = new Comparator<Integer>() {
            public int compare(Integer e1, Integer e2) {
                // MAX HEAP
                return e2 - e1;
            }
        };
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(k, cmp);
        int[] ans = new int[nums.length - k + 1];
        
        // 初始化，先把第一組放入pq
        for (int i = 0; i < k; i++) {
            pq.add(nums[i]);
        }
        ans[0] = pq.peek();
        
        for (int i = k; i < nums.length; i++) {
            pq.remove(nums[i - k]);
            pq.add(nums[i]);
            ans[i - k + 1] = pq.peek();
        }
        
        return ans;
    }
}

// O(n) 單調queue解法
class Solution {
    class monotonicQueue {
        public void push(int e) {
            while (!q.isEmpty() && q.peekLast() < e) {
                q.pollLast();
            }
            q.addLast(e);
        }
        
        public int pop() {
            return q.pollFirst();
        }
        
        public int max() {
            return q.peekFirst();
        }
        Deque<Integer> q = new ArrayDeque<>();
    }
    public int[] maxSlidingWindow(int[] nums, int k) {
        monotonicQueue mq = new monotonicQueue();
        int[] ans = new int[nums.length - k + 1];
        
        for (int i = 0; i < nums.length; i++) {
            mq.push(nums[i]);
            // 如果window size夠了之後
            if (i - k + 1 >= 0) {
                ans[i - k + 1] = mq.max();
                // 看我筆記的範例。如果當前要刪除的元素（被shift掉的元素）是MAX值，代表先前mq內建的pop沒因為pq.push而pop掉這個元素
                // 因此這邊要pop
                if (nums[i - k + 1] == mq.max()) {
                    mq.pop();
                }
            }
        }
        return ans;
    }
}