package codingTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PCCP_3_wooseok {

    public static void main(String[] args) {
        int[][] points = {
                {0, 0}, {2, 3}, {5, 1}
        };
        int[][] routes = {
                {1, 2, 3}, {2, 3, 1}, {3, 1, 2}
        };

        int result = solution(points, routes);
        System.out.println(result);
    }

    // 로봇 상태를 나타내는 내부 클래스
    private static class Robot {
        private int r; // 현재 행 위치
        private int c; // 현재 열 위치
        private int nextStopover; // 다음 경유지 인덱스

        public Robot(int r, int c, int nextStopover) {
            this.r = r;
            this.c = c;
            this.nextStopover = nextStopover;
        }

        // 목표 위치로 한 칸 이동
        public void moveToTarget(int targetR, int targetC) {
            if (r < targetR) {
                r++;
            } else if (r > targetR) {
                r--;
            } else if (c < targetC) {
                c++;
            } else if (c > targetC) {
                c--;
            }
        }

        // 목표 위치에 도착했는지 확인
        public boolean hasArrived(int targetR, int targetC) {
            return r == targetR && c == targetC;
        }

        // 다음 경유지로 이동 준비
        public void stopoverPlus() {
            nextStopover++;
        }
    }

    // 충돌 횟수를 계산하는 메인 솔루션 메서드
    public static int solution(int[][] points, int[][] routes) {
        int routeStep = routes[0].length; // 각 경로의 단계 수
        List<Robot> robots = new ArrayList<>(); // 로봇 리스트 초기화
        Map<Integer, Integer> startCollisions = new HashMap<>(); // 시작 지점 충돌 체크

        // 로봇 초기화 및 시작 충돌 확인
        for (int[] route : routes) {
            int startPoint = route[0] - 1; // 시작 지점 인덱스
            int r = points[startPoint][0]; // 시작 지점 행 좌표
            int c = points[startPoint][1]; // 시작 지점 열 좌표
            robots.add(new Robot(r, c, 1)); // 로봇 생성
            startCollisions.merge(route[0], 1, Integer::sum); // 시작 지점 충돌 기록
        }

        int result = 0; // 충돌 횟수
        // 시작 위치에서의 충돌 처리
        for (Integer value : startCollisions.values()) {
            if (value > 1) {
                result++;
            }
        }

        // 모든 로봇이 경로를 완료할 때까지 시뮬레이션
        while (!robots.isEmpty()) {
            Map<String, Integer> collisionMap = new HashMap<>(); // 현재 위치 충돌 체크
            boolean[] arrived = new boolean[robots.size()]; // 도착 여부 배열

            // 각 로봇 이동
            for (int i = 0; i < robots.size(); i++) {
                Robot robot = robots.get(i);
                int nextStopover = robot.nextStopover;

                // 경로의 끝에 도달한 경우
                if (nextStopover >= routeStep) {
                    arrived[i] = true;
                    continue;
                }

                // 다음 목표 위치 계산
                int pointIndex = routes[i][nextStopover] - 1;
                int targetR = points[pointIndex][0];
                int targetC = points[pointIndex][1];

                // 목표 위치로 이동
                robot.moveToTarget(targetR, targetC);

                // 현재 위치를 키로 충돌 체크
                String posKey = robot.r + " " + robot.c;
                collisionMap.merge(posKey, 1, Integer::sum);

                // 목표 위치에 도착한 경우 경유지 증가
                if (robot.hasArrived(targetR, targetC)) {
                    robot.stopoverPlus();
                }
            }

            // 현재 위치에서 발생한 충돌 처리
            for (Integer value : collisionMap.values()) {
                if (value > 1) {
                    result++;
                }
            }

            // 모든 로봇이 경로를 완료했는지 확인
            boolean allArrived = true;
            for (boolean arrivedStatus : arrived) {
                if (!arrivedStatus) {
                    allArrived = false;
                    break;
                }
            }

            // 모든 로봇이 완료되면 종료
            if (allArrived) {
                break;
            }
        }

        return result; // 최종 충돌 횟수 반환
    }
}
