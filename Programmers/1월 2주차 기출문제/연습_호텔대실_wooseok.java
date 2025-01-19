package codingTest;

import java.time.format.DateTimeFormatter;
import java.util.*;

public class 연습_호텔대실_wooseok {
    public static int solution(String[][] book_time) {
        // 예약 시간을 분 단위로 변환하여 시작 시간과 종료 시간 처리
        PriorityQueue<Integer> ongoingRooms = new PriorityQueue<>();
        int maxRooms = 0;

        // 예약 배열을 시간 순으로 정렬
        Arrays.sort(book_time, (a, b) -> a[0].compareTo(b[0]));

        for (String[] time : book_time) {
            int start = timeToMinutes(time[0]);
            int end = timeToMinutes(time[1]) + 10;  // 종료 시간에 10분 추가

            // 진행 중인 예약 중 끝나는 시간이 현재 예약의 시작 시간보다 빠른 예약을 제거
            while (!ongoingRooms.isEmpty() && ongoingRooms.peek() <= start) {
                ongoingRooms.poll();
            }

            // 현재 예약을 ongoingRooms에 추가
            ongoingRooms.offer(end);

            // 최대 객실 수 갱신
            maxRooms = Math.max(maxRooms, ongoingRooms.size());
        }

        return maxRooms;
    }

    // 시간을 "HH:mm" 형식으로 분 단위로 변환하는 메서드
    private static int timeToMinutes(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }

    public static void main(String[] args) {

        // 예시 1
        String[][] example1 = {{"15:00", "17:00"}, {"16:40", "18:20"}, {"14:20", "15:20"}, {"14:10", "19:20"}, {"18:20", "21:20"}};
        System.out.println(solution(example1));  // 예상 출력: 3

        // 예시 2
        String[][] example2 = {{"09:10", "10:10"}, {"10:20", "12:20"}};
        System.out.println(solution(example2));  // 예상 출력: 1

        // 예시 3
        String[][] example3 = {{"10:20", "12:30"}, {"10:20", "12:30"}, {"10:20", "12:30"}};
        System.out.println(solution(example3));  // 예상 출력: 3
    }
}
