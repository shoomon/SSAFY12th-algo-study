package PRO_1월_4주차;

import java.util.*;

public class KAKAO_표병합_hyunjin {
	static String[][] table = new String[51][51];
	static int[][] group = new int[51][51]; // 같은 그룹인지 확인하기 위한 배열
	static int groupId = 1; // 그룹 id는 1로 초기화
	static List<String> answer = new ArrayList<>();

	public List<String> solution(String[] commands) {

		for (int i = 0; i < commands.length; i++) {
			String str = commands[i];

			String[] parts = str.split(" ");
			String command = parts[0];

			switch (command) {
			case "UPDATE":
				update(parts);
				break;
			case "MERGE":
				merge(parts);
				break;
			case "UNMERGE":
				unmerge(parts);
				break;
			case "PRINT":
				print(parts);
				break;
			}

		}
		return answer;
	}

	public void update(String[] parts) {
		// ex) UPDATE korean hansik
		if (parts.length == 3) {
			String value1 = parts[1];
			String value2 = parts[2];

			for (int i = 1; i < 51; i++) {
				for (int j = 1; j < 51; j++) {
					if (table[i][j] != null && table[i][j].equals(value1)) {
						table[i][j] = value2;
					}

				}
			}
			// ex) UPDATE 1 3 group
		} else {
			int r = Integer.parseInt(parts[1]);
			int c = Integer.parseInt(parts[2]);
			String value = parts[3];

			int currentGroup = group[r][c];

			if (currentGroup == 0) {
				table[r][c] = value; // 그룹이 없으면 개별 셀만 업데이트
			} else {
				// 그룹이 있는 경우 같은 그룹의 모든 셀 업데이트
				for (int i = 1; i <= 50; i++) {
					for (int j = 1; j <= 50; j++) {
						if (group[i][j] == currentGroup) {
							table[i][j] = value;
						}
					}
				}
			}
		}
	}

	public void merge(String[] parts) {
		int r1 = Integer.parseInt(parts[1]);
		int c1 = Integer.parseInt(parts[2]);
		int r2 = Integer.parseInt(parts[3]);
		int c2 = Integer.parseInt(parts[4]);

		// 두 위치의 셀이 같으면 무시
		if (r1 == r2 && c1 == c2)
			return;

		int g1 = group[r1][c1];
		int g2 = group[r2][c2];

		// 둘 다 처음 병합 시, 새 그룹 생성
		if (g1 == 0 && g2 == 0) {
			int newGroupId = groupId++;
			group[r1][c1] = newGroupId;
			group[r2][c2] = newGroupId;
			// 둘 중 하나만 그룹인 경우, 한 쪽으로 병합
		} else if (g1 == 0 && g2 != 0) {
			group[r1][c1] = g2;
		} else if (g1 != 0 && g2 == 0) {
			group[r2][c2] = g1;
			// 둘 다 그룹이 있는 경우, (r1,c1)으로 병합
		} else if (g1 != g2) {
			for (int i = 1; i <= 50; i++) {
				for (int j = 1; j <= 50; j++) {
					if (group[i][j] == g2) {
						group[i][j] = g1;
					}
				}
			}
		}

		// 병합 후, 값 동기화하기
		String mergedValue = table[r1][c1] != null ? table[r1][c1] : table[r2][c2];
		int mergedGroup = group[r1][c1] != 0 ? group[r1][c1] : group[r2][c2];

		for (int i = 1; i <= 50; i++) {
			for (int j = 1; j <= 50; j++) {
				if (group[i][j] == mergedGroup) {
					table[i][j] = mergedValue;
				}
			}
		}
	}

	public void unmerge(String[] parts) {
		int r = Integer.parseInt(parts[1]);
		int c = Integer.parseInt(parts[2]);

		int currGroup = group[r][c];

		if (currGroup == 0)
			return; // 병합된 그룹 없으면 무시

		String currValue = table[r][c];

		for (int i = 1; i <= 50; i++) {
			for (int j = 1; j <= 50; j++) {
				if (group[i][j] == currGroup) {
					group[i][j] = 0; // 병합 해제
					table[i][j] = null; // table 값 초기화
				}
			}
		}
		// (r,c) 셀만 현재 값으로 복원
		table[r][c] = currValue;
	}

	public void print(String[] parts) {
		int r = Integer.parseInt(parts[1]);
		int c = Integer.parseInt(parts[2]);

		if (table[r][c] == null) {
			answer.add("EMPTY");
		} else {
			answer.add(table[r][c]);
		}

	}
}
