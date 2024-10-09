import java.util.Scanner;
//시간복잡도: 15*28*19 = 약 1만2천 이하
public class BOJ1476_soomin {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] in = new int[3];
        int E=1, S=1, M=1, cnt=1;

        for(int i = 0; i < 3; i++) {
            in[i] = sc.nextInt();
        }
        while(true) {
            if(in[0] == E && in[1] == S && in[2] == M) break;
            E++;
            S++;
            M++;
            if(E == 16) {
                E = 1;
            }
            if(S == 29) {
                S = 1;
            }
            if(M == 20) {
                M = 1;
            }
            cnt++;
        }
        System.out.println(cnt);

    }

}