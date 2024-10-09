import java.util.*;
import java.io.*;

public class Main {
    static int answer,N, curY, curX;
    static int[][] map;
    //dir = down
    //5row 5column -> 행, 열 수 같아야 배열 돌려도 계산 가능
    static double[][] dust = {
            {0,0,0,0,0},
            {0,0.01,0,0.01,0},
            {0.02,0.07,0,0.07,0.02},
            {0,0.1,0,0.1,0},
            {0,0,0.05,0,0}
    };

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        answer = 0;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        curY=N/2;
        curX=N/2;

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 1; i < N; i++) {
            for(int j = 0; j < i; j++) left();
            for(int j = 0; j < i; j++) down();
            i++;
            for(int j = 0; j < i; j++) right();
            for(int j = 0; j < i; j++) up();
        }

        for(int i = 0; i < N; i++) left();
        System.out.println(answer);

//		for(int i = 0; i < N; i++) {
//			for(int j = 0; j < N; j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}
    }
    //왼쪽x-1이 아래쪽y+1, 위쪽y-1이 왼쪽x-1,아래쪽y+1이 오른쪽x+1
    static void left() {
        if(!check(curY, curX-1)) return;
        int sand = map[curY][curX-1];
        for(int i = 0; i < 5; i++) {
            for(int j = -2; j <= 2; j++) {
                if(check(curY+j,curX-i+1)) {
                    map[curY+j][curX-i+1] += (int)(map[curY][curX-1]*dust[i][j+2]);
                }else {
                    answer += (int)(map[curY][curX-1]*dust[i][j+2]);
                }
                sand -= (int)(map[curY][curX-1]*dust[i][j+2]);
            }
        }
        if(check(curY, curX-2)) {
            map[curY][curX-2] += sand;
        }else {
            answer += sand;
        }
        map[curY][curX-1] = 0;
        curX -= 1;
//        System.out.println(sand);
//        debug();
    }
    //오른쪽x+1이 아래쪽y+1, 위쪽y+1이 오른쪽x+1, 아래쪽y-1이 왼쪽x-1
    static void right() {
        if(!check(curY, curX+1)) return;
        int sand = map[curY][curX+1];

        for(int i = 0; i < 5; i++) {
            for(int j = -2; j <= 2; j++) {
                if(check(curY+j,curX+i-1)) {
                    map[curY+j][curX+i-1] += (int)(map[curY][curX+1]*dust[i][j+2]);
                }else {
                    answer += (int)(map[curY][curX+1]*dust[i][j+2]);
                }
                sand -= (int)(map[curY][curX+1]*dust[i][j+2]);
            }
        }
        if(check(curY, curX+2)) {
            map[curY][curX+2] += sand;
        }else {
            answer += sand;
        }
        map[curY][curX+1] = 0;
        curX += 1;
//        System.out.println(sand);
//        debug();
    }
    //방향 그대로
    static void down() {
        if(!check(curY+1, curX)) return;
        int sand = map[curY+1][curX];

        for(int i = 0; i < 5; i++) {
            for(int j = -2; j <= 2; j++) {
                if(check(curY+i-1,curX+j)) {
                    //x, y, 부호 관계 잘 보기
                    //+= 뒤는 고정: 모래날리는 점 * dust[i][j+2]
                    map[curY+i-1][curX+j] += (int)(map[curY+1][curX]*dust[i][j+2]);
                }else {
                    answer += (int)(map[curY+1][curX]*dust[i][j+2]);
                }
                sand -= (int)(map[curY+1][curX]*dust[i][j+2]);
            }
        }
        //알파 위치
        if(check(curY+2, curX)) {
            map[curY+2][curX] += sand;
        }else {
            answer += sand;
        }
        map[curY+1][curX] = 0;
        curY += 1;
//        System.out.println(sand);
//        debug();
    }
    //위쪽y-1이 아래쪽y+1, 오른쪽x+1이 왼쪽x-1, 왼쪽x-1이 오른쪽x+1
    static void up() {
        if(!check(curY-1, curX)) return;
        int sand = map[curY-1][curX];

        for(int i = 0; i < 5; i++) {
            for(int j = -2; j <= 2; j++) {
                if(check(curY-i+1,curX-j)) {
                    map[curY-i+1][curX-j] += (int)(map[curY-1][curX]*dust[i][j+2]);
                }else {
                    answer += (int)(map[curY-1][curX]*dust[i][j+2]);
                }
                sand -= (int)(map[curY-1][curX]*dust[i][j+2]);
            }
        }

        if(check(curY-2, curX)) {
            map[curY-2][curX] += sand;
        }else {
            answer += sand;
        }
        map[curY-1][curX] = 0;
        curY -= 1;
//        System.out.println(sand);
//        debug();
    }

    static boolean check(int i, int j) {
        if(-1 < i && i < N && -1 < j && j < N) return true;
        return false;
    }

    static void debug(){
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println(curY+" "+curX+"============="+answer);
    }

}
