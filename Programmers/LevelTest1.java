package programmers;

import java.util.Arrays;

public class LevelTest1 {
	public static void main(String[] args) {
//		System.out.println(solution(new int[]{1,3,2,5,4}, 9));
		System.out.println(solution(new int[]{7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2}, "left"));
	}
	public static int solution(int[] d, int budget) {
        int max = 0;
        Arrays.sort(d);
        System.out.println(Arrays.toString(d));
        for (int i = 0; i < d.length; i++) {
			max += d[i];
		}
        int end = d.length - 1;
        while(max > budget) {
        	max -= d[end];
        	end--;
        }
        return end + 1;
    }
	public static String solution(int[] numbers, String hand) {
        StringBuilder answer = new StringBuilder();
        int right = 12;
        int left = 10;
        for (int i = 0; i < numbers.length; i++) {
			if(numbers[i] == 1 || numbers[i] == 4 || numbers[i] == 7) {
				left = numbers[i];
				answer.append("L");
			} else if(numbers[i] == 3 || numbers[i] == 6 || numbers[i] == 9) {
				right = numbers[i];
				answer.append("R");
			} else {
				if(numbers[i] == 0) numbers[i] = 11;
				int disR, disL;
				//세로길이
				disR = Math.abs(((numbers[i] - 1) % 3) - ((right - 1) % 3));
				disL = Math.abs(((numbers[i] - 1) % 3) - ((left - 1)% 3));
				//가로길이
				disR += Math.abs(((numbers[i] - 1) / 3) - ((right - 1) / 3));
				disL += Math.abs(((numbers[i] - 1) / 3) - ((left - 1) / 3));
				if(disR == disL) {
					if(hand.equals("right")) disL++;
					else disR++;
				}
				if(disR < disL) {
					right = numbers[i];
					answer.append("R");
				}else {
					left = numbers[i];
					answer.append("L");
				}
			}
		}
        return answer.toString();
    }
}
