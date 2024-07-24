import java.util.*;

public class BOJ4375_soomin {

	
	//아래 코드가 제대로 동작하지 않는 이유? (미해결)
//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		int N;
//		
//		while(sc.hasNext()) {
//			N = sc.nextInt();
//			System.out.println(N);
//			System.out.println(check(N));
//		}
//	}
//	
//	static int check(int N) {
//		int answer=0, n=1, idx=1;
//		
//		while(answer == 0) {
//			if(n >= N && n%N == 0) {
//				answer = idx;
//				break;
//			}
//			//x mod N = (x mod N) mod N
//			n = (n*10+1)%N;
//			idx++;
//		}
//		return answer;
//	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(sc.hasNextInt()) {
			int n = sc.nextInt();
			int num = 0;
			
			for(int i = 1; i <= n; i++) {
				num = (num*10+1)%n;
				if(num == 0) {
				System.out.println(i);
				break;
				}
			}
			
		}
	}

}
