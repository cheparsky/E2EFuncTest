package cheparsky.pages;

//connect all necessary standard libraries

import cheparsky.testRunner.RunAutoTest;
import cheparsky.utilities.MyDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//define the name of the class - it must be the same name as the file
public class EkContact {

    //add all the necessary variables
    private static WebDriver wd;

    //add WebElementy
    @FindBy(id = "contactEmail")
    private static WebElement skEmailI;

    @FindBy(id = "contactTopic")
    private static WebElement skTematI;

    @FindBy(id = "contactContent")
    private static WebElement skOpisI;

    @FindBy(xpath = ".//*[@id='faq-form']/form/fieldset/p/button")
    private static WebElement skWyslijB;

    @FindBy(className = "icon-warning")
    private static WebElement skIconWarnongL;

    // We define the functions that are responsible for checking contact - process
    public static void runKontakt() {
        String errMesage = "//*******KONTAKT*******//";
        wd = MyDriver.wd;
        EkContact.setPageObject();
        MyDriver.addErrList(errMesage);
        wd.get(RunAutoTest.mainPage);
        EkMain.sgKontaktB();
        if (MyDriver.ifWWWAreEquals(RunAutoTest.contactPage)) {
            EkContact.skEmailI();
            EkContact.skTematI();
            EkContact.skOpisI();
            EkContact.skWyslijB();
        }
        MyDriver.deleteErrList(errMesage);
    }

    // We define actions that are responsible for clearing the field Email
    public static void skEmailI() {
        MyDriver.InputDataClear(skEmailI,
                "Strona kontaktu: input 'Email' NIE jest edytowalny.",
                "Strona kontaktu nie zawiera inputu 'Email'.");
    }

    // We define actions that are responsible for clearing the field Temat
    public static void skTematI() {
        MyDriver.InputDataClear(skTematI,
                "Strona kontaktu: input 'Temat' NIE jest edytowalny.",
                "Strona kontaktu nie zawiera inputu 'Temat'.");
    }

    // We define actions that are responsible for clearing the field Opis
    public static void skOpisI() {
        MyDriver.InputDataClear(skOpisI,
                "Strona kontaktu: input 'Opis' NIE jest edytowalny.",
                "Strona kontaktu nie zawiera inputu 'Opis'.");
    }

    // We define the action, which is responsible for sending the contact form (wrong)
    public static void skWyslijB() {
        MyDriver.buttonElement(skWyslijB,
                skIconWarnongL,
                "Strona Kontakt: przycisk 'Wyślij' jest klikalny.",
                "Strona kontaktu: przycisk 'Wyślij' jest NIE klikalny.",
                "Strona kontaktu nie zawiera przycisku 'Wyślij'.");
    }

    // define the method that is responsible for adding Page Object Pattern
    public static void setPageObject() {
        PageFactory.initElements(wd, new EkContact());
    }

}
