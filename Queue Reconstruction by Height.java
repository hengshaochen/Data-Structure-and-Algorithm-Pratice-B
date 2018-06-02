public class Solution {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (p1, p2) -> { 
            return p1[0] == p2[0] ? p1[1] - p2[1] : p2[0] - p1[0]; 
        }); 
        LinkedList<int[]> list = new LinkedList<>(); 
        for (int i = 0; i < people.length; i++)
            list.add(people[i][1], people[i]); 

        return list.toArray(people); 
    }
}