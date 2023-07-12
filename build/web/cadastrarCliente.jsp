<%-- 
    Document   : index
    Created on : Jun 25, 2023, 10:02:25 AM
    Author     : emanuellima
--%>
<%@page import="enumerator.EstadoCivil"%>
<%@page import="enumerator.Sexo"%>
<%@page import="model.MunicipioModel"%>
<%@page import="model.ProvinciaModel"%>
<%@page import="bean.ProvinciaBean"%>
<%@page import="java.util.List"%>
<%@page import="enumerator.TipoCliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page  import="util.LoadEnun" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%-- css of bootstrap --%>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        <link href="css/cadastroClieneStyle.css" rel="stylesheet">
        <title>Criação de Conta</title>
    </head>
    <body>



        <div class="container mt-4">
            <%
                List<ProvinciaModel> provincias = (List<ProvinciaModel>) request.getAttribute("provincias");
                List<MunicipioModel> municipios = (List<MunicipioModel>) request.getAttribute("municipios");

            %>
            <h2>Registar-se</h2>
            <form id="registration-form" method="POST" action="CadastrarClienteServlet">
                <div class="form-group">
                    <label for="nome">Nome completo:</label>
                    <input name="nomeCompleto" type="text" class="form-control" id="nomeCompleto" placeholder="Digite seu nome" required>
                </div>
                <div class="form-group">
                    <label for="nome">Nome do pai:</label>
                    <input name="nomePai" type="text" class="form-control" id="nomePai" placeholder="Digite seu nome">
                </div>
                <div class="form-group">
                    <label for="nome">Nome da mãe:</label>
                    <input name="nomeMae" type="text" class="form-control" id="nomeMae" placeholder="Digite seu nome">
                </div>
                <div class="form-group">
                    <label for="nome">Província</label>
                    <select class="form-control" id="provincia" name="provincia" onchange="selectProvincia()" required>
                        <option value="null">Selecione</option>

                        <% for (int i = 0; i < provincias.size(); i++) {
                        %>
                        <option value="<%= provincias.get(i).getPkProvincia()%>"><%= provincias.get(i)%></option>
                        <%
                            }
                        %>
                    </select>
                </div>

                <div class="form-group">
                    <label for="nome">Municipio:</label>
                    <select class="form-control" id="municipio" name="municipio" required>
                        <option value="null">Selecione</option>
                        <%
                            for (int i = 0; i < municipios.size(); i++) {
                        %>
                        <option value="<%= municipios.get(i).getPkMunicipio()%>"><%= municipios.get(i)%></option>
                        <% }
                        %>
                    </select>
                </div>
                <div class="form-group">
                    <label for="numeroDocumento">Número do documeto:</label>
                    <input name="numeroDocumento" type="text" class="form-control" id="numeroDocumento" placeholder="Digite o número do documento" required>
                </div>
                <div class="form-group">
                    <label for="dataEmissao">Data de emissão:</label>
                    <input name="dataEmissao" type="date" class="form-control" id="dataEmissao" placeholder="Selecione a data" required>
                </div>
                <div class="form-group">
                    <label for="dataValidade">Data de validade:</label>
                    <input name="dataValidade" type="date" class="form-control" id="dataValidade" placeholder="Selecione a data" required>
                </div>
                <div class="form-group">
                    <label for="dataNascimento">Data de nascimento:</label>
                    <input name="dataNascimento" type="date" class="form-control" id="dataNascimento" placeholder="Selecione a data" required>
                </div>

                <div class="form-group">
                    <label for="sexo">Sexo:</label>
                    <select class="form-control" id="sexo" name="sexo" required>
                        <option value="null">Selecione</option>
                        <%  List<Sexo> sexo = LoadEnun.sexo();
                            for (int i = 0; i < sexo.size(); i++) {
                        %>
                        <option value="<%=sexo.get(i).name()%>"><%=sexo.get(i).toString()%></option>
                        <%
                            };

                        %>        
                    </select>
                </div>
                <div class="form-group">
                    <label for="estadoCivil">Estado Civil:</label>
                    <select class="form-control" id="estadoCivil" name="estadoCivil" required>
                        <option value="null">Selecione</option>
                        <%  List<EstadoCivil> estadoCivil = LoadEnun.estadoCivil();
                            for (int i = 0; i < estadoCivil.size(); i++) {
                        %>
                        <option value="<%=estadoCivil.get(i).name()%>"><%=estadoCivil.get(i).toString()%></option>
                        <%
                            };

                        %>        
                    </select>
                </div>

                <div class="form-group">
                    <label for="altura">Altura:</label>
                    <input name="altura" type="number" class="form-control" id="altura" placeholder="Digite a sua altura" required>
                </div>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input name="email" type="email" class="form-control" id="email" placeholder="Digite seu email" required>
                </div>
                <div class="form-group">
                    <label for="telefone">Telefone:</label>
                    <input name="telefone" type="tel" class="form-control" id="telefone" placeholder="Digite seu telefone" required>
                </div>
                <div class="form-group">
                    <label for="senha">Senha:</label>
                    <input name="senha" type="password" class="form-control" id="senha" placeholder="Digite sua senha" required>
                </div>
                <div class="form-group">
                    <label for="confirmarSenha">Confirmar Senha:</label>
                    <input name="confirmarSenha" type="password" class="form-control" id="confirmarSenha" placeholder="Confirme sua senha" required>
                </div>
                <div class="form-group">
                    <label for="tipo">Tipo de Cliente:</label>
                    <select class="form-control" id="tipo" name="tipoCliente" required>
                        <option value="null">Selecione</option>
                        <%                            List<TipoCliente> tiposCliente = LoadEnun.tipoCliente();
                            for (int i = 0; i < tiposCliente.size(); i++) {
                        %>
                        <option value="<%=tiposCliente.get(i).name()%>"><%=tiposCliente.get(i).toString()%></option>
                        <%
                            };

                        %>        
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Criar Conta</button>
                <div class="mt-5 row justify-content-center login-form__footer" style="color:red;"> 
                    <%=(request.getAttribute("typeMessage") != null ? request.getAttribute("typeMessage") : "")%>
                </div>
            </form>
        </div>

        <%-- JS of bootstrap --%>    
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>    
        <script src="js/cadastrarCliente.js"></script>
    </body>
</html>
