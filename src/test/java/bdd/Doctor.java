package bdd;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.jayway.restassured.response.Response;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.formatter.model.DataTableRow;
import org.junit.Assert;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.keystore;


/**
 * Created by sriramangajala on 02/12/16.
 */
public class Doctor {

    String url;
    String lastName;
    AfterSalesRequest afterSalesRequest;
    ResponseHolder responseHolder;
    Response afterSalesResponse;
    private String TOKEN;
    Map<String, Object> responseMap;


    @Given("^I hit the after sales apis$")
    public void I_hit_the_after_sales_apis() throws Throwable {


        System.out.println(APIUrls.getAfterSalesApi() + "/booking/GBZXA/" + afterSalesRequest.getPNR()+"/"+afterSalesRequest.getLastName()+"?locale=uk-en");

//        afterSalesResponse = given().headers("cid","test").queryParameters()
//                when().
//                get(APIUrls.getAfterSalesApi() + "/booking/GBZXA/" + afterSalesRequest.getPNR()+"/"+afterSalesRequest.getLastName()+"?locale=uk-en");


        ResponseHolder.setResponse(afterSalesResponse);

    }

    @When("^the PNR \"([^\"]*)\" and last name \"([^\"]*)\"$")
    public void the_PNR_test(String pnr, String lastName) throws Throwable {

        afterSalesRequest = new AfterSalesRequest(pnr, lastName, "GBZMB");

    }

    @Then("^the response code should be (\\d+)$")
    public void the_response_code_should_be(int responseCode) throws Throwable {
        Assert.assertEquals(responseCode,ResponseHolder.getResponseCode());
    }

    @And("^the PNR should match$")
    public void the_PNR_should_match() throws Throwable {
        Assert.assertTrue(ResponseHolder.getResponseBody().contains(afterSalesRequest.getPNR()));
    }

    @And("^the contact details should match as email \"([^\"]*)\" firstName \"([^\"]*)\" lastName \"([^\"]*)\" phoneNumber \"([^\"]*)\"$")
    public void the_contact_details_should_match(String email,String firstName,String lastName,String phoneNumber) throws Throwable {

        Assert.assertTrue(ResponseHolder.response.then().extract().path("contact").toString().contains("firstName="+firstName));
        Assert.assertTrue(ResponseHolder.response.then().extract().path("contact").toString().contains("phoneNumber="+phoneNumber));
        Assert.assertTrue(ResponseHolder.response.then().extract().path("contact").toString().contains("email="+email));
        Assert.assertTrue(ResponseHolder.response.then().extract().path("contact").toString().contains("phoneNumber="+phoneNumber));
    }


    @Given("^I take the \"([^\"]*)\"$")
    public void i_take_the(String url) throws Throwable {
        this.url = APIUrls.DOMAIN+url;
    }

    @When("^I register with following details$")
    public void i_register_with_following_details(DataTable dataTable) throws Throwable {
//
//        List<Map<String,String>> data = dataTable.asMaps(String.class,String.class);
//        System.out.print(data);
//        afterSalesResponse = given().queryParam()
//        when().
//                get(

        Map<String,String> query = new LinkedHashMap<String, String>();

        for (DataTableRow row : dataTable.getGherkinRows()) {
//            System.out.print(row.get);
            if(row.getCells().get(0).equalsIgnoreCase("token"))
            {
                query.put(row.getCells().get(0), TOKEN);
            }
            else
            {
                query.put(row.getCells().get(0), row.getCells().get(1));
            }


//                    Integer.parseInt(row.getCells().get(1)));
        }
        System.out.println("URL " + url+query);
        afterSalesResponse = given().queryParameters(query).when().get(url);
        ResponseHolder.setResponse(afterSalesResponse);


    }

//    @Then("^I should see json response with pairs$")
//    public void i_should_see_json_response_with_pairs(DataTable dataTable) throws Throwable {
//
//        Map<String,String> query = new LinkedHashMap<String, String>();
//
//        for (DataTableRow row : dataTable.getGherkinRows()) {
//            query.put(row.getCells().get(0), row.getCells().get(1));
//        }
//
//        ObjectReader reader = new ObjectMapper().reader(Map.class);
//
//        responseMap = reader.readValue(ResponseHolder.getResponseBody());
//        System.out.println(responseMap);
//        responseMap = (Map<String, Object>) responseMap.get("Msg");
//
//        for(String key:query.keySet())
//        {
//            Assert.assertTrue(responseMap.containsKey(key));
//            Assert.assertEquals(query.get(key),responseMap.get(key).toString());
//        }
//
//
//    }

    @Then("^I should see json response keys$")
    public void i_should_see_json_response_keys(DataTable dataTable) throws Throwable {
        Map<String,String> query = new LinkedHashMap<String, String>();

        for (DataTableRow row : dataTable.getGherkinRows()) {
//            System.out.print(row.get);
            if(row.getCells().get(0).equalsIgnoreCase("token"))
            {
                TOKEN = (String) responseMap.get(row.getCells().get(0));
            }
            query.put(row.getCells().get(0), row.getCells().get(1));

//                    Integer.parseInt(row.getCells().get(1)));
        }

        ObjectReader reader = new ObjectMapper().reader(Map.class);

        Map<String, Object> responseMap = reader.readValue(ResponseHolder.getResponseBody());
        System.out.println(responseMap);

        for(String key:query.keySet())
        {
            Assert.assertTrue(responseMap.containsKey(key));
        }
    }

    @And("^I should see json response with pairs contains$")
    public void I_should_see_json_response_with_pairs_contains(DataTable dataTable) throws Throwable {
        Map<String,String> query = new LinkedHashMap<String, String>();

        for (DataTableRow row : dataTable.getGherkinRows()) {
//            System.out.print(row.get);
            query.put(row.getCells().get(0), row.getCells().get(1));

//                    Integer.parseInt(row.getCells().get(1)));
        }

        ObjectReader reader = new ObjectMapper().reader(Map.class);

        Map<String, Object> responseMap = reader.readValue(ResponseHolder.getResponseBody());
        System.out.println(responseMap);

        for(String key:query.keySet())
        {
            Assert.assertTrue(responseMap.containsKey(key));
            Assert.assertTrue(responseMap.get(key).toString().contains(query.get(key)));

        }
    }
}
