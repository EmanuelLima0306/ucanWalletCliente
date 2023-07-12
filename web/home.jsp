<%-- 
    Document   : index
    Created on : Jun 25, 2023, 10:02:25 AM
    Author     : emanuellima
--%>
<%@page import="model.TransacaoModel"%>
<%@page import="model.ContaModel"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.UsuarioModel" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%-- css of bootstrap --%>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        <link href="css/homeStyle.css" rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <body>

        <%
            UsuarioModel usuarioLogado = (UsuarioModel) request.getSession().getAttribute("Usuario");
            ContaModel contaSelecionada = (ContaModel) request.getSession().getAttribute("contaSelected");
            List<ContaModel> contas = (List<ContaModel>) request.getSession().getAttribute("contas");
            List<TransacaoModel> transacaoModels = (List<TransacaoModel>) request.getSession().getAttribute("transacoes");
            boolean isConta = contaSelecionada != null;
        %>
        <!-- Menu no topo -->
        <nav class="navbar navbar-expand-lg" style="background-color: var(--cor-background-1)">
            <a class="navbar-brand" href="#">UcanWallet</a>
            <form method="POST" action="LoginServlet" id="formLogout">
                <div class="user-profile">
                    <img src="image/png-transparent-avatar-user-computer-icons-software-developer-avatar-child-face-heroes.png" alt="User Avatar">
                    <span class="user-name"><%=usuarioLogado%></span>

                    <input type="hidden" name="action" value="logout">
                    <a onclick="eventButtonLogout()" id="buttonLogout" type="submit">Terminar Sessão</a>


                </div>
            </form>
        </nav>

        <!-- Divs no centro -->
        <div class="container-fluid body-page">
            <div class="row">
                <div class="col-2 sidebar">
                    <div class="divContas">
                    <!-- Cards de contas do usuário -->
                    <%

                        for (int indexConta = 0; indexConta < contas.size(); indexConta++) {
                    %>
                    <div class="account-card <%= contaSelecionada.getPkConta() == contas.get(indexConta).getPkConta() ? "selected" : ""%>" data="<%=contas.get(indexConta).getPkConta()%>" onclick="selectAccount(this)">
                        Nº Conta: <%=contas.get(indexConta).getNumeroConta()%>
                        Saldo Disp.: <%=contas.get(indexConta).getSaldoDisponivel()%>

                        <canvas class="meuGrafico"></canvas>
                    </div>
                    <%
                        }
                    %>
                    </div>
                    <div class="btn-nova-conta" onclick="" data-bs-toggle="modal" data-bs-target="#modal-criar-conta">+ Conta</div>
                    <!-- Adicione mais cards de contas aqui -->
                </div>
                <div class="col-10 main-content">
                    <div class="row top-row">
                        <!-- Conteúdo da primeira div no topo -->
                        <div class="card bg-transparent">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-5">
                                        <div class="row">
                                            <div class="col-sm-6">
                                                <h2 class="titleWhite"><%= isConta ? "Nº Conta: " + contaSelecionada.getNumeroConta() : "Selecione uma conta"%></h2>
                                            </div>
                                            <div class="col-sm-6">
                                                <h2 class="sub-titleGray">Cópia a chave Publica</h2>
                                                <input type="text" id="publicKeyInput" placeholder="<%= isConta ? contaSelecionada.toString() : ""%>" value="<%= isConta ? contaSelecionada.toString() : ""%>" hidden>
                                                <div class="transparent-card" onclick="copiarChavePublica()">Copiar</div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-6">
                                                <h2 class="sub-titleGray">Total Disponível</h2>
                                                <h2 class="titleWhite"><%= isConta ? contaSelecionada.getSaldoDisponivel() : "0.00"%></h2>
                                            </div>
                                            <div class="col-sm-6 mt-2">
                                                <h2 class="sub-titleGray">Total Contabilístico</h2>
                                                <h2 class="titleWhite"><%= isConta ? contaSelecionada.getSaldoContablistico() : "0.00"%></h2>
                                            </div>
                                        </div>
                                        <div class="text-left">
                                            <%--<button class="btn btn-primary">Depositar</button>--%>
                                            <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modal-transferencia">Transferir</button>
                                        </div>
                                    </div>
                                    <div class="col-7">
                                        <h2 class="sub-titleGray">Total de Entradas</h2>
                                        <h2 class="titleWhite">200003038</h2>
                                        <div class="progress">
                                            <div class="progress-bar progress-entrada" role="progressbar" style="width: 70%;" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100"></div>
                                        </div>
                                        <h2 class="sub-titleGray">Total de Saídas</h2>
                                        <h2 class="titleWhite">200003038</h2>
                                        <div class="progress">
                                            <div class="progress-bar progress-saida" role="progressbar" style="width: 30%;" aria-valuenow="30" aria-valuemin="0" aria-valuemax="100"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row bottom-row">
                        <!-- Conteúdo da segunda div na parte inferior -->

                        <div class="table-responsive" style="max-height: 500px; overflow-y: auto;">

                            <table class="table table-striped custom-table">
                                <thead>
                                    <tr>

                                        <th scope="col">ID</th>
                                        <th scope="col">Conta</th>
                                        <th scope="col">Valor</th>
                                        <th scope="col">Data Envio</th>
                                        <th scope="col">Data Validação</th>
                                        <th scope="col"></th>
                                    </tr>
                                </thead>
                                <tbody>

                                    <% for (int i = 0; i < transacaoModels.size(); i++) {%>
                                    <tr scope="row">



                                        <td>
                                            <%= transacaoModels.get(i).getPkTransacao().intValue()%>
                                        </td>
                                        <td>
                                            <%= transacaoModels.get(i).getContaDestino().getNumeroConta()%>
                                            <small class="d-block"><%= transacaoModels.get(i).getContaDestino().getClienteModel().getPessoaModel().getNomeCompleto()%></small>
                                        </td>
                                        <td><a href="#"><%=contaSelecionada.getPkConta() == transacaoModels.get(i).getContaOrigem().getPkConta() ? "-" + transacaoModels.get(i).getValor() : "+"+transacaoModels.get(i).getValor()%></a></td>
                                        <td><%= transacaoModels.get(i).getDataTransacao()%></td>
                                        <td><%= transacaoModels.get(i).getDataValidacao()%></td>
                                        <td><a href="#" class="more"><%= transacaoModels.get(i).getEstado().toString()%></a></td>

                                    </tr>
                                    <%
                                        }
                                    %>

                                </tbody>
                            </table>
                        </div>


                    </div>
                </div>
            </div>
        </div>

        <!-- Modal Conta -->
        <div class="modal fade" id="modal-criar-conta" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content card-modal">
                    <div class="modal-header">
                        <%--<h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>--%>
                        <div class="close" data-bs-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </div>
                    </div>
                    <div class="modal-body">
                        <jsp:include page="criarConta.jsp"/>
                    </div>
                    <%--<div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary">Save changes</button>
                    </div>--%>
                </div>
            </div>
        </div>
        <!-- Modal Transferência -->
        <div class="modal fade" id="modal-transferencia" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content card-modal">
                    <div class="modal-header">
                        <%--<h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>--%>
                        <div class="close" data-bs-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </div>
                    </div>
                    <div class="modal-body">
                        <jsp:include page="transacao.jsp"/>
                    </div>
                    <%--<div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary">Save changes</button>
                    </div>--%>
                </div>
            </div>
        </div>


        <%-- JS of bootstrap --%>    
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

        <script src="js/home.js"></script>
    </body>
</html>
