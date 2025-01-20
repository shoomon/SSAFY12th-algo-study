package PRO_1월_2주차;

import java.util.*;

public class 연습_호텔대실_hyunjin {
	public static class Time {
		int startTime;
		int endTime;

		public Time(int startTime, int endTime) {
			this.startTime = startTime;
			this.endTime = endTime;
		}
	}

	public int solution(String[][] book_time) {
		int answer = 0;

		// 예약 시간 리스트에 저장
		List<Time> bookList = new LinkedList<>();

		for (int i = 0; i < book_time.length; i++) {
			String[] time = book_time[i];
			String st = time[0];
			String et = time[1];
			int st_hh = Integer.parseInt(st.split(":")[0]);
			int st_mm = Integer.parseInt(st.split(":")[1]);
			int et_hh = Integer.parseInt(et.split(":")[0]);
			int et_mm = Integer.parseInt(et.split(":")[1]);

			// 각 시간을 분으로 바꿔서 list에 추가
			bookList.add(new Time(st_hh * 60 + st_mm, et_hh * 60 + et_mm));
		}

		// 시작 시간 기준 정렬
		bookList.sort((o1, o2) -> o1.startTime - o2.startTime);

		// 우선순위 큐에 넣기 => 가장 먼저 사용 가능한 객실의 끝나는 시간 기준
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		for (Time time : bookList) {
			// pq의 퇴실 시간 + 10분(청소시간) <= 다음 예약의 시작 시간 이라면,
			if (!pq.isEmpty() && pq.peek() + 10 <= time.startTime) {
				// 객실 재사용
				pq.poll();
			}

			// 새로운 예약 추가
			pq.add(time.endTime);
		}

		// pq의 크기 == 필요한 객실의 수
		answer = pq.size();

		return answer;
	}
}
