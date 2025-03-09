import java.util.*;
import java.io.*;

public class SelectTangerine {
	public static void main(String[] args) {
		Solution sol = new Solution();
		int[] tangerine = {1, 3, 2, 5, 4, 5, 2, 3};
		int ans = sol.solution(6, tangerine);
		System.out.println(ans);
	}
	
	public static class Solution {
	    public int solution(int k, int[] tangerine) {
	        int answer = 0;
	        
	        // 귤의 개수 정보를 저장
	        Map<Integer, Integer> cntInfo = new HashMap<>();
	        for (int tang : tangerine) {
	        	if (cntInfo.containsKey(tang)) cntInfo.put(tang, cntInfo.get(tang) + 1);
	        	else cntInfo.put(tang, 1);
	        }
	        
	        // 역 순으로 개수를 저장할 수 있도록 설정
	        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
	        // 귤의 개수 정보를 순서대로 저장
	        for (int size : cntInfo.keySet()) pq.offer(cntInfo.get(size));
	        
	        while(k > 0) {
	        	answer++;
	        	k -= pq.poll();
	        }
	        
	        return answer;
	    }
	}
}
