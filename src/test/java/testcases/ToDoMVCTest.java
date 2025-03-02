package testcases;

import base.TestBase;
import common.Browser;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ToDoMVCPage;

import static common.Browser.click;

public class ToDoMVCTest extends TestBase {
    /**
     * Page Object Model- Todo MVC : Verify user able create a new todo
     * Open browser
     * Navigate to https://todomvc.com/examples/vue/dist/#/
     * Enter a new todo name
     * Verify a todo added
     * + verify name task 1 visible
     * + ALL/Active page
     * + verify counter item left
     * + verify All page:
     */
    ToDoMVCPage toDoMVCPage;

    @BeforeClass
    void setUp() {
        Browser.openBrowser("chrome");
        toDoMVCPage = new ToDoMVCPage();
        toDoMVCPage.open();
    }

    @Test
    void verifyUserAbleCreateNewTask() {
        String taskName = "task 1";
        int itemLeftBefore = toDoMVCPage.getItemLeft();
        toDoMVCPage.createTask(taskName);
        int itemLeftAfter = toDoMVCPage.getItemLeft();

        Assert.assertEquals(itemLeftAfter-itemLeftBefore, 1);

        toDoMVCPage.filterTask("All");
        Assert.assertTrue(toDoMVCPage.isTaskDisplayed(taskName));

        toDoMVCPage.filterTask("Active");
        Assert.assertTrue(toDoMVCPage.isTaskDisplayed(taskName));

        toDoMVCPage.filterTask("Completed");
        Assert.assertFalse(toDoMVCPage.isTaskDisplayed(taskName));
    }

    /**
     * Page Object Model- Todo MVC : Verify user able mark complete a  todo
     * Open browser
     * Navigate to https://todomvc.com/examples/vue/dist/#/
     * Mark completed a exist todo
     * Verify a todo is marked completed on completed, all filter
     */
    @Test
    void verifyUserAbleMarkCompleteTask() {
        String taskName = "task 1";
        toDoMVCPage.filterTask("All");
        toDoMVCPage.createTask(taskName);

        int itemLeftBefore = toDoMVCPage.getItemLeft();
        toDoMVCPage.markComplete(taskName);
//        click(By.xpath("//label[.='task 1]/preceding-sibling::input"));

        int itemLeftAfter = toDoMVCPage.getItemLeft();
        Assert.assertEquals(itemLeftBefore - itemLeftAfter, 1);

        toDoMVCPage.filterTask("All");
        Assert.assertTrue(toDoMVCPage.isTaskDisplayed(taskName));

        toDoMVCPage.filterTask("Completed");
        Assert.assertTrue(toDoMVCPage.isTaskDisplayed(taskName));
    }

    @Test
    void verifyUserAbleDeleteTask() {
        String taskName = "task 1";
        toDoMVCPage.filterTask("All");
        toDoMVCPage.createTask(taskName);

        int itemLeftBefore = toDoMVCPage.getItemLeft();
        toDoMVCPage.markComplete(taskName);

        toDoMVCPage.deleteComplete(taskName);

        int itemLeftAfter = toDoMVCPage.getItemLeft();
        Assert.assertEquals(itemLeftBefore - itemLeftAfter, 1);
    }

    @Test
    void verifyUserAbleChangeTask() {
        String taskName = "task 1";
        toDoMVCPage.filterTask("All");
        toDoMVCPage.createTask(taskName);
        int itemLeftBefore = toDoMVCPage.getItemLeft();

        Assert.assertTrue(toDoMVCPage.isTaskDisplayed(taskName));

        toDoMVCPage.updateTaskName(taskName, "task 2");
        Assert.assertTrue(toDoMVCPage.isTaskDisplayed("task 2"));
        int itemLeftAfter = toDoMVCPage.getItemLeft();
        Assert.assertEquals(itemLeftBefore, itemLeftAfter);
    }
}
