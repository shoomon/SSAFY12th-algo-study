

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BOJ_2609_Ming {
	// 맞았습니다!!
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 숫자 두 개 받기
		int num1 = sc.nextInt();
		int num2 = sc.nextInt();
		
		// 약수 리스트 만들기
		List<Integer> div1 = new ArrayList<>();
		List<Integer> div2 = new ArrayList<>();
		
		// 첫번쨰 숫자 약수 리스트 뽑기
		for (int i=1; i<=num1; i++) {
			if (num1%i == 0) {
				div1.add(i);
			}
		}
		
		// 두 번째 숫자 약수 리스트 뽑기
		for (int i=1; i<=num2; i++) {
			if (num2%i == 0) {
				div2.add(i);
			}
		}
		
		// 최대 공약수
		int max = 0;
		
		loop:
		for (int i=div1.size()-1; i>=0; i--) {
			for (int j=div2.size()-1; j>=0; j--) {
				if (div1.get(i).equals(div2.get(j))) {
					// 같은
					max = div1.get(i);
					break loop;
				}
			}
		}
		
		// 최소공배수
		int min = num1 * num2 / max;
		
		System.out.println(max);
		System.out.println(min);
	}
}