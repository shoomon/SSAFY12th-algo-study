import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        while (st.hasMoreTokens()){
            int n = Integer.parseInt(st.nextToken());
            if (n == 1){
                N--;
                continue;
            }
            for (int i =2; i < n; i++){
                if (n % i == 0){
                    N--;
                    break;
                }
            }
        }
        System.out.println(N);
    }
}
