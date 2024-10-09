import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int maxBlock = 0;  // 최대 블록 값 저장
    private static int[][] board;     // 게임 보드 상태 저장
    private static int size;          // 보드 크기 저장

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(br.readLine());  // 보드 크기 입력 받기

        board = new int[size][size];  // 게임 보드 초기화
        for (int i = 0; i < size; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());  // 보드 데이터 입력
            }
        }

        solve(0);  // 깊이 우선 탐색 시작
        System.out.println(maxBlock);  // 결과 출력
    }

    // 깊이 우선 탐색을 통해 5번 이동 후 최대 블록 값을 구하는 함수
    private static void solve(int depth) {
        if (depth == 5) {  // 5번 이동 시 최대 블록 계산
            maxBlock = Math.max(maxBlock, getMaxBlock());  // 현재 보드에서 최대 블록 계산 후 갱신
            return;
        }

        int[][] originalBoard = copyBoard();  // 현재 보드 상태 저장

        for (int dir = 0; dir < 4; dir++) {  // 4가지 방향으로 이동 시도
            move(dir);  // 보드를 해당 방향으로 이동

            solve(depth + 1);  // 다음 깊이 탐색

            restoreBoard(originalBoard);  // 보드를 원래 상태로 복구
        }
    }

    // 보드를 주어진 방향으로 이동시키는 함수
    private static void move(int dir) {
        switch (dir) {
            case 0: shiftUp(); break;
            case 1: shiftDown(); break;
            case 2: shiftLeft(); break;
            case 3: shiftRight(); break;
        }
    }

    // 위로 이동하는 함수
    private static void shiftUp() {
        for (int col = 0; col < size; col++) {
            int[] newCol = new int[size];
            int idx = 0;
            boolean merged = false;
            for (int row = 0; row < size; row++) {
                if (board[row][col] != 0) {
                    if (idx > 0 && newCol[idx - 1] == board[row][col] && !merged) {
                        newCol[idx - 1] *= 2;
                        merged = true;
                    } else {
                        newCol[idx++] = board[row][col];
                        merged = false;
                    }
                }
            }
            for (int row = 0; row < size; row++) {
                board[row][col] = newCol[row];
            }
        }
    }

    // 아래로 이동하는 함수
    private static void shiftDown() {
        for (int col = 0; col < size; col++) {
            int[] newCol = new int[size];
            int idx = size - 1;
            boolean merged = false;
            for (int row = size - 1; row >= 0; row--) {
                if (board[row][col] != 0) {
                    if (idx < size - 1 && newCol[idx + 1] == board[row][col] && !merged) {
                        newCol[idx + 1] *= 2;
                        merged = true;
                    } else {
                        newCol[idx--] = board[row][col];
                        merged = false;
                    }
                }
            }
            for (int row = 0; row < size; row++) {
                board[row][col] = newCol[row];
            }
        }
    }

    // 왼쪽으로 이동하는 함수
    private static void shiftLeft() {
        for (int row = 0; row < size; row++) {
            int[] newRow = new int[size];
            int idx = 0;
            boolean merged = false;
            for (int col = 0; col < size; col++) {
                if (board[row][col] != 0) {
                    if (idx > 0 && newRow[idx - 1] == board[row][col] && !merged) {
                        newRow[idx - 1] *= 2;
                        merged = true;
                    } else {
                        newRow[idx++] = board[row][col];
                        merged = false;
                    }
                }
            }
            for (int col = 0; col < size; col++) {
                board[row][col] = newRow[col];
            }
        }
    }

    // 오른쪽으로 이동하는 함수
    private static void shiftRight() {
        for (int row = 0; row < size; row++) {
            int[] newRow = new int[size];
            int idx = size - 1;
            boolean merged = false;
            for (int col = size - 1; col >= 0; col--) {
                if (board[row][col] != 0) {
                    if (idx < size - 1 && newRow[idx + 1] == board[row][col] && !merged) {
                        newRow[idx + 1] *= 2;
                        merged = true;
                    } else {
                        newRow[idx--] = board[row][col];
                        merged = false;
                    }
                }
            }
            for (int col = 0; col < size; col++) {
                board[row][col] = newRow[col];
            }
        }
    }

    // 보드를 복사하는 함수
    private static int[][] copyBoard() {
        int[][] newBoard = new int[size][size];
        for (int i = 0; i < size; i++) {
            System.arraycopy(board[i], 0, newBoard[i], 0, size);
        }
        return newBoard;
    }

    // 보드를 원래 상태로 복구하는 함수
    private static void restoreBoard(int[][] originalBoard) {
        for (int i = 0; i < size; i++) {
            System.arraycopy(originalBoard[i], 0, board[i], 0, size);
        }
    }

    // 현재 보드에서 가장 큰 블록 값을 반환하는 함수
    private static int getMaxBlock() {
        int max = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                max = Math.max(max, board[i][j]);
            }
        }
        return max;
    }
}
