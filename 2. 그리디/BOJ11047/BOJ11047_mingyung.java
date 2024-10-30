import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11047_mingyung {
	// S4_11047_동전0
	// 결과 : 맞았습니다!!
	// 메모리 : 14,248 kb
	// 시간 : 244 ms
	
	// 준규가 가지고 있는 동전 N종류, 각 동전 매우 많음
	// 동전 적절히 사용해 그 가치의 합을 K로 만들려고 함
	// 이 때 필요한 동전의 최솟값 구하기
	
	static int N, K, ans;
	static int[] coins;
	
	public static void main(String[] args) throws IOException {
		// 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 첫째 줄에 N과 K
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		// 그 다음 N개의 줄에 걸쳐 동전의 가치 오름차순으로 주어짐
		coins = new int[N];
		int maxIdx = N-1;
		for (int i=0; i<N; i++) {
			coins[i] = Integer.parseInt(br.readLine());
			if (coins[i]>K) {
				maxIdx = i;
			}
		}
		
		// K원 만드는 데 필요한 동전의 개수 최솟값 찾아보자.
		ans=0;
		for (int i=maxIdx; i>=0; i--) {
			while (K>=coins[i]) {
				ans++;
				K-=coins[i];
			}
			if (K==0) {
				break;
			}
		}
//		find(maxIdx);
		System.out.println(ans);
		
	} // main
	
	static void find(int idx) {
		// 인덱스 안벗어나도록 처리
		if (idx<0) {
			return;
		}
		
		// 뺏을 때 0보다 작아지면 적은 돈으로 줄이기
		if (K-coins[idx]<0) {
			find(idx-1);
		} else {
			// 위에서 걸러지지 않았다면 빼고 다시 부르기
			K-=coins[idx];
			ans++;
			find(idx);
		}
		
	}
}