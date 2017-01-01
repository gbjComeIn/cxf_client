package com.jacky;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.junit.Test;

import com.jacky.service.GetInfoService;

/**
 * Hello world!
 *
 */
public class App {
 /**
  * 简介：调用方式采用了和RMI类似的机制，即客户端直接服务器端提供的服务接口(interface),
  *       CXF通过运行时代理生成远程服务的代理对象，
  *       在客户端完成对webservice的访问;几个必填的字段：
  *       setAddress-这个就是我们发布webservice时候的地址，保持一致。
      缺点：这种调用service的好处在于调用过程非常简单，就几行代码就完成一个webservice的调用，
  *      但是客户端也必须依赖服务器端的接口，这种调用方式限制是很大的，
  *      要求服务器端的webservice必须是java实现--这样也就失去了使用webservice的意义。
  */
  @Test      
  public void test1(){
	   JaxWsProxyFactoryBean bean = new JaxWsProxyFactoryBean();  
       bean.setServiceClass(GetInfoService.class);  
       bean.setAddress("http://localhost:8080/cxf_demo/cxf/getInfoService");  
       GetInfoService service = (GetInfoService)bean.create();
       int result = service.add(1, 1);
       System.out.println("result====="+result);
       Info info = service.getInfo("宋瑶", 21);
       System.out.println("info==="+info);  
      }
   /**
    * 简介：只要指定服务器端wsdl文件的位置，然后指定要调用的方法和方法的参数即可，不关心服务端的实现方式。
    *    wsdl [Web Services Description Language]网络服务描述语言是Web Service的描述语言，它包含一系列描述某个web service的定义
    * @throws Exception
    */
    @Test
    public void test2() throws Exception {
    	JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();  
        Client client = clientFactory.createClient("http://localhost:8080/cxf_demo/cxf/getInfoService?wsdl"); 
        Object[] objs ={1,2};
        Object[] result = client.invoke("add", objs);  
        System.out.println(result[0]);  
    }
}
