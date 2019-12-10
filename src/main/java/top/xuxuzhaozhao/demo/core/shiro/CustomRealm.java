package top.xuxuzhaozhao.demo.core.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import top.xuxuzhaozhao.demo.domain.User;
import top.xuxuzhaozhao.demo.service.IUserService;
import top.xuxuzhaozhao.demo.service.RolePermService;
import top.xuxuzhaozhao.demo.service.UserRoleService;
import top.xuxuzhaozhao.demo.service.impl.UserServiceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 在Shiro中，最终是通过Realm来获取应用程序中的用户、角色及权限信息的
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RolePermService rolePermService;

    /**
     * 告诉shiro如何根据获取到的用户信息中的密码和盐值来校验密码
     */
    {
        //设置用于匹配密码的CredentialsMatcher
        HashedCredentialsMatcher hashMatcher = new HashedCredentialsMatcher();
        hashMatcher.setHashAlgorithmName("md5");
        hashMatcher.setStoredCredentialsHexEncoded(true);
        hashMatcher.setHashIterations(1024);
        this.setCredentialsMatcher(hashMatcher);
    }

    /**
     *  定义如何获取用户的角色和权限的逻辑，给shiro做权限判断
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }
        User user = (User) getAvailablePrincipal(principals);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(user.getRoles());
        info.setStringPermissions(user.getPerms());
        return info;
    }

    /**
     * 定义如何获取用户信息的业务逻辑，给shiro做登录
     * 检查提交的进行认证的令牌信息
     * 根据令牌信息从数据源(通常为数据库)中获取用户信息
     * 对用户信息进行匹配验证。
     * 验证通过将返回一个封装了用户信息的AuthenticationInfo实例。
     * 验证失败则抛出AuthenticationException异常信息。
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String username = upToken.getUsername();
        if (username == null) {
            throw new AccountException("Null usernames are not allowed by this realm.");
        }
        User userDB = userService.selectBy("username",username);
        if (userDB == null) {
            throw new UnknownAccountException("No account found for admin [" + username + "]");
        }
        //查询用户的角色和权限存到SimpleAuthenticationInfo中，这样在其它地方
        //SecurityUtils.getSubject().getPrincipal()就能拿出用户的所有信息，包括角色和权限
        List<String> roleList = userRoleService.getRolesByUserId(userDB.getId());
        List<String> permList = rolePermService.getPermsByUserId(userDB.getId());
        Set<String> roles = new HashSet(roleList);
        Set<String> perms = new HashSet(permList);
        userDB.setRoles(roles);
        userDB.setPerms(perms);

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userDB, userDB.getPassword(), getName());
        info.setCredentialsSalt(ByteSource.Util.bytes(userDB.getSalt()));
        return info;
    }
}
