import java.util.*;

public class MakeSameSumQueue {
	public static void main(String[] args) {
		Solution sol = new Solution();
		int[] queue1 = {3, 2, 7, 2};
		int[] queue2 = {4, 6, 5, 1};
		int ans = sol.solution(null, null);
		System.out.println(ans);
	}
	
	public static class Solution {
	    public int solution(int[] queue1, int[] queue2) {
	        // 큐를 LinkedList로 변환
	        Queue<Long> q1 = new LinkedList<>();
	        Queue<Long> q2 = new LinkedList<>();
	        
	        // 큐의 합 계산용 변수
	        long sum1 = 0;
	        long sum2 = 0;
	        
	        // 초기 큐 설정 및 합 계산
	        for (int num : queue1) {
	            q1.add((long)num);
	            sum1 += num;
	        }
	        for (int num : queue2) {
	            q2.add((long)num);
	            sum2 += num;
	        }
	        
	        // 전체 합이 홀수면 불가능
	        long totalSum = sum1 + sum2;
	        if (totalSum % 2 != 0) {
	            return -1;
	        }
	        
	        // 목표 합
	        long target = totalSum / 2;
	        
	        // 작업 횟수
	        int count = 0;
	        // 최대 작업 횟수 제한 (두 큐 길이의 2배 정도면 충분)
	        int limit = queue1.length * 4;
	        
	        while (count <= limit) {
	            if (sum1 == target) {
	                return count;
	            }
	            
	            if (sum1 > target) {
	                // q1에서 q2로 이동
	                long num = q1.poll();
	                q2.add(num);
	                sum1 -= num;
	                sum2 += num;
	            } else {
	                // q2에서 q1로 이동
	                long num = q2.poll();
	                q1.add(num);
	                sum2 -= num;
	                sum1 += num;
	            }
	            count++;
	        }
	        
	        // 해결 불가능한 경우
	        return -1;
	    }
	}
}
