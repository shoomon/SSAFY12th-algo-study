package PRO_5월_2주차;

import java.util.*;

class 완전탐색_전략망을둘로나누기_hyunjin {

	public int solution(int n, int[][] wires) {
		int answer = n;

		for (int i = 0; i < wires.length; i++) {
			// 1. 트리 초기화하기
			List<List<Integer>> tree = new ArrayList<>();

			for (int j = 0; j <= n; j++) {
				tree.add(new ArrayList<>());
			}

			// 2. 간선을 하나 끊고 트리 연결하기
			for (int j = 0; j < wires.length; j++) {
				// i번째 간선 끊기
				if (i == j)
					continue;

				// j번째 전선 정보에서 연결된 두 개의 송전탑
				int v1 = wires[j][0];
				int v2 = wires[j][1];

				// 두 개의 탑을 연결하기
				tree.get(v1).add(v2);
				tree.get(v2).add(v1);
			}

			// 3. 한 쪽의 서브트리 크기 구하기
			boolean[] visited = new boolean[n + 1];
			int cnt1 = dfs(1, tree, visited);

			// 4. 다른 쪽 서브트리 크기
			int cnt2 = n - cnt1;
			
			// answer : 두 전력망이 가지고 있는 송전탑 개수의 차이 (절댓값) 
			answer = Math.min(answer, Math.abs(cnt1 - cnt2));
		}
		return answer;
	}

	private int dfs(int idx, List<List<Integer>> tree, boolean[] visited) {
		visited[idx] = true;
		int cnt = 1;

		for (int next : tree.get(idx)) {
			if (!visited[next]) {
				cnt += dfs(next, tree, visited);
			}
		}

		return cnt;
	}
}