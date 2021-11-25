package top.beanshell.captcha.service.impl;

import cn.hutool.captcha.GifCaptcha;
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
 * Gif简单文本本地图形校验码实现
 * @author binchao
 */
@Slf4j
@Service("simpleGifTextCaptchaService")
public class CaptchaSimpleGifTextServiceImpl extends CaptchaBaseAbstractServiceImpl {

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

        RandomGenerator randomGenerator = new RandomGenerator(TEXT_SOURCE, codeCount);

        GifCaptcha captcha = new GifCaptcha(width, height, codeCount);
        if (StringUtils.hasText(configBO.getFont())) {
            try {
                Font font = FontUtil.createFont(configBO.getFont(), (int) (height * 0.75));
                captcha.setFont(font);
            } catch (Exception e) {
                log.error("create front error: {}", e.getMessage(), e);
            }
        }

        captcha.setGenerator(randomGenerator);
        captcha.createCode();

        CaptchaImageBO imageBO = CaptchaImageBO.builder().imageCode(captcha.getCode())
                .imageBase64(captcha.getImageBase64Data()).build();

        return imageBO;
    }
}
