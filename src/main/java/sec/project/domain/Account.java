package sec.project.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Account extends AbstractPersistable<Long> {

    private String username;
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> authorities;
    
    public Account() {
        
    }
    
    public Account(String username, String password, List<String> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }
    
    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        authorities = new ArrayList<>();
    }
    
    public List<String> getAuthorities() {
        return authorities;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }
}
