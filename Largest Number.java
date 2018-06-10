class Solution {
    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }
        
        // Step1: Integer array to String array
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        
        // Step2: 重寫Comparator
        Comparator<String> cmp = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                String str1 = s1 + s2;
                String str2 = s2 + s1;
                return str2.compareTo(str1);
            }
        };
        
        // Step3: sort
        Arrays.sort(strs, cmp);
        
        // Corner case:
        if (Integer.parseInt(strs[0]) == 0) {
            return "0";
        }
        
        // Step4: append
        StringBuilder sb = new StringBuilder();
        for (String cur : strs) {
            sb.append(cur);
        }
        
        
        return sb.toString();
    }
}