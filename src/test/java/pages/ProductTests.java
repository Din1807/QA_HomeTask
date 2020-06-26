package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTests {
    private BaseFunc baseFunc = new BaseFunc();
    private HomePage homePage = new HomePage(baseFunc);
    private ItemPage itemPage = new ItemPage(baseFunc);
    private CartPage cartPage = new CartPage(baseFunc);

    private final Logger LOGGER = LogManager.getLogger(ProductTests.class);

    private String productName = "Thrustmaster Race Gear Sparco Mod 4060131";
    private int itemCount = 2;
    private int firstItem = 0;
    private int secondItem = 5;

    @Test
    public void firstTask() {
        baseFunc.openHomePage();
        LOGGER.info("Opening k-senukai home page");
        baseFunc.popUpWindow();
        homePage.findInSearchInput(productName);
        LOGGER.info("Finding product '" + productName + "' in search input");
        itemPage.itemNumberSelection(itemCount);
        LOGGER.info("Item quantity selection: " + itemCount);
        double itemPricePerOne = itemPage.getItemPrice();
        String itemName = itemPage.getItemName();
        LOGGER.info("Product info in item page. Name: '" + itemName + "'. Price: " + itemPricePerOne);
        itemPage.addToCart();
        LOGGER.info("Item added to cart");
        itemPage.goToCart();
        LOGGER.info("Open cart");
        LOGGER.info("Product info in cart page. Name: '" + cartPage.getCartItemName() + "'. Price: " + cartPage.getCartItemPrice());
        LOGGER.info("Product total sum in item page: '" + cartPage.getCartItemTotalSum() + "'. Total sum in cart: " + itemPricePerOne * itemCount);
        LOGGER.info("Selected product quantity in item page: " + itemCount + ", in cart: " + cartPage.getCartItemQuantity());
        assertEquals(itemPricePerOne, cartPage.getCartItemPrice(), "Wrong product cost for 1 piece");
        assertEquals(itemName, cartPage.getCartItemName(), "Wrong product name");
        assertEquals(cartPage.getCartItemTotalSum(), itemPricePerOne * itemCount, "Wrong product total sum");
        assertEquals(itemCount, cartPage.getCartItemQuantity(), "Wrong product quantity");
        baseFunc.closeBrowser();
    }

    @Test
    public void secondTask() {
        baseFunc.openHomePage();
        LOGGER.info("Opening k-senukai home page");
        baseFunc.popUpWindow();
        homePage.findItemFromOffers(firstItem);
        LOGGER.info("Finding product from offers");
        double firstItemPrice = itemPage.getItemPrice();
        LOGGER.info("Saving item price");
        itemPage.addToCart();
        LOGGER.info("Item added to cart");
        itemPage.keepLooking();
        LOGGER.info("Click to continue shopping");
        itemPage.goToHomePage();
        LOGGER.info("Return to the main page");
        homePage.findItemFromOffers(secondItem);
        LOGGER.info("Finding product from offers");
        double secondItemPrice = itemPage.getItemPrice();
        LOGGER.info("Saving item price");
        itemPage.addToCart();
        LOGGER.info("Item added to cart");
        itemPage.goToCart();
        LOGGER.info("Open cart");
        LOGGER.info("First item page price " + firstItemPrice + ". In cart price " + cartPage.getItemPriceInList(0));
        assertEquals(firstItemPrice, cartPage.getItemPriceInList(0), "Wrong first item price");
        LOGGER.info("Second item page price " + secondItemPrice + ". In cart price " + cartPage.getItemPriceInList(1));
        assertEquals(secondItemPrice, cartPage.getItemPriceInList(1), "Wrong second item price");
        LOGGER.info("Total price in item page " + (cartPage.getItemPriceInList(0) + cartPage.getItemPriceInList(1)) + ". In cart total price " + cartPage.getCartItemTotalSum());
        assertEquals(cartPage.getCartItemTotalSum(), cartPage.getItemPriceInList(0) + cartPage.getItemPriceInList(1), "Wrong item total price");
        baseFunc.closeBrowser();
    }
}