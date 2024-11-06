import java.util.*;
//24.11.05
//16120 ms
//348 kb
public class Code15684 {
    static int N, M, H,answer;
    static int[][] ladder, simulMap;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringTokenizer st;

        st = new StringTokenizer(sc.nextLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        ladder = new int[H][N];
        simulMap = new int[H][N];
        answer = -1;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(sc.nextLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ladder[a - 1][b - 1] = 1; //오른쪽으로 이동 가능
            ladder[a - 1][b] = -1; //왼쪽으로 이동 가능
        }

//        for(int i = 0; i < H; i++) System.out.println(Arrays.toString(ladder[i]));

        for(int i = 0; i < 4; i++){
            copyMap();
            simulation(0,i,0);
            if(answer != -1) break;
        }
        System.out.println(answer);
    }
    
    static void simulation(int depth, int max, int idx){
        if(answer != -1) return;
        if(depth == max){
            for(int i = 0; i < N; i++){
                //자기자신으로 도달하지 않는 경로가 있다면 리턴
//                System.out.println(max+" "+dfs(i,0,i));
//                for(int j = 0; j < H; j++) System.out.println(Arrays.toString(simulMap[j]));
                if(!dfs(i,0,i)) return;
            }
            answer = max;
            return;
        }

        for(int i = idx/N; i < H; i++){
            for(int j = i == idx/N ? idx%N : 0; j < N-1; j++){
                //이미 가로선이 높여있으면 continue
                if(simulMap[i][j] != 0 || simulMap[i][j+1] != 0) continue;
                simulMap[i][j] = 1;
                simulMap[i][j+1] = -1;
                simulation(depth+1, max, i*N+j);
                simulMap[i][j] = simulMap[i][j+1] = 0;
            }
        }
    }

    static boolean dfs(int startLine, int i, int j){
        //이동 가능한 점이 있을 때까지 내려가기
        while(i < H && simulMap[i][j] == 0) i++;
        //가장 아래까지 내려갔을 때
        if(i == H) {
            //시작열과 다른 열에 도착하면 false 리턴
//            System.out.println(i+" "+j+" "+startLine);
            if(j != startLine) return false;
            return true;
        //오른쪽 또는 왼쪽으로 이동 가능
        }

        return dfs(startLine,i+1,j+simulMap[i][j]);
    }

    static void copyMap(){
        for(int i = 0; i < H; i++){
            for(int j = 0; j < N; j++){
                simulMap[i][j] = ladder[i][j];
            }
        }
    }
}
