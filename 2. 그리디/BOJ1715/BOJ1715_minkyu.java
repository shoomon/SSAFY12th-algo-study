import java.io.*;
import java.util.*;

/*
카드 정렬하기

카드 뭉치를 서로 뭉칠때의 시간이 가장 적게 걸리는 횟수를 구하시오.

메모리 : 24740 KB
시간 : 308 ms

*/

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++)
			pq.add(Integer.parseInt(br.readLine()));

		long sum = 0;
		while (pq.size() > 1) {
			int a = pq.poll();
			int b = pq.poll();
			sum += a + b;
			pq.add(a + b);
		}
		System.out.println(sum);
	}
}