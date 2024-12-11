package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ2839_설탕 배달 
// 메모리 :11544KB
// 시간 : 64ms

public class BOJ2839_hyunjin {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int total = Integer.parseInt(br.readLine()); // 배달해야 하는 총 설탕 무게

		int fiveCnt = 0;
		int threeCnt = 0;
		int x = total;

		total = (5 * fiveCnt) + (3 * threeCnt) + x;

		fiveCnt = total / 5;
        
        // 5kg 가져갈 개수가 0일때까지만 돌기
		while (fiveCnt >= 0) {
			threeCnt = (total - (5 * fiveCnt)) / 3;

			x = total - ((5 * fiveCnt) + (3 * threeCnt));
               
            // 나머지가 0이 될 때 stop
			if (x != 0) {
				fiveCnt--;
			} else {
				break;
			}
		} // while

        // 5kg의 수가 음수면 해당 total 값은 만들 수 없다는 말
		if (fiveCnt < 0) {
			System.out.println(-1);
		} else {
			System.out.println(fiveCnt + threeCnt);
		}

	}
}