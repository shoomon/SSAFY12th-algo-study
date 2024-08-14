

import java.util.Arrays;
import java.util.Scanner;

public class BOJ1037_Ming {
	public static void main(String[] args) {
		// 입력 받기
		Scanner sc = new Scanner(System.in);
		
		// N값 찾기
		
		// 약수 개수만큼 배열 만들기
		int num = sc.nextInt();
		int[] divs = new int[num];
		
		for (int i=0; i<num; i++) {
			divs[i] = sc.nextInt();
		}
		
		Arrays.sort(divs);
		
		System.out.println(divs[0]*divs[num-1]);
		
	}
}
