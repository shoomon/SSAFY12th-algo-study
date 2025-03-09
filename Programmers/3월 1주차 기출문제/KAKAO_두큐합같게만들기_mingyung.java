import java.util.*;
import java.io.*;

public class KAKAO_두큐합같게만들기_mingyung {
	public int solution(int[] queue1, int[] queue2) {
        int answer = -1;
        
        // 반복문 사이즈
        int size = queue1.length + queue2.length + 1;
        // 1번, 2번 큐 합계
        long sum1 = 0L;
        long sum2 = 0L;
        
        // 각 큐 만들기
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        // 각 큐의 합계 구하면서 큐 생성
        for (int num : queue1) {
            sum1 += num;
            q1.add(num);
        }
        for (int num : queue2) {
            sum2 += num;
            q2.add(num);
        }
        
        // 처음부터 함계가 같은 경우 0 리턴
        if (sum1 == sum2) return 0;
        
        // 이동 횟수 초기화
        int cnt = 0;
        
        // 사이즈보다 작거나 같고, 큐가 비어있지 않으면 수행
        while (cnt<=size && !q1.isEmpty() && !q2.isEmpty()) {
            // 합계에 따라 이동
            if (sum1 > sum2) {
                int num = q1.poll();
                q2.add(num);
                sum1 -= num;
                sum2 += num;
            } else {
                int num = q2.poll();
                q1.add(num);
                sum1 += num;
                sum2 -= num;
            }
            // 이동횟수 증가
            cnt++;
            // 합이 같아지면 return
            if (sum1 == sum2) return cnt;
        }
        
        return answer;
    }
}
