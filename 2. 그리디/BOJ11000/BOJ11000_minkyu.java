import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
강의실 배정

강의시간이 겹치지 않게 강의실을 배정하는 프로그램을 작성하고,
몇 개의 강의실이 필요한지 확인하시오.

메모리 : 76608 KB
시간 : 636 ms

*/

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int lectureCnt = Integer.parseInt(br.readLine());
		int[][] lectures = new int[lectureCnt][2];
		for (int i = 0; i < lectureCnt; i++) {
			st = new StringTokenizer(br.readLine());
			lectures[i][0] = Integer.parseInt(st.nextToken());
			lectures[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(lectures, (o1,o2)->{
			if (o1[0] == o2[0])
				return Integer.compare(o1[1], o2[1]);
			return Integer.compare(o1[0], o2[0]);
		});
		
		PriorityQueue<Integer> endTimes = new PriorityQueue<>();
		endTimes.offer(lectures[0][1]);
		for (int i = 1; i < lectureCnt; i++) {
			if (endTimes.peek() <= lectures[i][0])
				endTimes.poll();
			
			endTimes.offer(lectures[i][1]);
		}
		
		System.out.println(endTimes.size());
	}
}