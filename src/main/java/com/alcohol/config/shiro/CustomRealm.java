package com.alcohol.config.shiro;

import com.alcohol.mapper.RoleMapper;
import com.alcohol.mapper.UseraccountMapper;
import com.alcohol.pojo.Role;
import com.alcohol.pojo.Useraccount;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component
public class CustomRealm extends AuthorizingRealm {


	@Resource
	private UseraccountMapper useraccountMapper;
	@Resource
	private RoleMapper roleMapper;
	//获取角色和权限
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		// TODO Auto-generated method stub

		//1.从主体传过来的认证信息中，获得用户名
		String username = (String) arg0.getPrimaryPrincipal();
		Set<String> roles = getRolesByUserName(username);

		//Set<String> permissions = getPerissionsByuserName(username);
		
		 SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		// simpleAuthorizationInfo.setStringPermissions(permissions);
		 simpleAuthorizationInfo.addRoles(roles);	
		return simpleAuthorizationInfo;
	}

	private Set<String> getPerissionsByuserName(String username) {
		Set<String> perissions = new HashSet<>(12);
		perissions.add("user:delete");
		perissions.add("user:udpate");
		
		return perissions;
	}

	private Set<String> getRolesByUserName(String username) {
		// TODO Auto-generated method stub
		List<Role> list= roleMapper.queryRolesByUserName(username);
		Set<Role> roles =new HashSet<>(list);
		Set<String> permissions=new HashSet<>();
		if(roles.size()>0) {
			for(Role role : roles) {
				permissions.add(role.getRoleName());
			}
		}
		return permissions;
	}

	//获取对象,认证登陆
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {

		//1.从主体传过来的认证信息中，获得用户名
		String username = (String) arg0.getPrincipal();
		//2.通过用户名到数据库中获取凭证
		String password = getpasswordByUserName(username);
		
		if(password == null) {
			return null;
		}
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, password,"customRealm");

		//加盐 密码和用户名加密后的密码
		authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(username));

		return authenticationInfo;
	}
	private String getpasswordByUserName(String username) {
		//创建用户账号对象
		Useraccount useraccount = useraccountMapper.getUserByUserName(username);
		// TODO Auto-generated method stub
		if(useraccount == null) {
			return null;
		}
		return useraccount.getPassword();
	}
	
	public static void main(String[] args) {
		Md5Hash md5 = new Md5Hash("123123","Mark");
		System.out.println(md5.toString());
	}


}
