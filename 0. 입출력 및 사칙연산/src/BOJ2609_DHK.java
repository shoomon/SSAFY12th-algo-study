package codeplus_math;

import java.util.Scanner;

public class BOJ_2609_최대공약수와최소공배수 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		
		int first = a; int quo = b;	
		int mod = first % quo;
		
		while (quo != 0) {
			mod = first % quo;
			first = quo;
			quo = mod; 
		};
		
		System.out.println(first);
		System.out.println((a * b)/first);
		
	}
}
