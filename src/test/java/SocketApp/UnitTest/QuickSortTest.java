package SocketApp.UnitTest;
import SocketApp.QuickSort;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class QuickSortTest {
    private ArrayList<Integer> arr = new ArrayList<>();
    private ArrayList<String> dict = new ArrayList<>();
    @Before
    public void init(){
        arr.add(1387);
        arr.add(120);
        arr.add(1);
        arr.add(1011);
        dict.add("dog");
        dict.add("chicken");
        dict.add("parrot");
        dict.add("cat");
    }

    @After
    public void cleanUp(){
        arr.clear();
        dict.clear();
    }

    @Test
    public void quickSortTest(){
        QuickSort q = new QuickSort();
        q.quickSort(arr,dict,0, arr.size() - 1);
        Assert.assertEquals(arr.size(),dict.size());
        if(arr.size() > 1) {
            for (int i = 1; i < arr.size(); i++)
                Assert.assertTrue(arr.get(i) < arr.get(i - 1));
        }
    }

}
