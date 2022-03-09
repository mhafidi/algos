package com.mock;

import java.util.ArrayList;
import java.util.List;

public class Exo1
{
    public static void main(String args[])
    {

        int[] nums={2,4,5,3,1,7,8,9,11,12,19,22};
        int n=20;
        List list= getSums(nums,n);
        printMatrix(list);
        System.out.println(list.size());
        System.out.println(nums.length);
    }

    /// [2,4,5,3,1,7,8,9,11,12,19,22]    ---->

    static List<List<Integer>> getSums(int[]nums, int n)
    {
        List<List<Integer>> matrix= new ArrayList<>();
        List<Integer> list;
        for(int i=0;i<nums.length;i++)
        {
            for(int j=i+1;j<nums.length;j++)
            {
                for(int k=j+1;k<nums.length;k++)
                {
                    for(int l=k+1;l<nums.length;l++)
                    {
                        if(nums[i]+nums[j]+nums[k]+nums[l]==n)
                        {
                            list= new ArrayList<>();
                            list.add(nums[i]);list.add(nums[j]);list.add(nums[k]);list.add(nums[l]);
                            matrix.add(list);
                        }
                    }
                }
            }
        }
        return matrix;
    }


    static void printMatrix(List<List<Integer>> matrix)
    {
        for(List list:matrix)
        {
            for(int i=0;i<list.size();i++)
            {
                System.out.printf(list.get(i)+" ");
            }
            System.out.println();
        }
    }

}
