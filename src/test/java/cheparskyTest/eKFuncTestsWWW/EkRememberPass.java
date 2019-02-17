package cheparskyTest.eKFuncTestsWWW;

//connect all necessary standard libraries
import cheparskyTest.utilities.MyDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//define the name of the class - it must be the same name as the file
public class EkRememberPass {
	
	//add all the necessary variables
	private static WebDriver wd;
	
	//add WebElementy
	@FindBy(xpath = ".//*[@id='all']/section/div/form/a")
	private static WebElement sphNiepamietamB;
	
	@FindBy(id = "inputEmail")
	private static WebElement sphEmailI;
	
	@FindBy(xpath = ".//*[@id='all']/section/div/form/div/button")
	private static WebElement sphWyslijB;
	
	@FindBy(xpath = ".//*[@id='all']/section/div/form/fieldset/div[1]/p")
	private static WebElement sphErrorTextL;
	
	// We define the functions that are responsible for checking przypomnienia hasla - process
	public static void runNiePamietamHasla(String email) {	
		String errMesage = "//*******NIE PAMIETAM HASLA*******//";
		wd = MyDriver.wd;
		EkRememberPass.setPageObject();
		MyDriver.addErrList(errMesage);
		wd.get(RunAutoTest.mainPage);
		EkMain.sgUserB();
		EkMain.sgZalogujB();
		EkRememberPass.sphNiepamietamB();
		if(MyDriver.ifWWWAreEquals(RunAutoTest.rememberPassPage)){
			EkRememberPass.sphEmailI(email);
			EkRememberPass.sphWyslijB();
		}
		MyDriver.deleteErrList(errMesage);
	}
	
	// We define the action that is responsible for pressing the button nie pamietam danych logowanie na stronie logowania
	public static void sphNiepamietamB (){
		MyDriver.buttonLink(sphNiepamietamB, 
							RunAutoTest.rememberPassPage.length(), 
							RunAutoTest.rememberPassPage, 
							"Strona Przypomnienia hasła odpowiada.", 
							"Strona logowania: przycisk 'Nie pamiętam danych logowania' jest nie klikalny", 
							"Strona logowania nie zawiera przycisku 'Nie pamiętam danych logowania'.");
	}
	
	// We define the action that is responsible for entering email
	public static void sphEmailI (String eli){
		MyDriver.InputData(sphEmailI, 
							eli, 
							"Strona Przypomnienia hasła: input 'Email' nie jest edytowalny.", 
							"Strona Przypomnienia hasła nie zawiera inputu 'Email'.");
	}
	
	// We define the action that is responsible for pressing the button wyslij  (wersja z bledem)
	public static void sphWyslijB(){
		MyDriver.buttonElement(sphWyslijB, 
								sphErrorTextL,
								"Przycisk przypomnienia hasła działa.", 
								"Strona Przypomnienia hasła: przycisk 'Wyślij' jest nie klikalny.",
								"Strona logowania nie zawiera przycisku 'Wyślij'" );

	}
	
	// define the method that is responsible for adding Page Object Pattern
	public static void setPageObject () {
		PageFactory.initElements(wd, new EkRememberPass());
	}

}
