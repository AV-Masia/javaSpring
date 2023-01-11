package fs.spring.vue.model.form;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserDTO {

    private String username;
    private List<String> roles;

    public void addRoles(String role){
        roles = new ArrayList<>();
        roles.add(role);
    }
}
