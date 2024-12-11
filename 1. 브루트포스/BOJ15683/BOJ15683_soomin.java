import java.io.*;
import java.util.*;
//24.10.21
//설계 시간: 분
//구현 시간: 분
//메모리: 101580 kb
//시간: 904 ms
//cctv 8개, 각 방향 4개, 맵 크기 64 = 32*64 = 2^11 시뮬
//아니네 방향만 정하면 되네
public class Main {
    static int N,M, answer, totalCCTV;
    static int[][] map, simulMap;
    static int[] dY = {-1, 0, 1, 0};
    static int[] dX = {0, 1, 0, -1};
    static int[][] cctv = {{1},{1,3},{0,1},{0,1,3},{0,1,2,3}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        answer = Integer.MAX_VALUE;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        simulMap = new int[N][M];
        totalCCTV = 0;

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(0 < map[i][j] && map[i][j] < 6) totalCCTV++;
            }
        }

        copyMap();
        simulation(0,0);
        bw.write(answer+"\n");

        bw.close();
    }

    static void simulation(int depth, int cur) {
//        System.out.println(depth);
//        printMap();
        if(depth == totalCCTV) {
            updateCnt();
            return;
        }

        boolean[][] visited = new boolean[N][M];

        for(int i = cur/M; i < N; i++) {
            for(int j = (i == cur/M ? cur%M : 0); j < M; j++) {
//                System.out.println(i+" "+j);
                if(map[i][j] == 1) {
                    for(int k = 0; k < 4; k++) {
                        for(int dir : cctv[0]) {
                            int d = (dir+k)%4;
//                            System.out.println(dir+" "+d);
                            int nY = i;
                            int nX = j;
                            //각 방향마다 # 처리
                            while(true) {
                                nY += dY[d];
                                nX += dX[d];
                                if(!check(nY,nX) || map[nY][nX] == 6) break;
                                if(0 < simulMap[nY][nX] && simulMap[nY][nX] < 6 || simulMap[nY][nX] == -1) continue;
                                visited[nY][nX] = true;
                                simulMap[nY][nX] = -1;
                            }
                        }
                        //재귀 돌고
                        simulation(depth+1, i*M+j+1);
                        //나와서 원복
                        for(int a = 0; a < N; a++){
                            for(int b = 0; b < M; b++){
                                if(visited[a][b]) simulMap[a][b] = 0;
                            }
                        }
                    }
                }else if(map[i][j] == 2) {
                    for(int k = 0; k < 2; k++) {
                        for(int dir : cctv[1]) {
                            int d = (dir+k)%4;
//                            System.out.println(dir+" "+d);
                            int nY = i;
                            int nX = j;
                            //각 방향마다 # 처리
                            while(true) {
                                nY += dY[d];
                                nX += dX[d];
                                if(!check(nY,nX) || map[nY][nX] == 6) break;
                                if(0 < simulMap[nY][nX] && simulMap[nY][nX] < 6 || simulMap[nY][nX] == -1) continue;
                                visited[nY][nX] = true;
                                simulMap[nY][nX] = -1;
                            }
                        }
                        //재귀 돌고
                        simulation(depth+1, i*M+j+1);
                        //나와서 원복
                        for(int a = 0; a < N; a++) {
                            for (int b = 0; b < M; b++) {
                                if (visited[a][b]) simulMap[a][b] = 0;
                            }
                        }
                    }
                }else if(map[i][j] == 3) {
                    for(int k = 0; k < 4; k++) {
                        for(int dir : cctv[2]) {
                            int d = (dir+k)%4;
                            int nY = i;
                            int nX = j;
                            //각 방향마다 # 처리
                            while(true) {
                                nY += dY[d];
                                nX += dX[d];
                                if(!check(nY,nX) || map[nY][nX] == 6) break;
                                if(0 < simulMap[nY][nX] && simulMap[nY][nX] < 6 || simulMap[nY][nX] == -1) continue;
                                visited[nY][nX] = true;
                                simulMap[nY][nX] = -1;
                            }
                        }
                        //재귀 돌고
                        simulation(depth+1, i*M+j+1);
                        //나와서 원복
                        for(int a = 0; a < N; a++) {
                            for (int b = 0; b < M; b++) {
                                if (visited[a][b]) simulMap[a][b] = 0;
                            }
                        }
                    }
                }else if(map[i][j] == 4) {
                    for(int k = 0; k < 4; k++) {
                        for(int dir : cctv[3]) {
                            int d = (dir+k)%4;
                            int nY = i;
                            int nX = j;
                            //각 방향마다 # 처리
                            while(true) {
                                nY += dY[d];
                                nX += dX[d];
                                if(!check(nY,nX) || map[nY][nX] == 6) break;
                                if(0 < simulMap[nY][nX] && simulMap[nY][nX] < 6 || simulMap[nY][nX] == -1) continue;
                                visited[nY][nX] = true;
                                simulMap[nY][nX] = -1;
                            }
                        }
                        //재귀 돌고
                        simulation(depth+1, i*M+j+1);
                        //나와서 원복
                        for(int a = 0; a < N; a++) {
                            for (int b = 0; b < M; b++) {
                                if (visited[a][b]) simulMap[a][b] = 0;
                            }
                        }
                    }
                }else if(map[i][j] == 5) {
                    for(int dir : cctv[4]) {
//                        System.out.println(dir);
                        int nY = i;
                        int nX = j;
                        while(true) {
//                            System.out.println(nY+" "+nX);
                            nY += dY[dir];
                            nX += dX[dir];
                            if(!check(nY,nX) || map[nY][nX] == 6) break;
                            if(0 < simulMap[nY][nX] && simulMap[nY][nX] < 6 || simulMap[nY][nX] == -1) continue;
                            visited[nY][nX] = true;
                            simulMap[nY][nX] = -1;
                        }
                    }
                    //재귀 돌고
                    simulation(depth+1, i*M+j+1);
                    //나와서 원복
                    for(int a = 0; a < N; a++) {
                        for (int b = 0; b < M; b++) {
                            if (visited[a][b]) simulMap[a][b] = 0;
                        }
                    }
                }
            }
        }
    }

    static boolean check(int y, int x) {
        if(-1 < y && y < N && -1 < x && x < M) return true;
        return false;
    }

    static void copyMap() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                simulMap[i][j] = map[i][j];
            }
        }
    }

    static void updateCnt() {
        int cnt=0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(simulMap[i][j] == 0) cnt++;
            }
        }
        answer = Math.min(answer, cnt);
//        System.out.println("answer: "+answer);
//        printMap();
    }

    static void printMap(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                System.out.print(simulMap[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("==================");
    }
}
