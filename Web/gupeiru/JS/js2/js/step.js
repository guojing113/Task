$(document).ready(function () {
    var days = document.getElementsByClassName('days'); //获取天数标题节点
    var clear = document.getElementsByClassName("clear"); //获取步骤节点
    var word_kill = document.getElementsByClassName("word-kill"); //被杀死的人文本
    var word_vote = document.getElementsByClassName("word-vote"); //被投死的人文本
    var identity = JSON.parse(localStorage.getItem("key")); //获取存储的身份数组数据
    function change(step) { //步骤按钮变色函数
        $(step).css("background", "#83b09a");
        $(step).prev("span").css("border-top", "0.2rem solid #83b09a");
    };
    var sTeps = localStorage.getItem('steps'); //获取步骤
    // console.log(sTeps)
    var fsm = {
        state: localStorage.getItem('steps'),
        killStep: function () { //杀手函数
            switch (fsm.state) {
                case 'none': // 如果是初始状态，即点击杀手步骤
                    localStorage.setItem('steps', 'killer'); //存储步骤为杀手杀人
                    $(location).attr('href', "kill.html"); //点击杀手杀人跳转杀人页面
                    break;
                case 'death': //如果继续点击杀手步骤会弹窗提醒
                    ask("visible", "27vh auto"); //弹窗
                    $("#text").text("请点击亡灵发表遗言");
                    break;
                case 'talk': //如果点击发言
                    ask("visible", "27vh auto"); //弹窗
                    $("#text").text("请点击玩家依次发言");
                    break;
                case 'vote': //如果点击投票
                    ask("visible", "27vh auto"); //弹窗
                    $("#text").text("请点击全民投票");
                    break;
            }
        },
        deathStep: function () { //亡灵函数
            switch (fsm.state) {
                case 'death': //如果当前是点击亡灵步骤
                    ask("visible", "27vh auto"); //弹窗
                    $("#text").text("请死者亮明身份并发表遗言");
                    $("#confirm").on("click", function () { //点击确定变色，存储步骤
                        ask("hidden", "25vh auto"); //弹窗消失
                        change(".death"); //亡灵按钮变色
                        fsm.state = 'talk'; //步骤变为发言
                        localStorage.setItem('steps', fsm.state); //储存步骤
                    });
                    break;
                case 'talk': //如果继续点击亡灵步骤
                    ask("visible", "27vh auto"); //弹窗
                    $("#text").text("请点击玩家依次发言");
                    break;
                case 'none': //如果点击杀手步骤
                    ask("visible", "27vh auto"); //弹窗
                    $("#text").text("请点击杀手杀人");
                    break;
                case 'vote': //如果点击投票
                    ask("visible", "27vh auto"); //弹窗
                    $("#text").text("请点击全民投票");
                    break;
            };
        },
        talkStep: function () { //发言函数
            switch (fsm.state) {
                case 'talk': //如果点击发言步骤
                    ask("visible", "27vh auto"); //弹窗
                    $("#text").text("玩家依次发言讨论");
                    $("#confirm").on("click", function () { //点击确定返回游戏界面
                        ask("hidden", "25vh auto"); //弹窗消失
                        change(".talk"); //发言按钮变色
                        fsm.state = 'vote'; //步骤变为投票
                        localStorage.setItem('steps', fsm.state); //储存步骤
                    });
                    break;
                case 'vote': //如果继续点击发言步骤
                    ask("visible", "27vh auto"); //弹窗
                    $("#text").text("请点击全民投票");
                    break;
                case 'none': //如果点击杀手
                    ask("visible", "27vh auto"); //弹窗
                    $("#text").text("请点击杀手杀人");
                    break;
                case 'death': //如果点击亡灵
                    ask("visible", "27vh auto"); //弹窗
                    $("#text").text("请点击亡灵发表遗言");
                    break;
            };
        },
        voteStep: function () { //投票函数
            switch (fsm.state) {
                case 'vote': //如果点击投票步骤
                    $(location).attr('href', "kill.html"); //跳转投票页面
                    change(".vote"); //发言按钮变色
                    break;
                case 'none': //如果点击杀人
                    ask("visible", "27vh auto"); //弹窗
                    $("#text").text("请点击杀手杀人");
                    break;
                case 'death': //如果点击亡灵
                    ask("visible", "27vh auto"); //弹窗
                    $("#text").text("请点击亡灵发表遗言");
                    break;
                case 'talk': //如果点击发言
                    ask("visible", "27vh auto"); //弹窗
                    $("#text").text("请点击玩家依次发言");
                    break;
            };
        }
    };
    var numO = 0;
    num = JSON.parse(localStorage.getItem("num")); //获得杀人页面储存的num天数数字
    numO = numO + num;
    // console.log(numO);
    var day = ["一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二", "十三", "十四"] //创建所有天数数组
    var dienum = JSON.parse(localStorage.getItem('dieNum')); //获取杀人页面点击的索引数组
    var dienum2 = JSON.parse(localStorage.getItem('dieNum2')); //获取投死页面点击的索引数组
    // console.log(dienum);
    // console.log(dienum2);
    var allPlayers = JSON.parse(localStorage.getItem('allP')); //获取储存的所有人序列号和对应身份和状态
    // console.log(allPlayers);
    var q = localStorage.getItem('deathNums'); //获取被杀的人的身份索引
    var p = localStorage.getItem('deathNums2'); //获取被投死的人的身份索引
    // console.log(q);
    // console.log(p);
    if (numO > 0) { //如果是全民投票后跳转过来，天数+1
        for (var m = 0; m < numO; m++) {
            var clone = clear[0].cloneNode(true); //复制步骤
            $("main").append(clone);
            $(clear[m + 1]).children("p").text("第" + day[m + 1] + "天"); //天数标题改变
        };
    };
    //步骤点击事件
    $("main").find(clear[numO]).on("click", "p", function (event) { //事件委托
        var target = event.target;
        switch (target.className) {
            case "days":
                $(this).next().slideToggle(200);
                break;
            case "color kill":
                fsm.killStep();
                break;
            case "color death":
                fsm.deathStep();
                break;
            case "color talk":
                fsm.talkStep();
                break;
            case "color vote":
                fsm.voteStep();
                break;
        };
    });
    //前面天数的游戏步骤设置
    for (var j = 0; j < numO; j++) {
        $(clear[j]).find(".color").css("background", "#83b09a"); //前面步骤变色
        $(clear[j]).find(".triangle").css("border-top", "0.2rem solid #83b09a"); //前面步骤变色
        $("main").find(days[j]).next().hide(); //步骤变为隐藏
        $("main").find(days[j]).on("click", function () { //点击下滑
            $(this).next().slideToggle(200);
        });
        $("main").find(clear[j]).on("click", ".color", function () { //如果点击前面的步骤则弹窗提示请进行游戏下一项活动
            ask("visible", "27vh auto"); //弹窗
            $("#text").text("请进行游戏下一项活动");
            $("#confirm").on("click", function () {
                //点击确定返回游戏界面
                ask("hidden", "25vh auto"); //弹窗消失
            });
        });
    };

    function word() { //文本添加函数
        for (var i = 0; i <= numO; i++) { //添加文本
            if (q !== null) {
                $(word_kill[i]).text(1 + ~~dienum[i] + "号被杀手杀死，" + "真实身份是" + allPlayers[~~dienum[i]].status);
            };
            if (i > 0) { //有过一次投票后
                $(word_vote[i - 1]).text(1 + ~~dienum2[i - 1] + "号被投死，" + "真实身份是" + allPlayers[~~dienum2[i - 1]].status);
            };
        };
    };

    switch (sTeps) { //判断步骤状态变色
        case 'death': //如果点击过亡灵了
            change(".kill"); //杀手步骤变色
            word();
            break;
        case 'talk': //如果点击过发言了
            change(".death"); //亡灵步骤变色
            change(".kill"); //杀手步骤变色
            word();
            break;
        case 'vote': //如果点击过投票了
            change(".death"); //亡灵步骤变色
            change(".kill"); //杀手步骤变色
            change(".talk"); //发言步骤变色
            word();
            break;
        default: //其他
            for (var n = 0; n < numO; n++) { //即如果过了一天了，文字添加
                word_kill[n].innerHTML = 1 + ~~dienum[n] + "号被杀手杀死，" + "真实身份是" + allPlayers[~~dienum[n]].status;
                word_vote[n].innerHTML = 1 + ~~dienum2[n] + "号被投死，" + "真实身份是" + allPlayers[~~dienum2[n]].status;
            };
    };

    //顶部，底部按钮点击事件

    close(); //点击关闭按钮弹窗
    back("deal.html"); //点击返回按钮返回法官日记游戏界面
    hidden(); //点击取消弹窗消失
    enter(); //enter键等同底部按钮
    var Num = 0;
    var arryDay;
    var knumber = 0; //杀死人数
    var pnumber = 0; //平民人数
    var resault; //结果

    for (var n = 0; n < identity.length; n++) { //循环，获取杀手和平民的剩余人数
        if (allPlayers[n].state === 1) { //如果为存活状态
            // console.log(allPlayers[n].state)
            if (allPlayers[n].status === '杀手') { //如果是杀手
                knumber++; //杀手人数加1
            } else { //如果是平民
                pnumber++; //平民人数加1
            };
        };
    };
    // console.log(knumber, pnumber)
    var arryDay = new Array(); //创建一个空数组用来储存天数
    for (var x = 0; x < numO + 1; x++) {
        arryDay.push(day[x]);
    };
    // console.log(arryDay);
    $("#over").click(function () { //点击结束游戏按钮弹窗
        ask("visible", "27vh auto");
        $("#text").text("本轮游戏是否已经结束?");
        $("#confirm").on("click", function () {
            localStorage.setItem("pNum", JSON.stringify(pnumber));
            localStorage.setItem("kNum", JSON.stringify(knumber));
            localStorage.setItem("arryDay", JSON.stringify(arryDay));
            localStorage.setItem("num", JSON.stringify(Num)); //保存天数
            localStorage.setItem("dayNum", JSON.stringify(day));
            $(location).attr('href', "resault.html")
            resault = '游戏结束'; //游戏结束，跳转结果页面
            localStorage.setItem('resault', JSON.stringify(resault)); //存储结果
        });
    });
    $("#log").click(function () { //点击法官日志按钮跳转法官日记
        $(location).attr('href', "log-2.html");
    });
});