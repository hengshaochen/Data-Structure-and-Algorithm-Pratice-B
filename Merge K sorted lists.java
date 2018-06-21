// 法1
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        
        Comparator<ListNode> cmp = new Comparator<ListNode>() {
            public int compare(ListNode node1, ListNode node2) {
                return node1.val - node2.val;
            }
        };
        PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>(lists.length, cmp);
        
        // 把lists的頭都放入pq
        for (ListNode cur : lists) {
            if (cur != null) {
                pq.add(cur);
            }
        }
        
        // 每次取最小的出來，被拿出的那個list要向後移動一格，如果該list還有下個node, 把下個lists放到queue中
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (!pq.isEmpty()) {
            ListNode cur = pq.remove();
            tail.next = cur;
            if (cur.next != null) {
                cur = cur.next;
                pq.add(cur);
            }
            tail = tail.next;
        }
        
        return dummy.next;
    }
}

// 法3
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        return mergeHelper(lists, 0, lists.length - 1);
    }
    
    public ListNode mergeHelper(ListNode[] lists, int start, int end) {
        // 出口(base case)
        if (start == end) {
            return lists[start];
        }
        
        // 拆解
        int mid = start + (end - start) / 2;
        ListNode left = mergeHelper(lists, start, mid);
        ListNode right = mergeHelper(lists, mid + 1, end);
        
        // 合併
        ListNode mergeAns = mergeTwoList(left, right);
        
        return mergeAns;
    }
            
    ListNode mergeTwoList(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
            
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                tail.next = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next;
        }
            
        if (list1 != null) {
            tail.next = list1;
        } else {
            // list2 != null
            tail.next = list2;
        }
        
        return dummy.next;
    }
}