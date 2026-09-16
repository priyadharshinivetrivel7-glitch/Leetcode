lass Solution(object):
    def numberOfSets(self, n, k):
        """
        :type n: int
        :type k: int
        :rtype: int
        """
        MOD = 10**9 + 7
        a = n + k  - 1
        b = 2 * k
        numerator = 1
        denominator = 1

        for i in range(1, b + 1):
            numerator = numerator * (a - i + 1) % MOD
            denominator = denominator * i % MOD
        result = numerator * pow(denominator,MOD - 2, MOD) % MOD
        return result
