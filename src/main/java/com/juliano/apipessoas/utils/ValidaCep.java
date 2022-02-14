package com.juliano.apipessoas.utils;

public class ValidaCep {
    public static Boolean verificaCep(String cep) {
        var _cep = ValidaDocumento.removeCaracteresEspeciais(cep);
        if(_cep.length() != 8) {
            return false;
        } else {
            return true;
        }
    }

    public static String formataCep(String cep){
        var _parte1 = cep.substring(0,5);
        var _parte2 = cep.substring(5,8);
        return _parte1 + "-" + _parte2;
    }
}
