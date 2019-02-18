package cheparsky.pages;

//connect all necessary standard libraries

import cheparsky.testRunner.RunAutoTest;
import cheparsky.utilities.MyDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//define the name of the class - it must be the same name as the file
public class EkPurchasePath {

    //add all the necessary variables
    private static WebDriver wd;

    //add WebElementy
    @FindBy(xpath = ".//*[@id='all']/section/div/form/div[2]/span[1]/input")
    private static WebElement szKDalejB;

    @FindBy(xpath = ".//*[@id='all']/section/div/form/fieldset/div[1]/div[1]/p")
    private static WebElement szKDaneIWB;

    @FindBy(xpath = ".//*[@id='all']/section/div/form/fieldset/p/button")
    private static WebElement szKEdutyjDaneB;

    @FindBy(xpath = ".//*[@id='all']/section/div/form/fieldset/div[2]/p/button")
    private static WebElement szKEdutyjDaneFirmyB;

    @FindBy(id = "inputName")
    private static WebElement szKImieI;

    @FindBy(xpath = ".//*[@id='all']/section/div/form/fieldset/div[2]/div/p")
    private static WebElement szKDaneVATIWB;

    @FindBy(id = "inputNameFV")
    private static WebElement szKFirmaI;

    @FindBy(className = "payu-payment-button")
    private static WebElement szPPlaceB;

    @FindBy(xpath = "//*[@id='app']/div/div[2]/main/div[2]/article/div[1]/div/div[1]/a[1]")
    private static WebElement szPUBankTransfer;

    @FindBy(xpath = "//*[@id='app']/div/div[2]/main/div[2]/article/div[1]/div[2]/div[2]/div")
    private static WebElement szPUPrzelewBankowyB;

    @FindBy(id = "invokePay")
    private static WebElement szPUPlaceB;

    @FindBy(id = "button")
    private static WebElement szPUPowrotB;

    @FindBy(xpath = ".//*[@id='all']/section[1]/div/div/p")
    private static WebElement szSuccessL;

    // We define the functions that are responsible for checking sciezki zakpowej - process
    public static void runSciezkaZakupowa() {
        String errMesage = "//*******ŚCIEŻKA ZAKUPOWA*******//";
        wd = MyDriver.wd;
        EkPurchasePath.setPageObject();
        MyDriver.addErrList(errMesage);
        wd.get(RunAutoTest.mainPage);
        EkMain.kpProductTO(EkMain.sgRzepaB, 27, "http://www.e-kiosk.pl/numer", "Wydanie(Rzepa)");
        EkProductPage.kpDoKoszykaB();
        if (MyDriver.isVisible(EkLogIn.slLoginI)) {
            EkLogIn.slLogowanie(RunAutoTest.basketPage.length(), RunAutoTest.basketPage);
        }
        if (MyDriver.ifWWWAreEquals(RunAutoTest.basketPage)) {
            EkPurchasePath.szKEdutyjDaneB();
            EkPurchasePath.szKImieClearI();
            EkPurchasePath.szKEdutyjDaneFirmyB();
            EkPurchasePath.szKFirmaClearI();
            EkPurchasePath.szKDalejB();
            if (MyDriver.ifWWWAreEquals(RunAutoTest.basketErrPage)) {
                if (MyDriver.isVisible(szKDaneIWB)) {// if there is an error / if it is clickable
                    EkPurchasePath.szKImieI();
                } else {
                    MyDriver.errMessage("Koszyk: NIE ma komunikatu błędu danych zamawiającego.");
                }
                if (MyDriver.isVisible(szKDaneVATIWB)) {// if there is an error / if it is clickable
                    EkPurchasePath.szKFirmaI();
                } else {
                    MyDriver.errMessage("Koszyk: NIE ma komunikatu błędu danych do faktury VAT.");
                }
                EkPurchasePath.szKDalejB();
            }

            if (MyDriver.ifWWWAreEquals("https://www.e-kiosk.pl/secure/index.php?page=basket&step=payment")) {
                EkPurchasePath.szPPlaceB();
                if (MyDriver.ifWWWAreEquals(RunAutoTest.payUStep1PartPage)) {
                    System.out.println("//**PayU**//"); // Here begins the PayU part
                    EkPurchasePath.szPUQuickTransfer();
                    EkPurchasePath.szPUPrzelewBankowyB();
                    if (MyDriver.ifWWWAreEquals(RunAutoTest.payUStep2PartPage)) {
                        EkPurchasePath.szPUPowrotB();
                        if (MyDriver.isVisible(szSuccessL)) {
                            MyDriver.isZamowienie(szSuccessL);
                        }
                    }
                } else if (MyDriver.ifWWWNoEquals(RunAutoTest.mainPage)) {
                    wd.get(RunAutoTest.mainPage);
                }
            }
        }
        MyDriver.deleteErrList(errMesage);
    }

    // We define the action that is responsible for pressing the button edytuj danych osobowych w koszyku
    public static void szKEdutyjDaneB() {
        MyDriver.buttonElement(szKEdutyjDaneB,
                szKImieI,
                "Koszyk: przycisk 'edytuj dane' jest klikalny i działa.",
                "Koszyk: przycisk 'edytuj dane' jest NIE klikalny.",
                "Koszyk nie zawiera przycisku 'edytuj dane'.");
    }

    // We define the action that is responsible for pressing the button edytuj danych firmy w koszyku
    public static void szKEdutyjDaneFirmyB() {
        MyDriver.buttonElement(szKEdutyjDaneFirmyB,
                szKFirmaI,
                "Koszyk: przycisk 'edytuj dane firmy' jest klikalny i działa.",
                "Koszyk: przycisk 'edytuj dane firmy' jest NIE klikalny.",
                "Koszyk nie zawiera przycisku 'edytuj dane firmy'.");
    }

    // We define actions that are responsible for clearing the field Imie w danych osoby
    public static void szKImieClearI() {
        MyDriver.InputDataClear(szKImieI,
                "Strona Profilu - Dane adresowe: input 'Imię' nie jest edytowalny.",
                "Strona Profilu - Dane adresowe nie zawiera inputu 'Imię'.");

    }

    // We define actions that are responsible for clearing the field Firma w danych firmy
    public static void szKFirmaClearI() {
        MyDriver.InputDataClear(szKFirmaI,
                "Strona Profilu - Dane adresowe: input 'Firma' nie jest edytowalny.",
                "Strona Profilu - Dane adresowe nie zawiera inputu 'Firma'.");
    }

    // We define the action that is responsible for pressing the button Dalej w koszyku
    public static void szKDalejB() {
        MyDriver.ButtonTypical(szKDalejB,
                "Koszyk: przycisk 'dalej' jest klikalny.",
                "Koszyk: przycisk 'dalej' jest NIE klikalny.",
                "Koszyk nie zawiera przycisku 'dalej'.");
    }

    // We define actions, which are responsible for completing the field Imie in personal data
    public static void szKImieI() {
        MyDriver.InputData(szKImieI,
                "Andr",
                "Koszyk: input 'Imię' nie jest edytowalny.",
                "Koszyk nie zawiera inputu 'Imię'.");
    }

    // We define actions, which are responsible for completing the field Firma in company data
    public static void szKFirmaI() {
        MyDriver.InputData(szKFirmaI,
                "eK",
                "Koszyk: input 'Firma' nie jest edytowalny.",
                "Koszyk nie zawiera inputu 'Firma'.");
    }

    // We define the action that is responsible for pressing the button Place z PayU w Podsumowaniu
    public static void szPPlaceB() {
        MyDriver.buttonElement(szPPlaceB,
                szPUBankTransfer,
                "Platnosc: przycisk 'place z PayU' jest klikalny i działa.",
                "Platnosc: przycisk 'place z PayU' jest NIE klikalny.",
                "Platnosc nie zawiera przycisku 'place z PayU'.");
    }

    // We define the action that is responsible for pressing the button Quick Transfer on page PayU
    public static void szPUQuickTransfer() {
        MyDriver.buttonElement(szPUBankTransfer,
                szPUPrzelewBankowyB,
                "PayU Krok1: przycisk 'Bank Transfer' jest klikalny.",
                "PayU Krok1: przycisk 'Bank Transfer' NIE jest klikalny.",
                "PayU Krok1 nie zawiera przycisku 'Quick Transfer'.");
    }

    // We define the action that is responsible for pressing the button Przelew Bankowy on page PayU
    public static void szPUPrzelewBankowyB() {
        MyDriver.buttonElement(szPUPrzelewBankowyB,
                szPUPowrotB,
                "PayU Krok1: metoda płatności 'Przelew bankowy' jest klikalna.",
                "PayU Krok1: metoda płatności 'Przelew bankowy' NIE jest klikalna.",
                "PayU Krok1 nie zawiera metodu płatności 'Przelew bankowy'.");
    }

    // We define the action that is responsible for pressing the button Place on page PayU
    public static void szPUPlaceB() {
        MyDriver.buttonLink(szPUPlaceB,
                RunAutoTest.payUStep2PartPage.length(),
                RunAutoTest.payUStep2PartPage,
                "PayU Krok1: przycisk 'dalej' jest klikalny i działa.",
                "PayU Krok1: przycisk 'dalej' jest NIE klikalny.",
                "PayU Krok1 nie zawiera przycisku 'Płacę'.");
    }

    // We define the action that is responsible for pressing the button Powrot on page PayU
    public static void szPUPowrotB() {
        MyDriver.buttonElement(szPUPowrotB,
                szSuccessL,
                "Zamówienie zostało zrealizowane. Komunikat o zrealizowanym zamówieniu jest wyświetlany.",
                "PayU Krok2: przycisk 'Powrót' jest NIE klikalny.",
                "PayU Krok2 do sprawdzenia. process nie przechodzi do biblioteczki.");
    }

    // define the method that is responsible for adding Page Object Pattern
    public static void setPageObject() {
        PageFactory.initElements(wd, new EkPurchasePath());
    }

}
