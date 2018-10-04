
// Java program for flattening a Linked List 
class LinkedList2 
{ 
    Node head;  // head of list 
  
    /* Linked list Node*/
    class Node 
    { 
        int data; 
        Node right, down; 
        Node(int data) 
        { 
            this.data = data; 
            right = null; 
            down = null; 
        } 
    } 
  
    Node merge(Node l1, Node l2) {
        Node dummy = new Node(0);
        Node pre = dummy;
        while (l1 != null && l2 != null) {
            if (l1.data < l2.data) {
                pre.down = l1;
                l1.right = null;
                l1 = l1.down;
            } else {
                pre.down = l2;
                l2.right = null;
                l2 = l2.down;
            }                    
            pre = pre.down;
        }
        
        if (l1 != null) {
            pre.down = l1;
        }
        if (l2 != null) {
            pre.down = l2;
        }
        return dummy.down;
    }
    
    Node flatten(List<Node> lists, int start, int end) {
        // base case
        if (start == end) {
            return lists.get(start);  // start和end隨便回傳一個即可
        }
        
        int mid = (start + end) / 2;
        // System.out.println(start + " " + end);
        Node l1 = flatten(lists, start, mid);
        Node l2 = flatten(lists, mid + 1, end);
        
        return merge(l1, l2);
    }
    /* Utility function to insert a node at begining of the 
       linked list */
    Node push(Node head_ref, int data) 
    { 
        /* 1 & 2: Allocate the Node & 
                  Put in the data*/
        Node new_node = new Node(data); 
  
        /* 3. Make next of new Node as head */
        new_node.down = head_ref; 
  
        /* 4. Move the head to point to new Node */
        head_ref = new_node; 
  
        /*5. return to link it back */
        return head_ref; 
    } 
  
    void printList() 
    { 
        Node temp = head; 
        while (temp != null) 
        { 
            System.out.print(temp.data + " "); 
            temp = temp.down; 
        } 
        System.out.println(); 
    } 
  
    /* Drier program to test above functions */
    public static void main(String args[]) 
    { 
        LinkedList2 L = new LinkedList2(); 
        
  
        /* Let us create the following linked list 
            5 -> 10 -> 19 -> 28 
            |    |     |     | 
            V    V     V     V 
            7    20    22    35 
            |          |     | 
            V          V     V 
            8          50    40 
            |                | 
            V                V 
            30               45 
        */
  
        L.head = L.push(L.head, 30); 
        L.head = L.push(L.head, 8); 
        L.head = L.push(L.head, 7); 
        L.head = L.push(L.head, 5); 
  
        L.head.right = L.push(L.head.right, 20); 
        L.head.right = L.push(L.head.right, 10); 
  
        L.head.right.right = L.push(L.head.right.right, 50); 
        L.head.right.right = L.push(L.head.right.right, 22); 
        L.head.right.right = L.push(L.head.right.right, 19); 
  
        L.head.right.right.right = L.push(L.head.right.right.right, 45); 
        L.head.right.right.right = L.push(L.head.right.right.right, 40); 
        L.head.right.right.right = L.push(L.head.right.right.right, 35); 
        L.head.right.right.right = L.push(L.head.right.right.right, 20); 
        
        Node cur = L.head;
        Node pre = cur;
        List<Node> lists = new ArrayList<>();
        while (cur != null) {
            lists.add(cur);
            cur = cur.right;
            
            // 把right切斷
            pre.right = null;
            pre = cur;
        }
        
        // flatten the list 
        L.head = L.flatten(lists, 0, lists.size() - 1); 
  
        L.printList(); 
    } 
} /* This code is contributed by Rajat Mishra */
