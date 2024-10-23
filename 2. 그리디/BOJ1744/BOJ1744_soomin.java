import java.io.*;
import java.util.*;
//24.10.22
//설계 시간: 분
//구현 시간: 분
//메모리: 14240 kb
//시간: 104 ms
//부호 같은 가장 큰 수 두 개
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> positive = new PriorityQueue<>((o1,o2)->o2-o1);
		PriorityQueue<Integer> negative = new PriorityQueue<>((o1,o2)->o1-o2);
		int zeroCnt=0, oneCnt=0;
		int answer=0;
		
		for(int i = 0; i < N; i++) {
			int in = Integer.parseInt(br.readLine());
			if(in < 0) {
				negative.offer(in);
			}else if(in == 0) {
				zeroCnt++;
			}else if(in == 1){
				oneCnt++;
			}else {
				positive.offer(in);
			}
		}
		
		
		while(positive.size() > 1) {
			int first = positive.poll();
			int second = positive.poll();
			answer += first*second;
		}
		
		while(negative.size() > 1) {
			int first = negative.poll();
			int second = negative.poll();
			answer += first*second;
		}
		
		if(positive.size() == 1) answer += positive.poll();
		if(zeroCnt == 0 && negative.size() == 1) answer += negative.poll();
		answer += oneCnt;
		
		bw.write(answer+"\n");
		bw.close();
	}
}
