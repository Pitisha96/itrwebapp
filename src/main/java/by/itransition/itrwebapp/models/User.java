package by.itransition.itrwebapp.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String social;
    private Date firstDate;
    private Date lastDate;
    private boolean blocked;

    public String getFormatFirstDate(){
        return (new SimpleDateFormat("yyyy.MM.dd").format(firstDate)).toString();
    }
}
