<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="modal fade" id="planEmpInfo"  tabindex="-1" aria-labelledby="modalTitle" aria-hidden="true" data-keyboard="true">
	<div class="modal-dialog" style="width: 1000px">
		<div class="modal-content">
			<div class="modal-header">
				<button class="close" type="button" id="remove" data-dismiss="modal" aria-hidden="true">&times;</button>
				<div class="form-group" id="didDiv">
					<!-- 定义表单提示文字 -->
					<div class="alert alert-success" id="alert1Div" style="display:none;">
						<button type="button" class="close" data-dismiss="alert">&times;</button>
						<span id="alert1Text"></span>
					</div>
					<label class="col-md-2 control-label" for="did">员工所在部门：</label>
					<div class="col-md-5">
						<select id="did" name="did" class="form-control">
								<option value="9999">====== 请选择雇员所在部门 ======</option>
						</select>
					</div>
				</div>
			</div>
			<div class="modal-body">

				<div id="memberBasicInfo">
					<table class="table table-condensed table-hover" id="empTable">
						<thead>
							<tr>
								<th class="text-center"><strong>照片</strong></th>
								<th class="text-center"><strong>雇员编号</strong></th>
								<th class="text-center"><strong>姓名</strong></th>
								<th class="text-center"><strong>级别</strong></th>
								<th class="text-center"><strong>操作</strong></th>
							</tr>
						</thead>
						<tbody id="tbody">

						</tbody>
					</table>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" id="delete" class="btn btn-default" data-dismiss="modal">关闭窗口</button>
			</div>
		</div>
	</div>
</div>