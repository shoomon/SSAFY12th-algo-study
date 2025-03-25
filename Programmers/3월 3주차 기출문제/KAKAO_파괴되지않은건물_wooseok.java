package codingTest;

public class KAKAO_파괴되지않은건물_wooseok {
	public int solution(int[][] board, int[][] skill) {
		int answer = 0;

		int n = board.length;
		int m = board[0].length;

		// 변화량을 기록하는 memo 배열
		int [][] memo = new int [n + 1][m + 1];
		for(int i = 0; i < skill.length; i++){
			int degree = skill[i][5];
			int type = skill[i][0];
			int a = skill[i][1];
			int b = skill[i][2];
			int c = skill[i][3];
			int d = skill[i][4];
			if(type == 1){ // 공격
				memo[a][b] -= degree;
				memo[a][d+1] += degree;
				memo[c+1][b] += degree;
				memo[c+1][d+1] -= degree;
			}
			else{ // 회복
				memo[a][b] += degree;
				memo[a][d+1] -= degree;
				memo[c+1][b] -= degree;
				memo[c+1][d+1] += degree;
			}
		}

		// 변화량 누적합 구하기 왼 -> 오른
		for(int i = 0; i < n + 1; i++){
			for(int j = 0; j < m + 1; j++){
				if(j == 0) continue;
				memo[i][j] += memo[i][j-1];
			}
		}
		// 변화량 누적합 구하기 위 -> 아래
		for(int i = 0; i < n + 1; i++){
			if(i == 0) continue;
			for(int j = 0; j < m + 1; j++){
				memo[i][j] += memo[i-1][j];
			}
		}

		// 변화량 적용하기, 정답 구하기
		for(int i = 0; i < n; i++){
			for(int j = 0; j < m; j++){
				board[i][j] += memo[i][j];
				if(board[i][j] >= 1) answer++;
			}
		}

		return answer;
	}
}
