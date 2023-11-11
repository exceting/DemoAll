package demo.we.media.task;

import java.util.function.Supplier;

public interface Processor<T> {

    void process(Supplier<T> params);
}
