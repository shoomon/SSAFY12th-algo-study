
public class NumberTyping {
	public static void main(String[] args) {
		
	}
	
	public static class Solution {
	    // 자판의 좌표 설정 (0-9까지의 위치)
	    private static final int[][] POS = {
	        {3, 1}, // 0
	        {0, 0}, // 1
	        {0, 1}, // 2
	        {0, 2}, // 3
	        {1, 0}, // 4
	        {1, 1}, // 5
	        {1, 2}, // 6
	        {2, 0}, // 7
	        {2, 1}, // 8
	        {2, 2}  // 9
	    };
	    
	    // 두 위치 간의 가중치 계산
	    private int getCost(int from, int to) {
	        if (from == to) return 1; // 같은 위치면 가중치 1
	        
	        int[] posFrom = POS[from];
	        int[] posTo = POS[to];
	        
	        int dx = Math.abs(posFrom[0] - posTo[0]);
	        int dy = Math.abs(posFrom[1] - posTo[1]);
	        
	        if (dx == 0) return 2 * dy; // 수평 이동
	        if (dy == 0) return 2 * dx; // 수직 이동
	        
	        // 대각선 이동 포함
	        int min = Math.min(dx, dy);
	        int remain = Math.abs(dx - dy);
	        return 3 * min + 2 * remain;
	    }
	    
	    public int solution(String numbers) {
	        int n = numbers.length();
	        // dp[왼손위치][오른손위치][인덱스] = 최소 가중치
	        int[][][] dp = new int[10][10][n + 1];
	        
	        // dp 배열 초기화 (최대값으로)
	        for (int i = 0; i < 10; i++) {
	            for (int j = 0; j < 10; j++) {
	                for (int k = 0; k <= n; k++) {
	                    dp[i][j][k] = Integer.MAX_VALUE;
	                }
	            }
	        }
	        
	        // 초기 상태: 왼손 4, 오른손 6
	        dp[4][6][0] = 0;
	        
	        // 문자열을 하나씩 처리
	        for (int idx = 0; idx < n; idx++) {
	            int target = numbers.charAt(idx) - '0';
	            
	            for (int left = 0; left < 10; left++) {
	                for (int right = 0; right < 10; right++) {
	                    if (dp[left][right][idx] == Integer.MAX_VALUE) continue;
	                    if (left == right) continue; // 두 손가락이 같은 위치에 있을 수 없음
	                    
	                    // 1. 왼손으로 target 누르는 경우
	                    if (right != target) { // 오른손이 target에 있지 않을 때만
	                        int cost = getCost(left, target);
	                        dp[target][right][idx + 1] = Math.min(
	                            dp[target][right][idx + 1],
	                            dp[left][right][idx] + cost
	                        );
	                    }
	                    
	                    // 2. 오른손으로 target 누르는 경우
	                    if (left != target) { // 왼손이 target에 있지 않을 때만
	                        int cost = getCost(right, target);
	                        dp[left][target][idx + 1] = Math.min(
	                            dp[left][target][idx + 1],
	                            dp[left][right][idx] + cost
	                        );
	                    }
	                }
	            }
	        }
	        
	        // 최종 결과 계산
	        int answer = Integer.MAX_VALUE;
	        for (int left = 0; left < 10; left++) {
	            for (int right = 0; right < 10; right++) {
	                if (left != right) {
	                    answer = Math.min(answer, dp[left][right][n]);
	                }
	            }
	        }
	        
	        return answer;
	    }
	}
}
