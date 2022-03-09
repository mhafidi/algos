package com.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/*
You are given an integer array nums. You want to maximize the number of points you get by performing the following operation any number of times:

Pick any nums[i] and delete it to earn nums[i] points. Afterwards, you must delete every element equal to nums[i] - 1 and every element equal to nums[i] + 1.
Return the maximum number of points you can earn by applying the above operation some number of times.

 Example 1:

Input: nums = [3,4,2]
Output: 6
Explanation: You can perform the following operations:
- Delete 4 to earn 4 points. Consequently, 3 is also deleted. nums = [2].
- Delete 2 to earn 2 points. nums = [].
You earn a total of 6 points.
Example 2:

Input: nums = [2,2,3,3,3,4]
Output: 9
Explanation: You can perform the following operations:
- Delete a 3 to earn 3 points. All 2's and 4's are also deleted. nums = [3,3].
- Delete a 3 again to earn 3 points. nums = [3].
- Delete a 3 once more to earn 3 points. nums = [].
You earn a total of 9 points.


Constraints:

1 <= nums.length <= 2 * 104
1 <= nums[i] <= 104
 */
public class DeleteAndEarn {
    class Edge {
        int sVertex;
        int tVertex;
        int w;
        public Edge(int s,int t,int w)
        {
            this.sVertex=s;
            this.tVertex=t;
            this.w=w;
        }
    }
    class Graph {
        List<Edge> edges;
        int numberOfV;

        Graph(List<Edge> edges, int vert) {
            this.edges = edges;
            this.numberOfV = vert;

        }

        int longestPath(int from) {
            int e = edges.size();
            int[] dist = new int[numberOfV];
            for (int i = 0; i < numberOfV; i++) {
                dist[i] = -Integer.MAX_VALUE;
            }
            dist[from] = 0;
            numberOfV=1;


            for (int j = 0; j < e; j++) {
                int s = edges.get(j).sVertex;
                int t = edges.get(j).tVertex;
                int w = edges.get(j).w;
                if (dist[s] != -Integer.MAX_VALUE && dist[s] + w > dist[t])
                    dist[t] = dist[s] + w;

            }

            int value=0;
            for(int i=0;i<dist.length;i++)
            {
                if(dist[i]>value)
                    value=dist[i];
            }
            return value;

        }
    }
    public int deleteAndEarn_sol_leetcode(int[] nums) {
        int[] count = new int[10001];
        for (int x: nums) count[x]++;
        int avoid = 0, using = 0, prev = -1;

        for (int k = 0; k <= 10000; ++k) if (count[k] > 0) {
            int m = Math.max(avoid, using);
            if (k - 1 != prev) {
                using = k * count[k] + m;
                avoid = m;
            } else {
                using = k * count[k] + avoid;
                avoid = m;
            }
            prev = k;
        }
        return Math.max(avoid, using);
    }

    int deleteAndEarn(int[]nums)
    {
        TreeMap<Integer,Integer> uniqueNumAndTimes= new TreeMap<>();

        for (int num : nums) {
            if (uniqueNumAndTimes.containsKey(num)) {
                uniqueNumAndTimes.replace(num, uniqueNumAndTimes.get(num) + num);
            } else
                uniqueNumAndTimes.put(num, num);
        }


        ArrayList<Integer> fixedElt= new ArrayList<>(uniqueNumAndTimes.keySet());
        int numVert=fixedElt.size()+1;


        List<Edge> edges= new ArrayList<>();
        for(int i=1;i<fixedElt.size()+1;i++)
        {
            edges.add (new Edge(0,i,uniqueNumAndTimes.get(fixedElt.get(i-1))));
        }


        for(Integer key:uniqueNumAndTimes.keySet())
        {
            Integer value=key,value1=key+1,value2=key-1;
            int index=fixedElt.indexOf(value);

            if(index<fixedElt.indexOf(value1))
                index=fixedElt.indexOf(value1)+1;
            else
                index++;
            for(int i=index;i<fixedElt.size();i++)
            {
                edges.add(new Edge(fixedElt.indexOf(value)+1,i+1,uniqueNumAndTimes.get(fixedElt.get(i))));
            }
        }


        Graph graph= new Graph(edges,numVert);
        return  graph.longestPath(0);

    }
}
