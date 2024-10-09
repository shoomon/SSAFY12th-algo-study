import java.io.*;
import java.util.*;
//24.10.02
//설계 시간: 1분
//구현 시간: N 시간
//메모리: 15108 kb
//시간: 120 ms

public class Main {
    static int N, K, L, dir;
    static int[][] map;
    static String[][] turn;
    static int[] dX = {0,1,0,-1};
    static int[] dY = {-1,0,1,0};
    static Deque<int[]> snake;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        map = new int[N][N];
        snake = new ArrayDeque<>();

        snake.offer(new int[] {0,0});
        map[0][0] = -1;
        dir = 1;

        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1; //사과 위치 잘못 받아왔다......1,1부터 주어짐
            int c = Integer.parseInt(st.nextToken())-1;
            map[r][c] = 1;
        }

        L = Integer.parseInt(br.readLine());
        turn = new String[L][2];

        for(int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            turn[i][0] = st.nextToken();
            turn[i][1] = st.nextToken();
        }

//		Arrays.sort(turn, (o1, o2) -> {return Integer.parseInt(o1[0])-Integer.parseInt(o2[0]);});
        bw.write(simulation()+"\n");
        bw.close();
    }

    static int simulation() {
        int t = 0;
        int idx=0;
        while(true) {
            t++;
            //이동하고
            int nY = snake.peekFirst()[0]+dY[dir];
            int nX = snake.peekFirst()[1]+dX[dir];
            if(check(nY,nX)) {
                if(map[nY][nX] == -1) break; //몸에 부딛힘
                if(map[nY][nX] != 1) { //사과 없음
                    int[] tail = snake.pollLast(); //꼬리 없애기
                    map[tail[0]][tail[1]] = 0;
                }
                snake.addFirst(new int[] {nY,nX}); //머리 이동
                map[nY][nX] = -1; //-> 마킹부터 하고 사과 있는지 체크하면 안된다.
            }else { //벽에 부딛힘
                break;
            }
            if(idx < turn.length) {
                if(turn[idx][0].equals(t+"")) {
                    //방향 전환
                    if(turn[idx][1].equals("D")) {
                        dir = (dir+1)%4;
                    }else if(turn[idx][1].equals("L")) {
                        dir = (dir+3)%4;
                    }
                    idx++;
                }
            }
//            System.out.println(t);
//            debug();
        }
        return t;
    }

    static boolean check(int y, int x) {
        if(-1 < y && y < N && -1 < x && x < N) return true;
        return false;
    }

    static void debug() {
        System.out.println("+++++++++++++"+dir);
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }

}
