public class Codec {
    // Encodes a URL to a shortened URL.
    HashMap<String, String> URLtoShort = new HashMap<>();
    HashMap<String, String> shortToURL = new HashMap<>();
    String baseURL = "http://tinyurl.com/";
    String c = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";  // 62種
    
    Random random = new Random();
    public String encode(String longUrl) {
        // 如果已經有這個longUrl 直接回傳
        if (URLtoShort.containsKey(longUrl)) {
            return URLtoShort.get(longUrl);
        }
        
        // 如果沒有，則建構一個長度為六位的縮寫URL
        StringBuilder sb = new StringBuilder();
        do {
            for (int i = 0; i < 6; i++) {
                sb.append(c.charAt(random.nextInt(c.length())));
            }
        } while (URLtoShort.containsKey(sb.toString()));
        
        URLtoShort.put(longUrl, sb.toString());
        shortToURL.put(sb.toString(), longUrl);
        
        return baseURL + sb.toString();
    }
    

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        // example: shortUrl = "http://tinyurl.com/aREi56"
        // 只需取後半段aREi56，因為map只存後半段，前面baseURL沒存進去
        return shortToURL.get(shortUrl.substring(baseURL.length()));
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));