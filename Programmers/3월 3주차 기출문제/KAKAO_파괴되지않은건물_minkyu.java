public class UnbrokenBuilding {
    public static void main(String[] args) {

    }

    public static class Solution {
        public int solution(int[][] board, int[][] skill) {
            int rowSize = board.length;
            int colSize = board[0].length;
            int[][] diff = new int[rowSize + 1][colSize + 1];

            for (int[] s : skill) {
                int type = s[0];
                int r1 = s[1];
                int c1 = s[2];
                int r2 = s[3];
                int c2 = s[4];
                int degree = s[5];

                int value = (type == 1) ? -degree : degree;

                diff[r1][c1] += value;
                diff[r1][c2 + 1] -= value;
                diff[r2 + 1][c1] -= value;
                diff[r2 + 1][c2 + 1] += value;
            }

            for (int i = 0; i < rowSize; i++) {
                for (int j = 1; j < colSize; j++)
                    diff[i][j] += diff[i][j - 1];
            }

            for (int j = 0; j < colSize; j++) {
                for (int i = 1; i < rowSize; i++)
                    diff[i][j] += diff[i - 1][j];
            }

            int answer = 0;
            for (int i = 0; i < rowSize; i++) {
                for (int j = 0; j < colSize; j++) {
                    board[i][j] += diff[i][j];
                    if (board[i][j] > 0) answer++;
                }
            }

            return answer;
        }
    }
}
