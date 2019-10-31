package uk.gov.companieshouse.taf.pages;

import io.magentys.cinnamon.conf.Env;
import io.magentys.cinnamon.webdriver.Browser;
import io.magentys.cinnamon.webdriver.ReadyState;
import io.magentys.cinnamon.webdriver.Timeouts;
import io.magentys.cinnamon.webdriver.conditions.Conditions;
import io.magentys.cinnamon.webdriver.conditions.ElementConditions;
import io.magentys.cinnamon.webdriver.elements.PageElement;

import java.io.File;
import java.net.URISyntaxException;
import javax.inject.Inject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class BaseObject<T> {

    private static final String URI_SEPARATOR = "/";

    @FindBy(id = "submit")
    public PageElement continueButton;

    @FindBy(css = "input[id='fileToUpload']")
    private PageElement fileUploadElement;

    private Env environment;
    protected WebDriver webDriver;

    public static WebElement continueButton2(WebDriver driver) {
        return driver.findElement(By.id("submit"));
    }

    public static WebElement fileUploadElement2(WebDriver driver) {
        return driver.findElement(By.id("fileToUpload"));
    }

    public static WebElement successMessage(WebDriver driver) {
        return driver.findElement(By.cssSelector("#main-content > div > h1"));
    }

    public static WebElement errorMessage(WebDriver driver) {
        return driver.findElement(By.id("file-upload-error"));
    }

    @Inject
    public void setEnvironment(Env environment) {
        this.environment = environment;
    }

    @Inject
    public void setWebDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    protected String getSiteUrl() {
        return environment.config.getString("site-url");
    }

    @SuppressWarnings("unchecked")
    protected T click(PageElement element) {
        element.waitUntil(ElementConditions.displayed, Timeouts.timeoutInSeconds(10)).click();
        return waitForDocumentLoadComplete();
    }

    @SuppressWarnings("unchecked")
    public T waitForDocumentLoadComplete() {
        Browser.waitUntil(Conditions.readyState(ReadyState.COMPLETE));
        return (T) this;
    }

    /**
     * Clicks the Submit button to be able to submit in evidence
     * already attached.
     * @return returns the object of clicking the button
     */
    @SuppressWarnings("unchecked")
    public T clickContinue() {
        //click(continueButton);
        continueButton2(webDriver).click();
        return (T) this;
    }

    private String fileLocationGenerate(String filename) {
        try {
            String fileLocation = new File(getClass()
                    .getResource(URI_SEPARATOR + filename)
                    .toURI())
                    .getPath();

            return fileLocation;
        } catch (Exception exception) {
            throw new RuntimeException("Unable to load file", exception);
        }
    }

    /**
     * Grabs file location and pushes it in the file attachment screen to be able to upload file.
     * @param filename the file's relative position in the project for uploading
     * @throws InterruptedException throws exception if error occurs
     */
    public void uploadFile(String filename) throws InterruptedException {
        String fileLocation = fileLocationGenerate(filename);
        System.out.println(fileLocation);

        //fileUploadElement.sendKeys(fileLocation);
        fileUploadElement2(webDriver).sendKeys(fileLocation);
        Thread.sleep(5000);
    }

    /**
     * Grabs looping file locations and pushes it in the file attachment screen
     * to be able to upload file.
     * @param filenames the array of files relative position in the project for uploading
     * @throws InterruptedException throws exception if error occurs
     */
    public void uploadFiles(String[] filenames) throws InterruptedException {
        for (String file : filenames) {
            String fileLocation = fileLocationGenerate(file);

            System.out.println(fileLocation);
            fileUploadElement2(webDriver).sendKeys(fileLocation);
        }
        Thread.sleep(5000);
    }
}
