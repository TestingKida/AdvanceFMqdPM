package reusableComponents;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import testBase.DriverFactory;
import testBase.TestBase;

public class JavaScriptHelperUtils extends TestBase {

	public static void sendkeysUsingJavaScript(WebElement e , String value){
		
		JavascriptExecutor jse = (JavascriptExecutor)DriverFactory.getInstance().getDriver();
		jse.executeScript("arguments[0].value='"+value+"';", e);
		
	}
}
