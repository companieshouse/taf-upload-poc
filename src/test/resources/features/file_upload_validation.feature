@complete
Feature: File upload validation

  Note:
  * The file size limit is 4MB.
  * Supported file types are JPG, JPEG, ZIP, GIF, PNG, PDF, DOCX or XLSX.
  * When a file is uploaded on the file upload screen this calls the file-transfer-api to upload files to AWS S3.

  @TypeError
  Scenario: Upload an unsupported mime type
    Given a limited company is applying to be secure
    When the user uploads a unsupported file
    Then the following file type error message should be displayed

  @NoFileError
  Scenario: Upload no chosen file
    Given a limited company is applying to be secure
    When the user uploads a No file
    And the user clicks upload
    Then the following no file error message should be displayed

  @SizeError
  Scenario Outline: Upload an file that is too large (>4mb)
    Given a limited company is applying to be secure
    When the user uploads a <file_size> file
    Then the following size error message should be displayed

    Examples:
      | file_size  |
      | 4.03MB JPG |
      | 5.98MB ZIP |