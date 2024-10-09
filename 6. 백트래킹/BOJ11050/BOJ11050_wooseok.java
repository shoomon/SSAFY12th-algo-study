
// 1차 시도 _ 조합을 이용하여 조합의 수를 결과로 도출하는 코드
/*package 시험준비.문제;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_이항계수 {
    static int n, r, cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());  // n 입력
        r = Integer.parseInt(st.nextToken());  // r 입력

        cnt = 0;  // 조합의 수를 카운트할 변수 초기화

        // 조합 계산 시작
        comb(0, 0);

        // 결과 출력
        System.out.println(cnt);
    }

    // 조합을 계산하는 재귀 함수
    private static void comb(int depth, int start) {
        if (depth == r) {  // r개의 요소를 선택한 경우
            cnt++;  // 조합의 수 증가
            return;  // 종료
        }

        for (int i = start; i < n; i++) {
            comb(depth + 1, i + 1);  // 다음 요소 선택
        }
    }
}
*/

// 2차 시도 - 재귀적 계산: 이항계수의 일반적인 재귀 공식인 C(n,r)=C(n−1,r−1)+C(n−1,r)를 사용
/*
package 시험준비.문제;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_이항계수 {
    static int n, r;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());  // n 입력
        r = Integer.parseInt(st.nextToken());  // r 입력

        // 이항계수 계산 결과 출력
        System.out.println(binomialCoefficient(n, r));
    }

    // 이항계수를 계산하는 함수
    private static int binomialCoefficient(int n, int r) {
        // 기본 조건: r이 0이거나 r이 n과 같으면 1을 반환
        if (r == 0 || r == n) {
            return 1;
        }
        // 이항계수의 재귀적 계산 (동적 프로그래밍 적용 가능)
        return binomialCoefficient(n - 1, r - 1) + binomialCoefficient(n - 1, r);
    }
}
*/

//3차 시도 - 팩토리얼을 통해 조합의 공식 사용
//       - 해당 방식은 3가지 방식중 가장 효율적이다.
// 해당 방식에서는 숫자의 크기에 따라 오버플로우가 발생할 수 있는 가능성이 있다. 따라서, 숫자의 크기가 크다면 long 타입 또는 BigInteger를 사용하도록 한다.

package 시험준비.문제;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_이항계수 {
    static int n, r;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());  // n 입력
        r = Integer.parseInt(st.nextToken());  // r 입력

        // 이항계수 계산 결과 출력
        System.out.println(binomialCoefficient(n, r));
    }

    // 이항계수를 계산하는 함수
    private static int binomialCoefficient(int n, int r) {
        // 이항계수 계산 공식: C(n, r) = n! / (r! * (n - r)!)
        return factorial(n) / (factorial(r) * factorial(n - r));
    }

    // 팩토리얼을 계산하는 함수
    private static int factorial(int x) {
        int result = 1;
        for (int i = 1; i <= x; i++) {
            result *= i;
        }
        return result;
    }
}
