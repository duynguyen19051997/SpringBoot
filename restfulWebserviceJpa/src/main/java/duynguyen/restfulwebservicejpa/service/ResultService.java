package duynguyen.restfulwebservicejpa.service;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResultService {
    private Status status = Status.SUCCESS;
    private String massage = String.valueOf(Status.SUCCESS);
    private Object data;

    public enum Status {
        SUCCESS, FAILED;
    }
}
