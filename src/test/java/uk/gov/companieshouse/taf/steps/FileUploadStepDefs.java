package uk.gov.companieshouse.taf.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.magentys.cinnamon.webdriver.Browser;
import io.magentys.cinnamon.webdriver.elements.PageElement;
import io.magentys.cinnamon.webdriver.support.FindByKey;
import javax.inject.Inject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import uk.gov.ch.taf.common.verify.Verify;
import uk.gov.companieshouse.taf.domain.FileUploadDomain;
import uk.gov.companieshouse.taf.domain.GenericDomain;
import uk.gov.companieshouse.taf.pages.FileUploadPage;

public class FileUploadStepDefs extends BaseStepDefs {

    private static final String SUPPORTED_MIME_PDF = "mimeTypes/PdfTestFile.pdf";
    private static final String SUPPORTED_MIME_JPEG = "MimeTypes/JpegTestFile.jpeg";
    private static final String SUPPORTED_MIME_JPG = "MimeTypes/JpgTestFile3.85mb.jpg";
    private static final String SUPPORTED_MIME_ZIP = "MimeTypes/ZipTestFile.zip";
    private static final String SUPPORTED_MIME_GIF = "MimeTypes/GifTestFile.gif";
    private static final String SUPPORTED_MIME_PNG = "MimeTypes/PngTestFile.png";
    private static final String SUPPORTED_MIME_DOCX = "MimeTypes/DocxTestFile.docx";
    private static final String SUPPORTED_MIME_XLSX = "MimeTypes/XlsxTestFile.xlsx";
    private static final String UNSUPPORTED_MIME_TYPE = "MimeTypes/UnsupportedMime.json";
    private static final String FOUR_MB_EXCEEDING_MAX_FILESIZE = "MimeTypes/JpgTestFile4.03mb.jpg";
    private static final String SIX_MB_EXCEEDING_MAX_FILESIZE = "MimeTypes/ZipTestFile6mb.zip";

    @Inject
    private FileUploadDomain fileUploadDomain;

    @Inject
    private GenericDomain genericDomain;

    /**
     * Opens the WebDriver, navigates the webpage necessary and assert connection.
     */
    @Given("a limited company is applying to be secure")
    public void aaLimitedCompanyIsApplyingToBeSecure() {
        fileUploadDomain.navigateToWebPage();
        //webDriver.navigate().to("http://gateway1-fury1.dev.aws.internal:18519/");
        //assertEquals("Secure POC File Upload (Index)", webDriver.getTitle());
        //System.out.println(test.getText());
    }

    /**
     * Pushes file location into upload box, allowing to upload the file specified.
     * @param mimeType the type of file wanted for the test
     * @throws Throwable throws an error if problem occurs
     */
    @When("the user uploads a ([^\"]*) file")
    public void iiUploadAPdfFile(String mimeType) throws Throwable {
        switch (mimeType) {
            case "PDF":
                fileUploadDomain.uploadEvidence(SUPPORTED_MIME_PDF);
                break;
            case "JPEG":
                fileUploadDomain.uploadEvidence(SUPPORTED_MIME_JPEG);
                break;
            case "JPG (large 3.85mb)":
                fileUploadDomain.uploadEvidence(SUPPORTED_MIME_JPG);
                break;
            case "ZIP":
                fileUploadDomain.uploadEvidence(SUPPORTED_MIME_ZIP);
                break;
            case "GIF":
                fileUploadDomain.uploadEvidence(SUPPORTED_MIME_GIF);
                break;
            case "PNG":
                fileUploadDomain.uploadEvidence(SUPPORTED_MIME_PNG);
                break;
            case "DOCX":
                fileUploadDomain.uploadEvidence(SUPPORTED_MIME_DOCX);
                break;
            case "XLSX":
                fileUploadDomain.uploadEvidence(SUPPORTED_MIME_XLSX);
                break;
            case "unsupported":
                fileUploadDomain.uploadEvidence(UNSUPPORTED_MIME_TYPE);
                break;
            case "4.03MB JPG":
                fileUploadDomain.uploadEvidence(FOUR_MB_EXCEEDING_MAX_FILESIZE);
                break;
            case "5.98MB ZIP":
                fileUploadDomain.uploadEvidence(SIX_MB_EXCEEDING_MAX_FILESIZE);
                break;
            case "No":
                break;
            default:
                Verify.fail("Invalid mime type: " + mimeType
                        + " specified in the feature file.");
        }
    }

    @And("the user clicks upload")
    public void iiClickUpload() {
        fileUploadDomain.clickContinue();
    }

    @Then("A confirmation screen is displayed")
    public void aaConfirmationScreenIsDisplayed() throws Throwable {
        fileUploadDomain.checkSuccessMessage();
    }

    /**
     * Pushes multiple file locations into upload box, allowing to upload the file specified.
     * @throws Throwable throws an error if problem occurs
     */
    @When("I upload multiple files")
    public void iiUploadMultipleFiles() throws Throwable {
        String[] fileArray = {SUPPORTED_MIME_PDF, SUPPORTED_MIME_JPEG,
                SUPPORTED_MIME_JPG, SUPPORTED_MIME_ZIP,
                SUPPORTED_MIME_GIF, SUPPORTED_MIME_PNG,
                SUPPORTED_MIME_DOCX, SUPPORTED_MIME_XLSX};
        fileUploadDomain.uploadMultipleEvidence(fileArray);
    }

    @Then("the following file type error message should be displayed")
    public void theFollowingFileTypeErrorMessageShouldBeDisplayed() throws Throwable {
        fileUploadDomain.checkFileTypeErrorMessage();
    }

    @Then("the following size error message should be displayed")
    public void theFollowingSizeErrorMessageShouldBeDisplayed() throws Throwable {
        fileUploadDomain.checkSizeErrorMessage();
    }

    @Then("the following no file error message should be displayed")
    public void theFollowingNoFileErrorMessageShouldBeDisplayed() throws Throwable {
        fileUploadDomain.checkNoFileErrorMessage();
    }

    @When("I navigate back")
    public void iiNavigateBack() {
        genericDomain.clickBrowserBack();
    }
}
