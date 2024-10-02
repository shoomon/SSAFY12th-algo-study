import java.util.*;
import java.io.*;
//날짜 24.09.19
//설계 시간: 2분
//구현 시간: 15분
//메모리: 15928 kb
//시간: 340 ms
public class Code14889_2 {
    static int N, answer;
    static int[][] adj;
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        adj = new int[N][N];
        answer = Integer.MAX_VALUE;

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                adj[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //조합으로 팀 나누기
        combination(0,0,new boolean[N]);
        bw.write(answer+"");
        bw.close();
    }
    //팀 나누기
    static void combination(int depth, int idx, boolean[] isSelected){
        //팀이 다 나눠졌으면
        if(depth == N/2){
            //능력치 계산
            int start=0,link=0;
            for(int i = 0; i < N-1; i++){
                for(int j = i+1; j < N; j++){
                    if(isSelected[i] && isSelected[j]){
                        start += adj[i][j]+adj[j][i];
                    }else if(!isSelected[i] && !isSelected[j]){
                        link += adj[i][j]+adj[j][i];
                    }
                }
            }
            //정답 업데이트
            answer = Math.min(answer, Math.abs(start-link));
            return;
        }

        for(int i = idx; i < N; i++){
            isSelected[i] = true;
            combination(depth+1, i+1, isSelected);
            isSelected[i] = false;
        }
    }
}
