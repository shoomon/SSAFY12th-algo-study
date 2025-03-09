import java.util.*;
import java.io.*;

public class 연습_귤고르기_mingyung {
	public int solution(int k, int[] tangerine) {
        int answer = 0;
        
        // 각 원소 갯수 카운팅
        // 우선 최댓값 찾기
        int max = 0;
        for (int i : tangerine) {
            if (max < i) max=i;
        }
        // 카운트 배열
        int[] count = new int[max+1];
        for (int i : tangerine) {
            count[i]++;
        }
        
        // 쌍으로 우선순위큐에 넣기 (갯수가 많은게 앞으로 오도록)
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[1]-o1[1]);
        for (int i=0; i<max+1; i++) {
            pq.add(new int[] {i+1, count[i]});
        }
        // 이제 빼면서 구해보자
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            k -= cur[1];
            answer++;
            if (k <= 0) break;
        }
        
        return answer;
    }
}
