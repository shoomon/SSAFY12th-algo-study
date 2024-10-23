import java.io.*;
import java.util.*;
//24.10.10
//설계 시간: 1분
//구현 시간: 10분
//메모리: 15940 kb
//시간: 300 ms
public class Main {
	static int N, S[][],answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		S = new int[N][N];
		answer = Integer.MAX_VALUE;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				S[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		combination(0,0,new boolean[N]);
		
		bw.write(answer+"\n");
		bw.close();
	}
	
	static void combination(int depth, int idx, boolean[] selected) {
		if(depth  == N/2) {
			//각 팀의 능력치 계산
			int start=0, link=0;
			for(int i = 0; i < N-1; i++) {
				for(int j = i+1; j < N; j++) {
					if(selected[i] && selected[j]) {
						start += S[i][j];
						start += S[j][i];
					}else if(!selected[i] && !selected[j]) {
						link += S[i][j];
						link += S[j][i];
					}
				}
			}
			answer = Math.min(answer, Math.abs(start-link));
			return;
		}
		
		for(int i = idx; i < N; i++) {
			selected[i] = true;
			combination(depth+1, i+1, selected);
			selected[i] = false;
		}
	}
}