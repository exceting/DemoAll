package demo.sharemer.model.http;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TranslateResponse {
    private String errorCode;
    private String query;
    private String translation;
    private String l;
}
