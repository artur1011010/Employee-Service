import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.sql.Date;

@MappedSuperclass
@Setter
@Getter
public abstract class TimeEntity extends BaseEntity {
    private Date addedDate;
}
