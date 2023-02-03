package com.sp.spring.catalog.controller;

//import com.devd.spring.bookstorecommons.exception.RunTimeExceptionPlaceHolder;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
    Lot of improvement s can be done in image upload section.
    We can use AWS s3 to store images instead of this.
    For time being will go with no-cost implementation.
 */
@RestController
public class ImageUploadController {

    /*@PostMapping("image/upload")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity<?> uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
        if (file == null) {
            throw new RunTimeExceptionPlaceHolder("Invalid Image!!");
        }
        UUID uuid = UUID.randomUUID();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Files.createDirectories(Paths.get("images"));
        Path path = Paths.get("images/" + uuid.toString() + "__" + fileName);
        Path absolutePath = path.toAbsolutePath();
        try {
            Files.copy(file.getInputStream(), absolutePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, String> response = new HashMap<>();
        response.put("imageId", uuid.toString() + "__" + fileName);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }*/

    @GetMapping(path = "image/{imageId}")
    public ResponseEntity<?> getImage(@PathVariable String imageId) throws IOException {
        /*Stream<Path> list = Files.list(Paths.get("images"));
        list.limit(5).forEach(System.out::println);*/

        /*TODO - Ugly way to render images. Should come from S3 like static repository*/
        Optional<Path> images = Files.list(Paths.get("C:\\Users\\Sachin Pachpute\\IdeaProjects\\spring-security-parent\\catalog-service\\images"))
                .filter(img -> img.getFileName().toString().equals(imageId)).findFirst();

        if (images.isPresent()) {
            final ByteArrayResource inputStream = new ByteArrayResource(Files.readAllBytes(images.get()));
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.IMAGE_JPEG)
                    .contentLength(inputStream.contentLength())
                    .body(inputStream);
        }
        return ResponseEntity.ok().build();
    }
}
