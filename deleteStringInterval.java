// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        new Main();
    }
    
    class Interval {
        int start, end;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    
    public Main() {
        String s = "abcdefg";
        List<Interval> interval = new ArrayList<>();
        interval.add(new Interval(0, 2));
        interval.add(new Interval(1, 3));
        interval.add(new Interval(5, 6));
        System.out.println(deleteStringInterval(s, interval));
    }
    
    public String deleteStringInterval(String s, List<Interval> interval) {
        if (interval.size() == 0) {
            return s;
        }
        // 1. Merge Interval
        Comparator<Interval> cmp = new Comparator<Interval>() {
            public int compare(Interval e1, Interval e2) {
                return e1.start - e2.start;
            }    
        };
        Collections.sort(interval, cmp);
        Interval pre = interval.get(0);
        List<Interval> newInterval = new ArrayList<>();
        for (int i = 1; i < interval.size(); i++) {
            Interval cur = interval.get(i);
            if (pre.end < cur.start) {
                newInterval.add(pre);
                pre = cur;
            } else {
                pre.end = Math.max(pre.end, cur.end);
            }
        }
        newInterval.add(pre);
        for (int i = 0; i < newInterval.size(); i++) {
            System.out.println(newInterval.get(i).start + " " +newInterval.get(i).end);
        }
            
        // 2. 把在interval的跳過，不在interval的就append
        int idx = 0;
        int len = s.length();
        int intervalCount = 0;
        Interval cur = interval.get(intervalCount);
        StringBuilder sb = new StringBuilder();
        while (idx < len) {
            if (idx == cur.start) {
                while (idx <= cur.end) {
                    idx++;
                }
                // 避免interval越界
                if (intervalCount + 1 < newInterval.size()) {
                    cur = newInterval.get(++intervalCount);
                }
            } else {
                sb.append(s.charAt(idx++));
            }
        }
        return sb.toString();
    }
}