
public class pulse_sum {
	public static void main(String[] args) {
		
	}
	
	static class Solution {
	    public long solution(int[] sequence) {
	        long answer = 0;
	        long dp[][] = new long[sequence.length][2];
	        dp[0][0] = sequence[0];
	        dp[0][1] = -sequence[0];
	        for(int i = 1; i < sequence.length; i++) {
	            dp[i][0] = Math.max(dp[i-1][1], 0) + sequence[i];
	            dp[i][1] = Math.max(dp[i-1][0], 0) - sequence[i];
	        }

	        for(long[] val : dp)
	            answer = Math.max(answer, Math.max(val[0], val[1]));

	        return answer;
	    }
	}
}
