package br.com.aula.login;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Então;

public class LoginSteps {
    private LoginService loginService;
    private String resultado;

    @Dado("que o sistema está no ar")
    public void sistemaEstaNoAr() {
        loginService = new LoginService();
    }

    @Dado("existe um usuário cadastrado")
    public void existeUmUsuarioCadastrado() {
        // O usuário válido é representado pelos dados usados na tabela de exemplos.
    }

    @Quando("informo {string} e {string}")
    public void informoEmailESenha(String email, String senha) {
        resultado = loginService.autenticar(email, senha);
    }

    @Então("o resultado é {string}")
    public void resultadoEsperado(String resultadoEsperado) {
        assertEquals(resultadoEsperado, resultado);
    }
}
