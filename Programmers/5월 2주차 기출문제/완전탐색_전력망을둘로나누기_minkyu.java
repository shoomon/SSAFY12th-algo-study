import java.util.*;

class Solution {
    List<Integer>[] adjList;
    
    private int dfs(int node, int parent, boolean[] visited) {
        visited[node] = true;
        int count = 1; 
        
        for (int next : adjList[node]) {
            if (!visited[next] && next != parent) {
                count += dfs(next, node, visited);
            }
        }
        return count;
    }
    
    public int solution(int n, int[][] wires) {
        int minDiff = n;
        
        adjList = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }
        
        // 모든 전선을 하나씩 끊어보며 최소 차이 계산
        for (int i = 0; i < wires.length; i++) {
            for (int j = 1; j <= n; j++) adjList[j].clear();
            
            for (int j = 0; j < wires.length; j++) {
                if (j == i) continue;
                int v1 = wires[j][0];
                int v2 = wires[j][1];
                adjList[v1].add(v2);
                adjList[v2].add(v1);
            }
            
            boolean[] visited = new boolean[n + 1];
            int subtreeSize = dfs(1, 0, visited);
            
            int otherSubtreeSize = n - subtreeSize;
            int diff = Math.abs(subtreeSize - otherSubtreeSize);
            
            minDiff = Math.min(minDiff, diff);
        }
        
        return minDiff;
    }
}