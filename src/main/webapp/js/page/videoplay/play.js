var resource_id = 0;
var scenicSpot;
var map;
var markerLayer;

function initInfo(){
    // 获取景区信息
    // initScenicZoneInfo();
    // 获取景点信息
    // initScenicSpotInfo();
    // 获取景点对应资源列表
    initResourceListInfo();
}
function initScenicZoneInfo(){
    $.ajax({
        type : "post",
        url : "../../scenicZonetotalsearchservlet",
        data : {
            "scenicZone_id":scenicZone_id
        },
        async : false,
        success : function(data){
            console.log(data);
        }
    });
}

function initScenicSpotInfo(){
    $.ajax({
        type : "post",
        url : "../../scenicSpottotalsearchservlet",
        data : {
            "scenicSpot_id":scenicSpot_id
        },
        async : false,
        success : function(data){
            scenicSpot = data.data;
            // 更新景点介绍与定位
            $(".scenicSpotIntroduceText").text(scenicSpot.scenicSpot_introduce );
            // 设置景点名称
            $(".scenicSpotName").text(scenicSpot.scenicSpot_name);
            // 设置景点播放数
            $(".num").text(scenicSpot.scenicSpot_num + "次");
            // 初始化地图
            if(initmap){
                // 修改地图中心点
                var center = new TMap.LatLng(scenicSpot.scenicSpot_latitude, scenicSpot.scenicSpot_longitude);
                map.setCenter(center);
                // 修改景点标记点
                markerLayer.updateGeometries([
                    {
                        "styleId":"myStyle",
                        "id": "1",
                        "position": center,
                    }
                ])
            }else {
                var center = new TMap.LatLng(scenicSpot.scenicSpot_latitude, scenicSpot.scenicSpot_longitude);
                //定义map变量，调用 TMap.Map() 构造函数创建地图
                map = new TMap.Map(document.getElementById('scenicSpotMap'), {
                    center: center,//设置地图中心点坐标
                    zoom: 18,   //设置地图缩放级别
                    pitch: 43.5,  //设置俯仰角
                    rotation: 0    //设置地图旋转角度
                });
                markerLayer = new TMap.MultiMarker({
                    map: map,  //指定地图容器
                    //样式定义
                    styles: {
                        //创建一个styleId为"myStyle"的样式（styles的子属性名即为styleId）
                        "myStyle": new TMap.MarkerStyle({
                            "width": 35,  // 点标记样式宽度（像素）
                            "height": 35, // 点标记样式高度（像素）
                            "src": 'https://cdn.uemodel.com/%E5%B0%8F%E7%A8%8B%E5%BA%8F/UI/%E6%92%AD%E6%94%BE/location.png',  //图片路径
                            //焦点在图片中的像素位置，一般大头针类似形式的图片以针尖位置做为焦点，圆形点以圆心位置为焦点
                            "anchor": { x: 16, y: 32 }
                        })
                    },
                    //点标记数据数组
                    geometries: [{
                        "id": "1",   //点标记唯一标识，后续如果有删除、修改位置等操作，都需要此id
                        "styleId": 'myStyle',  //指定样式id
                        "position": center,  //点标记坐标位置
                        "properties": {//自定义属性
                            "title": "marker1"
                        }
                    }
                    ]
                });
                initmap = true;
                $(".scenicSpotInfoPanel").fadeOut("slow");
            }
        }
    });
}

function initResourceListInfo() {
    $.ajax({
        type : "post",
        url : "../../scenicSpot_resourcetotalsearchservlet",
        data : {
            "scenicSpot_id":scenicSpot_id,
            "scenicSpot_resource_use": true
        },
        async : false,
        success : function(data){
            // console.log(data);
            // 加载第一个资源
            getResource_url(data.data[0].resource_id);
        }
    });
}

// 加载视频路径
function getResource_url(resource_id) {
    $.ajax({
        type : "post",
        url : "../../resourcesearchservlet",
        data : {
            "resource_id":resource_id
        },
        async : false,
        success : function(data){
            // console.log(data);
            doVideo(data.data);
        }
    });
}

// 视频播放方法
function doVideo(resource){
    if(resource.resource_id == resource_id) return;
    // 中层播放控制栏 清空
    var PlayControlPanel = $("<div class=\"playControlPanel\"></div>");
    PlayControlPanel.remove();

    var id = resource.resource_id + '';
    var video3d = $("<div id=\""+ id +"\"></div>");
    var player = $(".player");
    player.append(video3d);

    vr = new VR({ 'id':  id});
    vr.vrbox.radius = 600;
    vr.init(function(){});
    console.log(resource.resource_type);
    switch (resource.resource_type) {
        case 2:
            vr.play(resource.resource_url, vr.resType.video);
            vr.video.setAttribute("loop", "loop");
            startLog();
            break;
        default:
            vr.play(resource.resource_url);
            vr.sliceSegment=1;
            break;
    }
    initPanel(resource);
    vr.loadProgressManager.onLoad = function () {
        console.log("界面加载完成");
        userPlay(resource.resource_id);
        if(resource_id == null) {
            resource_id = resource.resource_id;
            return;
        }
        var oldid = '#' + resource_id;
        var oldVR = $(oldid);
        oldVR.remove();
        resource_id = resource.resource_id;
    };
    vr.controls.gyroUnfreeze();
    freeze = true;
    recordplay(resource.resource_id);
}



// 开启陀螺仪
function opengyroUnfreeze() {
    vr.controls.gyroUnfreeze();
}

function closegyroFreeze(){
    vr.controls.gyroFreeze();
}

// 视频静音
function mute(){
    vr.video.muted=true;
}

function sound(){
    vr.video.muted =false;
}

// 视频跳转方法
function changeVideo(resource_id) {
    console.log("视频跳转:" + resource_id);
    // 查询用户是否有权限
    // 获取视频地址
    $.ajax({
        type : "post",
        url : "../../resourcesearchservlet",
        data : {
            "resource_id":resource_id
        },
        async : false,
        success : function(data){
            // 跳转至视频播放错误界面
            if(data.data.length == 0) location.href = "image.html";
            // 切换视频地址并播放
            doVideo(data.data[0]);
        }
    });
}

// 记录播放次数
function recordplay(resource_id){
    $.ajax({
        type : "post",
        url : "../../saveplayrecord",
        data : {
            "resource_id":resource_id
        },
        async : false,
        success : function(data){
            // 跳转至视频播放错误界面
            if(data.msg) console.log("播放次数++");
            else console.log("更新播放次数失败");
        }
    });
}

// 记录用户播放
function userPlay(resource_id){
    if(user_id == 0) return;
    console.log(resource_id);
    $.ajax({
        type : "post",
        url : "../../userPlaysubmitservlet",
        data : {
            "resource_id":resource_id,
            "user_id":user_id,
            "userPlay_time": parseInt(new Date().getTime()/1000)
        },
        async : false,
        success : function(data){
            console.log(data);
        }
    });
}

// 获取signature
function Getsignature(url){
    var timestamp = parseInt(new Date().getTime()/1000);
    var noncestr = "ABCDEFG";
    $.ajax({
        type : "post",
        url : "../../wxconfiggetsignature",
        data : {
            "url":url,
            "noncestr":noncestr,
            "timestamp": timestamp
        },
        async : false,
        success : function(data){
            wx.config({
                debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
                appId: 'wxbf351894bf0ed432', // 必填，公众号的唯一标识
                timestamp: timestamp, // 必填，生成签名的时间戳
                nonceStr: noncestr, // 必填，生成签名的随机串
                signature: data.signature,// 必填，签名
                jsApiList: ["chooseImage"] // 必填，需要使用的JS接口列表
            });
        }
    });
}

function getConfigUrl() {
    let u = window.navigator.userAgent;
    let isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
    //安卓需要使用当前URL进行微信API注册（即当场调用location.href.split('#')[0]）
    //iOS需要使用进入页面的初始URL进行注册，（即在任何pushstate发生前，调用location.href.split('#')[0]）
    let url = '';
    if (isiOS) {
        url = (`${window.localStorage.getItem('_iosWXConfig_') || window.location.href.split('#')[0]}`);//获取初始化的url相关参数
    } else {
        url = (window.location.href.split('#')[0]);
    }
    return url;
}