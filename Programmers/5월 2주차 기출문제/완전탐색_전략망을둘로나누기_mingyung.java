
import java.util.*;
import java.io.*;

public class 완전탐색_전략망을둘로나누기_mingyung {
	public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        
        // 1부터 시작하니 n+1
        ArrayList<Integer>[] graph = new ArrayList[n+1];
        for (int i=1; i<=n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int i=0; i<n-1; i++) {
            int a = wires[i][0];
            int b = wires[i][1];
            
            graph[a].add(b);
            graph[b].add(a);
        }
        
        // 하나씩 제거하면서 체크하기
        for (int i=0; i<n-1; i++) {
            int a = wires[i][0];
            int b = wires[i][1];
            
            boolean[] vis = new boolean[n+1];
            
            /* 이거는 인덱스로 인식해서 오류남!
            graph[a].remove(b);
            graph[b].remove(a);
            */
            graph[a].remove(Integer.valueOf(b));
            graph[b].remove(Integer.valueOf(a));
            
            // 한쪽에 연결된 노드 체크
            Queue<Integer> q = new LinkedList<>();
            q.add(1);
            vis[1] = true;
            int cnt = 1;
            while (!q.isEmpty()) {
                int next = q.poll();
                
                for (int now : graph[next]) {
                    if (!vis[now]) {
                        cnt += 1;
                        vis[now] = true;
                        q.add(now);
                    }
                }
            }
            
            int diff = Math.abs(cnt - (n - cnt));
            answer = Math.min(answer, diff);
            
            graph[a].add(b);
            graph[b].add(a);
        }
        
        return answer;
    }
}
