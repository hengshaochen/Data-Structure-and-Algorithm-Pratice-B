// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        new Main();
    }
    int start = 0;
    public Main() {
        String s = "3[a2[bd]]";
        System.out.println(decode(s));
    }
    
    public String decode(String s) {
        String curS = new String();
        
        while (start < s.length()) {
            char cur = s.charAt(start);
            if (Character.isDigit(cur)) {
                start += 2;
                String subS = decode(s);
                for (int i = 0; i < Character.getNumericValue(cur); i++) {
                    curS += subS;
                }
            } else if (cur == ']') {
                start += 1;
                return curS;
            } else if (Character.isLetter(cur)) {
                curS += cur;
                start += 1;
            }
        }
        return curS;
    }
    
}