<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="modal fade" id="scheduleModal"  tabindex="-1" aria-labelledby="modalTitle" aria-hidden="true" data-keyboard="true">
	<div class="modal-dialog" style="width: 1000px">
		<div class="modal-content">
			<div class="modal-header">
				<button class="close" id="remove1" type="button" data-dismiss="modal" aria-hidden="true">&times;</button>
				<div class="form-group" id="didDiv">
					<strong><span class="glyphicon glyphicon-eye-open"></span>&nbsp;任务详情</strong></h3>
				</div>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-xs-12">
						<table class="table table-condensed table-striped table-hover" id="myPlan">

						</table>
					</div>
				</div>
				<div class="row">
					<div class="panel-group" id="news">
						<div class="panel panel-info">
							<div class="panel-heading">
								<h4 class="panel-title"> 
									<a data-toggle="collapse" data-parent="news" href="#contentOne">
										<strong><span class="glyphicon glyphicon-user"></span>&nbsp;任务参与人员清单（总人数：<span id="currentCount"></span>人）</strong>
									</a>
								</h4>
							</div>
							<div id="contentOne" class="panel-collapse collapse in">
								<div class="panel-body">
									<table class="table table-condensed">
										<thead>
											<tr>
												<th class="text-center">照片</th> 
												<th class="text-center">姓名</th>
												<th class="text-center">级别</th>
												<th class="text-center">所在部门</th>
												<th class="text-center">联系电话</th>
											</tr>
										</thead>
										<tbody id="mytbody">

										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" id="delete1" class="btn btn-default" data-dismiss="modal">关闭窗口</button>
			</div>
		</div>
	</div>
</div>
