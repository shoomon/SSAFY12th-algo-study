import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int cnt = 0;
		cont : for (int i = 0; i < N; i++) {
			int curNum = sc.nextInt();
			if (curNum == 1)
				continue;
			else if (curNum == 2)
				cnt++;
			else {
				for (int j = 2; j < curNum; j++) {
					if (curNum % j == 0)
						continue cont;
				}
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}