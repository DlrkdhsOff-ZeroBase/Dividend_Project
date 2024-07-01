package zero.dividend.model;

import lombok.Data;
import zero.dividend.model.MemberEntity;

import java.util.List;

public class Auth {

    @Data
    public static class SignIn {
        private String userName;
        private String password;
    }

    @Data
    public static class SignUp {
        private String userName;
        private String password;
        private List<String> roles;

        public MemberEntity toEntity() {
            return MemberEntity.builder()
                    .userName(this.userName)
                    .password(this.password)
                    .roles(this.roles)
                    .build();
        }
    }
}
