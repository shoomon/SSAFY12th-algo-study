package codeplus_math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_4375_1 {
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String input;
		int n;
		long digit, res; // �ڷ��� ����ϱ�! long Ÿ�� �Ⱦ��� overflow �Ͼ
		while ((input=br.readLine())!=null) {
			try {
				n = Integer.parseInt(input);
				digit = 1; res = 1;
				while (res % n != 0) {
					res = (res *10 + 1) % n; //��� �ʰ��� ���� ���� ��ⷯ���� �ʿ�
					digit++;
				}
				System.out.println(digit);
			} catch (Exception e) {
				break;
				
			}
		}
		br.close();
		
	}

}
