import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//일단 5로 나눠보고 안되면 봉지 추가 후 3을 빼고 다시 해본다.
		int n = Integer.parseInt(br.readLine());
		int ans = 0;
		while(n >= 3) {
			//갱신하면서 5로 나눠떨어지는지 확인 
			if(n % 5 == 0) {
				ans += n/5;
				n = 0;
				break;
			}else {
			
			ans++;
			n-=3;
			}
		}
		
		if(n == 0) {
			//나눠떨어지지 않으면 -1, 떨어지면 ans출력 
			System.out.println(ans);
		}else {
			System.out.println(-1);
		}
	}
}
