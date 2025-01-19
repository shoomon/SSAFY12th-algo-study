import java.util.*;

public class hotelroom {
	public static void main(String[] args) {
		Solution sol = new Solution();

		int ans = sol.solution(null);
		System.out.println(ans);
	}

	static class Solution {
		public int solution(String[][] book_time) {
			int answer = 0;
			// 시작 시간 순서대로 정렬
			Arrays.sort(book_time, (x, y) -> x[0].compareTo(y[0]));
			// 종료 시간 정렬 추가
			PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[1] - y[1]);
			// 넣으면서 정렬
			for (String[] time : book_time) {
				// 시간 숫자로 변경
				String[] starts = time[0].split(":");
				String[] ends = time[1].split(":");
				int start = Integer.parseInt(starts[0]) * 60 + Integer.parseInt(starts[1]);
				int end = Integer.parseInt(ends[0]) * 60 + Integer.parseInt(ends[1]) + 10;
				// 타임이 없으면 추가
				if (pq.isEmpty()) {
					answer++;
					pq.offer(new int[] { start, end });
				}else {
					int[] tmp = pq.poll();
					// 이미 앞의 값이 끝난 경우 추가 방이 필요없음
					if (start >= tmp[1]) {
						pq.offer(new int[] { start, end });
					} else {
						answer++;
						pq.offer(new int[] { start, end });
						pq.offer(tmp);
					}
				}
				
			}
			return answer;
		}
	}
}
