# springboot-shiro
一个spring+shiro的小例子
关于用户--角色--权限这块，做了简单的实现
可以实现对登陆用户在访问页面以及方法上的控制。
但是有个问题就是：
如果在shiro配置类里面，
如果给一个页面，roles后增加俩个角色
或者给一个方法，perms后加俩个权限，
就必须该用户都具备(具备所有要求的角色和权限)
解决方法就是在程序启动的时候把角色和权限过滤器放进过滤器链里，应该就可以解决
因为是struts里面就是这样写的。但是springboot内，我还不会加。
希望有大神看到可以帮忙，谢谢。
qq510988896
启动后访问locahost:8080/index即可
账号 fanbin,密码admin
sql语句在resource目录下
这个demo密码没有加盐，只散列一次。
