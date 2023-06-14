function opentuoluoyi(){
    var u = navigator.userAgent;
    var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
    var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端

    //判断是ios还是安卓
    if(isiOS){
        $("#phone").text("IOS");
        if(DeviceOrientationEvent.requestPermission){
            requestAccess();
        }else{
            monitor();
        }
    }else if(isAndroid){
        $("#phone").text("安卓");
        monitor();
    }else {
        // 未知手机型号
        $("#phone").text("未知手机型号");
    }
}

// 获取权限
function requestAccess(){
    DeviceMotionEvent.requestPermission().then(function (state) {
        if ('granted' === state) {
            monitor();
        } else {
            alert('apply permission state: ' + state);
        }
    }).catch(function(err){
        $("#phone").text('error: ' + err);
    });
}

// 陀螺仪旋转后事件处理
function monitor() {
    if(window.addEventListener){
        window.addEventListener("deviceorientation", function(event) {
            // alert("转动手机");
            var alpha = event.alpha;
            var beta = event.beta;
            var gamma = event.gamma;
            $("#alpha").text(alpha);
            $("#beta").text(beta);
            $("#gamma").text(gamma);
            console.log("Alpha: " + alpha);
            console.log("Beta: " + beta);
            console.log("Gamma: " + gamma);
        });
    }else{
        $("#alpha").text('您的手机不支持陀螺仪哦~');
    }
}