import java.util.*;

public class BOJ1107_soomin {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] numPad = new int[10];
        int cur = 100;
        int nUp=N, nDown=N, cnt;

        for(int i= 0; i < M; i++) {
            int num = sc.nextInt();
            numPad[num] = -1;
        }

        //가고자 하는 채널이 현재 채널이면 0 출력
        cnt=0;
        //버튼 누르는 상한
        int max = Math.abs(N-cur);

        //가고자 하는 채널 위 아래 채널 숫자를 순차적으로 탐색
        while(cnt < max) {
//				System.out.println(cnt+" "+nUp+" "+" "+nDown);
            //위 또는 아래 채널 숫자버튼을 누를 수 있는지 확인
            if(nUp == cur || nDown == cur) break;
            boolean c1 = check(numPad, nUp), c2 = check(numPad, nDown);
            if(c1 && c2) {
                String s1 = nUp+"", s2 = nDown+"";
                cnt += Math.min(s1.length(), s2.length());
                break;
            }else if(c1) {
                String s = nUp+"";
                cnt += s.length();
                break;
            }else if(c2) {
                String s = nDown+"";
                cnt += s.length();
                break;
            }
            nUp++;
            cnt++;
            if(nDown <= 0) continue;
            nDown--;
        }
        cnt = Math.min(cnt, max);
        System.out.println(cnt);
    }

    //가고자 하는 채널의 각 숫자가 고장난 숫자가 아닌지 확인
    static boolean check(int[] numPad, int num) {
        String s = num+"";
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(numPad[c-'0'] == -1) return false;
        }
        return true;
    }

}