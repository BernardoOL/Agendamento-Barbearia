
package model;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Cliente {
    private int idCliente;
    private String nome;
    private String telefone;
    private String cpf;
    private String rua;
    private String quadra;
    private String numero;
    private String cep;
    
}
