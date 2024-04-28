package com.ebru.service;

import com.ebru.model.Autor;
import com.ebru.model.Buch;
import com.ebru.model.Verlag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class DataInitializer {

    @Value("${verlag.file.path}")
    private String verlagFilePath;

    @Value("${buch.file.path}")
    private String buchFilePath;

    @Value("${autor.file.path}")
    private String autorFilePath;


    public <T> List<T> loadObjects(String filePath, Function<String[], T> mapper)  {
        try(Stream<String> lines = Files.lines(Paths.get(filePath))){
            return lines
                    .map(line -> line.split(";"))
                    .map(mapper)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Verlag> loadVerlage()  {
        return loadObjects(verlagFilePath, data -> new Verlag(Long.parseLong(data[0]), data[1]));
    }

    public List<Buch> loadBuecher() {
        return loadObjects(buchFilePath, data -> new Buch(Long.parseLong(data[0]), data[1], Double.parseDouble(data[2]), data[3], Long.parseLong(data[4])));
    }

    public List<Autor> loadAutoren() {
        return loadObjects(autorFilePath, data -> new Autor(Long.parseLong(data[0]), data[1], data[2], Long.parseLong(data[3])));
    }

    public List<Buch> findBuecherByVerlagId(Long verlagId){
        try(Stream<String> buchLines = Files.lines(Paths.get(buchFilePath))){
            return buchLines
                    .map(line -> line.split(";"))
                    .map(data -> new Buch(Long.parseLong(data[0]), data[1], Double.parseDouble(data[2]), data[3], Long.parseLong(data[4])))
                    .filter(data -> data.getVerlagID().equals(verlagId))
                    .toList();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Autor>findAutorenByBuchId(Long buchId){
        try(Stream<String> lines = Files.lines(Paths.get(autorFilePath))) {
            return lines
                    .map(line -> line.split(";"))
                    .map(data -> new Autor(Long.parseLong(data[0]), data[1], data[2], Long.parseLong(data[3])))
                    .filter(data -> data.getBuchID().equals(buchId))
                    .collect(Collectors.toList());
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Verlag> findVerlagByVerlagId(Long verlagId){
        try(Stream<String> lines = Files.lines(Paths.get(verlagFilePath))){
            return lines
                    .map(line -> line.split(";"))
                    .map(data -> new Verlag(Long.parseLong(data[0]), data[1]))
                    .filter(data->data.getVerlagID().equals(verlagId))
                    .findFirst();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
