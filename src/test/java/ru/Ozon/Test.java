package ru.Ozon;

import org.openqa.selenium.support.PageFactory;
import ru.Ozon.HomePage;
import ru.Ozon.ProfilePage;
import ru.Ozon.ShoppingPage;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test{
    public ChromeDriver driver;
    public WebDriverWait wait;

    // Начало работы
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/Additional/IntelliJ IDEA Community Edition 2020.1.1/driver/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 240);
    }

    // Окончание работы
    @After
    public void close() {
        driver.quit();
    }

    private String city = "Вольск";
    private String phone = "9272244997";

    // Авторизация
    @org.junit.Test
    public void login() throws InterruptedException {

        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        ProfilePage profileUserPage = PageFactory.initElements(driver, ProfilePage.class);

        homePage.open();
        homePage.openLoginForm();
        homePage.fillPhone(phone);
        profileUserPage.checkAuthorization();
    }

    // Смена города
    @org.junit.Test
    public void changeCity() throws InterruptedException {

        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        ProfilePage profileUserPage = PageFactory.initElements(driver, ProfilePage.class);

        homePage.open();
        homePage.changeCity();
        homePage.fillCity(city);
        homePage.checkCurrentCity(city);
        homePage.openLoginForm();
        homePage.fillPhone(phone);
        profileUserPage.open();
        homePage.checkCurrentCity(city);
    }

    // Фильтрация по цене
    @org.junit.Test
    public void filtrationByPrice() throws InterruptedException {

        HomePage homePage = PageFactory.initElements(driver, HomePage.class);

        homePage.open();
        homePage.fillSearchQuery();

        ShoppingPage shoppingPage = PageFactory.initElements(driver, ShoppingPage.class);

        shoppingPage.fillPriceFromFilter();
        shoppingPage.fillPriceToFilter();
        shoppingPage.changeFiltrationMethod();
        shoppingPage.addSecondPositionToCart();
        shoppingPage.checkCart();
    }

    // Фильтрация по мощности
    @org.junit.Test
    public void filtrationByPower() throws InterruptedException {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);

        homePage.open();
        homePage.fillSearchQuery();

        ShoppingPage shoppingPage = PageFactory.initElements(driver, ShoppingPage.class);

        shoppingPage.fillPowerFromFilter();
        shoppingPage.changeFiltrationMethod();
        shoppingPage.addSecondPositionToCart();
        shoppingPage.checkCart();
    }
}
