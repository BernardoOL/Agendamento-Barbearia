package model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Menu {
    private int idMenu;
    private String nome;
    private String link;
    private int exibir;
}
