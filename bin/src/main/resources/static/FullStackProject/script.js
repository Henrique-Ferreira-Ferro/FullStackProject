const formulario = document.querySelector("form");

const Inome = document.querySelector(".nome");
const Iusername = document.querySelector(".username");
const Iemail = document.querySelector(".email");
const Isenha = document.querySelector(".senha");
const Itel = document.querySelector(".tel");

const btnCont = document.querySelector("#btn-enviar");

function cadastrar(){

    fetch("http://localhost:8080/auth/register",
        {
            headers: {
                'Accept':'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify(
                {
                    "nome": Inome.value,
                    "username": Iusername.value,
                    "email": Iemail.value,
                    "senha": Isenha.value,
                    "telefone":Itel.value
                }
            )
        }
    )
    .then(function(res){console.log(res)})
    .catch(function(res){console.log(res)})
}


function limpar(){
    Inome.value = "";
    Iusername.value = "";
    Iemail.value = "";
    Isenha.value = "";
    Itel.value = "";
}

formulario.addEventListener('submit', function(event){
    event.preventDefault();
    
    cadastrar();
    limpar();
    window.location.href = "login.html";
});

