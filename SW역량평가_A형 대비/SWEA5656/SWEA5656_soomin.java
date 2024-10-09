import java.util.*;
import java.io.*;
//메모리: 110,112kb
//시간: 953ms
class Block{
    int r,c,w;
    public Block(int r, int c, int w){
        this.r = r;
        this.c = c;
        this.w = w;
    }
}
public class SWEA5656_soomin {
    static int N, W, H, answer, maxCnt;
    static int[][] map, copy;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            map = new int[H][W];
            copy = new int[H][W];
            answer = 0;
            maxCnt = 0;

            for(int i = 0; i < H; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < W; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] != 0) answer++;
                }
            }

            //N번 구슬 쏘기
//            for(int i = 0; i < N; i++){
//                copyMap(copy, map);
//                //모든 열 다 쏴보기 -> N*W 최대 48
//                for(int j = 0; j < W; j++){
//                    int r=0;
//                    count=0;
//                    //원복
//                    copyMap(map, copy);
//                    while(check(r,j) && map[r][j] == 0){
//                        r++;
//                    }
//                    if(!check(r,j)) continue;
//                    int cnt = breakBlocks(r,j);
//                    if(cnt > count){
//                        targetR = r;
//                        targetC = j;
//                        count = cnt;
//                    };
//                }
//                //최대로 많이 깰 수 있는 위치에 쏘기
//                breakBlocks(targetR, targetC);
//                System.out.println("shoot "+i+" r,c: "+targetR+" "+targetC);
//                printMap();
//                //블록 떨어짐
//                dropBlocks();
//                printMap();
//
//            }

            simulation(0, new int[N]);
            answer -= maxCnt;
            bw.write("#"+t+" "+answer+"\n");
        }
        bw.close();
    }

    //N개 위치 선택 후 시뮬레이션 -> 최대 12^4 = 144*144 < 2250
    static void simulation(int depth, int[] sequence){
        if(depth == N){
            int cnt=0;
            copyMap(copy,map);
//            for(int i : sequence) System.out.print(i+" ");
            for(int i : sequence){
                cnt += breakBlocks(i);
//                System.out.println("col: "+ i+"==========");
//                printMap();
                dropBlocks();
//                printMap();
            }
            maxCnt = Math.max(maxCnt, cnt);
//            System.out.println(cnt+" "+maxCnt);
            //원복
            copyMap(map, copy);
            return;
        }

        for(int i = 0; i < W; i++){
            sequence[depth] = i;
            simulation(depth+1, sequence);
        }
    }
    //벽돌이 깨지면 연쇄적으로 주변 벽돌도 깨져야 한다.
    //주변 벽돌 큐에 삽입
    static int breakBlocks(int c){
        Queue<Block> q = new ArrayDeque<>();
        int r=0, cnt=0;
        while(check(r,c) && map[r][c] == 0){
            r++;
        }
        if(!check(r,c)) return 0;

        q.offer(new Block(r,c,map[r][c]));
//        visited[r][c] = true;
        while(!q.isEmpty()){
            Block cur = q.poll();
            for(int i = 0; i < cur.w; i++){
                if(check(cur.r-i, cur.c) && map[cur.r-i][cur.c] != 0){
                    q.offer(new Block(cur.r-i, cur.c, map[cur.r-i][cur.c]));
//                    visited[cur.r-1][cur.c] = true;
                    map[cur.r-i][cur.c] = 0;
                    cnt++;
                }
                if(check(cur.r+i, cur.c) && map[cur.r+i][cur.c] != 0){
                    q.offer(new Block(cur.r+i, cur.c, map[cur.r+i][cur.c]));
                    map[cur.r+i][cur.c] = 0;
                    cnt++;
                }
                if(check(cur.r, cur.c-i) && map[cur.r][cur.c-i] != 0){
                    q.offer(new Block(cur.r, cur.c-i, map[cur.r][cur.c-i]));
                    map[cur.r][cur.c-i] = 0;
                    cnt++;
                }
                if(check(cur.r, cur.c+i) && map[cur.r][cur.c+i] != 0){
                    q.offer(new Block(cur.r, cur.c+i, map[cur.r][cur.c+i]));
                    map[cur.r][cur.c+i] = 0;
                    cnt++;
                }
            }
        }
        return cnt;
    }

    static void dropBlocks(){
        Stack<Integer> stack;
        for(int j = 0; j < W; j++){
            stack = new Stack<>();
            for(int i = 0; i < H; i++){
                if(map[i][j] != 0){
                    stack.push(map[i][j]);
                    map[i][j] = 0;
                }
            }
            for(int i = H-1; !stack.isEmpty(); i--){
                map[i][j] = stack.pop();
            }
        }
    }

    static boolean check(int i, int j){
        if(-1 < i && i < H && -1 < j && j < W) return true;
        return false;
    }

    static void copyMap(int[][] arr1, int[][] arr2){
        for(int i = 0; i < H; i++){
            arr1[i] = Arrays.copyOf(arr2[i], W);
        }
    }

    static void printMap(){
        for(int i = 0; i < H; i++){
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println("=================");
    }
}