$(function () {

    $("#remove1").on("click", function () {
        $("#mytbody").html("");
        $("#myPlan").html("");
    });

    $("#delete1").on("click", function () {
        console.log("$$$$$$$$$");
        $("#mytbody").html("");
        $("#myPlan").html("");
    });


    $("span[id^=misid-]").each(function () {
        $(this).on("click", function () {
            pid = this.id.split("-")[1];
            $("#scheduleModal").modal("toggle");
            $.ajax({
                url: "pages/back/admin/plan/my_plan.action",
                dataType: "json",
                async: true,
                data: {pid: pid},
                traditional: true,
                type: "post",
                success: function (data) {
                    $("#currentCount").html(data.plan.amount);
                    var my = $("#myPlan");
                    var planName = "<tr><td style=\"width:15%;\"><strong>任务名称：</strong></td>" +
                        "<td>" + data.plan.title + "</td></tr>";
                    my.append(planName);
                    var planType = "<tr><td><strong>任务类型：</strong></td><td>" + data.plan.item + "</td></tr>";
                    my.append(planType);

                    var planAmount = "<tr><td><strong>任务人数：</strong></td><td><" +
                        "span id=\"empCount\">" + data.plan.amount + "</span>人</td></tr>";

                    my.append(planAmount);
                    var planstarttime = "<tr><td><strong>开始时间：</strong></td>" +
                        "<td>" + getSmpFormatDateByLong(data.plan.starttime, true) + "</td></tr>";
                    my.append(planstarttime);
                    var planstarttime = "<tr><td><strong>结束时间：</strong></td>" +
                        "<td>" + getSmpFormatDateByLong(data.plan.endtime, true) + "</td></tr>";
                    my.append(planstarttime);
                    var plannote = "<tr><td><strong>任务说明：</strong></td><td>" + data.plan.note + "</td></tr>";
                    my.append(plannote);

                    for (i = 0; i < data.empList.length; i++) {
                        var tr1 = "<tr id=trtest-" + data.empList[i].eid +"><tr>";
                        $("#mytbody").append(tr1);
                        var photo1 = "<td class=text-center>" +
                            "<img src=upload/emp/" + data.empList[i].photo + " style=\width:20px;/></td>";
                        $("#trtest-" + data.empList[i].eid).append(photo1);
                        var ename1 = " <td class =text-center>" +
                            "<span style =cursor:pointer;>" + data.empList[i].ename + "</span></td>";
                        $("#trtest-" + data.empList[i].eid).append(ename1);
                        var ltitle1 = "<td class=\"text-center\">" + data.levelList[data.empList[i].lid].title + "</td>";
                        $("#trtest-" + data.empList[i].eid).append(ltitle1);
                        var dept1 = "<td class=text-center>" + data.deptList[data.empList[i].did].dname + "</td>";
                        $("#trtest-" + data.empList[i].eid).append(dept1);
                        var phone1 = "<td class=text-center>" + data.empList[i].phone + "</td>";
                        $("#trtest-" + data.empList[i].eid).append(phone1);
                    }
                },
                error: function () {
                }
            });

        });
    });
})