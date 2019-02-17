package cheparskyTest.eKFuncTestsWWW;

//connect all necessary standard libraries
import cheparskyTest.utilities.MyDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//define the name of the class - it must be the same name as the file
public class EkProfile {
	
	//add all the necessary variables
	private static WebDriver wd;
	private static JavascriptExecutor jse;
	private static String nazwiskoOld;
	
	//add WebElementy
	@FindBy(linkText = "Dane adresowe")
	private static WebElement spDaneAdresoweB;
	
	@FindBy(id = "inputSurname")
	private static WebElement spDANazwiskoI;
	
	@FindBy(xpath = ".//*[@id='all']/section/div/form/div/button")
	private static WebElement spDAZapiszZmianyB;
	
	@FindBy(xpath = ".//*[@id='all']/section/div/form/div[1]/p")
	private static WebElement spDAIconSuccessL;
	
	@FindBy(linkText = "Opcje logowania")
	private static WebElement spOpcjeLogowaniaB;
	
	@FindBy(xpath = ".//*[@id='all']/section/div/form[3]/fieldset/p/button[1]")
	private static WebElement spOLFBB;
	
	@FindBy(id = "email")
	private static WebElement spOLFBEmailI;
	
	@FindBy(xpath = ".//*[@id='all']/section/div/form[3]/fieldset/p/button[2]")
	private static WebElement spOLGB;
	
	@FindBy(id = "identifierId")
	private static WebElement spOLGEmailI;
	
	@FindBy(xpath = ".//*[@id='all']/aside[1]/div/ul/li[1]/a")
	private static WebElement spBibliotekaB;
	
	// We define the functions that are responsible for checking profile page - process
	public static void runProfil() {	
		String errMesage = "//*******PROFIL*******//";
		wd = MyDriver.wd;
		EkProfile.setPageObject();
		MyDriver.addErrList(errMesage);
		wd.get(RunAutoTest.mainPage);
		jse = (JavascriptExecutor)wd; // this line allows you to use JavaScript in your code
		EkMain.sgUserB();
		EkMain.sgProfilB();
		if(MyDriver.ifWWWAreEquals(RunAutoTest.profileZPage)){
			EkProfile.spDaneAdresoweB();
			if(MyDriver.ifWWWAreEquals(RunAutoTest.profileDAPage)){
				if(MyDriver.isVisible(spDANazwiskoI)){
					nazwiskoOld = spDANazwiskoI.getAttribute("value").trim();
					if(nazwiskoOld.equals("Che")) { nazwiskoOld = "Chepars"; } else{ nazwiskoOld = "Che"; }
					EkProfile.spDANazwiskoI(nazwiskoOld);
					jse.executeScript("scroll(0,700)");
					EkProfile.spDAZapiszZmianyB();
					if(spDANazwiskoI.getAttribute("value").trim().equals(nazwiskoOld)){ 
						System.out.println("Strona Dane adresowe: dane zostały zapisane poprawne oraz komunikat o udanym zapisaniu danych jest wyswietlany."); }
				}
			}
			EkProfile.spOpcjeLogowaniaB();
			if(MyDriver.ifWWWAreEquals(RunAutoTest.profileOLPage)){
				EkProfile.spOLFBB();
				EkProfile.spOLGB();
			}
			
			jse.executeScript("scroll(0,0)");
			EkProfile.spBibliotekaB();
		}
		MyDriver.deleteErrList(errMesage);
	}
	
	// We define actions that are responsible for clicking in rozdzial profilu - dane adresowe
	public static void spDaneAdresoweB(){
		MyDriver.buttonLink(spDaneAdresoweB, 
							RunAutoTest.profileDAPage.length(), 
							RunAutoTest.profileDAPage, 
							"Profil: Strona Dane adresowe odpowiada.", 
							"Profil: przycisk 'Dane adresowe' jest nie klikalny.", 
							"Profil nie zawiera przycisku 'Dane adresowe'.");
		
	}
	
	// We define the action that is responsible for entering text to Nazwisko input
	public static void spDANazwiskoI(String nazwisko){
		MyDriver.InputData(spDANazwiskoI, 
							nazwisko, 
							"Strona Profilu - Dane adresowe: input 'Nazwisko' nie jest edytowalny.", 
							"Strona Profilu - Dane adresowe nie zawiera przycisku 'Nazwisko'.");
	}
	
	// We define the action that is responsible for pressing the button zapisz zmiany
	public static void spDAZapiszZmianyB(){
		MyDriver.buttonElement(spDAZapiszZmianyB, 
								spDAIconSuccessL, 
								"Strona Dane adresowe: przycisk 'Zapisz zmiany' jest klikalny.", 
								"Strona Dane adresowe: przycisk 'Zapisz zmiany' jest NIE klikalny.", 
								"Strona Dane adresowe nie zawiera przycisku 'Zapisz zmiany'.");
	}
	
	// We define actions that are responsible for clicking in rozdzial profilu - opcje logowania
	public static void spOpcjeLogowaniaB(){
		MyDriver.buttonLink(spOpcjeLogowaniaB, 
							RunAutoTest.profileOLPage.length(), 
							RunAutoTest.profileOLPage, 
							"Profil: Strona Opcje logowania odpowiada.", 
							"Profil: przycisk 'Opcje logowania' jest nie klikalny.", 
							"Profil nie zawiera przycisku 'Opcje logowania'.");
		
	}
	
	// We define the action, which is responsible for checking if the button works Polacz z kontem Fb
	public static void spOLFBB(){
		MyDriver.buttonSN(spOLFBB,
							spOLFBEmailI,
							EkMain.mainWindow,
							"Strona Opcje logowania: przycisk 'Połącz z kontem Facebooka' jest klikalny.", 
							"Strona Opcje logowania: przycisk 'Połącz z kontem Facebooka' jest NIE klikalny.", 
							"Strona Opcje logowania nie zawiera przycisku 'Połącz z kontem Facebooka'.");
	}
	
	// We define the action, which is responsible for checking if the button works Polacz z kontem Google
	public static void spOLGB(){
		MyDriver.buttonSN(spOLGB,
							spOLGEmailI,
							EkMain.mainWindow,
							"Strona Opcje logowania: przycisk 'Połącz z kontem Google' jest klikalny.", 
							"Strona Opcje logowania: przycisk 'Połącz z kontem Google' jest NIE klikalny.", 
							"Strona Opcje logowania nie zawiera przycisku 'Połącz z kontem Google'.");
	}
	
	// We define actions that are responsible for clicking in rozdzial profilu - Biblioteka  //NIEDLUGO DO WYCINANIA
	public static void spBibliotekaB(){
		MyDriver.buttonLink(spBibliotekaB, 
							RunAutoTest.libraryMainPage.length(),
							RunAutoTest.libraryMainPage,							
							"Strona Biblioteki odpowiada.", 
							"Profil: przycisk 'Biblioteka' jest nie klikalny.", 
							"Profil nie zawiera przycisku 'Biblioteka'.");
	}	
	
	// define the method that is responsible for adding Page Object Pattern
	public static void setPageObject () {
		PageFactory.initElements(wd, new EkProfile());
	}

}
