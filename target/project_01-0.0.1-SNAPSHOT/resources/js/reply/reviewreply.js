let replyService = {
	
	list : function(bno){
		console.log('댓글목록')
		$.ajax({
			type : 'get',
			url : `${contextPath}/reply/reviewReplyList`,
			data : {bno : bno},
			success : function(replyList){
				replyListRender(replyList);
			},
			error : function(){
				alert('댓글목록 조회 실패')
			}
			
		})
	},
	detail : function(){
		console.log('댓글조회')
	},
	write : function(replyVO){
		$.ajax({
			type : 'post',
			url : `${contextPath}/reply/reviewReplyWrite`,
			data : replyVO,
			success : function(result){
				$('.reply_content').val('');
				$('#feedback').find('.modal-body').html(result);
				$('#feedback').modal('show');
				replyService.list(replyVO.bno);
			},
			error : function(){
				alert('에러ㅋㅋ')
			}
		}); // ajax end
		
	},
	modify : function(){
		console.log('댓글수정')
	},
	remove : function(replyVO){ // 댓글 삭제
		$.ajax({
			type : 'post',
			url : `${contextPath}/reply/reviewReplyRemove`,
			data : replyVO,
			success : function(result){
				$('#feedback').find('.modal-body').html(result);
				$('#feedback').modal('show');
				replyService.list(replyVO.bno);
			},
			error : function(){
				alert('에러ㅋㅋ')
			}
		}) // ajaxEnd
		
	}
	
}

// 댓글 화면 렌더링
function replyListRender(replyList){
	let output = "";
		for(let r of replyList){
			output+= 
				`<li class="list-group-item d-flex justify-content-between bg-light text-dark">
					<div>
						<p>${r.reply}</p>
						<span class="badge badge-pill badge-primary">${r.writer}</span>
						<span class="badge badge-pill badge-info">${r.replyDate}</span>
					</div>`
					
			if(r.writer==auth.id&&auth.grade!='관리자'||r.writer==auth.id&&auth.grade!='운영자') { //로그인한 사용자(관리자가 아님)
				output+= `
				<div class="align-self-center" data-rno="${r.rno}">
					<button class="btn btn-sm btn-info reply_modBtn">수정</button>
					<button class="btn btn-sm btn-danger reply_delBtn">삭제</button>
				</div>
				`;
			}
			if(auth.grade=='관리자'&& r.writer==auth.id || auth.grade=='운영자'&& r.writer==auth.id){ //관리자 로그인, 작성자 관리자
				output+= `
				<div class="align-self-center" data-rno="${r.rno}">
					<button class="btn btn-sm btn-info reply_modBtn">수정</button>
					<button class="btn btn-sm btn-danger reply_delBtn">삭제</button>
				</div>
				`;
			}
			else if(auth.grade=='관리자' || auth.grade=='운영자'){ // 관리자
				output+= `
				<div class="align-self-center" data-rno="${r.rno}">
					<button class="btn btn-sm btn-danger reply_delBtn">삭제</button>
				</div>
				`;
			}
		}
		output+=`</li>`;
	$('.replyList ul').html(output);
	

}	
