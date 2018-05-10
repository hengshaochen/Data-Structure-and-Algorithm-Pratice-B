class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
        // Time: O(m + n) / Space: O(n) 
        // 思路：用Hash，因為用O(mn)把所有掃一次太慢，不如用O(n)空間把其中一個放到Hash<餐廳名稱, Index>
        // 然後再掃另一個list，若遇到相同的，就和當前min比一下誰小，如果小就更新。
        // 維護cur_min_index和List<String> ans，如果遇到更小的就要把ans清空
        List<String> ans = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        HashMap<String, Integer> map = new HashMap<>();
        
        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }
        
        for (int i = 0; i < list2.length; i++) {
            String cur = list2[i];
            if (map.containsKey(cur)) {
                if (i + map.get(cur) < min) {
                    ans.clear(); // 清空之前的，因為找到更小的sum of index
                    min = i + map.get(cur);
                    ans.add(cur);
                } else if (i + map.get(cur) == min) {
                    // 如果長度相等，繼續加進去
                    ans.add(cur);
                }
            }
        } 
        // 把List<String>轉成String[]
        return ans.toArray(new String[ans.size()]);
    }
}