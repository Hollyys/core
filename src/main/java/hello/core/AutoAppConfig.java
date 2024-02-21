package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( // @Bean같은 수동 설정 정보가 없어도 자동으로 스프링 빈을 등록하는 기능: 컴포넌트 스캔 대상 클래스에 @Component를 붙여줘야한다
        basePackages = "hello.core", // default는 해당 클래스를 포함하는 패키지
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        // 기존 코드들을 최대한 활용하기 위해 이미 Bean에 등록한 것들은 제외하고 등록.
        // 실무에서는 잘 쓰이지 않는 부분. 애초에 ComponentScan을 사용하기 때문
)
public class AutoAppConfig {

//    @Bean(name = "memoryMemberRepository")
//    MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//    }
}