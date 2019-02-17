package cheparskyTest.eKFuncTestsWWW;

//connect all necessary standard libraries
import cheparskyTest.utilities.MyDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//define the name of the class - it must be the same name as the file
public class EkLogIn {
	
	//add all the necessary variables
	private static  WebDriver wd;
	public static String iLogin = "AutoTest10";
	public static String ipass;
	
	//add WebElementy
	@FindBy(xpath = ".//*[@id='all']/section/div/form/fieldset/h3")
	private static WebElement slZalogujL; //strona logowania Zaloguj Label
	
	@FindBy(id = "inputEmail")
	public static WebElement slLoginI; //strona logowania Login input
	
	@FindBy(id = "inputPassword")
	private static WebElement slHasloI;
	
	@FindBy(className = "placeholder")
	private static WebElement slZapamietajmnieCh;
	
	@FindBy(className = "buttons")
	private static WebElement slZalogujB;
	
	@FindBy(className = "icon-warning")
	private static WebElement slBladL;
	
	// We define the functions that are responsible for checking logowania - process
	public static void runLogin (String haslo) {
		String errMesage = "//*******LOGOWANIE: "+EkLogIn.iLogin+"*******//";
		wd = MyDriver.wd;
		EkLogIn.setPageObject();
		MyDriver.addErrList(errMesage);
		wd.get(RunAutoTest.mainPage);
		EkLogIn.ipass = haslo;
		EkMain.sgUserB();
		EkMain.sgZalogujB();
		if(MyDriver.ifWWWAreEquals(RunAutoTest.loginPage)) {
			EkLogIn.slLogowanie(29, "http://www.e-kiosk.pl/secure/");   //process logowania
		}
		MyDriver.deleteErrList(errMesage);
	}
	
	// We define the action, which is responsible for checking the inscription on poczatku strony
	public static void slZalogujL (){
		MyDriver.Label(slZalogujL, 
						"ZALOGUJ ZA POMOCĄ ADRESU EMAIL", 
						"Strona logowania: etykieta 'ZALOGUJ ZA POMOCĄ ADRESU EMAIL' ma inny tekst.", 
						"Strona logowania nie zawiera etykietę 'ZALOGUJ ZA POMOCĄ ADRESU EMAIL'.");

	}
	
	// We define the action that is responsible for entering loginu
	public static void slLoginI (){
		MyDriver.InputData(slLoginI, 
							EkLogIn.iLogin, 
							"Strona logowania: input 'Email/Login' nie jest edytowalny.", 
							"Strona logowania nie zawiera inputu 'Email/Login'.");
	}
	
	// We define the action that is responsible for entering hasla
	public static void slHasloI (){
		MyDriver.InputData(slHasloI, 
							EkLogIn.ipass, 
							"Strona logowania: input 'Hasło' nie jest edytowalny.", 
							"Strona logowania nie zawiera inputu 'Hasło'.");
	}
	
	// We define the action that is responsible for selecting the checkbox Zapamietaj Mnie
	public static void slZapamietajMnieCh (){
		MyDriver.CheckboxUnClick(slZapamietajmnieCh, 
									"Strona logowania: checkbox 'Zapamiętaj mnie' jest nie zaznaczona, czego nie może być.", 
									"Strona logowania: checkbox 'Zapamiętaj mnie' NIE jest klikalny.", 
									"Strona logowania nie zawiera checkbox`a 'Zapamiętaj mnie'.");
		
	}
	
	// We define the action that is responsible for pressing the button zaloguj
	public static void slZalogujB (int urli, String url){
		MyDriver.buttonLink(slZalogujB, 
							urli, 
							url, 
							"Logowanie za pomocą konta: "+EkLogIn.iLogin+", udało się.", 
							"Strona logowania: przycisk 'zaloguj' jest nie klikalny.", 
							"Strona logowania nie zawiera przycisku 'zaloguj'.");
	}
	
	// We define functions that are responsible for login - process
	public static void slLogowanie (int lenghFinalUrl, String finalUrl){
		EkLogIn.slZalogujL();
		EkLogIn.slLoginI();
		EkLogIn.slHasloI();
		EkLogIn.slZapamietajMnieCh();
		EkLogIn.slZalogujB(lenghFinalUrl, finalUrl);
	}
	
	// define the method that is responsible for adding Page Object Pattern
	public static void setPageObject () {
		PageFactory.initElements(wd, new EkLogIn());
	}

}
