import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
 
public class Main {
	
	public static int T, N, M;
	public static HashSet<Integer> hashset = new HashSet<Integer>();
	public static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	T = Integer.parseInt(st.nextToken());
    	
    	for(int t=0;t<T;t++) {
            sb = new StringBuilder();
    		
	    	st = new StringTokenizer(br.readLine());
	    	N = Integer.parseInt(st.nextToken());
	    	
	    	hashset = new HashSet<>();
	    	st = new StringTokenizer(br.readLine());
	    	for(int i=0;i<N;i++) {
	    		hashset.add(Integer.parseInt(st.nextToken()));
	    	}
	    	
    		st = new StringTokenizer(br.readLine());
        	M = Integer.parseInt(st.nextToken());
        	
        	st = new StringTokenizer(br.readLine());
        	for(int j=0;j<M;j++) {
        		boolean result = hashset.contains(Integer.parseInt(st.nextToken()));
        		sb.append(result ? 1 : 0).append("\n");
        	}
	        	
        	System.out.print(sb);
    	}
 
    	
    }
   
}
