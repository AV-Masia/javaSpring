package com.example.sweater.demo.rest;

import com.example.sweater.demo.model.Developer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.sweater.demo.Utils.HAS_AUTHORITY_DEVELOPER_READ;
import static com.example.sweater.demo.Utils.HAS_AUTHORITY_DEVELOPER_WRITE;

@RestController
@RequestMapping("/api/v1/developers")
public class DeveloperRestControllerV1 {

    private static final List<Developer> DEVELOPERS = Stream.of(
            new Developer(1L, "Ivanov", "Ivanovich"),
            new Developer(2L, "Petr", "Petrovich"),
            new Developer(3L, "Sergey", "Sergeevich")
    ).collect(Collectors.toList());

    @GetMapping
    public List<Developer> getAll(){
        return DEVELOPERS;
    }

    @GetMapping("/{id}")
    @PreAuthorize(HAS_AUTHORITY_DEVELOPER_READ)
    public Developer getById(@PathVariable Long id) {
        return DEVELOPERS.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    @PostMapping
   @PreAuthorize(HAS_AUTHORITY_DEVELOPER_WRITE)
    public Developer createDeveloper(@RequestBody Developer developer) {
        this.DEVELOPERS.add(developer);
        return developer;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(HAS_AUTHORITY_DEVELOPER_WRITE)
    public void deleteById(@PathVariable Long id) {
        this.DEVELOPERS.removeIf(e -> e.getId().equals(id));
    }

//    @RequestMapping(value = "/v1", method = RequestMethod.GET)
//    public ResponseEntity<List<Developer>> getAll() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
//        return new ResponseEntity<List<Developer>>(DEVELOPERS, headers, HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/v1/{id}}", method = RequestMethod.GET)
//    public ResponseEntity<?> getById(@RequestParam String id) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
//        if(id != null) {
//            Developer developer = DEVELOPERS.stream()
//                    .filter(e -> e.getId().equals(Long.parseLong(id)))
//                    .findFirst()
//                    .orElse(null);
//            if(developer != null) {
//                return new ResponseEntity<Developer>(developer, headers, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<String>("not found by id: " + id, headers, HttpStatus.NOT_FOUND);
//            }
//        } else {
//            return new ResponseEntity<String>("request param id is null", headers, HttpStatus.BAD_REQUEST);
//        }
//    }
}
