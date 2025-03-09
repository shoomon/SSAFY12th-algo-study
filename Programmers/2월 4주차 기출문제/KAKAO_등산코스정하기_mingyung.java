
import java.util.*;
import java.io.*;

// 실패 상태
public class KAKAO_등산코스정하기_mingyung {
	private static class Node {
        int e;
        int w;
        
        public Node(int e, int w) {
            this.e = e;
            this.w = w;
        }
    }
    
    private static List<Node>[] adjList;
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = new int[2];
        
        // 인접리스트
        adjList = new ArrayList[n+1];
        for (int i=1; i<=n; i++) {
            adjList[i] = new ArrayList<>();
        }
        
        for (int i=0; i<paths.length; i++) {
            int s = paths[i][0];
            int e = paths[i][1];
            int w = paths[i][2];
            
            adjList[s].add(new Node(e, w));
            adjList[e].add(new Node(s, w));
        }
        
        // 다익스트라
        Queue<Node> q = new LinkedList<>();
        int[] intensity = new int[n+1];
        
        Arrays.fill(intensity, Integer.MAX_VALUE);
        
        // 출입구 전부 큐에 넣기
        for (int i=0; i<gates.length; i++) {
            q.add(new Node(gates[i], 0));
            intensity[gates[i]] = 0; // 시작 지점은 0
        }
        
        while (!q.isEmpty()) {
            Node cur = q.poll();
            
            // 현재 가중치가 저장된 가중치보다 크다면 스킵
            if (cur.w > intensity[cur.e]) continue;
            
            for (int i=0; i<adjList[cur.e].size(); i++) {
                Node next = adjList[cur.e].get(i);
                
                // 최소 갱신
                int dis = Math.max(intensity[cur.e], next.w);
                if (intensity[next.e] > dis) {
                    intensity[next.e] = dis;
                    q.add(new Node(next.e, dis));
                }
            }
        }
        
        answer[0] = summits[0];
        answer[1] = intensity[summits[0]];
        
        for (int i=1; i<summits.length; i++) {
            if (answer[1] > intensity[summits[i]] && answer[0] > summits[i]) {
                answer[1] = intensity[summits[i]];
                answer[0] = summits[i];
            }
        }
        
        return answer;
    }
}
