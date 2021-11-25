package top.beanshell.captcha.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 创建检验码参数
 * @author binchao
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CaptchaCreateDTO implements Serializable {


    /**
     * 宽
     */
    private Integer width;

    /**
     * 高
     */
    private Integer height;

    /**
     * 其他初始化参数
     */
    private String extJson;
}
