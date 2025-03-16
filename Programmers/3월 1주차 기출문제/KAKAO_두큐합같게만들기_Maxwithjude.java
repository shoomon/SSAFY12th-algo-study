import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> qu1 = new LinkedList<>();
        Queue<Integer> qu2 = new LinkedList<>();
        
        long sum1 = 0, sum2 = 0, total;
        
        // 큐 값 입력, 합 계산
        for (int i = 0; i < queue1.length; i++) {
             int num = queue1[i]; 
             qu1.offer(num);      
             sum1 += num;         
}
        for(int k = 0; k < queue2.length; k++){
            int nu = queue2[k];
            qu2.offer(nu);
            sum2 += nu;
        }
        
        total = sum1 + sum2;
        
        // 합이 홀수면 균형 불가능
        if (total % 2 != 0) return -1;
        
        long target = total / 2;
        int count = 0; // 이동 횟수 제한용
        int max = queue1.length * 3; // 최대 이동 가능 횟수
        
        while (sum1 != target) {
            if (count > max) return -1; // 최대 이동 횟수 초과 시 종료
            
            //1의 합이 타겟보다 작을때
            if (sum1 < target) {
                // queue2에서 pop하여 queue1으로 이동
                int value = qu2.poll();
                sum1 += value;
                sum2 -= value;
                qu1.offer(value);
            } else {
                // queue1에서 pop하여 queue2로 이동
                int value = qu1.poll();
                sum1 -= value;
                sum2 += value;
                qu2.offer(value);
            }
            count++;
        }
        
        return count;
    }
}
