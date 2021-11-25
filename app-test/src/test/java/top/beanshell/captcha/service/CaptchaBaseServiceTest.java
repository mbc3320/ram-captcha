package top.beanshell.captcha.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import top.beanshell.AppBootstrapTest;
import top.beanshell.captcha.model.dto.CaptchaCreateDTO;
import top.beanshell.captcha.model.dto.CaptchaValidDTO;
import top.beanshell.captcha.model.dto.CaptchaViewDTO;
import top.beanshell.captcha.service.impl.CaptchaSimpleCircleTextServiceImpl;
import top.beanshell.captcha.service.impl.CaptchaSimpleGifTextServiceImpl;
import top.beanshell.captcha.service.impl.CaptchaSimpleLineTextServiceImpl;
import top.beanshell.captcha.service.impl.CaptchaSimpleShearTextServiceImpl;

import javax.annotation.Resource;

public class CaptchaBaseServiceTest extends AppBootstrapTest {

    @Resource
    private ApplicationContext applicationContext;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void simpleLineTextTest() {
        CaptchaBaseService service = applicationContext.getBean("simpleLineTextCaptchaService", CaptchaBaseService.class);
        Assert.assertTrue(service instanceof CaptchaSimpleLineTextServiceImpl);
        captchaFlow(service);
    }

    @Test
    public void simpleCircleTextTest() {
        CaptchaBaseService service = applicationContext.getBean("simpleCircleTextCaptchaService", CaptchaBaseService.class);
        Assert.assertTrue(service instanceof CaptchaSimpleCircleTextServiceImpl);
        captchaFlow(service);
    }

    @Test
    public void simpleShearTextTest() {
        CaptchaBaseService service = applicationContext.getBean("simpleShearTextCaptchaService", CaptchaBaseService.class);
        Assert.assertTrue(service instanceof CaptchaSimpleShearTextServiceImpl);
        captchaFlow(service);
    }

    @Test
    public void simpleGifTextTest() {
        CaptchaBaseService service = applicationContext.getBean("simpleGifTextCaptchaService", CaptchaBaseService.class);
        Assert.assertTrue(service instanceof CaptchaSimpleGifTextServiceImpl);
        captchaFlow(service);
    }

    private void captchaFlow(CaptchaBaseService service) {

        Assert.assertNotNull(service);
        // 生成验证码
        CaptchaCreateDTO createDTO = CaptchaCreateDTO.builder().build();

        CaptchaViewDTO viewDTO = service.create(createDTO);

        Assert.assertNotNull(viewDTO);

        // 验证验证码
        String code = (String) redisTemplate.opsForValue().get("captcha:valid:" + viewDTO.getId());

        CaptchaValidDTO validDTO = CaptchaValidDTO.builder().id(viewDTO.getId()).code(code).build();

        boolean validResult = service.valid(validDTO);

        Assert.assertTrue(validResult);
    }


}
