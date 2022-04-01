package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

public class Task1 {
    WebDriver driver;

    @Before
    public void openPage() {

        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/enter_a_number");
    }

    @After
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void errorOnText() {
//        TODO
//        enter a text instead of a number, check that correct error is seen
        String textToEnter = "someText";
        driver.findElement(By.id("numb")).sendKeys(textToEnter);
        driver.findElement(By.tagName("button")).click();
        System.out.println(driver.findElement(By.id("ch1_error")).getText());
        assertEquals("Please enter a number", driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void errorOnNumberTooSmall() {
//        TODO
//        enter number which is too small (below 50), check that correct error is seen
    }

    @Test
    public void errorOnNumberTooBig() {
//        BUG: if I enter number 666 no errors where seen
//        TODO
        String textToEnter = "111";
        driver.findElement(By.id("numb")).sendKeys(textToEnter);
        driver.findElement(By.tagName("button")).click();
        System.out.println(driver.findElement(By.id("ch1_error")).getText());
        assertEquals( "Number is too big", driver.findElement(By.id("ch1_error")).getText());
//        enter number which is too big (above 100), check that correct error is seen
    }

    @Test
    public void correctSquareRootWithoutRemainder() {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 2 is square root of 4),
//        then and press submit and check that correct no error is seen and check that square root is calculated correctly
    int testNumber = 100;
    driver.findElement(By.id("numb")).sendKeys(String.valueOf(testNumber));
    driver.findElement(By.tagName("button")).click();
   // String expectedMessage = String.format(Locale.US, "Square root of %d is %.2f"), testNumber, Math.sqrt(testNumber));


        Alert alert = driver.switchTo().alert();
        String actualMessage = alert.getText();
        alert.accept();

       // assertEquals(expectedMessage, actualMessage);

//        String textFromMessege = "Square root of 64 is 8.00";
//        Alert alert = driver.switchTo().alert();
//        String actualMessage = alert.getText();
//        alert.accept();
    }

    @Test
    public void correctSquareRootWithRemainder() {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 1.732.. is square root of 3) and press submit,
//        then check that correct no error is seen and check that square root is calculated correctly
        for (int i = 50; i <= 100; i++){
            if ((int) Math.sqrt(i) * (int) Math.sqrt(i) == i) {
                System.out.println("testNumber = " + i + "was skipped");
                continue;
            }
            int testChar = i;
            // driver.findElement(By.id("numb")).sendKeys(toString(testChar));
             driver.findElement(By.tagName("button")).click();

            double testNumber = i;
             String expectedMessage =
                     String.format(Locale.US, "Square root of %d is %.2f", testNumber, Math.sqrt(testNumber));

             Alert alert = driver.switchTo().alert();
             String actualMessage = alert.getText();
             alert.accept();

             assertEquals(expectedMessage, actualMessage);

        }
    }
}
