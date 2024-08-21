package BOJ3085;

import java.io.*;
import java.util.*;
public class BOJ3085_wooseok {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 보드의 크기
        int N = Integer.parseInt(br.readLine());

        // 보드 생성
        char[][] board = new char[N][N];
        for (int i = 0; i < N; i++) {
            String candy = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = candy.charAt(j);
            }
        }

        // 먹을 수 있는 최대 사탕 개수
        int max = 0;

        // 초기 상태에서 최대 사탕 개수 확인
        max = eatCandy(board, max);

        // 행 단위로 순회하며, 인접한 캔디를 교환하여 최댓값 찾기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N - 1; j++) {
                // 캔디 교환
                char tmp = board[i][j];
                board[i][j] = board[i][j + 1];
                board[i][j + 1] = tmp;

                // 최대 사탕 개수 확인
                max = eatCandy(board, max);

                // 다시 원상복구
                tmp = board[i][j];
                board[i][j] = board[i][j + 1];
                board[i][j + 1] = tmp;
            }
        }

        // 열 단위로 순회하며 인접한 캔디를 교환하고 최댓값 찾기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N - 1; j++) {
                // 세로로 인접한 두 문자 교환
                char temp = board[j][i];
                board[j][i] = board[j + 1][i];
                board[j + 1][i] = temp;

                // 최대 사탕 개수 확인
                max = eatCandy(board, max);

                // 다시 원상복구
                temp = board[j][i];
                board[j][i] = board[j + 1][i];
                board[j + 1][i] = temp;
            }
        }

        System.out.println(max);
    }

    // 사탕을 먹기 위한 탐색 - 행 단위/ 열 단위
    static int eatCandy(char[][] board, int max) {
        // row 체크하며 카운트하기
        for (int i = 0; i < board.length; i++) {
            int count = 1;
            for (int j = 0; j < board[0].length - 1; j++) {
                if (board[i][j] == board[i][j + 1]) { // 다음 캔디도 먹을 수 있음
                    count++;
                } else {
                    count = 1; // 다음 캔디를 못 먹으니까 다시 1부터 시작
                }
                max = Math.max(count, max); // 최대값 갱신
            }
        }

        // col 체크하며 카운트하기
        for (int i = 0; i < board[0].length; i++) {
            int count = 1;
            for (int j = 0; j < board.length - 1; j++) {
                if (board[j][i] == board[j + 1][i]) { // 같은 열에 있는 사탕 비교
                    count++;
                } else {
                    count = 1; // 다음 캔디를 못 먹으니까 다시 1부터 시작
                }
                max = Math.max(count, max); // 최대값 갱신
            }
        }

        return max;
    }
}
