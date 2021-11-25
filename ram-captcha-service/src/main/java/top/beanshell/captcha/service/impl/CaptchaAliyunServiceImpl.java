package top.beanshell.captcha.service.impl;

import org.springframework.stereotype.Service;
import top.beanshell.captcha.model.dto.CaptchaValidDTO;

/**
 * 阿里云验证码实现
 * @author binchao
 */
@Service("captchaAliyunService")
public class CaptchaAliyunServiceImpl extends CaptchaCloudBaseAbstractServiceImpl{

    @Override
    public boolean valid(CaptchaValidDTO validDTO) {
        // todo
        return false;
    }
}
