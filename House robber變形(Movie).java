    // Complete the maximizeRatings function below.
    static int maximizeRatings(int[] ratings) {
        // corner case: the array size <= 1
        if (ratings.length == 1) {
            return Math.max(0, ratings[0]);
        }
        
        
        int len = ratings.length;
        int[] dp = new int[len];
        
        // init
        dp[0] = ratings[0];
        dp[1] = Math.max(ratings[1], ratings[0] + ratings[1]);
            System.out.println(dp[0]);
            System.out.println(dp[1]);
        
        // trans function
        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2]) + ratings[i];
            System.out.println(dp[i]);
        }
        
        // ans
        return Math.max(dp[len - 1], dp[len - 2]);
    }

