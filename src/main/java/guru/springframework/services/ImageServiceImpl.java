package guru.springframework.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService {

    @Override
    public void saveImageFile(Long id, MultipartFile multipartFile) {
        log.debug("Inside ImageService saveImageFile");
        if (multipartFile != null) log.debug("Received a file!");
    }
}
