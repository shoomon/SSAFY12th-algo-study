import java.util.*;
import java.io.*;

class PCCP_4_mingyung {
	static int answer = Integer.MAX_VALUE;
    static int [] dr = {-1,1,0,0};
    static int [] dc = {0,0,-1,1};
    static boolean [][] red;
    static boolean [][] blue;
    static int R, C;
    
    // 0: 빈칸 1: 빨간 시작 2: 파란 시작 3: 빨간 도착 4: 파란 도착 5: 벽 
    public int solution(int[][] maze) {
        int rr = 0;
        int rc = 0;
        int br = 0;
        int bc = 0;
        R = maze.length; C = maze[0].length;
        red  = new boolean [R][C]; // 빨간 방문 배열 
        blue = new boolean [R][C]; // 파란 방문 배열 
        for(int r = 0; r < R; r++){
            for(int c = 0; c < C; c++){
                if(maze[r][c] == 1){     // 빨간 시작 
                    red[r][c] = true;
                    rr = r; rc = c;
                }
                else if(maze[r][c] == 2){ // 파란 시작 
                    blue[r][c] = true;
                    br = r; bc = c;
                }
            }
        }
        // 백트래킹 시작 
        move(maze, rr, rc, br, bc, 0, false, false);  
      
        return answer == Integer.MAX_VALUE ? 0 : answer;
    }
    public static void move(int[][] maze, int rr, int rc, int br, int bc, int move, boolean red_end, boolean blue_end){
    
        if(!red_end && maze[rr][rc] == 3) red_end = true;
        if(!blue_end && maze[br][bc] == 4) blue_end = true;
        
        if(red_end && blue_end){
            answer = Math.min(answer, move);
            return;
        }
        
        ArrayList<int[]> r_list = new ArrayList<>(); // 빨강이 이동 가능한 
        ArrayList<int[]> b_list = new ArrayList<>(); // 파랑이 이동 가능한 
        
        if(!red_end){
            for(int d = 0; d < 4; d++){
                int nr = dr[d] + rr;
                int nc = dc[d] + rc;
                if(validation(nr, nc) && maze[nr][nc] != 5 && !red[nr][nc]) r_list.add(new int []{nr, nc});
            }
        }
        else r_list.add(new int []{rr, rc});
                
        if(!blue_end){
            for(int d = 0; d < 4; d++){
                int nr = dr[d] + br;
                int nc = dc[d] + bc;
                if(validation(nr, nc) && maze[nr][nc] != 5 && !blue[nr][nc]) b_list.add(new int []{nr, nc});
            }
        }
        else b_list.add(new int []{br, bc});
        
        for(int i = 0; i < r_list.size(); i++){
            int [] r_arr = r_list.get(i);
            for(int j = 0; j < b_list.size(); j++){
                int [] b_arr = b_list.get(j);
                // 서로 같은 위치로 이동 X
                if(r_arr[0] == b_arr[0] && r_arr[1] == b_arr[1]) continue;
                // 서로 자리 바꾸기 X
                if(rr == b_arr[0] && rc == b_arr[1] && br == r_arr[0] && bc == r_arr[1]) continue; 
                
                red[r_arr[0]][r_arr[1]] = true;
                blue[b_arr[0]][b_arr[1]] = true;
                move(maze, r_arr[0], r_arr[1], b_arr[0], b_arr[1], move + 1, red_end, blue_end);
                red[r_arr[0]][r_arr[1]] = false;
                blue[b_arr[0]][b_arr[1]] = false;
            }
        }
    }
    public static boolean validation(int r, int c){ // ArrayOutOfBoundsIndex 예외 방지
        return 0 <= r && 0 <= c && r < R && c < C;
    }
}