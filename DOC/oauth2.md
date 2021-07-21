# OAuth2鉴权
[官方文档: https://projects.spring.io/spring-security-oauth/docs/oauth2.html](https://projects.spring.io/spring-security-oauth/docs/oauth2.html)

## OAuth 2.0定义了四种授权方式
 * 授权码模式（authorization code）
 * 简化模式（implicit）
 * 密码模式（resource owner password credentials）
 * 客户端模式（client credentials）(主要用于api认证，跟用户无关)

对应 5种 grant_type
 * authorization_code 授权码模式(即先登录获取code,再获取token)
 * implicit 简化模式(在redirect_uri 的Hash传递token; Auth客户端运行在浏览器中,如JS,Flash)
 * password 密码模式(将用户名,密码传过去,直接获取token)
 * refresh_token 刷新access_token
 * client_credentials 客户端模式(无用户,用户向客户端注册,然后客户端以自己的名义向’服务端’获取资源)
 
## spring security oauth2 中的 endpoint
* /oauth/authorize(授权端，授权码模式使用)
* /oauth/token(令牌端，获取 token)
* /oauth/check_token(资源服务器用来校验token)
* /oauth/confirm_access(用户发送确认授权)
* /oauth/error(认证失败)
* /oauth/token_key(如果使用JWT，可以获的公钥用于 token 的验签)



