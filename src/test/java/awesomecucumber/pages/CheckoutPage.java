package awesomecucumber.pages;

import awesomecucumber.domainObjects.BillingDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage extends BasePage {

    @FindBy(id="billing_first_name")
    WebElement billingFirstNameFld;

    @FindBy(id="billing_last_name")
    WebElement billingLastNameFld;
    @FindBy(id="billing_address_1")
    WebElement billingAddressOneFld;
    @FindBy(id="billing_city")
    WebElement biillingCityFld;
    @FindBy(id="billing_state")
    WebElement biillingStateFld;

    @FindBy(id = "select2-billing_state_container")
    WebElement alternateBillingStateDrpDwn;
    @FindBy(id="billing_postcode")
    WebElement billingZipFld;
    @FindBy(id="billing_email")
    WebElement billingEmailFld;

    @FindBy(id = "place_order")
    WebElement placeOrderBtn;

    @FindBy(css = ".woocommerce-notice")
    WebElement noticeText;

    @FindBy(css = ".blockUI.blockOverlay")
    WebElement overlay1;

    private final By overlay = By.cssSelector(".blockUI.blockOverlay");

        public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage enterLastName(String billingLastName){
            WebElement lastName = wait.until(ExpectedConditions.visibilityOf(billingLastNameFld));
            lastName.clear();
            lastName.sendKeys(billingLastName);
            return this;
    }

    public CheckoutPage enterAddresslineOne(String billingAddrLineName){
        WebElement adrLineOne = wait.until(ExpectedConditions.visibilityOf(billingAddressOneFld));
        adrLineOne.clear();
        adrLineOne.sendKeys(billingAddrLineName);
        return this;
    }

    public CheckoutPage enterCity(String billingCityName){
        WebElement cityName = wait.until(ExpectedConditions.visibilityOf(billingAddressOneFld));
        cityName.clear();
        cityName.sendKeys(billingCityName);
        return this;
    }

    public CheckoutPage enterFirstName(String billingFirstName){
        WebElement firstName = wait.until(ExpectedConditions.visibilityOf(billingFirstNameFld));
        firstName.clear();
        firstName.sendKeys(billingFirstName);
        return this;
    }

    public CheckoutPage enterStateName(String billingStateName){
//        WebElement stateName = wait.until(ExpectedConditions.visibilityOf(biillingStateFld));
//        Select select = new Select(stateName);
//        select.selectByVisibleText(billingStateName);

        wait.until(ExpectedConditions.elementToBeClickable(alternateBillingStateDrpDwn)).click();
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//li[text()='"+billingStateName+"']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",0);
        e.click();

        return this;
    }

    public CheckoutPage enterZipcode(String billingPostcode){
        WebElement postcode = wait.until(ExpectedConditions.visibilityOf(billingZipFld));
        postcode.clear();
        postcode.sendKeys(billingPostcode);
        return this;
    }

    public CheckoutPage enterEmailcode(String billingEmail){
        WebElement email = wait.until(ExpectedConditions.visibilityOf(billingEmailFld));
        email.clear();
        email.sendKeys(billingEmail);
        return this;
    }

//    public CheckoutPage setBillingDetails(String fname,String lname,String addrline,String city,String state,String zip,String email){
//
//            return enterFirstName(fname).enterLastName(lname).enterAddresslineOne(addrline)
//                    .enterCity(city).enterStateName(state).enterZipcode(zip).enterEmailcode(email);
//    }

    public CheckoutPage setBillingDetails(BillingDetails billingDetails){

        return enterFirstName(billingDetails.getFirstname()).
                enterLastName(billingDetails.getLastname()).
                enterAddresslineOne(billingDetails.getAddress_line1())
                .enterCity(billingDetails.getCity()).
                enterStateName(billingDetails.getState()).
                enterZipcode(billingDetails.getZip()).
                enterEmailcode(billingDetails.getEmail());
    }

    public CheckoutPage placeOrder(){
            waitForOverlaysToDisappear(overlay);
            wait.until(ExpectedConditions.elementToBeClickable(placeOrderBtn)).click();
            return this;
    }

    public String getNotice(){
            return wait.until(ExpectedConditions.visibilityOf(noticeText)).getText();
    }
}
