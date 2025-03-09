import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        long sum1=0, sum2=0;
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();
        
        for(int i : queue1) {
            q1.offer(i);
            sum1 += i;
        }
        for(int i : queue2) {
            q2.offer(i);
            sum2 += i;
        }
        
        if(sum1 == sum2) return 0;
        
        int tmp = q1.size()+q2.size();
        tmp *= 2;
        while(sum1 != sum2 && tmp-- > 0){
            int val=0;
            if(sum1 > sum2 && !q1.isEmpty()){
                val = q1.poll();
                q2.offer(val);
                sum1 -= val;
                sum2 += val;
                answer++;
            }else if(sum1 < sum2 && !q2.isEmpty()){
                val = q2.poll();
                q1.offer(val);
                sum2 -= val;
                sum1 += val;
                answer++;
            }
        }
        
        return sum1 == sum2 ? answer : -1;
    }
}