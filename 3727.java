import java.util.Arrays;
class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n=nums.length;
        for(int i = 0;i<n;i++){
            nums[i] = nums[i]*nums[i];
        }
        Arrays.sort(nums);
        int mid = n/2;
        long ans = 0;
        for(int i =0;i<mid;i++){
            ans -= nums[i];
        }
        for(int i = mid;i<n;i++){
            ans += nums[i];
        }
        return ans;
    }
}
