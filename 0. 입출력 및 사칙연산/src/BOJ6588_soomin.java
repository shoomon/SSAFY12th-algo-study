import java.io.*;

public class BOJ6588_soomin {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n =-1, flag = 0;
        int[] prime = new int[1000001];

        prime[0] = 1;
        prime[1] = 1;

        for(int i = 2; i < 1001; i++){
            int tmp = i;
            while(tmp*i < 1000001){
                if(prime[tmp*i] == 0){
                    prime[tmp*i] = 1;
                }
                tmp++;
            }
        }

        while(n != 0){
            n = Integer.parseInt(br.readLine());
            flag = 0;
            for(int i = 2; i <= n/2; i++){
                if(prime[i] == 0 && prime[n-i] == 0){
                    System.out.println(n + " = " + i + " + " + (n-i));
                    flag = 1;
                    break;
                }
            }
            if(flag == 0 && n != 0){
                System.out.println("Goldbach's conjecture is wrong.");
            }
        }
    }
}
