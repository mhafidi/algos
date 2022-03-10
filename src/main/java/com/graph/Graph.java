package com.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Graph
{
   ArrayList<Edge> edges;
   HashSet<String> vertices;
   HashMap<String, List<String>> graph = new HashMap<>();
   HashMap<Integer,List<String>> cycles = new HashMap<>();
   int cyclenumber;


    public Graph(String json)
    {
        json=json.replace("null","");
        String stuff=json.substring(0,1);
        String stuffLast=json.substring(json.length()-2,json.length()-1);
        json=json.replace(stuff,"");
        json=json.replace(stuffLast,"");
        json=json.replace("\"","");
        String[] array=json.split(",");
        edges =new ArrayList<>();
        vertices= new HashSet<>();
        Edge edge;
        int index=0;
        while(index<array.length-1)
        {
            edge= new Edge(array[index],array[index+1]);
            addEdge(array[index],array[index+1]);
            vertices.add(array[index]);
            vertices.add(array[index+1]);
            edges.add(edge);
            index=index+2;
        }


    }
    void addEdge(String u, String v)
    {
        if(graph.containsKey(u))
        {
            List<String> list=graph.get(u);
            list.add(v);
            graph.replace(u,list);
        }
        else
        {
            List<String> list= new ArrayList<>();
            list.add(v);
            graph.put(u,list);

        }
        if(graph.containsKey(v))
        {
            List list=graph.get(v);
            list.add(u);
            graph.replace(v,list);
        }
        else
        {
            List<String> list= new ArrayList<>();
            list.add(u);
            graph.put(v,list);
        }

    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public HashSet<String> getVertices() {
        return vertices;
    }

    void dfs_cycle(String u, String p, HashMap<String,Integer> color,
                   HashMap<String,Integer> mark, HashMap<String,String>par)
    {

        // already (completely) visited vertex.
        if (color.get(u)!=null && color.get(u) == 2)
        {
            return;
        }

        // seen vertex, but was not completely visited -> cycle detected.
        // backtrack based on parents to find the complete cycle.
        if (color.get(u)!=null && color.get(u) == 1)
        {
            cyclenumber++;
            String cur = p;
            if(mark.containsKey(cur))
                mark.replace(cur, cyclenumber);
            else
                mark.put(cur,cyclenumber);
            // backtrack the vertex which are
            // in the current cycle that's found
            while (!cur.equals(u))
            {
                cur = par.get(cur);
                if(mark.containsKey(cur))
                    mark.replace(cur, cyclenumber);
                else
                    mark.put(cur,cyclenumber);
            }
            return;
        }
        if(par.containsKey(u))
            par.replace(u,p);
        else
            par.put(u,p);

        // partially visited.
        if(color.containsKey(u))
            color.replace(u,1);
        else
            color.put(u,1);

        // simple dfs on com.graph
        for (String v : graph.get(u))
        {

            // if it has not been visited previously
            if (v.equals(par.get(u)))
            {
                continue;
            }
            dfs_cycle(v, u, color, mark, par);
        }

        // completely visited.
        color.replace(u,2);
    }

    void collectCycles(HashMap<String,Integer>mark)
    {
        Integer cycleIndex;
        for(String key:mark.keySet())
        {
            cycleIndex=mark.get(key);
            if(cycles.containsKey(cycleIndex))
            {
                List<String> list=cycles.get(cycleIndex);
                list.add(key);
                cycles.replace(cycleIndex,list);

            }
            else
            {
                List<String> list= new ArrayList<>();
                list.add(key);
                cycles.put(cycleIndex,list);
            }
        }
    }

    void computeCycles()
    {
        HashMap<String,Integer> color = new HashMap<>();
        HashMap<String,String> par = new HashMap<>();

        HashMap<String,Integer> mark = new HashMap<>();

        cyclenumber = 0;

        dfs_cycle(vertices.stream().findAny().get(), "0", color, mark, par);
        collectCycles(mark);
    }
    public HashMap<Integer,List<String>> getCycles()
    {
        computeCycles();
        return cycles;
    }

}
