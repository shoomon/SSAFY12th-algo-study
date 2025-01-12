import java.util.*;
import java.io.*;

public class 연습_광물캐기_mingyung {
	// 한번에 캐는 광물 저장할 클래스
    static class group {
        int val;
        int diaCnt;
        int irCnt;
        int stCnt;
        
        public group(int val, int diaCnt, int irCnt, int stCnt) {
            this.val = val;
            this.diaCnt = diaCnt;
            this.irCnt = irCnt;
            this.stCnt = stCnt;
        }
    }
    
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        
        // 최대로 만들 수 있는 그룹 수
        int pN = picks[0] + picks[1] + picks[2];
        // 최대로 캘 수 있는 광물 수
        int mN = pN * 5;
        // 그룹 몇 개 나올까
        int len = minerals.length;
        int gN = mN < len ? pN : (len%5==0 ? len/5 : len/5 + 1);
        
        // PQ에 역순으로 저장하기
        PriorityQueue<group> pq = new PriorityQueue<>((o1, o2) -> o2.val - o1.val);
        // 5씩 증가하면서 group 만들기
        // 바깥for문 len 말고 gN*5로 진행해야 답이 나옴!
        for (int i=0; i<gN*5; i+=5) {
            int val = 0;
            int diaCnt=0, irCnt=0, stCnt=0;
            // 5개씩 안 끊길 때를 대비하여 i+j까지 처리
            for (int j=0; j<5 && i+j<len; j++) {
                switch (minerals[i+j]) {
                    case "diamond":
                        val += 25;
                        diaCnt++;
                        break;
                    case "iron":
                        val += 5;
                        irCnt++;
                        break;
                    case "stone":
                        val += 1;
                        stCnt++;
                        break;
                }
            }
            
            pq.offer(new group(val, diaCnt, irCnt, stCnt));
        }
        
        // 큐에서 빼면서 진행
        while (!pq.isEmpty()) {
            group cur = pq.poll();
            
            // 다이아 곡괭이 있으면 먼저 사용
            if (picks[0] > 0) {
                answer += cur.diaCnt + cur.irCnt + cur.stCnt;
                picks[0]--;
                continue;
            }
            // 철 곡괭이 있으면 사용
            else if (picks[1] > 0) {
                answer += 5*cur.diaCnt + cur.irCnt + cur.stCnt;
                picks[1]--;
                continue;
            }
            // 돌 곡괭이
            else {
                answer += cur.val;
                continue;
            }
            
        }
        
        return answer;
    }
}
