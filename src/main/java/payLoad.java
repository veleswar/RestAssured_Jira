public class payLoad {
    public static String createIssue() {
        String createIssuePayLoad = "{\n" +
                "        \n" +
                "        \"fields\": {\n" +
                "            \"project\": {\n" +
                "                \"key\": \"APITEST\"\n" +
                "            },\n" +
                "            \"summary\": \"name incorrect on debit card.\",\n" +
                "            \"description\": \" while testing bug appeared\", \n" +
                "             \"issuetype\": {\n" +
                "                \"name\": \"Bug\"\n" +
                "            }\n" +
                "            \n" +
                "         }\n" +
                "\n" +
                "        }" ;
        return createIssuePayLoad;

    }


    }