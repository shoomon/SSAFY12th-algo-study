import java.util.*;
import java.io.*;

//24.09.19
//설계 시간: 2분
//구현 시간: 5분
//메모리: 17728 kb
//시간: 152 ms
public class Main {
	static int L, C;
	static String[] alpha;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		alpha = new String[C];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < C; i++) {
			alpha[i] = st.nextToken();
		}
		Arrays.sort(alpha);
		combination(0,0,0, new String[L]);
	}
	
	static void combination(int depth, int idx, int cnt, String[] result) {
//		System.out.println(depth+" "+cnt);
		if(L-cnt < 2) return;
		if(depth == L) {
			if(cnt > 0) {
				for(int i = 0; i < L; i++) System.out.print(result[i]);
				System.out.println();
			}
			return;
		}
		
		for(int i = idx; i < C; i++) {
			result[depth] = alpha[i];
//			System.out.println(alpha[i]);
			if(alpha[i].equals("a") || alpha[i].equals("e") || alpha[i].equals("i") || alpha[i].equals("o") || alpha[i].equals("u")) {
				combination(depth+1, i+1, cnt+1, result);
			}else {
				combination(depth+1, i+1, cnt, result);
			}
		}
	}

}
