const now = document.querySelector(".now");
const clientWidth = document.documentElement.clientWidth;
const clientHeight = document.documentElement.clientHeight;
const body = document.body;

function Horizontal() {
    const style = `
    width: ${clientHeight}px;
    height: ${clientWidth}px;
    -webkit-transform-origin: ${clientWidth / 2}px ${clientWidth / 2}px;
    -webkit-transform: rotate(90deg);
    transform-origin: ${clientWidth / 2}px ${clientWidth / 2}px
    transform: rotate(90deg); 
  `;
    body.setAttribute("style", style);
}

function Vertical() {
    const style = `
    width: 100%;
    height: 100%;
    -webkit-transform-origin: 0px 0px;
    -webkit-transform: rotate(0);
    transform-origin: 0px 0px
    transform: rotate(0); 
  `;
    body.setAttribute("style", style);
}

function handleOrientation(event) {
    console.log("旋转");
    now.innerHTML = "旋转";
    const beta = event.beta;
    const gamma = event.gamma;

    const betaBetween = beta >= -30 && beta <= 30;
    const gammaBetween = gamma >= -45 && gamma <= 45;
    if (!gammaBetween && betaBetween) {
        now.innerHTML = "横屏";
        Horizontal();
    } else if (gammaBetween && !betaBetween) {
        now.innerHTML = "竖屏";
        Vertical();
    }
}

function buttonclick(){
    var mql = window.matchMedia("(orientation: portrait)");
    window.addEventListener("deviceorientation", handleOrientation);
    console.log(mql);
}

/*if (window.DeviceOrientationEvent) {
    window.addEventListener("deviceorientation", function(event) {
        // alpha: 围绕垂直手机屏幕的轴转动的旋转角度
        var rotateDegrees = event.alpha;
        // gamma: 围绕平行充电口的轴转动的旋转角度
        var leftToRight = event.gamma;
        // beta: 围绕平行音量键的轴转动的旋转角度
        var frontToBack = event.beta;

        handleOrientationEvent(frontToBack, leftToRight, rotateDegrees);
    }, true);
}

var handleOrientationEvent = function(frontToBack, leftToRight, rotateDegrees) {
    // 弹奏一曲夏威夷吉他
    console.log("旋转");
};
*/