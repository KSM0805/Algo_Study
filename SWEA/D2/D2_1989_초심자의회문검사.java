package swea.d2;

import java.util.Scanner;

public class D2_1989 {
	public static void main(String[] args) { 
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		sc.nextLine();
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			String str = sc.nextLine();
			int size = str.length();
			boolean pal = true;
			for (int i = 0; i < size; i++) {
				if(str.charAt(i) != str.charAt(size-1-i)){
					pal = false;
				}
			}
			if(pal) System.out.println("#"+test_case+" 1");
			else System.out.println("#"+test_case+" 0");
		}//for end
	}

}
