package top.beanshell.captcha.service;

import top.beanshell.captcha.model.dto.CaptchaCreateDTO;
import top.beanshell.captcha.model.dto.CaptchaValidDTO;
import top.beanshell.captcha.model.dto.CaptchaViewDTO;

/**
 * 验证码服务
 * @author binchao
 */
public interface CaptchaBaseService {

    /**
     * 校验图形校验码
     * @param validDTO  验证码校验信息
     * @return  true/false
     */
    boolean valid(CaptchaValidDTO validDTO);

    /**
     * 创建图形检验码
     * @param createDTO  初始化参数
     * @return           验证码信息
     */
    CaptchaViewDTO create(CaptchaCreateDTO createDTO);
}
