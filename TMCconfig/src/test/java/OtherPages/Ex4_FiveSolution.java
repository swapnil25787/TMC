package OtherPages;

import java.util.ArrayList;

public class Ex4_FiveSolution
{
 
    public static void main(String[] args) 
    {
        // TODO Auto-generated method stub
        int n=612,n1=0;
        int[] a=null;
        ArrayList<Integer>list=new ArrayList<Integer>();
        while(n!=0)
        {
            n1=n%1000;
            list.add(n1);
            n/=1000;
        }
        System.out.println(list);
        list.add(0,5);
        System.out.println(list); 
        for(Integer i:list)
            System.out.print(i);
    }
 
}