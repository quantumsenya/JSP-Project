// 게시물관련
$(function(){
	$('.viewMode').hide(); // 파일폼 숨김
	
	let viewForm = $('#viewForm');
	let titleObj = $('input[name="title"]');
	
	let contentObj = $('textarea[name="content"]');
	let origImg = $('.origImg').clone();
	
	let titleVal = titleObj.val();
	let contentVal = contentObj.val();
	let imageFile= "${board.imageFileName}";
	let pTag=$('.preview p').html();
	
	// 수정모드
	$('.toModForm').on('click',function(){
		$('input[name="title"],textarea[name="content"]').attr("readonly",false);
		$('.viewMode').show();
		$(this).closest('tr').hide();
	})
	
	// 뷰모드
	$('.backViewMode').on('click', function(){
		$('input[name="title"],textarea[name="content"]').attr("readonly",true);
		$('.viewMode').hide();
		$(this).closest('tr').prev().show();
		$('.preview').html(origImg); // 원본이미지 복원
		$('input[type="file"]').val(''); // 파일폼 초기화
		titleVal.val(titleVal);
		contentObj.val(contentVal);
		if(imageFile==''||imageFile==null){
			$('.preview').html(pTag);
		}
	});
	
	// 목록으로
	$('.toList').on('click',function(){
		viewForm.attr({
			"action":`${contextPath}/board/shareboard`,
			"method":"get"
		}).empty() // 파라미터 정보 삭제
		.submit();
	});
	
	// 수정처리
	$('.modify').on('click',function(){
		viewForm.attr({
			"action":`${contextPath}/board/shareboard/modShareBoard`,
			"method":"post"
		}).submit();
	});
	
	// 종료하기
	$('.Ended').on('click',function(){
		viewForm.attr({
			"action":`${contextPath}/board/shareboard/isEnd`,
			"method":"post"
		}).submit();
	});
	
	// 삭제처리
	$('.remove').on('click',function(){
		viewForm.attr({
			"action":`${contextPath}/board/shareboard/removeShareBoard`,
			"method":"post"
		}).submit();
	});
	
});
	
// 댓글관련
function get_reply_list() {
	
}


$(function(){
	let bno = $('input[name="bno"]').val()
	
	// 댓글 목록
	replyService.list(bno);
	
	// 댓글 쓰기
	$('.reply_write').on('click', function(){
		let writer = $('.reply_writer').val()
		let reply = $('.reply_content').val()
		
		let replyVO = {
			bno : bno,
			reply : reply,
			writer : writer
		}
		replyService.write(replyVO);
	});
	
	// 댓글 수정 버튼 이벤트
	$('.replyList').on('click','.reply_modBtn',function(){
		let rno = $(this).closest('div').data('rno');
		alert('수정입니다.' + rno +'번')
	});
	
	// 댓글 삭제 버튼 이벤트
	$('.replyList').on('click','.reply_delBtn',function(){
		let rno = $(this).closest('div').data('rno');
		
		let replyVO = {
			bno : bno,
			rno : rno
		}
		replyService.remove(replyVO);
	});	
	
})