package codingTest;

import java.util.*;

public class 연습_숫자변환하기_wooseok {

    public int solution(int x, int y, int n) {
        // 목표 값이 시작 값보다 작은 경우 바로 반환
        if (x > y) return -1;
        if (x == y) return 0;

        Queue<Integer> queue = new ArrayDeque<>(); // 큐 초기화
        Set<Integer> visited = new HashSet<>(); // 방문 체크를 위한 Set

        queue.offer(x); // 시작값 추가
        visited.add(x);

        int steps = 0; // 이동 횟수 초기화

        while (!queue.isEmpty()) {
            int size = queue.size(); // 현재 큐의 사이즈를 저장
            for (int i = 0; i < size; i++) {
                int current = queue.poll();

                // 목표 값에 도달하면 반환
                if (current == y) return steps;

                // 다음 가능한 숫자 계산 및 큐에 추가
                int nextAdd = current + n;
                int nextMul2 = current * 2;
                int nextMul3 = current * 3;

                if (nextAdd <= y && !visited.contains(nextAdd)) {
                    visited.add(nextAdd);
                    queue.offer(nextAdd);
                }
                if (nextMul2 <= y && !visited.contains(nextMul2)) {
                    visited.add(nextMul2);
                    queue.offer(nextMul2);
                }
                if (nextMul3 <= y && !visited.contains(nextMul3)) {
                    visited.add(nextMul3);
                    queue.offer(nextMul3);
                }
            }
            steps++; // 각 레벨 탐색 완료 후 단계 증가
        }

        return -1; // 목표 값에 도달할 수 없는 경우
    }
}
