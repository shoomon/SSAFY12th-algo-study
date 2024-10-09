import java.io.*;
import java.util.StringTokenizer;
	
public class BOJ_2609_Wooseok {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        Long a = Long.parseLong(st.nextToken());
        Long b = Long.parseLong(st.nextToken());
        long gcd = getGCD(Math.max(a, b), Math.min(a, b));
        long lcm = getLCM(a, b, gcd);

        System.out.println(gcd);
        System.out.println(lcm);
    }

    //최대공약수
    static long getGCD(long a, long b) {
        if (b == 0) {
            return a;
        }
        return getGCD(b, a % b);
    }

    //최소공배수
    static long getLCM(long a, long b, long gcd) {
        return (a*b)/gcd;
    }
}
