package zero.dividend.exception.impl;

import org.springframework.http.HttpStatus;
import zero.dividend.exception.AbstractException;

public class NoCompanyException extends AbstractException {

    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }

    @Override
    public String getMessage() {
        return "존재하지 않은 회사명 입니다.";
    }
}
