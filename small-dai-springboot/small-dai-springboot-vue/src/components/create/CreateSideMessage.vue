<template>
    <div id="smalldai_create_side_message">
        <el-collapse accordion>
                        <el-collapse-item>
                            <template slot="title">
                            了解小呆
                            </template>
                            <div>项目描述：小呆生成器是基于数据库信息和模版自动生成项目的系统。</div>
                            <div>详细说明：我们会生成一个基于数据的Spring
                                Boot项目提供下载，在此之前我们需要您来做一些简单的配置。</div>
                            <div>具体请看：<strong>低代码生成过程</strong></div>
                        </el-collapse-item>
                        <el-collapse-item title="项目创建">
                            <div>操作指引：根据如下描述输入自己对生成项目的描述信息。</div>
                            <div>包名：您的域名倒着写，比如baidu.com即输入com.baidu即可</div>
                            <div>项目名：写入不带特殊符号且无大写字母的项目英文名，用于让您知道这是一个什么项目，以及创建的项目文件夹名字即为此。</div>
                            <div>项目描述：本项目集成Maven在生成配置文件时会有一段关于本项目的描述，用于让自己或者其他开发人员了解这是个什么项目。</div>
                            <div>数据库地址：本项目集成MySQL，在编写yml配置文件时会有数据库相关信息，我们这里提供一件生成配置文件的功能，如有自行更改意愿的可以直接默认填写。</div>
                            <div>数据库端口：有些开发者在项目部署过程中为了系统安全会修改数据库端口，默认3306，如有修改请填写自己的端口即可，如有自行更改意愿的可以直接默认填写。</div>
                            <div>数据库备注：请以一个简洁的词语描述您的数据库备注，用于后期数据库区分，此处只是为了SQL规范，增强用户可读性。</div>
                            <div>数据库用户名：yml配置文件的内容，您的MySQL登录账号，如有自行修改意愿可以自行修改。</div>
                            <div>数据库密码: yml配置文件的内容，您的MySQL登录密码，如有自行修改意愿可以自行修改。</div>
                        </el-collapse-item>
                        <el-collapse-item title="创作标识">
                            <div>操作指引：根据如下描述输入自己对Logo信息的描述。</div>
                            <div>作者：写入代表自身的信息</div>
                            <div>时间：您创建该项目时间的选择</div>
                        </el-collapse-item>
                        <el-collapse-item title="配置文件">
                            <div>操作指引：此处主要针对项目中yml文件对MySQL和发送邮箱的配置，生成账号登录是使用邮箱注册加密的。</div>
                            <div>数据库地址：运行部署需要连接本地/远程MySQL数据库所以要放MySQL地址。</div>
                            <div>数据库端口：运行部署需要连接本地/远程MySQL数据库所以要放MySQL端口。</div>
                            <div>用户名：MySQL数据库连接的账号。</div>
                            <div>密码：MySQL数据库连接的密码。</div>
                            <div>邮箱账号：本项目生成系统的登录需要用邮箱验证码注册，所以需要配置SMTP协议的邮箱账号。</div>
                            <div>邮箱token：在邮箱开通协议后会提供一串token，使用token后即可发送邮件</div>
                        </el-collapse-item>
                        <el-collapse-item title="依赖配置">
                            <div>操作指引：此处主要针对生成项目所依赖的配置进行生成。</div>
                            <div>IP黑名单：用于Druid后端管理拦截某某某IP等(允许为空，即可以不填)，间隔中间用英文逗号间隔，例子: 192.168.1.1,192.168.1.2。</div>
                            <div>IP白名单：用于Druid后端管理允许某某某管理员IP等访问(允许为空，即可以不填，默认已有localhost)，例子如黑名单IP</div>
                            <div>Druid账号：用于Druid后台登录账号设置。</div>
                            <div>Druid密码：用于Druid后台登录密码设置。</div>
                            <div>Swagger标题：Swagger是一个后端接口在线文档，只是用于Swagger标题，随便自定义。</div>
                            <div>Swagger描述：给开发调试者相关内容说明，只是一个整体项目说明，随便自定义。</div>
                            <div>Swagger版本：当前开发版本号，用于产品迭代时，随便自定义，v1/1.0/1.0.0/v2.0。</div>
                            <div>Druid后台登录地址：http://{IP自己换自己的}/druid/login.html</div>
                            <div>Swagger地址：http://{IP自己换自己的}/swagger-ui/index.html</div>
                        </el-collapse-item>
                        <el-collapse-item title="生成表">
                            <div>操作指引：此处主要针对生成表的相关属性以及功能按钮的介绍，。</div>
                            <div>字段名：你需要描述的属性的英文名即字段。</div>
                            <div>备注：生成的字段的备注增强可阅读性。</div>
                            <div></div>
                        </el-collapse-item>
                        <el-collapse-item title="生成字段">
                            <div>操作指引：此处直观看表格即可。</div>
                            <div>表名：生成的表名。</div>
                            <div>备注：生成的表备注</div>
                            <div>字段属性：就是字段应该属于的类型。</div>
                            <div>字段大小：字段占有的内存容量。</div>
                            <div>主键：点击按钮选择是否主键，一张表必须有一个主键。</div>
                            <div>非空：判断字段是否允许为空。</div>
                            <div>自增：一般多为主键ID自增。</div>
                            <div>批量新增(暂存)：只是暂时向缓存中插入数据，只有点击提交所有缓存数据时才会发送请求新增。</div>
                            <div>批量删除：批量删除字段。</div>
                            <div>单条新增：只有在表中已经存在字段后才会触发单条新增。</div>
                            <div>修改字段：修改字段的备注、字段名等。</div>
                            <div>删除字段：删除某一字段。</div>
                        </el-collapse-item>
                        <el-collapse-item title="配置功能需求">
                            <div>操作指引：此处是对联表需要的情况的记录，在表列表中的功能需求进入。</div>
                            <div>是否联表ID：是否需要查询某一ID实体联取多个关联的表。</div>
                            <div>是否联表列表：是否需要查询某一关联的一系列集合的数据表。</div>
                            <div>是否联表删除：某些数据在删除时会需要联表删除。</div>
                            <div>注意：在此处会生成相关的Mapper/Service/ServiceImpl/Controller在项目中</div>
                        </el-collapse-item>
                        <el-collapse-item title="针对主表的XML配置">
                            <div>操作指引：写入需求功能的SQL数据即可。</div>
                            <div>注意：FROM处的表不可以加别名只能用表名，比如:t_project,t_user。</div>
                        </el-collapse-item>
                        <el-collapse-item title="生成实体类以及Pom">
                            <div>操作指引：点击生成实体按钮即可。</div>
                            <div>注意：此处会生成相关实体以及Pom文件在项目中。</div>
                        </el-collapse-item>
                    </el-collapse>
    </div>
</template>
<script>
export default {
    name: 'SmallDaiCreateSideMessage'
}
</script>
<style scoped>
#smalldai_create_side_message{
    width: 100%;
    height: 100%;
    text-indent: 1em;
}
</style>