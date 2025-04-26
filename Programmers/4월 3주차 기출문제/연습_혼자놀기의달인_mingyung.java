
import java.util.*;
import java.io.*;

public class 연습_혼자놀기의달인_mingyung {
	boolean[] vis; // 방문 확인하기 위한 배열
    PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2-o1); // 내림차순으로 정렬
    
    void dfs(int[] cards, int idx, int cnt) {
        // 방문한 곳이면 return
        if (vis[idx]) {
            pq.add(cnt);
            return;
        }
        
        // 아니면 계속 진행
        vis[idx] = true;
        dfs(cards, cards[idx]-1, cnt+1);
    }
    
    public int solution(int[] cards) {
        int answer = 0;
        int len = cards.length;
        vis = new boolean[len];
        
        for (int i=0; i<len; i++) {
            if (vis[i]) continue;
            dfs(cards, i, 0);
        }
        
        // 상자 그룹이 하나만 나왔을 때 처리
        answer = (pq.size() == 1) ? 0 : (pq.poll() * pq.poll());
        
        return answer;
    }
}
