package ait.cohort5860.accounting.dto;

import lombok.*;

import java.util.Set;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Builder
public class UserDto {
    private String login;
    @Setter
    private String password;
    @Setter
    private String firstName;
    @Setter
    private String lastName;
    private Set<String> roles;


}
