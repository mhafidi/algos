package com.easy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MaximumSubArray {

    class LastAndMax {
        int last, max;

        public LastAndMax(int last, int max) {
            this.last = last;
            this.max = max;
        }
    }
    public int maxSubArrayRecursive(int[]nums)
    {
        int maxIntValue=-Integer.MAX_VALUE;

        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
       maxIntValue= maxSubArrayValue(list,maxIntValue);

        return maxIntValue;
    }

    private int maxSubArrayValue(List<Integer> nums, int maxIntValue) {
        if(nums.size()==0)
            return maxIntValue;
        else
        {
            int last=0;
            int max=-Integer.MAX_VALUE;
            for(int i=0;i<nums.size();i++)
            {
                last=last+nums.get(i);
                if(last>max)
                    max=last;
            }
            if(max>maxIntValue)
             maxIntValue=max;
            nums.remove(0);
            return maxSubArrayValue(nums, maxIntValue);
        }
    }

    public int maxSubArrayKadaneAlgorithm(int[]nums)
    {//-2,1,-3,4,-1,2,1,-5,4
      //p -2 1 -2 4  3 5 6
      //c -2 1 -3 4 -1 2 1
        int p;int c;
        p=nums[0];
        int max=p;
        for(int i=1;i<nums.length;i++)
        {
            c=nums[i];
            if(c+p<c)
                p=c;
            else
                p=c+p;
            if(p>max)
                max=p;
        }
        return max;
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
