import java.io.*;
import java.util.*;
//24.10.10
//설계 시간: 1분
//구현 시간: 15분
//메모리: 15728 kb
//시간: 104 ms
public class Main {
	static int N,Nums[],Opr[],min,max;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		Nums = new int[N];
		Opr = new int[4];
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			Nums[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4; i++) {
			Opr[i] = Integer.parseInt(st.nextToken());
		}
		
		combination(0,Nums[0]);
		
		bw.write(max+"\n"+min+"\n");
		bw.close();
	}
	
	static void combination(int depth, int res) {
//		System.out.println(depth+" "+res);
		if(depth == N-1) {
			min = Math.min(min, res);
			max = Math.max(max, res);
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			if(Opr[i] > 0) {
				Opr[i]--;
				if(i == 0) {
					combination(depth+1, res+Nums[depth+1]);
				}else if(i == 1) {
					combination(depth+1, res-Nums[depth+1]);
				}else if(i == 2) {
					combination(depth+1, res*Nums[depth+1]);
				}else if(i == 3) {
					combination(depth+1, res/Nums[depth+1]);
				}
				Opr[i]++;
			}
		}
	}
}