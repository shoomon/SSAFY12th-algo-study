
public class KAKAO_표현가능한이진트리_mingyung {
	// 무슨 소리인지 전혀 모르겠어서 찾아봄
	// https://uldada.tistory.com/12
	public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        
        for(int i=0; i<numbers.length; i++){
        	// 주어진 10진수를 이진수 문자열 형태로 변경
            StringBuilder sb = new StringBuilder();
            long num = numbers[i];
            while(num > 0){
                if(num % 2 == 1){
                	// 주어진 수를 2로 나누었을 때 1이 남을 경우, 이진수에 1 추가
                    sb.insert(0,"1");
                } else {
                	// 주어진 수를 2로 나누었을 때 나머지가 없는 경우 0 추가
                    sb.insert(0,"0");
                }
                // 2로 나눠주고 반복
                num /= 2;
            }
            
            // 완전 이진트리로 표현하기 위한 길이로 변경
            int n=1;
            int len = sb.length();
            int addcnt; // 추가해야 할 길이
            while(true){
                n *= 2;
                // 2^x-1 이 위에서 구한 이진수의 길이보다 크거나 같아질 때까지 n(2^x)을 두배로 증가
                if(n - 1 >= len){
                	// 구한 n값에 대해 n-1에서 이진수의 길이를 뺴주어, 추가해야 할 길이 구함
                    addcnt = n-1-len;
                    break;
                }
            }
            for(int j=0; j<addcnt; j++){
            	// 추가해야 할 길이만큼 0으로 채움
                sb.insert(0, "0");
            }

            // 완전 이진트리로 구현이 가능한지 확인
            int ans = sum(sb);
            if(ans == -1){
                answer[i] = 0;    
            } else {
                answer[i] = 1;
            }
        }
        return answer;
    }
    
	// 완전 이진트리로 구현이 가능한지 확인
    int sum(StringBuilder node){
        int len = node.length();
        if(len == 1){
        	// 자식노드가 없다면 해당 노드의 값 리턴
            return node.charAt(0) - '0';
        }
        // 중간값 기준 왼쪽/오른쪽 노드
        StringBuilder sbLeft = new StringBuilder(node.substring(0, len/2));
        StringBuilder sbRight = new StringBuilder(node.substring(len/2+1));
        
        // 왼쪽 노드 유효성 검사 (재귀)
        int left = sum(sbLeft);
        if(left == -1){
        	// 왼쪽 노드가 유효하지 않다면 -1 리턴
            return -1;
        }
        // 오른쪽 노드 유효성 검사 (재귀)
        int right = sum(sbRight);
        if(right == -1){
        	// 오른쪽 노드가 유효하지 않다면 -1 리턴
            return -1;
        }
        // 중간값을 int형으로 변경
        int mid = node.charAt(len/2) - '0';
        if(left + right > 0 && mid == 0){
            // 왼쪽, 오른쪽에 최소 1개의 노드가 존재하는데 중간값이 0인 경우 이진트리 불가능
        	return -1;
        } else if(left + right + mid == 0){
            // 모두 노드가 존재하지 않으면 이진트리로 표현 가능, 노드 없으므로 0 리턴
        	return 0;
        } else {
        	// 그 외, 이진트리로 표현 가능하며 노드 존재하기 때문에 1 리턴
            return 1;
        }
    }
}
