package com.easy;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestEasyAlgos
{

    @Test
    public void testMaximumSubArray()
    {

        int[] array={-2,1,-3,4,-1,2,1,-5,4};
        MaximumSubArray maximumSubArray= new MaximumSubArray();
        assertEquals(maximumSubArray.maxSubArray(array),6);

    }
}
