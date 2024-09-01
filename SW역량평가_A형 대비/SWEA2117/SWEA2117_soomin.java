import java.util.*;
import java.io.*;
//메모리: 22,816kb
//시간: 408ms
public class SWEA2117_soomin {
    static int N,M, map[][], max, homeCnt;
    static int[] dX = {0,1,0,-1};
    static int[] dY = {-1,0,1,0};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            max = 0;
            homeCnt=0;

            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] == 1) homeCnt++;
                }
            }
            //완탐 + 좌표 -> N^3 = 8000
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
//                    System.out.println("sim "+ i +" "+j);
                    simulation(i,j);
                }
            }
            bw.write("#"+t+" "+max+"\n");
        }
        bw.close();
    }

    static void simulation(int y, int x){
        //netRevenue < 0일때 바로 끝내면 안됨.
        int netRev=0, cnt=0, area=0; //area = 서비스 영역 -1

        //새로 추가된 영역만 계산하는 방법?
        while(cnt < homeCnt && area <= N){
            cnt=0;
            for(int i = y-area; i <= y+area; i++){
                for(int j = x-area; j <= x+area; j++){
//                    System.out.println(i+" "+j+" "+Math.abs(i-y) +" "+ Math.abs(j-x)+ area);
                    if(check(i,j) && Math.abs(i-y) + Math.abs(j-x) <= area && map[i][j] == 1) cnt++;
                }
            }
            netRev = cnt*M - ((area+1)*(area+1) + area*area);
            area++;
//            System.out.print(cnt+" "+netRev+" "+area+" ");
            if(netRev > -1) max = Math.max(max, cnt);
//            System.out.println(max);
        }
    }

    static boolean check(int i, int j){
        return -1 < i && i < N && -1 < j && j < N;
    }
}