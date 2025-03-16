
import java.util.*;
import java.io.*;

public class 연습_등대_mingyung {
	ArrayList<Integer>[] graphs; // 그래프
    boolean[] visited, rights; // 방문체크 및 불 켜져있는지 체크
    int[] p; // 부모 노드 메모리
    int answer = 0;
    
    public int solution(int n, int[][] lighthouse) {
        graphs = new ArrayList[n+1]; // 등대가 1번부터
        visited = new boolean[n+1];
        rights = new boolean[n+1];
        p = new int[n+1];
        
        // 인접리스트
        for (int i=1; i<=n; i++) {
            graphs[i] = new ArrayList<>();
        }
        for (int i=0; i<lighthouse.length; i++) {
            graphs[lighthouse[i][0]].add(lighthouse[i][1]);
            graphs[lighthouse[i][1]].add(lighthouse[i][0]);
        }
        
        // 부모 노드 찾기
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        visited[1] = true;
        while (!q.isEmpty()) {
            int now = q.poll();
            for (int i=0; i<graphs[now].size(); i++) {
                int next = graphs[now].get(i);
                if (visited[next]) continue;
                visited[next] = true;
                p[next] = now;
                q.add(next);
            }
        }
        
        // 리프 노트부터 체크
        visited = new boolean[n+1];
        visited[1] = true;
        
        dfs(1);
        
        return answer;
    }
    
    void dfs(int now) {
        boolean ch = true;
        
        // 자신의 자식들을 모두 탐색
        for (int i=0; i<graphs[now].size(); i++) {
            int next = graphs[now].get(i);
            if (visited[next]) continue;
            visited[next] = true;
            dfs(next);
        }
        
        // 현재 자신의 자식이 다 켜져 있으면 자신은 안 켜줘도 됨
        for (int i=0; i<graphs[now].size(); i++) {
            int next = graphs[now].get(i);
            if (!rights[next]) ch = false;
        }
        if (ch) return;
        
        // 자신이 불이 켜져 있으면 넘어간다.
        if (rights[now]) return;
        
        // 자신이 불도 안 켜져 있고, 자신의 부모가 불이 안 켜져 있으면 부모의 불을 킨다.
        if (!rights[p[now]]) {
            answer++;
            rights[p[now]] = true;
        }
    }
}
