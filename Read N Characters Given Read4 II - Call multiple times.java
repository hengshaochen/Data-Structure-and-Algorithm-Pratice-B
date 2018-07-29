/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */
// 2ms
// beats 58%
public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    char[] prevBuf = new char[4];
    int prevSize = 0;
    int prevIndex = 0;
    
    public int read(char[] buf, int n) {
        // 代表這次read要求讀多少個字元
        int counter = 0;
        
        while (counter < n) {
            // 如果上次preBuf還有剩下的字元, 先讀完
            if (prevIndex < prevSize) {
                buf[counter++] = prevBuf[prevIndex++];
            } else {
                // 上次preBuf已經為空了, 在呼叫read4獲取新的字元, preSize代表在這次read4中真正讀到多少字元
                prevSize = read4(prevBuf);
                prevIndex = 0;
                if (prevSize == 0) {
                    // no more data to consume from stream
                    break;
                }
            }
        }
        return counter;
    }
}