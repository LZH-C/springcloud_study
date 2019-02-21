# springcloud_study
一个学习springcloud的项目

eureka 服务注册中心（微服务的核心，目前我是这样认为的）
有2中模式 1.server模式,用于接受消费者和生产者等服务的注册,并且可以多实例注册同一个服务名,实现高可以用,负载均衡
拓展：可以用于多种服务的高可用实现
2.提供服务的生产者或使用服务的消费者 

#是否向服务注册中心注册自己（默认true） 
eureka.client.register-with-eureka=false
#是否检索服务（默认true）
eureka.client.fetch-registry=false

高可用服务注册中心的概念
考虑到发生故障的情况，服务注册中心发生故障必将会造成整个系统的瘫痪，因此需要保证服务注册中心的高可用。
Eureka Server在设计的时候就考虑了高可用设计，在Eureka服务治理设计中，所有节点既是服务的提供方，也是服务的消费方，服务注册中心也不例外。
Eureka Server的高可用实际上就是将自己做为服务向其他服务注册中心注册自己，这样就可以形成一组互相注册的服务注册中心，以实现服务清单的互相同步，达到高可用的效果。

心跳机制 待加入描述

eureka的消费者之ribbon和feign
ribbon的使用（附加断路器的使用）
1.使用eureka注册到服务中心
2.ribbon的依赖 并注入bean(RestTemplate) 开启负载均衡使用注释@LoadBalanced
3.使用RestTemplate方法调用其他生产者的api 案例：restTemplate.getForObject("http://SERVICE-HI/hi?name="+name,String.class);
4.断路器的是使用 添加
  <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
  </dependency>
在启动器添加注释@EnableHystrix 在service方法体内添加@HystrixCommand(fallbackMethod = "hiError")和对应fallbackMethod方法
启动多个不同端口的实例注册同一个服务名下,测试消费能力和负载均衡能力和断路器的能力

feign和ribbon的使用类似
feign已经内置了断路器 需要在application.properties开启feign.hystrix.enabled=true 
接口方式开启消费,同时可以开启断路器
@FeignClient(value = "service-hi",fallback = SchedualServiceHiHystric.class)
断路器使用需要继承接口实现方法即可

springcolud-zuul
Zuul的主要功能是路由转发和过滤器
路由转发即相当于负载均衡
客户端的请求首先经过负载均衡（zuul、Ngnix），再到达服务网关（zuul集群），然后再到具体的服务
实现：
1.依赖<dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-zuul</artifactId>
        </dependency>
2.开启@EnableZuulProxy
3.继承extends ZuulFilter并实现方法







