package hello.core.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
// 적용 대상이 인터페이스가 아닌 클래스면 `TARGET_CLASS` 를 선택
// 적용 대상이 인터페이스면 `INTERFACES` 를 선택
// 웹 스코프는 실제 HTTP request가 있어야 작동하는데 MyLogger의 가짜 프록시 클래스를 만들어두고(CGLIB)
// HTTP request와 상관 없이 가짜 프록시 클래스를 다른 빈에 미리 주입해 둘 수 있다.
// 가짜 프록시 객체는 요청이 오면 그때 내부의 진짜 빈을 요청하는 위임 로직이 들어있다.
// 싱글톤이 아닌 이러한 scope는 무분별하게 사용하면 유지보수가 어려워지므로 최소화해서 사용
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message){
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
    }

    @PostConstruct
    public void init(){
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create: " + this);
    }

    @PreDestroy
    public void close(){
        System.out.println("[" + uuid + "] request scope bean close: " + this);
    }
}
