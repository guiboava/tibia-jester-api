package io.github.guiboava.tibiajesterapi.controller.dto;

import io.github.guiboava.tibiajesterapi.entity.enums.UserType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(name = "Usuário")
public record UserRequestDTO(

        @NotBlank(message = "O nome é obrigatório.")
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres.")
        @Schema(name = "nome")
        String name,

        @NotBlank(message = "O e-mail é obrigatório.")
        @Email(message = "E-mail inválido.")
        @Schema(name = "email")
        String email,

        @NotBlank(message = "O login de usuário é obrigatório.")
        @Size(min = 5, max = 50, message = "O login de usuário deve ter entre 5 e 50 caracteres.")
        @Schema(name = "login")
        String login,

        @NotNull
        @Schema(name = "tipoUsuario")
        UserType userType,

        @NotBlank(message = "A senha é obrigatória.")
        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres.")
        @Schema(name = "senha")
        String password,

        @NotBlank(message = "A confirmação de senha é obrigatória.")
        @Schema(name = "confirmacaoSenha")
        String confirmPassword
) {
    @Schema(hidden = true)
    @AssertTrue(message = "As senhas não coincidem")
    public boolean isPasswordsMatching() {
        return password != null && password.equals(confirmPassword);
    }
}
