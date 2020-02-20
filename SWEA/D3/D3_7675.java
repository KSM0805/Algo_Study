package swea.d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class D3_7675 {
		private static int N;
		private static String[] str;
		private static String[] stand = {".","?","!"};
		private static int[] cnt;
		
		public static void main(String[] args) throws NumberFormatException, IOException {
			System.setIn(new FileInputStream("input.txt"));
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			int T = Integer.parseInt(bf.readLine().trim());
			
			for (int test_case = 1; test_case <= T; test_case++) {
				N = Integer.parseInt(bf.readLine().trim());
				str = new String[N];
				cnt = new int[N];
				StringBuilder sb = new StringBuilder(bf.readLine().trim());
				int pos = 0;
				int arpos = 0;
				while (sb.length()>0) {
					int min =Integer.MAX_VALUE;
					for (int i = 0; i < 3; i++) {
						pos = sb.indexOf(stand[i]);
						if (pos != -1) {
							min = Math.min(min, pos);
						}
					}
					if(min != -1) {
						str[arpos] = sb.substring(0, min+1).trim();
						arpos++;
						sb.delete(0, min+1);
					}
				} // 문장별로 나누기
				StringTokenizer st;
				LinkedList<String> words = new LinkedList<>();
				for (int i = 0; i < N; i++) {
					words.clear();
					st = new StringTokenizer(str[i]," ");
					while(st.hasMoreTokens()) {
						words.add(st.nextToken());
					}
					while(!words.isEmpty()) {
						String word = words.poll();
						int len = word.length();
						if((int)word.charAt(0) > 64 && (int)word.charAt(0) < 91 ) {
							cnt[i]++;
							for (int j = 1; j <len; j++) {
								int ch = word.charAt(j);
								if(j==len-1 && (ch==(int)'!' || ch==(int)'?' || ch==(int)'.' )) break;
								if(ch<97 || ch>122) {
									cnt[i]--;
									break;
								}
							}
						}
						
					}
				}
				
				System.out.print("#"+test_case+" ");
				for (int num : cnt) {
					System.out.print(num+" ");
				}
				System.out.println();
			}//for end
		}

}
