import java.util.*;

class Solution {
    int base1 = 53, base2 = 997;
    int mod1 = 1000000007, mod2 = 1000000009;

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        int left = 1, right = n;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (check(nums, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    boolean check(int[] nums, int len) {
        int n = nums.length;

        long power1 = 1, power2 = 1;
        for (int i = 0; i < len; i++) {
            power1 = power1 * base1 % mod1;
            power2 = power2 * base2 % mod2;
        }

        long hash1 = 0, hash2 = 0;

        // First window
        for (int i = 0; i < len; i++) {
            hash1 = (hash1 * base1 + nums[i]) % mod1;
            hash2 = (hash2 * base2 + nums[i]) % mod2;
        }

        HashMap<Long, Integer> map = new HashMap<>();

        long key = (hash1 << 32) | hash2;
        map.put(key, 1);

        // Slide the window
        for (int i = len; i < n; i++) {
            hash1 = (hash1 * base1 + nums[i]
                    - (power1 * nums[i - len]) % mod1 + mod1) % mod1;

            hash2 = (hash2 * base2 + nums[i]
                    - (power2 * nums[i - len]) % mod2 + mod2) % mod2;

            key = (hash1 << 32) | hash2;

            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        // Check if any subarray occurs exactly once
        for (int count : map.values()) {
            if (count == 1) {
                return true;
            }
        }

        return false;
    }
}
