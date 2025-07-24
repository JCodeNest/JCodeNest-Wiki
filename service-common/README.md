# Service-Common 通用服务模块

## 概述

service-common 是 JCodeNest-Wiki 项目的通用服务模块，提供公共工具类、统一响应格式、自定义异常、错误码、异常处理、常量定义等基础功能。

## 功能特性

### 🔧 核心功能

- **统一响应格式** - 标准化的API响应结构
- **全局异常处理** - 统一的异常处理机制
- **业务枚举定义** - 完整的业务状态枚举
- **常量定义** - 系统级常量管理
- **工具类库** - 企业级工具类集合

### 📦 模块结构

```
service-common/
├── src/main/java/cn/jcodenest/wiki/common/
│   ├── annotation/         # 自定义注解
│   ├── constant/           # 常量定义
│   ├── enums/              # 枚举定义
│   ├── exception/          # 异常处理
│   ├── response/           # 统一响应格式
│   ├── utils/              # 工具类库
│   └── config/             # 配置类
├── src/main/resources/
│   ├── application.yml     # 配置文件
└── └── META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports  # 自动配置
```

## 快速开始

### 1. 添加依赖

在需要的微服务 POM XML 中添加以下依赖：

```xml
<dependency>
    <groupId>cn.jcodenest.wiki</groupId>
    <artifactId>service-common</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

### 2. 自动配置

模块支持 Spring Boot 自动配置，无需手动配置即可使用。

### 3. 使用示例

#### 统一响应格式

```java
@RestController
public class UserController {
    
    @GetMapping("/users/{id}")
    public Result<User> getUser(@PathVariable Long id) {
        User user = userService.getById(id);
        return Result.success(user);
    }
    
    @PostMapping("/users")
    public Result<Void> createUser(@RequestBody User user) {
        userService.create(user);
        return Result.success("用户创建成功");
    }
}
```

#### 业务异常处理

```java
@Service
public class UserService {
    
    public User getById(Long id) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw BusinessException.of(ErrorCode.USER_NOT_FOUND);
        }
        return user;
    }
}
```

#### 工具类使用

```java
// JSON工具类
String json = JsonUtils.toJsonString(object);
User user = JsonUtils.parseObject(json, User.class);

// 字符串工具类
String uuid = StringUtils.uuid();
String masked = StringUtils.maskMobile("13812345678");

// 日期工具类
String formatted = DateUtils.format(LocalDateTime.now());
boolean isToday = DateUtils.isToday(someDateTime);

// 加密工具类
String encrypted = EncryptUtils.aesEncrypt("password");
String hashed = EncryptUtils.bcryptEncode("password");
```

## 详细功能

### 统一响应格式

#### Result<T> 响应结果

```java
// 成功响应
Result.success();                    // 无数据成功响应
Result.success(data);                // 带数据成功响应
Result.success("操作成功", data);     // 自定义消息成功响应

// 失败响应
Result.error(ErrorCode.USER_NOT_FOUND);           // 使用错误码
Result.error(1001, "用户不存在");                   // 自定义错误码和消息
```

#### PageResult<T> 分页响应

```java
PageResult<User> pageResult = PageResult.of(userList, total, current, size);
return Result.success(pageResult);
```

### 异常处理体系

#### 异常类型

- **BaseException** - 基础异常类
- **BusinessException** - 业务异常
- **SystemException** - 系统异常
- **ValidationException** - 参数校验异常

#### 全局异常处理

系统自动处理以下异常：
- 业务异常 → 400 Bad Request
- 系统异常 → 500 Internal Server Error
- 参数校验异常 → 422 Unprocessable Entity
- 其他Spring异常 → 对应HTTP状态码

### 业务枚举

#### 用户状态枚举

```java
UserStatusEnum.NORMAL              // 正常
UserStatusEnum.DISABLED            // 禁用
UserStatusEnum.PENDING_ACTIVATION  // 待激活
```

#### 内容状态枚举

```java
ContentStatusEnum.DRAFT         // 草稿
ContentStatusEnum.PUBLISHED     // 已发布
ContentStatusEnum.UNDER_REVIEW  // 审核中
```

#### 订单状态枚举

```java
OrderStatusEnum.PENDING_PAYMENT // 待支付
OrderStatusEnum.PAID            // 已支付
OrderStatusEnum.CANCELLED       // 已取消
```

### 工具类库

#### JsonUtils - JSON处理

- `toJsonString(object)` - 对象转JSON字符串
- `parseObject(json, clazz)` - JSON转对象
- `parseArray(json, clazz)` - JSON转列表
- `isValidJson(json)` - 验证JSON格式

#### StringUtils - 字符串处理

- `randomString(length)` - 生成随机字符串
- `uuid()` - 生成UUID
- `camelToUnderscore(str)` - 驼峰转下划线
- `maskMobile(mobile)` - 手机号掩码
- `maskEmail(email)` - 邮箱掩码

#### DateUtils - 日期时间处理

- `now()` - 获取当前时间
- `format(dateTime)` - 格式化日期时间
- `parseDateTime(str)` - 解析日期时间字符串
- `daysBetween(start, end)` - 计算天数差
- `isToday(dateTime)` - 判断是否今天

#### EncryptUtils - 加密解密

- `md5(data)` - MD5加密
- `sha256(data)` - SHA256加密
- `bcryptEncode(password)` - BCrypt密码加密
- `bcryptMatches(raw, encoded)` - BCrypt密码验证
- `aesEncrypt(data)` - AES加密
- `aesDecrypt(encrypted)` - AES解密

#### FileUtils - 文件操作

- `getFileExtension(fileName)` - 获取文件扩展名
- `isImageFile(fileName)` - 判断是否为图片文件
- `formatFileSize(size)` - 格式化文件大小
- `calculateMD5(file)` - 计算文件MD5值
- `copyFile(source, target)` - 复制文件
- `readFileToString(path)` - 读取文件内容

#### HttpUtils - HTTP请求

- `get(url)` - 发送GET请求
- `post(url, body)` - 发送POST请求
- `put(url, body)` - 发送PUT请求
- `delete(url)` - 发送DELETE请求
- `getClientIpAddress(request)` - 获取客户端IP
- `getUserAgent(request)` - 获取用户代理

#### ValidationUtils - 参数校验

- `notNull(value, paramName)` - 校验非空
- `notBlank(value, paramName)` - 校验非空白
- `length(value, min, max, paramName)` - 校验长度
- `email(email, paramName)` - 校验邮箱格式
- `mobile(mobile, paramName)` - 校验手机号格式
- `range(value, min, max, paramName)` - 校验数值范围

#### BeanUtils - Bean操作

- `copyPropertiesIgnoreNull(source, target)` - 复制属性（忽略null）
- `copyProperties(source, targetClass)` - 复制并返回新对象
- `copyList(sourceList, targetClass)` - 复制对象列表
- `beanToMap(obj)` - 对象转Map
- `mapToBean(map, targetClass)` - Map转对象
- `getFieldValue(obj, fieldName)` - 获取字段值

### 常量定义

#### CommonConstants - 通用常量

```java
CommonConstants.System.SYSTEM_NAME     // 系统名称
CommonConstants.Number.ZERO            // 数字常量
CommonConstants.String.EMPTY           // 字符串常量
CommonConstants.Status.ENABLED         // 状态常量
```

#### RedisConstants - Redis常量

```java
RedisConstants.UserKey.USER_INFO       // 用户信息缓存键
RedisConstants.ExpireTime.ONE_HOUR     // 过期时间常量
```

#### SecurityConstants - 安全常量

```java
SecurityConstants.Token.TOKEN_HEADER   // Token请求头
SecurityConstants.Password.MIN_LENGTH  // 密码最小长度
```

### 自定义注解

#### @ApiLog - 接口日志注解

```java
@ApiLog(value = "用户登录", module = "用户管理", operationType = "LOGIN")
@PostMapping("/login")
public Result<String> login(@RequestBody LoginRequest request) {
    // 登录逻辑
}
```

#### @RateLimit - 限流注解

```java
@RateLimit(count = 10, period = 60, limitType = RateLimit.LimitType.IP)
@GetMapping("/api/data")
public Result<List<Data>> getData() {
    // 接口逻辑
}
```

#### @ValidateParam - 参数校验注解

```java
public Result<Void> updateUser(
    @ValidateParam(type = ValidationType.EMAIL, message = "邮箱格式错误")
    String email,
    @ValidateParam(minLength = 6, maxLength = 20, message = "密码长度6-20位")
    String password) {
    // 更新逻辑
}
```

### 错误码体系

#### ErrorCode - 错误码枚举

```java
// 系统级错误码 (0-999)
ErrorCode.SUCCESS                   // 操作成功
ErrorCode.SYSTEM_ERROR              // 系统内部错误
ErrorCode.DATABASE_ERROR            // 数据库操作失败

// 用户相关错误码 (2000-2099)
ErrorCode.USER_NOT_FOUND            // 用户不存在
ErrorCode.PASSWORD_ERROR            // 密码错误
ErrorCode.TOKEN_EXPIRED             // 令牌已过期

// 业务相关错误码 (3000+)
ErrorCode.CONTENT_NOT_FOUND         // 内容不存在
ErrorCode.ORDER_STATUS_ERROR        // 订单状态错误
ErrorCode.FILE_UPLOAD_FAILED        // 文件上传失败
```

## 配置说明

### application.yml 配置

```yaml
spring:
  jackson:
    date-format: yyyy-MM-dd HH:mm:ss
    time-zone: Asia/Shanghai
    default-property-inclusion: non_null

logging:
  level:
    cn.jcodenest.wiki: DEBUG
```

### 自动配置

模块提供以下自动配置：
- Jackson序列化配置
- 全局异常处理器
- Web MVC配置（跨域、静态资源）

## 版本历史

| 版本 | 日期 | 说明 |
|------|------|------|
| 1.0.0 | 2025-07-24 | 初始版本，包含核心功能 |

## 贡献指南

1. Fork 项目
2. 创建功能分支
3. 提交更改
4. 推送到分支
5. 创建 Pull Request

## 许可证

Copyright (c) 2025 JCodeNest-Wiki. All rights reserved.
