package top.beanshell.captcha.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 图形验证码图形信息
 * @author binchao
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CaptchaViewDTO implements Serializable {

    /**
     * 图形校验码ID
     */
    private String id;

    /**
     * 图形校验码数据
     */
    private String base64Data;

    /**
     * 扩展信息
     */
    private String extJson;
}
