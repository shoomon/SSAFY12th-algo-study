package oct_3th;

import java.io.*;
import java.util.*;

// 28m (bufferdWriter나 StringBuilder 사용하기 : 출력 제한 고려)
// 334772KB 1276ms

public class BOJ2776_dohee {
	static int N, note[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		while(T-->0) {
			N = Integer.parseInt(br.readLine());
			note = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				note[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(note);
			int M = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<M; i++) {
				int find = Integer.parseInt(st.nextToken());
				sb.append(search(find)).append("\n");
			}
		}
		
		System.out.println(sb.toString());
		
	}

	private static int search(int find) {
		int left = 0;
		int right = N-1;
		while(left<=right) {
			int mid = (left+right)/2;
			if(note[mid]==find) {
				return 1;
			} else if (note[mid]<find) {
				left = mid+1;
			} else {
				right = mid-1;
			}
		}
		return 0;
	}
}
