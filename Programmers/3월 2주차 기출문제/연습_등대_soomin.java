//연결된 노드 중 하나는 반드시 켜져있어야 한다. 가장 적은 노드를 켜는 방법.
import java.util.*;
class Solution {
    static List<Integer>[] adj;
    static int[][] dp;
    static boolean[] visited;
    
    public int solution(int n, int[][] lighthouse) {
        int answer = 0;
        adj = new List[n+1];
        dp = new int[n+1][2];
        visited = new boolean[n+1];
        
        for(int i = 0; i < n+1; i++) adj[i] = new ArrayList<>();
        
        for(int[] i : lighthouse){
            adj[i[0]].add(i[1]);
            adj[i[1]].add(i[0]);
        }
        
        dfs(1);
        
        answer = Math.min(dp[1][0], dp[1][1]);
        
        return answer;
    }
    
    static void dfs(int node){
        visited[node] = true;
        dp[node][0] = 0;
        dp[node][1] = 1;
        
        for(int i : adj[node]){
            if(!visited[i]){
                dfs(i);
                dp[node][0] += dp[i][1]; //현재 등대가 꺼져있으면 자식은 켜져있어야함.
                dp[node][1] += Math.min(dp[i][0], dp[i][1]);
            }
        }
    }
}