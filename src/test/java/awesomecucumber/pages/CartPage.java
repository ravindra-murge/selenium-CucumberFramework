package awesomecucumber.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {


    @FindBy(css = "td[class='product-name'] a")
    WebElement productNameFld;

    @FindBy(css = "input[type='number']")
    WebElement productQuantityFld;

    @FindBy(css = ".checkout-button")
    WebElement proceedToCheckoutBtn;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName(){
        String actualText = wait.until(ExpectedConditions.visibilityOf(productNameFld)).getText();
        return actualText;
    }

    public int getQauntity(){
        String actualQnty = wait.until(ExpectedConditions.visibilityOf(productQuantityFld)).getAttribute("value");
        return Integer.parseInt(actualQnty);
    }

    public void checkout(){
        wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutBtn)).click();
    }
}
