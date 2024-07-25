//설계 시간: 10분
//구현 시간: 5분
//시간복잡도, 데이터 사이즈 계산 연습 필요.
import java.util.Scanner;

public class BOJ17427_soomin {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		long answer=0;
		long[] fN = new long[N+1];
		
		for(int i = 1; i <= N; i++) {
			int tmp=1;
			while(i*tmp <= N) {
				fN[i*tmp] += i;
				tmp++;
			}
		}
		
		for(long i : fN) {
			answer += i;
		}
		
		System.out.println(answer);

	}

}
