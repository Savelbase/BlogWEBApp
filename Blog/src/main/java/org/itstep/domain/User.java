package org.itstep.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(setterPrefix = "with")
public class User {
    private Long id;
    private String login ;
    private String firstName;
    private String lastName;
    private String password ;

    public User(Long id, String login, String password, String firstName, String lastName) {
        this.firstName = firstName ;
        this.lastName = lastName;
        this.login = login;
        this.password = password ;
    }
}
