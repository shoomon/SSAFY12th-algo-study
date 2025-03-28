class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int n = board.length;
        int m = board[0].length;

        int[][] map = new int[n + 1][m + 1];

        for (int[] s : skill) {
            int type = s[0];
            int r1 = s[1];
            int c1 = s[2];
            int r2 = s[3];
            int c2 = s[4];
            int degree = s[5];

            int value = (type == 1) ? -degree : degree;

            // 네 꼭짓점 기준 누적합 구하는 방법
            map[r1][c1] += value;
            map[r2 + 1][c1] -= value;
            map[r1][c2 + 1] -= value;
            map[r2 + 1][c2 + 1] += value;
        }

        // 가로 누적합 구하기
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < m; j++) {
                map[i][j] += map[i][j - 1];
            }
        }
        // 세로 누적합 구하기
        for (int j = 0; j < m; j++) {
            for (int i = 1; i < n; i++) {
                map[i][j] += map[i - 1][j];
            }
        }

        // for(int i=0; i<n; i++){
        //     for(int j=0; j<m; j++){
        //         System.out.print(map[i][j] + " ");
        //     }
        //     System.out.println();
        // }

        // 파괴되지 않은 건물 구하기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] += map[i][j];
                if (board[i][j] > 0) answer++;
            }

        }
        return answer;
    }
}