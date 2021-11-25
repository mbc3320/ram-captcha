package top.beanshell.captcha.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import top.beanshell.captcha.common.exception.CaptchaException;
import top.beanshell.captcha.common.exception.code.CaptchaStatusCode;
import top.beanshell.captcha.model.bo.CaptchaImageBO;
import top.beanshell.captcha.model.dto.CaptchaCreateDTO;
import top.beanshell.captcha.model.dto.CaptchaValidDTO;
import top.beanshell.captcha.model.dto.CaptchaViewDTO;
import top.beanshell.captcha.service.CaptchaBaseService;
import top.beanshell.common.service.I18nService;
import top.beanshell.common.utils.IdUtil;

import java.util.concurrent.TimeUnit;

/**
 * 验证码抽象实现
 * @author binchao
 */
public abstract class CaptchaBaseAbstractServiceImpl implements CaptchaBaseService {

    /**
     * 缓存键格式
     * captcha:valid:{id}
     * captcha:valid:1234
     * captcha:valid:1235
     */
    protected static final String CAPTCHA_REDIS_KEY_FORMAT = "captcha:valid:%s";


    @Autowired
    protected I18nService i18nService;

    @Autowired
    protected RedisTemplate<String, Object> redisTemplate;


    @Override
    public boolean valid(CaptchaValidDTO validDTO) {
        Assert.notNull(validDTO, i18nService.getMessage("i18n.request.valid.common.required", "params"));
        Assert.hasText(validDTO.getId(), i18nService.getMessage("i18n.request.valid.common.required", "id"));
        Assert.hasText(validDTO.getCode(), i18nService.getMessage("i18n.request.valid.common.required", "code"));
        String cacheCode = (String) redisTemplate.opsForValue().get(getCacheKey(validDTO.getId()));

        if (!StringUtils.hasText(cacheCode)) {
            throw new CaptchaException(CaptchaStatusCode.CAPTCHA_EXPIRED_OR_IS_NOT_EXIST);
        }

        // 清除缓存
        redisTemplate.delete(getCacheKey(validDTO.getId()));

        if (cacheCode.equalsIgnoreCase(validDTO.getCode())) {
            return true;
        }
        return false;
    }

    @Override
    public CaptchaViewDTO create(CaptchaCreateDTO createDTO) {
        Assert.notNull(createDTO, i18nService.getMessage("i18n.request.valid.common.required", "createDTO"));
        String id = IdUtil.getIdStr();
        CaptchaViewDTO view = CaptchaViewDTO.builder().id(id).build();
        CaptchaImageBO imageBO = CaptchaImageBO(createDTO, view);
        if (null != imageBO) {
            view.setBase64Data(imageBO.getImageBase64());
            // 缓存验证码信息
            setCodeCache(id, imageBO.getImageCode());
            return view;
        }
        return null;
    }

    /**
     * 缓存正确的验证码文本
     * @param id     验证码ID
     * @param code   验证码文本
     */
    protected void setCodeCache(String id, String code) {
        Assert.hasText(id, i18nService.getMessage("i18n.request.valid.common.required", "id"));
        Assert.hasText(code, i18nService.getMessage("i18n.request.valid.common.required", "code"));
        String key = getCacheKey(id);
        // 5分钟内有效
        redisTemplate.opsForValue().set(key, code, 5, TimeUnit.MINUTES);
    }

    /**
     * 获取缓存键
     * @param id  验证码ID
     * @return    键值
     */
    protected String getCacheKey(String id) {
        Assert.notNull(id, i18nService.getMessage("i18n.request.valid.common.required", "id"));
        return String.format(CAPTCHA_REDIS_KEY_FORMAT, id);
    }

    /**
     * 个性化验证码信息
     * @param createDTO  待创建图片参数
     * @param view       验证码信息
     * @return 检验码图片base64文本及文本内容
     */
    protected abstract CaptchaImageBO CaptchaImageBO(CaptchaCreateDTO createDTO, CaptchaViewDTO view);
}
