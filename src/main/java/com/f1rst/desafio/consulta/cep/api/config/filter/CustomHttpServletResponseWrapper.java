package com.f1rst.desafio.consulta.cep.api.config.filter;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CustomHttpServletResponseWrapper extends HttpServletResponseWrapper {

    private final ByteArrayOutputStream capture;
    private ServletOutputStream output;

    public CustomHttpServletResponseWrapper(HttpServletResponse response) {
        super(response);
        capture = new ByteArrayOutputStream(response.getBufferSize());
    }

    @Override
    public ServletOutputStream getOutputStream() {
        if (output == null) {
            output = new ServletOutputStream() {
                @Override
                public void write(int b) {
                    capture.write(b);
                }

                @Override
                public boolean isReady() {
                    return true;
                }

                @Override
                public void setWriteListener(WriteListener writeListener) {
                    // No-op
                }
            };
        }

        return output;
    }

    public String getCaptureAsString() throws IOException {
        return new String(getCaptureAsBytes(), StandardCharsets.UTF_8);
    }

    public void copyBodyToResponse() throws IOException {
        HttpServletResponse response = (HttpServletResponse) getResponse();
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(getCaptureAsBytes());
        outputStream.flush();
    }

    public byte[] getCaptureAsBytes() throws IOException {
        if (output != null) {
            output.close();
        }
        return capture.toByteArray();
    }
}
