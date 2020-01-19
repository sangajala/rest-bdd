package bdd;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.formatter.model.DataTableRow;
import org.junit.Assert;

import javax.xml.crypto.Data;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by sriramangajala on 09/01/17.
 */
public class CityMovies {

    String url;
    String lastName;
    ResponseHolder responseHolder;
    Response response;
    private String TOKEN;
    Map<String, Object> responseMap;
    Map<String, String> query;
    Map<String, String> body;


    @Given("^the apis are up and running for \"([^\"]*)\"$")
    public void the_apis_are_up_and_running_for(String url) throws Throwable {
        this.url = url;
//        response = given().when().get(url);
//        Assert.assertEquals(200, response.getStatusCode());
    }


    @When("^a user posts a request to \"([^\"]*)\"$")
    public void a_user_posts_a_request_to(String api_url) throws Throwable {
        if(api_url==null)
            api_url="";
        this.url = this.url + api_url;
    }

    @When("^flowing body parameters$")
    public void flowing_body_parameters(DataTable dataTable) throws Throwable {
        Map<String, String> body = new LinkedHashMap<String, String>();
        for (DataTableRow row : dataTable.getGherkinRows()) {
            body.put(row.getCells().get(0), row.getCells().get(1));
        }
        System.out.print(body);
        response = given().contentType(ContentType.JSON).body(body).when().post(this.url);
        ResponseHolder.setResponse(response);
        System.out.println(ResponseHolder.getResponseBody());
    }

    @When("^a user performs a get request to \"([^\"]*)\"$")
    public void a_user_performs_a_get_request_to(String url) throws Throwable {

        this.url = this.url + url;
    }

    @When("^I add the following filters$")
    public void I_add_the_following_filters(DataTable dataTable) throws Throwable {
//
         query = new LinkedHashMap<String, String>();

        for (DataTableRow row : dataTable.getGherkinRows()) {
//
            query.put(row.getCells().get(0), row.getCells().get(1));

        }


    }

    @Then("^I should see json response with pairs$")
    public void i_should_see_json_response_with_pairs(DataTable dataTable) throws Throwable {

        Map<String,String> query = new LinkedHashMap<String, String>();

        for (DataTableRow row : dataTable.getGherkinRows()) {
            query.put(row.getCells().get(0), row.getCells().get(1));
        }

        ObjectReader reader = new ObjectMapper().reader(Map.class);

        responseMap = reader.readValue(ResponseHolder.getResponseBody());
        System.out.println(responseMap);
        responseMap = (Map<String, Object>) responseMap.get("Msg");

        for(String key:query.keySet())
        {
            Assert.assertTrue(responseMap.containsKey(key));
            Assert.assertEquals(query.get(key),responseMap.get(key).toString());
        }


    }

    @And("^and perform the request$")
    public void and_perform_the_request() throws Throwable {
        System.out.println("URL " + this.url + query);
        if(query==null)
        {
            response = given().relaxedHTTPSValidation("TLS").header("apikey","qBv6dUFYLuM39o8srYvItp8nC7eLnxTL").when().get(this.url);
        }
        else
        {
            response = given().queryParams(query).header("apikey","qBv6dUFYLuM39o8srYvItp8nC7eLnxTL").when().get(this.url);
        }

        ResponseHolder.setResponse(response);
    }

    @And("^I should see json response with pairs on the filtered \"([^\"]*)\" node$")
    public void I_should_see_json_response_with_pairs_on_the_filtered_node(String filter, DataTable dataTable) throws Throwable {
        Map<String,String> query = new LinkedHashMap<String, String>();

        for (DataTableRow row : dataTable.getGherkinRows()) {
            query.put(row.getCells().get(0), row.getCells().get(1));
        }

        ObjectReader reader = new ObjectMapper().reader(Map.class);

        responseMap = reader.readValue(ResponseHolder.getResponseBody());
        System.out.println(responseMap);
        responseMap = (Map<String, Object>) responseMap.get(filter);

        for(String key:query.keySet())
        {
            Assert.assertTrue(responseMap.containsKey(key));
            Assert.assertEquals(query.get(key),responseMap.get(key).toString());
        }
    }

    @When("^a user performs a post request to \"([^\"]*)\" with below details$")
    public void a_user_performs_a_post_request_to_with_below_details(String url,DataTable dataTable) throws Throwable {

        this.url = this.url + url;
        this.body = new LinkedHashMap<String, String>();
        for (DataTableRow row : dataTable.getGherkinRows()) {
            this.body.put(row.getCells().get(0), row.getCells().get(1));
        }
    }

    @And("^and perform the post request$")
    public void and_perform_the_post_request() throws Throwable {
        response = given().contentType(ContentType.JSON).body(this.body).when().post(this.url);
        ResponseHolder.setResponse(response);
    }

    @And("^I print the response$")
    public void I_print_the_response() throws Throwable {
        System.out.println(ResponseHolder.getResponseBody());
    }
}

