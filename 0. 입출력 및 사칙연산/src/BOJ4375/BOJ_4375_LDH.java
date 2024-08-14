import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class BOJ_4375_LDH {
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> li = new ArrayList<>();

        int i = 1;
        while(true){
            String input = br.readLine();
            if(input == null || input.isEmpty())break;

            int n = Integer.parseInt(input);

            String k = Integer.toString(n*i);
            li.add(k);
            i++;

            int arr[] = new int[14];
            int cnt = 0;
            for(int j = 0; j < li.size(); j++){
                for(int u = 0; u<li.get(j).length(); u++){

                }
            }
            
        }

    }
}
