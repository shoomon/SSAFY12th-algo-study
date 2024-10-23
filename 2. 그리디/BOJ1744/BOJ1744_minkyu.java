import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

/*
수 묶기

수의 곱을 진행하여 수의 합이 최대가 되도록 프로그램을 구하시오.

메모리 : 11544 KB
시간 : 64 ms

*/

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cnt = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> minuses = new PriorityQueue<>();
		PriorityQueue<Integer> pluses = new PriorityQueue<>(Collections.reverseOrder());
		
		int sum = 0;
		for (int i = 0; i < cnt; i++) {
			int curVal = Integer.parseInt(br.readLine());
			if (curVal <= 0)
				minuses.offer(curVal);
			else if (curVal == 1)
				sum += 1;
			else
				pluses.offer(curVal);
		}
		
		// 마이너스들은 무조건 곱하는게 이득이다 하지만 플러스랑 곱하는 것은 비효율적이다.
		while(minuses.size() > 1){
			sum += minuses.poll() * minuses.poll();
		}
		while(!minuses.isEmpty())
			sum += minuses.poll();
		
		// 플러스들은 큰 수끼리 곱하는게 무조건 이득이다.
		while(pluses.size() > 1) {
			sum += pluses.poll() * pluses.poll();
		}
		while(!pluses.isEmpty())
			sum += pluses.poll();
		
		System.out.println(sum);
	}
}