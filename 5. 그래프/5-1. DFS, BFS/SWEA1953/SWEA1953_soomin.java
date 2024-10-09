package swea;

import java.util.*;
import java.io.*;
//24.10.09
//설계 시간: 5 분
//구현 시간: 40 분
//메모리: 26,492 kb
//시간: 149 ms
//BFS탐색, 각 점의 상태 (시간) 저장 필요
public class Code1953 {
    static class Node{
        int r,c,t;
        public Node(int r, int c, int t){
            this.r = r;
            this.c = c;
            this.t = t;
        }
    }
    static int N, M, R, C, L;
    static int[][] map;
    static int[] dR = {-1, 0, 1, 0};
    static int[] dC = {0, 1, 0, -1};
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        Queue<Node> q;
        boolean[][] visited;
        int count;

        for(int test_case = 1; test_case <= T; test_case++)
        {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            map = new int[N][M];
            q = new ArrayDeque<>();
            visited = new boolean[N][M];
            count = 0;

            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < M; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            q.offer(new Node(R,C,1));
            visited[R][C] = true;

            while(!q.isEmpty()){
                Node cur = q.poll();
                if(cur.t > L) break;
                //꺼내면서 카운트
                count++;

                for(int i = 0; i < 4; i++){
                    //맵 범위를 벗어나지 않고 갈 수 있는 길이라면
                    if(check(cur.r,cur.c,i) && !visited[cur.r+dR[i]][cur.c+dC[i]]){
                        q.offer(new Node(cur.r+dR[i],cur.c+dC[i],cur.t+1));
                        visited[cur.r+dR[i]][cur.c+dC[i]] = true;
                    }
                }
            }

            bw.write("#"+test_case+" "+count+"\n");
        }
        bw.close();
    }
//현재 위치 파이프 모양, 다음 위치 파이프 모양 비교
    static boolean check(int r, int c, int dir){
        int nR = r+dR[dir];
        int nC = c+dC[dir];
        if(-1 < nR && nR < N && -1 < nC && nC < M){
            if(map[r][c] == 1){
                if(dir == 0){
                    if(map[nR][nC] == 1 || map[nR][nC] == 2 || map[nR][nC] == 5 || map[nR][nC] == 6) return true;
                }else if(dir == 1){
                    if(map[nR][nC] == 1 ||map[nR][nC] == 3 || map[nR][nC] == 6 || map[nR][nC] == 7) return true;
                }else if(dir == 2){
                    if(map[nR][nC] == 1 || map[nR][nC] == 2 || map[nR][nC] == 4 || map[nR][nC] == 7) return true;
                }else if(dir == 3){
                    if(map[nR][nC] == 1 || map[nR][nC] == 3 || map[nR][nC] == 4 || map[nR][nC] == 5) return true;
                }
            }else if(map[r][c] == 2){
                if(dir == 0){
                    if(map[nR][nC] == 1 || map[nR][nC] == 2 || map[nR][nC] == 5 || map[nR][nC] == 6) return true;
                }else if(dir == 2){
                    if(map[nR][nC] == 1 || map[nR][nC] == 2 || map[nR][nC] == 4 || map[nR][nC] == 7) return true;
                }
            }else if(map[r][c] == 3){
                if(dir == 1){
                    if(map[nR][nC] == 1 || map[nR][nC] == 3 || map[nR][nC] == 6 || map[nR][nC] == 7) return true;
                }else if(dir == 3){
                    if(map[nR][nC] == 1 || map[nR][nC] == 3 || map[nR][nC] == 4 || map[nR][nC] == 5) return true;
                }
            }else if(map[r][c] == 4){
                if(dir == 0){
                    if(map[nR][nC] == 1 || map[nR][nC] == 2 || map[nR][nC] == 5 || map[nR][nC] == 6) return true;
                }else if(dir == 1){
                    if(map[nR][nC] == 1 || map[nR][nC] == 3 || map[nR][nC] == 6 || map[nR][nC] == 7) return true;
                }
            }else if(map[r][c] == 5){
                if(dir == 1){
                    if(map[nR][nC] == 1 || map[nR][nC] == 3 || map[nR][nC] == 6 || map[nR][nC] == 7) return true;
                }else if(dir == 2){
                    if(map[nR][nC] == 1 || map[nR][nC] == 2 || map[nR][nC] == 4 || map[nR][nC] == 7) return true;
                }
            }else if(map[r][c] == 6){
                if(dir == 2){
                    if(map[nR][nC] == 1 || map[nR][nC] == 2 || map[nR][nC] == 4 || map[nR][nC] == 7) return true;
                }else if(dir == 3){
                    if(map[nR][nC] == 1 || map[nR][nC] == 3 || map[nR][nC] == 4 || map[nR][nC] == 5) return true;
                }
            }else if(map[r][c] == 7){
                if(dir == 0){
                    if(map[nR][nC] == 1 || map[nR][nC] == 2 || map[nR][nC] == 5 || map[nR][nC] == 6) return true;
                }else if(dir == 3){
                    if(map[nR][nC] == 1 || map[nR][nC] == 3 || map[nR][nC] == 4 || map[nR][nC] == 5) return true;
                }
            }
        }
        return false;
    }
}
