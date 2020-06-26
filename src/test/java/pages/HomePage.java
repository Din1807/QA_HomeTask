package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class HomePage {
    public BaseFunc baseFunc;
    private final By MAIN_SEARCH_INPUT = By.id("q");
    private final By BUTTON_SEARCH = By.xpath(".//button[contains(@class,'main-search-submit')]");
    private final By YOUR_OFFERS = By.xpath(".//div[contains(@class,'glide__slide product-slide products-list clearfix glide__slide--active')]/a");

    public HomePage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public void findInSearchInput(String name) {
        WebElement searchInput = baseFunc.findElement(MAIN_SEARCH_INPUT);
        searchInput.clear();
        searchInput.sendKeys(name);
        baseFunc.findElement(BUTTON_SEARCH).click();
    }

    public void findItemFromOffers(int number) {
        baseFunc.waitForElements(YOUR_OFFERS);
        List<WebElement> offers = baseFunc.findElements(YOUR_OFFERS);
        if (number <= offers.size() && number >= 0) {
            baseFunc.moveToElement(YOUR_OFFERS);
            baseFunc.wait.until(ExpectedConditions.elementToBeClickable(offers.get(number)));
            baseFunc.click(offers.get(number));
        } else {
            System.out.println("Not available item");
            baseFunc.closeBrowser();
            System.exit(1);
        }
    }
}
