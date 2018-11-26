# 简单 IOC 容器
## 概述
实现功能如下：
- 加载 xml 配置文件，遍历其中标签
- 获取标签 id 和 class 属性，加载 class 属性对应的类，并创建 bean
- 将 bean 注册到 bean 容器中

# 简单 AOP 代理
## 概述
AOP 是基于代理模式的，代理模式是实现 AOP 的基础，代理模式分为静态代理、动态代理、cglib代理，Spring AOP 主要用到动态代理和 cglib 代理。
## 通知 Advice
通知定义了要织入目标对象的逻辑
- 前置通知 Before：在目标方法执行前，执行通知
- 后置通知 After：在目标方法执行后，执行通知，无论执行结果如何都执行
- 返回通知 After-returning：在目标方法执行后结束后，如果执行成功，则执行通知
- 异常通知 After-throwing：在目标方法抛出异常后执行通知
- 环绕通知 Around：在目标方法执行前和执行后，都需要执行通知
## 切点 Pointcut
通知定义了在**何时执行通知**，那么切点定义了在何处执行通知。所以切点的作用就是通过匹配规则查找合适的连接点。
## 切面 Aspect
切面包含了通知和切点，通知和切点定义了切面是什么，在何时、何处执行切面逻辑，说完概念，简单的实现 AOP 实现的步骤，这里 AOP 是基于 JDK 动态代理的，只需 3 步即可：
- 1
- 2
- 3
# AOP 和 IOC 协同
在上文中我们实现了一个很简单的 AOP、IOC，且 AOP、IOC 两个模块没有整合到一起。在 IOC 加载 bean 的过程中，AOP 不能实现对 bean 的织入通知。
这个版本包含了如下功能：
- 根据 xml 配置文件加载相关 bean
- 对 BeanPostProcessor 类型的 bean 提供支持
- 对 BeanFactoryAware 类型的 bean 提供支持
- 实现了基于 JDK 动态代理的 AOP 
- 整合了 IOC 和 AOP，使得两者可以协同工作
## BeanFactory 的生命流程
1. BeanFactory 加载 Bean 配置文件，将读到的 Bean 配置封装成 BeanDefinition 对象
2. 将分装好的 BeanDefinition 对象注册到 BeanDefeinition 容器中
3. 注册 BeanPostProcessor 相关实现类到 BeanPostProcessor 容器中
4. BeanFactory 进入就绪状态
5. 外部调用 BeanFactory 的 getBean(String name) 方法，BeanFactory 着手实例化相应 Bean
6. 重复步骤 3 和 4，直至程序退出，BeanFactory 被销毁
## BeanDefeinition 及其他一些类介绍
在详细的介绍 IOC 容器之前，这里先介绍一个实现 IOC 所用到的辅助类，包括 BeanDefinition、BeanReference、PropertyValues、PropertyValue等













