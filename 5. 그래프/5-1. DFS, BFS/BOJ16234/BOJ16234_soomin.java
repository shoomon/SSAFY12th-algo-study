//24.12.10
//BFS 탐색
//시간복잡도 N^3 = 125000
//메모리: 296892 kb
//시간: 640 ms
import java.io.*;
import java.util.*;

public class Code16234 {
    static int N, L, R;
    static int[][] population;
    static Queue<int[]> q = new ArrayDeque<>();
    static int[] dY = {-1, 0, 1, 0};
    static int[] dX = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int answer;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        population = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                population[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        answer = 0;
        while(openBorder()){
            answer++;
        }
        System.out.println(answer);
    }

    static boolean openBorder(){
        boolean[][] visited = new boolean[N][N];
        //연합이 하나라도 있는지 확인하는 변수
        boolean flag = false;

        //국경을 모두 순회하며 연합 확인
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                //다른 연합에 포함되어 있다면 건너뜀
                if(visited[i][j]) continue;
                //시작지점
                q.offer(new int[]{i,j});
                visited[i][j] = true;
                //연합 인구, 연합 수
                int popul=population[i][j], cnt=1;
                //연합에 속한 국가들
                ArrayList<int[]> countries = new ArrayList<>();
                countries.add(new int[] {i,j});
                //인접 국가 탐색
                while(!q.isEmpty()){
                    int[] cur = q.poll();
                    int nY, nX;
                    for(int k = 0; k < 4; k++){
                        nY = cur[0]+dY[k];
                        nX = cur[1]+dX[k];
                        //연합이면 큐에 넣기
                        if(check(nY,nX) && !visited[nY][nX] && diffPop(cur[0],cur[1],nY,nX)){
                            q.offer(new int[] {nY,nX});
                            visited[nY][nX] = true;
                            flag = true;
                            cnt++;
                            popul += population[nY][nX];
                            countries.add(new int[] {nY,nX});
                        }
                    }
                }
                //인구 수 재분배
                popul /= cnt;
                for(int[] country : countries){
                    population[country[0]][country[1]] = popul;
                }
            }
        }
        return flag;
    }

    static boolean check(int y,int x){
        if(-1 < y && y < N && -1 < x && x < N) return true;
        return false;
    }

    static boolean diffPop(int y,int x, int nY, int nX){
        int diff = Math.abs(population[y][x]-population[nY][nX]);
        if(L <= diff && diff <= R) return true;
        return false;
    }
}
