<template>
    <div id="banner_index">
        <div class="banner_box">
            <h1 class="big_font_size">{{bannerText}}</h1>
            <p class="small_font_size">{{bannerMsg}}</p>
        </div>
    </div>
</template>
<script>
export default {
    name: 'SmallDaiBannerIndex',
    data(){
        return{
            bannerText: '人人可以搭建业务系统',
            bannerMsg: '小呆低代码系统搭建平台，只需简单操作即可搭建属于你的系统'
        }
    }
}
</script>
<style scoped>
#banner_index{
    width: 100%;
    height: 60vh;
    background-image: url('../../assets/banner/bannerIndex.jpg');
    background-size: 100% 60vh;
}
.banner_box{
    width: 100%;
    height: 20vh;
    position: relative;
    top: 20vh;
    text-align: center;
}
.big_font_size{
    font-size: 8vh;
    font-family: '微软雅黑';
}
.small_font_size{
    font-size: 20px;
    color: rgb(51, 48, 48)
}
</style>