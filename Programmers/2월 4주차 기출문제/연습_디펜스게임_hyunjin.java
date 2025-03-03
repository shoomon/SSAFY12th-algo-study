package Programmers.PRO_2월_4주차;

import java.util.*;

public class 연습_디펜스게임_hyunjin {

        public int solution(int n, int k, int[] enemy) {
            int answer = 0;

            PriorityQueue<Integer> pq = new PriorityQueue<>();

            for(int i=0; i<enemy.length; i++){
                pq.add(enemy[i]);

                // k개 만큼 무적권 사용
                if(pq.size() <= k){
                    answer++;
                    continue;
                }

                // k보다 pq에 있는 갯수가 많을 경우, 가장 작은 수부터 빼기
                n -= pq.poll(); // 가장 작은 적의 수부터 poll

                // 남은 병사가 없으면 break
                if(n < 0) break;

                answer++;
            }
            return answer;
        }
}
