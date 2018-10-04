class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> curPath = new ArrayList<>();
        curPath.add(0);
        dfs(graph, ans, curPath, 0, graph.length - 1);
        return ans;
    }
    
    void dfs(int[][] graph, List<List<Integer>> ans, List<Integer> curPath, int curNode, int endNode) {
        //System.out.println(curPath);
        // base case
        if (curNode == endNode) {
            ans.add(new ArrayList<>(curPath));
            return;
        }
        
        for (Integer neighbor : graph[curNode]) {
            curPath.add(neighbor);
            dfs(graph, ans, curPath, neighbor, endNode);
            curPath.remove(curPath.size() - 1);
        }
    }
}