class Solution {

    int[][] board;
    int N;
    int M;
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        this.board = board;
        this.N = board.length;
        this.M = board[0].length;
        return dfs(aloc[0], aloc[1], bloc[0], bloc[1]);
    }

    public int dfs(int plyrX, int plyrY, int opX, int opY) {
        if (board[plyrX][plyrY] == 0) {
            return 0;
        }
        int ret = 0;
        for (int[] dir : dirs) {
            int nextX = plyrX + dir[0];
            int nextY = plyrY + dir[1];
            if (isOOB(nextX, nextY) || board[nextX][nextY] == 0) {
                continue;
            }
            board[plyrX][plyrY] = 0;
            int val = dfs(opX, opY, nextX, nextY) + 1;
            board[plyrX][plyrY] = 1;
            if(ret % 2 == 0 && val % 2 == 1) {
                ret = val;
            } else if(ret % 2 == 0 && val % 2 == 0) {
                ret = ret > val ? ret : val;
            } else if(ret % 2 == 1 && val % 2 == 1) {
                ret = ret < val ? ret : val;
            }
        }
        return ret;
    }

    public boolean isOOB(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M;
    }
}