// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        String[] arr = new String[2];
        arr[0] = "bloomberg";
        arr[1] = "newyork";
        //arr[2] = "bc";
        
        int[] map = new int[26];
        for (String s : arr) {
            boolean[] set = new boolean[26];
            for (Character c : s.toCharArray()) {
                set[c - 'a'] = true;
            }
            
            for (int i = 0; i < 26; i++) {
                if (set[i] == true) {
                    map[i] += 1;
                }
            }
        }
        
        for (int i = 0; i < 26; i++) {
            if (map[i] == arr.length) {
                System.out.println((char)(i + 'a'));
            }
        }
    }
}