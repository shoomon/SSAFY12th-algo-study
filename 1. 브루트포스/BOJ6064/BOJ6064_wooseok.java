import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


class Main { // 카잉 달력

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int calCase = Integer.parseInt(br.readLine());

        

        for (int i = 0; i < calCase; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            boolean check = false;
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            for (int j = x; j < (n * m); j += m) {
                if (j % n == y) {
                    System.out.println(j + 1);
                    check = true;
                    break;
                }
            }

            if (!check) {
                System.out.println(-1);

            }


        }
    }
}