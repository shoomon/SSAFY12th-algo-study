import java.util.*;
import java.io.*;

/*
단어 수학

알파벳에 숫자를 할당하여 해당 덧셈이 최대의 값이 되도록 하는 프로그램을 작성하시오.

메모리 : 18300 KB
시간 : 188 ms

*/

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// 0 = A, 9 = J
		int[] arr = new int[26];
		String[] stringArr = new String[N];
		for (int i = 0; i < N; i++) {
			stringArr[i] = br.readLine();
			for (int j = 0; j < stringArr[i].length(); j++)
				arr[stringArr[i].charAt(j) - 'A'] += Math.pow(10, stringArr[i].length() - j - 1);
		}
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->{
			return -Integer.compare(o1[1], o2[1]);
		});
		
		for (int i = 0; i < 26; i++) {
			if (arr[i] == 0) continue;
			pq.add(new int[] {i, arr[i]});
		}
		
		Arrays.fill(arr, -1);
		int cnt = 9;
		while(!pq.isEmpty()) {
			int[] tmp = pq.poll();
			arr[tmp[0]] = cnt--;
		}
		
		long sum = 0;
		for (int j = 0; j < N; j++) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < stringArr[j].length(); i++)
				sb.append(arr[stringArr[j].charAt(i) - 'A']);
			sum += Integer.parseInt(sb.toString());
		}
		System.out.println(sum);
	}
}