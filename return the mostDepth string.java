import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


public class Solution {
    public static void main(String args[]) throws Exception {
        /* Enter yourcode here. Read input from STDIN. Print output to STDOUT */
        new Solution();
    }
    
    
    public Solution() {
        String s = "(a(bloomberg)(google))(facebook(ansys))";  // output:[bloomberg, google, ansys]

        
        System.out.println(mostDepth(s));
    }
    
    public List<String> mostDepth(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        int i = 0, depth = 0, ansDepth = 0, len = s.length();
        while (i < s.length()) {
            char cur = s.charAt(i);
            if (cur == '[' || cur == '(' || cur == '{') {
                depth += 1;
                i++;
                // continue;
            } else if (cur == ']' || cur == ')' || cur == '}') {
                depth -= 1;
                i++;
                // continue
            } else {
                // cur is char string, read all word
                StringBuilder word = new StringBuilder();
                while (Character.isLetter(cur)) {
                    word.append(cur);
                    cur = s.charAt(++i);;
                }
                
                if (depth > ansDepth) {
                    ans = new ArrayList<>();
                    ans.add(word.toString());
                    ansDepth = depth;
                } else if (depth == ansDepth) {
                    ans.add(word.toString());
                }
            }
        }
        return ans;
    }
    
}