package cheparskyTest.eKFuncTestsWWW;

//connect all necessary standard libraries
import java.io.File;

import cheparskyTest.utilities.MyDriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//define the name of the class - it must be the same name as the file
public class EkLibraryAndReader {
	
	//add all the necessary variables
	private static WebDriver wd;
	private static JavascriptExecutor jse;
	public static String bPlikP = "rz_2017-09-04.mobi"; //TRZEBA UKAZAC NAZWE POBIERANEGO PLIKU EPRASA
	public static String bPlikB = "Wspolpraca.pdf"; //TRZEBA UKAZAC NAZWE POBIERANEGO PLIKU EBOOKU
	public static String bPlikA = "Warszawa - miasto.zip";  //TRZEBA UKAZAC NAZWE POBIERANEGO PLIKU AUDIOBOOKU
	
	//add WebElementy
	@FindBy(className = "icon-download")
	private static WebElement bPobierzB;
	
	@FindBy(xpath = ".//*[@id='all']/section[2]/div/div/div[1]/ul/li[1]/a")
	private static WebElement bePrasaB;
	
	@FindBy(xpath = ".//*[@id='all']/section[2]/div/div/div[1]/ul/li[2]/a")
	private static WebElement beBookiB;
	
	@FindBy(xpath = ".//*[@id='all']/section[2]/div/div/div[1]/ul/li[3]/a")
	private static WebElement bAudiobookiB;
	
	@FindBy(xpath = ".//*[@id='all']/section[2]/div/div/div[1]/ul/li[4]/a")
	private static WebElement bPrenumeratyB;
	
	@FindBy(xpath = ".//*[@id='all']/section[2]/div/div/div[1]/ul/li[5]/a")
	private static WebElement bUlubioneB;
	
	@FindBy(xpath = "//*[@id='all']/section[2]/div/div/div[4]/p/a")
	private static WebElement bPokazWiecejB;
	
	@FindBy(xpath = ".//*[@id='all']/section[2]/div/div/div[4]/div/ul/li[13]/p[1]/a/img")
	private static WebElement bPWOkladkaB;
	
	@FindBy(xpath = ".//*[@id='all']/section[2]/div/div/div[4]/div/ul/li[1]/p[3]/a[1]/i")
	private static WebElement bCzytnikB;
	
	@FindBy(className = "close")
	private static WebElement wrCloseB;
	
	@FindBy(className = "next")
	private static WebElement wrStrzalkaPrawoB;
	
	@FindBy(className = "prev")
	private static WebElement wrStrzalkaLewoB;
	
	// We define the functions that are responsible for checking biblioteki, pobieranie,WebReader - process
	public static void runBiblioteka () {
		String errMesage = "//*******BIBLIOTEKA*******//";
		wd = MyDriver.wd;
		EkLibraryAndReader.setPageObject();
		MyDriver.addErrList(errMesage);
		jse = (JavascriptExecutor)wd;
		//Checking if the library view works
		if(MyDriver.ifWWWNoEquals(RunAutoTest.libraryMainPage)) {
			EkMain.sgUserB();
			EkMain.sgBibliotekaB();
		}
		//Checking the functionality shows more
		EkLibraryAndReader.bPokazWiecejB();
		//If the Chrome browser is a test download of the file
		if(RunAutoTest.browserName.equals("Chrome")){
			EkLibraryAndReader.bPPobierzB();
		}
		jse.executeScript("scroll(0,0)");
		//Checking whether the view bibliotecki - ebooki is presented
		EkLibraryAndReader.bKategoriaB(beBookiB, RunAutoTest.libraryeBookiPage.length(), RunAutoTest.libraryeBookiPage, "eBooki");
		if(MyDriver.ifWWWAreEquals(RunAutoTest.libraryeBookiPage)) {
			//If the Chrome browser is a test download of the file
			if(RunAutoTest.browserName.equals("Chrome")){
				EkLibraryAndReader.bBPobierzB();
			}
		}
		jse.executeScript("scroll(0,0)");
		//Checking whether the view bibliotecki - audiobooki is presented
		EkLibraryAndReader.bKategoriaB(bAudiobookiB, RunAutoTest.libraryAudiobookiPage.length(), RunAutoTest.libraryAudiobookiPage, "Audiobooki");
		if(MyDriver.ifWWWAreEquals(RunAutoTest.libraryAudiobookiPage)) {
			//If the Chrome browser is a test download of the file
			if(RunAutoTest.browserName.equals("Chrome")){
				//EkLibraryAndReader.bAPobierzB(); //na razie nie robimy tego tak jak nie ma co pobierać
			}
		}
		jse.executeScript("scroll(0,0)");
		//Checking whether the view bibliotecki - prenumeraty is presented
		EkLibraryAndReader.bKategoriaB(bPrenumeratyB, RunAutoTest.libraryPrenumeratyPage.length(), RunAutoTest.libraryPrenumeratyPage, "Prenumeraty");
		//Checking whether the view bibliotecki - ulubione is presented
		EkLibraryAndReader.bKategoriaB(bUlubioneB, RunAutoTest.libraryUlubionePage.length(), RunAutoTest.libraryUlubionePage, "Ulubione");
		//Checking whether the view bibliotecki - ebooki is presented
		EkLibraryAndReader.bKategoriaB(bePrasaB, RunAutoTest.libraryePrasaPage.length(), RunAutoTest.libraryePrasaPage, "ePrasa");
		//If page is Biblioteka - ePrasa then check if a Webreader work
		if(MyDriver.ifWWWAreEquals(RunAutoTest.libraryePrasaPage)) {
			String biblioteka = wd.getCurrentUrl();
			EkLibraryAndReader.bCzytnikB();
			if(MyDriver.ifWWWNoEquals(biblioteka) && MyDriver.isVisible(wrStrzalkaPrawoB)){
				EkLibraryAndReader.wrStrzalkaPrawoB();
				EkLibraryAndReader.wrStrzalkaLewoB();
				EkLibraryAndReader.wrCloseB();
			}
		}
		MyDriver.deleteErrList(errMesage);
	}
	
	// define actions that are responsible for clicking categories in the bookcase (eprasa, ebooki, audiobooki...)
	public static void bKategoriaB (WebElement element, int urli, String url, String categoryName){
		MyDriver.buttonLink(element, 
							urli, 
							url, 
							"Biblioteka - widok "+categoryName+" odpowiada.", 
							"Biblioteka: przycisk '"+categoryName+"' nie jest klikalnym.", 
							"Biblioteka nie zawiera przucisku '"+categoryName+"'.");
	}
	
	// We define actions, which is responsible for pressing the button shown in the bookcase
	public static void bPokazWiecejB (){
		MyDriver.buttonElement(bPokazWiecejB, 
								bPWOkladkaB, 
								"Biblioteka - ePrasa: przycisk 'pokaż więcej' jest klikalny", 
								"Biblioteka - ePrasa: przycisk 'pokaż więcej' jest NIE klikalny", 
								"Biblioteka - ePrasa nie zawiera przycisku 'pokaż więcej'.");
	}
	
	// We define the action that is responsible for downloading eprasy
	public static void bPPobierzB (){
		MyDriver.buttonDownload(bPobierzB, 
								bPlikP, 
								"ePrasa");
	}
	
	// We define the action that is responsible for downloading ebooku
	public static void bBPobierzB (){
		MyDriver.buttonDownload(bPobierzB, 
								bPlikB, 
								"eBook");
	}
	
	// We define the action that is responsible for downloading eprasy
	public static void bAPobierzB (){
		String curUrl = wd.getCurrentUrl();
		MyDriver.buttonDownload(bPobierzB, 
								bPlikA, 
								"Audiobook");
		if(MyDriver.ifWWWNoEquals(curUrl)){ wd.navigate().back(); }
	}
	
	// We define actions that are responsible for pressing the button czytaj
	public static void bCzytnikB (){
		MyDriver.buttonElement(bCzytnikB, 
								wrCloseB, 
								"Webreader odpowiada.", 
								"Biblioteka: przycisk 'czytaj' jest NIE klikalny.", 
								"Biblioteka nie zawiera przycisku 'czytaj'.");
		
	}
	
	// We define actions that are responsible for pressing in WebReaderze strzalki w prawo
	public static void wrStrzalkaPrawoB (){
		MyDriver.buttonWR(wrStrzalkaPrawoB, 
							"document.getElementsByClassName(\"next\")[0].click()", 
							"WebReader: strzałka w prawo jest klikalna.", 
							"WebReader: strzałka w prawo NIE jest klikalna.", 
							"WebReader NIE zawiera strzałki w prawo.");
	}
	
	// We define actions that are responsible for pressing in WebReaderze strzalki w lewo
	public static void wrStrzalkaLewoB (){
		MyDriver.buttonWR(wrStrzalkaLewoB, 
							"document.getElementsByClassName(\"prev\")[0].click()", 
							"WebReader: strzałka w lewo jest klikalna.", 
							"WebReader: strzałka w lewo NIE jest klikalna.", 
							"WebReader NIE zawiera strzałki w lewo.");
	}
	
	// We define actions that are responsible for pressing in WebReaderze przycisku zamknij
	public static void wrCloseB (){
		MyDriver.buttonWR(wrCloseB, 
							"document.getElementsByClassName(\"close\")[0].click()", 
							"WebReader: ikona zamknięcia jest klikalna.", 
							"WebReader: ikona zamknięcia NIE jest klikalna.", 
							"WebReader NIE zawiera ikony zamknięcia.");
	}
	
	// We define a function that is responsible for checking if the file is downloaded and deletes it if the file exists
	public static void deleteFile (String fileName){
		
		try{
			Capabilities cap = ((RemoteWebDriver) wd).getCapabilities();
			String un = System.getProperty("user.name");
			String v = cap.getVersion().toString();
			File jfile = new File("C:\\Program Files (x86)\\Google\\Chrome\\Application\\"+v+"\\"+fileName);
			File file = new File("C:\\Users\\"+un+"\\Downloads\\"+fileName);
			boolean del = file.delete();
			boolean delj = jfile.delete();
			if(del){ System.out.println(file+" Został skasowany.");
					}
			else if(delj){ System.out.println(jfile+" Został skasowany.");
							}
			else { MyDriver.errMessage("Plik "+fileName+" NIE Został skasowany."); }
		}
		catch(Exception e){ MyDriver.errMessage("Nie udało się skasować pliku, patrz log:",e); }
	}
	
	// define the method that is responsible for adding Page Object Pattern
	public static void setPageObject () {
		PageFactory.initElements(wd, new EkLibraryAndReader());
	}

}
