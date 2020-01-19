package bdd;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;


/**
 * Created by sriramangajala on 27/10/16.
 */
public class MyStepdefs {

    int responseCode;

    @Given("^passenger looks for his pnr with PNR \"([^\"]*)\" and last name \"([^\"]*)\"$")
    public void passenger_looks_for_his_pnr_with_PNR_and_last_name(String arg1, String arg2) throws Throwable {

        String url = "http://api.test.bpa.trainz.io/train-search/uk-en/7015400/8711184?outbound-date=2016-10-30&child=8&youth=1&booking-type=standard&raw-response=true";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }

    @Then("^he should get a response with response code (\\d+)$")
    public void he_should_get_a_response_with_response_code(int expResponseCode) throws Throwable {

        Assert.assertEquals(expResponseCode,responseCode);

    }

    @Given("^a request is made to aftersales service with PNR \"([^\"]*)\"$")
    public void aRequestIsMadeToAftersalesServiceWithPNR(String PNR) throws Throwable {

    }

    @Given("^with a CID value \"([^\"]*)\"$")
    public void withACIDValue(String cid) throws Throwable {

    }

    @Then("^aftersales service responds with below details$")
    public void aftersalesRespondsWithBelow() throws Throwable {

    }



}
