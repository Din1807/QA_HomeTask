package pages;

import org.openqa.selenium.*;

public class ItemPage {
    public BaseFunc baseFunc;
    private final By GO_TO_CART_BTN = By.xpath(".//a[contains(.,'Pārlūkot pirkumu grozu')]");
    private final By ADD_TO_CART_BTN = By.id("add_to_cart_btn");
    private final By KEEP_LOOKING_BTN = By.id("continue_shopping");
    private final By PRODUCT_PRICE = By.xpath(".//div[contains(@class,'product-price-details__block')]/span/span");
    private final By PRODUCT_NAME = By.xpath(".//div[contains(@class,'product-righter google-rich-snippet')]/h1");
    private final By GO_TO_HOMEPAGE = By.xpath(".//a[contains(@class,'main-logo')]");
    private final By ITEM_QUANTITY_INPUT = By.id("product_quantity");
    private final By ITEM = By.xpath(".//div[contains(@class,'new-product-item')]");
    private final By NO_ITEM_NOTIFICATION = By.xpath(".//div[contains(@class,'no-online-product-notification')]");

    public ItemPage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public void itemNumberSelection(int itemCount) {
        baseFunc.itemSelection(ITEM);
        if(baseFunc.isElementPresent(NO_ITEM_NOTIFICATION)){
            System.out.println("Selected item is not available for purchase");
            baseFunc.closeBrowser();
            System.exit(1);
        }else {
            WebElement itemQuantity = baseFunc.findElement(ITEM_QUANTITY_INPUT);
            itemQuantity.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
            itemQuantity.sendKeys(Integer.toString(itemCount));
        }
    }

    public void addToCart() {
        if(baseFunc.isElementPresent(NO_ITEM_NOTIFICATION)){
            System.out.println("Selected item is not available for purchase");
            baseFunc.closeBrowser();
            System.exit(1);
        }else {
            baseFunc.itemSelection(ADD_TO_CART_BTN);
        }
    }

    public void keepLooking() {
        baseFunc.itemSelection(KEEP_LOOKING_BTN);
    }

    public void goToHomePage() {
        baseFunc.itemSelection(GO_TO_HOMEPAGE);
    }

    public void goToCart() {
        baseFunc.waitForElements(GO_TO_CART_BTN);
        baseFunc.findElement(GO_TO_CART_BTN).click();
    }

    public double getItemPrice() {
        baseFunc.waitForElements(PRODUCT_PRICE);
        return Double.parseDouble((baseFunc.findElements(PRODUCT_PRICE).get(0).getText()).replace(",", "."));
    }

    public String getItemName() {
        return baseFunc.findElement(PRODUCT_NAME).getText();
    }
}
