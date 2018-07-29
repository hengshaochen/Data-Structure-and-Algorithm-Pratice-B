/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        char[] temp = new char[4];
        boolean eof = false;
        int charRemain = n;
        int totalChar = 0;
        while (!eof) {
            // 從read4 API讀'最多'4個char
            int read4Return = read4(temp);
            
            // charCount代表read4此次實際讀了幾個char
            int charCount = Math.min(read4Return, charRemain);
            
            charRemain -= charCount;
            
            // 如果沒有剩餘的字(case:剛好讀完) 或是 這次read4 API讀到的數量小於4 (case: n很大, 但實際沒這麼多) 代表之後不用讀了
            if (charRemain == 0 || read4Return < 4) {
                eof = true;
            }
            // 把當前讀到的append到 destination buf答案中
            for (int i = 0; i < charCount; i++) {
                buf[totalChar++] = temp[i];
            }
        }
        
        return totalChar;
    }
}