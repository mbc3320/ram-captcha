package top.beanshell.captcha.service.impl;

import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.img.FontUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.beanshell.captcha.model.bo.CaptchaImageBO;
import top.beanshell.captcha.model.bo.SimpleTextCaptchaConfigBO;
import top.beanshell.captcha.model.dto.CaptchaCreateDTO;
import top.beanshell.captcha.model.dto.CaptchaViewDTO;
import top.beanshell.common.utils.JSON;

import java.awt.*;

/**
 * 带圆圈的简单文本本地图形校验码实现
 * @author binchao
 */
@Slf4j
@Service("simpleCircleTextCaptchaService")
public class CaptchaSimpleCircleTextServiceImpl extends CaptchaBaseAbstractServiceImpl {

    /**
     * 文本源
     */
    private static final String TEXT_SOURCE = "0123456789abcdefghjkmnpqrstuvwxyz";

    @Override
    protected CaptchaImageBO CaptchaImageBO(CaptchaCreateDTO createDTO, CaptchaViewDTO view) {

        SimpleTextCaptchaConfigBO configBO;
        if (StringUtils.hasText(createDTO.getExtJson())) {
            configBO = JSON.parse(createDTO.getExtJson(), SimpleTextCaptchaConfigBO.class);
        } else {
            configBO = new SimpleTextCaptchaConfigBO();
        }

        Integer width = createDTO.getWidth() == null ? 100 : createDTO.getWidth();

        Integer height = createDTO.getHeight() == null ? 36 : createDTO.getHeight();

        Integer codeCount = configBO.getCodeCount() == null ? 5 : configBO.getCodeCount();

        Integer interferingLineCount = configBO.getInterferingLineCount() == null ? 20 : configBO.getInterferingLineCount();

        RandomGenerator randomGenerator = new RandomGenerator(TEXT_SOURCE, codeCount);

        CircleCaptcha circleCaptcha = new CircleCaptcha(width, height, codeCount, interferingLineCount);
        if (StringUtils.hasText(configBO.getFont())) {
            try {
                Font font = FontUtil.createFont(configBO.getFont(), (int) (height * 0.75));
                circleCaptcha.setFont(font);
            } catch (Exception e) {
                log.error("create front error: {}", e.getMessage(), e);
            }
        }

        circleCaptcha.setGenerator(randomGenerator);
        circleCaptcha.createCode();

        CaptchaImageBO imageBO = CaptchaImageBO.builder().imageCode(circleCaptcha.getCode())
                .imageBase64(circleCaptcha.getImageBase64Data()).build();

        return imageBO;
    }
}
