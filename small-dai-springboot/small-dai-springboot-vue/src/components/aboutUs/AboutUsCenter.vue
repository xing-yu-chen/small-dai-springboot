<template>
    <div id="smalldai_about_us_center">
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>小呆的初衷</span>
            </div>
            <div>
                <p  style="text-indent: 2em;">从平时的开发设计中可以知道绝大多数的业务实体是固定的，只需要通过对普遍业务的抽离即可以用来作为一个生成器使用，但是要让该生成器符合绝大多数的应用场景，所需要的功能必须由用户主体来个性化自定义。</p>
                <p  style="text-indent: 2em;">而在平时开发中，对简单小型项目的冗余重复开发并不能创造自己的价值，并且对一些其他行业的爱好者创建属于自己的项目并不友好，为了能让大众快速生产小型项目来个性化设计的生成器由此而生。</p>
            </div>
        </el-card>
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>联系方式</span>
            </div>
            <div>
                <p  style="text-indent: 2em;">
                    <a href="https://xing-yu-chen.github.io/">小呆博客</a>&nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="https://blog.csdn.net/yw99999">CSDN</a>&nbsp;&nbsp;&nbsp;&nbsp;
                    博客交流群:<a target="_blank" href="https://qm.qq.com/cgi-bin/qm/qr?k=SosTFOodtZq96rgcRbByzTuq-8KKKRfn&jump_from=webapi"><img border="0" src="//pub.idqqimg.com/wpa/images/group.png" alt="小呆博客" title="小呆博客"></a>&nbsp;&nbsp;&nbsp;&nbsp;
                    生成器交流群:<a target="_blank" href="https://qm.qq.com/cgi-bin/qm/qr?k=umd3CiDUdN7lkQmqnG5yJDzIMUlUAZ05&jump_from=webapi"><img border="0" src="//pub.idqqimg.com/wpa/images/group.png" alt="小呆生成器交流群" title="小呆生成器交流群"></a>
                </p>
            </div>
        </el-card>
    </div>
</template>
<script>
export default {
    name: 'SmallDaiAboutUsCenter'
}
</script>
<style scoped>
#smalldai_about_us_center{
    width: 100%;
    height: 50vh;
}
a{
    text-decoration: none;
    color:rgb(54, 44, 44);
}
</style>