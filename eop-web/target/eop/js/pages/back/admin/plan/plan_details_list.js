$(function () {
    $("span[id^=eid-]").each(function () {
        $(this).on("click", function () {
            eid = this.id.split("-")[1];
            $("#userInfo").modal("toggle");
        });
    });

    $("#remove").on("click", function () {
        $("#tbody").html("");
        $("#did *:gt(0)").remove();
    });

    $("#delete").on("click", function () {
        $("#tbody").html("");
        $("#did *:gt(0)").remove();
    });

    $("#did").on("change", function () {
        let did = $("#did option:selected").val();
        let pid = $("table[id^=table2]").attr("pid");
        $.ajax({
            url: "pages/back/admin/plan/get_did_emp_list.action",
            dataType: "json",
            async: true,
            data: {did: did, pid: pid},
            traditional: true,
            type: "post",
            success: function (data) {
                $("#tbody").html("");
                var map = {};
                for (w = 0; w < data.pidList.length; w++) {
                    let eid1 = data.pidList[w];
                    map[eid1] = "disabled";
                }
                for (x = 0; x < data.empList.length; x++) {
                    var tr = "<tr id = tr2-" + data.empList[x].eid + "></tr>";
                    $("#tbody").append(tr);
                    var photo = "<td class=text-center id=photo2-" + data.empList[x].eid + "></td>";
                    $("#tr2-" + data.empList[x].eid).append(photo);
                    var img = "<img src= upload/emp/" + data.empList[x].photo + " style=width:20px;/>";
                    $("#photo2-" + data.empList[x].eid).append(img);
                    var eid = "<td class=text-center id=eid2-" + data.empList[x].eid + ">" + data.empList[x].eid + "</td>";
                    $("#tr2-" + data.empList[x].eid).append(eid);
                    var ename = "<td class=text-center id=ename2-" + data.empList[x].eid + ">" + data.empList[x].ename + "</td>";
                    $("#tr2-" + data.empList[x].eid).append(ename);
                    var lid = "<td class=text-center id=level2-" + data.empList[x].lid + ">"
                        + data.levelList[data.empList[x].lid].title + "</td>";
                    $("#tr2-" + data.empList[x].eid).append(lid);

                    var add = "<td class=text-center><button " + map[data.empList[x].eid] +" type=\"button\" class=\"btn btn-danger btn-xs\" " +
                        "id=add-" + data.empList[x].eid + ">"
                        + "<span class=\"glyphicon glyphicon-plus-sign\" ></span>&nbsp;增加</button></td>";
                    $("#tr2-" + data.empList[x].eid).append(add);
                }

                $("button[id^=add-]").each(function () {
                    $(this).on("click", function () {
                        eid = this.id.substring(this.id.indexOf("-") + 1, this.id.length);
                        let pid = $("table[id^=table2]").attr("pid");
                        $.ajax({
                            url: "pages/back/admin/plan/emp_plan_add.action",
                            dataType: "json",
                            async: true,
                            data: {eid: eid, pid: pid},
                            traditional: true,
                            type: "post",
                            success: function (data) {
                                $("#add-" + eid).attr("disabled", true);
                                operate1Alert(data.flag, "任务人员增加成功！", "添加失败，该人员当前时段已有任务安排！");
                            },
                            error: function () {
                                operate1Alert(false, null, "添加失败，该人员当前时段已有任务安排！");
                            }
                        });
                    });
                });
            },
            error: function () {
                operateAlert(false, null, "查询失败！");
            }
        });
    });

    /**
     * 警告框操作信息，ID必须为“alert1Div”
     * @param flag 操作成功或失败的标记
     * @param suctext 操作成功时的显示文本内容
     * @param faltext 操作失败时的显示文本内容
     */
    function operate1Alert(flag, suctext, faltext) {
        if (flag) {
            $("#alert1Div").attr("class", "alert alert-success");
            $("#alert1Text").text(suctext);
        } else {
            $("#alert1Div").attr("class", "alert alert-danger");
            $("#alert1Text").text(faltext);
        }
        $("#alert1Div").fadeIn(500, function () {
            $("#alert1Div").fadeOut(1000);
        });
    }

    $("#addEmpBut").on("click", function () {
        $("#planEmpInfo").modal("toggle");
        let pid = $("table[id^=table2]").attr("pid");
        $.ajax({
            url: "pages/back/admin/plan/plan_details_modal.action",
            dataType: "json",
            async: true,
            data: {pid: pid},
            traditional: true,
            type: "post",
            success: function (data) {
                $("#tbody").html("");
                var map = {};
                for (w = 0; w < data.pidList.length; w++) {
                    let eid1 = data.pidList[w];
                    map[eid1] = "disabled";
                }
                for (i = 0; i < data.deptList.length; i++) {
                    let dname = data.deptList[i].dname;
                    $("#did").append("<option value= " + data.deptList[i].did + " >" + dname + "</option>");
                }
                for (x = 0; x < data.empList.length; x++) {
                    var tr = "<tr id = tr2-" + data.empList[x].eid + "></tr>";
                    $("#tbody").append(tr);
                    var photo = "<td class=text-center id=photo2-" + data.empList[x].eid + "></td>";
                    $("#tr2-" + data.empList[x].eid).append(photo);
                    var img = "<img src= upload/emp/" + data.empList[x].photo + " style=width:20px;/>";
                    $("#photo2-" + data.empList[x].eid).append(img);
                    var eid = "<td class=text-center id=eid2-" + data.empList[x].eid + ">" + data.empList[x].eid + "</td>";
                    $("#tr2-" + data.empList[x].eid).append(eid);
                    var ename = "<td class=text-center id=ename2-" + data.empList[x].eid + ">" + data.empList[x].ename + "</td>";
                    $("#tr2-" + data.empList[x].eid).append(ename);
                    var lid = "<td class=text-center id=level2-" + data.empList[x].lid + ">"
                        + data.levelList[data.empList[x].lid].title + "</td>";
                    $("#tr2-" + data.empList[x].eid).append(lid);
                    var add = "<td class=text-center><button "+ map[data.empList[x].eid]+" type=\"button\" class=\"btn btn-danger btn-xs\" " +
                        "id=add-" + data.empList[x].eid + ">"
                        + "<span class=\"glyphicon glyphicon-plus-sign\" ></span>&nbsp;增加</button></td>";
                    $("#tr2-" + data.empList[x].eid).append(add);
                }

                $("button[id^=add-]").each(function () {
                    $(this).on("click", function () {
                        eid = this.id.substring(this.id.indexOf("-") + 1, this.id.length);
                        let pid = $("table[id^=table2]").attr("pid");
                        $.ajax({
                            url: "pages/back/admin/plan/emp_plan_add.action",
                            dataType: "json",
                            async: true,
                            data: {eid: eid, pid: pid},
                            traditional: true,
                            type: "post",
                            success: function (data) {
                                if (data.flag) {19
                                    $("#currentCount").html(data.amount);
                                    $("#empCount").html(data.amount);
                                    var tr1 = "<tr id=tr-" + eid + "><tr>";
                                    $("#tbody-1").append(tr1);
                                    var photo1 = "<td class=text-center>" +
                                        "<img src=upload/emp/" + data.emp.photo + " style=\width:20px;/></td>";
                                    $("#tr-" + eid).append(photo1);
                                    var ename1 = " <td class =text-center>" +
                                        "<span style =cursor:pointer;>" + data.emp.ename + "</span></td>";
                                    $("#tr-" + eid).append(ename1);
                                    var ltitle1 = "<td class=\"text-center\">" + data.levelList[data.emp.lid].title + "</td>";
                                    $("#tr-" + eid).append(ltitle1);
                                    var dept1 = "<td class=text-center>" + data.deptList[data.emp.did].dname + "</td>";
                                    $("#tr-" + eid).append(dept1);
                                    var phone1 = "<td class=text-center>" + data.emp.phone + "</td>";
                                    $("#tr-" + eid).append(phone1);
                                    var remove1 = "<td class=\"text-center\"><button type=button class=\"btn btn-primary btn-xs\" id=remove-" + data.emp.eid + ">" +
                                        "<span class=\"glyphicon glyphicon-edit\"></span>&nbsp;移除</button></td>";
                                    $("#tr-" + eid).append(remove1);
                                    $("#add-" + eid).attr("disabled", true);
                                    operate1Alert(data.flag, "任务人员增加成功！", "添加失败，该人员当前时段已有任务安排！");
                                    $("button[id^=remove-]").each(function () {
                                        $(this).on("click", function () {
                                            eid = this.id.substring(this.id.indexOf("-") + 1, this.id.length);
                                            let pid = $("table[id^=table2]").attr("pid");
                                            removeEid(eid, pid);
                                        });
                                    });
                                } else {
                                    operate1Alert(data.flag, null, "添加失败，该人员当前时段已有任务安排！");
                                }
                            },
                            error: function () {
                                operate1Alert(false, null, "添加失败，该人员当前时段已有任务安排！");
                            }
                        });
                    });
                });
            },
            error: function () {
                operateAlert(false, null, "查询失败！");
            }
        });
    });

    $("button[id^=remove-]").each(function () {
        $(this).on("click", function () {
            eid = this.id.substring(this.id.indexOf("-") + 1, this.id.length);
            let pid = $("table[id^=table2]").attr("pid");
            removeEid(eid, pid);
        });
    });

    function removeEid(eid, pid) {
        if (eid != null) {
            $.ajax({
                url: "pages/back/admin/plan/delete_plan_personnel.action",
                dataType: "json",
                async: true,
                data: {eid: eid, pid: pid},
                traditional: true,
                type: "post",
                success: function (data) {
                    $("#tr-" + eid).remove();
                    $("#currentCount").html(data.amount);
                    $("#empCount").html(data.amount);
                    operateAlert(true, "任务人员移除成功！", null);
                },
                error: function () {
                    operateAlert(false, null, "任务人员移除失败！");
                }
            });
        }
    }
})