package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// BOJ1931_회의실배정
// Scanner => 메모리 : 165864KB / 시간 : 928ms
// BufferedReader => 메모리 : 43224KB / 시간 : 444ms
public class BOJ1931_hyunjin {

	public static class Node implements Comparable<Node> {
		int start, end;

		public Node(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Node o) {
			// 끝나는 시간이 같다면, 시작 시간 기준으로 정렬하기
			if (this.end == o.end) {
				return this.start - o.start;
			}
			// 회의가 끝나는 시간을 기준으로 정렬하기 => 끝나는 시간에 이은 다음 회의 배치에 유리
			return this.end - o.end;
		}

		@Override
		public String toString() {
			return "Time [start=" + start + ", end=" + end + "]";
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		Scanner sc = new Scanner(System.in);

		int N = Integer.parseInt(br.readLine()); // 회의의 수

		ArrayList<Node> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int startTime = Integer.parseInt(st.nextToken());
			int endTime = Integer.parseInt(st.nextToken());
			list.add(new Node(startTime, endTime));
		}

		Collections.sort(list);
//		System.out.println(list.toString());

		int ans = 0; // 정답 수
		int endTime = 0; // 끝나는 수 갱신을 위한 변수

		// list 내의 모든 노드를 돌면서,
		for (Node meeting : list) {
			// 다음 회의 시작시간이 이전 회의 끝나는 시간보다 크면,
			// 끝나는 시간 갱신
			if (meeting.start >= endTime) {
				endTime = meeting.end;
				ans++; // 회의 수 up
			}
		}

		System.out.println(ans);

	}
}
