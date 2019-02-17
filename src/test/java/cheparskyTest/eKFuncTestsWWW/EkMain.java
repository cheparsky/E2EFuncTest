package cheparskyTest.eKFuncTestsWWW;

//connect all necessary standard libraries
import java.util.ArrayList;
import java.util.List;

//import org.openqa.selenium.Cookie;
import cheparskyTest.utilities.MyDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//define the name of the class - it must be the same name as the file
public class EkMain {
	
	//add all the necessary variables
	private static WebDriver wd;
	public static String mainWindow = "";
	public static boolean stronaGlowna = false;
	public static List<String> errMain  = new ArrayList<String>();
	
	//add WebElementy
	@FindBy(xpath = ".//*[@id='all']/header/div[1]/nav/div/div[1]/p")
	private static WebElement sgUserB; //strona główna User button
	
	@FindBy(linkText = "rejestracja")
	private static WebElement sgRejestracjaB;
	
	@FindBy(linkText = "zaloguj")
	private static WebElement sgZalogujB;
	
	@FindBy(xpath = ".//*[@id='all']/header/div[1]/nav/div/div[2]/p/a")
	private static WebElement sgKoszB;
	
	@FindBy(linkText = "Biblioteka")
	private static WebElement sgBibliotekaB;
	
	@FindBy(linkText = "Profil")
	private static WebElement sgProfilB;
	
	@FindBy(linkText = "Wyloguj")       
	private static WebElement sgWylogujB;
	
	@FindBy(linkText = "Zamknij")
	private static WebElement sgZamknijCookiesB;
	
	@FindBy(linkText = "ePrasa")
	private static WebElement sgEPrasaB;
	
	@FindBy(linkText = "eBooki")
	private static WebElement sgEBookiB;
	
	@FindBy(linkText = "Audiobooki")
	private static WebElement sgAudiobookiB;
	
	@FindBy(xpath = ".//*[@id='all']/header/div[1]/h1/a")
	private static WebElement sgLogoB;
	
	@FindBy(xpath = "//*[@id='section-polecane']/div/div/div/div/ul/div[1]/div/div[1]/li/a")
	public static WebElement sgRzepaB;
	
	@FindBy(xpath = ".//*[@id='section-ebooki']/div[2]/div/div/div/ul/div[1]/div/div[1]/li/p[1]/a")
	public static WebElement sgTopEBookB;
	
	@FindBy(xpath = ".//*[@id='section-group']/section[6]/div/div[2]/div/div/ul/div[1]/div/div[1]/li/p[1]/a")
	public static WebElement sgTopAudiobookB;
	
	@FindBy(linkText = "kontakt")
	private static WebElement sgKontaktB;
	
	@FindBy(linkText = "kody aktywacyjne")
	private static WebElement sgKodyAktywacyjneB;
	
	// We define the functions that are responsible for checking availability of the main page - process
	public static void dostepnoscStrona (){
		String errMesage = "//*******DOSTEPNOSC STRONY: "+RunAutoTest.mainPage+"*******//";
		EkMain.wd = RunAutoTest.wd;
		EkMain.setPageObject();
		MyDriver.addErrList(errMesage);
		int licznik = 0;
		do{
			wd.get(RunAutoTest.mainPage);
			wd.manage().deleteAllCookies();
			//Cookie cook1 = new Cookie("tqsdk_user_closed_1070","1511793345257"); // Wpisujemy tu nazwe i wartosc cookie
			//wd.manage().addCookie(cook1); // Dodajemy tu cookie do strony
			mainWindow = wd.getWindowHandle();
			if(MyDriver.isVisible(sgUserB)){ System.out.println("Strona "+RunAutoTest.mainPage+" odpowiada");
												if(!EkMain.errMain.isEmpty()){ MyDriver.errMessage("Strona "+RunAutoTest.mainPage+" odpowiada"); }
												EkMain.stronaGlowna = true;}
			else{ MyDriver.addMainErrList("Strona "+RunAutoTest.mainPage+" NIE odpowiada", errMain);
					EkMain.stronaGlowna = false; 
					}
			licznik++;
			if(licznik%5==0){
				MyDriver.errMessage("Strona "+RunAutoTest.mainPage+" NIE odpowiada");
				try{
					//if(!EkMain.errMain.isEmpty()){ MyDriver.sendErrMessages(errMain,RunAutoTest.browserName+" Raport dostepnosci","gmailFrom","pass","emailTo"); }
					}
				catch(Exception e){ MyDriver.addMainErrList("Nie ma połączenia internetowego...", errMain); }
			}
		}
		while(!stronaGlowna);
		MyDriver.deleteErrList(errMesage);
	}
	
	// We define the functions that are responsible for checking czy dziala wylogowanie - process
	public static void runWyloguj () {
		EkMain.sgUserB();
		if(MyDriver.isVisible(sgWylogujB)){
			EkMain.sgWylogujB();
		}
		else{ MyDriver.errMessage("Strona główna nie zawiera przycisku 'Wyloguj'."); }
	}
	
	// We define the functions that are responsible for checking przyciskow glownych kategorii i logo na gornej belce - process
	public static void runKategoriiGlowne () {
		String errMesage = "//*******KATEGORII GLOWNE*******//";
		MyDriver.addErrList(errMesage);
		wd.get(RunAutoTest.mainPage);
		EkMain.sgEPrasaB();
		EkMain.sgEBookiB();
		EkMain.sgAudiobookiB();
		EkMain.sgLogoB();
		MyDriver.deleteErrList(errMesage);
	}
	
	// We define the action, which is responsible for checking if a hover appears after pointing the cursor at the guy
	public static void sgUserB(){
		MyDriver.HoverTypical(sgUserB, 
								"Strona główna: ikona ludzika jest klikalna.",
								"Strona główna: ikona ludzika jest NIE klikalna.", 
								"Strona główna nie zawiera ikony ludzika.");
	}
	
	// We define actions that are responsible for clicking a button rejestracja w hoverze
	public static void sgRejestracjaB(){
		MyDriver.buttonLink(sgRejestracjaB, 
							RunAutoTest.registrationPage.length(),
							RunAutoTest.registrationPage, 
							"Strona rejestracji odpowiada.", 
							"Strona główna: przycisk 'rejestracja' jest nie klikalny.", 
							"Strona główna nie zawiera przycisku 'rejestracja'.");				
	}
	
	// We define actions that are responsible for clicking a button zaloguj w hoverze
	public static void sgZalogujB(){
		MyDriver.buttonLink(sgZalogujB, 
							RunAutoTest.loginPage.length(),
							RunAutoTest.loginPage, 
							"Strona logowania odpowiada.", 
							"Strona główna: przycisk 'zaloguj' jest nie klikalny.", 
							"Strona główna nie zawiera ikony przycisku 'zaloguj'.");				
	}
	
	// We define actions that are responsible for clicking a button koszyk
	public static void sgKoszB(String page){
		MyDriver.buttonLink(sgKoszB, 
							page.length(), 
							page, 
							"Strona koszyka odpowiada.", 
							"Strona główna: przycisk 'Koszyka' jest nie klikalny.", 
							"Strona główna nie zawiera przycisku 'Koszyka'.");
		
	}
	
	// We define actions that are responsible for clicking a button biblioteka w hoverze
	public static void sgBibliotekaB(){
		MyDriver.buttonLink(sgBibliotekaB, 
							RunAutoTest.libraryMainPage.length(), 
							RunAutoTest.libraryMainPage, 
							"Strona Biblioteki odpowiada.", 
							"Strona główna: przycisk 'Biblioteki' jest nie klikalny.", 
							"Strona główna nie zawiera przycisku 'Biblioteki'.");
		
	}
	
	// We define actions that are responsible for clicking a button profil w hoverze
	public static void sgProfilB(){
		MyDriver.buttonLink(sgProfilB, 
							RunAutoTest.profileZPage.length(), 
							RunAutoTest.profileZPage, 
							"Strona Profilu odpowiada.", 
							"Strona główna: przycisk 'Profil' jest nie klikalny.", 
							"Strona główna nie zawiera przycisku 'Profil'.");
		
	}
	
	// We define actions that are responsible for clicking a button wyloguj w hoverze
	public static void sgWylogujB(){
		MyDriver.buttonLink(sgWylogujB, 
							RunAutoTest.logOutPage.length(), 
							RunAutoTest.logOutPage, 
							"Wylogowanie działa.", 
							"Strona główna: przycisk 'Wyloguj' jest nie klikalny.", 
							"Strona główna nie zawiera przycisku 'Wyloguj'.");
		
	}
	
	// We define actions that are responsible for clicking a button zamknij na belce informowan ia o cookies
	public static void sgZamknijCookiesB(){
		MyDriver.setWebDriverWait(3);
		MyDriver.buttonNoElement(sgZamknijCookiesB, 
								sgZamknijCookiesB, 
								"Strona główna: cookies-bar jest zamkniety.", 
								"Strona główna: przycisk 'zamknij' na cookies-bar jest nie klikalny.", 
								"Strona główna nie zawiera przycisku 'zamknij'.");
		MyDriver.setWebDriverWait(RunAutoTest.waitTime);
	}
	
	// We define actions that are responsible for clicking a button eprasa na gornej belce
	public static void sgEPrasaB (){
		MyDriver.buttonLink(sgEPrasaB, 
							RunAutoTest.ePrasaPage.length(), 
							RunAutoTest.ePrasaPage, 
							"Widok ePrasa działa, strona odpowiada.", 
							"Górna belka: przycisk 'ePrasa' jest NIE klikalny", 
							"Górna belka nie zawiera przycisku 'ePrasa'.");
		
	}
	
	// We define actions that are responsible for clicking a button ebooki na gornej belce
	public static void sgEBookiB (){
		MyDriver.buttonLink(sgEBookiB, 
							RunAutoTest.eBookiPage.length(), 
							RunAutoTest.eBookiPage, 
							"Widok eBooki działa, strona odpowiada.", 
							"Górna belka: przycisk 'eBooki' jest NIE klikalny", 
							"Górna belka nie zawiera przycisku 'eBooki'.");
	}
	
	// We define actions that are responsible for clicking a button audiobooki na gornej belce
	public static void sgAudiobookiB (){
		MyDriver.buttonLink(sgAudiobookiB, 
							RunAutoTest.AudiobookiPage.length(), 
							RunAutoTest.AudiobookiPage, 
							"Widok Audiobooki działa, strona odpowiada.", 
							"Górna belka: przycisk 'Audiobooki' jest NIE klikalny", 
							"Górna belka nie zawiera przycisku 'Audiobooki'.");
	}
	
	// We define actions that are responsible for clicking on logo na gornej belce
	public static void sgLogoB (){
		MyDriver.buttonLink(sgLogoB, 
							RunAutoTest.mainPage.length(), 
							RunAutoTest.mainPage, "Górna belka: przycisk logo eK jest klikalny", 
							"Górna belka: przycisk logo eK jest NIE klikalny", 
							"Górna belka nie zawiera przycisku logo eK.");
	}
	
	// we will evaluate the action, which is responsible for clicking the cover and redirecting to the product card
	public static void kpProductTO (WebElement productWE, int urli, String url, String productN){
		MyDriver.buttonElement(productWE, 
							EkProductPage.kpDoKoszykaB, 
							"Widok karty produktu "+productN+" odpowiada/działa", 
							"Strona główna: klikanie w okładkę "+productN+" nie działa.", 
							"Strona główna nie zawiera tytułu "+productN+".");
	}
	
	// We define actions that are responsible for clicking a button kontakt na stopce
	public static void sgKontaktB (){
		MyDriver.buttonLink(sgKontaktB, 
							RunAutoTest.contactPage.length(), 
							RunAutoTest.contactPage, 
							"Strona Kontakt odpowiada/działa.", 
							"Strona główna: przycisk 'kontakt' jest NIE klikalny.", 
							"Strona główna nie zawiera przycisku 'kontakt'.");
	}
	
	// We define actions that are responsible for clicking a button kody aktywacyjne na stopce
	public static void sgKodyAktywacyjneB (){
		MyDriver.buttonLink(sgKodyAktywacyjneB, 
							RunAutoTest.activationCodePage.length(), 
							RunAutoTest.activationCodePage, 
							"Strona kodów aktywacyjnych odpowiada/działa.", 
							"Strona główna: przycisk 'kody aktywacyjne' jest NIE klikalny.", 
							"Strona główna nie zawiera przycisku 'kody aktywacyjne'.");
	}
	
	// define the method that is responsible for adding Page Object Pattern
	public static void setPageObject () {
		PageFactory.initElements(wd, new EkMain());
	}

}
