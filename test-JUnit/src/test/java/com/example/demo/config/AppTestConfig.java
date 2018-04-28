package com.example.demo.config;

import org.springframework.context.annotation.Import;

/**
 * テストを実行するクラスで↓をすることで、
 * @SpringBootTest(classes = { DemoApplication.class, AppTestConfig.class })
 * Beanが、AppTestConfig.class で定義している同名のBeanで上書きされる
 * 
 * 例；AppSubConfig.java 内の testBean2 が、↑で上書きされて、AppTestSubConfig.java の testBean2 の内容になる
 */
@Import(AppTestSubConfig.class)
public class AppTestConfig {
}
