
public class DivineNumberCards {
	public static void main(String[] args) {
		Solution sol = new Solution();
		int[] arrayA = {10,17};
		int[] arrayB = {5,20};
		int ans = sol.solution(arrayA, arrayB);
		System.out.println(ans);
	}
	
	static public class Solution {
	    public int solution(int[] arrayA, int[] arrayB) {
	    	// 2배열의 최대 공약수가 나머지 배열 중에 하나라도 나눌 수 있는 것이 있다면 초기화.
	        int answer = 0;
	        
	        // 배열 A의 최대 공약수를 구하고, 해당 최대 공약수가 B에서 나눌 수 있는 것이 있는지 확인
	        int gcdA = getGcd(arrayA);
	        if (!isDividable(gcdA, arrayB))
	            answer = Math.max(answer, gcdA);
	        
	        // 배열 B의 최대 공약수를 구하고, 해당 최대 공약수가 A에서 나눌 수 있는 것이 있는지 확인
	        int gcdB = getGcd(arrayB);
	        if (!isDividable(gcdB, arrayA))
	            answer = Math.max(answer, gcdB);
	        
	        return answer;
	    }
	    
	    private int getGcd(int[] arr) {
	        int result = arr[0];
	        for (int i = 1; i < arr.length; i++) {
	            result = gcd(result, arr[i]);
	        }
	        return result;
	    }
	    
	    // 유클리드 호제법
	    private int gcd(int a, int b) {
	        while (b != 0) {
	            int tmp = b;
	            b = a % b;
	            a = tmp;
	        }
	        return a;
	    }
	    
	    private boolean isDividable(int gcd, int[] arr) {
	        for (int value : arr) {
	            if (value % gcd == 0)
	                return true;
	        }
	        return false;
	    }
	}
}
