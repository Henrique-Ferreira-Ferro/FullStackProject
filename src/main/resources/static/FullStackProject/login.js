const Iemail = document.querySelector(".email");
const Isenha = document.querySelector(".senha");

const formulario = document.querySelector("form");

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
    .then(response => response.json())
    .then(data => {
        if (data.token) {
            // Armazena o token e redireciona após sucesso
            localStorage.setItem('token', data.token);
            console.log('Token armazenado com sucesso:', data.token);
            
            // Redireciona para a página show.html
            window.location.href = "show.html";
            
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
