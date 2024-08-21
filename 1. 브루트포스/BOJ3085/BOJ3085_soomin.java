import java.io.*;
public class BOJ3085_soomin {
    static char[][] map;
    static int[] dX = {0, 1, 0, -1};
    static int[] dY = {-1, 0, 1, 0};
    static int N, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        answer = Integer.MIN_VALUE;

        for(int i = 0; i < N; i++) {
            String s = br.readLine();
            for(int j = 0; j < N; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        simulation();
        System.out.println(answer);

    }

    static void simulation() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                //사방 탐색
                for(int k = 0; k < 4; k++) {
                    int nY = i+dY[k];
                    int nX = j+dX[k];
                    //맵 범위 벗어나는지 + 서로 다른 색인지 확인
                    if(check(nY,nX) && map[nY][nX] != map[i][j]) {
                        //두 자리 교환
                        char tmp = map[i][j];
                        map[i][j] = map[nY][nX];
                        map[nY][nX] = tmp;
                        //정답 업데이트
                        getMax();
                        //원상복구
                        tmp = map[i][j];
                        map[i][j] = map[nY][nX];
                        map[nY][nX] = tmp;
                    }
                }
            }
        }
    }

    static boolean check(int i, int j) {
        if(-1 < i && i < N && -1 < j && j < N) return true;
        return false;
    }

    static void getMax() {
        int maxCnt=0;
        //행 기준 최댓값 업데이트
        for(int i = 0; i < N; i++) {
            int tmp=1;
            for(int j = 0; j < N-1; j++) {
                if(map[i][j] == map[i][j+1]) {
                    tmp++;
                }else {
                    maxCnt = Math.max(maxCnt, tmp);
                    tmp=1;
                }
            }
            maxCnt = Math.max(maxCnt, tmp);
        }
        //열 기준 최댓값 없데이트
        for(int i = 0; i < N; i++) {
            int tmp=1;
            for(int j = 0; j < N-1; j++) {
                if(map[j][i] == map[j+1][i]) {
                    tmp++;
                }else {
                    maxCnt = Math.max(maxCnt, tmp);
                    tmp=1;
                }
            }
            maxCnt = Math.max(maxCnt, tmp);
        }
        //현재 맵의 최댓값과 비교하여 정답 값 업데이트
        answer = Math.max(answer, maxCnt);
    }
}