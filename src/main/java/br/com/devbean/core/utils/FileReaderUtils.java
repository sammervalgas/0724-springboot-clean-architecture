package br.com.devbean.core.utils;

import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.nio.file.Files;
import java.nio.file.Paths;

// DESAFIO => Fazer um aspect para gerar uma anotacao com o @Query puxando o sql de um arquivo
public class FileReaderUtils {

    @SneakyThrows
    public static String read(String fileName) {
        Resource resource = new ClassPathResource(fileName);
        byte[] bytes = Files.readAllBytes(Paths.get(resource.getURI()));
        return new String(bytes);
    }
}