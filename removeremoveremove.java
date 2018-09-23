// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        new Main();
    }
    
    public Main() {
        //String s = "122221133444443";
        //String pre = "122221133444443";
        //String s = "33444443";
        String s = "aabbbc";
        String pre = "-1";
        while (s != pre && s.length() > 0) {
            int target = s.charAt(0);
            int startIdx = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != target) {
                    if (i - startIdx - 1 >= 2) {
                        // 消除
                        pre = s;
                        s = s.substring(0, startIdx) + s.substring(i, s.length());
                        break;
                    } else {
                        // 換target繼續找
                        target = s.charAt(i);
                        startIdx = i;
                    }
                }
                
                // 如果掃到最後一個
                if (i == s.length() - 1) {
                    if (i - startIdx >= 2) {
                        // 消除
                        pre = s;
                        s = s.substring(0, startIdx) + s.substring(i + 1, s.length());
                        break;
                    } else {
                        // 掃到結尾還是不能消除
                        pre = s;
                        break;
                    }
                } 
            }
            System.out.println(s);
        }
        System.out.println("Answer: " + s);
    }
}