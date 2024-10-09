import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // 지도의 크기(N: 세로, M: 가로), 주사위 위치(R, C), 명령의 개수(K)
    static int N, M, R, C, K;
    // 지도 배열
    static int[][] map;
    // 방향 배열 (1: 동, 2: 서, 3: 북, 4: 남)
    static int[][] delta = {{}, {0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        // 지도 크기, 주사위 시작 위치, 명령 개수
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        // 지도 크기에 맞게 map 배열 생성
        map = new int[N][M];
        // 지도 정보 입력
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 명령어를 입력받기 위한 StringTokenizer
        st = new StringTokenizer(br.readLine());
        // 주사위 객체 생성 (초기 위치: R, C)
        Dice dice = new Dice(R, C);
        
        // 각 명령에 대해 처리
        for (int k = 0; k < K; k++) {
            int dir = Integer.parseInt(st.nextToken()); // 이동할 방향 입력
            // 주사위 이동할 위치 계산
            int nr = dice.r + delta[dir][0];
            int nc = dice.c + delta[dir][1];
            
            // 이동 가능한 위치인지 확인
            if (isIn(nr, nc)) {
                // 주사위 위치 갱신
                dice.r = nr;
                dice.c = nc;
                // 주사위를 굴리고 꼭대기 값을 가져옴
                int top = dice.roll(dir);
                // 결과를 저장
                sb.append(top).append('\n');
            }
        }
        // 결과 출력
        System.out.println(sb);
    }
    
    // 주사위가 지도의 범위를 벗어나지 않는지 확인
    private static boolean isIn(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < M;
    }

    // 주사위 클래스
    static class Dice {
        int r, c; // 주사위의 현재 위치
        // 주사위의 6면 배열 (index: 0-5)
        int[] dice = new int[6];
        // 주사위 면의 인덱스 상수
        int TOP = 0;
        int BOTTOM = 1;
        int FRONT = 2;
        int REAR = 3;
        int LEFT = 4;
        int RIGHT = 5;

        // 주사위의 초기 위치 설정
        public Dice(int r, int c) {
            this.r = r;
            this.c = c;
        }

        // 주사위를 주어진 방향으로 굴리는 함수
        public int roll(int d) {
            // 주사위를 동쪽으로 굴림
            if (d == 1) {
                rotate(BOTTOM, LEFT, TOP, RIGHT);
            // 주사위를 서쪽으로 굴림
            } else if (d == 2) {
                rotate(BOTTOM, RIGHT, TOP, LEFT);
            // 주사위를 북쪽으로 굴림
            } else if (d == 3) {
                rotate(BOTTOM, FRONT, TOP, REAR);
            // 주사위를 남쪽으로 굴림
            } else {
                rotate(BOTTOM, REAR, TOP, FRONT);
            }

            // 지도에서 현재 위치의 값이 0이면 주사위의 바닥 값을 복사
            if (map[r][c] == 0) {
                map[r][c] = dice[BOTTOM];
            // 지도에 값이 있으면 주사위에 그 값을 복사하고 지도 값은 0으로 변경
            } else {
                dice[BOTTOM] = map[r][c];
                map[r][c] = 0;
            }
            
            // 주사위의 꼭대기 값을 반환
            return dice[TOP];
        }

        // 주사위의 면을 회전시키는 함수 (4개의 면을 시계 방향으로 교체)
        private void rotate(int a, int b, int c, int d) {
            int tmp = dice[d];
            dice[d] = dice[c];
            dice[c] = dice[b];
            dice[b] = dice[a];
            dice[a] = tmp;
        }
    }
}
