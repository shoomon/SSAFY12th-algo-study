import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ12738_wooseok {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numElements = Integer.parseInt(reader.readLine());

        // 입력 배열 생성 및 값 할당
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int[] elements = new int[numElements];
        for (int i = 0; i < numElements; i++) {
            elements[i] = Integer.parseInt(tokenizer.nextToken());
        }

        // 이분 탐색을 이용한 최장 증가 부분 수열(LIS) 계산
        int[] lisArray = new int[numElements];
        int lisLength = 0;

        for (int i = 0; i < numElements; i++) {
            // LIS 배열에서 해당 값을 넣을 적절한 위치를 찾음
            int position = Arrays.binarySearch(lisArray, 0, lisLength, elements[i]);

            // 음수일 경우, 삽입해야 할 위치 계산
            if (position < 0) position = -(position + 1);

            // 해당 위치에 현재 값을 넣음
            lisArray[position] = elements[i];

            // 배열의 끝에 추가될 경우, LIS의 길이 증가
            if (position == lisLength) {
                lisLength++;
            }
        }

        System.out.println(lisLength);
    }
}
