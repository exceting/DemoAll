package demo.sharemer.model;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class Country {

    private Integer id;

    private String name;

    private String cnName;

    private String alias;

    private String icon;

    private Set<String> states;

    private Set<String> cities;

    public void setName(String name) {
        this.name = name.toLowerCase();
    }

    public void setTranslations(String translations) {
        if (!Strings.isNullOrEmpty(translations)) {
            JSONObject ojb = JSONObject.parseObject(translations);
            this.cnName = ojb.getString("cn");
        }
    }
}
