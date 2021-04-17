package pageObjects;

import java.util.HashMap;
import java.util.UUID;

import org.openqa.selenium.By;

import reusableComponents.DB_Operations;
import testBase.DriverFactory;
import testBase.TestBase;

public class TaskPageObjects extends TestBase {

	
	By btn_addTask = By.xpath("//button[text()='Add Task']");
	By field_Search = By.id("search_menu");
	By txt_Search = By.xpath("//*[@id='search_menu']//input[@name='search[keywords]']");
	By btn_Search = By.xpath("//*[@id='search_menu']//input[@type='submit']");
	By dd_SelectProjectForNewTaskCreation = By.xpath("//select[@id='form_projects_id']");
	By dd_taskType = By.id("tasks_tasks_type_id");
	By txt_taskName = By.id("tasks_name");
	By dd_taskStatus = By.id("tasks_tasks_status_id");
	By dd_taskPriority = By.id("tasks_tasks_priority_id");
	By dd_taskLabel = By.id("tasks_tasks_label_id");
	By dd_taskCreatedBy = By.id("tasks_created_by");
	By btn_save = By.xpath("//button[@type='submit' and text()='Save']");
	
	private static String TaskName ;
	private DB_Operations dbOps = new DB_Operations();

	public synchronized void createTask(HashMap<String, String> testData) throws Throwable {
		Thread.sleep(2000);  // here try to create Unique Task by appendiung Stamp and make that var at instance level as proivate 
		selectDropDownByVisibleText_custom(DriverFactory.getInstance().getDriver().findElement(dd_SelectProjectForNewTaskCreation), "NewTaskProjectDropDown", testData.get("ProjectToCreateTaskUnder"));
		selectDropDownByVisibleText_custom(DriverFactory.getInstance().getDriver().findElement(dd_taskType), "NewTaskType", testData.get("TaskType"));
		TaskName = UUID.randomUUID().toString();
		sendKeys_custom(DriverFactory.getInstance().getDriver().findElement(txt_taskName), "newTaskName", TaskName);//testData.get("TaskName")
		selectDropDownByVisibleText_custom(DriverFactory.getInstance().getDriver().findElement(dd_taskStatus), "NewTaskStatus", testData.get("TaskStatus"));
		selectDropDownByVisibleText_custom(DriverFactory.getInstance().getDriver().findElement(dd_taskPriority), "NewTaskPriority", testData.get("TaskPriority"));
		selectDropDownByVisibleText_custom(DriverFactory.getInstance().getDriver().findElement(dd_taskLabel), "NewTaskLabel", testData.get("Label"));
		click_custom(DriverFactory.getInstance().getDriver().findElement(btn_save), "NewTaskSaveButton");

	}

	public synchronized void Search_Verify_TaskCreationOnUI(HashMap<String, String> testData) throws Throwable {

		moveToElement_custom(DriverFactory.getInstance().getDriver().findElement(field_Search), "TaskSearchOption");
		sendKeys_custom(DriverFactory.getInstance().getDriver().findElement(txt_Search), "TaskSearchBox", TaskName);//testData.get("TaskName")
		click_custom(DriverFactory.getInstance().getDriver().findElement(btn_Search), "SearchButton");
		
		//table verification
		assertEqualsString_custom(TaskName , getTaskTableCellValueByColumnName("Name"), "TaskNameInTable");//testData.get("TaskName")

	}

	private synchronized String getTaskTableCellValueByColumnName(String columnName) {
		
		String valueXpath = "//table[starts-with(@id, 'itmes_listing')]/tbody/tr/td[count(//table[starts-with(@id, 'itmes_listing')]/thead/tr/th/div[text()='"+columnName+"']/parent::th/preceding-sibling::th)+1]";
		String value = DriverFactory.getInstance().getDriver().findElement(By.xpath(valueXpath)).getText();
		return value;
	}
	
	//verify  Created Task in DB
	public synchronized void VerifyTaskInDB() throws Throwable{
		String sql = "SELECT * FROM `tasks` where name = '"+ TaskName+"'";		
		HashMap<String, String> dbData = dbOps.getSqlResultInMap(sql);
		String TaskNameInDB = dbData.get("name");
		assertEqualsString_custom(TaskName, TaskNameInDB, "DB_Task_Name");
	}
}
