import java.util.Scanner;
/*
소요 메모리 : 17680KB
소요 시간 : 164ms

수 이어 쓰기1
특정 숫자까지의 수를 이어서 쓰면 새로운 하나의 수를 얻는다.
새로운 수는 몇 자리 수인가?
*/
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int targetNum = sc.nextInt();
		int counter = 9;
		int cnt = 1;
		int result = 0;
		while(true) {
			if (targetNum >= counter) {
				targetNum -= counter;
				result += counter * cnt;
			}else {
				result += targetNum * cnt;
				break;
			}
			counter *= 10;
			cnt++;
		}
		System.out.println(result);
	}
}