import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
용액
알칼리와 산성용액을 섞었을 때 0에 가장 가까운 값을 내는 2 용액의 정도를 출력하시오.

메모리 : 32320 KB
시간 : 260 ms

*/

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int liqCnt = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int[] liquids = new int[liqCnt];
		for (int i = 0; i < liqCnt; i++)
			liquids[i] = Integer.parseInt(st.nextToken());
		
		int liq1 = 0;
		int liq2 = 0;
		long minVal = Long.MAX_VALUE;
		
		// 각각의 위치의 액체를 가지고 더해서 0값과 더 가까운 값을 찾아낸다.
		for (int i = 0; i < liqCnt; i++) {
			// 더할 액체의 범위 설정
			int left = i + 1;
			int right = liqCnt - 1;
			// 현재 기준이 되는 액체의 양
			int curLiq = liquids[i];
			long curMin = Long.MAX_VALUE;
			int curAns = 0;
			int	idx = 0;
			// 이분탐색으로 더한 값이 0과 가장 가까워질 수 있는 값을 찾아낸다.
			while(left <= right) {
				int mid = (left + right) / 2;
				idx = mid;
				curAns = liquids[mid] + curLiq;
				curMin = Math.min(Math.abs(curAns), minVal);
				if (minVal > curMin) {
					minVal = curMin;
					liq1 = i;
					liq2 = idx;
				}
				if (curAns == 0)
					break;
				else if (curAns > 0)
					right = mid - 1;
				else
					left = mid + 1;
			}
			
			
		}
		
		System.out.printf("%d %d",liquids[liq1], liquids[liq2]);
	}
}