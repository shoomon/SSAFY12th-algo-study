package codingTest;

public class KAKAO_코딩테스트공부_wooseok {
    private static int[][] dp;
    private static final int MAX = Integer.MAX_VALUE;

    public static int solution(int alp, int cop, int[][] problems) {
        int answer = 0;

        int maxAlp = 0;
        int maxCop = 0;


        //문제들 중 가장 큰 필요 알고력 & 코딩력
        for (int[] problem : problems) {
            maxAlp = Math.max(problem[0], maxAlp);
            maxCop = Math.max(problem[1], maxCop);
        }

        dp = new int[maxAlp+2][maxCop+2];

        // 범위를 벗어나지 않도록
        if (alp > maxAlp) {
            alp = maxAlp;
        } else if (cop > maxCop) {
            cop = maxCop;
        }

        for (int i = alp; i < maxAlp + 1; i++) {
            for(int j = cop; j< maxCop+1; j++){
                dp[i][j] = MAX;
            }
        }

        dp[alp][cop] = 0;

        for (int i = alp; i < maxAlp + 1; i++) {
            for (int j = cop; j < maxCop + 1; j++) {
                // 매초 알고력 증가
                dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j]+1);

                //매초 코딩력 증가
                dp[i][j+1] = Math.min(dp[i][j+1], dp[i][j]+1);

                for (int[] problem : problems) {
                    int alpReq = problem[0];
                    int copReq = problem[1];
                    int alpRwd = problem[2];
                    int copRwd = problem[3];
                    int cost = problem[4];



                    //현재 나의 알고력과 코딩력으로 문제를 풀 수 있을 때
                    if (i >= alpReq && j >= copReq) {
                        int alpLevel = i + alpRwd;
                        int copLevel = j + copRwd;

                        //해당 문제를 통해 코딩력과 알고력이 모두 목표를 달성한 경우
                        if ((alpLevel >= maxAlp) && (copLevel >= maxCop)) {
                            dp[maxAlp][maxCop] = Math.min(dp[maxAlp][maxCop], dp[i][j] + cost);
                        }
                        //증가한 알고력만 목표달성인 경우
                        else if (alpLevel >= maxAlp) {
                            dp[maxAlp][copLevel] = Math.min(dp[maxAlp][copLevel], dp[i][j] + cost);
                        }
                        //증가한 코딩력만 목표달성인 경우
                        else if (copLevel >= maxCop) {
                            dp[alpLevel][maxCop] = Math.min(dp[alpLevel][maxCop], dp[i][j] + cost);
                        }
                        //둘다 달성하지 못한 경우
                        else {
                            dp[alpLevel][copLevel] = Math.min(dp[alpLevel][copLevel], dp[i][j] + cost);
                        }
                    }
                }
            }
        }

        answer = dp[maxAlp][maxCop];
        return answer;
    }


    /*
    private static class Node implements Comparable<Node> {
        int time, alp, cop;

        public Node(int time, int alp, int cop) {
            this.time = time;
            this.alp = alp;
            this.cop = cop;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.time, other.time);
        }
    }

    public int solution(int alp, int cop, int[][] problems) {
        int maxAlp = 0, maxCop = 0;

        // 목표 알고력과 코딩력 찾기
        for (int[] p : problems) {
            maxAlp = Math.max(maxAlp, p[0]);
            maxCop = Math.max(maxCop, p[1]);
        }

        // 초기 상태가 이미 목표치를 초과하는 경우 조정
        alp = Math.min(alp, maxAlp);
        cop = Math.min(cop, maxCop);

        int[][] dist = new int[maxAlp + 2][maxCop + 2]; // 최소 시간 저장 배열
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);
        dist[alp][cop] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, alp, cop));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int time = current.time;
            int curAlp = current.alp;
            int curCop = current.cop;

            // 목표 도달 시 종료
            if (curAlp >= maxAlp && curCop >= maxCop) return time;

            // 현재 상태에서 1초씩 증가하는 경우 추가
            if (curAlp + 1 <= maxAlp && time + 1 < dist[curAlp + 1][curCop]) {
                dist[curAlp + 1][curCop] = time + 1;
                pq.offer(new Node(time + 1, curAlp + 1, curCop));
            }

            if (curCop + 1 <= maxCop && time + 1 < dist[curAlp][curCop + 1]) {
                dist[curAlp][curCop + 1] = time + 1;
                pq.offer(new Node(time + 1, curAlp, curCop + 1));
            }

            // 문제를 풀었을 때의 상태 갱신
            for (int[] p : problems) {
                int reqAlp = p[0], reqCop = p[1], alpRwd = p[2], copRwd = p[3], cost = p[4];

                if (curAlp >= reqAlp && curCop >= reqCop) { // 문제를 풀 수 있는 경우
                    int nextAlp = Math.min(maxAlp, curAlp + alpRwd);
                    int nextCop = Math.min(maxCop, curCop + copRwd);

                    if (time + cost < dist[nextAlp][nextCop]) {
                        dist[nextAlp][nextCop] = time + cost;
                        pq.offer(new Node(time + cost, nextAlp, nextCop));
                    }
                }
            }
        }

        return dist[maxAlp][maxCop]; // 목표 상태까지 걸린 최소 시간 반환
    }
     */

}
