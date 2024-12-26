
public class KAKAO_산모양타일링_mingyung {

    public static int solution(int n, int[] tops) {
        int answer = 0;
        

        int[] a = new int[n + 1]; // 사다리꼴 하나를 이용하는 방법
        int[] b = new int[n + 1]; // 나머지를 고려한 경우

        a[1] = 1;
        if (tops[0] == 1) b[1] = 3; // top이 있으면 마름모 가능
        else b[1] = 2; // 없으면 마름모 불가능

        // DP
        for (int i = 2; i <= n; i++) {
            // 사다리꼴 모양은 위가 있으나 없으나 같음
            a[i] = (a[i - 1] + b[i - 1]) % 10007;
            // 나머지 모양을 고려할 때 마름모 사용 여부를 위해 top 체크
            if (tops[i - 1] == 1) {
                b[i] = (a[i - 1] * 2 + b[i - 1] * 3) % 10007;
            } else {
                b[i] = (a[i - 1] + b[i - 1] * 2) % 10007;
            }
        }
        
        answer = (a[n] + b[n]) % 10007;
        
        return answer;
    }

    public static void main(String[] args) {
        int[] tops = {1, 1, 0, 1};
        System.out.println(solution(4, tops));
    }
}
