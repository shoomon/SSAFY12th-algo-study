//24.12.11
//설계 시간: 1시간
//구현 시간: 30분
//메모리: 24584 kb
//시간: 264 ms
//시뮬레이션
import java.util.*;
import java.io.*;

public class Code16236 {
    static class obj{
        int y,x,size,cnt, dist;

        public obj(int y, int x, int size){
            this.y = y;
            this.x = x;
            this.size = size;
        }

        public void eat(obj fish){
            if(fish.size >= this.size) return;
            this.cnt++;
            this.y = fish.y;
            this.x = fish.x;
            if(this.size == this.cnt) {
                this.size++;
                this.cnt = 0;
            }
        }
    }

    static int[][] map, distance;
    static int N, answer;
    static obj shark;
    static int[] dY = {-1, 0, 1, 0};
    static int[] dX = {0,1,0,-1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int [N][N];
        distance = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9) shark = new obj(i,j,2);
            }
        }
        while(simulation());
        System.out.println(answer);
    }

    static boolean simulation(){
        Queue<int[]> q = new ArrayDeque<>();
        PriorityQueue<obj> pq = new PriorityQueue<>((o1,o2)->{
            if(o1.dist == o2.dist){
                if(o1.y == o2.y) return o1.x-o2.x;
                return o1.y-o2.y;
            }
            return o1.dist-o2.dist;
        });
        //BFS로 거리 표시 -> O(N) = 20*20
        initDistance();
        q.offer(new int[] {shark.y,shark.x});

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int nY,nX;

            for(int i = 0; i < 4; i++){
                nY = cur[0]+dY[i];
                nX = cur[1]+dX[i];

                if(check(nY,nX) && map[nY][nX] != 9 && map[nY][nX] <= shark.size && distance[nY][nX] == 0){
                    distance[nY][nX] = distance[cur[0]][cur[1]]+1;
                    q.offer(new int[]{nY,nX});
                    if(0 < map[nY][nX] && map[nY][nX] < shark.size) {
                        obj fish = new obj(nY,nX,map[nY][nX]);
                        fish.dist = distance[nY][nX];
                        pq.offer(fish);
                    }
                }
            }
        }
        if(pq.isEmpty()) return false;
        //타겟 물고기 먹기
        obj fish = pq.poll();
        map[shark.y][shark.x] = 0;
        map[fish.y][fish.x] = 9;
        shark.eat(fish);
//        printDistance(fish);
        answer += distance[fish.y][fish.x];
        return true;
    }

    static boolean check(int y, int x){
        if(-1 < y && y < N && -1 < x && x < N) return true;
        return false;
    }

    static void initDistance(){
        for(int i = 0; i < N; i++) Arrays.fill(distance[i], 0);
    }

    static void printDistance(obj fish){
        System.out.println(shark.y+" "+shark.x+" "+shark.size+"========================="+fish.y+" "+fish.x);
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N ;j++){
                System.out.print(distance[i][j]+" ");
            }
            System.out.println();
        }
    }
}
