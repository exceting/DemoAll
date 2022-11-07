package io.exceting.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddDraft extends Base {

    @JSONField(name = "media_id")
    private String mediaId;

}
