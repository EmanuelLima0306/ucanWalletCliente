
document.addEventListener("DOMContentLoaded", function () {

    // Seu código JavaScript aqui
    getGraphicsCardCont();
    getTransacoes();
    pesquisa();
});

function pesquisa() {
    var campoPesquisa = document.getElementById("campoPesquisa");
    campoPesquisa.addEventListener("keyup", function () {

        // Obter o texto digitado no campo de pesquisa
        var textoPesquisa = campoPesquisa.value.toLowerCase();
        console.log(textoPesquisa);
        var options = {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        };

        // Especifique a URL da sua servlet
        var url = 'pesquisa?textoPesquisa=' + textoPesquisa;

        // Faça a requisição POST usando fetch
        fetch(url, options)
                .then(function (response) {
                    // Verifique se a resposta foi bem-sucedida
                    if (response.ok) {
                        return response.text(); // Retorna a resposta como texto
                    } else {
                        throw new Error('Erro na requisição: ' + response.status);
                    }
                })
                .then(function (data) {
                    // Crie um elemento div temporário para armazenar a resposta como HTML
                    var tempDiv = document.createElement("div");

                    tempDiv.insertAdjacentHTML("beforeend", data);

                    var tbodyResponse = tempDiv.querySelector("#tbody");
                    var tbodyPagina = document.querySelector("#tbody");
                    console.log(tbodyResponse.innerHTML);
                    tbodyPagina.innerHTML = tbodyResponse.innerHTML;


                })
                .catch(function (error) {
                    // Lógica de tratamento de erros
                    console.error(error);
                });
    });
}

function eventButtonLogout() {
    document.getElementById('formLogout').submit();
}

function getGraphicsCardCont() {
    // Obtém a referência para o elemento canvas
    var canvasElements = document.getElementsByClassName('meuGrafico');

    for (var i = 0; i < canvasElements.length; i++) {
        var ctx = canvasElements[i].getContext('2d');
        new Chart(ctx, {
            type: 'line',
            data: {
                labels: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio'], // Exemplo de rótulos do eixo X
                datasets: [{
                        data: [10, 100, 30, 25, 15], // Exemplo de dados do eixo Y
                        borderWidth: 1, // Espessura da linha
                        borderColor: 'white', // Cor da linha
                        fill: false, // Não preencher área abaixo da linha
                        pointRadius: 0, // Ocultar pontos de dados
                    }]
            },
            options: {
                scales: {
                    x: {
                        display: false // Ocultar o eixo X
                    },
                    y: {
                        display: false // Ocultar o eixo Y
                    }
                },
                plugins: {
                    legend: {
                        display: false // Ocultar a legenda
                    },
                    tooltip: {
                        enabled: false // Desabilitar dicas de ferramentas
                    }
                }
            }
        });
    }

}


function copiarChavePublica() {
    var publicKeyInput = document.getElementById("publicKeyInput");
    publicKeyInput.select();
    publicKeyInput.setSelectionRange(0, 99999); // Para dispositivos móveis

    navigator.clipboard.writeText(publicKeyInput.value).then(function () {
        alert("Chave pública copiada para a área de transferência!");
    }, function () {
        alert("Falha ao copiar a chave pública.");
    });
}



function selectAccount(card) {
    // Remover a classe 'selected' de todos os cards de conta
    var cards = document.getElementsByClassName('account-card');
    for (var i = 0; i < cards.length; i++) {
        cards[i].classList.remove('selected');
    }

    // Adicionar a classe 'selected' ao card de conta selecionado
    card.classList.add('selected');
    var data = card.getAttribute("data");//Pego ID da conta selecionada

    var dados = {
        contaId: data
    };

    // Defina as opções da requisição
    var options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(dados)
    };

    // Especifique a URL da sua servlet
    var url = 'home';

    // Faça a requisição POST usando fetch
    fetch(url, options)
            .then(function (response) {
                // Verifique se a resposta foi bem-sucedida
                if (response.ok) {
                    return response.text(); // Retorna a resposta como texto
                } else {
                    throw new Error('Erro na requisição: ' + response.status);
                }
            })
            .then(function (data) {
                // Lógica de tratamento da resposta da servlet
                location.reload();
            })
            .catch(function (error) {
                // Lógica de tratamento de erros
                console.error(error);
            });

}

function getTransacoes() {

    setInterval(function () {

        var contaSelecionada = document.querySelector(".contaSelecionada");
        var idConta = contaSelecionada.id;

        var options = {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        };

        // Especifique a URL da sua servlet
        var url = 'transacao?idConta=' + idConta;

        // Faça a requisição POST usando fetch
        fetch(url, options)
                .then(function (response) {
                    // Verifique se a resposta foi bem-sucedida
                    if (response.ok) {
                        return response.text(); // Retorna a resposta como texto
                    } else {
                        throw new Error('Erro na requisição: ' + response.status);
                    }
                })
                .then(function (data) {
                    // Crie um elemento div temporário para armazenar a resposta como HTML
                    var tempDiv = document.createElement("div");

                    tempDiv.insertAdjacentHTML("beforeend", data);

                    var tbodyResponse = tempDiv.querySelector("#tbody");
                    var tbodyPagina = document.querySelector("#tbody");

                    //Verifica se as listagem de transações são iguais...
                    if (tbodyResponse.innerHTML === tbodyPagina.innerHTML) {
                    } else {
                        var campoPesquisa = document.getElementById("campoPesquisa");
                        var textoPesquisa = campoPesquisa.value.toLowerCase();
                        if(textoPesquisa === ""){
                            tbodyPagina.innerHTML = tbodyResponse.innerHTML;
                            emitirSomDoSistema();
                        }
                        


                    }

                })
                .catch(function (error) {
                    // Lógica de tratamento de erros
                    console.error(error);
                });
    }, 500);

}

function emitirSomDoSistema() {
    var meuAudio = document.getElementById('meuAudio');
    meuAudio.play();
}





