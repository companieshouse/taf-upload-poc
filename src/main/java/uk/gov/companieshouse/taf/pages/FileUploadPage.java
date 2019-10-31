package uk.gov.companieshouse.taf.pages;

import io.magentys.cinnamon.webdriver.Browser;
import io.magentys.cinnamon.webdriver.ReadyState;
import io.magentys.cinnamon.webdriver.conditions.Conditions;
import io.magentys.cinnamon.webdriver.elements.PageElement;
import javax.inject.Inject;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import uk.gov.ch.taf.common.verify.Verify;
import uk.gov.companieshouse.taf.pages.BaseObject;

public class FileUploadPage extends BaseObject {

    /**
     * Ensures the file is not of null value.
     * @param filename filename pathing fed in
     * @return returns the file used
     * @throws InterruptedException interrupts process if error occurs
     */
    public FileUploadPage selectFileToUpload(String filename) throws InterruptedException {
        if (filename != null) {
            uploadFile(filename);
        }
        return this;
    }

    /**
     * Ensures the file array is not of null value.
     * @param filenames filename pathing fed in
     * @return returns the file used
     * @throws InterruptedException interrupts process if error occurs
     */
    public FileUploadPage selectFilesToUpload(String[] filenames) throws InterruptedException {
        if (filenames != null) {
            uploadFiles(filenames);
        }
        return this;
    }

    /**
     * Asserts the success message seen after attachment upload.
     * @return returns the result
     * @throws InterruptedException returns an exception if error occurs
     */
    public FileUploadPage checkSuccessMessage() throws InterruptedException {
        try {
            Verify.areEqual("Upload Successful", successMessage(webDriver).getText());
        } catch (NoSuchElementException ex) {
            Assert.fail("Page not displayed");
        }
        Thread.sleep(5000);
        return this;

    }

    /**
     * Asserts the error message seen after attachment upload relating to File Typing.
     * @return returns the result
     * @throws InterruptedException returns an exception if error occurs
     */
    public FileUploadPage checkFileTypeErrorMessage() throws InterruptedException {
        try {
            Verify.areEqual("Selected file(s) must be a JPG, JPEG, ZIP, "
                    + "GIF, PNG, PDF, DOCX or XLSX", errorMessage(webDriver).getText());
        } catch (NoSuchElementException ex) {
            Assert.fail("Page not displayed");
        }
        Verify.isTrue(continueButton2(webDriver).isEnabled() == false);
        Thread.sleep(5000);
        return this;
    }

    /**
     * Asserts the error message seen after attachment upload relating to Size.
     * @return returns the result
     * @throws InterruptedException returns an exception if error occurs
     */
    public FileUploadPage checkSizeErrorMessage() throws InterruptedException {
        try {
            Verify.areEqual("Selected file(s) must be "
                    + "smaller than 4MB", errorMessage(webDriver).getText());
        } catch (NoSuchElementException ex) {
            Assert.fail("Page not displayed");
        }
        Verify.isTrue(continueButton2(webDriver).isEnabled() == false);
        Thread.sleep(5000);
        return this;
    }

    /**
     * Asserts the error message seen after attachment upload relating to No File Attached.
     * @return returns the result
     * @throws InterruptedException returns an exception if error occurs
     */
    public FileUploadPage checkNoFileErrorMessage() throws InterruptedException {
        try {
            Verify.areEqual("No file(s) chosen, please "
                    + "select some to upload.", errorMessage(webDriver).getText());
        } catch (NoSuchElementException ex) {
            Assert.fail("Page not displayed");
        }
        Verify.isTrue(continueButton2(webDriver).isEnabled() == false);
        Thread.sleep(5000);
        return this;
    }

    public FileUploadPage visit() {
        Browser.open(getSiteUrl());
        return this;
    }


}
