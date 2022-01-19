package model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ServicoAgendamento {
   private long idServicoAgendamento;
   private double valorCobrado;   
   private int qtdServicos;
   private Agendamento agendamento;
   private Servico servico;
}
