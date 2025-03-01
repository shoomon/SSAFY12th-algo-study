package 프로그래머스;

import java.util.*;

class 연습_숫자변환하기 {
    public int solution(int x, int y, int n) {
        if (x == y) return 0; // 이미 x와 y가 같다면 연산이 필요 없음
        
        Queue<int[]> queue = new LinkedList<>(); // BFS 탐색을 위한 큐 (현재 값, 연산 횟수)
        Set<Integer> visited = new HashSet<>(); // 방문한 숫자 저장
        
        queue.add(new int[]{x, 0}); // 초기 값과 연산 횟수 0으로 설정
        visited.add(x);
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll(); // 큐에서 현재 값과 연산 횟수 가져오기
            int value = current[0];
            int steps = current[1];
            
            // 가능한 연산 수행 (x + n, x * 2, x * 3)
            int[] nextValues = {value + n, value * 2, value * 3};
            
            for (int next : nextValues) {
                if (next == y) return steps + 1; // 목표 값 도달 시 최소 연산 횟수 반환
                
                if (next < y && !visited.contains(next)) { // y를 초과하지 않으며 방문하지 않은 값만 큐에 추가
                    queue.add(new int[]{next, steps + 1});
                    visited.add(next);
                }
            }
        }
        
        return -1; // y를 만들 수 없는 경우 -1 반환
    }
}