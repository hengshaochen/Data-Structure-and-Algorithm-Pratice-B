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
        /*
        Interval event = new Interval(3, 10);
        List<Interval> list = new ArrayList();
        list.add(new Interval(0, 1));
        list.add(new Interval(4, 6));
        list.add(new Interval(7, 9));
        */
        Interval event = new Interval(0, 1500);
        List<Interval> list = new ArrayList();
        list.add(new Interval(65, 200));
        list.add(new Interval(400, 500));
        list.add(new Interval(800, 1200));
        
        List<Interval> ans = findOverlap(event, list);
        for (Interval cur : ans) {
            System.out.println(cur.start + " " + cur.end);
        }
    }
    
    List<Interval> findOverlap(Interval event, List<Interval> list) {
        int end = event.start;
        List<Interval> ans = new ArrayList<>();
        for (Interval cur : list) {
            if (end < event.end) {
                if (end < cur.start) {
                    ans.add(new Interval(end, cur.start));
                    end = cur.end;
                } else {
                    ans.add(new Interval(cur.end, end));
                }
            }
        }
        // 最尾端
        if (end < event.end) {
            ans.add(new Interval(end, event.end));
        }
        
        return ans;
    }
}