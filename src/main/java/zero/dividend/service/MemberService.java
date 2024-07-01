package zero.dividend.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import zero.dividend.exception.impl.AlreadyExistUserException;
import zero.dividend.model.Auth;
import zero.dividend.model.MemberEntity;
import zero.dividend.persist.MemberRepository;


@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return this.memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("존재하지 않은 회원 입니다. -> " + username));
    }

    public MemberEntity register(Auth.SignUp member){
        boolean exists = this.memberRepository.existsByUsername(member.getUsername());

        if (exists){
            throw new AlreadyExistUserException();
        }

        member.setPassword(this.passwordEncoder.encode(member.getPassword()));

        return this.memberRepository.save(member.toEntity());
    }

    public MemberEntity authenticate(Auth.SignIn member){
        MemberEntity user = memberRepository.findByUsername(member.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("존재하지 않은 회원 입니다. -> " + member.getUsername()));


        if(this.passwordEncoder.matches(this.passwordEncoder.encode(member.getPassword()), user.getPassword())){
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
        return user;
    }
}