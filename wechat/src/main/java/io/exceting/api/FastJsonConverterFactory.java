package io.exceting.api;

import com.alibaba.fastjson.JSON;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.http.Streaming;
import retrofit2.internal.EverythingIsNonNull;

import javax.annotation.Nullable;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

public class FastJsonConverterFactory extends Converter.Factory {


    /**
     * Returns true if {@code annotations} contains an instance of {@code cls}.
     */
    static boolean isAnnotationPresent(Annotation[] annotations,
                                       Class<? extends Annotation> cls) {
        for (Annotation annotation : annotations) {
            if (cls.isInstance(annotation)) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    @Override
    @EverythingIsNonNull
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if (type == String.class) {
            return ToStringResponseBodyConverter.INSTANCE;
        }
        if (type == ResponseBody.class) {
            // Streaming body
            if (isAnnotationPresent(annotations, Streaming.class)) {
                return StreamingResponseBodyConverter.INSTANCE;
            }
            // buffered body
            return BufferingResponseBodyConverter.INSTANCE;
        }

        // no response body
        if (type == Void.class) {
            return VoidResponseBodyConverter.INSTANCE;
        }
        return new FastjsonResponseBodyConverter<>(type);
    }

    @Nullable
    @Override
    @EverythingIsNonNull
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
    }

    static final class FastjsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
        private final Type responseType;

        public FastjsonResponseBodyConverter(Type type) {
            this.responseType = type;
        }

        @Override
        public T convert(ResponseBody value) throws IOException {
            final String input = value.string();
            return JSON.parseObject(input, responseType);
        }
    }

    static final class VoidResponseBodyConverter implements Converter<ResponseBody, Void> {
        static final VoidResponseBodyConverter INSTANCE = new VoidResponseBodyConverter();

        @Override
        public Void convert(ResponseBody value) {
            value.close();
            return null;
        }
    }

    static final class StreamingResponseBodyConverter
            implements Converter<ResponseBody, ResponseBody> {
        static final StreamingResponseBodyConverter INSTANCE = new StreamingResponseBodyConverter();

        @Override
        @EverythingIsNonNull
        public ResponseBody convert(ResponseBody value) {
            return value;
        }
    }

    static final class BufferingResponseBodyConverter
            implements Converter<ResponseBody, ResponseBody> {
        static final BufferingResponseBodyConverter INSTANCE = new BufferingResponseBodyConverter();

        @Override
        public ResponseBody convert(ResponseBody value) throws IOException {
            try {
                // Buffer the entire body to avoid future I/O.
                Buffer buffer = new Buffer();
                value.source().readAll(buffer);
                return ResponseBody.create(value.contentType(), value.contentLength(), buffer);
            } finally {
                value.close();
            }
        }
    }

    static final class ToStringResponseBodyConverter implements Converter<ResponseBody, String> {
        static final ToStringResponseBodyConverter INSTANCE = new ToStringResponseBodyConverter();

        @Override
        public String convert(ResponseBody value) throws IOException {
            return value.string();
        }
    }


}