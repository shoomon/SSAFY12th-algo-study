
import java.util.*;
import java.io.*;

class Solution {
    class Robot {
        int[] curPos = new int[2];
        Queue<Integer> routes = new ArrayDeque<>();
    }

    public int solution(int[][] points, int[][] routes) {
        int[][] map = new int[101][101];

        // 로봇에 대한 정보 저장
        List<Robot> robots = new ArrayList<>();
        for (int i = 0; i < routes.length; i++) {
            Robot robot = new Robot();
            robot.curPos[0] = points[routes[i][0] - 1][0];
            robot.curPos[1] = points[routes[i][0] - 1][1];
            map[robot.curPos[0]][robot.curPos[1]]++;
            for (int j = 1; j < routes[i].length; j++)
                robot.routes.offer(routes[i][j]);
            robots.add(robot);
        }

        // 충돌가능 횟수 기록
        int crashCnt = 0;
        // 로봇이 있을때까지 계속 진행
        while (!robots.isEmpty()) {
            // 판에 2개 이상의 로봇이 존재하는 곳이 있는지 파악.
            for (int i = 0; i < 101; i++) {
                for (int j = 0; j < 101; j++) {
                    if (map[i][j] > 1)
                        crashCnt++;
                }
            }

            // 로봇이 마지막 위치에 도달한 경우 해당 로봇 제외시킬 것.
            for (int i = robots.size() - 1; i >= 0; i--) {
                if (robots.get(i).routes.size() == 0) {
                    map[robots.get(i).curPos[0]][robots.get(i).curPos[1]]--;
                    robots.remove(i);
                }
            }

            // 로봇을 움직이는 과정이 필요하다.
            for (Robot robot : robots) {
                // 이전 로봇이 존재하고 있던 위치는 로봇 삭제
                map[robot.curPos[0]][robot.curPos[1]]--;

                // 목표 지점
                int[] tarPos = points[robot.routes.peek() - 1];

                if (tarPos[0] > robot.curPos[0])
                    robot.curPos[0]++;
                else if (tarPos[0] < robot.curPos[0])
                    robot.curPos[0]--;
                else if (tarPos[1] > robot.curPos[1])
                    robot.curPos[1]++;
                else if (tarPos[1] < robot.curPos[1])
                    robot.curPos[1]--;

                // 이동을 완료하면, 해당 위치에 로봇이 존재함을 표시할 것
                map[robot.curPos[0]][robot.curPos[1]]++;

                // 원하던 목표지점에 도착한 경우엔 해당 위치에 대한 목표 루트를 제외할 것
                if (tarPos[0] == robot.curPos[0] && tarPos[1] == robot.curPos[1])
                    robot.routes.poll();
            }
        }


        return crashCnt;
    }
}

