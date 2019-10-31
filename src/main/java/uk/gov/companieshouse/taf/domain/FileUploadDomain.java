package uk.gov.companieshouse.taf.domain;

import javax.inject.Inject;

import uk.gov.companieshouse.taf.pages.FileUploadPage;

public class FileUploadDomain extends BaseDomain {

    @Inject
    private FileUploadPage fileUploadPage;

    /**
     * Takes filename and passes it to Page level.
     * @param filename the file path with relative location
     * @return the intended outcomes
     * @throws InterruptedException throws exception if error occurs
     */
    public FileUploadDomain uploadEvidence(String filename)
            throws InterruptedException {
        fileUploadPage
                .selectFileToUpload(filename);
        return this;
    }

    public FileUploadDomain uploadMultipleEvidence(String[] filenames)
            throws InterruptedException {
        fileUploadPage.selectFilesToUpload(filenames);
        return this;
    }

    public FileUploadDomain navigateToWebPage() {
        fileUploadPage.visit();
        return this;
    }

    public FileUploadDomain checkConfirmationMessage() throws InterruptedException {
        fileUploadPage.checkSuccessMessage();
        return this;
    }

    public FileUploadDomain checkFileTypeErrorMessage() throws InterruptedException {
        fileUploadPage.checkFileTypeErrorMessage();
        return this;
    }

    public FileUploadDomain checkSizeErrorMessage() throws InterruptedException {
        fileUploadPage.checkSizeErrorMessage();
        return this;
    }

    public FileUploadDomain checkNoFileErrorMessage() throws InterruptedException {
        fileUploadPage.checkNoFileErrorMessage();
        return this;
    }
}
