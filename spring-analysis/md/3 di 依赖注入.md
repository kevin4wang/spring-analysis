di依赖注入
当ioc容器通过定位，载入，注册之后，把资源文件转为BeanDefinition对象，该对象集合存在DefaultListableBeanFactory.beanDefinitionMap中.
相当于业务系统需要的对象class信息存到了beanDefinitionMap，但是还没有创建对象给业务系统用，这时根据下面两个条件进行创建对象：
1第一次通过getBean方法向IoC容器获取对应Bean时，IoC容器触发依赖注入。
2在定义Bean资源文件中为<Bean>元素配置了lazy-init属性，让ioc容器在解析注册Bean定义时进行预实例化，触发依赖注入。

依赖注入
  入口：org.springframework.beans.factory.support.AbstractBeanFactory.getBean(java.lang.String)
  具体实现：org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean
doGetBean方法简述：
  如果Bean定义的单态模式(Singleton)，则容器在创建之前先从缓存中查找，以确保整个容器中只存在一个实例对象。
  如果Bean定义的是原型模式(Prototype)，则容器每次都会创建一个新的实例对象。
  除此之外，Bean定义还可以扩展为指定其生命周期范围。
  
依赖注入调用链：
>org.springframework.beans.factory.support.AbstractBeanFactory.getBean(java.lang.String)
 >org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean
  >org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(String,RootBeanDefinition, Object[])
    >org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance:生成Bean所包含的java对象实例
      >org.springframework.beans.factory.support.SimpleInstantiationStrategy.instantiate(RootBeanDefinition,String,BeanFactory):使用默认的无参构造方法创建Bean实例化对象
        >org.springframework.beans.BeanUtils.instantiateClass(Constructor<T>, Object...):通过反射机制调用空构造器newInstance(arg)来进行实例化
        >org.springframework.beans.factory.support.CglibSubclassingInstantiationStrategy.instantiateWithMethodInjection:使用CGLIB进行Bean对象实例化
    >org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.populateBean:对Bean属性的依赖注入进行处理
    
判断FactoryBean对象的地方
org.springframework.beans.factory.support.AbstractBeanFactory.getObjectForBeanInstance
后置处理器的属性修改：
org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.populateBean
对bean配置了lazy-init属性的Bean进行预实例化处理
org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization
PostConstruct实现：

Autowired注解实现的地方：
>org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.populateBean
 >org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.autowireByName

实现InitializingBean接口的地方:
>org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.invokeInitMethods
 >org.springframework.beans.factory.InitializingBean.afterPropertiesSet

Spring IoC容器提供了两种管理Bean依赖关系的方式：
1显式管理：通过配置文件bean标签内定义依赖关系
2Autowired:Spring IoC容器的依赖自动装配功能,IoC容器会自动使用反射查找属性的类型和名称，然后基于属性的类型或者名称来自动匹配容器中管理的Bean，从而自动地完成依赖注入。






