# CzStepServer
乐心健康自动刷步手动刷步Spring boot 服务端
参数说明：

phone        手机号

password  密码

steps          提交的步数

flag             是否开启自动刷步   0为不自动刷步  1为自动刷步   刷步时间为每日中午12点



## 单次提交步数

get请求 

```http
  /Service/updateStep?phone=18888888888&password=CZ123456&steps=9990&flag=0
```

响应 

```json
{
	"code": 200,
	"msg": "成功",
	"data": {
		"pedometerRecordHourlyList": [{
			"id": "08be23751dc24a4bbca0638ad8880973",
			"userId": 26993431,
			"deviceId": "M_NULL",
			"measurementTime": "2020-08-22 00:00:00",
			"step": "9950,9950,0,0,0,0,0,0,0,0,0,0,4550,5650,0,0,9990,0,0,0,0,0,0,0",
			"calories": "2487.00,2487.00,0,0,0,0,0,0,0,0,0,0,1137.00,1412.00,0,0,2497.00,0,0,0,0,0,0,0",
			"distance": "3316.00,3316.00,0,0,0,0,0,0,0,0,0,0,1516.00,1883.00,0,0,3330.00,0,0,0,0,0,0,0",
			"dataSource": 2,
			"created": "2020-08-22 00:17:42",
			"active": 0,
			"updated": 1598084752590
		}]
	}
}
```





## 提交自动刷步

get请求

```http
 /Service/updateStep?phone=18888888888&password=CZ123456&steps=9990&flag=1
```

响应

```json
{
	"code": 508,
	"msg": "已加入数据库进行定时任务"
}
```



----

响应码

```json
{
	"code": 200,
	"msg": "成功",
	"data": {
		"pedometerRecordHourlyList": [{
			"id": "08be23751dc24a4bbca0638ad8880973",
			"userId": 26993431,
			"deviceId": "M_NULL",
			"measurementTime": "2020-08-22 00:00:00",
			"step": "9950,9950,0,0,0,0,0,0,0,0,0,0,4550,5650,0,0,9990,0,0,0,0,0,0,0",
			"calories": "2487.00,2487.00,0,0,0,0,0,0,0,0,0,0,1137.00,1412.00,0,0,2497.00,0,0,0,0,0,0,0",
			"distance": "3316.00,3316.00,0,0,0,0,0,0,0,0,0,0,1516.00,1883.00,0,0,3330.00,0,0,0,0,0,0,0",
			"dataSource": 2,
			"created": "2020-08-22 00:17:42",
			"active": 0,
			"updated": 1598084752590
		}]
	}
}
```

```json
{
	"code": 508,
	"msg": "已加入数据库进行定时任务"
}
```

```json
{
	"code": 407,
	"msg": "密码错误"
}
```

```json
{
	"code": 510,
	"msg": "手机号长度错误"
}
```



