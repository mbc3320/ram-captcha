package top.beanshell.captcha.model.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 图形校验码图形及文本信息
 * @author binchao
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CaptchaImageBO implements Serializable {

    /**
     * 图形base64字符串
     */
    private String imageBase64;

    /**
     * 图形文本信息
     */
    private String imageCode;
}
