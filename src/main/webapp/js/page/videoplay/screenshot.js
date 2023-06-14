function takeScreenshot() {
    vr.takeScreenShot(function(screenshotImg){
        console.log(screenshotImg)
        var img = new Image();
        img.setAttribute("src", screenshotImg);
                var url = img.src.replace(/^data:image\/[^;]/, 'data:application/octet-stream');
                window.open(url);
    })

}

