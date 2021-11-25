package top.beanshell.captcha.service.impl;

import top.beanshell.captcha.common.exception.CaptchaException;
import top.beanshell.captcha.common.exception.code.CaptchaStatusCode;
import top.beanshell.captcha.model.dto.CaptchaCreateDTO;
import top.beanshell.captcha.model.dto.CaptchaViewDTO;
import top.beanshell.captcha.service.CaptchaBaseService;


/**
 * 第三方云验证码抽象实现
 * @author binchao
 */
public abstract class CaptchaCloudBaseAbstractServiceImpl implements CaptchaBaseService {

    @Override
    public CaptchaViewDTO create(CaptchaCreateDTO createDTO) {
        // 第三方云验证码不是在本地生成
        throw new CaptchaException(CaptchaStatusCode.CAPTCHA_CREATE_UNSUPPORTED);
    }
}
