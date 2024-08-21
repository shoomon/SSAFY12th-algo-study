import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> seven = new ArrayList<>();

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            int num = Integer.parseInt(br.readLine());
            sum += num;
            seven.add(num);
        }

        boolean found = false;
        for (int i = 0; i < 8 && !found; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (sum - seven.get(i) - seven.get(j) == 100) {
                    seven.remove(j); // 더 큰 인덱스를 먼저 제거
                    seven.remove(i); // 작은 인덱스를 나중에 제거
                    found = true;
                    break;
                }
            }
        }

        Collections.sort(seven);
        for (int i = 0; i < seven.size(); i++) {
            System.out.println(seven.get(i));
        }
    }
}
