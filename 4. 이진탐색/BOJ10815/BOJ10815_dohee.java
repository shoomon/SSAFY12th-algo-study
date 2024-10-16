package oct_3th;

import java.io.*;
import java.util.*;

// 40m
// 156468KB	1120ms
// 이진탐색 bound별 구현방법 공부!!

public class BOJ10815_dohee {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] nums = br.readLine().split(" ");
		int[] numbers = new int[N];
		for (int i=0;i<N;i++) {
			numbers[i] = Integer.parseInt(nums[i]);
		}
		Arrays.sort(numbers);
		
		int M = Integer.parseInt(br.readLine());
		String[] find = br.readLine().split(" ");
		
		StringBuilder sb = new StringBuilder();
		
		for (int i=0; i<find.length;i++) {
			boolean found = false;

			int left = 0;
			int right = N-1;
			int mid;
			
			int findVal = Integer.parseInt(find[i]);

			if(findVal == numbers[left] || findVal ==numbers[right]) {
				sb.append(1+" ");
				continue;
			}
			
			while (left+1<right) {
				mid = (left+right) / 2;
				
				if (findVal == numbers[mid] ) {
					sb.append(1+" ");
					found = true;
					break;
				} else if (findVal < numbers[mid]) {
					right = mid;
				} else {
					left = mid;
				}
			
			}
			if(!found) {
				sb.append(0+" ");
			}
		}
		
		System.out.println(sb.toString());
	}
}
