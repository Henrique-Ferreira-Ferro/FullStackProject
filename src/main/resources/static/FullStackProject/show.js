const princ = document.querySelector("#main");
const tableCont = document.querySelector(".table-container");

function loadTable(usuarios) {
    // Limpa o conteúdo anterior da tabela (caso necessário)

    usuarios.forEach(usuario => {
        let tagTr = document.createElement("tr");

        let nomeTd = document.createElement("td");
        nomeTd.textContent = usuario.nome;
        tagTr.appendChild(nomeTd);

        let emailTd = document.createElement("td");
        emailTd.textContent = usuario.email;
        tagTr.appendChild(emailTd);

        let telefoneTd = document.createElement("td");
        telefoneTd.textContent = usuario.telefone;
        tagTr.appendChild(telefoneTd);

        // Adiciona a linha na tabela (ou div principal)
        tableCont.appendChild(tagTr);
    });
}

function loadConnection() {
    const token = localStorage.getItem('token'); // Obtém o token armazenado

    if (!token) {
        console.log('Token não encontrado. Faça login primeiro.');
        return;
    }

    fetch("http://localhost:8080/usuarios/all", {
        headers: {
            'Authorization': `Bearer ${token}`, // Envia o token de autenticação
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: "GET"
    })
    .then(res => res.json()) // Converte a resposta em JSON
    .then(function(usuarios) {
        loadTable(usuarios); // Chama a função para popular a tabela
    })
    .catch(function(error) {
        console.log('Erro ao carregar os usuários: ', error);
    });
}

// Usa o evento DOMContentLoaded no window, não no elemento principal
window.addEventListener("DOMContentLoaded", function(event) {
    event.preventDefault();
    loadConnection();
});
