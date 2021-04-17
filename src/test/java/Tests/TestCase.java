package Tests;

import org.testng.annotations.Test;
import java.util.HashMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.HomePageObjects;
import pageObjects.LoginPageObjects;
import pageObjects.TaskPageObjects;
import reusableComponents.DB_Operations;
import reusableComponents.ExcelOperations;
import testBase.DriverFactory;
import testBase.ExtentFactory;
import testBase.TestBase;

/**
 * @author: Prakash Narkhede
 * @Youtube: https://www.youtube.com/automationtalks
 * @LinkedIn: https://www.linkedin.com/in/panarkhede89/
 */

public class TestCase extends TestBase{
	private LoginPageObjects loginPage = new LoginPageObjects();
	private  HomePageObjects homePage = new HomePageObjects();
	private  TaskPageObjects taskPage = new TaskPageObjects();	
	private   ExcelOperations excel = new ExcelOperations("TaskCreationData");

	@Test(dataProvider = "taskCreationData")
	public void  TaskCreationTest(Object obj1) throws Throwable {
				
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;

		ExtentFactory.getInstance().getExtent().info("Test Data for this execution run is: "+ testData);

		loginPage.login(testData.get("UserName"), testData.get("Password"));
		//check if dashboard page opens
		homePage.checkIfDashBoardPageIsOpened();
		homePage.clickOnSideSubMenu("Tasks", "Add Task");
		//add task
		taskPage.createTask(testData);
		//verify task on UI
		taskPage.Search_Verify_TaskCreationOnUI(testData);

		// verify Created Task in DB
		taskPage.VerifyTaskInDB();

	}

	@Test(dataProvider = "taskCreationData")
	public void  TaskCreationTest_1(Object obj1) throws Throwable {
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;

		ExtentFactory.getInstance().getExtent().info("Test Data for this execution run is: "+ testData);

		loginPage.login(testData.get("UserName"), testData.get("Password"));
		//check if dashboard page opens
		homePage.checkIfDashBoardPageIsOpened();
		homePage.clickOnSideSubMenu("Tasks", "Add Task");
		//add task
		taskPage.createTask(testData);
		//verify task on UI
		taskPage.Search_Verify_TaskCreationOnUI(testData);

		// verify Created Task in DB
		taskPage.VerifyTaskInDB();

	}
	@Test(dataProvider = "taskCreationData")
	public void  TaskCreationTest_2(Object obj1) throws Throwable {
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;

		ExtentFactory.getInstance().getExtent().info("Test Data for this execution run is: "+ testData);

		loginPage.login(testData.get("UserName"), testData.get("Password"));
		//check if dashboard page opens
		homePage.checkIfDashBoardPageIsOpened();
		homePage.clickOnSideSubMenu("Tasks", "Add Task");
		//add task
		taskPage.createTask(testData);
		//verify task on UI
		taskPage.Search_Verify_TaskCreationOnUI(testData);

		// verify Created Task in DB
		taskPage.VerifyTaskInDB();

	}


	//Dataprovider method --> return object array

	@DataProvider (name = "taskCreationData")
	public Object[][] testDataSupplier() throws Exception {
		Object[][] obj = new Object[excel.getRowCount()][1];
		for (int i = 1; i <= excel.getRowCount(); i++) {
			HashMap<String, String> testData = excel.getTestDataInMap(i);
			obj[i-1][0] = testData;
		}
		return obj;

	}
	/*@DataProvider (name = "taskCreationData1")
	public Object[][] testDataSupplier_1() throws Exception {
		Object[][] obj1 = new Object[excel.getRowCount()][1];
		for (int i = 1; i <= excel.getRowCount(); i++) {
			HashMap<String, String> testData1 = excel.getTestDataInMap(i);
			obj1[i-1][0] = testData1;
		}
		return obj1;

	}
	@DataProvider (name = "taskCreationData2")
	public Object[][] testDataSupplier_2() throws Exception {
		Object[][] obj2 = new Object[excel.getRowCount()][1];
		for (int i = 1; i <= excel.getRowCount(); i++) {
			HashMap<String, String> testData2 = excel.getTestDataInMap(i);
			obj2[i-1][0] = testData2;
		}
		return obj2;

	}*/
}
