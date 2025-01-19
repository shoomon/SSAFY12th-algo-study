
public class ricochat {
	public static void main(String[] args) {
		Solution sol = new Solution();
		String[] board = { "...D..R", ".D.G...", "....D.D", "D....D.", "..D...." };
		int ans = sol.solution(board);
		System.out.println(ans);
	}

	static class Solution {
		char[][] map;
		int row, col;
		int min = Integer.MAX_VALUE;
		int[] dr = {1,-1,0,0};
		int[] dc = {0,0,1,-1};
		
		boolean[][] visited;
		int[] endPos = new int[2];
		public int solution(String[] board) {
			int answer = 0; 
			row = board.length;
			col = board[0].length();
			map = new char[row][col];
			visited = new boolean[row][col];
			int[] startPos = new int[2];
			// map에 보드에 대한 상황 기록
			for (int i = 0; i < row; i++) {
				String curLine = board[i];
				for (int j = 0; j < col; j++) {
					char curChar = curLine.charAt(j);
					if (curChar == 'R') {
						startPos[0] = i;
						startPos[1] = j;
					} else if (curChar == 'G') {
						endPos[0] = i;
						endPos[1] = j;
					} else if (curChar == 'D')
						map[i][j] = curLine.charAt(j);
				}
			}
			
			recur(startPos, 0);
			
			// 밖으로 나가는 경우를 찾지 못한 경우는 제외
			return min == Integer.MAX_VALUE ? -1 : min;
		}
		
		public void recur(int[] curPos, int moveCnt) {
			// 이미 최소로 도달한 횟수를 초과하는 경우 초기화.
			if (min < moveCnt) return;
			// 해당 위치가 마지막인 경우 종료
			if (curPos[0] == endPos[0] && curPos[1] == endPos[1]) {
				min = Math.min(min, moveCnt);
				return;
			}			
			// 4방향으로 먼저 이동해보기.
			for (int i = 0; i < 4; i++) {
				int r = curPos[0] + dr[i];
				int c = curPos[1] + dc[i];
				// 해당 위치로 이동시, 벽 또는 가장자리인 경우 의미없는 것이기때문에 가지치기
				if (0>r||r>=row||0>c||c>=col||map[r][c]=='D') continue;
				// 벽에 부딪히거나, 경기장 가장자리에 부딪히는 경우까지 이동 
				while(true) {
					// 부딪힌 경우
					if (0>r||r>=row||0>c||c>=col||map[r][c]=='D') {
						int curR = r-dr[i];
						int curC = c-dc[i];
						// 이미 해당 위치에 방문한 적이 있으면 종료 (백트래킹)
						if (visited[curR][curC]) break;
						visited[curR][curC] = true;
						recur(new int[] {r - dr[i] ,c - dc[i]}, moveCnt+1);
						visited[curR][curC] = false;
						break;
					}
					r += dr[i]; c += dc[i];
				}
			}
		}
	}
}
