package top.beanshell.captcha.common.exception;

import top.beanshell.captcha.common.exception.code.CaptchaStatusCode;
import top.beanshell.common.exception.BaseException;

/**
 * 状态码业务自定义异常
 * @author binchao
 */
public class CaptchaException extends BaseException {

    public CaptchaException(CaptchaStatusCode status) {
        super(status);
    }

    public CaptchaException(CaptchaStatusCode status, String message) {
        super(status, message);
    }
}
