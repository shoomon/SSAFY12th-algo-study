import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        // 각 시험장에 있는 응시자 수를 입력받아 배열로 저장
        String[] arr = br.readLine().split(" ");

        // 총감독관이 관리할 수 있는 응시자 수 B와 부감독관이 관리할 수 있는 응시자 수 C를 입력받음
        String[] bc = br.readLine().split(" ");
        int b = Integer.parseInt(bc[0]); // 총감독관이 한 시험장에서 관리할 수 있는 응시자 수
        int c = Integer.parseInt(bc[1]); // 부감독관이 한 시험장에서 관리할 수 있는 응시자 수

        // 필요한 감독관 수를 계산할 변수. 각 시험장에는 총감독관 1명이 반드시 필요하므로 처음에 N을 더함
        long answer = n;

        // 각 시험장별로 필요한 부감독관 수 계산
        for (int i = 0; i < n; i++) {
            // 각 시험장의 응시자 수에서 총감독관이 관리한 응시자 수(B)를 뺀 나머지 계산
            int remaining = Integer.parseInt(arr[i]) - b;
            
            // 남은 응시자가 있을 경우 부감독관이 추가로 필요함
            if (remaining > 0) {
                // 남은 응시자를 C명씩 관리하기 위해 필요한 부감독관 수를 더함
                // (remaining + c - 1) / c는 나머지 응시자들을 모두 부감독관이 관리할 수 있도록 계산하는 식
                answer += (remaining + c - 1) / c;
            }
        }

        // 최종적으로 필요한 감독관 수를 출력
        System.out.println(answer);
    }
}
