package duynguyen.securityjwt.model;


import duynguyen.securityjwt.constant.MessageConstant;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Result {
    private Status status = Status.SUCCESS;
    private String massage = MessageConstant.SUCCESS;
    private Object data;

    public enum Status {
        SUCCESS, FAILED;
    }
}
