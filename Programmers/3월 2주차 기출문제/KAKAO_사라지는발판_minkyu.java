
public class MovingPlatform {
	public static void main(String[] args) {
		Solution sol = new Solution();
		int[][] board = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
		int[] aloc = {1,0};
		int[] bloc = {1,2};
		int ans = sol.solution(board, aloc, bloc);
		System.out.println(ans);
	}
	
	public static class Solution {
	    int rows, cols;
	    int[] dr = {0, 1, 0, -1};
	    int[] dc = {1, 0, -1, 0};

	    public int solution(int[][] board, int[] aloc, int[] bloc) {
	        rows = board.length;
	        cols = board[0].length;
	        return dfs(board, aloc[0], aloc[1], bloc[0], bloc[1], 0).moves;
	    }

	    private class Result {
	        boolean win;
	        int moves;
	        Result(boolean win, int moves) {
	            this.win = win;
	            this.moves = moves;
	        }
	    }

	    private Result dfs(int[][] board, int ar, int ac, int br, int bc, int turn) {
	        // 현재 턴의 플레이어 위치
	        int curR = (turn % 2 == 0) ? ar : br;
	        int curC = (turn % 2 == 0) ? ac : bc;

	        // 현재 위치에 발판이 없는 경우 패배
	        if (board[curR][curC] == 0) return new Result(false, 0); // 수정: 0으로 변경

	        boolean canMove = false;
	        int minWinMoves = Integer.MAX_VALUE;
	        int maxLoseMoves = 0;

	        // 상하좌우 이동 시도
	        for (int i = 0; i < 4; i++) {
	            int r = curR + dr[i];
	            int c = curC + dc[i];
	            if (r >= 0 && r < rows && c >= 0 && c < cols && board[r][c] == 1) {
	                canMove = true;
	                int[][] newBoard = copyBoard(board);
	                newBoard[curR][curC] = 0; // 현재 발판 제거

	                Result next;
	                if (turn % 2 == 0) next = dfs(newBoard, r, c, br, bc, turn + 1);
	                else next = dfs(newBoard, ar, ac, r, c, turn + 1);

	                if (!next.win) minWinMoves = Math.min(minWinMoves, 1 + next.moves);
	                else maxLoseMoves = Math.max(maxLoseMoves, 1 + next.moves);
	            }
	        }

	        // 이동 불가능하면 패배
	        if (!canMove) return new Result(false, 0);
	        // 이길 수 있으면 최소 이동으로 승리
	        if (minWinMoves != Integer.MAX_VALUE) return new Result(true, minWinMoves);
	        // 질 수밖에 없으면 최대한 오래 버팀
	        return new Result(false, maxLoseMoves);
	    }

	    private int[][] copyBoard(int[][] board) {
	        int[][] newBoard = new int[rows][cols];
	        for (int i = 0; i < rows; i++)
	            System.arraycopy(board[i], 0, newBoard[i], 0, cols);
	        return newBoard;
	    }
	}
}
