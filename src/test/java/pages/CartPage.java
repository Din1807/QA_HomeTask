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
        baseFunc.waitForElements(PRICE_PER_ONE);
        return baseFunc.getDoubleValue(PRICE_PER_ONE);
    }

    public String getCartItemName() {
        return baseFunc.findElement(CART_PRODUCT_NAME).getText();
    }

    public double getCartItemTotalSum() {
        baseFunc.waitForElements(CART_PRODUCT_TOTAL_SUM);
        return baseFunc.getDoubleValue(CART_PRODUCT_TOTAL_SUM);
    }

    public int getCartItemQuantity() {
        return Integer.parseInt(baseFunc.findElement(CART_PRODUCT_QUANTITY).getAttribute("data-original-quantity"));
    }

    public double getItemPriceInList(int itemIndex) {
        baseFunc.moveToElement(CART_PRODUCT_LIST);
        List<WebElement> prices = baseFunc.findElements(CART_PRODUCT_LIST);
        return Double.parseDouble(prices.get(itemIndex).findElement(PRICE_PER_ONE).getText().replace(",", ".")
                .substring(0, (baseFunc.findElement(CART_PRODUCT_TOTAL_SUM).getText()).replace(",", ".").lastIndexOf(' ')));
    }
}
