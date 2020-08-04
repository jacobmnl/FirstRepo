package DenoProject;

import org.testng.Assert;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class arrayList {

    public static void main(String[] args){

        String[] justanarray = {"bangalore","delhi","cochin"};
        String[] passingarray = {"bangalore","delhi","cochin"};

        ArrayList <String> justanarraylist = new ArrayList <String>();

        for(int i=0;i<3;i++)
        {
            justanarraylist.add(passingarray[i]);
        }
        List<String> justanarraynew = Arrays.asList(justanarray);
        System.out.println("Array List Is " + justanarraylist );
        Assert.assertTrue(justanarraylist.equals(justanarraynew));

    }
}
