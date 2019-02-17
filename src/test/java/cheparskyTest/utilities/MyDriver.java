// Definiujemy jak nazywa nasza paczka
package cheparskyTest.utilities;

//connect all necessary standard libraries
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//define the name of the class - it must be the same name as the file
public class MyDriver {
	
	//add all the necessary variables
	public static WebDriver wd;
	public static List<String> errList;
	public static Date currentDate;
	public static String date;
	private static int timeToWait;

	// We define the function that is responsible for connecting the appropriate driver, with the help of which we will test
	public static WebDriver runWDriver (String driver){
	
		if (driver.equals("Firefox")){
			System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
			wd = new FirefoxDriver();
		}
		else{
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver73.exe");
			wd = new ChromeDriver();
			wd.manage().window().maximize();
		}
/*		else if (driver.equals("IE")){
			System.setProperty("webdriver.ie.driver", pathToDriver);
			wd = new InternetExplorerDriver();
		}*/
		return wd;
	}
	
	public static void setWDriver(WebDriver wdriver) { 
		wd = wdriver;
			
	}
	
	// We define functions that are responsible for setting the waiting time for an element
	public static void  setWebDriverWait(int timeSec){
		timeToWait = timeSec;	
	}
	
	// Definiujemy funkcje, ktora sprawdza czy element jest widoczny na stronie czy nie
	public static boolean isVisible(WebElement e) {
	    try { new WebDriverWait(wd, timeToWait).until(ExpectedConditions.visibilityOf(e));
	        boolean result = false;
	        result = e.isDisplayed();
	        return result;
	    } catch (Exception ex) {
	    	//System.out.println(ex); //We only use it when we do not know why the element is not visible
	    	return false;
	    }
	}
	
	// We define the functions with which we develop the hover
	public static void HoverTypical (WebElement element,  //webelement
			String successMsg, //Message when button is clickable
			String errMsg1,   //Message when button is non-clickable
			String errMsg2){	 //Message when is no button

		if(MyDriver.isVisible(element)){
			try{ Actions action = new Actions(wd);
			action.moveToElement(element).perform(); 
			System.out.println(successMsg);}
			catch (Exception e){ MyDriver.errMessage(errMsg1,e); }
		}
		else{ MyDriver.errMessage(errMsg2); }
	}
	
	// Definiujemy funkcje, ktora sporawdza czy przycisk jest i czy jest klikalny
	public static void ButtonTypical (WebElement element,  //webelement
										String successMsg, //Message when button is clickable
										String errMsg1,   //Message when button is non-clickable
										String errMsg2){	 //Message when is no button
		
		if(MyDriver.isVisible(element)){
			try{ element.click(); 
					System.out.println(successMsg);}
			catch (Exception e){ MyDriver.errMessage(errMsg1,e); }
		}
		else{ MyDriver.errMessage(errMsg2); }
	}
	
	// Definiujemy funkcje, ktora sporawdza czy przycisk jest, czy jest klikalny i czy przekierowuje na odpowiednia strone
	public static void buttonLink (WebElement element, //Webelement
									int urli, //ilośc znaków z obecnej strony, która musi odpowiadać do pageTo
									String pageTo,  //Strona do której musi przejść po wciskaniu okładki
									String successMsg, //Widomość w razie przechodzenia do poprawnej strony
									String errMsg1, //Wiadomość w razie nie przechodzenia do poprawnej strony, czyli przycisk nie klikalny
									String errMsg2 ){ //Wiadomość w razie braku przycisku na stronie
		
		if(MyDriver.isVisible(element)){
			try{ 
				element.click();
				Thread.sleep(1000);
				if(wd.getCurrentUrl().substring(0, urli).equals(pageTo)){ System.out.println(successMsg+" Jesteś na stronie: "+wd.getCurrentUrl());  }
				else {	MyDriver.errMessage(errMsg1+" Nadal jesteś na stronie: "+wd.getCurrentUrl()); }
			}
			//catch(WebDriverException wex){ WriteCookiesToFile.ReadCookies(wd); }
			catch(Exception e){ MyDriver.errMessage(e+errMsg1+" Nadal jesteś na stronie: "+wd.getCurrentUrl(),e); }
		}
		else{ MyDriver.errMessage(errMsg2); }
	}
	
	// Definiujemy funkcje, ktora sporawdza czy przycisk jest, czy jest klikalny i czy po kliknieciu jest pokazany docelowy element na stronie
	public static void buttonElement (WebElement element, //Webelement
										WebElement elementCheck,  //Webelement za pomocą którego sprawdzamy czy przycisk działa
										String successMsg, //Widomość w razie jeżeli elementCheck jest na stronie
										String errMsg1, //Wiadomość w razie jeżeli elementCheck nie ma na stronie, czyli przycisk nie klikalny
										String errMsg2 ){ //Wiadomość w razie braku przycisku na stronie
		
		if(MyDriver.isVisible(element)){
			try{ element.click();
				 if(MyDriver.isVisible(elementCheck)){ System.out.println(successMsg); }
				 else {	MyDriver.errMessage(errMsg1+" Nadal jesteś na stronie: "+wd.getCurrentUrl()); }
			}
			//catch(WebDriverException wex){ WriteCookiesToFile.ReadCookies(wd); }
			catch(Exception e){ MyDriver.errMessage(errMsg1+" Nadal jesteś na stronie: "+wd.getCurrentUrl(),e); }
		}
		else{ MyDriver.errMessage(errMsg2); }
	}
	
	// Definiujemy funkcje, ktora sporawdza czy przycisk jest, czy jest klikalny i czy po kliknieciu zniknal docelowy element na stronie
	public static void buttonNoElement (WebElement element, //Webelement
										WebElement elementCheck,  //Webelement za pomocą którego sprawdzamy czy przycisk działa
										String successMsg, //Widomość w razie jeżeli elementCheck jest na stronie
										String errMsg1, //Wiadomość w razie jeżeli elementCheck nie ma na stronie, czyli przycisk nie klikalny
										String errMsg2 ){ //Wiadomość w razie braku przycisku na stronie

		if(MyDriver.isVisible(element)){
			try{ element.click();
			Thread.sleep(3000);
			if(!MyDriver.isVisible(elementCheck)){ System.out.println(successMsg); }
			else {	MyDriver.errMessage(errMsg1); }
			}
			//catch(WebDriverException wex){ WriteCookiesToFile.ReadCookies(wd); }
			catch(Exception e){ MyDriver.errMessage(errMsg1,e); }
		}
		else{ MyDriver.errMessage(errMsg2); }
	}
	
	// Definiujemy funkcje, ktora sporawdza czy przycisk jest, czy jest klikalny i czy po kliknieciu pojawia się ekran z logowaniem do tej SN
	public static void buttonSN (WebElement element,  //webelement
									WebElement	waitElement, //webelement na który czekamy nw oknie od SN
									String mainWindow, //
									String successMsg, //Widomość w razie jeżeli przycisk jest klikalny
									String errMsg1,   //Message when button is non-clickable
									String errMsg2){	 //Message when is no button

		if(MyDriver.isVisible(element)){
			try{ element.click();
					Set<String> allWindows = wd.getWindowHandles();
					String newWindowg = null;
					for(String curWindow : allWindows){
						wd.switchTo().window(curWindow);
						newWindowg = curWindow;
					}
					if(MyDriver.isVisible(waitElement)){
						if(!mainWindow.equals(newWindowg)){ 
							System.out.println(successMsg);
							wd.close();
							wd.switchTo().window(mainWindow); }
						else{ MyDriver.errMessage(errMsg1); }
					}
					else{ MyDriver.errMessage(errMsg1); }
				}
			//catch(WebDriverException wex){ WriteCookiesToFile.ReadCookies(wd); }
			catch(Exception e){ MyDriver.errMessage(errMsg1,e); }
		}
		else{ MyDriver.errMessage(errMsg2); }
	}
	
	// Definiujemy funkcje, ktora sporawdza WebReader: czy przycisk jest, czy jest klikalny i czy po kliknieciu sa zmiany w url
	public static void buttonWR (WebElement element,  //webelement
									String jsScript,  //JavaScript, za pomocą którego klikamy w przyciski WebReader`a
									String successMsg, //Widomość w razie jeżeli przycisk jest klikalny
									String errMsg1,   //Message when button is non-clickable
									String errMsg2){	 //Message when is no button

		JavascriptExecutor jse = (JavascriptExecutor)wd;
		String prevUrl = wd.getCurrentUrl();
		if(MyDriver.isVisible(element)){
			boolean isBWork = false;
			for(int iwt = 0;iwt<=15000;iwt = iwt+500){
				try { Thread.sleep(500);
						if(!wd.getCurrentUrl().equals(prevUrl)){ isBWork=true; break; }
						jse.executeScript(jsScript);
				}
				//catch(WebDriverException wex){ WriteCookiesToFile.ReadCookies(wd); }
				catch (Exception e) { isBWork = false;  }
			}
			if(isBWork){ System.out.println(successMsg); }
			else{ MyDriver.errMessage(errMsg1); }
		}
		else{ MyDriver.errMessage(errMsg2); }
	}
	
	// Definiujemy funkcje, ktora sporawdza czy przycisk jest, czy jest klikalny i czy po kliknieciu jest pobierany plik
	public static void buttonDownload (WebElement element,  //webelement
										String fileName,  //Nazwa pliku
										String typeOfPlik){	 //Nazwa (typu) pliky

		String currentSystemUserName = System.getProperty("user.name");
		if(MyDriver.isVisible(element)){
			String versionOfChrome = null;
			try { element.click();
				System.out.println(typeOfPlik+": przycisk pobierania jest klikalny. Pobieranie zaczyna się.");
				Thread.sleep(5000);
				Capabilities cap = ((RemoteWebDriver) wd).getCapabilities();
				versionOfChrome = cap.getVersion().toString();
				} 
			//catch(WebDriverException wex){ WriteCookiesToFile.ReadCookies(wd); }
			catch (Exception e) { MyDriver.errMessage(typeOfPlik+": przycisk pobierania jest NIE klikalny.",e); }		
			File hfile = new File("C:\\Program Files (x86)\\Google\\Chrome\\Application\\"+ versionOfChrome +"\\"+fileName);
			File hdfile = new File("C:\\Program Files (x86)\\Google\\Chrome\\Application\\"+ versionOfChrome +"\\"+fileName+".crdownload");
			File file = new File("C:\\Users\\"+ currentSystemUserName +"\\Downloads\\"+fileName);
			File dfile = new File("C:\\Users\\"+ currentSystemUserName +"\\Downloads\\"+fileName +".crdownload");
			if(dfile.exists() || file.exists() || hfile.exists() || hdfile.exists()){ System.out.println(typeOfPlik+" pobiera się."); }
			else {MyDriver.errMessage(typeOfPlik+" nie został pobrany."); }
		}
		else{ MyDriver.errMessage(typeOfPlik+" nie zawiera przycisku pobierz."); }	
	}

	// Definiujemy funkcje, ktora sprwdza czy jest input, czy mozna w input wpisac dane i wpisuje dane
	public static void InputData(WebElement element, //Webelement
									String data, // tekst wprowadzany w input i zmianną globalną
									String errMsg1,  // Wiadomość w razie nieedytowania inputru
									String errMsg2){	// Wiadomość w razie braku inputu

		if(MyDriver.isVisible(element)){
			try{
				element.clear();
				element.sendKeys(data);}
			//catch(WebDriverException wex){ WriteCookiesToFile.ReadCookies(wd); }
			catch (Exception e){ MyDriver.errMessage(errMsg1,e); }
		}
		else{ MyDriver.errMessage(errMsg2); }
	}
	
	// Definiujemy funkcje, ktora sprwdza czy jest input, czy mozna w inpucie skasowac dane i kasuje dane
	public static void InputDataClear (WebElement element, //Webelement
									String errMsg1,  // Wiadomość w razie nieedytowania inputru
									String errMsg2){	// Wiadomość w razie braku inputu
				
		if(MyDriver.isVisible(element)){
			try{
				element.clear();}
			//catch(WebDriverException wex){ WriteCookiesToFile.ReadCookies(wd); }
			catch (Exception e){ MyDriver.errMessage(errMsg1,e); }
		}
		else{ MyDriver.errMessage(errMsg2); }
	}
	
	// Definiujemy funkcje, ktora sprawdza czy podany tekst jest taki sam jak tekst na stronie w podanym Label
	public static void Label (WebElement element, //Webelement
								String labelText, //Tekst etykiety
								String errMsg1, //Wiadomość w razie niepoprawnego tekstu etykiety
								String errMsg2){  //Wiadomośc w razie braku etykiety
		
		if(MyDriver.isVisible(element)){
			try{
				if(!element.getText().trim().equals(labelText)) {	MyDriver.errMessage(errMsg1); }
			}
			//catch(WebDriverException wex){ WriteCookiesToFile.ReadCookies(wd); }
			catch(Exception e) { MyDriver.errMessage(errMsg1,e); }
		}
		else{ MyDriver.errMessage(errMsg2); }
	} 
	
	// Definiujemy funkcje, ktora czy jest checkbox, czy jest klikalny i zaznacza go
	public static void CheckboxClick (WebElement element,  //Webelement
									String errMSg1, // Wiadomość w razie niepoprawnego stanu checkbox`u
									String errMSg2,  // Wiadomość w razie nieklikalności checkbox`u
									String errMSg3){  // Wiadomość w razie braku checkbox`u
		
		if(MyDriver.isVisible(element)){
			try{
				if (!element.isSelected()) { element.click();} // jeżeli zaznaczone to = true | niezaznaczone = false
				else { MyDriver.errMessage(errMSg1); }
			}
			//catch(WebDriverException wex){ WriteCookiesToFile.ReadCookies(wd);}
			catch (Exception e){ MyDriver.errMessage(errMSg2,e); }
		}
		else{ MyDriver.errMessage(errMSg3); }
	} 
	
	// Definiujemy funkcje, ktora czy jest checkbox, czy jest klikalny i odznacza go
	public static void CheckboxUnClick (WebElement element,  //Webelement
										String errMSg1, // Wiadomość w razie niepoprawnego stanu checkbox`u
										String errMSg2,  // Wiadomość w razie nieklikalności checkbox`u
										String errMSg3){  // Wiadomość w razie braku checkbox`u

		if(MyDriver.isVisible(element)){
			try{
				if (!element.isSelected()) { element.click();} // jeżeli zaznaczone to = true | niezaznaczone = false
				else { MyDriver.errMessage(errMSg1); }
			}
			//catch(WebDriverException wex){ WriteCookiesToFile.ReadCookies(wd); }
			catch(Exception e){ MyDriver.errMessage(errMSg2,e); }
		}
		else{ MyDriver.errMessage(errMSg3); }
	}
	
	// Definiujemy funkcje, ktora sprawdza czy jest okładka na stronie, czy jest klikalna i czy po klikaniu zostało przekierowanie na inna strone
	public static void titleCover (WebElement element,  //Webelement
									String pageFrom,  //strona na której klikalmy w okładkę, żeby sprawdzić czy przechodzimy na inna stronę
									String successMsg, //Widomość w razie przechodzenia na kartę produktu
									String errMsg1,  //Wiadomość w razie nie przechodzenia na kartę produktu, czyli okładka nie klikalna
									String errMsg2){ //Wiadomość w razie braku okładki na stronie pageFrom
					
		if(MyDriver.isVisible(element)){
			try{
				element.click();
				if(!wd.getCurrentUrl().equals(pageFrom)){
					System.out.println(successMsg); }
				else { MyDriver.errMessage(errMsg1);}
			}
			//catch(WebDriverException wex){ WriteCookiesToFile.ReadCookies(wd); }
			catch (Exception e){ MyDriver.errMessage(errMsg1,e); }
		}
		else{ MyDriver.errMessage(errMsg2); }
	} 
	
	// Definiujemy funkcje, ktora sprawdza czy w informacji o zamowieniu jest numer zamowienia (cyfry)
	public static void isZamowienie (WebElement element){
		String zam = element.getText().trim();
		String numer = "";
		for (int i= 0 ; i<zam.length();i++){
			if(Character.isDigit(zam.charAt(i))) {
				numer = numer + zam.charAt(i);
	        } 
		}
		//System.out.println(numer);
		try{
			int zamowienie = Integer.parseInt(numer);
			System.out.println("Biblioteczka: Zamówienie: "+ zamowienie +" zostało zrealizowane");}
		//catch(WebDriverException wex){ WriteCookiesToFile.ReadCookies(wd); }
		catch(Exception e){MyDriver.errMessage("Biblioteczka: Komunikat o zrealizowanym zamówieniu nie zawiera numery");}
		
	}
	
	// Definiujemy funkcje, ktora dodaje do raportu błedów informację o błędzie oraz błąd po stronie Java
	public static void errMessage(String errMesage, Exception ex){
		System.err.println(errMesage+"\n"+ex);
		currentDate = new Date();
		date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentDate);
		errList.add(date+" - "+errMesage+"\n");
    	String expartone = ex.fillInStackTrace().toString();
    	errList.add(date+" - "+expartone+"\n");
    	String exparttwo = Arrays.toString(ex.getStackTrace());
    	errList.add(date+" - "+exparttwo+"\n");
	}
	
	// Definiujemy funkcje, ktora dodaje do raportu błedów informację o błędzie
	public static void errMessage(String errMesage){
		currentDate = new Date();
		date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentDate);
		errList.add(date+" - "+errMesage+"\n");
		System.err.println(errMesage);
	}
	
	// Definiujemy funkcje, ktora dodaje do raportu błedów informację o jaka to jest funkcjonalnośc
	public static void addErrList(String errMesage){
		currentDate = new Date();
		date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentDate);
		errList.add(date+" - "+errMesage+"\n");
		System.out.println(errMesage);
	}
	
	// Definiujemy funkcje, ktora kasuje z raportu błedów ostatni wpis jezeli on taki sam jak podany (wykorzystywana dal kasowania funkcjonalnosci jaka jest testowana jezeli w niej nie ma bledow)
	public static void deleteErrList(String errMesage){
		try{
			int l = errMesage.length() + 22;
	    	int s = errList.size() -1;
	    	if(errList.get(s).substring(22,l).equals(errMesage)){
	    		errList.remove(s);
	    	}
		}
		catch(Exception e){}
	}
	
	// Definiujemy funkcje, ktora dodaje do raportu bledow dostepnosci Strony info o tym czy jest dotepna strona glowna czy nie i dlaczego
	public static void addMainErrList(String errMainMesage, List<String> errMain){
		currentDate = new Date();
		date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentDate);
		errMain.add(date+" - "+errMainMesage+"\n");
		System.err.println(errMainMesage);
	}
	
	// Definiujemy funkcje, ktora sprawdza czy podana i obecna strony są te same, jeżeli te same to OK
	public static boolean ifWWWAreEquals(String page){
		int pageLength = page.length();
		try{
			if(wd.getCurrentUrl().substring(0, pageLength).equals(page)) { System.out.println("Obecna strona nie różni się od żądanej strony - OK. Jesteś na stronie: "+wd.getCurrentUrl());
																			return true; }
			else { 
				System.out.println("Obecna strona różni się od żądanej strony. Jesteś na stronie: "+wd.getCurrentUrl());
				return false; 
				}
		}
		catch(Exception e) {
			MyDriver.errMessage("Obecna strona '"+wd.getCurrentUrl()+"' różni się ot żądanej strony '"+page+"'.");
			return false;
		}
	}
	
	// Definiujemy funkcje, ktora sprawdza czy podana i obecna strony nie są te same, jeżeli nie te same to OK
	public static boolean ifWWWNoEquals(String page){
		int pageLength = page.length();
		try{
			if(wd.getCurrentUrl().substring(0, pageLength).equals(page)) { System.out.println("Obecna strona nie różni się ot żądanej strony. Jesteś na stronie: "+wd.getCurrentUrl());
																			return false; }
			else { System.out.println("Obecna strona różni się od żądanej strony - OK. Jesteś na stronie: "+wd.getCurrentUrl());
					return true; }
		}
		catch(Exception e) {
			MyDriver.errMessage("Obecna strona '"+wd.getCurrentUrl()+"' różni się od żądanej strony '"+page+"' (OK), ale jest błąd przy sprawdzeniu URL.");
			return true;
		}
	}
	
	// Definiujemy funkcje, ktora wysyła mail z raportem bledow
	public static void sendErrMessages(List<String> errList, String errPlace, final String emailFrom, final String pass, String emailTo){
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(emailFrom,pass); // Tu dane do logowania do konta z ktorego beda wysylane emiale
				}
			});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(emailFrom));// TU ZMIENIAMY ADRES DOSTAWCY
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo)); // TU ZMIENIAMY ADRESY ODBIORCOW
			//message.addRecipients(Message.RecipientType.TO, InternetAddress.parse("cheparsky@gmail.com"));
			message.setSubject("EKIOSK - TESTY AUTOMATYCZNE - BLEDY: "+errPlace);
			String errRaport = "";
			for(int i = 0;i<errList.size();i++){
				errRaport = errRaport + errList.get(i);
	        }
			message.setText(errRaport);

			Transport.send(message);

			System.out.println("Done: Email został wysłany.");

		} catch (MessagingException e) { throw new RuntimeException(e); }
	}
	
	// Definiujemy funkcje, ktora ustawia raport bledow
	public static void setErrList(List<String> RunerrList){
		errList = RunerrList;
	}
}