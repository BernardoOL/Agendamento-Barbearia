package model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Servico {
    private int idServico;
    private String nome;
    private double valor;
}
