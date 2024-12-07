class Main {
    public static void main(String[] args) {
        String url1 = "https://fake-json-api.mock.beeceptor.com/users";
        String url2 = "https://fake-json-api.mock.beeceptor.com/companies";

        Parser parser = new Parser();

        parser.parseAndPrint(url1);

        System.out.println("\n\n\n");

        parser.parseAndPrint(url2);
    }
}