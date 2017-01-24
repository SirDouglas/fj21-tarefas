<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib tagdir="/WEB-INF/tags" prefix="caelum" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Alterar Tarefa</title>
   <link href="css/tarefa.css" rel="stylesheet" />
   <link href="css/jquery-ui.css" rel="stylesheet">
    <script src="js/jquery.js"></script>
    <script src="js/jquery-ui.js"></script>
    <script type="text/javascript">
  function finalizaAgora(id) {
    $.post("finalizaTarefa", {'id' : id}, function(resposta) {
      $("#tarefa_"+id).html(resposta);
    });
  }
</script>
</head>
<body>
<h1>Alterar Tarefa</h1>
<form action="alteraTarefa" method="post">
<input type="hidden" name="id" value="${tarefa.id}" />
Descrição <br />
<textarea  name="descricao" rows="5" cols="100">${tarefa.descricao}</textarea>
<br/>
Finalizado? <input type="checkbox" name="finalizado" value="true" ${tarefa.finalizado? 'checked' : ''} /> <br/>
Data de finalização: <br />
<fmt:formatDate  value="${tarefa.dataFinalizacao.time}" pattern="dd/MM/yyyy" var="data"/>
<caelum:campoData id="dataFinalizacao" value="${data}"/>
<br />
<input type="submit" value="Alterar" />
</form>
</body>
</html>