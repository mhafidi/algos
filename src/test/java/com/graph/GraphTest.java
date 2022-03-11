package com.graph;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.utils.ReadFile;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class GraphTest {
    @Test
    public void testReadGraph() {
        String json=new ReadFile("src/main/resources/graph_PANCAKESWAPV2.json").getContent();
        Graph graph= new Graph(json);

        System.out.println("Edges: "+graph.getEdges().size());
        System.out.println("Vertices: "+graph.getVertices().size());
        assertNotNull(graph.getEdges());
        assertNotNull(graph.getVertices());
    }
    @Test
    public void testComputeCycles()
    {
        String json=new ReadFile("src/main/resources/graph_PANCAKESWAPV2_extended.json").getContent();
        Graph graph= new Graph(json);
        HashMap hashMap=graph.getCycles();
        //System.out.println(hashMap);
        List list= (List) hashMap.keySet().stream().filter(key->((List) hashMap.get(key)).size()>2).map(key->hashMap.get(key)).collect(Collectors.toList());
        System.out.println(list.size());
    }

    //[["1","2"],["2","3"],["3","4"],["4","6"],["4","7"],["5","6"],["3","5"],["7","8"],["6","10"],["5","9"],["10","11"],["11","12"],["11","13"],["12","13"]]
    @Test
    public void testComputeCyclesForSmallGraph()
    {
        String json="[[\"1\",\"2\"],[\"2\",\"3\"],[\"3\",\"4\"],[\"4\",\"6\"],[\"4\",\"7\"],[\"5\",\"6\"],[\"3\",\"5\"],[\"7\",\"8\"],[\"6\",\"10\"],[\"5\",\"9\"],[\"10\",\"11\"],[\"11\",\"12\"],[\"11\",\"13\"],[\"12\",\"13\"]]";
        Graph graph= new Graph(json);
        HashMap hashMap=graph.getCycles();
        assertEquals(hashMap.size(),2);
        System.out.println(hashMap);
    }
}
