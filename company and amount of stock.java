// Map + PriorityQueue, k不固定，用MAX_HEAP全部都存
// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        new Main();
    }
    
        class Pair {
            String company;
            int num;
            public Pair(String company, int num) {
                this.company = company;
                this.num = num;
            }
        }
    // HashMap + PriorityQueue (topK k是會變)，用MAX_HEAP存N個全部存到pq中
    class Stock {
        Map<String, Integer> map;
        PriorityQueue<Map.Entry<String, Integer>> pq;
        Comparator<Map.Entry<String, Integer>> cmp = new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                return e2.getValue() - e1.getValue();
            }
        };
        public Stock() {
            map = new HashMap<>();
            pq = new PriorityQueue<>(2, cmp);
        }
        
        void add(String company, int num) {
            if (!map.containsKey(company)) {
                map.put(company, num);
                for (Map.Entry<String, Integer> entry : map.entrySet()) {
                    if (entry.getKey() == company) {
                        pq.offer(entry);
                    }
                }
            } else {
                for (Map.Entry<String, Integer> entry : map.entrySet()) {
                    if (entry.getKey() == company) {
                        pq.remove(entry);
                        map.put(company, num);
                        pq.offer(entry);
                    }
                }
            }
        }
        List<Pair> topK(int k) {
            PriorityQueue<Map.Entry<String, Integer>> buffer = new PriorityQueue<>(pq);
            List<Pair> ans = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                Map.Entry<String, Integer> entry = buffer.poll();
                ans.add(new Pair(entry.getKey(), entry.getValue()));
            }
            return ans;
        }
    }
    public Main() {
        Stock stock = new Stock();
        stock.add("BB", 200);
        stock.add("CC", 300);
        stock.add("DD", 500);
        List<Pair> top2 = stock.topK(2);
        for (int i = 0; i < 2; i++) {
            System.out.println(top2.get(i).company + " " + top2.get(i).num);
        }
    }
}

// ----------------------------------
// 法2: DoubleLinkedList的head存top3的最多股票數量的，tail存top3最少股票數量的
// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        new Main();
    }
    class DoubleListNode {
        DoubleListNode pre, next;
        String company;
        int num;
        public DoubleListNode(String company, int num) {
            this.company = company;
            this.num = num;
            pre = null;
            next = null;
        }
    }
    
    class Stock {
        DoubleListNode head, tail;
        Map<String, DoubleListNode> map;
        int capacity;
        public Stock(int k) {
            head = new DoubleListNode("Head", Integer.MAX_VALUE);
            tail = new DoubleListNode("Tail", Integer.MAX_VALUE);
            head.next = tail;
            tail.pre = head;
            map = new HashMap<>();
            capacity = k;
        }
        
        void adjustPosition(DoubleListNode adjustNode) {
            DoubleListNode insert = adjustNode.pre;
            while (adjustNode.num > insert.num) {
                // 往前走，走到第一個比要調整的node大的
                // 不會越界，因為head的值設為Integer.MAX_VALUE
                insert = insert.pre;
            }
            
            // 從當前node移除
            adjustNode.pre.next = adjustNode.next;
            adjustNode.next.pre = adjustNode.pre;
            // 移動到適合位置
            DoubleListNode temp = insert.next;
            insert.next = adjustNode;
            adjustNode.pre = insert;
            adjustNode.next = temp;
            temp.pre = adjustNode;
        }
        
        void add(String company, int num) {
            if (!map.containsKey(company)) {
                DoubleListNode newNode = new DoubleListNode(company, num);
                map.put(company, newNode);
                // 插入在linkedlist尾端
                DoubleListNode temp = tail.pre;
                tail.pre = newNode;
                newNode.next = tail;
                newNode.pre = temp;
                temp.next = newNode;
                // 調整到適當位置
                adjustPosition(newNode);
            } else {
                // 如果已經存在, 只要update該node的num
                DoubleListNode curNode = map.get(company);
                curNode.num = num;
                adjustPosition(curNode);
            }
            if (map.size() > capacity) {
                // 刪除最尾端的元素
                DoubleListNode deleteNode = map.get(tail.pre.company);
                map.remove(deleteNode.company);
                deleteNode.pre.next = tail;
                tail.pre = deleteNode.pre;
            }
        }
        
        List<DoubleListNode> top3() {
            List<DoubleListNode> ans = new ArrayList<>();
            DoubleListNode cur = head.next;
            while (cur != null && cur != tail) {
                ans.add(cur);
                cur = cur.next;
            }
            return ans;
        }
    }
    public Main() {
        int k = 3;
        Stock stock = new Stock(k);
        System.out.println("----");
        stock.add("Bloomberg", 200);
        stock.add("ANSYS", 300);
        stock.add("APPLE", 100);
        stock.add("GOOGLE", 1200);
        stock.add("Bloomberg", 350);
        List<DoubleListNode> test = stock.top3();
        for (DoubleListNode cur : test) {
            System.out.println(cur.company + " " + cur.num);
        }
    }
}