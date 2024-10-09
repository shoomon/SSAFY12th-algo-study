import java.io.*;

public class BOJ17425_soomin {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        long[] fX = new long[1000001], gX = new long[1000001];

        for(int i = 1; i < 1000001; i++){
            int tmp = 1;
            while(tmp*i < 1000001){
                fX[tmp*i] += i;
                tmp++;
            }
        }

        gX[1] = fX[1];
        for(int i = 2; i < 1000001; i++){
            gX[i] = gX[i-1] + fX[i];
        }

        for(int t = 0; t < T; t++){
            int N = Integer.parseInt(br.readLine());
            bw.write(gX[N]+"\n");
        }
        bw.close();
    }
}
