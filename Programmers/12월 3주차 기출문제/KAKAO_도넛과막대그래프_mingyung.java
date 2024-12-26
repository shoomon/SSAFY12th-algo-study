import java.util.*;
import java.io.*;

class KAKAO_도넛과막대그래프_mingyung {
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        
        // 최고 숫자 정점 찾기
        int max_v = 0;
        for (int i=0; i<edges.length; i++) {
            max_v = Math.max(max_v, Math.max(edges[i][0], edges[i][1]));
        }
        
        // 인접리스트 생성
        ArrayList<Integer>[] adjList = new ArrayList[max_v+1];
        for (int i=1; i<=max_v; i++) {
            adjList[i] = new ArrayList<>();
        }
        // 들어오는 간선 수 체크
        int[] indegree = new int[max_v+1];
        for (int i=0; i<edges.length; i++) {
            adjList[edges[i][0]].add(edges[i][1]);
            indegree[edges[i][1]]++;
        }
        
        // 간선이 많으면서 들어오는 간선이 없는 정점 찾기
        int v = 0;
        int max = 0;
        for (int i=1; i<=max_v; i++) {
            if (indegree[i] != 0) continue; // 들어오는 간선 있으면 넘어감
            if (adjList[i].size() > max) { // 들어오는 간선 수 많으면 정점 갱신
                v = i;
                max = adjList[i].size();
            }
        }
        
        answer[0] = v;
        
        // 그래프 모양 찾기
        // 시간복잡도 상에서 링크드리스트보다 유리하여 덱 사용
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        // 그래프의 개수는 추가된 정점의 간선의 개수와 동일함.
        boolean[] vis = new boolean[max_v+1];
        for (int i=0; i<adjList[v].size(); i++) {
            // 모든 간선 돌면서 정점의 개수와 간선의 개수 기록
            int start = adjList[v].get(i);
            int n = 1; // 정점의 개수
            int c = 0; // 간선의 개수
            vis[start] = true;
            ad.addLast(start);
            while (!ad.isEmpty()) {
                int cur = ad.pollFirst();
                int size = adjList[cur].size();
                c += size; // 간선의 개수 추가
                for (int j=0; j<size; j++) {
                    int next = adjList[cur].get(j);
                    if (vis[next]) continue; // 방문했다면 다음
                    vis[next] = true;
                    n++; // 정점의 개수 추가
                    ad.addLast(next);
                }
            }
            if (n == c) answer[1]++; // 도넛 모양
            else if (n-1 == c) answer[2]++; // 막대 모양
            else answer[3]++; // 8자모양
        }
        
        return answer;
    }
}