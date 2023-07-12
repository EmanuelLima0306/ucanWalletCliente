<%-- 
    Document   : index
    Created on : Jun 25, 2023, 10:02:25 AM
    Author     : emanuellima
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%-- css of bootstrap --%>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        <link href="css/style.css" rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
                    <div class="login-container">
                        <div class="login-header">
                            <h2>Ucan Wallet</h2>
                            <p>Faça login para acessar sua conta</p>
                        </div>
                        <form method="POST" action="LoginServlet" class="login-form" >
                            <input type="hidden" name="action" value="login">
                            <div class="form-group">
                                <input type="email" class="form-control" name="usuario" id="usuario" placeholder="Digite seu email" >
                            </div>
                            <div class="form-group">
                                <input type="password" name="senha" class="form-control" id="senha" placeholder="Digite sua senha">
                            </div>
                            <button type="submit" class="btn btn-primary btn-block">Entrar</button>
                        </form>
                        <div class="login-footer">
                            <p>Não tem uma conta? <a href="cadastrarCliente">Registre-se</a></p>
                        </div>
                        <div class="mt-5 row justify-content-center login-form__footer" style="color:red;"> 
                            <%=(request.getAttribute("typeMessage") != null ? request.getAttribute("typeMessage") : "")%>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%-- JS of bootstrap --%>    
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
        <script src="js/index.js"></script>
    </body>
</html>
