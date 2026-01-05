package fr.esgi.tierlist.application.form;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {
    private String lastname;
    private String firstname;
    private String username;
    private String email;
    private String password;
}
