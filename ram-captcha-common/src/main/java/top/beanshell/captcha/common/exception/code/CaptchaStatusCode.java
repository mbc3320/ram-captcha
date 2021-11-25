package top.beanshell.captcha.common.exception.code;

import top.beanshell.common.model.enu.EnumCode;

/**
 * 验证码服务状态码
 * 11000~11099
 * @author binchao
 */
public enum CaptchaStatusCode implements EnumCode {

    CAPTCHA_CREATE_UNSUPPORTED(11000, "不支持的创建方式"),
    UNSUPPORTED_CAPTCHA_TYPE(11001, "不支持的验证码服务"),
    CAPTCHA_INPUT_ERROR(11002, "验证码不正确"),
    CAPTCHA_CREATE_FAILED(11003, "验证码创建失败"),
    CAPTCHA_EXPIRED_OR_IS_NOT_EXIST(11004, "验证码已过期或不存在")
    ;

    private Integer code;

    private String text;

    CaptchaStatusCode(Integer code, String text) {
        this.code = code;
        this.text = text;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getText() {
        return text;
    }
}
