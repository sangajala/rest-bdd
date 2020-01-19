package bdd;

import java.util.Random;

/**
 * Created by sriramangajala on 23/04/17.
 */
public class EmailsAddress {

    public static void main(String[] args)
    {
        Random r = new Random();

        for(int i=0;i<=1000;i++)
        {
            System.out.println(Math.abs(r.nextInt())+"@gmail.com");
        }
    }
}
