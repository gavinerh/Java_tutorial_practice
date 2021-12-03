package Array_questions;

public class TrappingRainwater {
    public int trap_BruteForce(int[] height) {
        if(height.length < 2){
            return 0;
        }

        int totalVol = 0;
        for(int p=1; p<height.length; p++){
            int maxL = 0; int maxR = 0; int leftP = p;
            int rightP = p;
            while(leftP >= 0){
                maxL = Math.max(maxL, height[leftP]);
                leftP -= 1;
            }
            while(rightP <= height.length - 1){
                maxR = Math.max(maxR, height[rightP]);
                rightP += 1;
            }
            int vol = Math.min(maxL, maxR) - height[p];
            if (vol > 0){
                totalVol += vol;
            }
        }
        return totalVol;
    }

    public int trap_optimized(int[] height) {
        if(height.length < 2){
            return 0;
        }
        int totalVol = 0;
        int maxLeft = 0; int maxRight = 0;
        int left = 0; int right = height.length - 1;
        while(left != right){
            //need to check if current value is smaller or larger than max

            //decide to move only the smaller of 2 pointers
            if(height[left] < height[right]){
                //check with maxleft
                if(height[left] < maxLeft){
                    totalVol += maxLeft - height[left];
                }else{
                    maxLeft = Math.max(maxLeft, height[left]);
                }
                left++;
            }else{
                //check with maxright
                if(height[right] < maxRight){
                    totalVol += maxRight - height[right];
                }else{
                    maxRight = Math.max(maxRight, height[right]);
                }
                right--;
            }
        }
        return totalVol;
    }
}
