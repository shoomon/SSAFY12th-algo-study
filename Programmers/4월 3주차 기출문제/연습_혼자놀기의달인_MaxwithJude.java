import java.util.*;

class Solution {
    public int solution(int[] cards) {
        int n = cards.length;
        boolean[] visited = new boolean[n];
        List<Integer> groupSizes = new ArrayList<>();
        //순환하는 세트는 어디서 시작해도 똑같이 끝남
        //예를 들면 1,4,7,8 일 때 어디서 시작해도 그룹 1은 1,4,7,8임
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;

            int count = 0;
            int current = i;
            //방문하지 않았으면
            while (!visited[current]) {
                visited[current] = true;
                //밸류
                current = cards[current] - 1;
                count++;
            }
            //그룹에 추가
            groupSizes.add(count);
        }

        // 사이클 크기가 1개일 수도 있으므로 예외처리
        if (groupSizes.size() < 2) return 0;

        // 내림차순 정렬 후, 가장 큰 두 개 선택
        Collections.sort(groupSizes, Collections.reverseOrder());

        return groupSizes.get(0) * groupSizes.get(1);
    }
}
