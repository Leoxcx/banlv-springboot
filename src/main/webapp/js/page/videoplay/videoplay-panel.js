var initjudge = false;

// 初始化面板
function initPanel(resource_id){
    initMenuPanel(resource_id);
}

// 初始化菜单界面
function initMenuPanel(resource_id){
    var MenuPanel = $("<div class=\"menuPanel\"></div>");
    // 显示进度条
    // MenuPanel.click( function () { $("._toolBarArea").css("display",'block') });
    if(!initjudge){
        initjudge = true;
        var vrSlider = $(".vrSlider");
        var _toolBarBtn = $("._toolBarBtn");
        var _toolBarplay = $("._toolBarPlay");
        MenuPanel.append(vrSlider);
        MenuPanel.append(_toolBarBtn);
        MenuPanel.append(_toolBarplay);
    }
    var _toolBarArea = $("._toolBarArea");
    _toolBarArea.remove();
    var body = $("body");
    body.append(MenuPanel);
}