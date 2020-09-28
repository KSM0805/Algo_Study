package BOJ;

public class LC_11_ContainerWithMostWater {

	public static void main(String[] args) throws Exception {
		int[] height = {1,8,6,2,5,4,8,3,7};
		System.out.println(maxArea(height));
	}
	public static int maxArea(int[] height) {
	    int answer = 0;
	    int start = 0;
	    int end = height.length - 1;
	    for (int i = 0; i < height.length; i++) {
	        int w = (end-start) * (height[end] > height[start] ? height[start] : height[end]);
	        answer = Math.max(answer, w);
	        if(height[end] < height[start]) end--;
	        else start++;
	    }
	    return answer;
	}
}
