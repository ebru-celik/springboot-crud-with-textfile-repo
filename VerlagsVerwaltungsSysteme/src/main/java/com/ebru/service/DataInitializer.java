package com.ebru.service;

import com.ebru.model.Autor;
import com.ebru.model.Buch;
import com.ebru.model.Verlag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class DataInitializer implements InitializingBean {

    @Value("${verlag.file.path}")
    private String verlagFilePath;

    @Value("${buch.file.path}")
    private String buchFilePath;

    @Value("${autor.file.path}")
    private String autorFilePath;

    private ConcurrentMap<Long, Autor> autoren_map = new ConcurrentHashMap<>();
    private ConcurrentMap<Long, Buch> buch_map = new ConcurrentHashMap<>();
    private ConcurrentMap<Long, Verlag> verlag_map = new ConcurrentHashMap<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        loadVerlageFromFile().forEach(obj->verlag_map.put(obj.getVerlagID(), obj));
        loadBuecherFromfile().forEach(obj->buch_map.put(obj.getBuchID(),obj));
        loadAutorenFromFile().forEach(obj-> autoren_map.put(obj.getAutorID(), obj));
    }

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

    private List<Verlag> loadVerlageFromFile()  {
        return loadObjects(verlagFilePath, data -> new Verlag(Long.parseLong(data[0]), data[1]));
    }
    private List<Buch> loadBuecherFromfile() {
        return loadObjects(buchFilePath, data -> new Buch(Long.parseLong(data[0]), data[1], Double.parseDouble(data[2]), data[3], Long.parseLong(data[4])));
    }
    private List<Autor> loadAutorenFromFile() {
        return loadObjects(autorFilePath, data -> new Autor(Long.parseLong(data[0]), data[1], data[2], Long.parseLong(data[3])));
    }

    public List<Verlag> loadVerlage()  {
        return verlag_map.values().stream().toList();
    }
    public List<Buch> loadBuecher() {
        return buch_map.values().stream().toList();
    }
    public List<Autor> loadAutoren() {
        return autoren_map.values().stream().toList();
    }

    public List<Buch> findBuecherByVerlagId(Long verlagId){
        return buch_map.values().stream().filter(data -> data.getVerlagID().equals(verlagId)).toList();
    }

    public List<Autor>findAutorenByBuchId(Long buchId){
        return autoren_map.values().stream().filter(data -> data.getBuchID().equals(buchId)).toList();
    }

    public Optional<Verlag> findVerlagByVerlagId(Long verlagId){
        return verlag_map.values().stream().filter(data->data.getVerlagID().equals(verlagId)).findFirst();
    }


}
