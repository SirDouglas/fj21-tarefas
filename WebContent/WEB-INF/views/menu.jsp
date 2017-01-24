<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menu.</title>
  <link href="css/tarefa.css" rel="stylesheet" />
     <link href="css/jquery-ui.css" rel="stylesheet">
</head>
<body>
<h2>Página Inicial da Lista de Tarefa  </h2>
<p>Bem vindo, ${usuarioLogado.login} </p>
<a href="listaTarefa"> Clique aqui</a> para acessar a lista de tarefas <br />
<a href="logout">Sair do Sistema</a>
</body>
</html>