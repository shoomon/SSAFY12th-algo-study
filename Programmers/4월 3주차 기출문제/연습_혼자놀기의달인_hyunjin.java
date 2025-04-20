package PRO_4월_3주차;

import java.util.*;

class 연습_혼자놀기의달인_hyunjin {
    // 1번 상자 : 8 -> 8번 상자 : 4 -> 4번 상자 : 7 -> 7번 상자 : 1 -> 1번 상자 : 8
    public int solution(int[] cards) {
        int answer = 0;
        
        // 그룹 내 상자의 수를 담고 있는 List
        List<Integer> groupBoxSize = new ArrayList<>();
        boolean [] visited = new boolean[cards.length];
        
        for(int i = 0; i<cards.length; i++){
            // 이미 방문한 상자면 다음으로 패쓰
            if(visited[i]) continue;
            
            int count = 0; //한 그룹에 해당하는 상자의 수 
            int current = i; // 현재 상자의 인덱스
            
            // 이미 열었던 상자로 돌아올 때까지 반복
            while(!visited[current]){
                visited[current] = true;
                current = cards[current] - 1; // 인덱스 값이므로 -1 해야함
                count++;
            }
            
            // 그룹의 크기를 넣기
            groupBoxSize.add(count);
            
        }
        
        // 그룹이 1번 밖에 없으면 점수는 0
        if(groupBoxSize.size() < 2) return 0;
        
        groupBoxSize.sort((a, b) -> b-a);
        answer = groupBoxSize.get(0) * groupBoxSize.get(1);
        
        return answer;
    }
}