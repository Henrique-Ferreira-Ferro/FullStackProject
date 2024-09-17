const Iemail = document.querySelector(".email");
const Isenha = document.querySelector(".senha");

const formulario = document.querySelector("form");


function verificarRole(token) {
    const decodedToken = jwt_decode(token); // Decodifica o token JWT
    const roles = decodedToken.roles || []; // Obtém as roles do token
    
    if (roles.includes('ADMIN')) {
        return true; // Usuário é admin
    }
    return false; // Usuário não é admin
}

function exibirPopupSemPermissao(){
    
    // Cria o overlay Escuro
    const overlay = document.createElement("div");
    overlay.classList.add("overlay");

    // Cria o popup
    const popup = document.createElement("div");
    popup.classList.add("popup");

    const message = document.createElement("div");
    const tagP = document.createElement("p");
    tagP.innerHTML = "Você não tem permissão para acessar esta página. Apenas administradores podem acessar.";
    message.appendChild(tagP);

    const closeButton = document.createElement("button");
    closeButton.textContent = "Fechar";
    closeButton.addEventListener("click", () => {
        document.body.removeChild(overlay);
    });

    popup.appendChild(message);
    popup.appendChild(closeButton);
    overlay.appendChild(popup);
    document.body.appendChild(overlay);

}



function logar() {
    fetch("http://localhost:8080/auth/login", {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: "POST",
        body: JSON.stringify({
            "email": Iemail.value,
            "senha": Isenha.value
        })
    })
    .then(response => {
        if(!response.ok){
            if(response.status === 403){
                alert("Usuario não encontrado ou senha incorreta")
            }else{
                throw new Error('Erro ao tentar logar');
            }
        }
        return response.json();
    })
    .then(data => {
        if (data.token) {
            // Armazena o token e redireciona após sucesso
            localStorage.setItem('token', data.token);
            console.log('Token armazenado com sucesso:', data.token);
            
            if (verificarRole(data.token)) {
                // Redireciona para show.html se for admin
                window.location.href = "show.html";
            } else {
                // Exibe mensagem ou redireciona para outra página se não for admin
                exibirPopupSemPermissao();
            }
            // Limpa os campos após o redirecionamento
            limpar();
        } else {
            console.log('Erro ao obter o token:', data);
        }
    })
    .catch(error => {
        console.log('Erro ao fazer login:', error);
    });
}

formulario.addEventListener("submit", function(event) {
    event.preventDefault();
    logar();
});

function limpar() {
    Iemail.value = '';
    Isenha.value = '';
}
