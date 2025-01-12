//25.01.12
//설계: 정답참조
//구현: 10분
//연속부분집합 50만*50만 = 2500만 X
//이전까지의 수열에 현재 수를 더 붙일 것인지 아닌지
class Solution {
    public long solution(int[] sequence) {
        long answer = 0;
        int N = sequence.length;
        long[][] dp = new long[N][2];
        
        dp[0][0] = sequence[0];
        dp[0][1] = -sequence[0];
        answer = Math.max(answer, Math.max(dp[0][0],dp[0][1]));
        
        for(int i = 1; i < N; i++){
            dp[i][0] = Math.max(sequence[i], dp[i-1][1]+sequence[i]);
            dp[i][1] = Math.max(-sequence[i], dp[i-1][0]-sequence[i]);
            answer = Math.max(answer, Math.max(dp[i][0],dp[i][1]));
        }
        return answer;
    }
}