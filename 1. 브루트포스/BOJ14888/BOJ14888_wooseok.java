import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class BOJ14888_wooseok {

    public static int maxValue = Integer.MIN_VALUE;	// 최댓값 (Integer의 가장 최솟값을 넣어줌)
    public static int minValue = Integer.MAX_VALUE;	// 최솟값 (Integer의 가장 최댓값을 넣어줌)
    public static int[] operator = new int[4];	    // 연산자 개수 [더하기, 빼기, 곱하기, 나누기]의 순으로 각각의 개수 들어감
    public static int[] number;					    // 숫자 => 연산자와 연결할 숫자 배열
    public static int N;						    // 숫자 개수

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine()); //숫자의 개수
        number = new int[N]; //숫자 넣을 배열 초기화

        // 숫자 입력
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            number[i] = Integer.parseInt(st.nextToken());
        }

        // 연산자 입력
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 4; i++) { //더하기, 빼기, 곱하기, 나누기 연산자가 존재하므로 총 4개
            operator[i] = Integer.parseInt(st.nextToken());
        }
        br.close();

        solution(number[0], 1);

        System.out.println(maxValue);
        System.out.println(minValue);

    }

    // 최솟값 최댓값을 찾아서 변수에 저장하는 백트래킹 솔루션
    public static void solution(int num, int index) {

        if (index == N) {
            maxValue = Math.max(maxValue, num);
            minValue = Math.min(minValue, num);
            return;
        }

        for (int i = 0; i < 4; i++) {
            // 연산자 개수가 1개 이상인 경우
            if (operator[i] > 0) {

                // 해당 연산자를 1 감소시킨다.
                operator[i]--;

                switch (i) {

                    case 0: //더하기일 경우
                        solution(num + number[index], index + 1);
                        break;
                    case 1: //빼기일 경우
                        solution(num - number[index], index + 1);
                        break;
                    case 2: //곱하기일 경우
                        solution(num * number[index], index + 1);
                        break;
                    case 3: //나누기일 경우
                        solution(num / number[index], index + 1);
                        break;

                }
                // 재귀호출이 종료되면 다시 해당 연산자 개수를 복구한다.
                operator[i]++;
            }
        }
    }

}