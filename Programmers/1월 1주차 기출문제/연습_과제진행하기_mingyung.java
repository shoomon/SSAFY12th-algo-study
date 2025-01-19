import java.util.*;
import java.io.*;

public class 연습_과제진행하기_mingyung {
	static class Work {
        String name;
        int start;
        int playtime;
        
        public Work(String name, int start, int playtime) {
            this.name = name;
            this.start = start;
            this.playtime = playtime;
        }
    }
    
    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];
        
        // 우선 시간 순으로 정렬하기
        PriorityQueue<Work> pq = new PriorityQueue<>((o1, o2) -> (o1.start - o2.start));
        for (int i=0; i<plans.length; i++) {
            String name = plans[i][0]; // 과제명
            // 시작 시간 분으로 바꿔 저장
            StringTokenizer st = new StringTokenizer(plans[i][1], ":");
            int start = 60 * Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());
            int playtime = Integer.parseInt(plans[i][2]);
            
            // PQ에 넣어 정렬
            pq.offer(new Work(name, start, playtime));
        }
        
        // 과제하기
        int idx = 0;
        // 다 처리하지 못한 과제 최신 순으로 정리하기 위해 Stack 사용
        Stack<Work> stack = new Stack<>();
        
        while (!pq.isEmpty()) {
            Work cur = pq.poll();
            int curT = cur.start; // 현재 시간
            
            // pq가 아직 비지 않았다면 다음 과제 체크하기
            if (!pq.isEmpty()) {
                Work next = pq.peek(); // 체크만 하기
                
                // 다음 과제 전까지 과제 수행 가능하면 과제 진행하기
                if (curT + cur.playtime < next.start) {
                    curT += cur.playtime;
                    answer[idx++] = cur.name;
                    while (!stack.isEmpty()) {
                        cur = stack.pop();
                        
                        // 똑같이 과제 수행 가능하면 과제 진행
                        if (curT + cur.playtime < next.start) {
                            curT += cur.playtime;
                            answer[idx++] = cur.name;
                            // continue;
                        }
                        
                        // 같으면 진행하고 벗어나기
                        else if (curT + cur.playtime == next.start) {
                            answer[idx++] = cur.name;
                            break;
                        }
                        
                        // 과제 수행 불가능하면 남은 시간만큼 진행하고 다시 넣기
                        else {
                            int t = cur.playtime - next.start + curT;
                            stack.add(new Work(cur.name, curT, t));
                            break;
                        }
                    }
                }
                
                // 같으면 다음 과제로 진행
                else if (curT + cur.playtime == next.start) {
                    answer[idx++] = cur.name;
                    continue;
                }
                
                // 불가능하면 가능한 시간만큼 과제하고 stack에 담기
                else {
                    int t = cur.playtime - next.start + curT;
                    stack.add(new Work(cur.name, curT, t));
                    continue;
                }
            }
            
            // 만약 다음 과제가 없다면 남은 과제 수행
            else {
                // 일단 지금 과제 수행
                answer[idx++] = cur.name;
                
                // stack에 남아있으면 계속 진행
                while (!stack.isEmpty()) {
                    cur = stack.pop();
                    answer[idx++] = cur.name;
                }
            }
        }
        
        return answer;
    }
}
