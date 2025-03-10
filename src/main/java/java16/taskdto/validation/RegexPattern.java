package java16.taskdto.validation;

public class RegexPattern {
    public static boolean emailPattern(String email){
        return email.trim().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");
    }
    public static boolean passwordPattern(String password){
        return password.matches("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[@#$%^&+=!]).{8,}$");
    }
}
