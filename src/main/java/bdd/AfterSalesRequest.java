package bdd;

/**
 * Created by sriramangajala on 02/12/16.
 */
public class AfterSalesRequest {

    public AfterSalesRequest(String PNR,String lastName, String market) {
        this.lastName = lastName;
        this.market = market;
        this.PNR = PNR;
    }

    public String PNR;
    public String lastName;
    public String market;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getPNR() {
        return PNR;
    }

    public void setPNR(String PNR) {
        this.PNR = PNR;
    }


}
