package resources;

public class GoRestCreateUserRequest {

    public String getRequest(String... params) {
        return "{\n" +
                "    \"name\": \"" + params[0] + "\",\n" +
                "    \"email\": \"" + params[1] + "\",\n" +
                "    \"gender\": \"" + params[2] + "\",\n" +
                "    \"status\": \"" + params[3] + "\"\n" +
                "}";
    }
}
