function startLog(){
    // 加载完成
    vr.loadProgressManager.onLoad = function () {
        console.log("界面加载完成");
    };
    //全景资源加载中回调
    vr.loadProgressManager.onProgress = function () {
        console.log("视频加载中");
    };
    //全景资源加载错误回调
    vr.loadProgressManager.onError = function () {
        console.log("视频加载错误");
    };
    // 视频已开始加载
    vr.video.onloadstart = function () {
        console.log("视频已开始加载");
    };
    // 当音频/视频的加载已放弃时触发。
    vr.video.onabort = function () {
        console.log("音频/视频的加载已放弃");
    };
    // 当浏览器可以开始播放音频/视频时触发。
    vr.video.oncanplay = function () {
        console.log("浏览器可以开始播放音频/视频");
    };
    // 当浏览器可在不因缓冲而停顿的情况下进行播放时触发
    vr.video.oncanplaythrough = function () {
        console.log("浏览器可在不因缓冲而停顿的情况下进行播放时");
    };
    // 当音频/视频的时长已更改时触发。
    vr.video.ondurationchange = function () {
        console.log("音频/视频的时长已更改");
    };
    // 当目前的播放列表已结束时触发。
    vr.video.onended = function () {
        console.log("目前的播放列表已结束");
    };
    // 当在音频/视频加载期间发生错误时触发。
    vr.video.onerror = function () {
        console.log("音频/视频加载期间发生错误");
    };
    // 当浏览器已加载音频/视频的当前帧时触发。
    vr.video.onloadeddata = function () {
        console.log("浏览器已加载音频/视频的当前帧");
    };
    // 当浏览器已加载音频/视频的元数据时触发。
    vr.video.onloadedmetadata = function () {
        console.log("浏览器已加载音频/视频的元数据");
    };
    // 当音频/视频已暂停时触发。
    vr.video.onpause = function () {
        console.log("音频/视频已暂停");
    };
    // 当音频/视频已开始或不再暂停时触发。
    vr.video.onplay = function () {
        console.log("音频/视频已开始或不再暂停");
    };
    // 当音频/视频在因缓冲而暂停或停止后已就绪时触发。
    vr.video.onplaying = function () {
        console.log("音频/视频在因缓冲而暂停或停止后已就绪");
    };
    // 当浏览器正在下载音频/视频时触发。
    vr.video.onprogress = function () {
        console.log("浏览器正在下载音频/视频");
    };
    // 当音频/视频的播放速度已更改时触发。
    vr.video.onratechange = function () {
        console.log("音频/视频的播放速度已更改");
    };
// 当用户已移动/跳跃到音频/视频中的新位置时触发。
    vr.video.onseeked = function () {
        console.log("用户已移动/跳跃到音频/视频中的新位置");
    };
// 当用户开始移动/跳跃到音频/视频中的新位置时触发。
    vr.video.onseeking = function () {
        console.log("用户开始移动/跳跃到音频/视频中的新位置");
    };
// 当浏览器尝试获取媒体数据，但数据不可用时触发。
    vr.video.onstalled = function () {
        console.log("浏览器尝试获取媒体数据，但数据不可用");
    };
// 当浏览器刻意不获取媒体数据时触发。
    vr.video.onsuspend = function () {
        console.log("浏览器刻意不获取媒体数据");
    };
// 当目前的播放位置已更改时触发。
    vr.video.ontimeupdate = function () {
        console.log("目前的播放位置已更改");
    };
// 当音量已更改时触发。
    vr.video.onvolumechange = function () {
        console.log("音量已更改");
    };
// 当视频由于需要缓冲下一帧而停止时触发。
    vr.video.onwaiting = function () {
        console.log("视频由于需要缓冲下一帧而停止");
    };
}
