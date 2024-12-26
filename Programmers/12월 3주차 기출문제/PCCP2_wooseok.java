package codingTest;
/*
// diff : 현재 퍼즐 난이도
// time_cur : 현재 퍼즐 소요시간
// time_prev : 이전 퍼즐 소요시간
// level : 플레이어 퍼즐 숙련도

diff <= level : time_cur만큼 시간소요하여 퍼즐 해결
diff > level : diff - level 만큼 실패, 실패할때마다 time_cur만큼 시간 소요  &  time_prev만큼 시간을 추가로 사용하여 이전퍼즐 재시도
             : 이전 퍼즐 시도 시 이전 퍼즐의 난이도와 관계없이 무조건 통과
             : (diff - level) 번 실패 후 도전 시 time_cur 만큼의 시간 사용 후 퍼즐 해결



 */
public class PCCP2_wooseok {

    public static void main(String[] args) {
        int diffs[] = {1, 5, 3};
        int times[] = {2, 4, 7};
        long limit = 30;

        int result = solution(diffs, times, limit);
        System.out.println(result);

    }


    public static int solution(int[] diffs, int[] times, long limit) {

        return binary_search(diffs, times, limit);

    }

    private static int binary_search(int[] diffs, int[] times, long limit) {
        int max = 1;
        int min = 100_000;

        while (max <= min) {
            int level = (max + min) / 2;
            long mid = cal(diffs, times, level);
            if (mid > limit) max = level + 1;
            else min = level - 1;
        }
        return max;
    }

    private static long cal(int[] diffs, int[] times, int level) {
        long ans = 0;
        for (int i = 0; i < diffs.length; i++) {
            if(diffs[i] <= level) ans += times[i];
            else ans += (long) (times[i] + times[i - 1]) * (long) (diffs[i] - level) + times[i];
        }
        return ans;
    }


}


