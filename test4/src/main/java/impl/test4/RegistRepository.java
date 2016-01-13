package impl.test4;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope(value="prototype")
public class RegistRepository {

}
