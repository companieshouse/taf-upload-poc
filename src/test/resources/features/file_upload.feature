@complete
Feature: File upload

  Note:
  * The file size limit is 4MB.7:00
  * Supported file types are JPG, JPEG, ZIP, GIF, PNG, PDF, DOCX or XLSX.
  * When a file is uploaded on the file upload screen this calls the file-transfer-api to upload files to AWS S3.

  @single
  Scenario: Successfully upload a single attachment - Single File
    Given a limited company is applying to be secure
    When the user uploads a PDF file
    And the user clicks upload
    Then the following confirmation screen is displayed

  @compatible
  Scenario Outline: Successfully upload multiple attachments - All supported mime types
    Given a limited company is applying to be secure
    When the user uploads a <mime_type> file
    And the user clicks upload
    Then the following confirmation screen is displayed

    Examples:
      | mime_type          |
      | PDF                |
      | JPEG               |
      | JPG (large 3.85mb) |
      | ZIP                |
      | GIF                |
      | PNG                |
      | DOCX               |
      | XLSX               |

  @atonce
  Scenario: Successfully upload multiple attachments at once - All supported mime types
    Given a limited company is applying to be secure
    When the user uploads multiple files
    And the user clicks upload
    Then the following confirmation screen is displayed