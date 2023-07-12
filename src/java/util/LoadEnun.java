/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import enumerator.EstadoCivil;
import enumerator.Sexo;
import enumerator.TipoCliente;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author emanuellima
 */
public class LoadEnun {
    
    public static List<TipoCliente> tipoCliente(){
        List<TipoCliente> list = new ArrayList<>();
        list.add(TipoCliente.EMPRESA);
        list.add(TipoCliente.PARTICULAR);
        return list;
    }
    
    public static List<Sexo> sexo(){
        List<Sexo> list = new ArrayList<>();
        list.add(Sexo.MASCULINO);
        list.add(Sexo.FEMININO);
        return list;
    }
    public static List<EstadoCivil> estadoCivil(){
        List<EstadoCivil> list = new ArrayList<>();
        list.add(EstadoCivil.SOLTEIRO);
        list.add(EstadoCivil.CASADO);
        list.add(EstadoCivil.VIUVO);
        return list;
    }
}
