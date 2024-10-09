import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

class Solution{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 받기 위한 BufferedReader
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder(); // 출력 결과를 저장할 StringBuilder
    static int N, K, T, ans; // N: 맵 크기, K: 나무를 통과할 수 있는 최대 횟수, T: 테스트 케이스 수, ans: 정답(이동 횟수)
    static char[][] field; // 맵 정보를 저장할 2차원 배열
    static boolean[][][] vis; // 방문 여부를 저장하는 3차원 배열 (방향까지 고려)
    static int[] beg, end, cur; // beg: 시작점(X), end: 도착점(Y), cur: 현재 위치 및 상태 정보
    static Queue<int[]> q = new LinkedList<>(); // BFS에서 사용할 큐

    // 방향 벡터: 상(위), 우(오른쪽), 하(아래), 좌(왼쪽)
    static int[] dr = {-1, 0, 1, 0}; // 행 이동 (상, 우, 하, 좌)
    static int[] dc = {0, 1, 0, -1}; // 열 이동 (상, 우, 하, 좌)

    public static void main(String args[]) throws Exception
    {
        T = Integer.parseInt(br.readLine()); // 테스트 케이스 수 입력받기
        for(int t = 1; t <= T; t++) // 각 테스트 케이스 반복
        {
            sb.append("#").append(t).append(" "); // 테스트 케이스 번호 추가
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 맵 크기 N 입력
            K = Integer.parseInt(st.nextToken()); // 나무를 통과할 수 있는 횟수 K 입력
            field = new char[N][N]; // 맵 초기화
            vis = new boolean[N][N][4]; // 방문 배열 초기화 (각 칸마다 4개의 방향을 고려)
            for(int r=0; r<N; r++) { // 맵 정보 입력
                char[] row = br.readLine().toCharArray();
                for(int c=0; c<N; c++) {
                    field[r][c] = row[c];
                    if(field[r][c] == 'X') beg = new int[] {r, c}; // 시작점(X)의 좌표 저장
                    if(field[r][c] == 'Y') end = new int[] {r, c}; // 도착점(Y)의 좌표 저장
                }
            }
            solve(); // 문제 해결 함수 호출
            sb.append(ans == 0 ? -1 : ans).append("\n"); // 정답 출력 (-1은 도착 불가능)
            ans = 0; // 다음 테스트 케이스를 위해 초기화
            q.clear(); // 큐 초기화
        }
        System.out.println(sb); // 모든 테스트 케이스에 대한 결과 출력
    }

    static void solve() {
        // 시작점에서 상 방향(0)으로 출발, 나무 통과 횟수 0, 이동 횟수 0
        q.add(new int[] {beg[0], beg[1], 0, 0, 0});
        vis[beg[0]][beg[1]][0] = true; // 시작점 방문 표시 (상 방향)

        while(!q.isEmpty()) { // 큐가 비어있지 않으면 반복
            cur = q.poll(); // 현재 위치 정보 꺼내기
            int r = cur[0]; // 현재 행
            int c = cur[1]; // 현재 열
            int dir = cur[2]; // 현재 방향
            int tree = cur[3]; // 현재 나무 통과 횟수
            int move = cur[4]; // 현재 이동 횟수

            if(r == end[0] && c == end[1]) { // 도착점에 도달하면
                ans = move; // 이동 횟수 저장
                return; // 종료
            }

            // 현재 방향으로 한 칸 이동
            int nr = r + dr[dir];
            int nc = c + dc[dir];
            if(nr >= 0 && nr < N && nc >= 0 && nc < N && !vis[nr][nc][dir]) { // 맵을 벗어나지 않고, 방문하지 않은 곳이면
                if(field[nr][nc] == 'G' || field[nr][nc] == 'Y') { // 목적지 혹은 빈 땅일 경우
                    vis[nr][nc][dir] = true; // 방문 표시
                    q.add(new int[] {nr, nc, dir, tree, move + 1}); // 큐에 추가 (그 방향으로 계속 진행)
                }
                else if(field[nr][nc] == 'T' && tree < K) { // 나무가 있고, 통과 가능 횟수가 남아있다면
                    vis[nr][nc][dir] = true; // 방문 표시
                    q.add(new int[] {nr, nc, dir, tree + 1, move + 1}); // 나무 통과 횟수 증가하여 큐에 추가
                }
            }

            // 왼쪽으로 회전 (dir에서 -1 방향)
            int left = (dir + 3) % 4;
            if(!vis[r][c][left]) { // 아직 방문하지 않은 방향이면
                vis[r][c][left] = true; // 방문 표시
                q.add(new int[] {r, c, left, tree, move + 1}); // 왼쪽 방향으로 회전 후 큐에 추가
            }

            // 오른쪽으로 회전 (dir에서 +1 방향)
            int right = (dir + 1) % 4;
            if(!vis[r][c][right]) { // 아직 방문하지 않은 방향이면
                vis[r][c][right] = true; // 방문 표시
                q.add(new int[] {r, c, right, tree, move + 1}); // 오른쪽 방향으로 회전 후 큐에 추가
            }
        }
    }
}
