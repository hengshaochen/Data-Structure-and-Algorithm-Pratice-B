// "static void main" must be defined in a public class.
public class Main {
    class latestStock {
        Map<String, Queue<Integer>> map;
        public latestStock() {
            map = new HashMap<>();
        }
        
        void put(String stockName, int price) {
            if (!map.containsKey(stockName)) {
                map.put(stockName, new LinkedList<Integer>());
                map.get(stockName).add(price);
                return;
            } else {
                // 存在
                // 小於5
                Queue<Integer> curStockQ = map.get(stockName);
                if (curStockQ.size() < 5) {
                    curStockQ.add(price);
                } else {
                    // 大於5
                    curStockQ.remove(); // 刪除頭（最舊的股價）
                    curStockQ.add(price); // 加入尾（最新的股價）
                }
            }
            
        }
        
        public Deque<Integer> get(String stockName) {
            if (!map.containsKey(stockName)) {
                return null;
            }
            Deque<Integer> stockPrice = new LinkedList<>();
            for (Integer cur : map.get(stockName)) {
                stockPrice.addFirst(cur);
            }
            return stockPrice;
        }
    }
    public static void main(String[] args) {
        new Main();
    }
    
    public Main() {
        latestStock latestStock = new latestStock();
        latestStock.put("FB", 150);
        latestStock.put("FB", 161);
        latestStock.put("FB", 173);
        latestStock.put("FB", 175);
        latestStock.put("FB", 144);
        latestStock.put("FB", 200);
        
        System.out.println(latestStock.get("FB"));
    }
}