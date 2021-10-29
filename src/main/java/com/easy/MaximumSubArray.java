package com.easy;

public class MaximumSubArray {

    class LastAndMax {
        int last, max;

        public LastAndMax(int last, int max) {
            this.last = last;
            this.max = max;
        }
    }

    public int maxSubArray(int[] nums) {

        LastAndMax[] lastAndMaxes = new LastAndMax[nums.length];
        LastAndMax newLastAndMax;
        LastAndMax lastAndMax;
        int maxValue;
        //initialization
        for (int i = 0; i < nums.length; i++) {
            lastAndMaxes[i] = new LastAndMax(nums[i], nums[i]);

        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                lastAndMax = lastAndMaxes[i];
                newLastAndMax = new LastAndMax(lastAndMax.last + nums[j], lastAndMax.max);
                if (newLastAndMax.last > newLastAndMax.max) {
                    newLastAndMax.max = newLastAndMax.last;
                }

                lastAndMaxes[i] = newLastAndMax;
            }

        }
        maxValue = lastAndMaxes[0].max;
        for (int i = 1; i < lastAndMaxes.length; i++) {
            if (lastAndMaxes[i].max > maxValue)
                maxValue = lastAndMaxes[i].max;
        }
        return maxValue;

    }
}
