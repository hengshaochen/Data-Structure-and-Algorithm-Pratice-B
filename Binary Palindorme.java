// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        new Main();
    }
    
    public Main() {
        System.out.println(binPal(4));
    }
    
    boolean binPal(int num) {
        // 1. decimal to binary
        String s = new String();
        while (num > 0) {
            s = s + (num % 2);
            num = num / 2;
        }
        //s = s.reverse();  不需要reverse 因為paldinore reverse是一樣 不影響結果
        
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        
        System.out.println(s);
        return true;
    }
}