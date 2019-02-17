package cheparskyTest.eKFuncTestsWWW;

//connect all necessary standard libraries
import java.util.List;

import cheparskyTest.utilities.MyDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//define the name of the class - it must be the same name as the file
public class EkRegistration {
	
	//add all the necessary variables
	private static WebDriver wd;
	private static String ie;
	
	//add WebElementy
	@FindBy(xpath = ".//*[@id='all']/section/div/form/fieldset/h3")
	private static WebElement srRejestracjaL; //strona rejestracji Rejestracja Label
	
	@FindBy(id = "inputEmail")
	private static WebElement srEmailI; //strona rejestracji Email input
	
	@FindBy(id = "inputPassword")
	private static WebElement srHasloI;
	
	@FindBy(xpath = ".//*[@id='all']/section/div/form/fieldset/div[3]/h3")
	private static WebElement srUlubioneL; //strona rejestracji Ulubione Label
		
	@FindBy(id = "inputName")
	private static WebElement srImieI;
	
	@FindBy(id = "inputBirth")
	private static WebElement srDataUrI;
	
	@FindBy(xpath = ".//*[@id='all']/section/div/form/fieldset/div[3]/div[1]/label/span[3]")
	private static WebElement srUlubioneCh;
	
	@FindBy(xpath = ".//*[@id='all']/section/div/form/fieldset/div[6]/label/span")
	private static WebElement srZgodaRegulaminCh;
	
	@FindBy(xpath = ".//*[@id='all']/section/div/form/fieldset/div[7]/label/span")
	private static WebElement srZgodaMarketingCh;
	
	@FindBy(xpath = ".//*[@id='all']/section/div/form/div/button")
	private static WebElement srZarejestrujB;
	
	@FindBy(className = "icon-warning")
	private static WebElement srIconError;
	
	// We define the functions that are responsible for checking registration - process
	public static void runRejestracja (String email, String pass, String zgoda){
		String errMesage = "//*******REJESTRACJA - ZGODA "+zgoda+"*******//";
		wd = MyDriver.wd;
		EkRegistration.setPageObject();
		MyDriver.addErrList(errMesage);
		ie = email;
		wd.get(RunAutoTest.mainPage);
		EkMain.sgUserB();
		EkMain.sgRejestracjaB();
		if(MyDriver.ifWWWAreEquals(RunAutoTest.registrationPage)) {
			EkRegistration.srRejestracjaL();
			EkRegistration.srEmailI(email);
			EkRegistration.srHasloI(pass);
			EkRegistration.srUlubioneL();
			EkRegistration.srUlubioneCh();
			EkRegistration.srImieI("ImieEkTestac10");
			EkRegistration.srDataUrI("07/09/2015");
			EkRegistration.srZgodaMarketingCh();
			EkRegistration.srZarejestrujB();
		}
		MyDriver.deleteErrList(errMesage);
	}
	
	// We define the action, which is responsible for checking the inscription on początku strony
	public static void srRejestracjaL (){
		MyDriver.Label(srRejestracjaL, 
						"REJESTRACJA ZA POMOCĄ ADRESU EMAIL", 
						"Strona rejestracji: etykieta 'REJESTRACJA ZA POMOCĄ ADRESU EMAIL' zawiera inny tekst.", 
						"Strona rejestracji nie zawiera etykietę 'REJESTRACJA ZA POMOCĄ ADRESU EMAIL'.");

	}
	
	// We define actions that are responsible for entering email
	public static void srEmailI (String ei){
		MyDriver.InputData(srEmailI, 
							ei, 
							"Strona rejestracji: input 'Email' nie jest edytowalny.", 
							"Strona logowania nie zawiera inputu 'Email'.");
	}
	
	// We define actions that are responsible for entering hasla
	public static void srHasloI (String pass){
		MyDriver.InputData(srHasloI, 
							pass, 
							"Strona rejestracji: input 'Hasło' nie jest edytowalny.", 
							"Strona logowania nie zawiera inputu 'Hasło'.");
	}
	
	// We define actions, which are responsible for checking the inscription before categories
	public static void srUlubioneL (){
		MyDriver.Label(srUlubioneL, 
						"TWOJE ULUBIONE KATEGORIE TO?", 
						"Strona rejestracji: etykieta 'TWOJE ULUBIONE KATEGORIE TO?' zawiera inny tekst.", 
						"Strona rejestracji nie zawiera etykietę 'TWOJE ULUBIONE KATEGORIE TO?'.");
	}
	
	// We define the action that is responsible for all the check boxes
	public static void srUlubioneCh (){
		if(MyDriver.isVisible(srUlubioneCh)){
			List<WebElement> boxy = wd.findElements(By.xpath(".//*[@id='all']/section/div/form/fieldset/div[3]/div"));
			int count = boxy.size();
			for (int il = 1; il<=count; il++){
				WebElement element = wd.findElement(By.xpath(".//*[@id='all']/section/div/form/fieldset/div[3]/div["+il+"]/label/span[3]"));
				String text = element.getAttribute("innerHTML").trim();
				if (!element.isSelected()) { // if unchecked is = false
					try{
						element.click();
						}
					catch (Exception e){ MyDriver.errMessage("Strona rejestracji: checkbox "+text+" nie jest klikalny."); }
				} 
				else{ MyDriver.errMessage("Strona rejestracji: checkbox "+text+" jest już zaznaczony, czego nie może być."); }
			}
		}
		else{ MyDriver.errMessage("Strona rejestracji nie zawiera checkbox`y ulubionych kategorii."); }
	}
	
	// We define a function that is responsible for entering the date of birth
	public static void srDataUrI (String ei){
		MyDriver.InputData(srDataUrI, 
							ei, 
							"Strona rejestracji: input 'Data urodzenia' nie jest edytowalny.", 
							"Strona logowania nie zawiera inputu 'Data urodzenia'.");
		EkRegistration.srDataUrI.sendKeys(Keys.ENTER); // Potwierdzamy wprowadzona date
	}
	
	// We define the action that is responsible for the introduction Imie
	public static void srImieI (String ei){
		MyDriver.InputData(srImieI, 
							ei, 
							"Strona rejestracji: input 'Podaj imię' nie jest edytowalny.", 
							"Strona logowania nie zawiera inputu 'Podaj imię'.");
	}
	
	// We define the action that is responsible for the introduction Zgoda Regulaminu // NIE UZYWAMY NA RAZIE
	public static void srZgodaRegulaminCh (){
		MyDriver.CheckboxClick(srZgodaRegulaminCh, 
				"Strona rejestracji: zgoda Regulaminu jest już zaznaczona, czego nie może być.",
				"Strona rejestracji: zgoda Regulaminu nie jest klikalna.", 
				"Strona rejestracji nie zawiera zgody Regulaminu.");
	}
	
	// We define the action that is responsible for the introduction Zgoda Marketingowa
	public static void srZgodaMarketingCh (){
		MyDriver.CheckboxClick(srZgodaMarketingCh, 
				"Strona rejestracji: zgoda Marketingu jest już zaznaczona, czego nie może być.",
				"Strona rejestracji: zgoda Marketingu nie jest klikalna.", 
				"Strona rejestracji nie zawiera zgody Marketingu.");

	}
	
	// We define the action that is responsible for pressing the button Zarejestruj (wersja z bledem)
	public static void srZarejestrujB(){
		MyDriver.buttonElement(srZarejestrujB, 
								srIconError,
								"Rejestrowanie (blednie) konta: "+EkRegistration.ie+", udało się.", 
								"Strona rejestracji: przycisk 'Zarejestruj' jest nie klikalny.",
								"Strona rejestracji nie zawiera przycisku 'Zarejestruj'." );
	}
	
	// define the method that is responsible for adding Page Object Pattern
	public static void setPageObject () {
		PageFactory.initElements(wd, new EkRegistration());
	}

}
