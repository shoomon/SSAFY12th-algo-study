import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder(); // 결과를 저장할 StringBuilder
    static int T, N, Q, C, dir; // 테스트 케이스 수 T, 필드 크기 N, 명령어 수 Q, 명령어 C, 방향 dir
    static int[] dr = {-1, 0, 1, 0}; // 상, 우, 하, 좌 방향을 나타내는 행 변화량
    static int[] dc = {0, 1, 0, -1}; // 상, 우, 하, 좌 방향을 나타내는 열 변화량
    static char[][] field; // 필드를 저장하는 2차원 배열
    static int[] beg, end, cur; // 시작점, 도착점, 현재 위치를 저장하는 배열
    static char[] cmd; // 명령어 배열

    public static void main(String args[]) throws Exception {
        T = Integer.parseInt(br.readLine()); 
        for (int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" "); // 각 테스트 케이스 번호 출력
            N = Integer.parseInt(br.readLine()); // 필드 크기 입력
            field = new char[N][N]; // 필드 초기화
            for (int r = 0; r < N; r++) {
                char[] row = br.readLine().toCharArray(); // 필드의 한 행 입력
                for (int c = 0; c < N; c++) {
                    field[r][c] = row[c]; // 필드에 값 저장
                    if (field[r][c] == 'X') beg = new int[] {r, c}; // 시작점 찾기
                    if (field[r][c] == 'Y') end = new int[] {r, c}; // 도착점 찾기
                }
            }
            Q = Integer.parseInt(br.readLine()); // 명령어 수 입력
            for (int i = 0; i < Q; i++) {
                st = new StringTokenizer(br.readLine());
                C = Integer.parseInt(st.nextToken()); // 명령어 횟수 입력
                cmd = st.nextToken().toCharArray(); // 명령어 배열 입력
                solve(); // 명령어 실행
            }
            sb.append("\n"); // 한 테스트 케이스 끝난 후 줄바꿈
        }
        System.out.println(sb); // 결과 출력
    }

    // 명령어를 실행하는 함수
    static void solve() {
        cur = new int[] {beg[0], beg[1]}; // 현재 위치를 시작점으로 초기화
        dir = 0; // 방향 초기화 (0: 상, 1: 우, 2: 하, 3: 좌)
        for (int i = 0; i < C; i++) {
            char now = cmd[i]; // 현재 명령어
            switch (now) {
                case 'A': // 앞으로 이동
                    int nr = cur[0] + dr[dir]; // 이동할 행 계산
                    int nc = cur[1] + dc[dir]; // 이동할 열 계산
                    if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue; // 범위 밖이면 무시
                    if (field[nr][nc] == 'T') continue; // 나무(T)가 있으면 무시
                    cur[0] = nr; // 현재 위치 갱신
                    cur[1] = nc; // 현재 위치 갱신
                    break;
                case 'L': // 왼쪽으로 회전
                    dir = (dir + 3) % 4; // 왼쪽 회전은 방향을 반시계로 한 칸 이동
                    break;
                case 'R': // 오른쪽으로 회전
                    dir = (dir + 1) % 4; // 오른쪽 회전은 방향을 시계로 한 칸 이동
                    break;
            }
        }
        // 명령어 수행 후 최종 위치가 도착점과 일치하면 1, 아니면 0을 출력 결과에 추가
        sb.append(cur[0] == end[0] && cur[1] == end[1] ? 1 : 0).append(" ");
    }
}
