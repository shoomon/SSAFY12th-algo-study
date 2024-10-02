package 시험준비.문제;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_크면서작은수 {
    // 전역 변수 선언
    static int N, num, min = Integer.MAX_VALUE; // N: 자릿수 개수, num: 입력값, min: 입력값보다 큰 수 중 최소값을 저장
    static int[] arr, list; // arr: 입력 숫자의 각 자릿수를 저장하는 배열, list: 순열을 저장하는 배열
    static boolean[] visited; // visited: 각 자릿수를 사용했는지 여부를 확인하는 배열

    public static void main(String[] args) throws IOException {
        // 입력을 받기 위한 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력값을 문자열로 받아옴
        String s = br.readLine();

        // 입력값을 정수형으로 변환하여 num에 저장
        num = Integer.parseInt(s);

        // 입력값의 자릿수 개수 계산
        N = s.split("").length;

        // arr 배열 초기화: 입력값의 각 자릿수를 저장
        arr = new int[N];

        // list 배열 초기화: 순열을 저장할 배열
        list = new int[N];

        // visited 배열 초기화: 각 자릿수 사용 여부 확인
        visited = new boolean[N];

        // 입력값의 각 자릿수를 arr 배열에 저장
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(s.split("")[i]);
        }

        // 백트래킹 함수 호출, 깊이 0부터 시작
        BT(0);

        // 결과 출력: 입력값보다 큰 수가 없다면 0 출력, 있으면 min 출력
        System.out.println(min == Integer.MAX_VALUE ? 0 : min);
    }

    // 백트래킹 함수: 순열 생성 및 조건에 맞는 최소값 찾기
    public static void BT(int depth) {
        // 종료 조건: depth가 N과 같아지면 순열 완성
        if (depth == N) {
            // 현재 순열을 문자열로 변환
            String s = "";
            for (int i: list) {
                s += i;
            }

            // 문자열을 정수로 변환
            int n = Integer.parseInt(s);

            // 입력값보다 큰 수 중에서 최솟값 갱신
            if (num < n) {
                min = Math.min(min, n);
            }
            return;
        }

        // 순열 생성: 방문하지 않은 자릿수를 선택
        for (int i = 0; i < N; i++) {
            if (!visited[i]) { // 현재 자릿수를 아직 사용하지 않았다면
                visited[i] = true; // 사용 표시
                list[depth] = arr[i]; // 현재 자릿수를 list에 저장
                BT(depth + 1); // 다음 자릿수로 이동
                visited[i] = false; // 사용 표시 해제 (백트래킹)
            }
        }
    }
}
