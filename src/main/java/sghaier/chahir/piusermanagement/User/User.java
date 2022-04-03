package sghaier.chahir.piusermanagement.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sghaier.chahir.piusermanagement.registre.token.ConfirmationToken;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class User implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole appUserRole;
    private Boolean locked = false;
    private Boolean enabled = false;
    private String picture ;
    private String adress ;
    private String companyName ;
    private String numTel ;
    private String associationName ;
    private String description ;
    private String cin ;
    private String availability ;
    private int age ;
    private String profession;
    private String niveauetude ;
    private LocalDateTime birthdate;


    public User(String firstName, String lastName, String email, String password, UserRole appUserRole, Boolean locked, Boolean enabled, String picture, String adress, String companyName, String numTel, String associationName, String description, String cin, String availability, int age, String profession, String niveauetude, LocalDateTime birthdate, Set<ConfirmationToken> confirmationTokens) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.appUserRole = appUserRole;
        this.locked = locked;
        this.enabled = enabled;
        this.picture = picture;
        this.adress = adress;
        this.companyName = companyName;
        this.numTel = numTel;
        this.associationName = associationName;
        this.description = description;
        this.cin = cin;
        this.availability = availability;
        this.age = age;
        this.profession = profession;
        this.niveauetude = niveauetude;
        this.birthdate = birthdate;
        this.confirmationTokens = confirmationTokens;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(appUserRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<ConfirmationToken> confirmationTokens;


    public boolean getEnabled() {
        return true;
    }

    public boolean getLocked() {
        return false;
    }


    public String getEmail() {
    return email;
    }






}

