const Iemail = document.querySelector(".email");
const Isenha = document.querySelector(".senha");

const formulario = document.querySelector("form");

function logar(){

    fetch("http://localhost:8080/auth/login",
        {
            headers:{
                'Accept':'application/json',
                'Content-type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify(
                {
                    "email": Iemail.value,
                    "senha": Isenha.value
                }
            )
        }
    )
    .then(response => response.json())
    .then(data => {
        if(data.token){
            localStorage.setItem('token', data.token);
            console.log('Token armazenado com sucesso: ', data.token);
            
        }else{
            console.log('Erro ao obter o token: ', data);
        }
    })
    .catch(error => {
        console.log('Erro ao fazer login: ', error);
    })
}

formulario.addEventListener("submit", function(event){
    event.preventDefault();

    logar();
    limpar();

})

function limpar(){
    Iemail.value = '';
    Isenha.value = '';
}

