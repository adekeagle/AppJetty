public class Lang {
    private Long id;
    private String welcomeMessage;
    private String code;

    public Lang() {
    }

    public Lang(Long id, String welcomeMessage, String code) {
        this.id = id;
        this.welcomeMessage = welcomeMessage;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Lang{" +
                "id=" + id +
                ", welcomeMessage='" + welcomeMessage + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
