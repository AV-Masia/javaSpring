package fs.spring.vue;

import fs.spring.vue.service.MovieService;
import fs.spring.vue.service.impl.MovieServiceImpl;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Utils {

    MovieService movieService = new MovieServiceImpl();

    public static final String HAS_AUTHORITY_DEVELOPER_WRITE = "hasAuthority('developers:write')";
    public static final String HAS_AUTHORITY_DEVELOPER_READ = "hasAuthority('developers:read')";


}
