package top.beanshell.captcha.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 校验验证码参数
 * @author binchao
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CaptchaValidDTO implements Serializable {

    /**
     * 校验码id
     */
    private String id;

    /**
     * 校验码值
     */
    private String code;

    /**
     * 其他扩展参数
     */
    private String extJson;


}
