package PRO_1월_1주차;

import java.util.*;

public class 연습_과제진행하기_hyunjin {

	public static void main(String[] args) {
		String[][] plans = { { "science", "12:40", "50" }, { "music", "12:20", "40" }, { "history", "14:00", "30" },
				{ "computer", "12:30", "100" } };

		연습_과제진행하기_hyunjin sol = new 연습_과제진행하기_hyunjin();
		System.out.println(Arrays.toString(sol.solution(plans)));

	}

	static class Task {
		String name;
		int startTime;
		int playTime;

		Task(String name, int startTime, int playTime) {
			this.name = name;
			this.startTime = startTime;
			this.playTime = playTime;
		}

	}

	public String[] solution(String[][] plans) {

		List<Task> tasks = new ArrayList<>();
		for (int i = 0; i < plans.length; i++) {
			String name = plans[i][0]; // 과목명
			String time = plans[i][1]; // 시작 시간

			int hh = Integer.parseInt(time.split(":")[0]);
			int mm = Integer.parseInt(time.split(":")[1]);
			int startTime = 60 * hh + mm; // 시작 시간 -> 분으로 변경 후 정렬

			int playTime = Integer.parseInt(plans[i][2]); // 과제 진행 시간

			tasks.add(new Task(name, startTime, playTime));
		}

		tasks.sort((o1, o2) -> o1.startTime - o2.startTime);

		// 결과 확인용 출력
		// for (Task task : tasks) {
		// System.out.println(task.name + " " + task.startTime + " " + task.playTime);
		// }

		// 스택에 넣어서 남은 시간 계산 후, 순서대로 빼서 정답 list에 추가
		Stack<Task> stack = new Stack<>(); // 멈춘 과제를 저장
		List<String> result = new ArrayList<>(); // 완료된 과제 순서대로 저장

		int currTime = 0; // 현재 시간
		for (int i = 0; i < tasks.size(); i++) {
			Task currTask = tasks.get(i);
			// System.out.println(currTask.startTime);

			// 새로운 과제 시작
			currTime = currTask.startTime;
			// 다음 과제
			if (i < tasks.size() - 1 && currTime + currTask.playTime > tasks.get(i + 1).startTime) {
				currTask.playTime -= tasks.get(i + 1).startTime - currTime; // 현재 과제 남은 시간 계산
				stack.push(currTask); // 중지된 과제 => stack에 넣기
				currTime = tasks.get(i + 1).startTime; // 현재 시간 == 다음 과제 시작 시간으로 변경
			} else {
				// 완료된 과제하면, result 리스트에 추가하고, 현재 시간 변경
				result.add(currTask.name);
				currTime += currTask.playTime;
			}

			// 기존에 중지한 과제 다시 시작
			while (!stack.isEmpty() && (i < tasks.size() - 1 && currTime < tasks.get(i + 1).startTime)) {
				Task pausedTask = stack.pop();
				int remainingTime = pausedTask.playTime - (tasks.get(i + 1).startTime - currTime); // 중지된 과제의 남은 시간

				// 다음 과제 시작 전에 중지된 과제를 끝낼 수 있다면,
				if (remainingTime <= 0) {
					result.add(pausedTask.name);
					currTime += pausedTask.playTime;
				} else {
					pausedTask.playTime = remainingTime;
					stack.push(pausedTask);
					currTime = tasks.get(i + 1).startTime;
					break;
				}
			}
		}

		// 마지막 과제까지 다 끝내고 난 후,
		// 중단한 과제 마저 하기
		while (!stack.isEmpty()) {
			result.add(stack.pop().name);
		}

		String[] answer = new String[result.size()];
		for (int i = 0; i < result.size(); i++) {
			answer[i] = result.get(i);
		}

		return answer;
	}
}
