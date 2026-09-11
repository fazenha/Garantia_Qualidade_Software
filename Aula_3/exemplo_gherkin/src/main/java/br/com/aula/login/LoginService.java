package br.com.aula.login;

public class LoginService {

    public String autenticar(String email, String senha) {
        if ("ana@x.com".equals(email) && "123456".equals(senha)) {
            return "liberado";
        }

        return "negado";
    }
}
