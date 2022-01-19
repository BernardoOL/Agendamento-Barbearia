package model;

import java.util.Date;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Agendamento {

    private int idAgendamento;
    private Cliente cliente;
    private Usuario vendedor;
    private Status status;
    private Servico servico;
    private Date dataHora;
    private String horaAgendamento;

}
