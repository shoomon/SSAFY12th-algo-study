package 프로그래머스;

public class 연습_2차원동전뒤집기_mirim {

	class Solution {
	    public int solution(int[][] beginning, int[][] target) {
	    	
	        // 배열의 행과 열
	        int R = beginning.length;
	        int C = beginning[0].length;
	        
	        // 최소 뒤집기 횟수( 큰값으로 초기화 )
	        int answer = Integer.MAX_VALUE;
	        
	        // 비트마스킹
	        // 각 행은 뒤집거나 뒤집지 않는 2가지 상태, 경우의 수 : 2^R가지
	        // rowMask는 0부터 (1<<R)-1까지 순회.
	        for (int rowMask = 0; rowMask < (1 << R); rowMask++) {
	        	
	            // 배열 복사
	            int[][] crr = new int[R][C];
	            for (int i = 0; i < R; i++) {
	                for (int j	 = 0; j < C; j++) {
	                	crr[i][j] = beginning[i][j];
	                }
	            }
	            
	            // 행 뒤집기
	            // 1 -> 0 / 0 -> 1
	            for (int i = 0; i < R; i++) {
	                if ((rowMask & (1 << i)) != 0) { // rowMask의 i번째 비트 = 1
	                	
	                    // i번째 행 뒤집음
	                    for (int j = 0; j < C; j++) {
	                    	crr[i][j] = 1 - crr[i][j];
	                    }
	                }
	            }
	            
	            // 행 뒤집기를 완료
	            // target의 첫 번째 행과 crr의 첫 번째 행 비교
	            int colMask = 0;
	            for (int j = 0; j < C; j++) {
	            	
	                // 다르면 뒤집음
	                if (crr[0][j] != target[0][j]) {
	                    colMask |= (1 << j); // j번째 열을 뒤집는다는 의미로 colMask의 j번째 비트를 1로 설정
	                }
	            }
	            
	            // 열 뒤집기
	            for (int j = 0; j < C; j++) {
	                if ((colMask & (1 << j)) != 0) {
	                    // j번째 열을 뒤집음
	                    for (int i = 0; i < R; i++) {
	                    	crr[i][j] = 1 - crr[i][j];
	                    }
	                }
	            }
	            
	            // 모든 행과 열 뒤집기가 끝난 후, 최종 상태 crr가 target과 일치하는지 확인
	            boolean match = true;
	            out:for (int i = 0; i < R; i++) {
		                for (int j = 0; j < C; j++) {
		                	
		                    if (crr[i][j] != target[i][j]) { // 다를 경우
		                        match = false;
		                        break out;
		                    }
		                }
		            }
	            
	            // 일치할 경우, 뒤집은 횟수를 계산
	            if (match) {
	                // Integer.bitCount(숫자)는 해당 숫자를 이진수로 표현했을 때 1의 개수를 반환
	                // rowMask와 colMask에서 1의 개수가 각각 행과 열을 뒤집은 횟수
	                int flips = Integer.bitCount(rowMask) + Integer.bitCount(colMask);
	                
	                // 최소 뒤집기 횟수
	                answer = Math.min(answer, flips);
	            }
	        }
	        
	        // 매치 불가시 : -1을 반환
	        return (answer == Integer.MAX_VALUE) ? -1 : answer;
	    }
	}

	
}
