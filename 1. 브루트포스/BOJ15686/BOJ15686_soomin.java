//24.11.03
//설계시간: 15분
//구현시간: 25분
//메모리: 15296 kb
//시간: 160 ms
//부르트포스
import java.util.*;
import java.io.*;
public class Code15686 {
    static int N, M, answer;
//    static int[][] map;
    static List<int[]> chicken, house; //치킨집, 집 좌표 저장 (r,c)
    public static void main(String[] arge) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        answer = Integer.MAX_VALUE;
//        map = new int[N][N];
        chicken = new ArrayList(13);
        house = new ArrayList(2*N);

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
//                map[i][j] = Integer.parseInt(st.nextToken());
                int in = Integer.parseInt(st.nextToken());
                if(in == 1) {
                    house.add(new int[] {i,j});
                }else if(in == 2){
                    chicken.add(new int[] {i,j});
                }
            }
        }

        simulation(0,0,new boolean[13]);
        System.out.println(answer);

    }

    static void simulation(int depth, int idx, boolean[] isSelected){
        if(depth == M){
            int dist = calculateDist(isSelected);
            answer = Math.min(answer, dist);
        }

        for(int i = idx; i < chicken.size(); i++){
            isSelected[i] = true;
            simulation(depth+1, i+1, isSelected);
            isSelected[i] = false;
        }
    }

    static int calculateDist(boolean[] isSelected){
        int ret=0;
        //i번째 집까지의 최소 거리 저장
        int[] minDist = new int[house.size()];
        Arrays.fill(minDist, Integer.MAX_VALUE);

        for(int i = 0; i < chicken.size(); i++){
            if(isSelected[i]){
                int[] cur = chicken.get(i);
                for(int j = 0; j < house.size(); j++){
                    int[] h = house.get(j);
                    minDist[j] = Math.min(minDist[j], Math.abs(cur[0]-h[0])+Math.abs(cur[1]-h[1]));
                }
            }
        }

        for(int i : minDist) ret += i;

        return ret;
    }
}
