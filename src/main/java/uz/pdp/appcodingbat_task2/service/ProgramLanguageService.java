package uz.pdp.appcodingbat_task2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appcodingbat_task2.entity.ProgramLanguage;
import uz.pdp.appcodingbat_task2.entity.User;
import uz.pdp.appcodingbat_task2.payload.ApiResponse;
import uz.pdp.appcodingbat_task2.repository.ProgramLanguageRepository;
import uz.pdp.appcodingbat_task2.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProgramLanguageService {

    @Autowired
    ProgramLanguageRepository programLanguageRepository;

    @Autowired
    UserRepository userRepository;


    public List<ProgramLanguage> getProgramLanguages() {
        return programLanguageRepository.findAll();

    }

    public ProgramLanguage getProgramLanguageById(Integer id) {

        Optional<ProgramLanguage> optionalProgramLanguage = programLanguageRepository.findById(id);
        return optionalProgramLanguage.orElse(null);
    }

    public ApiResponse deleteProgramLanguage(Integer id) {

        try {
            programLanguageRepository.deleteById(id);
            return new ApiResponse("Deleted", true);

        } catch (Exception e) {
            return new ApiResponse("Program Language not found", false);
        }
    }


    public ApiResponse add(ProgramLanguage programLanguage) {
        Optional<User> optionalUser = userRepository.findById(programLanguage.getUser().getId());
        if (!optionalUser.isPresent()) {
            return new ApiResponse("User not found", false);
        }
        User user = optionalUser.get();

        ProgramLanguage newProgramLanguage = new ProgramLanguage();
        newProgramLanguage.setName(programLanguage.getName());
        newProgramLanguage.setUser(user);
        programLanguageRepository.save(newProgramLanguage);

        return new ApiResponse("Successfully added", true);
    }

    public ApiResponse edit(Integer id, ProgramLanguage programLanguage) {
        Optional<ProgramLanguage> optionalProgramLanguage = programLanguageRepository.findById(id);
        if(!optionalProgramLanguage.isPresent()){
            return new ApiResponse("Pgrogram language not found",false);
        }
        Optional<User> optionalUser = userRepository.findById(programLanguage.getUser().getId());
        if (!optionalUser.isPresent()) {
            return new ApiResponse("User not found", false);
        }
        User user = optionalUser.get();
        ProgramLanguage programLanguage1=new ProgramLanguage();
        programLanguage1.setUser(user);
        programLanguage1.setName(programLanguage.getName());
        programLanguageRepository.save(programLanguage1);
        return new ApiResponse("Successfully edited",true);

    }
}
