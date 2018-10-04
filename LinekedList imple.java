// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        new Main();
    }
    
    class ListNode {
        int val;
        ListNode pre, next;
        public ListNode(int val) {
            this.val = val;
            pre = null;
            next = null;
        }
    }
    class myLinkedList {
        ListNode head, tail;
        public myLinkedList() {
            head = new ListNode(-1);
            tail = new ListNode(-1);
            head.next = tail;
            tail.pre = head;
        }
        
        void push(int val) {
            ListNode temp = tail.pre;
            ListNode newNode = new ListNode(val);
            newNode.pre = temp;
            newNode.next = tail;
            temp.next = newNode;
            tail.pre = newNode;
        }
        
        int pop() {
            if (head.next == tail) {
                // Empty
                return -1;
            }
            ListNode ans = tail.pre;
            ListNode temp = tail.pre.pre;
            temp.next = tail;
            tail.pre = temp;
            return ans.val;
        }
    }
    
    public Main() {
        myLinkedList myLinkedList = new myLinkedList();
        myLinkedList.push(1);
        myLinkedList.push(2);
        myLinkedList.push(3);
        
        System.out.println(myLinkedList.pop());
        System.out.println(myLinkedList.pop());
        System.out.println(myLinkedList.pop());
        myLinkedList.push(4);
        myLinkedList.push(5);
        System.out.println(myLinkedList.pop());
        System.out.println(myLinkedList.pop());
    }
}