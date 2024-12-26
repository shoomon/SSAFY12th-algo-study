package codingTest;

public class KAKAO_산모양타일링_wooseok {
    private final int MOD = 10007;

    public int solution(int n, int[] tops) {
        // DP 배열 정의
        int[][] dp = new int[n + 1][2];

        // 첫 상태를 자유로운 상태로 초기화
        dp[0][1] = 1;

        for (int i = 0; i < n; i++) {
            int current = tops[i]; // 현재 tops 값
            int next = i + 1; // 다음 인덱스

            // 마지막 상태가 자유롭지 않을 때의 경우의 수 갱신
            dp[next][0] = (dp[i][0] + dp[i][1]) % MOD;

            if (current == 0) {
                // 현재 위치에 추가 삼각형이 없는 경우
                dp[next][1] = (dp[i][0] + dp[i][1] * 2) % MOD;
            } else {
                // 현재 위치에 추가 삼각형이 있는 경우
                dp[next][1] = (dp[i][0] * 2 + dp[i][1] * 3) % MOD;
            }
        }

        // 최종 상태를 합산하여 반환
        return (dp[n][0] + dp[n][1]) % MOD;
    }

    public static void main(String[] args) {
        KAKAO_산모양타일링_wooseok sol = new KAKAO_산모양타일링_wooseok();

        // 테스트 케이스
        int n1 = 4;
        int[] tops1 = {1, 1, 0, 1};
        System.out.println(sol.solution(n1, tops1)); // 예상 결과: 149

        int n2 = 2;
        int[] tops2 = {0, 1};
        System.out.println(sol.solution(n2, tops2)); // 예상 결과: 11

        int n3 = 10;
        int[] tops3 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        System.out.println(sol.solution(n3, tops3)); // 예상 결과: 7704
    }
}

