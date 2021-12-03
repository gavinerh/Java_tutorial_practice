package Array_questions;

public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        int width = height.length - 1;
        int leftIndex = 0;
        int rightIndex = height.length - 1;
        int max = 0;
        while(width > 0){
            int minHeightIndex = -1;
            if(height[leftIndex] <= height[rightIndex]){
                minHeightIndex = leftIndex;
                leftIndex += 1;
            }else{
                minHeightIndex = rightIndex;
                rightIndex -= 1;
            }
            int area = height[minHeightIndex] * width;
            if(area > max){
                max = area;
            }
            width -= 1;
        }
        return max;

    }
}
