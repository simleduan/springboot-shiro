package com.chan.config.shiro;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.chan.info.model.UserInfo;
import com.chan.info.service.GetRoleAndPerService;
import com.chan.info.service.UserInfoService;

/**
 * 
 */
public class FanBinRealm extends AuthorizingRealm {
	@Resource
	private UserInfoService userInfoService;

	@Resource
	private GetRoleAndPerService getRoleAndPerService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		UserInfo userinfo = (UserInfo) principalCollection.getPrimaryPrincipal();
		System.out.println("授权用户：" + userinfo.getName());
		System.out.println("执行了授权的方法");
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

		String username = userinfo.getUsername();
		// 假设这是从数据库读取的 权限

		// 从数据库获取用户的角色
		List<Map<String, String>> list = getRoleAndPerService.getRoleByUsername(username);
		System.out.println("我获取到的角色信息值是" + list.toString());
		if(list!=null){
			for (Map<String, String> map : list) {
				if(map!=null){
					info.addRole(map.get("role"));
				}
				
			}
		}

		List<Map<String,String>> list2 = getRoleAndPerService.getPermissionByUsername(username);
		System.out.println("我获取到的权限信息值是" + list2.toString());
		if(list2!=null){
			for (Map<String, String> map : list2) {
				if(map!=null){
					info.addStringPermission(map.get("permission"));
				}
			}
		}

		// info.addStringPermission("person");
		// info.addStringPermission("success");
		// info.addStringPermission("person");
		return info;
	}

	/** 登录认证 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken tokenMsg) {

		System.out.println("执行了认证的方法");
		UsernamePasswordToken token = (UsernamePasswordToken) tokenMsg;// 用户名密码令牌
		String username = token.getUsername();// 得到用户名
		UserInfo userInfo = userInfoService.queryUserInfo(username);// 判断账号是否存在
		if (userInfo == null) {
			System.out.println("认证方法：用户名密码错误");
			return null;
		}
		// 参数1：principal 主角 (用户实体类)
		// 参数2：credentials 密码
		// 参数3：realmName realm的名字 (固定写法 getName() )
		System.out.println("认证方法：登陆成功");
		return new SimpleAuthenticationInfo(userInfo, userInfo.getPassword(), getName());
	}
}
