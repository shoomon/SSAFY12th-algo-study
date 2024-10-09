import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*

2048 (Easy)

2048 게임에서 5회 움직이는 동안 만들 수 있는 수 중 가장 큰 수를 출

메모리 : 24672 KB
시간 : 216ms

 */

public class Main {
    static int N;
    static int[][] board = {};
    static int maxVal;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }
        
        move(0, board);
        System.out.println(maxVal);
    }

    public static void move(int cnt, int[][] curBoard) {
        if (cnt == 5) {
            int maxBlock = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++)
                    if (maxBlock < curBoard[i][j])
                        maxBlock = curBoard[i][j];
            }

            if (maxVal < maxBlock)
                maxVal = maxBlock;
            return;
        }

        // 사방탐색
        moveInDirection(cnt, curBoard, true);  // Up
        moveInDirection(cnt, curBoard, false); // Down
        moveInDirectionHorz(cnt, curBoard, true);  // Left
        moveInDirectionHorz(cnt, curBoard, false); // Right
    }

    private static void moveInDirection(int cnt, int[][] curBoard, boolean isPositive) {
        // Up or Down
        int[][] newBoard = new int[N][N];
        Deque<Integer> deq = new ArrayDeque<>();
        
        for (int c = 0; c < N; c++) {
            boolean hasAdded = false;
            for (int r = isPositive ? 0 : N - 1; isPositive ? r < N : r >= 0; r += (isPositive ? 1 : -1)) {
                if (curBoard[r][c] != 0) {
                    int curVal = curBoard[r][c];
                    if (deq.isEmpty()) {
                        deq.offerLast(curVal);
                        hasAdded = true;
                    } else {
                        if (deq.peekLast() == curVal && hasAdded) {
                            deq.offerLast(deq.pollLast() + curVal);
                            hasAdded = false;
                        } else {
                            deq.offerLast(curVal);
                            hasAdded = true;
                        }
                    }
                }
            }

            int size = deq.size();
            for (int r = 0; r < size; r++)
                newBoard[isPositive ? r : N - 1 - r][c] = deq.pollFirst();
        }
        
        move(cnt + 1, newBoard);
    }

    private static void moveInDirectionHorz(int cnt, int[][] curBoard, boolean isPositive) {
        // Left or Right
        int[][] newBoard = new int[N][N];
        Deque<Integer> deq = new ArrayDeque<>();
        
        for (int r = 0; r < N; r++) {
            boolean hasAdded = false;
            for (int c = isPositive ? 0 : N - 1; isPositive ? c < N : c >= 0; c += (isPositive ? 1 : -1)) {
                if (curBoard[r][c] != 0) {
                    int curVal = curBoard[r][c];
                    if (deq.isEmpty()) {
                        deq.offerLast(curVal);
                        hasAdded = true;
                    } else {
                        if (deq.peekLast() == curVal && hasAdded) {
                            deq.offerLast(deq.pollLast() + curVal);
                            hasAdded = false;
                        } else {
                            deq.offerLast(curVal);
                            hasAdded = true;
                        }
                    }
                }
            }

            int size = deq.size();
            for (int c = 0; c < size; c++)
                newBoard[r][isPositive ? c : N - 1 - c] = deq.pollFirst();
        }
        
        move(cnt + 1, newBoard);
    }
}