import java.io.*;
import java.util.*;
public class Main {
//BOJ20056
    //연산 횟수: M*K = 2,500,000
    static int[] dX = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[] dY = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int N, M, K;
    static LinkedList<fireball>[][] map;
    static Queue<fireball> f;
    static class fireball{
        int r, c, m, s, d;
        public fireball(int r, int c, int m, int s, int d){
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj==null || getClass() != obj.getClass()) return false;
            fireball f = (fireball) obj;
            return (r==f.r && c==f.c && m==f.m && s==f.s && d==f.d);
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new LinkedList[N][N];
        f = new ArrayDeque<>();

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                map[i][j] = new LinkedList<>();
            }
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            f.add(new fireball(r,c,m,s,d));
            map[r][c].add(new fireball(r,c,m,s,d));
        }

        for(int k = 0; k < K; k++){
            //파이어볼 이동
            while(!f.isEmpty()){
                fireball i = f.poll();
                map[i.r][i.c].remove(i);
//                System.out.println(i.r+" "+i.c+" "+i.m+" "+i.s+" "+i.d);
                //이동
                //N만 더해줄 경우 좌표 0 방향 -1 이고 N < s인 경우 좌표 값이 -가 된다.
                i.r = (i.r +N*N+ (dY[i.d]*i.s)%N)%N;
                i.c = (i.c +N*N+ (dX[i.d]*i.s)%N)%N;
//                System.out.println(i.r+" "+i.c);
                map[i.r][i.c].add(i);
            }
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    int size = map[i][j].size();
                    //2개 이상 있는 칸
                    if(size > 1){
                        int cnt = 0, sumM=0, d=-1, sumS=0;
                        for(fireball l : map[i][j]) {
                            sumM += l.m;
                            sumS += l.s;
                            if (l.d % 2 == 0) {
                                cnt++;
                            } else {
                                cnt--;
                            }
                        }
                        sumM /= 5;
                        sumS /= size;
                        map[i][j].clear();
                        //질량 0이면 소멸
                        if(sumM == 0) continue;
                        //방향이 모두 홀수이거나 모두 짝수이면 0방향부터 시작
                        if(Math.abs(cnt) == size){
                            d=0;
                        }else{
                            d=1;
                        }
                        //새로운 파이어볼 4개 생성
                        for(int l = 0; l < 4; l++){
                            f.add(new fireball(i,j,sumM,sumS,d));
                            map[i][j].add(new fireball(i,j,sumM,sumS,d));
                            d += 2;
                        }

                    }
                    //1개인 칸
                    else if(size == 1){
                        fireball cur = map[i][j].get(0);
                        f.offer(cur);
                    }
                }
            }
        }
        int sum = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(map[i][j].isEmpty()) continue;
                for(fireball k : map[i][j]){
//                    System.out.println(k.r+" "+k.c+" "+k.m+" "+k.s+" "+k.d);
                    sum += k.m;
                }
            }
        }
        System.out.println(sum);
    }
}

//        4 4 2
//        1 2 13 4 3
//        1 4 12 3 7
//        4 1 2 5 7
//        4 2 6 3 0
