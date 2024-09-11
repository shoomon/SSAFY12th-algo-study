import java.util.Scanner;
/*
소요 메모리 : 17628KB
소요 시간 : 168ms

1,2,3 더하기
정수 n을 1,2,3의 합으로 나타내는 모든 방법의 수를 구하는 프로그램 작성
*/
public class Main {
	static int cnt = 0;
	static int targetNum = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		// 테스트 케이스 수만큼 반복
		for (int tc = 0; tc < T; tc++) {
			targetNum = sc.nextInt();
			cnt = 0;
			getCnt(0);
			System.out.println(cnt);
		}
	}
	
	public static void getCnt(int curNum) {
		// 크기가 넘어가면 더 이상 선택 불가
		if (targetNum < curNum)
			return;
		// 크기가 넘어가지 않아도 선택 가능
		if (targetNum == curNum) {
			cnt++;
			return;
		}
		
		// 타겟 번호보다 작은 숫자들 내에서 중복 가능하게 선택해서 경우의 수를 구할 수 있음
		// 순서가 달라도 같이 선택 되어야 하기 때문에 해당 방식 이용해도 상관 없음
		for (int i = 1; i <= 3; i++) {
			getCnt(curNum + i);
		}
	}
}