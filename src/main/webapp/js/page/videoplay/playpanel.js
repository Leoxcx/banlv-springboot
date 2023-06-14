var initjudge;
var initmap;
var resourcelistshow;
let audio;

// 初始化面板
function initPanel(resource){
    initMenuPanel(resource);
}

// 初始化菜单界面
function initMenuPanel(resource){
    if(initjudge){
        var PlayControlPanel = $(".playControlPanel");
        switch (resource.resource_type) {
            case 2:
                // 中层播放控制栏 清空
                var vrSlider = $(".vrSlider");
                var _toolBarBtn = $("._toolBarBtn");
                var _toolBarplay = $("._toolBarPlay");
                PlayControlPanel.append(vrSlider);
                PlayControlPanel.append(_toolBarBtn);
                PlayControlPanel.append(_toolBarplay);
                PlayControlPanel.fadeIn("slow");
                break;
            default:
                PlayControlPanel.fadeOut("slow");
        }
        // 重置资源列表
        initResourceList($(".resourceListPanel"));
        initScenicSpotInfo();

        // 陀螺仪
        $("#gyroscopeCloseButton").fadeOut("slow");
        opengyroUnfreeze();
        $("#gyroscopeOpenButton").fadeIn("slow");
        // 静音
        if(resource.resource_type === 2){
            toolButtonPanel4.fadeIn("slow");
            $("#musicCloseButton").fadeOut("slow");
            sound();
            $("#musicOpenButton").fadeIn("slow");
        }
        // 解说
        $("#commentaryPlayButton").fadeOut("slow");
        audio.pause();
        audio = new Audio(resource.resource_commentaryaudio);
        audio.play();
        $("#commentaryPauseButton").fadeIn("slow");

        $("._toolBarArea").remove();
        return;
    }
    initjudge = true;
    var body = $("body");

    // 顶部
    var TopPanel = $("<div class=\"topPanel\"></div>");
    body.append(TopPanel);

    // 顶部左侧
    var TopLeftPanel = $("<div class=\"topLeftPanel\"></div>");
    TopPanel.append(TopLeftPanel);
    var Logo = $("<img class=\"logo\" src='https://cdn.uemodel.com/server/logo.png'>");
    TopLeftPanel.append(Logo);

    var ScenicSpotTitle = $("<div class=\"scenicSpotTitle\"></div>");
    TopLeftPanel.append(ScenicSpotTitle);
    var ScenicSpotName = $("<text class=\"scenicSpotName\">"+ scenicSpot_id+"</text>");
    ScenicSpotTitle.append(ScenicSpotName);

    // 顶部右侧
    var TopRightPanel = $("<div class=\"topRightPanel\"></div>");
    TopPanel.append(TopRightPanel);
    var PlayNum = $("<div class=\"playNum\"></div>");
    TopRightPanel.append(PlayNum);
    var num = $("<text class=\"num\">0</text>");
    PlayNum.append(num);
    var Hot = $("<img class=\"hot\" src='https://cdn.uemodel.com/server/hot.png'>");
    TopRightPanel.append(Hot);

    // 中部
    var CenterPanel = $("<div class=\"centerPanel\"></div>");
    body.append(CenterPanel);
    // 弹窗
    var TipPanel = $("<div class=\"tipPanel\"  style='display: none'></div>");
    CenterPanel.append(TipPanel);
    // 弹窗文字
    var TipText = $("<Text class=\"tipText\">提示</Text>");
    TipPanel.append(TipText);

    // 底部
    var BottomPanel = $("<div class=\"bottomPanel\"></div>");
    body.append(BottomPanel);

    // 最下层工具栏
    var ToolPanel = $("<div class=\"toolPanel\"></div>");
    BottomPanel.append(ToolPanel);
    // 控制资源页面显示与隐藏按钮
    var toolButtonPanel1 = $("<div class=\"toolButtonPanel\" id='toolButtonPanel1'></div>");
    ToolPanel.append(toolButtonPanel1);
    var menuOpenbutton = $("<img class='toolbutton' id='menuOpenbutton' src='https://cdn.uemodel.com/%E5%B0%8F%E7%A8%8B%E5%BA%8F/UI/%E6%92%AD%E6%94%BE/menuopen.png'>");
    var menuClosebutton = $("<img class='toolbutton' id='menuClosebutton' src='https://cdn.uemodel.com/%E5%B0%8F%E7%A8%8B%E5%BA%8F/UI/%E6%92%AD%E6%94%BE/menuclose.png'>");
    toolButtonPanel1.append(menuOpenbutton);
    toolButtonPanel1.append(menuClosebutton);
    menuOpenbutton.fadeOut("slow");
    menuOpenbutton.click(function () {
        $("#menuOpenbutton").fadeOut("slow");
        $(".resourceListPanel").fadeIn("slow");
        $("#menuClosebutton").fadeIn("slow");
    });
    menuClosebutton.click(function () {
        $("#menuClosebutton").fadeOut("slow");
        $(".resourceListPanel").fadeOut("slow");
        $("#menuOpenbutton").fadeIn("slow");
    });
    // 控制地图按钮
    var toolButtonPanel2 = $("<div class=\"toolButtonPanel\" id='toolButtonPanel2'></div>");
    ToolPanel.append(toolButtonPanel2);
    var mapbutton = $("<img class='toolbutton' id='mapbutton' src='https://cdn.uemodel.com/%E5%B0%8F%E7%A8%8B%E5%BA%8F/UI/%E6%92%AD%E6%94%BE/map.png'>");
    toolButtonPanel2.append(mapbutton);

    var body = $("body");
    var scenicSpotInfoPanel = $("<div class=\"scenicSpotInfoPanel\" ></div>");

    var scenicSpotTab = $("<div class=\"scenicSpotTab\"></div>");

    var scenicSpotIntroduceTab = $("<button class = \"scenicSpotIntroduceTab\" disabled>景点简介</button>");
    var scenicSpotsTab = $("<button class = \"scenicSpotsTab\">周边景点</button>");
    var closeScenicSpotPanelButton = $("<img class='closeScenicSpotPanelButton' src='https://cdn.uemodel.com/%E5%B0%8F%E7%A8%8B%E5%BA%8F/UI/%E6%92%AD%E6%94%BE/close.png'>");

    closeScenicSpotPanelButton.click(function () {
        scenicSpotInfoPanel.fadeOut("slow");
    });

    // 景点介绍信息
    var scenicSpotIntroducePanel = $("<div class = \"scenicSpotIntroducePanel\"></div>");
    var scenicSpotIntroduce = $("<div class = \"scenicSpotIntroduce\"></div>");
    var scenicSpotIntroduceText = $("<text class = \"scenicSpotIntroduceText\"></text>");
    var scenicSpotMap = $("<div class = \"scenicSpotMap\" id=\"scenicSpotMap\"></div>");

    // 景点列表信息
    var scenicSpotsPanel = $("<div class = \"scenicSpotsPanel\" style='display:none;'></div>");

    $.ajax({
        type : "post",
        url : "../../getallscenicspotbyscenicspotid",
        data : {
            "scenicSpot_id":scenicSpot_id
        },
        async : false,
        success : function(data){
            // console.log(data);
            var scenicSpots = data.data;
            var row = scenicSpots.length/2;
            for(var i = 0; i < row ;i++){
                let scenicSpot_id1 = scenicSpots[i * 2].scenicSpot_id;
                var scenicSpotRow = $("<div class = \"scenicSpotRow\"></div>");
                var scenicSpotCube1 = $("<div class = \"scenicSpotCube\"></div>");
                var scenicSpotImg1 = $("<img class=\"scenicSpotsImg\" src=\"https://cdn.uemodel.com/picture/no picture.jpg\">");
                getPictureURL(scenicSpots[i * 2].scenicSpot_thumbnail,scenicSpotImg1);
                var scenicSpotName1 = $("<text class=\"scenicSpotsName\">" + scenicSpots[i * 2].scenicSpot_name + "</text>");
                // if(scenicSpot_id != scenicSpot_id1){
                    scenicSpotCube1.click(function () {
                        // 跳转至其他景点界面
                        if(scenicSpot_id1 == scenicSpot_id) return;
                        scenicSpot_id = scenicSpot_id1;
                        initInfo();
                    });
                // }
                scenicSpotCube1.append(scenicSpotImg1);
                scenicSpotCube1.append(scenicSpotName1);
                scenicSpotRow.append(scenicSpotCube1);
                if((i * 2 + 1) != scenicSpots.length){
                    let scenicSpot_id2 = scenicSpots[i * 2 + 1].scenicSpot_id;
                    var scenicSpotName2 = $("<text class=\"scenicSpotsName\">" + scenicSpots[i * 2 + 1].scenicSpot_name + "</text>");
                    var scenicSpotCube2 = $("<div class = \"scenicSpotCube\"></div>");
                    var scenicSpotImg2 = $("<img class=\"scenicSpotsImg\" src=\"https://cdn.uemodel.com/picture/no picture.jpg\">");
                    getPictureURL(scenicSpots[i * 2 + 1].scenicSpot_thumbnail,scenicSpotImg2);
                    scenicSpotCube2.append(scenicSpotImg2);
                    scenicSpotCube2.append(scenicSpotName2);
                    scenicSpotRow.append(scenicSpotCube2);
                    // if(scenicSpot_id != scenicSpot_id2){
                        scenicSpotCube2.click(function () {
                            // 跳转至其他景点界面
                            if(scenicSpot_id1 == scenicSpot_id) return;
                            scenicSpot_id = scenicSpot_id2;
                            initInfo();
                        });
                    // }
                }else {
                    var scenicSpotCube2 = $("<div class = \"scenicSpotCube\"></div>");
                    scenicSpotCube2.attr("style","background-color:rgba(0,0,0,0)");
                    scenicSpotRow.append(scenicSpotCube2);
                }
                scenicSpotsPanel.append(scenicSpotRow);
            }
        }
    });


    scenicSpotIntroduceTab.click(function () {
        scenicSpotIntroduceTab.attr("disabled","true");
        scenicSpotsTab.removeAttr("disabled");
        scenicSpotIntroducePanel.fadeIn("slow");
        scenicSpotsPanel.fadeOut("slow");
    });

    scenicSpotsTab.click(function () {
        scenicSpotIntroduceTab.removeAttr("disabled");
        scenicSpotsTab.attr("disabled","true");
        scenicSpotIntroducePanel.fadeOut("slow");
        scenicSpotsPanel.fadeIn("slow");
    });

    scenicSpotTab.append(scenicSpotIntroduceTab);
    scenicSpotTab.append(scenicSpotsTab);
    scenicSpotTab.append(closeScenicSpotPanelButton);
    scenicSpotInfoPanel.append(scenicSpotTab);

    scenicSpotIntroduce.append(scenicSpotIntroduceText);
    scenicSpotIntroducePanel.append(scenicSpotIntroduce);
    scenicSpotIntroducePanel.append(scenicSpotMap);

    scenicSpotInfoPanel.append(scenicSpotTab);
    scenicSpotInfoPanel.append(scenicSpotIntroducePanel);
    scenicSpotInfoPanel.append(scenicSpotsPanel);

    body.append(scenicSpotInfoPanel);

    mapbutton.click(function () {
        // WeixinJSBridge.call('closeWindow');
        // wx.miniProgram.navigateBack();//返回
        // 显示景点信息
        scenicSpotInfoPanel.fadeIn("slow");
        scenicSpotIntroduceTab.attr("disabled","true");
        scenicSpotsTab.removeAttr("disabled");
        scenicSpotIntroducePanel.fadeIn("slow");
        scenicSpotsPanel.fadeOut("slow");
    });
    // 陀螺仪按钮
    var toolButtonPanel3 = $("<div class=\"toolButtonPanel\" id='toolButtonPanel3'></div>");
    ToolPanel.append(toolButtonPanel3);
    var gyroscopeOpenButton = $("<img class='toolbutton' id='gyroscopeOpenButton' src='https://cdn.uemodel.com/%E5%B0%8F%E7%A8%8B%E5%BA%8F/UI/%E6%92%AD%E6%94%BE/gyroscopeopen.png'>");
    var gyroscopeCloseButton = $("<img class='toolbutton' id='gyroscopeCloseButton' src='https://cdn.uemodel.com/%E5%B0%8F%E7%A8%8B%E5%BA%8F/UI/%E6%92%AD%E6%94%BE/gyroscopeclose.png'>");
    toolButtonPanel3.append(gyroscopeOpenButton);
    toolButtonPanel3.append(gyroscopeCloseButton);

    gyroscopeCloseButton.fadeOut("slow");

    gyroscopeOpenButton.click(function () {
        $(".tipPanel").fadeIn("slow");
        $(".tipText").text("关闭陀螺仪");
        setTimeout(function () {
            $(".tipPanel").fadeOut("slow");
        },1000);
        $("#gyroscopeOpenButton").fadeOut("slow");
        closegyroFreeze();
        $("#gyroscopeCloseButton").fadeIn("slow");
    });
    gyroscopeCloseButton.click(function () {
        $(".tipPanel").fadeIn("slow");
        $(".tipText").text("开启陀螺仪");
        setTimeout(function () {
            $(".tipPanel").fadeOut("slow");
        },1000);
        $("#gyroscopeCloseButton").fadeOut("slow");
        opengyroUnfreeze();
        $("#gyroscopeOpenButton").fadeIn("slow");
    });

    // 静音按钮
    var toolButtonPanel4 = $("<div class=\"toolButtonPanel\" id='toolButtonPanel4'></div>");
    ToolPanel.append(toolButtonPanel4);
    if(resource.resource_type!=2){
        toolButtonPanel4.fadeOut("100");
    }
    var musicOpenButton = $("<img class='toolbutton' id='musicOpenButton' src='https://cdn.uemodel.com/%E5%B0%8F%E7%A8%8B%E5%BA%8F/UI/%E6%92%AD%E6%94%BE/musicopen.png'>");
    var musicCloseButton = $("<img class='toolbutton' id='musicCloseButton' src='https://cdn.uemodel.com/%E5%B0%8F%E7%A8%8B%E5%BA%8F/UI/%E6%92%AD%E6%94%BE/musicclose.png'>");

    toolButtonPanel4.append(musicOpenButton);
    toolButtonPanel4.append(musicCloseButton);

    musicCloseButton.fadeOut("slow");

    musicOpenButton.click(function () {
        $(".tipPanel").fadeIn("slow");
        $(".tipText").text("静音");
        setTimeout(function () {
            $(".tipPanel").fadeOut("slow");
        },1000);
        $("#musicOpenButton").fadeOut("slow");
        mute();
        $("#musicCloseButton").fadeIn("slow");
    });
    musicCloseButton.click(function () {
        $(".tipPanel").fadeIn("slow");
        $(".tipText").text("取消静音");
        setTimeout(function () {
            $(".tipPanel").fadeOut("slow");
        },1000);
        $("#musicCloseButton").fadeOut("slow");
        sound();
        $("#musicOpenButton").fadeIn("slow");
    });

    // 解说词按钮
    var toolButtonPanel5 = $("<div class=\"toolButtonPanel\" id='toolButtonPanel5'></div>");
    ToolPanel.append(toolButtonPanel5);
    var commentaryPlayButton = $("<img class='toolbutton' id='commentaryPlayButton' src='https://cdn.uemodel.com/%E5%B0%8F%E7%A8%8B%E5%BA%8F/UI/%E6%92%AD%E6%94%BE/commentaryplay.png'>");
    var commentaryPauseButton = $("<img class='toolbutton' id='commentaryPauseButton' src='https://cdn.uemodel.com/%E5%B0%8F%E7%A8%8B%E5%BA%8F/UI/%E6%92%AD%E6%94%BE/commentarypause.png'>");

    var audiopanel = $("<audio id=\"myaudio\" loop=\"loop\" preload=\"preload\" muted=\"muted\" />");
    audio = new Audio(resource.resource_commentaryaudio);

    var autoplayAudio = document.addEventListener("WeixinJSBridgeReady", function () {
        //微信H5环境，监听WeixinJSBridgeReady事件，再次进行play//亲测iOS 微信H5页面能够自动播放
        audio.play();
        document.removeEventListener("WeixinJSBridgeReady",autoplayAudio);
    }, false);

    toolButtonPanel5.append(commentaryPlayButton);
    toolButtonPanel5.append(commentaryPauseButton);
    toolButtonPanel5.append(audiopanel);

    commentaryPlayButton.fadeOut("slow");

    commentaryPlayButton.click(function () {
        $(".tipPanel").fadeIn("slow");
        $(".tipText").text("开启解说");
        setTimeout(function () {
            $(".tipPanel").fadeOut("slow");
        },1000);
        $("#commentaryPlayButton").fadeOut("slow");
        audio.play();
        $("#commentaryPauseButton").fadeIn("slow");
    });
    commentaryPauseButton.click(function () {
        $(".tipPanel").fadeIn("slow");
        $(".tipText").text("关闭解说");
        setTimeout(function () {
            $(".tipPanel").fadeOut("slow");
        },1000);
        $("#commentaryPauseButton").fadeOut("slow");
        audio.pause();
        $("#commentaryPlayButton").fadeIn("slow");
    });

    // 更新解说词
    audio.addEventListener('timeupdate', function () {
        // 判断菜单是否弹出
        console.log(audio.currentTime);
        console.log("播放时间改变");
    }, false);

    // 截屏按钮
    var toolButtonPanel6 = $("<div class=\"toolButtonPanel\"  id='toolButtonPanel6'></div>");
    ToolPanel.append(toolButtonPanel6);
    var screenShotbutton = $("<img class='toolbutton' id='screenShotbutton' src='https://cdn.uemodel.com/%E5%B0%8F%E7%A8%8B%E5%BA%8F/UI/%E6%92%AD%E6%94%BE/screenshot.png'>");
    toolButtonPanel6.append(screenShotbutton);
    screenShotbutton.click(function () {
        console.log("截屏");
        // $(".tipPanel").fadeIn("slow");
        // $(".tipText").text("敬请期待");
        // setTimeout(function () {
        //     $(".tipPanel").fadeOut("slow");
        // },1000);
        takeScreenshot(resource);
        // 获取当前资源图片
        // Getsignature(getConfigUrl());
        // wx.ready(function(){
        //     wx.chooseImage({
        //         count: 1, // 默认9
        //         sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
        //         sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
        //         success: function (res) {
        //             var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
        //         }
        //     });
        // })
        // wx.error(function(res){
        //     console.log(res)
        //     // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
        // });
        // 显示返回按钮
    });

    // 全屏按钮
    var toolButtonPanel7 = $("<div class=\"toolButtonPanel\"  id='toolButtonPanel7'></div>");
    ToolPanel.append(toolButtonPanel7);
    var fullScreenOpenbutton = $("<img class='toolbutton' id='fullScreenOpenbutton' src='https://cdn.uemodel.com/%E5%B0%8F%E7%A8%8B%E5%BA%8F/UI/%E6%92%AD%E6%94%BE/fullscreenopen.png'>");
    var fullScreenClosebutton = $("<img class='toolbutton' id='fullScreenClosebutton' src='https://cdn.uemodel.com/%E5%B0%8F%E7%A8%8B%E5%BA%8F/UI/%E6%92%AD%E6%94%BE/fullscreenclose.png'>");
    toolButtonPanel7.append(fullScreenOpenbutton);
    toolButtonPanel7.append(fullScreenClosebutton);

    fullScreenClosebutton.fadeOut("slow");

    fullScreenOpenbutton.click(function () {
        $(".tipPanel").fadeIn("slow");
        $(".tipText").text("全屏");
        setTimeout(function () {
            $(".tipPanel").fadeOut("slow");
        },1000);
        $("#fullScreenOpenbutton").hide();
        $(".topPanel").fadeOut("slow");
        $(".resourceListPanel").fadeOut("slow");
        $('.toolPanel div:not(:last-child)').fadeOut(100);
        if(resource.resource_type == 2){
            $(".playControlPanel").fadeOut("slow");
        }
        $("#fullScreenClosebutton").fadeIn("slow");
    });
    fullScreenClosebutton.click(function () {
        $(".tipPanel").fadeIn("slow");
        $(".tipText").text("取消全屏");
        setTimeout(function () {
            $(".tipPanel").fadeOut("slow");
        },1000);
        $("#fullScreenClosebutton").hide();
        $(".topPanel").fadeIn("slow");
        $('.toolPanel div:not(:last-child)').fadeIn("slow");
        // 菜单按钮变为关闭
        $("#menuOpenbutton").fadeIn("slow");
        $("#menuClosebutton").fadeOut("slow");
        if(resource.resource_type == 2){
            $(".playControlPanel").fadeIn("slow");
        }
        $("#fullScreenOpenbutton").fadeIn("slow");
    });

    // 中层播放控制栏
    var PlayControlPanel = $("<div class=\"playControlPanel\"></div>");
    BottomPanel.append(PlayControlPanel);
    var vrSlider = $(".vrSlider");
    var _toolBarBtn = $("._toolBarBtn");
    var _toolBarplay = $("._toolBarPlay");
    switch (resource.resource_type) {
        case 2:
            // 中层播放控制栏 清空
            PlayControlPanel.append(vrSlider);
            PlayControlPanel.append(_toolBarBtn);
            PlayControlPanel.append(_toolBarplay);
            PlayControlPanel.fadeIn("slow");
            break;
        default:
            PlayControlPanel.fadeOut("slow");
    }
    $("._toolBarArea").remove();

    // 资源选择滑动组件
    var ResourceListPanel = $("<div class=\"resourceListPanel\"></div>");
    BottomPanel.append(ResourceListPanel);
    initResourceList(ResourceListPanel);

    var _toolBarArea = $("._toolBarArea");
    _toolBarArea.remove();
    var body = $("body");
    body.append(body);
    initScenicSpotInfo();
}

function initResourceList(ResourceListPanel){
    ResourceListPanel.empty();
    var scenicSpotMenu = $("<div class=\"swiper scenicSpotMenu\" style='background: rgba(59,8,13,0.18)'></div>");
    var swiperwrapper = $("<div class=\"swiper-wrapper\">");
    // 获取视频关联信息
    $.ajax({
        type : "post",
        url : "../../getresourcesbyscenicspotid",
        data : {
            "scenicSpot_id":scenicSpot_id
        },
        async : false,
        success : function(res){
            var resources = res.data;
            for(var i = 0 ; i < resources.length ;i++) {
                var swiperslide = $("<div class=\"swiper-slide\"></div>");
                var videoChangeCube = $("<div class=\"resourceCube\"></div>");
                if(resources[i].resource_thumbnail != null){
                    var img = $("<img class=\"resourceImage\" src=\"" + resources[i].resource_thumbnail + "\"/>");
                    videoChangeCube.append(img);
                }
                let resourceId = resources[i].resource_id;
                img.click(function () {changeVideo(resourceId);});
                var videoNameText = $("<text class=\"resourceText\">" + resources[i].resource_name +"</text>");

                videoChangeCube.append(videoNameText);

                swiperslide.append(videoChangeCube);
                swiperwrapper.append(swiperslide);
            }
            var swiperpagination = $("<div class=\"swiper-pagination\"></div>");
            scenicSpotMenu.append(swiperwrapper);
            scenicSpotMenu.append(swiperpagination);
            ResourceListPanel.append(scenicSpotMenu);
            var scenicSpotMenuswiper = new Swiper(".scenicSpotMenu", {
                slidesPerView: 5,
                spaceBetween: 10,
                freeMode: true,
                pagination: {
                    el: ".swiper-pagination",
                    clickable: true,
                },
            });
            // $(".resourceListPanel").fadeOut("slow");
            $("#ban1").fadeIn("slow");
        }
    });
}

function getPictureURL(picture_id,scenicSpotImg){
    if(picture_id != 0){
        $.ajax({
            type : "post",
            url : "../../picturetotalsearchservlet",
            data : {
                "picture_id":picture_id,
                "picture_use":true
            },
            async : false,
            success : function(data){
                console.log("图片信息：");
                console.log(data.data.picture_url);
                scenicSpotImg.attr("src","https://cdn.uemodel.com/picture/" + data.data.picture_url);
            }
        });
    }else {
        return "https://cdn.uemodel.com/picture/no picture.jpg";
    }
}