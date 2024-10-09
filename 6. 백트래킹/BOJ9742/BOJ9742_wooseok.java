//4번째 시도

package 시험준비.문제;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_순열 {
    static String str;  // 입력된 문자열
    static int idx;  // 찾고자 하는 순열의 인덱스
    static int len;  // 문자열의 길이
    static String[] arr;  // 문자열을 문자 배열로 저장
    static boolean[] check;  // 각 문자가 사용되었는지 확인하는 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;

        while ((input = br.readLine()) != null && !input.isEmpty()) {
            StringTokenizer st = new StringTokenizer(input);
            str = st.nextToken();  // 문자열 입력
            idx = Integer.parseInt(st.nextToken());  // 인덱스 입력

            len = str.length();  // 문자열의 길이 저장
            arr = new String[len];
            check = new boolean[len];

            arr = str.split("");  // 문자열을 개별 문자로 쪼개 배열에 저장
            Arrays.sort(arr);  // 사전순으로 정렬

            if (factorial(len) < idx) {  // 가능한 순열 개수보다 인덱스가 크다면
                System.out.println(str + " " + idx + " = No permutation");
            } else {
                findPermutation(0, idx, "");  // 순열을 찾는 메서드 호출
            }
        }
    }

    // 순열을 찾는 메서드
    public static void findPermutation(int depth, int k, String current) {
        if (depth == len) {  // 기저 조건: 현재 순열이 완성된 경우
            System.out.println(str + " " + idx + " = " + current);  // 결과 출력
            return;
        }

        int fact = factorial(len - depth - 1);  // 남은 자리에서 가능한 순열의 수

        for (int i = 0; i < len; i++) {
            if (!check[i]) {  // 해당 문자가 사용되지 않은 경우
                if (fact < k) {  // 현재 위치에서 가능한 순열의 수보다 k가 크면 다음 문자로 넘어감
                    k -= fact;
                } else {  // 그렇지 않으면 해당 문자를 선택
                    check[i] = true;
                    findPermutation(depth + 1, k, current + arr[i]);  // 다음 단계로 진행
                    return;  // 목표 순열을 찾으면 함수 종료
                }
            }
        }
    }

    // n! (팩토리얼)을 계산하는 메서드
    public static int factorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }
}


// 1차 시도
/* 첫시도
// 메모리 초과 코드

package 시험준비.문제;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_순열 {
    // 입력된 문자열과 인덱스를 저장할 변수들
    static String str;
    static int idx;

    // 문자열의 길이와 순열 생성을 위한 배열, 방문 체크 배열
    static int len;
    static String[] arr;
    static boolean[] check;

    // 현재까지 생성된 순열의 개수와 결과 저장을 위한 변수
    static int count;
    static String result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;

        // 입력이 주어지는 동안 반복문 실행
        while((input = br.readLine()) != null && !input.isEmpty()) {
            StringTokenizer st = new StringTokenizer(input);
            str = st.nextToken(); // 문자열 입력
            idx = Integer.parseInt(st.nextToken()); // 인덱스 입력

            len = str.length(); // 문자열의 길이 저장
            arr = new String[len]; // 문자열을 담을 배열 생성
            check = new boolean[len]; // 방문 체크를 위한 배열 생성
            count = 0; // 순열 카운트를 0으로 초기화
            result = "No permutation"; // 기본 결과 값 설정

            arr = str.split(""); // 문자열을 개별 문자로 쪼개 배열에 저장
            Arrays.sort(arr);  // 사전순으로 정렬 (순열을 사전순으로 생성하기 위해)

            permute(0, ""); // 백트래킹으로 순열 생성 시작

            // 결과 출력 (해당 인덱스의 순열이 있으면 출력, 없으면 "No permutation" 출력)
            System.out.println(str + " " + idx + " = " + result);
        }
    }

    // 순열을 생성하는 백트래킹 메서드
    public static void permute(int depth, String current) {
        // 기저 조건: 현재 생성된 순열의 길이가 입력 문자열의 길이와 같으면
        if (depth == len) {
            count++; // 순열 카운트 증가
            if (count == idx) { // 현재 순열이 목표 인덱스와 같으면
                result = current; // 결과를 저장
            }
            return; // 더 이상 진행하지 않고 돌아감
        }

        // 순열을 생성하기 위해 모든 문자를 순회
        for (int i = 0; i < len; i++) {
            if (!check[i]) { // 해당 문자가 사용되지 않은 경우
                check[i] = true; // 방문 체크
                permute(depth + 1, current + arr[i]); // 재귀적으로 다음 문자 선택
                check[i] = false; // 백트래킹: 다음 경우를 위해 방문 해제
            }
        }
    }
}
*/

// 2차 시도
/* 2번째 시도 - 틀림
package 시험준비.문제;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_순열 {
    // 입력된 문자열과 인덱스를 저장할 변수들
    static String str;
    static int idx;

    // 문자열의 길이와 순열 생성을 위한 배열, 방문 체크 배열
    static int len;
    static String[] arr;
    static boolean[] check;

    // 현재까지 생성된 순열의 개수
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;

        // 입력이 주어지는 동안 반복문 실행
        while((input = br.readLine()) != null && !input.isEmpty()) {
            StringTokenizer st = new StringTokenizer(input);
            str = st.nextToken(); // 문자열 입력
            idx = Integer.parseInt(st.nextToken()); // 인덱스 입력

            len = str.length(); // 문자열의 길이 저장
            arr = new String[len]; // 문자열을 담을 배열 생성
            check = new boolean[len]; // 방문 체크를 위한 배열 생성
            count = 0; // 순열 카운트를 0으로 초기화

            arr = str.split(""); // 문자열을 개별 문자로 쪼개 배열에 저장
            Arrays.sort(arr);  // 사전순으로 정렬 (순열을 사전순으로 생성하기 위해)

            if (factorial(len) < idx) { // 가능한 순열 개수보다 인덱스가 크다면
                // 해당 인덱스의 순열이 없음을 출력
                System.out.println(str + " " + idx + " = No permutation");
            } else {
                permute(0, ""); // 백트래킹으로 순열 생성 시작
            }
        }
    }

    // 순열을 생성하는 백트래킹 메서드
    public static void permute(int depth, String current) {
        if (depth == len) { // 기저 조건: 현재 생성된 순열의 길이가 입력 문자열의 길이와 같으면
            count++; // 순열 카운트 증가
            if (count == idx) { // 현재 순열이 목표 인덱스와 같으면
                System.out.println(str + " " + idx + " = " + current); // 결과 출력
                System.exit(0); // 프로그램 종료 (더 이상 순열을 생성하지 않음)
            }
            return; // 더 이상 진행하지 않고 돌아감
        }

        for (int i = 0; i < len; i++) {
            if (!check[i]) { // 해당 문자가 사용되지 않은 경우
                check[i] = true; // 방문 체크
                permute(depth + 1, current + arr[i]); // 재귀적으로 다음 문자 선택
                check[i] = false; // 백트래킹: 다음 경우를 위해 방문 해제
            }
        }
    }

    // n! (팩토리얼)을 계산하는 메서드
    public static int factorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }
}
*/

// 3차 시도
/* 3번쨰 시도 - 메모리 초과
package 시험준비.문제;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_순열 {
    // 입력된 문자열과 인덱스를 저장할 변수들
    static String str;
    static int idx;

    // 문자열의 길이와 순열 생성을 위한 배열, 방문 체크 배열
    static int len;
    static String[] arr;
    static boolean[] check;

    // 현재까지 생성된 순열의 개수
    static int count;
    static String result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;

        // 입력이 주어지는 동안 반복문 실행
        while((input = br.readLine()) != null && !input.isEmpty()) {
            StringTokenizer st = new StringTokenizer(input);
            str = st.nextToken(); // 문자열 입력
            idx = Integer.parseInt(st.nextToken()); // 인덱스 입력

            len = str.length(); // 문자열의 길이 저장
            arr = new String[len]; // 문자열을 담을 배열 생성
            check = new boolean[len]; // 방문 체크를 위한 배열 생성
            count = 0; // 순열 카운트를 0으로 초기화
            result = ""; // 결과 초기화

            arr = str.split(""); // 문자열을 개별 문자로 쪼개 배열에 저장
            Arrays.sort(arr);  // 사전순으로 정렬 (순열을 사전순으로 생성하기 위해)

            if (factorial(len) < idx) { // 가능한 순열 개수보다 인덱스가 크다면
                // 해당 인덱스의 순열이 없음을 출력
                System.out.println(str + " " + idx + " = No permutation");
            } else {
                permute(0, ""); // 백트래킹으로 순열 생성 시작
            }
        }
    }

    // 순열을 생성하는 백트래킹 메서드
    public static void permute(int depth, String current) {
        if (depth == len) { // 기저 조건: 현재 생성된 순열의 길이가 입력 문자열의 길이와 같으면
            count++; // 순열 카운트 증가
            if (count == idx) { // 현재 순열이 목표 인덱스와 같으면
                result = current; // 결과 저장
                System.out.println(str + " " + idx + " = " + result); // 결과 출력
                return; // 함수 종료
            }
            return; // 더 이상 진행하지 않고 돌아감
        }

        for (int i = 0; i < len; i++) {
            if (!check[i]) { // 해당 문자가 사용되지 않은 경우
                check[i] = true; // 방문 체크
                permute(depth + 1, current + arr[i]); // 재귀적으로 다음 문자 선택
                check[i] = false; // 백트래킹: 다음 경우를 위해 방문 해제
            }
        }
    }

    // n! (팩토리얼)을 계산하는 메서드
    public static int factorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }
}
*/
