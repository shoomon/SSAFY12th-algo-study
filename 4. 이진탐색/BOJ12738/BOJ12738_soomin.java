import java.io.*;
import java.util.*;
//24.10.11
//설계 시간: 1분
//구현 시간: 5분
//메모리: 154920 kb
//시간: 664 ms
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int N  = Integer.parseInt(br.readLine());
		long[] A = new long[N];
		ArrayList<Long> lis = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			A[i] = Long.parseLong(st.nextToken());
			int idx = Collections.binarySearch(lis, A[i]);
			if(idx < 0) {
				idx = -(idx+1);
			}
			if(idx == lis.size()) {
				lis.add(A[i]);
			}else if(idx < lis.size()) {
				lis.set(idx, A[i]);
			}
		}
		
		bw.write(lis.size()+"\n");
		bw.close();
	}
}