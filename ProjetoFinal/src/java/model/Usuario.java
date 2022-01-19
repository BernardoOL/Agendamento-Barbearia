
package model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Usuario {
    private int idUsuario;
    private String nome;
    private String senha;
    private int status;
    private String login;
    private Perfil Perfil;

    
}
