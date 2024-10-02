import java.util.*;
import java.io.*;
//날짜 24.09.19
//설계 시간: 2분
//구현 시간: 20분
//메모리: 16780 kb
//시간: 744 ms
//시간복잡도: 20C10 = 184,756
public class Code15661 {
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

        //부분집합으로 팀 구성
        boolean[] isSelected = new boolean[N];
        //depth == 0인 경우 검사를 하지 않기 위해 첫번째 선수 선택은 subSet 함수 밖에서 수행
        for(int i = 0; i < N; i++){
            isSelected[i] = true;
            subSet(1, i+1, isSelected);
            isSelected[i] = false;
        }
        bw.write(answer+"");
        bw.close();
    }

    static void subSet(int depth, int idx, boolean[] isSelected){
        //절반을 선택한 경우는 절반을 선택하지 않은 경우와 중복되므로 리턴
        if(depth > N/2) return;
        //선택이 될 때마다 팀 능력치 계산
        int str=0, lnk=0;
        for(int i = 0; i < N-1; i++){
            for(int j = i+1; j < N; j++){
                if(isSelected[i] && isSelected[j]){
                    str += adj[i][j]+adj[j][i];
                }else if(!isSelected[i] && !isSelected[j]){
                    lnk += adj[i][j]+adj[j][i];
                }
            }
        }
//        System.out.println(answer+" "+lnk+" "+str);
        answer = Math.min(answer, Math.abs(lnk-str));

        for(int i = idx; i < N; i++){
            isSelected[i] = true;
            subSet(depth+1, i+1, isSelected);
            isSelected[i] = false;
        }
    }
}
