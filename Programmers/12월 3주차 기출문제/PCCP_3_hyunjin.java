/* 
 [PCCP 기출문제]_3번_충돌 위험 피하기 

오답 -> 테케는 통과하는데 제출하면 패스 못 함

timeMap을 활용하는 방법
출력하면 아래와 같이 나옴
이때, value가 2 이상이면 충돌한 상태라고 수를 세면 된다고 생각하는데, 안 됨.. ㅜㅜ

{0={1,4=1, 3,2=1, 6,4=1}, 1={2,4=1, 4,2=1, 5,4=1}, 2={3,4=1, 4,3=1, 4,4=1}, 3={3,4=1, 4,4=2}, 4={2,4=1, 5,4=1, 4,5=1}, 5={1,4=1, 6,4=1, 4,6=1}, 6={4,7=1}} 
 */


import java.util.*;

class PCCP_3_hyunjin {
	// 로봇이 움직인 좌표 및 시간을 저장하는 Pos 클래스
	public static class Pos {
		public int x;
		public int y;
		public int time;

		public Pos(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}

	// 상, 하, 좌, 우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public int solution(int[][] points, int[][] routes) {
		int answer = 0;
		// 충돌 횟수를 계산하기 위한 Map
		// 시간 별로 좌표를 추적한다.
		// ex) Map<1초, Map<"3,5 위치", 1번 방문>>
		Map<Integer, Map<String, Integer>> timeMap = new HashMap<>();

		for (int[] route : routes) {
			// 각 로봇별로 경로 계산
			calculateRoutes(points, route, timeMap);
		}

		System.out.println(timeMap);

		// timeMap에서 로봇의 방문 횟수가 2이상인 곳 == 충돌한 곳
		for (Map<String, Integer> map : timeMap.values()) {
			for (int count : map.values()) {
				if (count >= 2) {
					answer++;
				}
			}
		}

		// System.out.println(answer);
		return answer;
	}

	public static void calculateRoutes(int[][] points, int[] route, Map<Integer, Map<String, Integer>> timeMap) {
		int startPoint = route[0]; // 로봇의 시작 포인트
		int endPoint = route[1]; // 로봇의 도착 포인트
		int startX = points[startPoint - 1][0];
		int startY = points[startPoint - 1][1];
		int endX = points[endPoint - 1][0];
		int endY = points[endPoint - 1][1];

		Queue<Pos> queue = new LinkedList<>();
		queue.add(new Pos(startX, startY, 0)); // 로봇이 움직이기 시작한 위치 x, y, 시간

		while (!queue.isEmpty()) {
			Pos curr = queue.poll();
			int currX = curr.x;
			int currY = curr.y;
			int currTime = curr.time;

			// 현재 좌표를 문자열(String)으로 변환
			String movingPosition = currX + "," + currY;

			// putIfAbsent
			// 1. key가 존재할 경우, value 반환
			// 2. key가 존재하지 않거나 value가 null 인 경우 -> key와 value 추가
			timeMap.putIfAbsent(currTime, new HashMap<>());

			// getOrDefault
			// 1. key가 존재할 경우, value 반환
			// 2. key가 존재하지 않거나 value가 null 인 경우 -> default 값 반환
			int meetCnt = timeMap.get(currTime).getOrDefault(movingPosition, 0) + 1;
			// ex) currTime = 1초에, movingPosition = "3,5"인 지점에 아무도 없으면 0 +1/ 누가 왔다면 기존 방문한
			// 로봇수 +1 => 누적 방문 횟수 기록
			timeMap.get(currTime).put(movingPosition, meetCnt);

			// 도착 지점에 도달하면 탐색 종료
			if (currX == endX && currY == endY) {

				// 큐를 비우고 완전히 종료
				// queue.clear();
				break;
			}
			// r 좌표로 우선 이동, -> 이후 c 좌표로 이동
			if (currX != endX) {
				int nx = currX + ((currX < endX) ? +1 : -1);
				queue.add(new Pos(nx, currY, currTime + 1));
			} else if (currY != endY) {
				int ny = currY + ((currY < endY) ? +1 : -1);
				queue.add(new Pos(currX, ny, currTime + 1));
			}
		}

	}
}
