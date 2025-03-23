
import java.util.*;

public class HailstoneSequenceIntegral {
	public static void main(String[] args) {
		Solution sol = new Solution();
		int[][] ranges = {{0,0},{0,-1},{2,-3},{3,-3}};
		double[] ans = sol.solution(5, ranges);
		System.out.println(ans);
	}
	
	public static class Solution {
	    public double[] solution(int k, int[][] ranges) {
	        List<Integer> sequence = new ArrayList<>();
	        sequence.add(k);
	        int current = k;
	        // 1이 될때까지 콜라츠 방식을 반복
	        while (current != 1) {
	            if (current % 2 == 0) {
	                current /= 2;
	            } else {
	                current = current * 3 + 1;
	            }
	            sequence.add(current);
	        }
	        
	        int cnt = sequence.size() - 1;
	        
	        // 각 구간별 면적을 미리 구해놓음
	        double[] areas = new double[cnt];
	        for (int i = 0; i < cnt; i++)
	            areas[i] = (sequence.get(i) + sequence.get(i + 1)) / 2.0;
	        
	        // ranges별로 너비 계산
	        double[] answer = new double[ranges.length];
	        for (int i = 0; i < ranges.length; i++) {
	            int start = ranges[i][0];
	            int end = cnt + ranges[i][1];
	            
	            // 끝점이 시작점보다 먼저 오는 경우엔 넓이를 계산할 수 없음.
	            if (start > end) {
	                answer[i] = -1.0;
	                continue;
	            }
	            
	            // 구간이 전체 범위를 벗어나면 넓이를 계산할 수 없음.
	            if (start < 0 || end > cnt) {
	                answer[i] = -1.0;
	                continue;
	            }
	            
	            // 구간 면적 합산
	            double sum = 0.0;
	            for (int j = start; j < end; j++) sum += areas[j];
	            answer[i] = sum;
	        }
	        
	        return answer;
	    }
	}
}
