//25.03.03
//그리디
//무적권을 사용하는 시점 -> 지나온 라운드 중 적의 수가 가장 많을 때?
import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(
            Collections.reverseOrder()
        );
        int answer = 0;
        
        if(enemy.length <= k) return enemy.length;
        
        for(int i : enemy){
            n -= i;
            pq.offer(i);
            
            if(n < 0){
                if(k > 0){
                    n += pq.poll();
                    k--;
                }else{
                    break;
                }
            }
            answer++;
        }
        return answer;
    }
}