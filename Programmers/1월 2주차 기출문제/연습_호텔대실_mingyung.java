import java.util.*;
import java.io.*;

public class 연습_호텔대실_mingyung {
	public int solution(String[][] book_time) {        
        // 시작 시간 순으로 정렬
        PriorityQueue<int[]> start = new PriorityQueue<>((o1, o2) -> o1[0]-o2[0]);
        // 배열 사이즈만큼 돌면서 int[시작시각, 종료시각]로 변환
        for (int i=0; i<book_time.length; i++) {
            StringTokenizer st = new StringTokenizer(book_time[i][0], ":");
            int s = 60 * Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());
            st = new StringTokenizer(book_time[i][1], ":");
            int e = 60 * Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());
            start.offer(new int[] {s, e});
        }
        
        // 끝나는 시간이 짧은 순으로 정렬하면서 관리
        PriorityQueue<Integer> end = new PriorityQueue<>();
        // 시작시간이 이른 것부터 돌면서 확인
        while (!start.isEmpty()) {
            // 첫 예약 확인
            int[] cur = start.poll();
            // 만약 end가 비어있지 않고,
            // 종료 시간보다 현재 시작 시간이 늦다면 그 객실 사용
            if (!end.isEmpty() && cur[0] >= end.peek()) {
                end.poll();
                end.offer(cur[1] + 10);
            }
            // 아니면 객실 하나 더 사용
            else end.offer(cur[1] + 10);
        }
        
        return end.size();
    }
}
