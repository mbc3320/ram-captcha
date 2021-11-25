package top.beanshell.captcha.model.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * 简单文本图形校验码配置信息
 * @author binchao
 */
@Data
public class SimpleTextCaptchaConfigBO implements Serializable {


    /**
     * 文本字体
     */
    private String font;

    /**
     * 文字个数
     */
    private Integer codeCount;

    /**
     * 干扰线质数
     */
    private Integer interferingLineCount;


}
