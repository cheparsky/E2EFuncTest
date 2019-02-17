package cheparskyTest.eKFuncTestsWWW;

//connect all necessary standard libraries
import cheparskyTest.utilities.MyDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//define the name of the class - it must be the same name as the file
public class EkProductPage {
	
	//add all the necessary variables
	private static  WebDriver wd;
	private static String product;
	
	//add WebElementy
	@FindBy(xpath = ".//*[@id='section-content']/div[2]/div/div[1]/div/dl/form[1]/dd/input[4]")
	public static WebElement kpDoKoszykaB;
	
	@FindBy(xpath = ".//*[@id='section-content']/div[2]/div/div[1]/div/dl/form[2]/dd/input")
	private static WebElement kpZamawiamB;
	
	@FindBy(xpath = "//*[@id='section-eprasa']/div/div[1]/p/a") 
	private static WebElement kpPokazWszystkieB;
	
	// We define the functions that are responsible for checking product card and adding the product to the basket - process
	public static void runKartaProduktu (WebElement productWE, int urli, String url, String productN) {
		String errMesage = "//*******KARTA PRODUKTU: "+productN+"*******//";
		wd = MyDriver.wd;
		EkProductPage.setPageObject();
		MyDriver.addErrList(errMesage);
		product = productN;
		wd.get(RunAutoTest.mainPage);
		EkMain.kpProductTO(productWE, urli, url, product);
		if(product.equals("Prenumerata(Rzepa)")){ EkProductPage.kpZamawiamB(); } 
		else { EkProductPage.kpDoKoszykaB(); }
		EkLogIn.slLogowanie(RunAutoTest.basketPage.length(),RunAutoTest.basketPage);
		MyDriver.deleteErrList(errMesage);
	}
	
	// We define actions that are responsible for clicking on karcie produktu w przycisk do koszyka
	public static void kpDoKoszykaB (){	
		MyDriver.buttonLink(kpDoKoszykaB, 
							30, 
							"https://www.e-kiosk.pl/secure/", 
							"Karta produktu: przycisk 'do koszyka' jest klikalny", 
							"Karta produktu: przycisk 'do koszyka' jest NIE klikalny",
							"Karta produktu nie zawiera przycisku 'do koszyka'.");
	}
	
	// We define actions that are responsible for clicking on karcie produktu w przycisk zamawiam
	public static void kpZamawiamB (){	
		MyDriver.buttonElement(EkProductPage.kpZamawiamB, 
								EkLogIn.slLoginI, 
								"Karta produktu: przycisk 'zamawiam' jest klikalny", 
								"Karta produktu: przycisk 'zamawiam' jest NIE klikalny",
								"Karta produktu nie zawiera przycisku 'zamawiam'.");
	}
	
	// We define actions that are responsible for clicking on karcie produktu w przycisk pokaz wszystkie (kieruje do numerow archiwalnych)
	public static void kpPokazWszystkieB (){
		MyDriver.buttonLink(kpPokazWszystkieB, 
							RunAutoTest.archivalNumberPartPage.length(), 
							RunAutoTest.archivalNumberPartPage, 
							"Widok numerów archiwalnych odpowiada/działa.", 
							"Karta produktu: przycisk 'pokaż wszystkie' jest nie klikalny.", 
							"Karta produktu nie zawiera przycisku 'pokaż wszystkie'.");
	}
	
	// define the method that is responsible for adding Page Object Pattern
	public static void setPageObject () {
		PageFactory.initElements(wd, new EkProductPage());
	}

}
