package com.chan.config.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2016/10/8.
 * 
 *  认证相关
	anon不认证不登陆也可以访问          匿名过滤器
	authc必须登录才能访问
	授权相关
	perms必须有某个权限才可以访问(必须又采购权限才可以访问)
 */
@Configuration
public class ShiroConfiguration {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
    	System.out.println("ShiroConfiguration.shiroFilterFactoryBean我进入了司令部了，你呢？");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        
        
        
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
//        filterChainDefinitionMap.put("/login", "login");加了这句，页面都没有了。
//        filterChainDefinitionMap.put("/logout", "logout");
        
        filterChainDefinitionMap.put("/everyone", "anon");//设置，每一个人，不需要登陆，都可以访问
        filterChainDefinitionMap.put("/403", "anon");
        filterChainDefinitionMap.put("/favicon.ico","anon");
        //此处要注意：授权要配置在anno的下面，认证的上面
        //意思是是，success这个页面，必须要有two这个权限才能访问
        //页面是由角色控制的  
        //页面的控制,你拥有这个角色才能够访问这个页面
        filterChainDefinitionMap.put("/add", "roles[admin,vip]");
        filterChainDefinitionMap.put("/personOption", "roles[admin]");
        filterChainDefinitionMap.put("/addPerson/**", "perms[userinfo:add]");
        //方法的控制,你拥有个权限，你才能访问这个方法
        filterChainDefinitionMap.put("/editAdd", "perms[weihu:add]");
        filterChainDefinitionMap.put("/editDel", "perms[weihu:del]");
        filterChainDefinitionMap.put("/editUp", "perms[weihu:up]");
        filterChainDefinitionMap.put("/editSel", "perms[weihu:sel]");
        
        filterChainDefinitionMap.put("/**", "authc");//所有地址访问都必须经过认证
//        filterChainDefinitionMap.put("/**", "anon");//所有地址访都是匿名的，不做限制
        
        shiroFilterFactoryBean.setLoginUrl("/login");//认证相关：当用户没有登陆就访问资源时，跳转到此页面
        shiroFilterFactoryBean.setSuccessUrl("/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");//授权相关：当用户访问没有权限的资源时，跳转到此页面
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    //安全管理器 shiro的核心组件(相当与军队的司令)   需要注入realm(此处注入自定义的realm)
    @Bean
    public SecurityManager securityManager(FanBinRealm fanBinRealm) {
    	System.out.println("ShiroConfiguration.securityManager我见到司令本人了，你呢？");
        return new DefaultWebSecurityManager(fanBinRealm);
    }

    //自定义的Realm
    @Bean
    public FanBinRealm fanBinRealm(HashedCredentialsMatcher hashedCredentialsMatcher) {
    	System.out.println("ShiroConfiguration.fanBinRealm本司龄要调用FanBinRealm了，你呢？");
        FanBinRealm realm = new FanBinRealm();
        realm.setCredentialsMatcher(hashedCredentialsMatcher); 
        return realm;
    }

	@Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
    	System.out.println("ShiroConfiguration.hashedCredentialsMatcher我正在对密码md5，你呢？");
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        System.out.println("我md5后，matcher的值是---"+matcher);
//        matcher.setHashIterations(2);
        return matcher;
    }
	
}
