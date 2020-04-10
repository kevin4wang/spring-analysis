源码分析阶段：
先了解森林，再了解重要部件，再次了解细节
森林：ioc，di
重要部件：
ioc：
aop：
细节看列举重要部件对应类的注释和必要图



spring 重要部件
>org.springframework.context.support.AbstractApplicationContext :初始化容器，多播器，bean注册入口，后置处理器添加

细节

获取 `bean` 容器
org.springframework.context.support.AbstractApplicationContext#obtainFreshBeanFactory
 >org.springframework.context.support.AbstractRefreshableApplicationContext#refreshBeanFactory

BanFactory 自定义
通过这个方法，可以对工厂进行定制化设置，让子类进行自由配置
org.springframework.context.support.AbstractRefreshableApplicationContext#customizeBeanFactory

解析 `schema` 和 `dtd`
org.springframework.beans.factory.xml.ResourceEntityResolver#resolveEntity

配置文件加载
org.springframework.beans.factory.support.AbstractBeanDefinitionReader#loadBeanDefinitions(java.lang.String, java.util.Set<org.springframework.core.io.Resource>)
在实例化 `bean` 前，读取 `bean`信息和修改它的属性。
org.springframework.context.support.PostProcessorRegistrationDelegate#invokeBeanFactoryPostProcessors(org.springframework.beans.factory.config.ConfigurableListableBeanFactory, java.util.List<org.springframework.beans.factory.config.BeanFactoryPostProcessor>)
初始化此上下文的消息源
org.springframework.context.support.AbstractApplicationContext#initMessageSource
