import java.io.*;
import java.util.*;
//24.10.22
//설계 시간: 분
//구현 시간: 분
//메모리: 25812 kb
//시간: 324 ms
//다음 비교 횟수는 이전 합 + 다음 수 -> 2*a_i + a_{i+1} 형태 반복
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> nums = new PriorityQueue<>();
		int answer = 0;
		int sum=0;
		
		for(int i = 0; i < N; i++) nums.offer(Integer.parseInt(br.readLine()));
		
		while(nums.size() > 1) {
			int first = nums.poll();
			int second = nums.poll();
			answer += first+second;
			nums.offer(first+second);
		}
		
		bw.write(answer+"\n");
		bw.close();
	}
}

//5
//73
//18
//14
//24
//13
