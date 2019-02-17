package cheparskyTest.eKFuncTestsWWW;

//connect all necessary standard libraries
import java.util.List;

import cheparskyTest.utilities.MyDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//define the name of the class - it must be the same name as the file
public class EkEmptyingBasket {
	
	//add all the necessary variables
	private static WebDriver wd;
	
	//add WebElementy
	@FindBy(xpath = ".//*[@id='all']/section/div/form/ul/li[1]/div/p[4]/a/i")
	private static WebElement okKrzyzykB;
	
	@FindBy(xpath = ".//*[@id='all']/section/div/form/ul/li")
	private static List<WebElement> okListOfProduct;

	// We define the functions that are responsible for checking emptying basket - process
	public static void runOproznanieKoszyka () {
		String errMesage = "//*******OPRÓŻNANIE KOSZYKA*******//";
		wd = MyDriver.wd;
		EkEmptyingBasket.setPageObject();
		MyDriver.addErrList(errMesage);
		EkMain.sgKoszB(RunAutoTest.basketPage); 
		//check the page to which we get and what we adopt the right actions
		if (MyDriver.ifWWWAreEquals(RunAutoTest.loginPage)){
				EkLogIn.slLogowanie(29, "http://www.e-kiosk.pl/secure/");
				EkMain.sgKoszB(RunAutoTest.basketPage);
			}
		else if (MyDriver.ifWWWAreEquals(RunAutoTest.basketPage)) {}
		else { 
			wd.get(RunAutoTest.basketPage); 
			if (MyDriver.isVisible(EkLogIn.slLoginI)){
				EkLogIn.slLogowanie(29, "http://www.e-kiosk.pl/secure/");
				wd.get(RunAutoTest.basketPage);
			}
		}
		//Jezeli krzyzek zamkniecia produktu jest to sprawdzamy ile tych produktow i kasujemy ich wszystkie
		if(MyDriver.isVisible(okKrzyzykB)){
			int countP = okListOfProduct.size();
			for(int i = 1;i<=countP;i++){
				EkEmptyingBasket.okKrzyzykB();
			}
			MyDriver.setWebDriverWait(5);
			if(!MyDriver.isVisible(okKrzyzykB)){ System.out.println("KOSZYK JEST PUSTY - OK"); }
			MyDriver.setWebDriverWait(60);
		}
		else {MyDriver.errMessage("Koszyk nie zawiera żadnego produktu, a musiał zawierać po dodaniu eprasy,ebooka,audiobooka.");}
		MyDriver.deleteErrList(errMesage);
	}
	
	// define actions that are responsible for deleting a product from the basket
	public static void okKrzyzykB (){	
		MyDriver.ButtonTypical(okKrzyzykB, 
								"Koszyk: ikona kasowania produktu z koszyka (krzyżyk) jest klikalny",
								"Koszyk: ikona kasowania produktu z koszyka (krzyżyk) jest NIE klikalny", 
								"Koszyk nie zawiera ikony kasowania produktu z koszyka (krzyżyk).");
	}
	
	// define the method that is responsible for adding Page Object Pattern
	public static void setPageObject () {
		PageFactory.initElements(wd, new EkEmptyingBasket());
	}

}
