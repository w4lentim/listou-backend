package com.senac.listou.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TipoCargo {

    CLIENTE("ROLE_CLIENTE"),
    ADMINISTRADOR("ROLE_ADMINISTRADOR");

    String nomeCargo;

    TipoCargo(String descricao) {
        this.nomeCargo = descricao;
    }
}
