
package br.com.helpdesklab.backend.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AtualizarChamadoRequest(

    @NotBlank(message = "O titulo e obrigatorio")
    @Size(max = 200, message = "O titulo deve ter no maximo 200 caracteres")
    String titulo,

    @NotBlank(message = "A descricao e obrigatoria")
    String descricao,

    @NotBlank(message = "O status e obrigatorio")
    @Pattern(
        regexp = "ABERTO|EM_ANDAMENTO|RESOLVIDO",
        message = "Status invalido"
    )
    String status

) {}
