package codingTest;


public class 연습_두원사이의정수쌍_woosoek {
    public static long solution(int r1, int r2) {
        long answer = 0;

        for(int x=1;x<=r2;x++){
            long minH = (long)Math.ceil(Math.sqrt(1.0*r1*r1 - 1.0*x*x));
            long maxH = (long)Math.floor(Math.sqrt(1.0*r2*r2 - 1.0*x*x));

            answer += (maxH - minH +1);
        }

        return answer*4;
    }

    public static void main(String[] args) {
        int r1 = 2;
        int r2 = 3;

        System.out.println(solution(r1,r2));
    }

}
