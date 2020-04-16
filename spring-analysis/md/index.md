源码分析阶段：
先了解森林，再了解重要部件，再次了解细节
森林：ioc，di
重要部件：
ioc：
  ioc 容器初始化：定位，加载，初始化
aop：
事务

细节看列举重要部件对应类的注释和必要图

ioc实现逻辑：注册，加载，获取
## 1工程名介绍

spring 总纲
>org.springframework.context.support.AbstractApplicationContext.refresh
这个方法定义了spring初始化容器全流程，分析spring源码入口和各个模块有机组合关系

spring 重要部件
ioc 类
>org.springframework.context.support.AbstractApplicationContext :初始化容器，多播器，bean注册入口，后置处理器添加
>org.springframework.beans.factory.xml.NamespaceHandler 自定义标签接口
>org.springframework.beans.factory.config.BeanFactoryPostProcessor bean工厂的bean属性处理容器
>org.springframework.beans.factory.config.BeanPostProcessor bean处理器，该处理器可分为前置处理器，后置处理器

aop类

细节
1获取 `bean` 容器
org.springframework.context.support.AbstractApplicationContext#obtainFreshBeanFactory
 >org.springframework.context.support.AbstractRefreshableApplicationContext#refreshBeanFactory

2BanFactory 自定义
通过这个方法，可以对工厂进行定制化设置，让子类进行自由配置
org.springframework.context.support.AbstractRefreshableApplicationContext#customizeBeanFactory
3解析 `schema` 和 `dtd`
org.springframework.beans.factory.xml.ResourceEntityResolver#resolveEntity
4配置文件加载
org.springframework.beans.factory.support.AbstractBeanDefinitionReader#loadBeanDefinitions(java.lang.String, java.util.Set<org.springframework.core.io.Resource>)
5在实例化 `bean` 前，读取 `bean`信息和修改它的属性。
org.springframework.context.support.PostProcessorRegistrationDelegate#invokeBeanFactoryPostProcessors(org.springframework.beans.factory.config.ConfigurableListableBeanFactory, java.util.List<org.springframework.beans.factory.config.BeanFactoryPostProcessor>)
6初始化此上下文的消息源
org.springframework.context.support.AbstractApplicationContext#initMessageSource
自定义一个starter
7 spring标签
  a) 默认标签
    eg： bean，import、alias、bean、 beans等
  b) 自定义标签
    eg: <tx:annotation-driven />,<context:component-scan base-package="xxx.xxx" />等
    实现这个概念的方法在org.springframework.beans.factory.xml.DefaultBeanDefinitionDocumentReader.parseBeanDefinitions
  判断标签是自定义标签和默认标签，是依据找到命名空间 `NamespaceURI` 变量，如果是`http://www.springframework.org/schema/beans`，表示它是默认标签，然后进行默认标签的元素解析，否者使用自定义标签解析。
    @see org.springframework.beans.factory.xml.BeanDefinitionParserDelegate.isDefaultNamespace(java.lang.String)

处理器    
https://blog.csdn.net/weixin_40834464/article/details/82832173?fps=1&locationNum=2
    
    