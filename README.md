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

心跳机制
默认情况下，如果Eureka Server在一定时间内（默认90秒）没有接收到某个微服务实例的心跳，Eureka Server将会移除该实例。但是当网络分区故障发生时，微服务与Eureka Server之间无法正常通信，而微服务本身是正常运行的，此时不应该移除这个微服务，所以引入了自我保护机制。
自我保护模式正是一种针对网络异常波动的安全保护措施，使用自我保护模式能使Eureka集群更加的健壮、稳定的运行。
自我保护机制的工作机制是如果在15分钟内超过85%的客户端节点都没有正常的心跳，那么Eureka就认为客户端与注册中心出现了网络故障，Eureka Server自动进入自我保护机制，此时会出现以下几种情况：
1、Eureka Server不再从注册列表中移除因为长时间没收到心跳而应该过期的服务。
2、Eureka Server仍然能够接受新服务的注册和查询请求，但是不会被同步到其它节点上，保证当前节点依然可用。
3、当网络稳定时，当前Eureka Server新的注册信息会被同步到其它节点中。
因此Eureka Server可以很好的应对因网络故障导致部分节点失联的情况，而不会像ZK那样如果有一半不可用的情况会导致整个集群不可用而变成瘫痪。
# 该配置可以移除这种自我保护机制，防止失效的服务也被一直访问 (Spring Cloud默认该配置是 true)
eureka.server.enable-self-preservation: false

# 该配置可以修改检查失效服务的时间，每隔10s检查失效服务，并移除列表 (Spring Cloud默认该配置是 60s)
eureka.server.eviction-interval-timer-in-ms: 10



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







