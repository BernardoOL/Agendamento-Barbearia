package model;

import java.util.ArrayList;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Perfil {
    
    private int idPerfil;
    private String nome;
    private ArrayList<Menu> menus;
    private ArrayList<Menu> naoMenus;
}
