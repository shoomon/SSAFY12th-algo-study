package codingTest;

import java.util.*;

public class 연습_과제진행하기_wooseok {
    public String[] solution(String[][] plans) {
        // 과제 시작시간순 정렬
        Arrays.sort(plans, new Comparator<String[]>(){
            @Override
            public int compare(String[] o1, String[] o2){
                return o1[1].compareTo(o2[1]);
            }
        });

        // 대기 큐
        Stack<String[]> stop = new Stack<>();
        // 정답 리스트
        List<String> list = new ArrayList<>();
        // 타이머
        int timer = 0;
        // 과제 반복문
        for(String[] now : plans){
            // 현재 과제
            String name  = now[0];
            // 시작시간 분으로 계산
            int start = Integer.parseInt(now[1].split(":")[0])*60 + Integer.parseInt(now[1].split(":")[1]);
            // 수행시간 분으로 계산
            int playtime =  Integer.parseInt(now[2]);

            // 타이머 + 스택의 과제 남은시간 <= 현재과제 시작시간
            while (!stop.isEmpty() && timer + Integer.parseInt(stop.peek()[1]) <= start) {
                // 스택에 있는 과제를 꺼내서, 리스트에 추가한다.
                String[] s=stop.pop();
                list.add(s[0]);
                timer+= Integer.parseInt(s[1]);
            }
            // 스택에 과제가 비어있으면
            if (stop.isEmpty()) {
                // 스택에 과제 추가
                stop.push(new String[]{name,String.valueOf(playtime)});
                timer = start;
                // 스택에 과제가 비어있지않고, 타이머 + 스택의 과제 수행시간 > 현재과제 수행시간
            } else if (timer + Integer.parseInt(stop.peek()[1]) > start) {
                // 남은시간을 스택 과제에서 차감한다.
                stop.peek()[1] = String.valueOf(Integer.parseInt(stop.peek()[1])- (start-timer));
                // 스택에 현재 과제를 추가한다.
                stop.push(new String[] {name, String.valueOf(playtime)});
                timer = start;
            }
        }
        // 과제 반복문이 끝나고, 스택이 비어있지 않으면,
        // 남은 과제를 처리한다.
        while (!stop.isEmpty()) {
            timer += Integer.parseInt(stop.peek()[1]);
            list.add(stop.pop()[0]);
        }


        return list.stream().toArray(String[]::new);
    }
}
