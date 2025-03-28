
import java.util.*;
import java.io.*;

public class 연습_부대복귀_mingyung {
	int INF = 987654321;
    List<Integer>[] adjList;
    int[] dist;
    boolean[] vis;
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        
        // 인접리스트
        adjList = new ArrayList[n+1];
        for (int i=1; i<=n; i++) {
            adjList[i] = new ArrayList<>();
        }
        
        dist = new int[n+1];
        Arrays.fill(dist, INF);
        
        for (int i=0; i<roads.length; i++) {
            adjList[roads[i][0]].add(roads[i][1]);
            adjList[roads[i][1]].add(roads[i][0]);
        }
        
        vis = new boolean[n+1];

        dijkstra(destination);
        
        for (int i=0; i<sources.length; i++) {
            if (dist[sources[i]] == INF) answer[i] = -1;
            else answer[i] = dist[sources[i]];
        }
        
        return answer;
    }
    
    // 다익스트라
    void dijkstra(int start) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1]-o2[1]);
        
        dist[start] = 0;
        
        pq.add(new int[] {start, 0});
        
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            
            if (vis[cur[0]]) continue;
            vis[cur[0]] = true;
            
            for (int i : adjList[cur[0]]) {
                if (!vis[i] && dist[i] > dist[cur[0]]+1) {
                    dist[i] = dist[cur[0]] + 1;
                    pq.add(new int[] {i, dist[i]});
                }
            }
        }
    }
}
