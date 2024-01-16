package com.senac.listou.dto;

import lombok.*;

@Data
public class UsuarioDTO {

    private String idUsuario;
    private String login;
    private String email;
    private Boolean status;
}
