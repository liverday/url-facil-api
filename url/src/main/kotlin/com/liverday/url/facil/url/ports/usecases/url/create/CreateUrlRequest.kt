package com.liverday.url.facil.url.ports.usecases.url.create

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CreateUrlRequest(
        @field:NotNull(message = "É preciso enviar um link para ser encurtado")
        @field:NotBlank(message = "O link não pode ser vazio")
        val link: String?,

        val token: String? = null
)