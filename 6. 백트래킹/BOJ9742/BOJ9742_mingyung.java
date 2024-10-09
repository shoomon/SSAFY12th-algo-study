
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9742_mingyung {
	// 서로 다른 숫자와 문자로 이루어진 집합과 위치가 주어졌을 때
	// 그 집합의 순열 중 주어진 위치의 순열을 구하는 프로그램을 작성하시오.
	
	static char[] arr, tmp; // 원본배열, 순열넣을 임시배열, 답배열
	static String ans;
	static int N, M, A; // 배열 길이, 몇 번째 숫자 뽑는지, 몇 번째인지 확인
	static boolean[] visited; // 방문체크
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		while((input = br.readLine()) != null) {
			// input = 수나 문자 + 공백 + 순서
			StringTokenizer st = new StringTokenizer(input);
			arr = st.nextToken().toCharArray();
			N = arr.length;
			tmp = new char[N];
			visited = new boolean[N];
			A = 0;
			ans = "";
			M = Integer.parseInt(st.nextToken());
			perm(0);
			
			System.out.print(input + " = ");
			if (ans.equals("")) {
				System.out.println("No permutation");
			} else {
				System.out.println(ans);
			}
			
		} // tc
	} // main
	
	static void perm(int idx) {
		if (idx == N) {
			A++;
			if (A==M) {
				for (int i=0; i<N; i++) {
					ans += tmp[i];
				}
			}
		}
		
		for (int i=0; i<N; i++) {
			if(visited[i]) continue;
			tmp[idx] = arr[i];
			visited[i] = true;
			perm(idx+1);
			visited[i] = false;
		}
	}
}