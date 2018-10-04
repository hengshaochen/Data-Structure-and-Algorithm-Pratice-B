// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        new Main();
    }
    class Pair {
        String s;
        int t;
        public Pair(String s, int t) {
            this.s = s;
            this.t = t;
        }
    }
    class timeMachine {
        List<Pair> sequence;
        public timeMachine() {
            sequence = new LinkedList<>();
        }
        
        String insert(String s, int t) {
            Pair insertNode = new Pair(s, t);
            if (sequence.size() == 0) {
                // 特判: 當長度為0時, 直接插入
                sequence.add(insertNode);
            } else if (sequence.get(sequence.size() - 1).t < insertNode.t) {
                // 插入的node比尾端還大 --> 直接插入尾端
                // 這樣寫是因為下面for會忽略這個case
                sequence.add(sequence.size() , insertNode);
            } else {  
                // 插入在中間內，找到合適的位置插入
                for (int i = 0; i < sequence.size(); i++) {
                    if (sequence.get(i).t > insertNode.t) {
                        sequence.add(i, insertNode);
                        break;
                    }
                }
            }
            /*
            // 測試LinkedList結構代碼
            System.out.print("sequnece: ");
            for (int i = 0; i < sequence.size(); i++) {
                System.out.print(sequence.get(i).s + " ");
            }
            System.out.println("");
            */
            int count = 1;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < sequence.size(); i++) {
                Pair cur = sequence.get(i);
                if (cur.t == count) {
                    sb.append(cur.s + " ");
                    count++;
                } else {
                    return "";
                }
            }
            return sb.toString();
        }
    }
    
    public Main() {
        timeMachine m = new timeMachine();
        System.out.println(m.insert("Tic", 3));
        System.out.println(m.insert("Toc", 2));
        System.out.println(m.insert("Toe", 1));
        System.out.println(m.insert("AB", 4));
    }
}