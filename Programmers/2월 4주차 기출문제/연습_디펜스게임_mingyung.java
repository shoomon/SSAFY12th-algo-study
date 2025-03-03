
import java.util.*;
import java.io.*;

public class 연습_디펜스게임_mingyung {
	public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        
        // 일단 k가 enemy 길이 보다 길거나 같으면 답은 정해져있다.
        if (k >= enemy.length) answer = enemy.length;
        else {
            // 일단 k개 그냥 담아
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for (int i=0; i<k; i++) {
                pq.add(enemy[answer++]);
            }
            // pq에서 꺼내면서 비교하기
            while (answer < enemy.length) {
                int tmp = pq.peek();

                // pq에 있는 값보다 크면
                if (tmp < enemy[answer]) {
                    pq.poll();
                    pq.add(enemy[answer++]);
                    n -= tmp;
                }
                // 아니면 걍 뺌
                else {
                    n -= enemy[answer++];
                }

                if (n < 0) {
                    answer--;
                    break;
                }
                else if (n == 0) {
                    break;
                }
            }
        }
        
        return answer;
    }
}
