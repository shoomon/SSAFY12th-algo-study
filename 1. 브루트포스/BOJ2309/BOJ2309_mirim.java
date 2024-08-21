import java.util.Arrays;
import java.util.Scanner;

public class BOJ2309_mirim {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 9명의 난쟁이 중에 2명 임포스터 누구야
		// 7명 난쟁이 키 합 : 100
		// 출력 : 7명 난쟁이 찾아서 키 오름차순 출력

		int sum = 0;
		int whoru = 0;
		int[] nanjang = new int[9];
		for (int i = 0; i < 9; i++) { // 난쟁이 키 입력받기
			nanjang[i] = sc.nextInt();
			sum += nanjang[i]; // 난쟁이 키합
		}
		whoru = sum - 100; // 색출해낼 두명 키 구함

		out: for (int i = 0; i < 9; i++) { 
			for (int k = 0; k < 9; k++) {
				if (nanjang[i] + nanjang[k] == whoru && i != k) { // 
					nanjang[i] = 0;
					nanjang[k] = 0;
					break out; // 키 더한 값 whoru랑 같은 값 나오면 반복 빠져나옴
				}

			}
		}
		Arrays.sort(nanjang); // 오름차순 정렬
		// 0빼고 배열 출력
		for (int i = 0; i < 9; i++) {
			if (nanjang[i] != 0) {
				System.out.println(nanjang[i]);
			}
		}

		sc.close();
	} // end of main
}
