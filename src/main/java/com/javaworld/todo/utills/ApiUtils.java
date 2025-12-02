package com.javaworld.todo.utills;

public class ApiUtils {

	private ApiUtils() {}
	
	public static final String MANDATORY_FIELD= "MANDATORY_FIELD";
	
	public static final String NAME_REGEX= "^[A-Za-z0-9_]+$";
	
	public static final String PASSWOR_RGEX= "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,40}$";
	
	public static final String INVALID_PASSWORD= "Password must contain at least one uppercase letter, one lowercase letter, and one number";

	public static final String INVALID_EMAIL = "Invalid Email";
	
	public static final String INVALID_NAME = "Username can only contain letters, numbers, and underscores";
	
	public static final String UNAUTHORIZED= "You don't have permission to access this resource";

	public static final String USER_NOT_FOUND = "User Not Found";

	public static final String ROLE_NOT_FOUND = "Role Not Found";

	public static final String NOTE_NOT_FOUND = "Note Not Found";

	public static final String USER_ALREADY_EXISTS = "User Already Exists";
	
	public static final String EMAIL_ALREADY_EXISTS = "Email Already Exists";

	public static final String BAD_CREDENTIALS = "Invalid username or password...";
}
