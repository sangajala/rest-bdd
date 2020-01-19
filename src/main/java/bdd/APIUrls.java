package bdd;

/**
 * Created by sriramangajala on 02/12/16.
 */
public class APIUrls {

    public static String DOMAIN = "http://www.doctorsfriend.co.uk";
    public static String AppRegistration = DOMAIN+"/AppRegistration.ashx";
//    public static String AppRegistration = "/AppRegistration.ashx";

    public static String getAfterSalesApi()
    {
        String url = "http://ec2-52-213-142-201.eu-west-1.compute.amazonaws.com:8080";
        return url;
    }

}
