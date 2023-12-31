<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- member_join_list.jsp // 가입신청내역 -->

<%@include file="admin_top.jsp" %>

<!-- 삭제 script -->
<script type="text/javascript">
	function checkDel(mem_num){
		var isDel = window.confirm("정말로 삭제하겠습니까?")
		if (isDel){
			document.f.mem_num.value = mem_num
			document.f.submit()
		}
	}
</script>
<script type="text/javascript">
	function sendForm(mem_num){
		window.open("admin_member_sendEmail.do?mem_num="+mem_num, "", "width=640, height=400")
	}
</script>

<%@include file="member_sidebar.jsp" %>

		<!-- 내용 // 가입신청 목록-->
		<div class="container text-center">
			<p>
			<p>
				<div class="row row-cols-1" style="width: 800px;">
			    	<div class="col">
			    		<p class="fs-5">비즈니스 회원 가입 신청 내역</p>
			    	</div>
			    	<p>
					<div class="col" style="overflow: scroll; height: 800px;">
						<table class="table table-hover table-bordered table align-middle" width="80%" height="100">
							<thead class="table-light">
							<tr align="center">
								<th width="10%" height="40">#</th>
								<th width="15%">아이디</th>
								<th width="15%">이름</th>
								<th width="15%">닉네임</th>
								<th width="15%">신청일</th>
								<th width="10%">메일<br>발송</th>
								<th width="10%">승인</th>
								<th width="10%">삭제</th>
							</tr>
							</thead>
							<tbody>
							<c:if test="${empty listMember}">
								<tr>
									<td colspan="8">등록된 회원이 없습니다.</td>
								</tr>
							</c:if>
							<c:if test="${not empty listMember}">
							<c:forEach items="${listMember}" var="dto">
								<tr align="center">
									<td>${dto.mem_num}</td>
									<td>${dto.mem_id}</td>
									<td>${dto.mem_name}</td>
									<td>${dto.mem_nickname}</td>
									<td>${dto.mem_regdate}</td>
									<td><a href="javascript:sendForm('${dto.mem_num}')">발송</a></td>
									<td><a href="admin_member_join_ok.do?mem_num=${dto.mem_num}">승인</a></td>
									<td><a href="javascript:checkDel('${dto.mem_num}')">삭제</a></td>
								</tr>
							</c:forEach>
							</c:if>
							</tbody>
						</table>
						<form name="f" action="admin_member_delete.do" method="post">
							<input type="hidden" name="mem_num"/>
							<input type="hidden" name="view" value="join"/>
						</form>
					</div>
				</div>
			</div>
	</main>
</div>


<%@include file="admin_bottom.jsp" %>
