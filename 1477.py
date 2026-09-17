class Solution(object):
    def minSumOfLengths(self, arr, target):
        n = len(arr)

        best = [float('inf')] * n

        left = 0
        total = 0
        answer = float('inf')

        for right in range(n):
            total += arr[right]

            while total > target:
                total -= arr[left]
                left += 1

            if total == target:
                length = right - left + 1

                # Previous non-overlapping subarray
                if left > 0 and best[left - 1] != float('inf'):
                    answer = min(answer, best[left - 1] + length)

                best[right] = length

            # Keep the shortest valid subarray seen so far
            if right > 0:
                best[right] = min(best[right], best[right - 1])

        if answer == float('inf'):
            return -1

        return answer
