package step.learning.services.formparse;

import org.apache.commons.fileupload.FileUploadException;

import javax.servlet.http.HttpServletRequest;

public interface FormParseService {
    FormParseResult parse(HttpServletRequest request) throws FileUploadException;
}
