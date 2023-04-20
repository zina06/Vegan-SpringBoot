package com.kosa.Catchvegan.Controller;



import com.kosa.Catchvegan.DTO.RestaurantDTO;
import com.kosa.Catchvegan.Service.ManagerService;
import com.kosa.Catchvegan.Service.ReserveService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Log4j2
@RestController
public class FileController {
    @Autowired
    private ManagerService managerService;

    @Autowired
    private ReserveService reserveService;

    @Value("${spring.servlet.multipart.location}")
    private String location;
//    private final static String imageDirectory= Paths.get("").toAbsolutePath()+"/images/";
    private final static String imageDirectory= "C:\\final-vue\\vegan-vue\\src\\assets/uploadimages";

    public FileController() {
        File file = new File(imageDirectory);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    private String getExtension(MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        int index = fileName.indexOf(".");
        if (index > -1) {
            return fileName.substring(index);
        }
        return "";
    }

    @GetMapping("/image/{fileName}")
    public ResponseEntity<Resource> image(@PathVariable String fileName) throws FileNotFoundException {
        String filePath = imageDirectory + fileName;
        InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(filePath));
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(inputStreamResource);
    }

    @GetMapping("/files")
    public List<String> getFileNames() {
        return Stream.of(new File(imageDirectory).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toList());
    }

//    @PostMapping(value = "/files")
//    public void uploadFiles(MultipartFile[] files) {
//        System.out.println(imageDirectory);
//        for (MultipartFile file : files) {
//           String filePath = imageDirectory + File.separator + UUID.randomUUID() + file.getOriginalFilename();
//
//            System.out.println("75line");
//            System.out.println(filePath);
//            System.out.println(UUID.randomUUID());
//            System.out.println(file.getOriginalFilename());
//            try (FileOutputStream writer = new FileOutputStream(filePath)) {
//                writer.write(file.getBytes());
//            } catch (Exception e) {
//                log.error(e.getMessage(), e);
//                throw new RuntimeException("Fail to upload files.");
//            }
//        }
//    }

    @PostMapping ("/file/update")
    public void updateFile(@RequestPart("restaurantDTO") RestaurantDTO restaurantDTO, @RequestParam("file") MultipartFile file , @RequestParam("managerIdx") int managerIdx){
        System.out.println(restaurantDTO);
        RestaurantDTO dto = managerService.getOneRestaurant(managerIdx);
        dto.setName(restaurantDTO.getName());
//        dto.setImages(file.getOriginalFilename());
        dto.setMenu(restaurantDTO.getMenu());
        System.out.println("images : " + dto.getImages());
        System.out.println("file : " + file);
            String filePath = imageDirectory + File.separator + UUID.randomUUID() + file.getOriginalFilename();
        System.out.println(filePath);
            String oldFileName=reserveService.restaurantDetail(dto.getRestaurantIdx()).getImages();
        System.out.println(filePath);
        System.out.println(oldFileName);
            if(file.getOriginalFilename().equals(""))
//                restaurantDTO.setImages(oldFileName);
                dto.setImages(file.getOriginalFilename());
            else{
                String newFileName="n_"+file.getOriginalFilename();
//                restaurantDTO.setImages(newFileName);
                dto.setImages(newFileName);

                try {
                    file.transferTo(new File(imageDirectory + "/" + newFileName));
                }catch(IllegalStateException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            System.out.println("75line");
            System.out.println(filePath);
            System.out.println(UUID.randomUUID());
            System.out.println(file.getOriginalFilename());
//            try (FileOutputStream writer = new FileOutputStream(filePath)) {
//                writer.write(file.getBytes());
//
//            } catch (Exception e) {
//                log.error(e.getMessage(), e);
//                throw new RuntimeException("Fail to upload files.");
//            }
//                 File deletefile=new File(imageDirectory);
//                if(deletefile.exists()) { //해당 경로에 파일이 있는 경우 true 값 반환
//                    deletefile.delete();
//                    System.out.println("파일 삭제 완료");
//                }
            managerService.updateRestaurant(dto);
    }


    @PostMapping(value = "/files")
    public void uploadFiles(MultipartFile[] files, @RequestPart(required = false) RestaurantDTO restaurantDTO) {
        System.out.println(imageDirectory);
        for (MultipartFile file : files) {
            String filePath = imageDirectory + File.separator + UUID.randomUUID() + file.getOriginalFilename();

            System.out.println("75line");
            System.out.println(filePath);
            System.out.println(UUID.randomUUID());
            System.out.println(file.getOriginalFilename());


            managerService.updateRestaurant(restaurantDTO);

            try (FileOutputStream writer = new FileOutputStream(filePath)) {
                writer.write(file.getBytes());
            } catch (Exception e) {
                log.error(e.getMessage(), e);

                throw new RuntimeException("Fail to upload files.");
            }
        }
    }
}
