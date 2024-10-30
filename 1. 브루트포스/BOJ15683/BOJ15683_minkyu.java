import java.util.*;
import java.io.*;

/*
감시

CCTV가 감시할 수 없는 사각지대의 수를 구하시오.

메모리 : 35608 KB
시간 : 252 ms

*/

public class Main {
    static int N;
    static int M;
    static int[][] map = {};
    static List<int[]> cctvPos = new ArrayList<>();
    
    static int minVal;
    
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int curVal = Integer.parseInt(st.nextToken());
                map[i][j] = curVal;
                // cctv 위치 저장
                if (curVal != 0 && curVal != 6)
                    cctvPos.add(new int[] {i,j});
            }
        }

        minVal = Integer.MAX_VALUE;
        boolean[][] isSurveiled = new boolean[N][M];
        recur(0, isSurveiled);
        System.out.println(minVal);
    }

    public static void recur(int curIdx, boolean[][] isSurveiled) {
        // 모든 cctv의 방향이 결정된 경우
        if (curIdx == cctvPos.size()) {
        	int curCnt = 0;
        	for (int i = 0; i < N; i++) {
        		for (int j = 0; j < M; j++) {
        			// 감시가 안되었으면서 벽이 아닌 곳이 사각지대
        			if (!isSurveiled[i][j] && map[i][j] == 0)
        				curCnt++;
        		}
        	}
        	// 사각지대의 최소값을 구한다.
        	minVal = Math.min(minVal, curCnt);
            return;
        }

        int cctv = map[cctvPos.get(curIdx)[0]][cctvPos.get(curIdx)[1]];
        switch(cctv) {
        // 4방향 모두 확인해야함
        case 1:
        case 3:
        case 4:
            for (int i = 0; i < 4; i++)
                recur(curIdx + 1, checkSurveilance(cctvPos.get(curIdx), cctv, i, isSurveiled));
            break;
        // 2 방향만 확인하면 됨
        case 2:
            for (int i = 0; i < 2; i++)
                recur(curIdx + 1, checkSurveilance(cctvPos.get(curIdx), cctv, i, isSurveiled));
            break;
        // 1 방향만 확인하면 됨
        case 5:
            recur(curIdx + 1, checkSurveilance(cctvPos.get(curIdx), cctv, 0, isSurveiled));
            break;
        }
    }

    public static boolean[][] checkSurveilance(int[] curPos, int cctv, int dir, boolean[][] isSurveiled){
        boolean[][] newArr = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++)
                newArr[i][j] = isSurveiled[i][j];
        }

        // 맨 처음 방향대로 무조건 먼저 진행
        int tmpR = curPos[0] + dr[dir];
        int tmpC = curPos[1] + dc[dir];
        while(0<=tmpR&&tmpR<N&&0<=tmpC&&tmpC<M) {
        	if (map[tmpR][tmpC] == 6) break;
        	newArr[tmpR][tmpC] = true;
        	tmpR += dr[dir];
        	tmpC += dc[dir];
        }

        // 2번과 5번 모두 뒤를 살펴봐야 한다.
        if (cctv == 2 || cctv == 5) {
        	int tmpDir = (dir + 2) % 4;
        	tmpR = curPos[0] + dr[tmpDir];
        	tmpC = curPos[1] + dc[tmpDir];
        	while(0<=tmpR&&tmpR<N&&0<=tmpC&&tmpC<M) {
            	if (map[tmpR][tmpC] == 6) break;
            	newArr[tmpR][tmpC] = true;
            	tmpR += dr[tmpDir];
            	tmpC += dc[tmpDir];
            }
        }
        
        // 오른쪽을 살펴봐야 한다.
        if (cctv == 3 || cctv == 4 || cctv == 5) {
        	int tmpDir = (dir + 1) % 4;
        	tmpR = curPos[0] + dr[tmpDir];
        	tmpC = curPos[1] + dc[tmpDir];
        	while(0<=tmpR&&tmpR<N&&0<=tmpC&&tmpC<M) {
            	if (map[tmpR][tmpC] == 6) break;
            	newArr[tmpR][tmpC] = true;
            	tmpR += dr[tmpDir];
            	tmpC += dc[tmpDir];
            }
        }
        
        // 왼쪽을 살펴봐야 한다.
        if (cctv == 4 || cctv == 5) {
        	int tmpDir = (dir + 3) % 4;
        	tmpR = curPos[0] + dr[tmpDir];
        	tmpC = curPos[1] + dc[tmpDir];
        	while(0<=tmpR&&tmpR<N&&0<=tmpC&&tmpC<M) {
            	if (map[tmpR][tmpC] == 6) break;
            	newArr[tmpR][tmpC] = true;
            	tmpR += dr[tmpDir];
            	tmpC += dc[tmpDir];
            }
        }

        return newArr;
    }
}