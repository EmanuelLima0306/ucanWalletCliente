<%-- 
    Document   : criarConta
    Created on : Jun 27, 2023, 5:17:19 PM
    Author     : emanuellima
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        <link href="css/criarContaStyle.css" rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <div class="form-container">
                <h2 class="titleWhite">Transferência</h2>
                <form method="POST" action="transacao">
                    <div class="form-group">
                        <label class="sub-titleWhite" for="saldo">Número de conta:</label>
                        <input type="number" class="form-control" id="numeroConta" name="numeroConta" required>
                    </div>
                    <div class="form-group">
                        <label class="sub-titleWhite" for="saldo">Valor:</label>
                        <input type="number" class="form-control" id="valor" name="valor" required>
                    </div>

                    <button type="button" class="btn btn-primary">Transferir</button>
                    <div class="mt-5 row justify-content-center login-form__footer" style="color:red;"> 
                        <%=(request.getAttribute("typeMessage") != null ? request.getAttribute("typeMessage") : "")%>
                    </div>
                </form>

            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    </body>
</html>
