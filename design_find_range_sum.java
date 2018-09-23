import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


public class Solution {
    public static void main(String args[] ) throws Exception {
        /* Enter yourcode here. Read input from STDIN. Print output to STDOUT */
        new Solution();
    }
    
    class fastSum {
        List<Integer> nums;
        List<Integer> prefix;
        
        public fastSum() {
            nums = new ArrayList<Integer>();
            prefix = new ArrayList<Integer>();
        }
        
        void add(int num) {
            nums.add(num);
            if (prefix.size() == 0) {
                prefix.add(num);
            } else {
                prefix.add(prefix.get(prefix.size() - 1) + num);
            }
        }
        
        int sum(int start, int end) {
            // corner case
            if (start < 0 || end > prefix.size() || (end - start) <= 0) {
                // throw exception
                throw new IndexOutOfBoundsException();
            }
            
            if (start == 0) {
                return prefix.get(end - 1);
            }
            
            return prefix.get(end - 1) - prefix.get(start - 1);
        }
    }
    
    public Solution() {
        fastSum f = new fastSum();
        f.add(2);
        f.add(4);
        f.add(6);
        f.add(8);
        
        System.out.println(f.sum(3, 4));
    }
    
}