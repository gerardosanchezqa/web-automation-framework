package otherJavaCodeTests;

import java.util.ArrayList;

public class ArrayListSandbox{

    public static void main(String args[]) {

        ArrayList testArrayList = new ArrayList();

        System.out.println("Size of ArrayList: "+testArrayList.size());

        testArrayList.add("A");
        testArrayList.add("B");
        testArrayList.add("C");
        testArrayList.add("D");

        System.out.println("Size of ArrayList after adding elements: "+testArrayList.size());

        System.out.println("List all contents of the ArrayList: "+ testArrayList);

        testArrayList.remove("C");

        System.out.println("Size of ArrayList after removing one element: "+testArrayList.size());

        testArrayList.remove(2);

        System.out.println("Size of ArrayList after removing an element by index: "+testArrayList.size());

        System.out.println("Does the ArrayList contain an A? "+testArrayList.contains("A"));
    }
}


