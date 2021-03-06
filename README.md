# DessertHouse
Manage a dessert house.

![image](https://github.com/MelonGO/DessertHouse/raw/master/screenshot/s4.jpg)
![image](https://github.com/MelonGO/DessertHouse/raw/master/screenshot/s1.jpg)
![image](https://github.com/MelonGO/DessertHouse/raw/master/screenshot/s2.jpg)
![image](https://github.com/MelonGO/DessertHouse/raw/master/screenshot/s3.jpg)

##会员
* 注册
  * 每位会员持有一张会员卡（7位识别码，系统自动分配）
* 会员资格激活
  * 办卡后，一次交纳200元以上激活
  * 会费通过银行卡支付（数据库中的银行卡账号）
* 会员资格暂停/恢复/停止
  * 有效期一年，到期后卡上费用不足将暂停其记录；一旦支付，则恢复，会员记录可用；暂停1年后未支付，会员记录停止（系统自动完成）
* 会员资格取消
  * 会员可通知系统取消资格（即停止）
* 会员卡级别
  * 根据缴纳金额，设置不同级别，享受不同优惠
  * 消费，可获取积分，积分可兑换为卡金额
* 预订产品

##系统管理员
* 店面管理
* 店员管理：每个店员属于不同的店面

##总店服务员
* 产品计划管理，包括每个店面一周内每日可售的产品、数量、价格等，提交给经理批准
* 分店服务员
  * 销售（有卡和无卡/现金）

##经理
* 批准服务员制定的产品计划（批准后，会员方可预订；如不批准，服务员可修改计划）
* 逐月统计
  * 会员年龄及各年龄段百分比、性别、居住地、消费、卡有效/停止/暂停/取消情况
  * 预订、售出情况（按照店面统计）
  * 热卖产品
  * 采用图表显示

##另外
* 会员，可查询其注册信息、消费记录、缴费记录；可修改其注册信息
* 服务员，可查询会员信息、会员消费记录、缴费记录等




