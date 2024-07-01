package zero.dividend.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import zero.dividend.model.Auth;
import zero.dividend.model.MemberEntity;
import zero.dividend.persist.MemberRepository;

@Slf4j
@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return this.memberRepository.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("couldn't find user ->" + userName));

    }

    public MemberEntity register(Auth.SignUp member) {
        boolean exists = this.memberRepository.existsByUserName(member.getUserName());

        if (exists) {
            throw new RuntimeException("이미 사용중인 아이디 입니다");
        }

        member.setPassword(this.passwordEncoder.encode(member.getPassword()));
        return this.memberRepository.save(member.toEntity());
    }

    public MemberEntity authenticate(Auth.SignIn member) {
        MemberEntity user = this.memberRepository.findByUserName(member.getUserName())
                                                     .orElseThrow(() -> new RuntimeException("존재하지 않은 ID 입니다"));

        if (!this.passwordEncoder.matches(member.getPassword(), user.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        return user;
    }
}
