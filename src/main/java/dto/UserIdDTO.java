
package dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class UserIdDTO {

    private String email;
    private String firstName;
    private String id;
    private String lastName;
    private String password;
    private String phone;
    private Long userStatus;
    private String username;
}
