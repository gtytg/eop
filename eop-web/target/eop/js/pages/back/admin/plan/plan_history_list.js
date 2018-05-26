$(function () {
    $("button[id^=pub-]").each(function () {
        $(this).on("click", function () {
            misid = this.id.split("-")[1];
            $.ajax({
                url: "pages/back/admin/plan/update_status.action",
                dataType: "json",
                async: true,
                data: {misid: misid},
                traditional: true,
                type: "post",
                success: function () {
                    console.log(misid);
                    $("#a-"+misid).attr("href","pages/back/admin/plan/get_plan_details.action?pid="+misid);
                    $("#a-"+misid).attr("disabled",false);
                    $("#pub-"+misid).attr("disabled",true);
                    var attr = $("#td0-" + misid).attr("id","#td1-"+misid);
                    attr.html("发布成功");
                    operateAlert(true, "任务发布成功，所有人员等待执行！", null);
                },
                error: function () {
                    operateAlert(false, null, "任务发布失败，请修改后重新发布！");
                }
            });
        });
    });

    $(selall).on("click", function () {
        $("input[id^=misid-]").each(function () {
            $(this).prop("checked", $(selall).prop("checked"));
        });
    });

    $()

    $("#rmBtn").on("click", function () {
        var length = $("input[id^=misid-]:checked").length;
        var pids = new Array();
        var foot = 0;
        if (length > 0) {
            $("input[id^=misid-]:checked").each(function () {
                pids[foot++] = this.value;
            });
            $.ajax({
                url: "pages/back/admin/plan/delete_plan.action",
                dataType: "json",
                async: true,
                data: {pids: pids},
                traditional: true,
                type: "get",
                success: function (data) {
                    if (data.pid.length == pids.length) {
                        for (i = 0; i < data.pid.length; i++) {
                            $("#pid0-" + data.pid[i]).remove();
                            operateAlert(true, "草稿删除成功！", null);
                        }
                    } else {
                        operateAlert(false, null, "删除失败，不可删除成功发布的任务！");
                    }
                },
                error: function () {
                    operateAlert(false, null, "草稿删除失败！");
                }
            });
        } else {
            operateAlert(false, null, "您还未选择任何数据，请确认您的操作！");
        }
    })
})