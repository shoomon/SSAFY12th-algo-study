
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class BOJ2992_mingyung {
	// 정수 X가 주어졌을 때,
	// X와 구성이 같으면서 X보다 큰 수 중 가장 작은 수
	// 수를 이루는 각 자리수가 같은 수 ex. 123 = 321 | 123 != 432
	
	// 순열 위한 변수 선언
	static char[] nums, tmp;
	static int N, min, num;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		// 입력 받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		// 필요한 변수 초기화
		nums = str.toCharArray();
		N = nums.length;
		num = Integer.parseInt(str);
		min = Integer.MAX_VALUE;
		visited = new boolean[N];
		tmp = new char[N];
		
		perm(0);
		
		// 출력하기
		if (min != Integer.MAX_VALUE) {
			System.out.println(min);
		} else {
			System.out.println(0);
		}
	} // main

	static void perm(int idx) {
		if (idx==N) {
			// 배열 다 만들었으면 숫자로 변환해서 최솟값 찾기
			String n = "";
			for (int i=0; i<N; i++) {
				n += tmp[i];
			}
			int number = Integer.parseInt(n);
			if (number > num && number < min) {
				min = number;
			}
		}
		
		// 순열 재귀
		for (int i=0; i<N; i++) {
			if (visited[i]) continue;
			tmp[idx] = nums[i];
			visited[i] = true;
			perm(idx+1);
			visited[i] = false;
		}
	}
}

/*     백트래킹말고 그냥 푼 거!!

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class BOJ2992_mingyung_2 {
	// 정수 X가 주어졌을 때,
	// X와 구성이 같으면서 X보다 큰 수 중 가장 작은 수
	// 수를 이루는 각 자리수가 같은 수 ex. 123 = 321 | 123 != 432
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		char[] num = str.toCharArray();
		// 자리 바꿨는지 확인 위한 boolean 선언
		boolean check = false;
		
		// 출력용 Queue
		Queue<Character> num1 = new LinkedList<>();
		PriorityQueue<Character> num2 = new PriorityQueue<>();
		
		// 뒤에서부터 크기 비교해 앞 숫자보다 뒤 숫자가 큰 위치 찾기
		here:
		for (int i=num.length-2; i>=0; i--) {
			for (int j=num.length-1; j>i; j--) {
				// i가 앞으로 가는 건 뒤 숫자보다 크기 때문
				// => j는 i 뒤 중에서 항상 최솟값
				if (num[i]<num[j]) {
					for (int k=0; k<i; k++) {
						num1.add(num[k]);
					}
					// 최솟값이 앞으로 와야지 큰 수 중 가장 작은 수
					num1.add(num[j]);
					for (int k=i; k<num.length; k++) {
						if (k != j) {
							num2.add(num[k]);
						}
					}
					// 자리 바꿔졌으면 check를 true로 바꾸기
					// => 결과 처리, 반복문 끝내기
					check = true;
					break here;
				}
			} // 두 수 비교 
		}
		
		// 출력하기
		if (check) {
			while (!num1.isEmpty()) {
				System.out.print(num1.poll());
			}
			while (!num2.isEmpty()) {
				System.out.print(num2.poll());
			}
		} else {
			System.out.println(0);
		}
	} // main
}
*/