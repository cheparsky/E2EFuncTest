package cheparskyTest.eKFuncTestsWWW;

//connect all necessary standard libraries
import cheparskyTest.utilities.MyDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//define the name of the class - it must be the same name as the file
public class EkSearch {
	
	//add all the necessary variables
	private static WebDriver wd;
	
	//add WebElementy
	@FindBy(id = "searchInput")
	private static WebElement sgWyszukiwarkaI;
	
	@FindBy(xpath = ".//*[@id='all']/header/div[1]/nav/div/form/fieldset/div/button[1]")
	private static WebElement sgWyszukiwarkaB;
	
	@FindBy(xpath = ".//*[@id='all']/section/div/div/div[2]/div/ul/li[1]/p[1]/a[1]")
	private static WebElement sgWyszukiwarkaRzepaB;
	
	@FindBy(className = "main-info")
	public static WebElement kpMainInfo;
	
	// We define the functions that are responsible for checking wyszukiwarki - process
	public static void runWyszukiwarka () {
		String errMesage = "//*******WYSZUKIWARKA*******//";
		wd = MyDriver.wd;
		EkSearch.setPageObject();
		MyDriver.addErrList(errMesage);
		wd.get(RunAutoTest.mainPage);
		EkSearch.sgWyszukiwarkaI();
		EkSearch.sgWyszukiwarkaB();
		if(MyDriver.ifWWWAreEquals(RunAutoTest.searchPage)) {
				EkSearch.sgWyszukiwarkaRzepaB();	
		}
		MyDriver.deleteErrList(errMesage);
	}
	
	// We define the action that is responsible for entering tekstu do wyszukiwarki
	public static void sgWyszukiwarkaI (){
		MyDriver.InputData(sgWyszukiwarkaI, 
							"Rzeczpospolita", 
							"Strona głowna: input 'wyszukiwarki nie jest edytowalny.", 
							"Strona główna nie zawiera inputu wyszukiwarki.");
	}
	
	// We define actions that are responsible for pressing the search button
	public static void sgWyszukiwarkaB (){
		MyDriver.buttonElement(sgWyszukiwarkaB, 
								sgWyszukiwarkaRzepaB, 
								"Wyszukiwarka: przycisk wyszukiwania jest klikalny.", 
								"Wyszukiwarka: przycisk wyszukiwania jest NIE klikalny.", 
								"Górna belka nie zawiera przycisku wyszukiwania.");
	}
	
	// We define actions that are responsible for pressing in okładkę pierwszego wyniku wyszukiwania
	public static void sgWyszukiwarkaRzepaB (){
		MyDriver.buttonElement(sgWyszukiwarkaRzepaB, 
								kpMainInfo, 
								"Cały process wyszukiwania działa.",
								"Wcisniecie w okładkę w wynikach wyszukiwania nie działa.", 
								"Wyniki wyszukiwania są puste.");
	}
	
	// define the method that is responsible for adding Page Object Pattern
	public static void setPageObject () {
		PageFactory.initElements(wd, new EkSearch());
	}

}
