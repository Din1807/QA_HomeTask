package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {
    public BaseFunc baseFunc;
    private final By PRICE_PER_ONE = By.xpath(".//p[contains(@class,'detailed-cart-item__price')]");
    private final By CART_PRODUCT_NAME = By.xpath(".//a[contains(@class,'detailed-cart-item__name-link')]");
    private final By CART_PRODUCT_TOTAL_SUM = By.id("cart-full-total-price");
    private final By CART_PRODUCT_QUANTITY = By.xpath(".//div[contains(@class, 'detailed-cart-item__form-item')]/input");
    private final By CART_PRODUCT_LIST = By.xpath(".//div[contains(@class,'detailed-cart-item')][@data-price]");

    public CartPage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public double getCartItemPrice() {
        double itemPrice = (Double.parseDouble((baseFunc.findElement(PRICE_PER_ONE).getText()).replace(",", ".")
                .substring(0, (baseFunc.findElement(PRICE_PER_ONE).getText()).replace(",", ".").lastIndexOf(' '))));
        return itemPrice;
    }

    public String getCartItemName() {
        String itemName = baseFunc.findElement(CART_PRODUCT_NAME).getText();
        return itemName;
    }

    public double getCartItemTotalSum() {
        double itemTotalSum = Double.parseDouble((baseFunc.findElement(CART_PRODUCT_TOTAL_SUM).getText()).replace(",", ".")
                .substring(0, (baseFunc.findElement(CART_PRODUCT_TOTAL_SUM).getText()).replace(",", ".").lastIndexOf(' ')));
        return itemTotalSum;
    }

    public int getCartItemQuantity() {
        int itemCartQuantity = Integer.parseInt(baseFunc.findElement(CART_PRODUCT_QUANTITY).getAttribute("data-original-quantity"));
        return itemCartQuantity;
    }

    public double getItemPriceInList(int itemIndex) {
        baseFunc.moveToElement(CART_PRODUCT_LIST);
        List<WebElement> prices = baseFunc.findElements(CART_PRODUCT_LIST);
        double itemCost = Double.parseDouble(prices.get(itemIndex).findElement(PRICE_PER_ONE).getText().replace(",", ".")
                .substring(0, (baseFunc.findElement(CART_PRODUCT_TOTAL_SUM).getText()).replace(",", ".").lastIndexOf(' ')));
        return itemCost;
    }

}
