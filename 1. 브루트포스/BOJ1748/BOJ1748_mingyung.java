import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1748_mingyung {
	// S4_수이어쓰기1 _맞았습니다!!
	// 1부터 N까지 이어써서 만들어진 새로운 수의 자리수
	// 1<=N<=100,000,000
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String N = br.readLine();
		long ans = 0;
		// 자리수 별로 더해주는 값 변경줌
		switch (N.length()) {
		case 1 :
			ans = 1*(Integer.parseInt(N));
			break;
		case 2 :
			ans = 1*9 + 2*(Integer.parseInt(N)-9);
			break;
		case 3 :
			ans = 1*9 + 2*90 + 3*(Integer.parseInt(N)-99);
			break;
		case 4 :
			ans = 1*9 + 2*90 + 3*900 + 4*(Integer.parseInt(N)-999);
			break;
		case 5 :
			ans = 1*9 + 2*90 + 3*900 + 4*9000 + 5*(Integer.parseInt(N)-9999);
			break;
		case 6 :
			ans = 1*9 + 2*90 + 3*900 + 4*9000 + 5*90000 + 6*(Integer.parseInt(N)-99999);
			break;
		case 7 :
			ans = 1*9 + 2*90 + 3*900 + 4*9000 + 5*90000 + 6*900000 + 7*(Integer.parseInt(N)-999999);
			break;
		case 8 :
			ans = 1*9 + 2*90 + 3*900 + 4*9000 + 5*90000 + 6*900000 + 7*9000000 + 8*(Integer.parseInt(N)-9999999);
			break;
		case 9 :
			ans = 1*9 + 2*90 + 3*900 + 4*9000 + 5*90000 + 6*900000 + 7*9000000 + 8*90000000 + 9;
			break;
		}
		
		System.out.println(ans);
	}
}