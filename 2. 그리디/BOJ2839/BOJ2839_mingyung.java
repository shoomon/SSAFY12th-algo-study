import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2839_mingyung {
	// S4_2839_설탕 배달
	// 결과 : 맞았습니다!!
	// 메모리 : 14,272 kb
	// 시간 : 104 ms
	
	// 설탕을 N킬로그램 배달해야 함
	// 봉지는 3KG과 5KG이 있음
	// 최대한 적은 봉지를 들고가려고 함.
	// 최소 봉지 갯수 구하는 프로그램
	
	public static void main(String[] args) throws IOException {
		// 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 첫번째 줄 N
		int N = Integer.parseInt(br.readLine());
		
		boolean check = false; // 만들수 있는 숫자인지 확인할 변수
		int ans = 0; // 답
		// 일단 5부터 빼보면서 값 올려보기
		while (N>=5) {
			ans++;
			N -= 5;
		}
		
		// 만약 남은게 3으로 나눠떨어지지 않으면
		if (N%3 != 0) {
			// 뺀만큼 더하면서 3으로 나눠 떨어지는지 체크
			while (ans>0) {
				ans--;
				N+=5;
				// 3으로 나눠 떨어지면 만들 수 있으니 true 반환
				if(N%3==0) {
					check = true;
					break;
				}
			}
		} else check=true;
		
		// 만들수 없는 숫자면 -1, 아니면 다시 계산해서 출력
		if (!check) {
			System.out.println(-1);
		} else {
			while(N!=0) {
				ans++;
				N-=3;
			}
			System.out.println(ans);
		}
	}
}