```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main5 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//0일 때까지 값 입력받기
		while(true) {
			int n = Integer.parseInt(br.readLine());
			if(n == 0)break;
			
			ArrayList<Integer> li = new ArrayList<>();
			
			//소수의 배열만들기
			for(int i = 2; i <= Math.sqrt(n); i+= 2) {
				if((i % 2 != 0 && i % 3 != 0 && i % 5 != 0) || i ==2 || i == 5 
						|| i == 3) {
					li.add(i);
				}
			//2개의 합으로 이루어지는지 탐색
				//에라토스 테네스의 체
				
		}
			for(int i = 0; i < li.size(); i++) {
				for(int j = 0; j < li.size(); j++) {
					//조건 설정 
					if(n == li.get(i) + li.get(j)) {
						ArrayList<Integer> li2 = new ArrayList<>();
						li2.add(li.get(i), li.get(j));
						
						//그 중 차이가 가장 큰 값을 출력
						//그냥 배열로 저장하면 둘의 값만 뽑기가 힘들다.어차피 b-a가 최대인 값은 최소값 최대값이므로
						//정렬 후 최소값 최대값을 출력한다. 
						 Collections.sort(li2);
						 int mi = li2.get(0);
						 int max = li2.get(li.size()-1);
						System.out.println(n + " = " mi + " + " + max) ;
					}else {//예외처리
						System.out.println("Goldbach's conjecture is wrong.");
					}
				}
			}
}
}
}
```
수정코드

public class Main5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) break;
            
            ArrayList<Integer> li = new ArrayList<>();
            for (int i = 2; i <= n; i++) {
                if (isPrime(i)) {
                    li.add(i);
                }
            }

            boolean found = false;
            for (int i = 0; i < li.size(); i++) {
                for (int j = i; j < li.size(); j++) {
                    if (n == li.get(i) + li.get(j)) {
                        System.out.println(n + " = " + li.get(i) + " + " + li.get(j));
                        found = true;
                        break;
                    }
                }
                if (found) break;
            }
            if (!found) {
                System.out.println("Goldbach's conjecture is wrong.");
            }
        }
    }
    
    public static boolean isPrime(int num) {
        if (num < 2) return false;
        if (num == 2 || num == 3 || num == 5) return true;
        if (num % 2 == 0 || num % 3 == 0 || num % 5 == 0) return false;
        for (int i = 7; i * i <= num; i += 2) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
