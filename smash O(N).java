// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        new Main();
    }
    public Main() {
        String s = "zaabbccddeeeeeeeeeeeedcbaz";
        System.out.println(smash(s));
    }
    
    List<Character> smash(String s) {
        List<Character> ans = new ArrayList<>();
        
        for (Character cur : s.toCharArray()) {
            //System.out.println(cur + "  " + ans);
            int ansSize = ans.size();
            if (ansSize == 0 || cur == ans.get(ansSize - 1)) {
                ans.add(cur);
            } else {
                if (ansSize >= 3) {
                    tryDelete(ans);
                }
                ans.add(cur);
            }
        }
        // 最尾巴可能會剩下[a,a,a]要再刪除, 因為尾巴會沒有跑到上面if判斷條件
        if (ans.size() >= 3) {
            tryDelete(ans);
        }
        return ans;
    }
    
    void tryDelete(List<Character> ans) {
        int ansSize = ans.size();
        char last1 = ans.get(ansSize - 1);
        char last2 = ans.get(ansSize - 2);
        char last3 = ans.get(ansSize - 3);
        if (last2 == last1 && last2 == last3) {
            // 繼續往前找, 找到不一樣的index為止
            int lastFirst = ansSize - 3;
            while (lastFirst >= 0) {
                if (ans.get(lastFirst) != last2) {
                    break;
                }
                lastFirst--;
            }
            // 從找到最前面相同的移除到最尾
            //System.out.println(lastFirst);
            for (int j = 0; j < ansSize - lastFirst - 1; j++) {
                ans.remove(lastFirst + 1);
            }
            //ans.removeRange(lastFirst + 1, ansSize);
        }
    }
}