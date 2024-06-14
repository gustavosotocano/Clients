package com.gml.infrastructure.adapters.inbound.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Pattern;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 360000)
@RestController

public class ClienteOldController {

    private static final String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

 //   private final ClientServiceI personService;

    public ClienteOldController( ) {
   // public ClienteController(ClientServiceI personService) {


        //this.personService = personService;
    }
/*
    @GetMapping("/v1/client")
    public List<Client> findAll() {
        return personService.findAll();
    }

    @GetMapping("/v1/client/email/{email}")
    public ResponseEntity<Object> getClientByEmail(@Valid @NotNull @PathVariable String email) {
        Objects.requireNonNull(email, "email is required");

        Client byEmail = personService.findByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(byEmail);


    }

    @GetMapping("/v1/client/sharedKey/{sharedKey}")
    public ResponseEntity<Object> getClientById(@Valid @NotNull @PathVariable String sharedKey) {
        Objects.requireNonNull(sharedKey, "Shared key es requerido");
        //    Assert.isTrue(numeroDocumento<=0, "Numero documento no puede ser null  ");

        Client bySharedKey = personService.findBySharedKey(sharedKey);
        return ResponseEntity.status(HttpStatus.OK).body(bySharedKey);


    }
*/
   /* @PostMapping("/v1/clients")
    public ResponseEntity<Object> addClient(@Valid @RequestBody Client client) {
        Client createdTodo = null;
        try {
            if (client.started().compareTo(client.ended()) > 0) {
                throw new RequestException("002", "Start date cannot be greater than end date");
            }

            validateEmailFormat(client);
            createdTodo = personService.save(client);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);
    }
*/
    /*
    private static void validateEmailFormat(Client client) {
        Matcher matcher = EMAIL_PATTERN.matcher(client.email());
        if (!matcher.matches()) {
            throw new RequestException("003", "Email is not formatted correctly");

        }

    }
    */

/*
    @PutMapping("/v1/client")
    public ResponseEntity<Object> updClient(@Valid @RequestBody Client client) {
        Client createdTodo = null;
        try {
            if (client.started().compareTo(client.ended()) < 0) {
                throw new RequestException("002", "Start date cannot be greater than end date");
            }
            validateEmailFormat(client);


            createdTodo = personService.update(client);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);
    }


    @GetMapping("/v1/exportCSV")
    public void exportCSV(HttpServletResponse response) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
        String fileName = "employee-data.csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName);

        StatefulBeanToCsv<Client> writer = new StatefulBeanToCsvBuilder<Client>(response.getWriter())
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withOrderedResults(true)
                .build();

        writer.write(personService.findAll());
    }
*/
}
