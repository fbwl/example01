<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<c:set var="url" value="${pageContext.request.requestURL }"/>
<c:set var="uri" value="${pageContext.request.requestURI }"/>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<meta charset="UTF-8">
<title>메모</title>

<script>
$(document).ready(function(){
   list();
   $('#btnSave').click(function(){
      insert();
   });

});
function insert(){
   var writer = $("#writer").val();
   var content = $("#content").val();
   var param = "writer="+writer + "&content="+content;
   
   $.ajax({
      type:"post",
      data: param,
      url: "${path}/memo_servlet/writeProc.do",
      success: function(){
         list();
         $("#writer").val("");
         $("#content").val("");
      }
   });
}
function list(){
   var param = "search_gubun=&sdata="
   $.ajax({
      type: "post",
      data: " ",
      url: "${path}/memo_servlet/list.do?pageNumber=${pageNumber}",
      success: function(result){
         $("#result").html(result);
      }
   });
}
</script>
</head>
<body>
   <form name="memoForm">
      <h2>메모장</h2>
      <table width="400">
         <tr>
            <td>
               이름 : <input type="text" id="writer" size="20"><br> 
               내용 : <input type="text" id ="content" size="35"> &nbsp;<input type="button"  id="btnSave" value="확인" ><br>
            </td>
         </tr>
      </table>
   </form>
   <div id="result"></div>
</body>
</html>