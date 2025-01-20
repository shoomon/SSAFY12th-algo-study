package codingTest;

public class 연습_혼자서하는틱택토_wooseok {
    private static final int[] dx = {0, 1, 1, -1}; // 가로, 세로, 대각선 우하단, 대각선 우상단
    private static final int[] dy = {1, 0, 1, 1}; // 가로, 세로, 대각선 우하단, 대각선 우상단
    private static int n;

    public static int solution(String[] board) {
        n = board.length;
        int oCount = 0, xCount = 0;

        // 'O'와 'X'의 개수를 세기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i].charAt(j) == 'O') oCount++;
                if (board[i].charAt(j) == 'X') xCount++;
            }
        }

        // 기본 규칙 위반 체크: 'O'와 'X' 개수 차이는 0 또는 1만 가능
        if (oCount < xCount || oCount - xCount > 1) return 0;

        // 'O'와 'X'의 직선 존재 여부 확인
        boolean oLine = checkLine(board, 'O');
        boolean xLine = checkLine(board, 'X');

        // 규칙에 따라 결과 계산
        if (oLine && xLine) return 0; // 둘 다 직선이 있으면 불가능
        if (oLine && oCount == xCount) return 0; // 'O'가 직선을 만들었지만 'X'와 개수가 같으면 불가능
        if (xLine && oCount > xCount) return 0; // 'X'가 직선을 만들었지만 'O'가 더 많으면 불가능

        return 1; // 규칙에 어긋나지 않으면 가능
    }

    private static boolean checkLine(String[] board, char c) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i].charAt(j) == c) {
                    // 4방향으로 직선 확인
                    for (int d = 0; d < 4; d++) {
                        if (isValidLine(board, c, i, j, dx[d], dy[d])) return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean isValidLine(String[] board, char c, int x, int y, int dx, int dy) {
        int count = 0;
        for (int i = 0; i < 3; i++) { // 길이 3인 직선만 확인
            int nx = x + dx * i;
            int ny = y + dy * i;

            if (nx < 0 || ny < 0 || nx >= n || ny >= n || board[nx].charAt(ny) != c) {
                return false;
            }
            count++;
        }
        return count == 3; // 직선 길이가 3인지 확인
    }


    public static void main(String[] args) {
        String[] board1 = {"O.X", ".O.", "..X"};
        String[] board2 = {"OOO", "...", "XXX"};
        String[] board3 = {"...", "...", "..."};

        System.out.println(solution(board1)); // 기대값: 1
        System.out.println(solution(board2)); // 기대값: 0
        System.out.println(solution(board3)); // 기대값: 0
    }
}
