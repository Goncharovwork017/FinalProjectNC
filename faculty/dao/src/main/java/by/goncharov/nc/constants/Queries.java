package by.goncharov.nc.constants;

/**
 * Created by ivan on 01.06.2017.
 */
public class Queries {

    public static String GET_BY_LOGIN = "from Acc where login = :login";
    public static String CHECK_AUTHORIZATION = "from Acc where login = :login and password = :password";
    public static String REG = "from Acc where login = :login";
    public static String ROLES = "from Roles where rolesname = :rolesname";
    public static String FROM_ROLES = "from Roles";

    public static final String FIND_CHECK_COURSE = "from course where course.id not in" +
            "(select sheetlist.courseid from sheetlist inner join user on user.id = sheetlist.userid " +
            "where user.login = :login";

    public static final String FIND_CURRENT_COURSE = "from sheetlist inner join " +
            "course on course.id = sheetlist.courseid inner join user on user.id = sheetlist.userid" +
            " where user.login = :login";


    public static final String GET_ALL_COURSE_WITH_TEACHER ="from Course where userId = :userId";




    public static final String FIND_ALL_STUDENT_FOR_COURSE = "from sheetlist inner join user on sheetlist.userid = user.id where sheetlist.courseid = :courseid";

//select sheetlist.id,sheetlist.score,sheetlist.shortcomment," +" user.id, user.firstname,user.lastname from sheetlist inner join user on sheetlist.userid = user.id where sheetlist.courseid = :courseid









}
