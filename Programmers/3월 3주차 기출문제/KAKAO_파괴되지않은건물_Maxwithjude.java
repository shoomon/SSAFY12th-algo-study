class Solution {
    public int solution(int[][] board, int[][] skill) {
        int n = board.length;        // 행의 개수
        int m = board[0].length;     // 열의 개수
        int cnt = 0;  // 파괴되지 않은 건물 개수

        for (int i = 0; i < skill.length; i++) {
            // 적의 공격이면
            if (skill[i][0] == 1) {
                attack(board, skill[i]);  // 오타 수정
            } else {
                healing(board, skill[i]);
            }
        }

        // 파괴되지 않은 건물 개수 계산
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] >= 1) cnt++;
            }
        }

        return cnt;
    }

    // 공격
    static void attack(int arr[][], int sk[]) {
        int a = sk[1];
        int b = sk[2];
        int c = sk[3];
        int d = sk[4];
        int amount = sk[5];

        for (int i = a; i <= c; i++) {
            for (int j = b; j <= d; j++) {
                arr[i][j] -= amount;
            }
        }
    }

    // 회복
    static void healing(int arr[][], int sk2[]) {
        int a = sk2[1];
        int b = sk2[2];
        int c = sk2[3];
        int d = sk2[4];
        int amount = sk2[5];

        for (int i = a; i <= c; i++) {
            for (int j = b; j <= d; j++) {
                arr[i][j] += amount;
            }
        }
    }
}
