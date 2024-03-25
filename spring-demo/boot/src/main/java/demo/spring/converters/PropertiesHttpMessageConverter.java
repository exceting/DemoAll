package demo.spring.converters;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.util.Properties;

public class PropertiesHttpMessageConverter extends AbstractHttpMessageConverter<Properties> {

    @Override
    protected boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    protected Properties readInternal(Class<? extends Properties> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    protected void writeInternal(Properties properties, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

    }
}
