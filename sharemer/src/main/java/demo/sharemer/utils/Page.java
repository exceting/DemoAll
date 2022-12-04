package demo.sharemer.utils;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Setter
public class Page<T> implements Serializable {

    private static final long serialVersionUID = -8327726441443886587L;

    /**
     * 最大页大小
     */
    public static final int MAX_PS = 1000;

    /**
     * 数据
     */
    private List<T> data = Lists.newArrayList();

    /**
     * 页号
     */
    private int pn;

    /**
     * 页大小
     */
    private int ps;

    /**
     * 总条数
     */
    private int total;

    /**
     * 起始行号
     */
    @JSONField(serialize = false)
    private int startRow;

    public Page() {
        this(1, 10, 0);
    }

    public Page(Integer pn, Integer ps) {
        this(pn, ps, 0);
    }

    public Page(Integer pn, Integer ps, int total) {
        this.pn = Optional.ofNullable(pn).orElse(1);
        this.ps = Optional.ofNullable(ps).orElse(50);
        this.total = total;
        this.calculateStartAndEndRow();
    }

    /**
     * @param pn    pageNum
     * @param ps    pageSize
     * @param total 总数
     * @param data  全量list
     */
    public Page(int pn, int ps, int total, List<T> data) {
        this(pn, ps, total);
        if (data != null) {
            this.data = data.stream()
                    .skip(this.startRow)
                    .limit(ps)
                    .collect(Collectors.toList());
        }
    }

    /**
     * 计算起止行号
     */
    private void calculateStartAndEndRow() {
        this.startRow = this.pn > 0 ? (this.pn - 1) * this.ps : 0;
    }

}
