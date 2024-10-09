
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2309_mingyung {
	// 브론즈1_일곱 난쟁이_맞았습니다!!
	// 난쟁이 키의 합이 100
	// 9명 중 7명 찾아 오름차순으로 키 정렬해 출력
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 난쟁이 키 배열 만들기 + 키의 합 구하기
		int[] height = new int[9];
		int sum = 0;
		for (int i=0; i<9; i++) {
			height[i] = Integer.parseInt(br.readLine());
			sum += height[i];
		}
		int x = sum-100;
		// 빼야하는 난쟁이(i, j) 찾기
		int i=0;
		int j=0;
		here:
		for (i=0; i<8; i++) {
			for (j=i+1; j<9; j++) {
				if (height[i]+height[j] == x) {
					break here;
				}
			}
		}
		
		// 난쟁이 배열 만들어서 출력하기
		int[] small = new int[7];
		int n=0;
		int m=0;
		while (n != 7) {
			if (m != i && m!=j) {
				small[n] = height[m];
				n++;
			}
			m++;
		}
		Arrays.sort(small);
		for (n=0; n<7; n++) {
			System.out.println(small[n]);
		}
	}
}