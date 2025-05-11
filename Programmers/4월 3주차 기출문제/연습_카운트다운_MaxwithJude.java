class Solution {
    public int[] solution(int target) {
        int[] answer = new int[2];
        int throwcnt = 0;
        int cnt = 0;
        
        int[] scores = new int[21];  // 1부터 20까지
        for (int i = 1; i <= 20; i++) {
            scores[i] = i;
        }

        while (target > 0 && throwcnt < 3) {
            if (target == 50 || contains(scores, target)) {
                // 불 or 싱글이면
                target -= target;
                throwcnt++;
                cnt++;
            } else if (target >= 40 && target % 2 == 0) {
                // 더블
                target -= target;
                throwcnt++;
            } else if (target >= 60 && target % 3 == 0) {
                // 트리플
                target -= target;
                throwcnt++;
            } else {
                // 싱글 처리
                target -= 1;
                throwcnt++;
                cnt++;
            }
        }

        answer[0] = throwcnt;
        answer[1] = cnt;
        return answer;
    }

    private boolean contains(int[] arr, int value) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == value) return true;
        }
        return false;
    }
}
