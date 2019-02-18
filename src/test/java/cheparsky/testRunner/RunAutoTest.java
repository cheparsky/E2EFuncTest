package cheparsky.testRunner;

//connect all necessary standard libraries

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cheparsky.pages.*;
import cheparsky.utilities.MyDriver;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

//define the name of the class - it must be the same name as the file
public class RunAutoTest {

    //add all the necessary variables
    public static int waitTime = 60;
    public static WebDriver wd;
    public static String haslo = "123456";
    public static List<String> errList = new ArrayList<String>();
    public static String browserName;

    @BeforeClass
    public static void setUp() {
        //We set parameters to run tests
        browserName = System.getProperty("browserName");
        wd = MyDriver.runWDriver(browserName); //Chrome, Firefox
        MyDriver.setWebDriverWait(RunAutoTest.waitTime);
        MyDriver.setErrList(errList);
    }

    //We define the main function that runs our tests
    @Test
    public void RunAutoTest() throws IOException {

        //checking availability of the main page
        EkMain.dostepnoscStrona();

        //If the main page is availability we start our tests
        if (EkMain.stronaGlowna) {

            //Close cookies bar
            EkMain.sgZamknijCookiesB();

            //star login process
            EkLogIn.runLogin(haslo);
            //if login was successful we start test user profile and library
            if (MyDriver.ifWWWAreEquals("http://www.e-kiosk.pl/secure/")) {
                EkProfile.runProfil();
                EkLibraryAndReader.runBiblioteka();
                EkMain.runWyloguj();
            }

            //test of registration
            EkRegistration.runRejestracja("EkRejestracjaTest3@ek.pl", RunAutoTest.haslo, "NIE");
            //EkMain.runWyloguj(); // na rzie nie wciaksny przycsik rejestracja, wiec wylogowanie nie jest potrzebne

            //test of availability of main categories
            EkMain.runKategoriiGlowne();

            //test of search mechanism
            EkSearch.runWyszukiwarka();

            //test of product page
            EkProductPage.runKartaProduktu(EkMain.sgRzepaB, 27, "http://www.e-kiosk.pl/numer", "Wydanie(Rzepa)");
            EkMain.runWyloguj();
            EkProductPage.runKartaProduktu(EkMain.sgRzepaB, 27, "http://www.e-kiosk.pl/numer", "Prenumerata(Rzepa)");
            EkMain.runWyloguj();
            EkProductPage.runKartaProduktu(EkMain.sgTopEBookB, 27, "http://www.e-kiosk.pl/numer", "Ebook");
            EkMain.runWyloguj();
            EkProductPage.runKartaProduktu(EkMain.sgTopAudiobookB, 27, "http://www.e-kiosk.pl/numer", "Audiobook");

            //basket emptying test
            EkEmptyingBasket.runOproznanieKoszyka();

            //archival number page test
            EkArchivalNumber.runNumeryArchiwalne();

            //contact page test
            EkContact.runKontakt();

            //logout test
            EkMain.runWyloguj();

            //activation code test
            EkActivationCode.runKodAktywacyjny();
            EkMain.runWyloguj();

            //purchase page test
            EkPurchasePath.runSciezkaZakupowa();
            EkMain.runWyloguj();

            //password reminder process test
            EkRememberPass.runNiePamietamHasla("NieistniejacyEmail@ek.pl");


        }
    }

    @AfterClass
    public static void tearDown() {
        //delete downloaded files
        if (RunAutoTest.browserName.equals("Chrome")) {
            EkLibraryAndReader.deleteFile(EkLibraryAndReader.bPlikP);
            EkLibraryAndReader.deleteFile(EkLibraryAndReader.bPlikB);
            // EkLibraryAndReader.deleteFile(EkLibraryAndReader.bPlikA); // EkLibraryAndReader.bAPobierzB(); //na razie nie robimy tego tak jak nie ma co pobierać
        }

        //close the browser after completing the tests
        wd.quit();

        //If there are errors, we send mail with tests errors
        System.out.println("//*******KONIEC AUTO TESTOW*******//");
        if (!MyDriver.errList.isEmpty()) {
            System.out.println("//*******Email z bledami wyslany*******//");
            //MyDriver.sendErrMessages(MyDriver.errList, RunAutoTest.browserName + " Raport","gmailFrom", "pass", "emailTo");

        }

    }


    //define all links for our tests
    public static String loginPage = "https://www.e-kiosk.pl/secure/index.php?page=login";
    public static String mainPage = "http://www.e-kiosk.pl/";
    public static String rememberPassPage = "https://www.e-kiosk.pl/secure/index.php?page=remind";
    public static String logOutPage = "https://www.e-kiosk.pl/secure/index.php?page=Welcome&cmd=logout";
    public static String registrationPage = "https://www.e-kiosk.pl/secure/index.php?page=register";
    public static String ePrasaPage = "http://www.e-kiosk.pl/e_prasa";
    public static String eBookiPage = "http://www.e-kiosk.pl/e_ksiazki";
    public static String AudiobookiPage = "http://www.e-kiosk.pl/audiobooki";
    public static String searchPage = "http://www.e-kiosk.pl/index.php?page=search";
    public static String basketPage = "https://www.e-kiosk.pl/secure/index.php?page=basket";
    public static String basketErrPage = "https://www.e-kiosk.pl/secure/index.php?page=basket&step=payment#err";
    public static String libraryMainPage = "https://www.e-kiosk.pl/secure/index.php?page=history&subpage=zakupy";
    public static String libraryePrasaPage = "https://www.e-kiosk.pl/secure/index.php?page=history&subpage=zakupy&id_issue_type=1";
    public static String libraryeBookiPage = "https://www.e-kiosk.pl/secure/index.php?page=history&subpage=zakupy&id_issue_type=2";
    public static String libraryAudiobookiPage = "https://www.e-kiosk.pl/secure/index.php?page=history&subpage=zakupy&id_issue_type=3";
    public static String libraryPrenumeratyPage = "https://www.e-kiosk.pl/secure/index.php?page=history&subpage=zakupy&id_issue_type=-1";
    public static String libraryUlubionePage = "https://www.e-kiosk.pl/secure/index.php?page=history&subpage=favourites";
    public static String archivalNumberPartPage = "http://www.e-kiosk.pl/index.php?page=titleissues&id_title=";
    public static String contactPage = "http://www.e-kiosk.pl/index.php?page=kontakt&type=1";
    public static String activationCodePage = "http://www.e-kiosk.pl/aktywacja";
    public static String profileZPage = "https://www.e-kiosk.pl/secure/index.php?page=client";
    public static String profileDAPage = "https://www.e-kiosk.pl/secure/index.php?page=client&subpage=moje_dane";
    public static String profileOLPage = "https://www.e-kiosk.pl/secure/index.php?page=client&subpage=opcje_logowania";
    public static String payUStep1PartPage = "https://secure.payu.com/";
    public static String payUStep2PartPage = "https://www.platnosci.pl/np/newpayment";

}
