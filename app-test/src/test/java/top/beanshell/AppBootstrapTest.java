package top.beanshell;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 单元测试启动器
 * @author: binchao
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppBootstrapTest {

    protected static final Long STATIC_ID = 123456L;


    @Test
    public void loadTest() {
        log.info("JUnit Test Start...");
    }

}
