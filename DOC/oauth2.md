# OAuth2鉴权
[官方文档: https://projects.spring.io/spring-security-oauth/docs/oauth2.html](https://projects.spring.io/spring-security-oauth/docs/oauth2.html)

## OAuth 2.0定义了四种授权方式
 * 授权码模式（authorization code）
 * 简化模式（implicit）
 * 密码模式（resource owner password credentials）
 * 客户端模式（client credentials）(主要用于api认证，跟用户无关)
 
## spring security oauth2 中的 endpoint
* /oauth/authorize(授权端，授权码模式使用)
* /oauth/token(令牌端，获取 token)
* /oauth/check_token(资源服务器用来校验token)
* /oauth/confirm_access(用户发送确认授权)
* /oauth/error(认证失败)
* /oauth/token_key(如果使用JWT，可以获的公钥用于 token 的验签)



