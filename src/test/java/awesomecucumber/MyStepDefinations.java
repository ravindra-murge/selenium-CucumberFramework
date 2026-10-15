package awesomecucumber;

import awesomecucumber.constants.Endpoint;
import awesomecucumber.domainObjects.BillingDetails;
import awesomecucumber.domainObjects.Product;
import awesomecucumber.factory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import awesomecucumber.pages.CartPage;
import awesomecucumber.pages.CheckoutPage;
import awesomecucumber.pages.StorePage;

import java.util.List;
import java.util.Map;

public class MyStepDefinations {

    private WebDriver driver;
//    private String firstname;
//    private String lastname;
//    private String address_line1;
//    private String city;
//    private String state;
//    private String zip;
//    private String email;

    BillingDetails billingDetails;

    @Given("User is on the store page")
    public void user_is_on_the_store_page() {

        driver = DriverFactory.getDriver();
        StorePage storePage =  new StorePage(driver);
        storePage.load(Endpoint.STORE.url);
       // driver.get("https://askomdch.com/store");
    }

    @When("User add a {product} to the cart")
    public void user_add_a_to_the_cart(Product product) throws InterruptedException {

//        By addToCart = By.cssSelector("a[aria-label='Add “"+ productName+ "” to your cart']");
//        driver.findElement(addToCart).click();
       // By viewCartLink = By.cssSelector("a[title='View cart']");
       // Thread.sleep(5000);
       // driver.findElement(viewCartLink).click();
        StorePage storePage = new StorePage(driver);
        storePage.addToCart(product.getName());



    }
    @Then("User should see {int} {product} in the cart")
    public void user_should_see_in_the_cart(int quantity, Product product) {

//        By productNameFld = By.cssSelector("td[class='product-name'] a");
//        String actualProductName = driver.findElement(productNameFld).getText();
//        By productQuantityFld =  By.cssSelector("input[type='number'] ");
//        String actualQauntity =  driver.findElement(productQuantityFld).getAttribute("value");

        CartPage  cartPage = new CartPage(driver);
        String actualproductName = cartPage.getProductName();
       int actualquantity = cartPage.getQauntity();
        Assert.assertEquals(product.getName(),actualproductName);
        Assert.assertEquals(quantity,actualquantity);

    }

    @Given("User is a guest User")
    public void user_is_a_guest_user() {
      driver =  DriverFactory.getDriver();
      new StorePage(driver).load(Endpoint.STORE.url);
    }

    @Given("billing details are")
    public void billing_details_are(BillingDetails billingDetails) {

//        firstname = billingDetails.get(0).get("firstname");
//        lastname = billingDetails.get(0).get("lastname");
//        address_line1 = billingDetails.get(0).get("address_line1");
//        city = billingDetails.get(0).get("city");
//        state = billingDetails.get(0).get("state");
//        zip = billingDetails.get(0).get("zip");
//        email = billingDetails.get(0).get("email");

        this.billingDetails = billingDetails;

    }

    @Given("User has a product in the cart")
    public void user_has_a_product_in_the_cart() {
       new StorePage(driver).addToCart("Blue Shoes");
    }

    @Given("User on the Checkout page")
    public void user_on_the_checkout_page() {
//      By proceedToCheckoutBtn = By.cssSelector(".checkout-button");
//      driver.findElement(proceedToCheckoutBtn).click();
        CartPage cartPage = new CartPage(driver);
        cartPage.checkout();
    }

    @When("User provide billing details")
    public void user_provide_billing_details() {

//        By billingFirstNameFld = By.id("billing_first_name");
//        By billingLastNameFld = By.id("billing_last_name");
//        By billingAddressOneFld = By.id("billing_address_1");
//        By biillingCityFld = By.id("billing_city");
//        By billingStateFld = By.id("billing_state");
//        By billingZipFld = By.id("billing_postcode");
//        By billingEmailFld = By.id("billing_email");

//        driver.findElement(billingFirstNameFld).clear();
//        driver.findElement(billingFirstNameFld).sendKeys(billingDetails.get(0).get("firstname"));
//        driver.findElement(billingLastNameFld).clear();
//        driver.findElement(billingLastNameFld).sendKeys(billingDetails.get(0).get("lastname"));
//        driver.findElement(billingAddressOneFld).clear();
//        driver.findElement(billingAddressOneFld).sendKeys(billingDetails.get(0).get("address_line1"));
//        driver.findElement(biillinCityFld).clear();
//        driver.findElement(biillinCityFld).sendKeys(billingDetails.get(0).get("city"));
//        Select select = new Select(driver.findElement(billingStateFld));
//        select.selectByVisibleText(billingDetails.get(0).get("state"));
//        driver.findElement(billingZipFld).clear();
//        driver.findElement(billingZipFld).sendKeys(billingDetails.get(0).get("zip"));
//        driver.findElement(billingEmailFld).clear();
//        driver.findElement(billingEmailFld).sendKeys(billingDetails.get(0).get("email"));

        CheckoutPage checkoutPage = new CheckoutPage(driver);
//        checkoutPage.setBillingDetails(billingDetails.get(0).get("firstname"),
//                billingDetails.get(0).get("lastname"),
//                billingDetails.get(0).get("address_line1"),
//                billingDetails.get(0).get("city"),
//                billingDetails.get(0).get("state"),
//                billingDetails.get(0).get("zip"),
//                billingDetails.get(0).get("email"));

//        checkoutPage.setBillingDetails(firstname,lastname,address_line1,city,state,zip,email);

        checkoutPage.setBillingDetails(billingDetails);


    }

    @When("User place an order")
    public void user_place_an_order() {

//        By placeOrderBtn = By.id("place_order");
//        driver.findElement(placeOrderBtn).click();
        new CheckoutPage(driver).placeOrder();
    }
    @Then("the order should be placed successfully")
    public void the_order_should_be_placed_successfully() {

       // By noticeTxt = By.cssSelector(".woocommerce-notice");
     String actualNoticeMsg =   new CheckoutPage(driver).getNotice();
//        String actualNoticeMsg = driver.findElement(noticeTxt).getText();

        Assert.assertEquals("Thank you . your order has been received ", actualNoticeMsg);
    }

}
