package 시험준비.문제;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class BOJ_파스칼삼감형 {
    public static void main(String[] args) throws IOException {
        // BufferedReader를 사용하여 표준 입력으로부터 데이터 읽기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 문자열을 공백을 기준으로 분리하기 위해 StringTokenizer 사용
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 첫 번째 토큰을 정수형으로 변환하여 n에 저장
        int n = Integer.parseInt(st.nextToken());
        // 두 번째 토큰을 정수형으로 변환하여 k에 저장
        int k = Integer.parseInt(st.nextToken());

        // n번째 행의 k번째 수를 계산하여 출력
        System.out.println(binomialCoefficient(n, k));
    }

    // 이항계수를 계산하는 메소드
    private static BigInteger binomialCoefficient(int n, int k) {
        // n!을 계산
        BigInteger numerator = factorial(n);
        // k!과 (n - k)!의 곱을 계산
        BigInteger denominator = factorial(k).multiply(factorial(n - k));
        // 이항계수는 n!을 k! * (n - k)!로 나눈 값
        return numerator.divide(denominator);
    }

    // 주어진 정수 x의 팩토리얼을 계산하는 메소드
    private static BigInteger factorial(int x) {
        // 팩토리얼 계산의 초기값을 1로 설정
        BigInteger result = BigInteger.ONE;
        // 2부터 x까지 반복하며 곱셈 수행
        for (int i = 2; i <= x; i++) {
            // 현재 결과값에 i를 곱함
            result = result.multiply(BigInteger.valueOf(i));
        }
        // 계산된 팩토리얼 값을 반환
        return result;
    }
}