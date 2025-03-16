import java.util.*;


public class LightHouse {
	public static void main(String[] args) {
		Solution sol = new Solution();
		int[][] lighthouse = {{1, 2}, {1, 3}, {1, 4}, {1, 5}, {5, 6}, {5, 7}, {5, 8}};
		int ans = sol.solution(8, lighthouse);
		System.out.println(ans);
	}
	
	public static class Solution {
	    public int solution(int n, int[][] lighthouse) {
	        // 인접 리스트 생성
	        List<List<Integer>> adjList = new ArrayList<>();
	        for (int i = 0; i <= n; i++) adjList.add(new ArrayList<>());
	        for (int[] edge : lighthouse) {
	        	adjList.get(edge[0]).add(edge[1]);
	        	adjList.get(edge[1]).add(edge[0]);
	        }
	        
	        // dp[노드][상태] = 최소 등대 개수
	        // 0 = 꺼짐, 1 = 켜짐
	        int[][] dp = new int[n + 1][2];
	        boolean[] visited = new boolean[n + 1];
	        
	        // 루트는 1번 노드로 가정하고 DFS 실행
	        dfs(1, adjList, dp, visited);
	        
	        // 루트 노드의 최소값 반환 (1번 노드가 꺼져있거나 켜져있을 경우 최소값)
	        return Math.min(dp[1][0], dp[1][1]);
	    }
	    
	    private void dfs(int node, List<List<Integer>> list, int[][] dp, boolean[] visited) {
	        visited[node] = true;
	        
	        // 리프 노드면 기본값 설정
	        dp[node][0] = 0; // 꺼진 경우
	        dp[node][1] = 1; // 켜진 경우
	        
	        for (int child : list.get(node)) {
	            if (!visited[child]) {
	                dfs(child, list, dp, visited);
	                // 현재 노드가 꺼져 있으면 모든 자식이 켜져 있어야 함
	                dp[node][0] += dp[child][1];
	                // 현재 노드가 켜져 있으면 자식은 켜지거나 꺼질 수 있음
	                dp[node][1] += Math.min(dp[child][0], dp[child][1]);
	            }
	        }
	    }
	}
}
