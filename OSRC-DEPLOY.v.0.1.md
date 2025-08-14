
# 1.背景

虽然智慧物业管理系统的作者是一位资深的程序员，技术方面的背景并未影响他在市场和行业发展的思考和远见。最近10年以来，互联网得到长足发展并广泛地改变传统行业，实际上进行了整个社会的数字化改造。但是仍然有很多企业应用没法得到满足，这些企业也没有足够的资金来专门建设IT系统，这样的市场需求广泛存在。智慧物业管理系统的作者认为，这样的市场需求应该以标准化的应用系统来满足，智慧物业管理系统系统正是针对物业行业完成的系统，大多数物业公司预算有限，但是智慧物业管理系统系统提供标准化的物业管理功能，足以解决用户的主要诉求,帮助用户提高效益。

OSRC（开源运行时社区[osrc.com](https://osrc.com/)） 是基于云原生技术，让开源爱好者以运行时形态分享开源作品的社区服务。OSRC(开源运行时社区)致力于推动开源项目向更成熟方向发展，开源项目可以在OSRC社区中在线运行起来，运行起来的项目可以提供更鲜活的体验，从而为投身于开源的小伙伴提供助力。
    

## 智慧物业管理系统介绍
 
![logo](https://osrtm.oss-cn-beijing.aliyuncs.com/wiki/img/1649833329546_logo-red.png)   

《智慧物业管理系统》是一个免费开源的基于java的物业管理系统。未来将涵盖停车、安保、客服、工单、收费、财务、办公自动化等模块，构建一个软硬件一体的智慧物业解决方案。 

## 产品蓝图


![产品蓝图](https://osrtm.oss-cn-beijing.aliyuncs.com/wiki/img/1649833354922_0-11.png) 

 

**官网: [pms.example.com](http://pms.example.com/)**


 

# 在开源运行时社区的部署过程

下面描述一下系统在 OSRC 的部署过程。

## step 1：获取代码
首先从 Gitee 获取代码 :  [gitee.com](https://gitee.com/Eventide00/pms-osrc) :


        git clone https://gitee.com/Eventide00/pms-osrc


## step 2:分析代码

项目是模块化的， 各个模块都有单独路径，主程序在 pms-admin 下，前端在web-app下。因此要先在项目主目录下编译，然后在 pms-admin 下编译。 


## step 3: 后台部署


### 1. 配置 Database 资源 
项目使用mysql数据库（数据库资源在 sql 目录下），pms 和 jmreport 两个 database
#### 1.1. 配置 pms 数据库
1. 创建Database pms
2. 按要求执行DDL(pms0.9.1.sql、workflow_view.sql)，创建、配置数据库对象。

![配置 pms 截图](https://osrtm.oss-cn-beijing.aliyuncs.com/wiki/img/1649830037230_0-1.PNG)

#### 1.2. 配置 jmreport 数据库
1. 创建Database jmreport
2. 按要求执行DDL(jmreport.sql)，创建、配置数据库对象。


![配置 jmreport 截图](https://osrtm.oss-cn-beijing.aliyuncs.com/wiki/img/1649830179425_0-2.PNG)
 
#### 1.3. 修改项目 mysql Database 配置
 配置文件：  ..\pms-admin\src\main\resources\application-druid.yml  

        master:
                # url: jdbc:mysql://*******:3306/pms?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&nullCatalogMeansCurrent=true
                # username: ********
                # password: ********   

调整后的文件 [application-druid.yml](https://gitee.com/havithe/pms-osrc/blob/osrc/pms-admin/src/main/resources/application-druid.yml) 
 
#### 1.3. 修改项目 redis 配置
 配置文件：  ..\pms-admin\src\main\resources\application.yml  

          redis:
                # 地址
                host: ********
                # 端口，默认为6379
                port: 6379
                # 数据库索引
                database: 0
                # 密码
                password: 
 

调整后的文件 [application-druid.yml](https://gitee.com/havithe/pms-osrc/blob/osrc/pms-admin/src/main/resources/application.yml) 



 
### 2. 配置后台服务  


配置文件：  ..\pms-admin\src\main\resources\application.yml

#### 2.1. 端口调整  
        server:
                # 服务器的HTTP端口，默认为8080
                # port: 8080
                # 原 8080 端口 修改为随机端口
                port: ${random.int[10000,15000]} 


#### 2.1. 日志配置  



        logging:
                level:
                com.pms: info
                com.baomidou: info
                org.springframework: info
 
调整后的文件 [application-druid.yml](https://gitee.com/havithe/pms-osrc/blob/osrc/pms-admin/src/main/resources/application.yml)  

 


### 3. 项目编译 
#### 3.1. 主目录编译
项目根目录下执行：
        mvn clean intall
        
![主目录编译 截图1](https://osrtm.oss-cn-beijing.aliyuncs.com/wiki/img/1649830316561_01.PNG)

##### 遇到错误：

        
        [ERROR] COMPILATION ERROR :
        [INFO] -------------------------------------------------------------
        [ERROR] /C:/_dev_OSRC/pms-osrc/pms-pms/src/main/java/com/pms/base/util/Constants.java:[3,22] 找不到符号
        符号:   类 Agent
        位置: 程序包 sun.management
        [INFO] 1 error
        [INFO] -------------------------------------------------------------
        [INFO] ------------------------------------------------------------------------
        [INFO] Reactor Summary for 智慧物业管理系统 0.9.1:
        [INFO]
        [INFO] 智慧物业管理系统 ............................................. SUCCESS [  0.293 s]
        [INFO] pms-common ................................... SUCCESS [  5.847 s]
        [INFO] pms-quartz ................................... SUCCESS [  1.365 s]
        [INFO] pms-generator ................................ SUCCESS [  1.365 s]
        [INFO] pms-system ................................... SUCCESS [  1.419 s]
        [INFO] pms-framework ................................ SUCCESS [  1.637 s]
        [INFO] pms-pms ...................................... FAILURE [  4.249 s]
        [INFO] pms-workflow ................................. SKIPPED
        [INFO] pms-admin .................................... SKIPPED
        [INFO] pms-jmReport ................................. SKIPPED
        [INFO] pms-tenant ................................... SKIPPED
        [INFO] ------------------------------------------------------------------------
        [INFO] BUILD FAILURE
        [INFO] ------------------------------------------------------------------------
        [INFO] Total time:  16.583 s
        [INFO] Finished at: 2022-04-07T10:48:24+08:00
        [INFO] ------------------------------------------------------------------------
        [ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.1:compile (default-compile) on project pms-pms: Compilation failure
        [ERROR] /C:/_dev_OSRC/pms-osrc/pms-pms/src/main/java/com/pms/base/util/Constants.java:[3,22] 找不到符号
        [ERROR]   符号:   类 Agent
        [ERROR]   位置: 程序包 sun.management
        [ERROR]
        [ERROR] -> [Help 1]
        [ERROR]
        [ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
        [ERROR] Re-run Maven using the -X switch to enable full debug logging.
        [ERROR]
        [ERROR] For more information about the errors and possible solutions, please read the following articles:
        [ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException
        [ERROR]
        [ERROR] After correcting the problems, you can resume the build with the command
        [ERROR]   mvn <args> -rf :pms-pms

##### 错误处理：

修改pms-osrc/pms-pms/src/main/java/com/pms/base/util/Constants.java，
注释 第三行：
import sun.management.Agent;

再次编译，编译通过

![主目录编译成功 截图1](https://osrtm.oss-cn-beijing.aliyuncs.com/wiki/img/1649830380264_1.PNG)

#### 3.2. pms-admin 目录编译
pms-admin 目录下执行：
        mvn clean install

![admin 目录编译成功 截图1] (https://osrtm.oss-cn-beijing.aliyuncs.com/wiki/img/1649830480085_1-10.PNG)

#### 3.3. 本地试运行 

..\pms-admin\target>java -jar pms-admin-osrc-app.jar
    试运行失败，因为数据库需要设置大小写不敏感，配置文件中的代码大小写和java不一致，调整数据库后，成功启动：

![admin 目录试运行 成功 截图1](https://osrtm.oss-cn-beijing.aliyuncs.com/wiki/img/1649830541764_5.PNG)

#### 3.3.部署到osrc
pms-admin 目录下pom.xml文件引入编译部署插件。

        <plugin>
        <groupId>com.maplecloudy.osrt</groupId>
        <artifactId>maplecloudy-osrt-maven-plugin</artifactId>
        <version>1.0.0-RELEASE</version>
        <executions>
                <execution>
                <goals>
                        <goal>repackage</goal>
                        <goal>install-osrt-app</goal>
                </goals>
                </execution>
        </executions>
        </plugin> 

运行mvn clean install 部署到 osrc：[智慧物业管理系统](https://osrc.com/user/apps/app_772124380457914368)



### 4. 启动后台服务 
4.1. 启动服务

  ![启动服务截图](https://osrtm.oss-cn-beijing.aliyuncs.com/wiki/img/1649830672649_0-3.PNG)

4.2.	查看日志

刷新日志，发现运行正常 

  ![启动服务成功截图](https://osrtm.oss-cn-beijing.aliyuncs.com/wiki/img/1649830852695_0-5.PNG)

登记运行端口

  ![登记运行端口截图](https://osrtm.oss-cn-beijing.aliyuncs.com/wiki/img/1649830873005_0-4.PNG)
 


### 4. 前端部署 
#### 4.1. 调整配置 
4.1.1. 检查 packagejson

4.1.1.1. 调整Name

旧代码：

        "name": "ruoyi",
新代码：

        "name": "zhapxin-pms-ruoyi",
  

4.1.1.2. packagejson要求
node版本要求：

        "engines": {
        "node": ">=8.9",
        "npm": ">= 3.0.0"
        },

#### 4.2. 构建前端项目 
4.2.1. 依赖安装

        npm install --registry=https://registry.npm.taobao.org

  ![ npm install 截图 ](https://osrtm.oss-cn-beijing.aliyuncs.com/wiki/img/1649830948803_7.PNG)

4.2.2. bulid
运行 npm run dev
        npm run dev

编译遇到问题：

  ![问题 截图 ](https://osrtm.oss-cn-beijing.aliyuncs.com/wiki/img/1649830993033_8.PNG)

问题处理：
1.删除目录下 node_modules目录和package-lock.json
2. 运行 npm i

  ![ npm i 截图 ](https://osrtm.oss-cn-beijing.aliyuncs.com/wiki/img/1649831104050_9.PNG)

3. pms-osrc\pms-web>npm run build:prod
编译通过：
  ![ build:prod 截图 ](https://osrtm.oss-cn-beijing.aliyuncs.com/wiki/img/1649831115927_10.PNG)
         

#### 4.3. 前端osrc部署 

执行deploy命令osrci deploy

        osrc deploy

  ![ deploy 截图 ](https://osrtm.oss-cn-beijing.aliyuncs.com/wiki/img/1649831150003_11.PNG)


## 配置代理 
在OSRC 用户自己的page下找刚发布的项目

根据  ..\pms-web\.env.production 中配置的的 VUE_APP_BASE_API ,更新代理配置：

        # 智慧物业管理系统/生产环境
        VUE_APP_BASE_API = '/prod-api'

  ![代理配置截图](https://osrtm.oss-cn-beijing.aliyuncs.com/wiki/img/1649831260528_0-6.PNG)

## 进入系统
打开链接，进入系统：系统链接
[系统链接](https://page-havithe-0bd77392564633f4dbc3b40968f30086.osrc.com/)  

![系统截图1](https://osrtm.oss-cn-beijing.aliyuncs.com/wiki/img/1649831528394_0-7.PNG)

![系统截图2](https://osrtm.oss-cn-beijing.aliyuncs.com/wiki/img/1649831598399_0-8.PNG)

![系统截图3](https://osrtm.oss-cn-beijing.aliyuncs.com/wiki/img/1649831613119_0-9.PNG)
部署调整后的代码：[gitee.com](https://gitee.com/havithe/pms-osrc/tree/osrc/) OSRC 分支

# 部署总结&建议
1. 有源码错误，需要调整 pms-osrc/pms-pms/src/main/java/com/pms/base/util/Constants.java


2. 


3. 


4. 

  
# 联系 OSRC 

![联系截图] (https://osrtm.oss-cn-beijing.aliyuncs.com/wiki/img/1649835025749_wangt.PNG)
  
